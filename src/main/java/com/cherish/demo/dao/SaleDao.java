package com.cherish.demo.dao;

import com.cherish.demo.entity.sale.SaleOrder;
import com.cherish.demo.entity.sale.SaleOrderDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleDao {

    void insertSaleOrder(SaleOrder saleOrder);

    void insertSaleOrderDetail(SaleOrderDetail saleOrderDetail);

    void updateSaleOrderStatus(SaleOrder saleOrder);

    void updateSaleOrderMoney(SaleOrder saleOrder);

    void deleteSaleOrder(String orderNumber);

    void deleteSaleOrderDetail(String orderNumber);

    SaleOrder selectSaleOrderByOrderNumber(String orderNumber);

    List<SaleOrder> selectAllSaleOrder(String statusId);

    List<SaleOrder> selectAllIsPayDepositSaleOrder();

    List<SaleOrder> selectAllIsPayFinalSaleOrder();

    List<SaleOrder> selectAllIsSuccessSaleOrder();

    List<SaleOrderDetail> selectSaleOrderDetailByOrderNumber(String orderNumber);

}
