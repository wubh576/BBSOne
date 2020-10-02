package com.bluemsun.BBS.dao;

import com.bluemsun.BBS.BaseTest;
import com.bluemsun.BBS.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserDaoTest extends BaseTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testQueryUserByUsernameAndPassword(){
        String username = "admin";
        String password = "admin";
        User user = new User();
//        user = userDao.queryUserByUsernameAndPassword(username,password);
        System.out.println(user);
    }
}