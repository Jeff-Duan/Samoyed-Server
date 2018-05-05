package com.cherish.demo.entity.warehouse;

public class WareHouseProduce {

    private long id;
    private long produceId;
    private long produceTypeId;
    private double produceNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProduceId() {
        return produceId;
    }

    public void setProduceId(long produceId) {
        this.produceId = produceId;
    }

    public long getProduceTypeId() {
        return produceTypeId;
    }

    public void setProduceTypeId(long produceTypeId) {
        this.produceTypeId = produceTypeId;
    }

    public double getProduceNumber() {
        return produceNumber;
    }

    public void setProduceNumber(double produceNumber) {
        this.produceNumber = produceNumber;
    }
}
