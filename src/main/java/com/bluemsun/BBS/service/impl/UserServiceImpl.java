package com.bluemsun.BBS.service.impl;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dao.UserDao;
import com.bluemsun.BBS.entity.User;
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
     *
     * @param user
     * @return
     */
    public ServerResponse<User> login(User user) {
        if (user == null) {
            return ServerResponse.createByErrorMessage("账号为空");
        }
        User user1 = null;
        user1 = userDao.selectLogin(user.getUsername(), user.getPassword());
        if (user1 == null) {
            return ServerResponse.createByErrorMessage("登陆失败，用户名或密码错误");
        }
        return ServerResponse.createBySuccess("登陆成功", user1);
    }

    /**
     * 用户注册时，校验用户名是否存在
     *
     * @param username
     * @return
     */
    @Override
    public ServerResponse<String> checkUsername(String username) {
        if (username == "") {
            return ServerResponse.createByErrorCodeMessage(2, "用户名为空");
        }
        int result = userDao.checkUsername(username);
        if (result > 0) {
            return ServerResponse.createByErrorMessage("该用户名已存在");
        }
        return ServerResponse.createBySuccessMessage("该用户名可用");
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @Override
    public ServerResponse<User> register(User user) {
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(2, "用户为空");
        }
        user.setCreateTime(new Date());
        user.setLastEditTime(new Date());
        user.setProfileImg("http://bluesun.natapp1.cc/uploads/test.png");
        int result = userDao.insertUser(user);
        if (result == 1) {
            User user1 = userDao.selectLogin(user.getUsername(), user.getPassword());
            return ServerResponse.createBySuccess("注册成功，用户信息如下", user1);
        }
        return ServerResponse.createByError();
    }

    /**
     * 绑定邮箱
     *
     * @param user
     * @return
     */
    @Override
    public ServerResponse<User> bindMail(User user) {
        if(user == null) {
            return ServerResponse.createByErrorCodeMessage(2, "用户为空");
        }
        int result = userDao.updateByPrimaryKeySelective(user);
        if (result == 1) {
            User user1 = userDao.selectByUserId(user.getUserId());
            return ServerResponse.createBySuccess("绑定邮箱成功！返回最新个人信息！", user1);
        }
        return ServerResponse.createByErrorMessage("发生错误！绑定邮箱失败！");
    }

    /**
     * 更新用户头像
     *
     * @param user
     * @return
     */
    @Override
    public ServerResponse<User> updateUserImg(User user) {
        if (user == null) {
            return ServerResponse.createByErrorMessage("传入用户为空，变更头像失败");
        }
        int result = userDao.updateByPrimaryKeySelective(user);
        if (result == 1) {
            User user1 = userDao.selectByUserId(user.getUserId());
            return ServerResponse.createBySuccess("更改头像成功！返回最新个人信息！", user1);
        }
        return ServerResponse.createByErrorMessage("发生错误！更换头像失败！");
    }

    /**
     * 通过userId查询最新用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public ServerResponse<User> onLoadUser(int userId) {
        User user = userDao.selectByUserId(userId);
        return ServerResponse.createBySuccess("加载成功", user);
    }
}
