package com.bluemsun.BBS.dto;

import java.util.Date;
import java.util.List;

public class PageCommentAndUser {

    private String username;
    private Integer userId;
    private String profileImg;
    private Integer userRank;

    private Integer firstCommentId;
    private String firstContent;
    private Date firstCreateTime;
    private Integer firstCommentLike;

    private List<SecondCommentAndUser> secondCommentAndUser;
    private Integer secondCommentAndUserCount;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public Integer getUserRank() {
        return userRank;
    }

    public void setUserRank(Integer userRank) {
        this.userRank = userRank;
    }

    public Integer getFirstCommentId() {
        return firstCommentId;
    }

    public void setFirstCommentId(Integer firstCommentId) {
        this.firstCommentId = firstCommentId;
    }

    public String getFirstContent() {
        return firstContent;
    }

    public void setFirstContent(String firstContent) {
        this.firstContent = firstContent;
    }

    public Date getFirstCreateTime() {
        return firstCreateTime;
    }

    public void setFirstCreateTime(Date firstCreateTime) {
        this.firstCreateTime = firstCreateTime;
    }

    public Integer getFirstCommentLike() {
        return firstCommentLike;
    }

    public void setFirstCommentLike(Integer firstCommentLike) {
        this.firstCommentLike = firstCommentLike;
    }

    public List<SecondCommentAndUser> getSecondCommentAndUser() {
        return secondCommentAndUser;
    }

    public void setSecondCommentAndUser(List<SecondCommentAndUser> secondCommentAndUser) {
        this.secondCommentAndUser = secondCommentAndUser;
    }

    public Integer getSecondCommentAndUserCount() {
        return secondCommentAndUserCount;
    }

    public void setSecondCommentAndUserCount(Integer secondCommentAndUserCount) {
        this.secondCommentAndUserCount = secondCommentAndUserCount;
    }

    @Override
    public String toString() {
        return "PageCommentAndUser{" +
                "username='" + username + '\'' +
                ", userId=" + userId +
                ", profileImg='" + profileImg + '\'' +
                ", userRank=" + userRank +
                ", firstCommentId=" + firstCommentId +
                ", firstContent='" + firstContent + '\'' +
                ", firstCreateTime=" + firstCreateTime +
                ", firstCommentLike=" + firstCommentLike +
                ", secondCommentAndUser=" + secondCommentAndUser +
                ", secondCommentAndUserCount=" + secondCommentAndUserCount +
                '}';
    }
}
