package com.bluemsun.BBS.dto;

public class BlogPage {

    private Integer blogId;
    private String blogTitle;
    private String plateName;
    private String username;
    private Integer blogHot;

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

    @Override
    public String toString() {
        return "BlogPage{" +
                "blogId=" + blogId +
                ", blogTitle='" + blogTitle + '\'' +
                ", plateName='" + plateName + '\'' +
                ", username='" + username + '\'' +
                ", blogHot=" + blogHot +
                '}';
    }
}
