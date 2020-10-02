package com.bluemsun.BBS.web;

import com.bluemsun.BBS.dao.UserDao;
import com.bluemsun.BBS.dto.UserExecution;
import com.bluemsun.BBS.entity.User;
import com.bluemsun.BBS.service.UserService;
import com.bluemsun.BBS.util.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    /**
     * 用户登陆
     * status: 1 登陆失败
     *         0 登陆成功，并返回用户
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/login" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(@RequestBody User user, HttpServletRequest request) throws IOException{
        //todo 获取前端发送的用户名及密码
        Map<String, Object> modelMap = new HashMap<String, Object>();
        UserExecution userExecution = userService.login(user);
        if(userExecution.getState() == 0) {
            modelMap.put("status",1);
            modelMap.put("msg",userExecution.getStateInfo());
            return modelMap;
        }
        modelMap.put("status",0);
        modelMap.put("msg",userExecution.getStateInfo());
        modelMap.put("currentUser",userExecution.getUser());
        return modelMap;
    }

    /**
     * 校验用户名
     * status: 2 表示用户名为空
     *         1 表示该用户名已存在
     *         0 表示该用户名可以使用
     * @param
     * @return
     */
    @RequestMapping(value = "/checkusername" ,method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> checkUsername(HttpServletRequest request) throws IOException {
        String jsonString = JSONUtil.getJson(request);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(jsonString,User.class);
        String username = user.getUsername();
        UserExecution userExecution = userService.checkUsername(username);
        if(userExecution.getState() == 4) {
            modelMap.put("status",2);
        } else {
            if(userExecution.getState() == 3) {
                modelMap.put("status",1);
                modelMap.put("msg",userExecution.getStateInfo());
                return modelMap;
            }
            modelMap.put("status",0);
        }
        modelMap.put("msg",userExecution.getStateInfo());
        return modelMap;
    }

    /**
     * 用户注册
     * @param request
     * status: 1 注册失败
     *         0 注册成功
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/register" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> register(HttpServletRequest request) throws IOException {
        String jsonString = JSONUtil.getJson(request);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(jsonString,User.class);
        UserExecution userExecution = userService.register(user);
        if(userExecution.getState() == 6){
            modelMap.put("status",1);
            modelMap.put("msg",userExecution.getStateInfo());
            return modelMap;
        }
        user = userDao.selectLogin(user.getUsername(),user.getPassword());

        modelMap.put("status",0);
        modelMap.put("msg",userExecution.getStateInfo());
        modelMap.put("currentUser",user);
        return modelMap;
    }
}
