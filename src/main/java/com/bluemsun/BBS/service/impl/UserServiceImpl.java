package com.bluemsun.BBS.service.impl;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dao.PlateDao;
import com.bluemsun.BBS.dao.UserDao;
import com.bluemsun.BBS.dto.PageDto;
import com.bluemsun.BBS.dto.PageUserForAdmin;
import com.bluemsun.BBS.dto.PlateIdAndName;
import com.bluemsun.BBS.entity.PlateAndUser;
import com.bluemsun.BBS.entity.User;
import com.bluemsun.BBS.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PlateDao plateDao;

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 用户登陆
     *
     * @param user
     * @return
     */
    public ServerResponse<User> login(User user) {
        logger.info("start");
        if (user == null) {
            return ServerResponse.createByErrorMessage("账号为空");
        }
        User user1 = null;
        user1 = userDao.selectLogin(user.getUsername(), user.getPassword());
        if (user1 == null) {
            return ServerResponse.createByErrorMessage("登陆失败，用户名或密码错误");
        }
        logger.debug("登陆用户信息为{}",user1.toString());
        logger.info("end");
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
        if (StringUtils.isEmpty(username)) {
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
        logger.info("start");
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(2, "用户为空");
        }
        int count = userDao.checkUsername(user.getUsername());
        if (count == 1) {
            return ServerResponse.createByErrorCodeMessage(3, "非法注册，求求了，请莫破坏孩子的系统，该用户名已存在，注册失败");
        }
        user.setCreateTime(new Date());
        user.setLastEditTime(new Date());
        // TODO: 2020/10/16 阿里云
        user.setProfileImg("http://bluemsum.tech:8080/uploads/test.jpg");
        // TODO: 2020/10/16 线下
//        user.setProfileImg("http://bluesun.natapp1.cc/uploads/test.png");
        int result = userDao.insertUser(user);
        if (result == 1) {
            User user1 = userDao.selectLogin(user.getUsername(), user.getPassword());
            logger.debug("注册的用户信息如下{}",user1.toString());
            logger.info("end");
            return ServerResponse.createBySuccess("注册成功，用户信息如下", user1);
        }
        logger.info("end");
        return ServerResponse.createByError();
    }

    /**
     * 查验邮箱是否重复
     *
     * @param email
     * @return
     */
    public ServerResponse<String> checkEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return ServerResponse.createByErrorMessage("邮箱为空");
        }
        int count = 0;
        count = userDao.checkEmail(email);
        if (count == 1) {
            return ServerResponse.createByErrorCodeMessage(4, "该邮箱已被绑定");
        }
        return ServerResponse.createBySuccessMessage("该邮箱可用，已成功发送验证码");
    }

    /**
     * 绑定邮箱
     *
     * @param user
     * @return
     */
    @Override
    public ServerResponse<User> bindMail(User user) {
        if (user == null) {
            return ServerResponse.createByErrorNotLogin();
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
        if (userId == 0) {
            return ServerResponse.createByErrorMessage("发生错误");
        }
        User user = userDao.selectByUserId(userId);
        return ServerResponse.createBySuccess("加载成功", user);
    }

    /**
     * 更新密码
     *
     * @param user
     * @return
     */
    public ServerResponse<User> updatePassword(User user) {
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(2, "user为空");
        }
        int result = userDao.updateByPrimaryKeySelective(user);
        if (result == 1) {
            User user1 = userDao.selectByUserId(user.getUserId());
            return ServerResponse.createBySuccess("密码更新成功,返回最新个人信息", user1);
        }
        return ServerResponse.createByErrorMessage("发生错误，密码更新失败");
    }

    /**
     * 管理员删除通过用户id删除用户
     *
     * @param userId
     * @return
     */
    @Override
    public ServerResponse<String> delUserByUserId(int userId) {
        int result = userDao.delUserByUserId(userId);
        if (result == 1) {
            return ServerResponse.createBySuccess("成功删除该用户");
        }
        return ServerResponse.createByErrorMessage("删除用户失败");
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @Override
    public ServerResponse<User> updateUser(User user) {
        int result = userDao.updateByPrimaryKeySelective(user);
        if (result == 1) {
            User newUser = userDao.selectByUserId(user.getUserId());
            return ServerResponse.createBySuccess("更新用户信息成功", newUser);
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }

    /**
     * 任命版主
     *
     * @param userId
     * @param plateId
     * @return
     */
    @Override
    public ServerResponse<List<PlateAndUser>> addModerator(int userId, int plateId) {
        int judgeNum = userDao.judgeModerator(userId, plateId);
        if (judgeNum == 1) {
            return ServerResponse.createByErrorCodeMessage(3, "任命失败，该用户已经是该板块的版主了");
        }
        int count = userDao.countModerator(plateId);
        if (count == 5) {
            return ServerResponse.createByErrorCodeMessage(4, "任命失败，该板块的版主已达到上限5人");
        }
        int result = userDao.insertModerator(userId, plateId);
        if (result == 1) {
            List<PlateAndUser> list = userDao.selectModerator(plateId);
            return ServerResponse.createBySuccess("任命成功，目前该板块版主如下：", list);
        }
        return ServerResponse.createByErrorMessage("任命版主失败");
    }

    /**
     * 革职版主
     *
     * @param userId
     * @param plateId
     * @return
     */
    @Override
    public ServerResponse<List<PlateAndUser>> delModerator(int userId, int plateId) {
        int count = userDao.delModerator(userId, plateId);
        if (count == 1) {
            List<PlateAndUser> list = userDao.selectModerator(plateId);
            return ServerResponse.createBySuccess("革职成功，目前该板块所剩版主如下：", list);
        }
        return ServerResponse.createByErrorMessage("革职失败");
    }

    /**
     * 查询用户分页
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public ServerResponse<PageDto> pageUser(int pageNo, int pageSize) {
        int startIndex = (pageNo - 1) * pageSize;
        List<PageUserForAdmin> list = userDao.listUser(null, startIndex, pageSize);
        int count = userDao.countUser(null);
        for (int i = 0; i < list.size(); i++) {
            int userId = list.get(i).getUserId();
            List<PlateIdAndName> list1 = plateDao.listPlateName(userId);
            list.get(i).setList(list1);
        }
        PageDto pageDto = new PageDto();
        pageDto.setList(list);
        pageDto.setCount(count);
        return ServerResponse.createBySuccess("查看用户成功", pageDto);
    }

    /**
     * 管理员模糊搜索查看用户分页
     *
     * @param pageNo
     * @param pageSize
     * @param username
     * @return
     */
    @Override
    public ServerResponse<PageDto> pageUserByUsername(int pageNo, int pageSize, String username) {
        int startIndex = (pageNo - 1) * pageSize;
        String newUsername = '%' + username + '%';
        List<PageUserForAdmin> list = userDao.listUser(newUsername, startIndex, pageSize);
        int count = userDao.countUser(newUsername);
        for (int i = 0; i < list.size(); i++) {
            int userId = list.get(i).getUserId();
            List<PlateIdAndName> list1 = plateDao.listPlateName(userId);
            list.get(i).setList(list1);
        }
        PageDto pageDto = new PageDto();
        pageDto.setList(list);
        pageDto.setCount(count);
        return ServerResponse.createBySuccess("模糊搜索查看用户成功", pageDto);
    }

    /**
     * 查看该用户所管理的板块
     *
     * @param userId
     * @return
     */
    @Override
    public ServerResponse<List<PlateIdAndName>> checkManagePlate(int userId) {
        List<PlateIdAndName> list = userDao.checkManagePlate(userId);
        return ServerResponse.createBySuccess("该用户所管理的板块有：",list);
    }
}

