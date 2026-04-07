package com.bedmaster.inventory.dto;

public class BedDTO {

    private Integer bedID;
    private String bedNumber;
    private String bedType;
    private String status;

    public Integer getBedID() {
        return bedID;
    }
    public void setBedID(Integer bedID) {
        this.bedID = bedID;
    }

    public String getBedNumber() {
        return bedNumber;
    }
    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    public String getBedType() {
        return bedType;
    }
    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}