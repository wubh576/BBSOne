package com.bluemsun.BBS.entity;

import java.util.Date;

public class Blog {

    //主键ID
    private Integer blogId;
    //博客题目
    private String blogTitle;
    //博客作者ID
    private Integer blogAuthorId;
    //创建时间
    private Date createTime;
    //博客内容
    private String blogContent;

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

    @Override
    public String toString() {
        return "Blog{" +
                "blogId=" + blogId +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogAuthorId=" + blogAuthorId +
                ", createTime=" + createTime +
                ", blogContent='" + blogContent + '\'' +
                '}';
    }
}
