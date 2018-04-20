package com.cherish.demo.entity.basic;

public class Material {

    private long materialId;
    private String materialName;
    private double materialPrice;

    private Unit unit;

    public long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(long materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public double getMaterialPrice() {
        return materialPrice;
    }

    public void setMaterialPrice(double materialPrice) {
        this.materialPrice = materialPrice;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
