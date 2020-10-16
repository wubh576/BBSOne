package com.bluemsun.BBS.service;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.entity.User;

public interface UserService {

    /**
     * 用户登陆
     *
     * @param user
     * @return
     */
    ServerResponse<User> login(User user);

    /**
     * 用户注册时，校验用户名是否存在
     *
     * @param username
     * @return
     */
    ServerResponse<String> checkUsername(String username);

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    ServerResponse<User> register(User user);

    /**
     * 绑定邮箱
     *
     * @param user
     * @return
     */
    ServerResponse<User> bindMail(User user);

    /**
     * 更新用户头像
     *
     * @param user
     * @return
     */
    ServerResponse<User> updateUserImg(User user);

    /**
     * 通过userId查询最新用户信息
     *
     * @param userId
     * @return
     */
    ServerResponse<User> onLoadUser(int userId);

    /**
     * 验证邮箱是否重复
     *
     * @param email
     * @return
     */
    ServerResponse<String> checkEmail(String email);

    /**
     * 更改密码
     *
     * @param user
     * @return
     */
    ServerResponse<User> updatePassword(User user);


}
