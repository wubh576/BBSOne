package com.bluemsun.BBS.dto;

import java.util.List;

public class DelModerator {

    private Integer userId;
    private List plateIdList;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List getPlateIdList() {
        return plateIdList;
    }

    public void setPlateIdList(List plateIdList) {
        this.plateIdList = plateIdList;
    }

    @Override
    public String toString() {
        return "DelModerator{" +
                "userId=" + userId +
                ", plateIdList=" + plateIdList +
                '}';
    }
}
