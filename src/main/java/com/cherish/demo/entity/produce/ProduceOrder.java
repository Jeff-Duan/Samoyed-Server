package com.cherish.demo.entity.produce;

import com.cherish.demo.entity.basic.Unit;
import com.cherish.demo.entity.user.User;

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
    private double orderWasteNumber;
    private long orderWasteUnitId;

    private List<ProduceOrderPlanDetail> produceOrderPlanDetails;
    private List<ProduceOrderActualDetail> produceOrderActualDetails;
    private ProduceOrderStatus produceOrderStatus;
    private User user;
    private Unit unit;

    public ProduceOrder() {
        this.orderInsertTime = "1980-01-01";
        this.orderStartTime = "1980-01-01";
        this.orderEndTime = "1980-01-01";
        this.orderRemark = "";
        this.orderStatusId = 1;
        this.orderWasteNumber = 0;
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

    public String getOrderStartTime() {
        return orderStartTime.substring(0, 10);
    }

    public void setOrderStartTime(String orderStartTime) {
        this.orderStartTime = orderStartTime.substring(0, 10);
    }

    public String getOrderEndTime() {
        return orderEndTime.substring(0, 10);
    }

    public void setOrderEndTime(String orderEndTime) {
        this.orderEndTime = orderEndTime.substring(0, 10);
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

    public double getOrderWasteNumber() {
        return orderWasteNumber;
    }

    public void setOrderWasteNumber(double orderWasteNumber) {
        this.orderWasteNumber = orderWasteNumber;
    }

    public long getOrderWasteUnitId() {
        return orderWasteUnitId;
    }

    public void setOrderWasteUnitId(long orderWasteUnitId) {
        this.orderWasteUnitId = orderWasteUnitId;
    }

    public List<ProduceOrderPlanDetail> getProduceOrderPlanDetails() {
        return produceOrderPlanDetails;
    }

    public void setProduceOrderPlanDetails(List<ProduceOrderPlanDetail> produceOrderPlanDetails) {
        this.produceOrderPlanDetails = produceOrderPlanDetails;
    }

    public List<ProduceOrderActualDetail> getProduceOrderActualDetails() {
        return produceOrderActualDetails;
    }

    public void setProduceOrderActualDetails(List<ProduceOrderActualDetail> produceOrderActualDetails) {
        this.produceOrderActualDetails = produceOrderActualDetails;
    }

    public ProduceOrderStatus getProduceOrderStatus() {
        return produceOrderStatus;
    }

    public void setProduceOrderStatus(ProduceOrderStatus produceOrderStatus) {
        this.produceOrderStatus = produceOrderStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
