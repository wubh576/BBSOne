package com.bluemsun.BBS.service;

import com.bluemsun.BBS.dto.UserExecution;
import com.bluemsun.BBS.entity.User;

public interface UserService {

    /**
     * 用户登陆
     * @param user
     * @return
     */
    UserExecution login(User user);

    /**
     * 注册时，校验用户名是否存在
     * @param username
     * @return
     */
    UserExecution checkUsername(String username);

    UserExecution register(User user);
}
