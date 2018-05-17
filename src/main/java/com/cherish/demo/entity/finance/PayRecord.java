package com.cherish.demo.entity.finance;

import com.cherish.demo.entity.user.User;

public class PayRecord {

    private long id;
    private String orderNumber;
    private String recordInsertTime;
    private long recordUserId;
    private double recordPayMoney;

    private User user;

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
        return recordInsertTime.substring(0, 10);
    }

    public void setRecordInsertTime(String recordInsertTime) {
        this.recordInsertTime = recordInsertTime.substring(0, 10);
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
