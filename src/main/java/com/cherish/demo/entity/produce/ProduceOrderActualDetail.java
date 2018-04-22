package com.cherish.demo.entity.produce;

import com.cherish.demo.entity.basic.Produce;
import com.cherish.demo.entity.basic.ProduceType;
import com.cherish.demo.entity.basic.Unit;

public class ProduceOrderActualDetail {

    private long detailId;
    private String detailOrderNumber;
    private long detailProduceId;
    private long detailProduceTypeId;
    private long detailProduceUnitId;
    private double detailProduceNumber;

    private Produce produce;
    private ProduceType produceType;
    private Unit unit;

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

    public Produce getProduce() {
        return produce;
    }

    public void setProduce(Produce produce) {
        this.produce = produce;
    }

    public ProduceType getProduceType() {
        return produceType;
    }

    public void setProduceType(ProduceType produceType) {
        this.produceType = produceType;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
