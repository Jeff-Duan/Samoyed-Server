package com.cherish.demo.entity.produce;

public class ProduceOrderDetail {

    private long detailId;
    private String detailOrderNumber;
    private long detailProduceId;
    private long detailProduceUnitId;
    private double detailProduceNumber;
    private long detailMaterialId;
    private long detailMaterialUnitId;
    private double detailMaterialNumber;

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

    public long getDetailMaterialId() {
        return detailMaterialId;
    }

    public void setDetailMaterialId(long detailMaterialId) {
        this.detailMaterialId = detailMaterialId;
    }

    public long getDetailMaterialUnitId() {
        return detailMaterialUnitId;
    }

    public void setDetailMaterialUnitId(long detailMaterialUnitId) {
        this.detailMaterialUnitId = detailMaterialUnitId;
    }

    public double getDetailMaterialNumber() {
        return detailMaterialNumber;
    }

    public void setDetailMaterialNumber(double detailMaterialNumber) {
        this.detailMaterialNumber = detailMaterialNumber;
    }
}
