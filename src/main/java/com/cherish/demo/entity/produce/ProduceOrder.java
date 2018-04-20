package com.cherish.demo.entity.produce;

import java.util.List;

public class ProduceOrder {

    private long orderId;
    private String orderNumber;
    private String orderInsertTime;
    private String orderStartTime;
    private String orderEndTime;
    private String orderRemark;
    private long orderUserId;
    private long orderStatusId;

    private List<ProduceOrderDetail> produceOrderDetails;

    public ProduceOrder() {
        this.orderInsertTime = "1980-01-01";
        this.orderStartTime = "1980-01-01";
        this.orderEndTime = "1980-01-01";
        this.orderRemark = "";
        this.orderStatusId = 1;
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

    public String getOrderStartTime() {
        return orderStartTime;
    }

    public void setOrderStartTime(String orderStartTime) {
        this.orderStartTime = orderStartTime;
    }

    public String getOrderEndTime() {
        return orderEndTime;
    }

    public void setOrderEndTime(String orderEndTime) {
        this.orderEndTime = orderEndTime;
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

    public List<ProduceOrderDetail> getProduceOrderDetails() {
        return produceOrderDetails;
    }

    public void setProduceOrderDetails(List<ProduceOrderDetail> produceOrderDetails) {
        this.produceOrderDetails = produceOrderDetails;
    }
}
