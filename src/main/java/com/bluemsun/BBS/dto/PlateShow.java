package com.bluemsun.BBS.dto;

import com.bluemsun.BBS.entity.User;

import java.util.Date;
import java.util.List;

public class PlateShow {

    private Integer plateId;
    private String plateName;
    private String plateImg;
    private String plateDesc;
    private Date createTime;
    private Integer plateHot;

    private List<User> list;

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

    public String getPlateImg() {
        return plateImg;
    }

    public void setPlateImg(String plateImg) {
        this.plateImg = plateImg;
    }

    public String getPlateDesc() {
        return plateDesc;
    }

    public void setPlateDesc(String plateDesc) {
        this.plateDesc = plateDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPlateHot() {
        return plateHot;
    }

    public void setPlateHot(Integer plateHot) {
        this.plateHot = plateHot;
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PlateShow{" +
                "plateId=" + plateId +
                ", plateName='" + plateName + '\'' +
                ", plateImg='" + plateImg + '\'' +
                ", plateDesc='" + plateDesc + '\'' +
                ", createTime=" + createTime +
                ", plateHot=" + plateHot +
                ", list=" + list +
                '}';
    }
}
