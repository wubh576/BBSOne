package com.bluemsun.BBS.enums;

public enum ResponseEnum {

    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT"),
    NO_AUTHORITY(15,"NO_AUTHORITY");

    private final int code;
    private final String desc;


    ResponseEnum(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }
}
