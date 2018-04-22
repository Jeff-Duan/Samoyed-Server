package com.cherish.demo.dao;

import com.cherish.demo.entity.sale.SaleOrder;
import com.cherish.demo.entity.sale.SaleOrderDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleDao {

    void insertSaleOrder(SaleOrder saleOrder);

    void insertSaleOrderDetail(SaleOrderDetail saleOrderDetail);

    List<SaleOrder> selectAllSaleOrder(String statusId);

    List<SaleOrderDetail> selectSaleOrderDetailByOrderNumber(String orderNumber);

}
