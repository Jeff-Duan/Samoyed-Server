package com.cherish.demo.entity.purchase;

import com.cherish.demo.entity.basic.Material;

public class PurchaseOrderDetail {

    private long detailId;
    private String detailOrderNumber;
    private long detailMaterialId;
    private double detailMaterialPrice;
    private String detailUnitName;
    private double detailMaterialNumber;

    private Material material;

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

    public long getDetailMaterialId() {
        return detailMaterialId;
    }

    public void setDetailMaterialId(long detailMaterialId) {
        this.detailMaterialId = detailMaterialId;
    }

    public double getDetailMaterialPrice() {
        return detailMaterialPrice;
    }

    public void setDetailMaterialPrice(double detailMaterialPrice) {
        this.detailMaterialPrice = detailMaterialPrice;
    }

    public String getDetailUnitName() {
        return detailUnitName;
    }

    public void setDetailUnitName(String detailUnitName) {
        this.detailUnitName = detailUnitName;
    }

    public double getDetailMaterialNumber() {
        return detailMaterialNumber;
    }

    public void setDetailMaterialNumber(double detailMaterialNumber) {
        this.detailMaterialNumber = detailMaterialNumber;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
