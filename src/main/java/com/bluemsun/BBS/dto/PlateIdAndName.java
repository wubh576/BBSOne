package com.bluemsun.BBS.dto;

public class PlateIdAndName {

    private Integer plateId;
    private String plateName;

    public Integer getPlateId() {
        return plateId;
    }

    public void setPlateId(Integer plateId) {
        this.plateId = plateId;
    }

    public String getPlateName() {
        return plateName;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    @Override
    public String toString() {
        return "PlateIdAndName{" +
                "plateId=" + plateId +
                ", plateName='" + plateName + '\'' +
                '}';
    }
}
