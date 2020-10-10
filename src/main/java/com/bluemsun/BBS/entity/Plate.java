package com.bluemsun.BBS.entity;

import java.util.Date;

public class Plate {

    private Integer plateId;
    private String plateName;
    private Date createTime;
    private String plateImg;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPlateImg() {
        return plateImg;
    }

    public void setPlateImg(String plateImg) {
        this.plateImg = plateImg;
    }

    @Override
    public String toString() {
        return "Plate{" +
                "plateId=" + plateId +
                ", plateName='" + plateName + '\'' +
                ", createTime=" + createTime +
                ", plateImg='" + plateImg + '\'' +
                '}';
    }
}
