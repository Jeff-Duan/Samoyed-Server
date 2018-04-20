package com.cherish.demo.dao;

import com.cherish.demo.entity.sale.SaleOrder;
import com.cherish.demo.entity.sale.SaleOrderDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDao {

    void insertSaleOrder(SaleOrder saleOrder);

    void insertSaleOrderDetail(SaleOrderDetail saleOrderDetail);

}
