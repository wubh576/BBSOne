package com.bluemsun.BBS.dao;

import com.bluemsun.BBS.dto.PageUserForAdmin;
import com.bluemsun.BBS.dto.PlateIdAndName;
import com.bluemsun.BBS.dto.UserIdAndName;
import com.bluemsun.BBS.entity.PlateAndUser;
import com.bluemsun.BBS.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    /**
     * 用户登陆
     * 通过账号和密码判断用户是否登陆成功
     *
     * @param username
     * @param password
     * @return
     */
    User selectLogin(@Param("username") String username, @Param("password") String password);

    /**
     * 查看用户名是否存在
     *
     * @param username
     * @return
     */
    int checkUsername(String username);


    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    int insertUser(User user);


    /**
     * 通过userId查询用户信息
     *
     * @param userId
     * @return
     */
    User selectByUserId(int userId);


    /**
     * 通过主键更新用户信息（动态SQL）
     *
     * @param user
     * @return
     */
    int updateByPrimaryKeySelective(User user);

    /**
     * 查询邮箱是否存在
     *
     * @param email
     * @return
     */
    int checkEmail(String email);

    /**
     * 管理员删除通过用户id删除用户
     *
     * @param userId
     * @return
     */
    int delUserByUserId(int userId);

    int insertModerator(@Param("userId") int userId,@Param("plateId") int plateId);

    int countModerator(int plateId);

    List<PlateAndUser> selectModerator(int plateId);

    int judgeModerator(@Param("userId") int userId,@Param("plateId") int plateId);

    int delModerator(@Param("userId") int userId,@Param("plateId") int plateId);

    List<PageUserForAdmin> listUser(@Param("username") String username, @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    int countUser(@Param("username") String username);

    List<PlateIdAndName> checkManagePlate(int userId);

    List<UserIdAndName> checkModerator(int plateId);
}
