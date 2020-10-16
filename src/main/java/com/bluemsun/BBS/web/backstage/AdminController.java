package com.bluemsun.BBS.web.backstage;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.entity.Blog;
import com.bluemsun.BBS.entity.Plate;
import com.bluemsun.BBS.entity.User;
import com.bluemsun.BBS.service.PlateService;
import com.bluemsun.BBS.util.JsonUtil;
import com.bluemsun.BBS.util.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private PlateService plateService;

    /**
     * 插入新板块
     *
     * @param plate
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/insertPlate.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> insertPlate(@RequestBody Plate plate, HttpServletRequest httpServletRequest) {
        String loginToken = httpServletRequest.getHeader("loginToken");
        String jsonStr = RedisPoolUtil.get(loginToken);
        if (StringUtils.isEmpty(jsonStr)) {
            return ServerResponse.createByErrorNotLogin();
        }
        User user = JsonUtil.string2Obj(jsonStr, User.class);
        if (user.getUserType() != 3) {
            return ServerResponse.createByErrorNoAuthority();
        }
        //  必须传入板块名称、板块描述
        ServerResponse<String> response = plateService.insertPlate(plate);
        return response;
    }

    /**
     * 查看该板块名是否已经存在
     *
     * @param plate
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/checkPlateName.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkPlateName(@RequestBody Plate plate, HttpServletRequest httpServletRequest) {
        String loginToken = httpServletRequest.getHeader("loginToken");
        String jsonStr = RedisPoolUtil.get(loginToken);
        if (StringUtils.isEmpty(jsonStr)) {
            return ServerResponse.createByErrorNotLogin();
        }
        User user = JsonUtil.string2Obj(jsonStr, User.class);
        if (user.getUserType() != 3) {
            return ServerResponse.createByErrorNoAuthority();
        }
        ServerResponse<String> response = plateService.checkPlateName(plate.getPlateName());
        return response;
    }





}
