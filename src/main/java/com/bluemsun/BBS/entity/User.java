package com.bluemsun.BBS.entity;

import java.util.Date;

public class User {
    // 主键ID
    private Long userId;
    // 用户名称
    private String username;
    // 用户密码
    private String password;
    // 用户头像
    private String profileImg;
    // 用户邮箱
    private String email;
    // 用户电话
    private String phone;
    // 用户类别：0表示普通用户，1表示超级管理员
    private Integer userType;
    // 创建时间
    private Date createTime;
    // 最近一次的更新时间
    private Date lastEditTime;
    // 用户等级
    private Integer userRank;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
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

    public Integer getUserRank() {
        return userRank;
    }

    public void setUserRank(Integer userRank) {
        this.userRank = userRank;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", profileImg='" + profileImg + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", userType=" + userType +
                ", createTime=" + createTime +
                ", lastEditTime=" + lastEditTime +
                ", userRank=" + userRank +
                '}';
    }
}
