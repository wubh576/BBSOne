package com.bluemsun.BBS.entity;

public class PlateAndUser {

    private Integer userId;
    private String username;

    private Integer plateId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPlateId() {
        return plateId;
    }

    public void setPlateId(Integer plateId) {
        this.plateId = plateId;
    }

    @Override
    public String toString() {
        return "PlateAndUser{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", plateId=" + plateId +
                '}';
    }
}
