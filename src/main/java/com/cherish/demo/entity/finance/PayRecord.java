package com.cherish.demo.entity.finance;

public class PayRecord {

    private long id;
    private String orderNumber;
    private String recordInsertTime;
    private long recordUserId;
    private double recordPayMoney;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getRecordInsertTime() {
        return recordInsertTime;
    }

    public void setRecordInsertTime(String recordInsertTime) {
        this.recordInsertTime = recordInsertTime;
    }

    public long getRecordUserId() {
        return recordUserId;
    }

    public void setRecordUserId(long recordUserId) {
        this.recordUserId = recordUserId;
    }

    public double getRecordPayMoney() {
        return recordPayMoney;
    }

    public void setRecordPayMoney(double recordPayMoney) {
        this.recordPayMoney = recordPayMoney;
    }
}
