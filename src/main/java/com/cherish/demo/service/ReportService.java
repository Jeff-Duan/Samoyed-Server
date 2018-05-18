package com.cherish.demo.service;

import com.cherish.demo.dao.ProduceDao;
import com.cherish.demo.dao.PurchaseDao;
import com.cherish.demo.dao.SaleDao;
import com.cherish.demo.entity.finance.PayRecord;
import com.cherish.demo.entity.finance.ReceivableRecord;
import com.cherish.demo.entity.produce.ProduceOrder;
import com.cherish.demo.entity.purchase.PurchaseOrder;
import com.cherish.demo.entity.sale.SaleOrder;
import com.cherish.demo.entity.warehouse.WareHouseWaste;
import com.cherish.demo.util.WeekDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReportService {

    private static final long[] PURCHASE_ORDER_STATUS_LIST = {1, 2, 3, 4, 5, 6, 7, 8};
    private static final long[] PRODUCE_ORDER_STATUS_LIST = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    private static final long[] SALE_ORDER_STATUS_LIST = {1, 2, 3, 4, 5, 6, 7};

    @Autowired
    FinanceService financeService;

    @Autowired
    WareHouseService wareHouseService;

    @Autowired
    PurchaseDao purchaseDao;

    @Autowired
    ProduceDao produceDao;

    @Autowired
    SaleDao saleDao;

    //统计采购订单
    public List<Map<String, Object>> purchaseNumber() {
        List<Map<String, Object>> list = new ArrayList();
        for (long purchaseOrderStatusId : PURCHASE_ORDER_STATUS_LIST) {
            Map<String, Object> map = new HashMap();
            List<PurchaseOrder> purchaseOrders = purchaseDao.selectAllPurchaseOrder(String.valueOf(purchaseOrderStatusId));
            if (purchaseOrders.size() > 0) {
                map.put("name", purchaseOrders.get(0).getPurchaseOrderStatus().getStatusName());
                map.put("value", purchaseOrders.size());
                list.add(map);
            }
        }
        return list;
    }

    //统计生产订单
    public List<Map<String, Object>> produceNumber() {
        List<Map<String, Object>> list = new ArrayList();
        for (long produceOrderStatusId : PRODUCE_ORDER_STATUS_LIST) {
            Map<String, Object> map = new HashMap();
            List<ProduceOrder> produceOrders = produceDao.selectAllProduceOrder(String.valueOf(produceOrderStatusId));
            if (produceOrders.size() > 0) {
                map.put("name", produceOrders.get(0).getProduceOrderStatus().getStatusName());
                map.put("value", produceOrders.size());
                list.add(map);
            }
        }
        return list;
    }

    //统计销售订单
    public List<Map<String, Object>> saleNumber() {
        List<Map<String, Object>> list = new ArrayList();
        for (long saleOrderStatusId : SALE_ORDER_STATUS_LIST) {
            Map<String, Object> map = new HashMap();
            List<SaleOrder> saleOrders = saleDao.selectAllSaleOrder(String.valueOf(saleOrderStatusId));
            if (saleOrders.size() > 0) {
                map.put("name", saleOrders.get(0).getSaleOrderStatus().getStatusName());
                map.put("value", saleOrders.size());
                list.add(map);
            }
        }
        return list;
    }

    //统计财务收支
    public Map<String, Object> financeNumber() {
        Map<String, Object> map = new HashMap();
        List<Double> list1 = new ArrayList();
        List<Double> list2 = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        WeekDate.getWeekdays().stream().forEach(date -> {
            double payMoney = financeService.getPayRecordByDate(sdf.format(date)).stream().mapToDouble(PayRecord::getRecordPayMoney).sum();
            double receivableMoney = financeService.getReceivableRecordByDate(sdf.format(date)).stream().mapToDouble(ReceivableRecord::getRecordReceivableMoney).sum();
            list1.add(payMoney);
            list2.add(receivableMoney);
        });
        map.put("PayName", "财务付款");
        map.put("PayList", list1);
        map.put("ReceivableName", "财务收款");
        map.put("ReceivableList", list2);
        return map;
    }

    //统计仓储明细
    public Map<String, List> warehouseNumber() {
        Map<String, List> map = new HashMap();
        List<String> list1 = new ArrayList();
        List<Double> list2 = new ArrayList();
        wareHouseService.getAllMaterial().stream().forEach(wareHouseMaterial -> {
            list1.add(wareHouseMaterial.getMaterial().getMaterialName());
            list2.add(wareHouseMaterial.getMaterialNumber());
        });
        wareHouseService.getAllProduce().stream().forEach(wareHouseProduce -> {
            list1.add(wareHouseProduce.getProduce().getProduceName() + "(" + wareHouseProduce.getProduceType().getProduceTypeName() + ")");
            list2.add(wareHouseProduce.getProduceNumber());
        });
        WareHouseWaste wareHouseWaste = wareHouseService.getAllWaste();
        list1.add(wareHouseWaste.getWasteName());
        list2.add(wareHouseWaste.getWasteNumber());
        map.put("Name", list1);
        map.put("Number", list2);
        return map;
    }

}
