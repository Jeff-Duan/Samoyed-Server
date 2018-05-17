package com.cherish.demo.service;

import com.cherish.demo.dao.ProduceDao;
import com.cherish.demo.dao.PurchaseDao;
import com.cherish.demo.dao.SaleDao;
import com.cherish.demo.dao.WareHouseDao;
import com.cherish.demo.entity.produce.ProduceOrder;
import com.cherish.demo.entity.purchase.PurchaseOrder;
import com.cherish.demo.entity.sale.SaleOrder;
import com.cherish.demo.entity.warehouse.WareHouseMaterial;
import com.cherish.demo.entity.warehouse.WareHouseProduce;
import com.cherish.demo.entity.warehouse.WareHouseWaste;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class WareHouseService {

    private static final Logger logger = Logger.getLogger(WareHouseService.class);
    public static final String RESULT_SUCCESS = "SUCCESS";
    public static final String RESULT_ERROR = "ERROR";
    public static final String RESULT_NOT_ENOUGH = "NOT_ENOUGH";

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    ProduceService produceService;

    @Autowired
    SaleService saleService;

    @Autowired
    PurchaseDao purchaseDao;

    @Autowired
    ProduceDao produceDao;

    @Autowired
    SaleDao saleDao;

    @Autowired
    WareHouseDao wareHouseDao;

    @Autowired
    Gson gson;

    //单位转换-输出为公斤
    public double conversion(long unitId, double number) {
        if (unitId == 1) {
            return number / 2;
        } else if (unitId == 3) {
            return number * 1000;
        } else {
            return number;
        }
    }

    //计算比率
    public String ratio(double minNumber, double maxNumber) {
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        return numberFormat.format((float) minNumber / (float) maxNumber * 100);
    }

    /*
     * 仓储入库-采购
     * */

    @Transactional
    public String purchaseDelivery(String orderNumber) {
        Optional<PurchaseOrder> purchaseOrderOptional = Optional.ofNullable(purchaseService.getOne(orderNumber));
        if (purchaseOrderOptional.isPresent()) {
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setOrderNumber(purchaseOrderOptional.get().getOrderNumber());
            purchaseOrder.setOrderStatusId(4);
            purchaseDao.updatePurchaseOrderStatus(purchaseOrder);
            return RESULT_SUCCESS;
        }
        return RESULT_ERROR;
    }

    public String purchaseBatchDelivery(String[] orderNumbers) {
        for (String orderNumber : orderNumbers) {
            String result = purchaseDelivery(orderNumber);
            if (RESULT_ERROR.equals(result)) {
                return RESULT_ERROR;
            }
        }
        return RESULT_SUCCESS;
    }

    @Transactional
    public String purchaseCheck(String orderNumber) {
        Optional<PurchaseOrder> purchaseOrderOptional = Optional.ofNullable(purchaseService.getOne(orderNumber));
        if (purchaseOrderOptional.isPresent()) {
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            if (purchaseOrderOptional.get().getOrderPayType() == 1) {
                //直接付款
                purchaseOrder.setOrderNumber(purchaseOrderOptional.get().getOrderNumber());
                purchaseOrder.setOrderStatusId(6);
            } else if (purchaseOrderOptional.get().getOrderPayType() == 2) {
                //货到付款
                purchaseOrder.setOrderNumber(purchaseOrderOptional.get().getOrderNumber());
                purchaseOrder.setOrderStatusId(2);
            } else {
                //支付定金
                purchaseOrder.setOrderNumber(purchaseOrderOptional.get().getOrderNumber());
                purchaseOrder.setOrderStatusId(2);
            }
            purchaseDao.updatePurchaseOrderStatus(purchaseOrder);
            return RESULT_SUCCESS;
        }
        return RESULT_ERROR;
    }

    public String purchaseBatchCheck(String[] orderNumbers) {
        for (String orderNumber : orderNumbers) {
            String result = purchaseCheck(orderNumber);
            if (RESULT_ERROR.equals(result)) {
                return RESULT_ERROR;
            }
        }
        return RESULT_SUCCESS;
    }

    @Transactional
    public String purchaseReturn(String orderNumber) {
        Optional<PurchaseOrder> purchaseOrderOptional = Optional.ofNullable(purchaseService.getOne(orderNumber));
        if (purchaseOrderOptional.isPresent()) {
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setOrderNumber(purchaseOrderOptional.get().getOrderNumber());
            purchaseOrder.setOrderStatusId(5);
            purchaseDao.updatePurchaseOrderStatus(purchaseOrder);
            return RESULT_SUCCESS;
        }
        return RESULT_ERROR;
    }

    public String purchaseBatchReturn(String[] orderNumbers) {
        for (String orderNumber : orderNumbers) {
            String result = purchaseReturn(orderNumber);
            if (RESULT_ERROR.equals(result)) {
                return RESULT_ERROR;
            }
        }
        return RESULT_SUCCESS;
    }

    @Transactional
    public String purchaseStorage(String orderNumber) {
        Optional<PurchaseOrder> purchaseOrderOptional = Optional.ofNullable(purchaseService.getOne(orderNumber));
        if (purchaseOrderOptional.isPresent()) {
            purchaseOrderOptional.get().getPurchaseOrderDetails().stream().forEach(purchaseOrderDetail -> {
                WareHouseMaterial wareHouseMaterial = new WareHouseMaterial();
                wareHouseMaterial.setMaterialId(purchaseOrderDetail.getDetailMaterialId());
                wareHouseMaterial.setMaterialNumber(conversion(purchaseOrderDetail.getDetailUnitId(), purchaseOrderDetail.getDetailMaterialNumber()));
                wareHouseDao.updateAddWareHouseMaterial(wareHouseMaterial);
            });
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setOrderNumber(purchaseOrderOptional.get().getOrderNumber());
            purchaseOrder.setOrderStatusId(7);
            purchaseDao.updatePurchaseOrderStatus(purchaseOrder);
            return RESULT_SUCCESS;
        }
        return RESULT_ERROR;
    }

    public String purchaseBatchStorage(String[] orderNumbers) {
        for (String orderNumber : orderNumbers) {
            String result = purchaseStorage(orderNumber);
            if (RESULT_ERROR.equals(result)) {
                return RESULT_ERROR;
            }
        }
        return RESULT_SUCCESS;
    }

    /*
     *仓储入库-生产
     */

    @Transactional
    public String produceStorage(String orderNumber) {
        Optional<ProduceOrder> produceOrderOptional = Optional.ofNullable(produceService.getOne(orderNumber));
        if (produceOrderOptional.isPresent()) {
            produceOrderOptional.get().getProduceOrderActualDetails().forEach(produceOrderActualDetail -> {
                WareHouseProduce wareHouseProduce = new WareHouseProduce();
                wareHouseProduce.setProduceId(produceOrderActualDetail.getDetailProduceId());
                wareHouseProduce.setProduceTypeId(produceOrderActualDetail.getDetailProduceTypeId());
                wareHouseProduce.setProduceNumber(produceOrderActualDetail.getDetailProduceNumber());
                wareHouseDao.updateAddWareHouseProduce(wareHouseProduce);
            });
            WareHouseWaste wareHouseWaste = new WareHouseWaste();
            wareHouseWaste.setWasteNumber(conversion(produceOrderOptional.get().getOrderWasteUnitId(), produceOrderOptional.get().getOrderWasteNumber()));
            wareHouseDao.updateAddWareHouseWaste(wareHouseWaste);
            ProduceOrder produceOrder = new ProduceOrder();
            produceOrder.setOrderNumber(produceOrderOptional.get().getOrderNumber());
            produceOrder.setOrderStatusId(13);
            produceDao.updateProduceOrderStatus(produceOrder);
            return RESULT_SUCCESS;
        }
        return RESULT_ERROR;
    }

    public String produceBatchStorage(String[] orderNumbers) {
        for (String orderNumber : orderNumbers) {
            String result = produceStorage(orderNumber);
            if (RESULT_ERROR.equals(result)) {
                return RESULT_ERROR;
            }
        }
        return RESULT_SUCCESS;
    }

    /*
     *仓储出库-生产
     */

    @Transactional
    public String produceIssue(String orderNumber) {
        AtomicBoolean bool = new AtomicBoolean(true);
        Optional<ProduceOrder> produceOrderOptional = Optional.ofNullable(produceService.getOne(orderNumber));
        if (produceOrderOptional.isPresent()) {
            produceOrderOptional.get().getProduceOrderPlanDetails().stream().forEach(produceOrderPlanDetail -> {
                //需求量
                double number1 = conversion(produceOrderPlanDetail.getDetailMaterialUnitId(), produceOrderPlanDetail.getDetailMaterialNumber());
                //库存量
                double number2 = wareHouseDao.selectWareHouseMaterial(String.valueOf(produceOrderPlanDetail.getMaterial().getMaterialId())).getMaterialNumber();
                //判断库存
                if (number2 - number1 < 0 && bool.get()) {
                    bool.set(false);
                }
            });
            if (bool.get()) {
                produceOrderOptional.get().getProduceOrderPlanDetails().stream().forEach(produceOrderPlanDetail -> {
                    //减少库存
                    WareHouseMaterial wareHouseMaterial = new WareHouseMaterial();
                    wareHouseMaterial.setMaterialId(produceOrderPlanDetail.getDetailMaterialId());
                    wareHouseMaterial.setMaterialNumber(conversion(produceOrderPlanDetail.getDetailMaterialUnitId(), produceOrderPlanDetail.getDetailMaterialNumber()));
                    wareHouseDao.updateReduceWareHouseMaterial(wareHouseMaterial);
                });
                //修改订单状态
                ProduceOrder produceOrder = new ProduceOrder();
                produceOrder.setOrderNumber(produceOrderOptional.get().getOrderNumber());
                produceOrder.setOrderStatusId(3);
                produceDao.updateProduceOrderStatus(produceOrder);
            }
        }
        if (bool.get()) {
            return RESULT_SUCCESS;
        } else {
            return RESULT_NOT_ENOUGH;
        }
    }

    public String produceBatchIssue(String[] orderNumbers) {
        for (String orderNumber : orderNumbers) {
            String result = produceIssue(orderNumber);
            if (RESULT_NOT_ENOUGH.equals(result)) {
                return RESULT_NOT_ENOUGH;
            }
        }
        return RESULT_SUCCESS;
    }

    /*
     *仓储出库-销售
     */

    @Transactional
    public String saleDeliver(String orderNumber) throws RuntimeException {
        AtomicBoolean bool = new AtomicBoolean(true);
        Optional<SaleOrder> saleOrderOptional = Optional.ofNullable(saleService.getOne(orderNumber));
        if (saleOrderOptional.isPresent()) {
            saleOrderOptional.get().getSaleOrderDetails().stream().forEach(saleOrderDetail -> {
                //需求量
                double number1 = conversion(saleOrderDetail.getDetailProduceUnitId(), saleOrderDetail.getDetailProduceNumber());
                //库存量
                WareHouseProduce wareHouseProduce = new WareHouseProduce();
                wareHouseProduce.setProduceId(saleOrderDetail.getDetailProduceId());
                wareHouseProduce.setProduceTypeId(saleOrderDetail.getDetailProduceTypeId());
                double number2 = wareHouseDao.selectWareHouseProduce(wareHouseProduce).getProduceNumber();
                //判断库存
                if (number2 - number1 < 0 && bool.get()) {
                    bool.set(false);
                }
            });
            if (bool.get()) {
                saleOrderOptional.get().getSaleOrderDetails().stream().forEach(saleOrderDetail -> {
                    //减少库存
                    WareHouseProduce wareHouseProduce = new WareHouseProduce();
                    wareHouseProduce.setProduceId(saleOrderDetail.getDetailProduceId());
                    wareHouseProduce.setProduceTypeId(saleOrderDetail.getDetailProduceTypeId());
                    wareHouseProduce.setProduceNumber(conversion(saleOrderDetail.getDetailProduceUnitId(), saleOrderDetail.getDetailProduceNumber()));
                    wareHouseDao.updateReduceWareHouseProduce(wareHouseProduce);
                });
                //修改订单状态
                SaleOrder saleOrder = new SaleOrder();
                saleOrder.setOrderNumber(saleOrderOptional.get().getOrderNumber());
                saleOrder.setOrderStatusId(4);
                saleDao.updateSaleOrderStatus(saleOrder);
            }
        }
        if (bool.get()) {
            return RESULT_SUCCESS;
        } else {
            return RESULT_NOT_ENOUGH;
        }
    }

    public String saleBatchDeliver(String[] orderNumbers) {
        for (String orderNumber : orderNumbers) {
            String result = saleDeliver(orderNumber);
            if (RESULT_NOT_ENOUGH.equals(result)) {
                return RESULT_NOT_ENOUGH;
            }
        }
        return RESULT_SUCCESS;
    }

    @Transactional
    public String saleBack(String orderNumber) {
        Optional<SaleOrder> saleOrderOptional = Optional.ofNullable(saleService.getOne(orderNumber));
        if (saleOrderOptional.isPresent()) {
            saleOrderOptional.get().getSaleOrderDetails().forEach(saleOrderDetail -> {
                WareHouseProduce wareHouseProduce = new WareHouseProduce();
                wareHouseProduce.setProduceId(saleOrderDetail.getDetailProduceId());
                wareHouseProduce.setProduceTypeId(saleOrderDetail.getDetailProduceTypeId());
                wareHouseProduce.setProduceNumber(conversion(saleOrderDetail.getDetailProduceUnitId(), saleOrderDetail.getDetailProduceNumber()));
                wareHouseDao.updateAddWareHouseProduce(wareHouseProduce);
            });
            SaleOrder saleOrder = new SaleOrder();
            saleOrder.setOrderNumber(saleOrderOptional.get().getOrderNumber());
            saleOrder.setOrderStatusId(5);
            saleDao.updateSaleOrderStatus(saleOrder);
            return RESULT_SUCCESS;
        }
        return RESULT_ERROR;
    }

    public String saleBatchBack(String[] orderNumbers) {
        for (String orderNumber : orderNumbers) {
            String result = saleBack(orderNumber);
            if (RESULT_NOT_ENOUGH.equals(result)) {
                return RESULT_NOT_ENOUGH;
            }
        }
        return RESULT_SUCCESS;
    }

    /*
     *仓储明细
     */

    @Transactional
    public String updateMaterial(String data){
        WareHouseMaterial wareHouseMaterial = gson.fromJson(data,WareHouseMaterial.class);
        wareHouseDao.updateWareHouseMaterial(wareHouseMaterial);
        return RESULT_SUCCESS;
    }

    @Transactional
    public String updateProduce(String data){
        WareHouseProduce wareHouseProduce = gson.fromJson(data,WareHouseProduce.class);
        wareHouseDao.updateWareHouseProduce(wareHouseProduce);
        return RESULT_SUCCESS;
    }

    @Transactional
    public String updateWaste(String data){
        WareHouseWaste wareHouseWaste = gson.fromJson(data,WareHouseWaste.class);
        wareHouseDao.updateWareHouseWaste(wareHouseWaste);
        return RESULT_SUCCESS;
    }

    public List<WareHouseMaterial> getAllMaterial() {
        AtomicReference<Double> materialCount = new AtomicReference<>(0.00);
        List<WareHouseMaterial> wareHouseMaterials = wareHouseDao.selectAllWareHouseMaterial();
        wareHouseMaterials.stream().forEach(wareHouseMaterial -> {
            materialCount.updateAndGet(v -> new Double((double) (v + wareHouseMaterial.getMaterialNumber())));
        });
        wareHouseMaterials.stream().forEach(wareHouseMaterial -> {
            wareHouseMaterial.setRatio(Double.valueOf( ratio(wareHouseMaterial.getMaterialNumber(),materialCount.get())));
        });
        return wareHouseMaterials;
    }

    public List<WareHouseProduce> getAllProduce() {
        AtomicReference<Double> produceCount = new AtomicReference<>(0.00);
        List<WareHouseProduce> wareHouseProduces = wareHouseDao.selectAllWareHouseProduce();
        wareHouseProduces.stream().forEach(wareHouseProduce -> {
            produceCount.updateAndGet(v -> new Double((double) (v + wareHouseProduce.getProduceNumber())));
        });
        wareHouseProduces.stream().forEach(wareHouseProduce -> {
            wareHouseProduce.setRatio(Double.valueOf(ratio(wareHouseProduce.getProduceNumber(),produceCount.get())));
        });
        return wareHouseProduces;
    }

    public WareHouseWaste getAllWaste() {
        WareHouseWaste wareHouseWaste = wareHouseDao.selectWareHouseWaste();
        wareHouseWaste.setRatio(100.00);
        return wareHouseWaste;
    }

}
