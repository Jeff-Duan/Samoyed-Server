package com.cherish.demo.entity.warehouse;

import com.cherish.demo.entity.basic.Produce;
import com.cherish.demo.entity.basic.ProduceType;

public class WareHouseProduce {

    private long id;
    private long produceId;
    private long produceTypeId;
    private double produceNumber;

    private Produce produce;
    private ProduceType produceType;
    private double ratio;

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

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
}
