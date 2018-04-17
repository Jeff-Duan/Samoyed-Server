package com.cherish.demo.dao;

import com.cherish.demo.entity.purchase.PurchaseOrder;
import com.cherish.demo.entity.purchase.PurchaseOrderDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseDao {

    void insertPurchaseOrder(PurchaseOrder purchaseOrder);

    void insertPurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail);

    List<PurchaseOrder> selectAllPurchaseOrder();

}
