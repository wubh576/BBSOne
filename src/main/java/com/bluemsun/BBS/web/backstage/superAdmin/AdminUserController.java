package com.bluemsun.BBS.web.backstage.superAdmin;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dto.DelModerator;
import com.bluemsun.BBS.dto.PageDto;
import com.bluemsun.BBS.dto.PlateIdAndName;
import com.bluemsun.BBS.entity.PlateAndUser;
import com.bluemsun.BBS.entity.User;
import com.bluemsun.BBS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private UserService userService;

    /**
     * 删除用户
     *
     * @param user
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> delUser(@RequestBody User user, HttpServletRequest httpServletRequest) {
        ServerResponse<String> response = userService.delUserByUserId(user.getUserId());
        return response;
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> updateUser(@RequestBody User user, HttpServletRequest httpServletRequest) {
        ServerResponse<User> response = userService.updateUser(user);
        return response;
    }

    /**
     * 任命版主
     *
     * @param plateAndUser
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/addModerator.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<PlateAndUser>> addModerator(@RequestBody PlateAndUser plateAndUser, HttpServletRequest httpServletRequest) {
        ServerResponse<List<PlateAndUser>> response = userService.addModerator(plateAndUser.getUserId(), plateAndUser.getPlateId());
        return response;
    }

    /**
     * 革职版主
     *
     * @param delModerator
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/delModerator.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<PlateIdAndName>> delModerator(@RequestBody DelModerator delModerator, HttpServletRequest httpServletRequest) {
        System.out.println(delModerator);
        List<String> list = delModerator.getPlateIdList();
        int userId = delModerator.getUserId();
        for (int i = 0; i < list.size(); i++) {
            userService.delModerator(userId, Integer.parseInt(list.get(i)));
        }
        ServerResponse<List<PlateIdAndName>> response = userService.checkManagePlate(userId);
        return response;
    }


    /**
     * 查看用户分页展示
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/pageUser.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageDto> pageUser(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize, HttpServletRequest httpServletRequest) {
        ServerResponse<PageDto> response = userService.pageUser(pageNo, pageSize);
        return response;
    }

    /**
     * 查询用户分页的模糊搜索
     *
     * @param username
     * @param pageNo
     * @param pageSize
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/pageUserByUsername.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageDto> pageUserByUsername(@RequestParam("username") String username, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize, HttpServletRequest httpServletRequest) {
        ServerResponse<PageDto> response = userService.pageUserByUsername(pageNo, pageSize, username);
        return response;
    }

    /**
     * 查看该用户管理的板块
     *
     * @param user
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/checkManagePlate.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<PlateIdAndName>> checkManagePlate(@RequestBody User user, HttpServletRequest httpServletRequest) {
        ServerResponse<List<PlateIdAndName>> response = userService.checkManagePlate(user.getUserId());
        return response;
    }
}
