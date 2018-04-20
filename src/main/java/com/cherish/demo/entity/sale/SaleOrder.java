package com.cherish.demo.entity.sale;

import java.util.List;

public class SaleOrder {

    private long orderId;
    private String orderNumber;
    private String orderInsertTime;
    private String orderReceivableTime;
    private String orderSendTime;
    private String orderDeliveryAddress;
    private double orderTotalMoney;
    private double orderDepositMoney;
    private String orderRemark;
    private long orderUserId;
    private long orderStatusId;
    private double orderIsReceivableMoney;
    private double ordertoReceivableMoney;

    private List<SaleOrderDetail> saleOrderDetails;

    public SaleOrder(){
        this.orderInsertTime = "1980-01-01";
        this.orderReceivableTime = "1980-01-01";
        this.orderSendTime = "1980-01-01";
        this.orderRemark = "";
        this.orderStatusId = 1;
        this.orderIsReceivableMoney = 0.00;
        this.ordertoReceivableMoney = 0.00;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderInsertTime() {
        return orderInsertTime;
    }

    public void setOrderInsertTime(String orderInsertTime) {
        this.orderInsertTime = orderInsertTime;
    }

    public String getOrderReceivableTime() {
        return orderReceivableTime;
    }

    public void setOrderReceivableTime(String orderReceivableTime) {
        this.orderReceivableTime = orderReceivableTime;
    }

    public String getOrderSendTime() {
        return orderSendTime;
    }

    public void setOrderSendTime(String orderSendTime) {
        this.orderSendTime = orderSendTime;
    }

    public String getOrderDeliveryAddress() {
        return orderDeliveryAddress;
    }

    public void setOrderDeliveryAddress(String orderDeliveryAddress) {
        this.orderDeliveryAddress = orderDeliveryAddress;
    }

    public double getOrderTotalMoney() {
        return orderTotalMoney;
    }

    public void setOrderTotalMoney(double orderTotalMoney) {
        this.orderTotalMoney = orderTotalMoney;
    }

    public double getOrderDepositMoney() {
        return orderDepositMoney;
    }

    public void setOrderDepositMoney(double orderDepositMoney) {
        this.orderDepositMoney = orderDepositMoney;
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    public long getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(long orderUserId) {
        this.orderUserId = orderUserId;
    }

    public long getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(long orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public double getOrderIsReceivableMoney() {
        return orderIsReceivableMoney;
    }

    public void setOrderIsReceivableMoney(double orderIsReceivableMoney) {
        this.orderIsReceivableMoney = orderIsReceivableMoney;
    }

    public double getOrdertoReceivableMoney() {
        return ordertoReceivableMoney;
    }

    public void setOrdertoReceivableMoney(double ordertoReceivableMoney) {
        this.ordertoReceivableMoney = ordertoReceivableMoney;
    }

    public List<SaleOrderDetail> getSaleOrderDetails() {
        return saleOrderDetails;
    }

    public void setSaleOrderDetails(List<SaleOrderDetail> saleOrderDetails) {
        this.saleOrderDetails = saleOrderDetails;
    }
}
