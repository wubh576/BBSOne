package com.bluemsun.BBS.service;

import com.bluemsun.BBS.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class PlateServiceTest extends BaseTest {

    @Autowired
    private PlateService plateService;

    @Test
    public void pagePlateForAdmin() {
        System.out.println(plateService.pagePlateForAdmin(1,2));
    }
}