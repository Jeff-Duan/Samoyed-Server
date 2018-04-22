package com.cherish.demo.entity.purchase;

import com.cherish.demo.entity.basic.Supplier;
import com.cherish.demo.entity.user.User;

import java.util.List;

public class PurchaseOrder {

    private long orderId;
    private String orderNumber;
    private String orderInsertTime;
    private String orderPayTime;
    private String orderDeliveryTime;
    private String orderDeliveryAddress;
    private long orderPayType;
    private double orderPayMoney;
    private String orderPayBank;
    private double orderTotalMoney;
    private String orderRemark;
    private long orderSupplierId;
    private long orderUserId;
    private long orderStatusId;
    private double orderIsPayMoney;
    private double orderToPayMoney;

    private List<PurchaseOrderDetail> purchaseOrderDetails;
    private PurchaseOrderStatus purchaseOrderStatus;
    private Supplier supplier;
    private User user;

    public PurchaseOrder() {
        this.orderInsertTime = "1980-01-01";
        this.orderPayTime = "1980-01-01";
        this.orderDeliveryTime = "1980-01-01";
        this.orderRemark = "";
        this.orderStatusId = 1;
        this.orderIsPayMoney = 0.00;
        this.orderToPayMoney = 0.00;
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

    public String getOrderPayTime() {
        return orderPayTime.substring(0, 10);
    }

    public void setOrderPayTime(String orderPayTime) {
        this.orderPayTime = orderPayTime.substring(0, 10);
    }

    public String getOrderDeliveryTime() {
        return orderDeliveryTime.substring(0, 10);
    }

    public void setOrderDeliveryTime(String orderDeliveryTime) {
        this.orderDeliveryTime = orderDeliveryTime.substring(0, 10);
    }

    public String getOrderDeliveryAddress() {
        return orderDeliveryAddress;
    }

    public void setOrderDeliveryAddress(String orderDeliveryAddress) {
        this.orderDeliveryAddress = orderDeliveryAddress;
    }

    public long getOrderPayType() {
        return orderPayType;
    }

    public void setOrderPayType(long orderPayType) {
        this.orderPayType = orderPayType;
    }

    public double getOrderPayMoney() {
        return orderPayMoney;
    }

    public void setOrderPayMoney(double orderPayMoney) {
        this.orderPayMoney = orderPayMoney;
    }

    public String getOrderPayBank() {
        return orderPayBank;
    }

    public void setOrderPayBank(String orderPayBank) {
        this.orderPayBank = orderPayBank;
    }

    public double getOrderTotalMoney() {
        return orderTotalMoney;
    }

    public void setOrderTotalMoney(double orderTotalMoney) {
        this.orderTotalMoney = orderTotalMoney;
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    public long getOrderSupplierId() {
        return orderSupplierId;
    }

    public void setOrderSupplierId(long orderSupplierId) {
        this.orderSupplierId = orderSupplierId;
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

    public double getOrderIsPayMoney() {
        return orderIsPayMoney;
    }

    public void setOrderIsPayMoney(double orderIsPayMoney) {
        this.orderIsPayMoney = orderIsPayMoney;
    }

    public double getOrderToPayMoney() {
        return orderToPayMoney;
    }

    public void setOrderToPayMoney(double orderToPayMoney) {
        this.orderToPayMoney = orderToPayMoney;
    }

    public List<PurchaseOrderDetail> getPurchaseOrderDetails() {
        return purchaseOrderDetails;
    }

    public void setPurchaseOrderDetails(List<PurchaseOrderDetail> purchaseOrderDetails) {
        this.purchaseOrderDetails = purchaseOrderDetails;
    }

    public PurchaseOrderStatus getPurchaseOrderStatus() {
        return purchaseOrderStatus;
    }

    public void setPurchaseOrderStatus(PurchaseOrderStatus purchaseOrderStatus) {
        this.purchaseOrderStatus = purchaseOrderStatus;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
