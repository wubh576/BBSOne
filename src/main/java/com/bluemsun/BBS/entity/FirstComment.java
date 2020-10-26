package com.bluemsun.BBS.entity;

import java.util.Date;

/**
 * 一级评论
 */
public class FirstComment {

    //主键id
    private Integer firstCommentId;
    //谁的评论
    private Integer firstUserId;
    //与用户相关联
    private User user;
    //评论内容
    private String firstContent;
    //评论的创建时间
    private Date firstCreateTime;
    //评论的点赞数
    private Integer firstCommentLike;
    //该评论所属博客的id
    private Integer firstBlogId;

    public Integer getFirstCommentId() {
        return firstCommentId;
    }

    public void setFirstCommentId(Integer firstCommentId) {
        this.firstCommentId = firstCommentId;
    }

    public Integer getFirstUserId() {
        return firstUserId;
    }

    public void setFirstUserId(Integer firstUserId) {
        this.firstUserId = firstUserId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Integer getFirstBlogId() {
        return firstBlogId;
    }

    public void setFirstBlogId(Integer firstBlogId) {
        this.firstBlogId = firstBlogId;
    }

    @Override
    public String toString() {
        return "FirstComment{" +
                "firstCommentId=" + firstCommentId +
                ", firstUserId=" + firstUserId +
                ", user=" + user +
                ", firstContent='" + firstContent + '\'' +
                ", firstCreateTime=" + firstCreateTime +
                ", firstCommentLike=" + firstCommentLike +
                ", firstBlogId=" + firstBlogId +
                '}';
    }
}
