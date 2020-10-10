package com.bluemsun.BBS.web;

import com.bluemsun.BBS.common.Const;
import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.entity.ResponseData;
import com.bluemsun.BBS.entity.User;
import com.bluemsun.BBS.service.UserService;

import com.bluemsun.BBS.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.GeneralSecurityException;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登陆
     * status: 1 登陆失败
     * 0 登陆成功，并返回用户
     *
     * @param user
     * @param session
     * @param httpServletResponse
     * @return
     */
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(@RequestBody User user, HttpSession session, HttpServletResponse httpServletResponse) {
        ServerResponse<User> response = userService.login(user);
        if (response.isSuccess()) {
            CookieUtil.writeLoginToken(httpServletResponse, session.getId());
            RedisPoolUtil.setEx(session.getId(), JsonUtil.obj2String(response.getData()), Const.RedisCacheExtime.REDIES_SESSION_EXTIME);
        }
        return response;
    }

    /**
     * 用户注册时校验用户名是否存在
     *
     * @param user
     * @param session
     * @param httpServletResponse
     * @return
     */
    @RequestMapping(value = "/checkUsername", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkUsername(@RequestBody User user, HttpSession session, HttpServletResponse httpServletResponse) {
        ServerResponse<String> response = userService.checkUsername(user.getUsername());
        return response;
    }

    /**
     * 用户注册
     *
     * @param user
     * @param httpServletResponse
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> register(@RequestBody User user, HttpServletResponse httpServletResponse) {
        ServerResponse<User> response = userService.register(user);
        return response;
    }

    /**
     * 发送绑定邮箱验证码，有效期为10分钟
     *
     * @param user
     * @param httpServletRequest
     * @return
     * @throws GeneralSecurityException
     * @throws IOException
     * @throws MessagingException
     */
    @RequestMapping(value = "/bindMail.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> bindMail(@RequestBody User user, HttpServletRequest httpServletRequest) throws GeneralSecurityException, IOException, MessagingException {
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        String jsonStr = RedisPoolUtil.get(loginToken);
        User user1 = JsonUtil.string2Obj(jsonStr, User.class);
        user1.setEmail(user.getEmail());
        String verifyCode = VerifyCodeUtil.verifyCode();
        MailUtil.SendTextMial(user1, verifyCode);
        RedisPoolUtil.setEx(String.valueOf(user1.getUserId()), verifyCode, 10 * 60);
        return ServerResponse.createBySuccessMessage("发送邮件成功");
    }

    /**
     * 核对邮箱校验码
     *
     * @param responseData
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/checkVerificationCode.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> checkVerificationCode(@RequestBody ResponseData responseData, HttpServletRequest httpServletRequest) {
        String verificationCode = responseData.getData();
        if (verificationCode == null || verificationCode.equals("")) {
            return ServerResponse.createByErrorCodeMessage(2, "验证码为空");
        }
        String email = responseData.getEmail();
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        String jsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(jsonStr, User.class);
        User user1 = new User();
        user1.setUserId(user.getUserId());
        user1.setEmail(email);
        String strFromRedis = RedisPoolUtil.get(String.valueOf(user.getUserId()));
        if (strFromRedis == null) {
            return ServerResponse.createByErrorCodeMessage(3, "验证码已过期，请重新发送！");
        }
        if (verificationCode.equals(strFromRedis)) {
            ServerResponse<User> response = userService.bindMail(user1);
            RedisPoolUtil.del(String.valueOf(user.getUserId()));
            return response;
        }
        return ServerResponse.createByErrorMessage("验证码错误！");
    }

    /**
     * 跳转加载信息
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @RequestMapping(value = "/onLoad.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> onLoad(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        String jsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(jsonStr,User.class);
        ServerResponse<User> response = userService.onLoadUser(user.getUserId());
        return response;
    }


        /**
         * 退出登陆
         *
         * @param httpServletRequest
         * @param httpServletResponse
         * @return
         */
        @RequestMapping(value = "/logout.do", method = RequestMethod.POST)
        @ResponseBody
        public ServerResponse<String> logout (HttpServletRequest httpServletRequest, HttpServletResponse
        httpServletResponse){
            String loginToken = CookieUtil.readLoginToken(httpServletRequest);
            CookieUtil.delLoginToken(httpServletRequest, httpServletResponse);
            RedisPoolUtil.del(loginToken);
            return ServerResponse.createBySuccessMessage("退出登陆成功");
        }
    }
