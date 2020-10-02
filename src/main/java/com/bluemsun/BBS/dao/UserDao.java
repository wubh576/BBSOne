package com.bluemsun.BBS.dao;

import com.bluemsun.BBS.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    /**
     * 用户登陆
     * 通过账号和密码判断用户是否登陆成功
     * @param username
     * @param password
     * @return
     */
    User selectLogin(@Param("username") String username, @Param("password") String password);

    /**
     * 查看用户名是否存在
     * @param username
     * @return
     */
    int checkUsername(String username);


    /**
     * 用户注册
     * @param user
     * @return
     */
    int insertUser(User user);

}
