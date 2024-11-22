package com.aurora.database.models;

public class MaterialDirection {
    private String building;
    private String floorBuilding;
    private String shelCode;
    private String  shelFloor;



    public MaterialDirection(String building, String floorBuilding, String shelCode, String shelFloor) {
        this.building = building;
        this.floorBuilding = floorBuilding;
        this.shelCode = shelCode;
        this.shelFloor = shelFloor;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloorBuilding() {
        return floorBuilding;
    }

    public void setFloorBuilding(String floorBuilding) {
        this.floorBuilding = floorBuilding;
    }

    public String getShelCode() {
        return shelCode;
    }

    public void setShelCode(String shelCode) {
        this.shelCode = shelCode;
    }

    public String getShelFloor() {
        return shelFloor;
    }

    public void setShelFloor(String shelFloor) {
        this.shelFloor = shelFloor;
    }
    @Override
    public String toString() {
        return "MaterialDirection{" +
                "building='" + building + '\'' +
                ", floorBuilding='" + floorBuilding + '\'' +
                ", shelCode='" + shelCode + '\'' +
                ", shelFloor='" + shelFloor + '\'' +
                '}';
    }
}
