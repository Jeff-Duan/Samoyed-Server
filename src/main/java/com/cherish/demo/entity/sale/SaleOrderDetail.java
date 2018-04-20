package com.cherish.demo.entity.sale;

public class SaleOrderDetail {

    private long detailId;
    private String detailOrderNumber;
    private long detailProduceId;
    private long detailProduceTypeId;
    private double detailProducePrice;
    private long detailProduceUnitId;
    private double detailProduceNumber;

    public long getDetailId() {
        return detailId;
    }

    public void setDetailId(long detailId) {
        this.detailId = detailId;
    }

    public String getDetailOrderNumber() {
        return detailOrderNumber;
    }

    public void setDetailOrderNumber(String detailOrderNumber) {
        this.detailOrderNumber = detailOrderNumber;
    }

    public long getDetailProduceId() {
        return detailProduceId;
    }

    public void setDetailProduceId(long detailProduceId) {
        this.detailProduceId = detailProduceId;
    }

    public long getDetailProduceTypeId() {
        return detailProduceTypeId;
    }

    public void setDetailProduceTypeId(long detailProduceTypeId) {
        this.detailProduceTypeId = detailProduceTypeId;
    }

    public double getDetailProducePrice() {
        return detailProducePrice;
    }

    public void setDetailProducePrice(double detailProducePrice) {
        this.detailProducePrice = detailProducePrice;
    }

    public long getDetailProduceUnitId() {
        return detailProduceUnitId;
    }

    public void setDetailProduceUnitId(long detailProduceUnitId) {
        this.detailProduceUnitId = detailProduceUnitId;
    }

    public double getDetailProduceNumber() {
        return detailProduceNumber;
    }

    public void setDetailProduceNumber(double detailProduceNumber) {
        this.detailProduceNumber = detailProduceNumber;
    }
}
