package com.bluemsun.BBS.dao;

import com.bluemsun.BBS.BaseTest;
import com.bluemsun.BBS.dto.PlateIdAndName;
import com.bluemsun.BBS.entity.Plate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class PlateDaoTest extends BaseTest {

    @Autowired
    private PlateDao plateDao;

    @Test
    public void selectList() {
        String str = null;
        List<PlateIdAndName> list = plateDao.selectList(str);
        System.out.println(list);
    }
}