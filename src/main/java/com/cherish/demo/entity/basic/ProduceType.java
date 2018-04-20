package com.cherish.demo.entity.basic;

public class ProduceType {

    private long produceTypeId;
    private String produceTypeName;

    private double producePrice;
    private Unit unit;

    public long getProduceTypeId() {
        return produceTypeId;
    }

    public void setProduceTypeId(long produceTypeId) {
        this.produceTypeId = produceTypeId;
    }

    public String getProduceTypeName() {
        return produceTypeName;
    }

    public void setProduceTypeName(String produceTypeName) {
        this.produceTypeName = produceTypeName;
    }

    public double getProducePrice() {
        return producePrice;
    }

    public void setProducePrice(double producePrice) {
        this.producePrice = producePrice;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
