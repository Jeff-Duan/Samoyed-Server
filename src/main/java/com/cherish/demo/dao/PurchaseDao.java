package com.cherish.demo.dao;

import com.cherish.demo.entity.purchase.PurchaseOrder;
import com.cherish.demo.entity.purchase.PurchaseOrderDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseDao {

    void insertPurchaseOrder(PurchaseOrder purchaseOrder);

    void insertPurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail);

    void updatePurchaseOrderStatus(PurchaseOrder purchaseOrder);

    PurchaseOrder selectPurchaseOrderByOrderNumber(String orderNumber);

    List<PurchaseOrder> selectAllPurchaseOrder(String statusId);

    List<PurchaseOrder> selectAllToPayPurchaseOrder();

    List<PurchaseOrder> selectAllAlreadyPayPurchaseOrder();

    List<PurchaseOrderDetail> selectPurchaseOrderDetailByOrderNumber(String orderNumber);

}
