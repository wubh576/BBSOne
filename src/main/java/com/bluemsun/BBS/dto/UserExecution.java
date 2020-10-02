package com.bluemsun.BBS.dto;

import com.bluemsun.BBS.entity.User;
import com.bluemsun.BBS.enums.UserEnum;

public class UserExecution {

    // 结果状态
    private int state;

    // 状态标识
    private String stateInfo;

    // 操作的user
    private User user;

    public UserExecution() {

    }

    // 用户操作失败的时候使用的构造器
    public UserExecution(UserEnum userEnum) {
        this.state = userEnum.getState();
        this.stateInfo = userEnum.getStateInfo();
    }

    public UserExecution(UserEnum userEnum, User user) {
        this.state = userEnum.getState();
        this.stateInfo = userEnum.getStateInfo();
        this.user = user;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
