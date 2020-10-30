package com.bluemsun.BBS.dto;

import java.util.List;

public class ListDto {

    private List list;
    private Integer userId;

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ListDto{" +
                "list=" + list +
                ", userId=" + userId +
                '}';
    }
}
