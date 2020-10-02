package com.bluemsun.BBS.enums;

public enum UserEnum {

    LOGIN_FAIl(0, "登陆失败，用户名或密码错误"),LOGIN_SUCCESS(1, "登陆成功"),
    CHECK_EXIST_SUCCESS(2,"该用户名可用"),CHECK_EXIST_FAIL(3,"该用户名已存在"),
    CHECK_EXIST_NULL(4,"用户名为空"),
    REGISTER_SUCCESS(5,"注册成功"),REGISTER_FAIL(6,"注册失败"),
    NULL_USER(-1,"user为空");

    private int state;
    private String stateInfo;

    private UserEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * 依据传入的state返回相应的enum值
     */
    public static UserEnum stateOf(int state) {
        for (UserEnum stateEnum : values()) {
            if (stateEnum.getState() == state) {
                return stateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

}
