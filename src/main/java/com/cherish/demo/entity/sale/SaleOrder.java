package com.cherish.demo.entity.sale;

import com.cherish.demo.entity.finance.PayRecord;
import com.cherish.demo.entity.finance.ReceivableRecord;
import com.cherish.demo.entity.user.User;

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
    private double orderToReceivableMoney;

    private List<SaleOrderDetail> saleOrderDetails;
    private SaleOrderStatus saleOrderStatus;
    private User user;
    private List<PayRecord> payRecords;
    private List<ReceivableRecord> receivableRecords;


    public SaleOrder() {
        this.orderInsertTime = "1980-01-01";
        this.orderReceivableTime = "1980-01-01";
        this.orderSendTime = "1980-01-01";
        this.orderRemark = "";
        this.orderStatusId = 1;
        this.orderIsReceivableMoney = 0.00;
        this.orderToReceivableMoney = 0.00;
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
        return orderInsertTime.substring(0, 10);
    }

    public void setOrderInsertTime(String orderInsertTime) {
        this.orderInsertTime = orderInsertTime.substring(0, 10);
    }

    public String getOrderReceivableTime() {
        return orderReceivableTime.substring(0, 10);
    }

    public void setOrderReceivableTime(String orderReceivableTime) {
        this.orderReceivableTime = orderReceivableTime.substring(0, 10);
    }

    public String getOrderSendTime() {
        return orderSendTime.substring(0, 10);
    }

    public void setOrderSendTime(String orderSendTime) {
        this.orderSendTime = orderSendTime.substring(0, 10);
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

    public double getOrderToReceivableMoney() {
        return orderToReceivableMoney;
    }

    public void setOrderToReceivableMoney(double orderToReceivableMoney) {
        this.orderToReceivableMoney = orderToReceivableMoney;
    }

    public List<SaleOrderDetail> getSaleOrderDetails() {
        return saleOrderDetails;
    }

    public void setSaleOrderDetails(List<SaleOrderDetail> saleOrderDetails) {
        this.saleOrderDetails = saleOrderDetails;
    }

    public SaleOrderStatus getSaleOrderStatus() {
        return saleOrderStatus;
    }

    public void setSaleOrderStatus(SaleOrderStatus saleOrderStatus) {
        this.saleOrderStatus = saleOrderStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PayRecord> getPayRecords() {
        return payRecords;
    }

    public void setPayRecords(List<PayRecord> payRecords) {
        this.payRecords = payRecords;
    }

    public List<ReceivableRecord> getReceivableRecords() {
        return receivableRecords;
    }

    public void setReceivableRecords(List<ReceivableRecord> receivableRecords) {
        this.receivableRecords = receivableRecords;
    }
}
