package com.cherish.demo.service;

import com.cherish.demo.dao.PurchaseDao;
import com.cherish.demo.dao.WareHouseDao;
import com.cherish.demo.entity.purchase.PurchaseOrder;
import com.cherish.demo.entity.warehouse.WareHouseMaterial;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WareHouseService {

    private static final Logger logger = Logger.getLogger(WareHouseService.class);
    public static final String RESULT_SUCCESS = "SUCCESS";
    public static final String RESULT_ERROR = "ERROR";

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    PurchaseDao purchaseDao;

    @Autowired
    WareHouseDao wareHouseDao;

    //单位转换-输出为公斤
    public double conversion(long unitId, double number) {
        if (unitId == 1) {
            return number / 2;
        } else if (unitId == 3) {
            return number / 1000;
        } else {
            return number;
        }
    }

    /*
     * 仓储入库-采购
     * */

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
            Optional<PurchaseOrder> purchaseOrderOptional = Optional.ofNullable(purchaseService.getOne(orderNumber));
            if (purchaseOrderOptional.isPresent()) {
                PurchaseOrder purchaseOrder = new PurchaseOrder();
                purchaseOrder.setOrderNumber(purchaseOrderOptional.get().getOrderNumber());
                purchaseOrder.setOrderStatusId(4);
                purchaseDao.updatePurchaseOrderStatus(purchaseOrder);
            }
        }
        return RESULT_SUCCESS;
    }

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
            }
        }
        return RESULT_SUCCESS;
    }

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
            Optional<PurchaseOrder> purchaseOrderOptional = Optional.ofNullable(purchaseService.getOne(orderNumber));
            if (purchaseOrderOptional.isPresent()) {
                PurchaseOrder purchaseOrder = new PurchaseOrder();
                purchaseOrder.setOrderNumber(purchaseOrderOptional.get().getOrderNumber());
                purchaseOrder.setOrderStatusId(5);
                purchaseDao.updatePurchaseOrderStatus(purchaseOrder);
            }
        }
        return RESULT_SUCCESS;
    }

    public String purchaseStorage(String orderNumber) {
        Optional<PurchaseOrder> purchaseOrderOptional = Optional.ofNullable(purchaseService.getOne(orderNumber));
        if (purchaseOrderOptional.isPresent()) {
            purchaseOrderOptional.get().getPurchaseOrderDetails().stream().forEach(purchaseOrderDetail -> {
                WareHouseMaterial wareHouseMaterial = new WareHouseMaterial();
                wareHouseMaterial.setMaterialId(purchaseOrderDetail.getDetailMaterialId());
                wareHouseMaterial.setMaterialNumber(conversion(purchaseOrderDetail.getDetailUnitId(), purchaseOrderDetail.getDetailMaterialNumber()));
                wareHouseDao.updateWareHouseMaterial(wareHouseMaterial);
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
            Optional<PurchaseOrder> purchaseOrderOptional = Optional.ofNullable(purchaseService.getOne(orderNumber));
            if (purchaseOrderOptional.isPresent()) {
                purchaseOrderOptional.get().getPurchaseOrderDetails().stream().forEach(purchaseOrderDetail -> {
                    WareHouseMaterial wareHouseMaterial = new WareHouseMaterial();
                    wareHouseMaterial.setMaterialId(purchaseOrderDetail.getDetailMaterialId());
                    wareHouseMaterial.setMaterialNumber(conversion(purchaseOrderDetail.getDetailUnitId(), purchaseOrderDetail.getDetailMaterialNumber()));
                    wareHouseDao.updateWareHouseMaterial(wareHouseMaterial);
                });
                PurchaseOrder purchaseOrder = new PurchaseOrder();
                purchaseOrder.setOrderNumber(purchaseOrderOptional.get().getOrderNumber());
                purchaseOrder.setOrderStatusId(7);
                purchaseDao.updatePurchaseOrderStatus(purchaseOrder);
            }
        }
        return RESULT_SUCCESS;
    }


}
