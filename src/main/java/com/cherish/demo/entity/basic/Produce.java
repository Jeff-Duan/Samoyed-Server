package com.cherish.demo.entity.basic;

import java.util.List;

public class Produce {

    private long produceId;
    private String produceName;
    private long produceMaterialId;


    private Material material;
    private List<ProduceType> produceTypes;


    public long getProduceId() {
        return produceId;
    }

    public void setProduceId(long produceId) {
        this.produceId = produceId;
    }

    public String getProduceName() {
        return produceName;
    }

    public void setProduceName(String produceName) {
        this.produceName = produceName;
    }

    public long getProduceMaterialId() {
        return produceMaterialId;
    }

    public void setProduceMaterialId(long produceMaterialId) {
        this.produceMaterialId = produceMaterialId;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public List<ProduceType> getProduceTypes() {
        return produceTypes;
    }

    public void setProduceTypes(List<ProduceType> produceTypes) {
        this.produceTypes = produceTypes;
    }
}
