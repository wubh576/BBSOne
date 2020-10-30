package com.bluemsun.BBS.entity;

public class LikeBlog {

    private Integer userId;
    private Integer blogId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    @Override
    public String toString() {
        return "LikeBlog{" +
                "userId=" + userId +
                ", blogId=" + blogId +
                '}';
    }
}
