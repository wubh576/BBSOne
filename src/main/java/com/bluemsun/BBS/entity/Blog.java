package com.bluemsun.BBS.entity;

import java.util.Date;

public class Blog {

    //主键ID
    private Integer blogId;
    //博客题目
    private String blogTitle;
    //博客内容
    private String blogContent;
    //博客所属板块ID
    private String blogPlateId;
    //博客作者ID
    private Integer blogAuthorId;
    //创建时间
    private Date createTime;
    //最新更改时间
    private Date lastEditTime;
    //博客置顶
    private Integer blogTopping;
    //博客热度
    private Integer blogHot;
    //博客点赞数
    private Integer blogLike;
    //博客状态
    private Integer blogStatus;
    //博客状态描述
    private String blogStatusInfo;

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

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public String getBlogPlateId() {
        return blogPlateId;
    }

    public void setBlogPlateId(String blogPlateId) {
        this.blogPlateId = blogPlateId;
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

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Integer getBlogTopping() {
        return blogTopping;
    }

    public void setBlogTopping(Integer blogTopping) {
        this.blogTopping = blogTopping;
    }

    public Integer getBlogHot() {
        return blogHot;
    }

    public void setBlogHot(Integer blogHot) {
        this.blogHot = blogHot;
    }

    public Integer getBlogLike() {
        return blogLike;
    }

    public void setBlogLike(Integer blogLike) {
        this.blogLike = blogLike;
    }

    public Integer getBlogStatus() {
        return blogStatus;
    }

    public void setBlogStatus(Integer blogStatus) {
        this.blogStatus = blogStatus;
    }

    public String getBlogStatusInfo() {
        return blogStatusInfo;
    }

    public void setBlogStatusInfo(String blogStatusInfo) {
        this.blogStatusInfo = blogStatusInfo;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "blogId=" + blogId +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogContent='" + blogContent + '\'' +
                ", blogPlateId='" + blogPlateId + '\'' +
                ", blogAuthorId=" + blogAuthorId +
                ", createTime=" + createTime +
                ", lastEditTime=" + lastEditTime +
                ", blogTopping=" + blogTopping +
                ", blogHot=" + blogHot +
                ", blogLike=" + blogLike +
                ", blogStatus=" + blogStatus +
                ", blogStatusInfo='" + blogStatusInfo + '\'' +
                '}';
    }
}
