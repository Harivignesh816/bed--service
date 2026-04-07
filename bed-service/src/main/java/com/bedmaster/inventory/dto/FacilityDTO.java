package com.bedmaster.inventory.dto;

public class FacilityDTO {

    private Integer facilityID;
    private String name;
    private String campus;
    private String status;

    public Integer getFacilityID() {
        return facilityID;
    }
    public void setFacilityID(Integer facilityID) {
        this.facilityID = facilityID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCampus() {
        return campus;
    }
    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}