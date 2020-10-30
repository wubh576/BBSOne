package com.bluemsun.BBS.dto;

import java.util.Date;
import java.util.List;

public class PlatePageForAdmin {

    //主键板块id
    private Integer plateId;
    //板块名称
    private String plateName;
    //板块图片
    private String plateImg;
    //板块描述
    private String plateDesc;
    //创建时间
    private Date createTime;
    //最新更改时间
    private Date lastEditTime;
    //板块置顶
    private Integer plateTopping;
    //板块热度
    private Integer plateHot;
    //板块状态
    private Integer plateStatus;
    //板块状态描述
    private String plateStatusInfo;
    //该板块的版主列表
    private List list;

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

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Integer getPlateTopping() {
        return plateTopping;
    }

    public void setPlateTopping(Integer plateTopping) {
        this.plateTopping = plateTopping;
    }

    public Integer getPlateHot() {
        return plateHot;
    }

    public void setPlateHot(Integer plateHot) {
        this.plateHot = plateHot;
    }

    public Integer getPlateStatus() {
        return plateStatus;
    }

    public void setPlateStatus(Integer plateStatus) {
        this.plateStatus = plateStatus;
    }

    public String getPlateStatusInfo() {
        return plateStatusInfo;
    }

    public void setPlateStatusInfo(String plateStatusInfo) {
        this.plateStatusInfo = plateStatusInfo;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PlatePageForAdmin{" +
                "plateId=" + plateId +
                ", plateName='" + plateName + '\'' +
                ", plateImg='" + plateImg + '\'' +
                ", plateDesc='" + plateDesc + '\'' +
                ", createTime=" + createTime +
                ", lastEditTime=" + lastEditTime +
                ", plateTopping=" + plateTopping +
                ", plateHot=" + plateHot +
                ", plateStatus=" + plateStatus +
                ", plateStatusInfo='" + plateStatusInfo + '\'' +
                ", list=" + list +
                '}';
    }
}
