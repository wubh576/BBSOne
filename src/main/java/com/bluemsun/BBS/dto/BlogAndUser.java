package com.bluemsun.BBS.dto;

import java.util.Date;

public class BlogAndUser {

    private Integer blogId;
    private String blogTitle;
    private Integer blogAuthorId;
    private Date createTime;
    private String blogContent;

    private String username;
    private String profileImg;
    private Integer userRank;

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

    public Integer getBlogAuthorId() {
        return blogAuthorId;
    }

    public void setBlogAuthorId(Integer blogAuthorId) {
        this.blogAuthorId = blogAuthorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return "BlogAndUser{" +
                "blogId=" + blogId +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogAuthorId=" + blogAuthorId +
                ", createTime=" + createTime +
                ", blogContent='" + blogContent + '\'' +
                ", username='" + username + '\'' +
                ", profileImg='" + profileImg + '\'' +
                ", userRank=" + userRank +
                '}';
    }
}