package com.bluemsun.BBS.entity;

import java.util.Date;

/**
 * 二级评论
 */
public class SecondComment {
    //主键id
    private Integer secondCommentId;
    //谁的评论
    private Integer secondUserId;
    //评论内容
    private String secondContent;
    //评论的创建时间
    private Date secondCreateTime;
    //该二级评论隶属于哪个一级评论
    private Integer secondFirstId;

    public Integer getSecondCommentId() {
        return secondCommentId;
    }

    public void setSecondCommentId(Integer secondCommentId) {
        this.secondCommentId = secondCommentId;
    }

    public Integer getSecondUserId() {
        return secondUserId;
    }

    public void setSecondUserId(Integer secondUserId) {
        this.secondUserId = secondUserId;
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

    public Integer getSecondFirstId() {
        return secondFirstId;
    }

    public void setSecondFirstId(Integer secondFirstId) {
        this.secondFirstId = secondFirstId;
    }

    @Override
    public String toString() {
        return "SecondComment{" +
                "secondCommentId=" + secondCommentId +
                ", secondUserId=" + secondUserId +
                ", secondContent='" + secondContent + '\'' +
                ", secondCreateTime=" + secondCreateTime +
                ", secondFirstId=" + secondFirstId +
                '}';
    }
}
