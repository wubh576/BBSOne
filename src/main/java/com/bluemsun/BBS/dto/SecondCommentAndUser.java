package com.bluemsun.BBS.dto;

import java.util.Date;

public class SecondCommentAndUser {

    private String username;
    private Integer userId;
    private String profileImg;
    private Integer userRank;

    private Integer secondCommentId;
    private String secondContent;
    private Date secondCreateTime;
    private Integer secondCommentLike;
    private Integer secondFirstId;

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

    public Integer getSecondCommentId() {
        return secondCommentId;
    }

    public void setSecondCommentId(Integer secondCommentId) {
        this.secondCommentId = secondCommentId;
    }

    public String getSecondContent() {
        return secondContent;
    }

    public void setSecondContent(String secondContent) {
        this.secondContent = secondContent;
    }

    public Date getSecondCreateTime() {
        return secondCreateTime;
    }

    public void setSecondCreateTime(Date secondCreateTime) {
        this.secondCreateTime = secondCreateTime;
    }

    public Integer getSecondCommentLike() {
        return secondCommentLike;
    }

    public void setSecondCommentLike(Integer secondCommentLike) {
        this.secondCommentLike = secondCommentLike;
    }

    public Integer getSecondFirstId() {
        return secondFirstId;
    }

    public void setSecondFirstId(Integer secondFirstId) {
        this.secondFirstId = secondFirstId;
    }

    @Override
    public String toString() {
        return "CommentAndUser{" +
                "username='" + username + '\'' +
                ", userId=" + userId +
                ", profileImg='" + profileImg + '\'' +
                ", userRank=" + userRank +
                ", secondCommentId=" + secondCommentId +
                ", secondContent='" + secondContent + '\'' +
                ", secondCreateTime=" + secondCreateTime +
                ", secondCommentLike=" + secondCommentLike +
                ", secondFirstId=" + secondFirstId +
                '}';
    }
}
