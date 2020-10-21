package com.bluemsun.BBS.dto;

import java.util.List;

public class PageDto {

    int count;
    List list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageDto{" +
                "count=" + count +
                ", list=" + list +
                '}';
    }
}
