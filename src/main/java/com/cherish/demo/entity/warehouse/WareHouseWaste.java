package com.cherish.demo.entity.warehouse;

public class WareHouseWaste {

    private long id;
    private String wasteName;
    private double wasteNumber;

    private double ratio;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWasteName() {
        return wasteName;
    }

    public void setWasteName(String wasteName) {
        this.wasteName = wasteName;
    }

    public double getWasteNumber() {
        return wasteNumber;
    }

    public void setWasteNumber(double wasteNumber) {
        this.wasteNumber = wasteNumber;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
}
