package com.bluemsun.BBS.entity;

public class BlogFile {

    private int blogFileId;
    private int blogId;
    private String uri;

    public int getBlogFileId() {
        return blogFileId;
    }

    public void setBlogFileId(int blogFileId) {
        this.blogFileId = blogFileId;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public BlogFile(int blogFileId, int blogId, String uri) {
        this.blogFileId = blogFileId;
        this.blogId = blogId;
        this.uri = uri;
    }
}
