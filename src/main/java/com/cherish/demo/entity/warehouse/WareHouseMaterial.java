package com.cherish.demo.entity.warehouse;

import com.cherish.demo.entity.basic.Material;

public class WareHouseMaterial {

    private long id;
    private long materialId;
    private double materialNumber;

    private Material material;
    private double ratio;

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

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
}
