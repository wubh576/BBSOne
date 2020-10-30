package com.bluemsun.BBS.dao;

import com.bluemsun.BBS.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PlateDaoTest extends BaseTest {

    @Autowired
    private PlateDao plateDao;

    @Test
    public void selectList() {
        String str = null;
//        List<PlateIdAndName> list = plateDao.selectList(str);
//        System.out.println(list);
    }
}