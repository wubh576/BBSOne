package com.bluemsun.BBS.service;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dto.PageDto;
import com.bluemsun.BBS.dto.PlateIdAndName;
import com.bluemsun.BBS.entity.PlateAndUser;
import com.bluemsun.BBS.entity.User;

import java.util.List;

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

    /**
     * 管理员删除通过用户id删除用户
     *
     * @param userId
     * @return
     */
    ServerResponse<String> delUserByUserId(int userId);

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    ServerResponse<User> updateUser(User user);

    /**
     * 任命版主
     *
     * @param userId
     * @param plateId
     * @return
     */
    ServerResponse<List<PlateAndUser>> addModerator(int userId, int plateId);

    /**
     * 革职版主
     *
     * @param userId
     * @param plateId
     * @return
     */
    ServerResponse<List<PlateAndUser>> delModerator(int userId, int plateId);

    /**
     * 管理员查看用户分页
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    ServerResponse<PageDto> pageUser(int pageNo, int pageSize);

    /**
     * 管理员模糊搜索查看用户分页
     *
     * @param pageNo
     * @param pageSize
     * @param username
     * @return
     */
    ServerResponse<PageDto> pageUserByUsername(int pageNo, int pageSize, String username);

    /**
     * 查看该用户所管理的板块
     *
     * @param userId
     * @return
     */
    ServerResponse<List<PlateIdAndName>> checkManagePlate(int userId);
}
