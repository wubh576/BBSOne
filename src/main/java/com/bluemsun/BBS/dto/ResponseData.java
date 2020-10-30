package com.bluemsun.BBS.dto;

public class ResponseData {

    private String data;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "data='" + data + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
