package com.bluemsun.BBS.dto;

import java.util.Date;

public class BlogPage {

    private Integer blogId;
    private String blogTitle;
    private String plateName;
    private String username;
    private Integer blogHot;
    private Integer blogComment;
    private Integer blogLike;
    private Date createTime;
    private String profileImg;
    private Integer userRank;

    private Integer blogTopping;

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getPlateName() {
        return plateName;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getBlogHot() {
        return blogHot;
    }

    public void setBlogHot(Integer blogHot) {
        this.blogHot = blogHot;
    }

    public Integer getBlogComment() {
        return blogComment;
    }

    public void setBlogComment(Integer blogComment) {
        this.blogComment = blogComment;
    }

    public Integer getBlogLike() {
        return blogLike;
    }

    public void setBlogLike(Integer blogLike) {
        this.blogLike = blogLike;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Integer getBlogTopping() {
        return blogTopping;
    }

    public void setBlogTopping(Integer blogTopping) {
        this.blogTopping = blogTopping;
    }

    @Override
    public String toString() {
        return "BlogPage{" +
                "blogId=" + blogId +
                ", blogTitle='" + blogTitle + '\'' +
                ", plateName='" + plateName + '\'' +
                ", username='" + username + '\'' +
                ", blogHot=" + blogHot +
                ", blogComment=" + blogComment +
                ", blogLike=" + blogLike +
                ", createTime=" + createTime +
                ", profileImg='" + profileImg + '\'' +
                ", userRank=" + userRank +
                ", blogTopping=" + blogTopping +
                '}';
    }
}
