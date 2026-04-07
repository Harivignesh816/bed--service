package com.bedmaster.inventory.dto;

public class UnitDTO {

    private Integer unitID;
    private String name;
    private String specialty;
    private Integer capacity;
    private String status;

    public Integer getUnitID() {
        return unitID;
    }
    public void setUnitID(Integer unitID) {
        this.unitID = unitID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Integer getCapacity() {
        return capacity;
    }
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}