package com.cherish.demo.entity.produce;

import com.cherish.demo.entity.basic.Material;
import com.cherish.demo.entity.basic.Produce;
import com.cherish.demo.entity.basic.Unit;

public class ProduceOrderPlanDetail {

    private long detailId;
    private String detailOrderNumber;
    private long detailProduceId;
    private long detailProduceUnitId;
    private double detailProduceNumber;
    private long detailMaterialId;
    private long detailMaterialUnitId;
    private double detailMaterialNumber;

    private Produce produce;
    private Unit produceUnit;
    private Material material;
    private Unit materialUnit;

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

    public Produce getProduce() {
        return produce;
    }

    public void setProduce(Produce produce) {
        this.produce = produce;
    }

    public Unit getProduceUnit() {
        return produceUnit;
    }

    public void setProduceUnit(Unit produceUnit) {
        this.produceUnit = produceUnit;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Unit getMaterialUnit() {
        return materialUnit;
    }

    public void setMaterialUnit(Unit materialUnit) {
        this.materialUnit = materialUnit;
    }
}
