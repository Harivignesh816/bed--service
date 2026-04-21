package com.bedmaster.inventory.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Bed response")
public class BedResponseDTO {

    @Schema(description = "Bed ID", example = "300")
    private Integer bedID;

    @Schema(description = "Bed number", example = "B-01")
    private String bedNumber;

    @Schema(description = "Bed type", example = "STANDARD")
    private String bedType;

    @Schema(description = "Bed status", example = "AVAILABLE")
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