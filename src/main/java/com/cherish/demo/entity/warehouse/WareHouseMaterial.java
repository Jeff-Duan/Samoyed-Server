package com.cherish.demo.entity.warehouse;

public class WareHouseMaterial {

    private long id;
    private long materialId;
    private double materialNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(long materialId) {
        this.materialId = materialId;
    }

    public double getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(double materialNumber) {
        this.materialNumber = materialNumber;
    }
}
