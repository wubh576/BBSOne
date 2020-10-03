package com.bluemsun.BBS.service.impl;

import com.bluemsun.BBS.dao.UserDao;
import com.bluemsun.BBS.dto.UserExecution;
import com.bluemsun.BBS.entity.User;
import com.bluemsun.BBS.enums.UserEnum;
import com.bluemsun.BBS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 用户登陆
     * @param user
     * @return
     */
    @Override
    public UserExecution login(User user) {
        if(user == null) {
            return new UserExecution(UserEnum.NULL_USER);
        }
        User user1 = null;
        user1 = userDao.selectLogin(user.getUsername(),user.getPassword());
        if(user1 == null){
            return new UserExecution(UserEnum.LOGIN_FAIl);
        }
        return new UserExecution(UserEnum.LOGIN_SUCCESS,user1);
    }

    /**
     * 注册时，校验用户名是否存在
     * @param username
     * @return
     */
    @Override
    public UserExecution checkUsername(String username) {
        if("".equals(username)){
            return new UserExecution(UserEnum.CHECK_EXIST_NULL);
        }
        int i = userDao.checkUsername(username);
        if(i > 0) {
            return new UserExecution(UserEnum.CHECK_EXIST_FAIL);
        }
        return new UserExecution(UserEnum.CHECK_EXIST_SUCCESS);
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public UserExecution register(User user) {
        if(user == null) {
            return new UserExecution(UserEnum.NULL_USER);
        }
        user.setCreateTime(new Date());
        user.setLastEditTime(new Date());
        int resultCount = userDao.insertUser(user);
        if(resultCount == 0) {
            return new UserExecution(UserEnum.REGISTER_FAIL);
        }
        return new UserExecution(UserEnum.REGISTER_SUCCESS);
    }
}
