package com.bluemsun.BBS.web.backstage.moderator;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dto.PageDto;
import com.bluemsun.BBS.dto.PlateIdAndName;
import com.bluemsun.BBS.entity.Blog;
import com.bluemsun.BBS.entity.Plate;
import com.bluemsun.BBS.entity.User;
import com.bluemsun.BBS.service.BlogService;
import com.bluemsun.BBS.service.PlateService;
import com.bluemsun.BBS.util.JsonUtil;
import com.bluemsun.BBS.util.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/moderator")
@Controller
public class ModeratorController {

    @Autowired
    private PlateService plateService;
    @Autowired
    private BlogService blogService;

    /**
     * 管理员查看已有页面
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/pageList.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<PlateIdAndName>> plateList(HttpServletRequest httpServletRequest) {
        String loginToken = httpServletRequest.getHeader("token");
        String jsonStr = RedisPoolUtil.get(loginToken);
        if (StringUtils.isEmpty(jsonStr)) {
            return ServerResponse.createByErrorNotLogin();
        }
        User user = JsonUtil.string2Obj(jsonStr, User.class);
        ServerResponse<List<PlateIdAndName>> response = plateService.plateList(user.getUserId());
        return response;
    }

    /**
     * 查看某个板块的详细信息
     *
     * @param plate
     * @return
     */
    @RequestMapping(value = "/plateInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Plate> plateInfo(@RequestBody Plate plate) {
        ServerResponse<Plate> response = plateService.plateInfo(plate.getPlateId());
        return response;
    }

    /**
     * 查看该板块所拥有的博客
     *
     * @param plateId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/pageBlogByPlateId.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageDto> pageBlogByPlateId(@RequestParam("plateId") int plateId, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        ServerResponse<PageDto> response = plateService.pageBlogByPlateId(plateId, pageNo, pageSize);
        return response;
    }

    /**
     * 可以更新的板块信息有（板块图像、板块描述、板块名字）
     *
     * @param plate
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Plate> updatePlate(@RequestBody Plate plate,HttpServletRequest httpServletRequest) {
        String loginToken = httpServletRequest.getHeader("token");
        String jsonStr = RedisPoolUtil.get(loginToken);
        if(StringUtils.isEmpty(jsonStr)) {
            return ServerResponse.createByErrorNotLogin();
        }
        // 防止用户自己注入json
        Plate newPlate = new Plate();
        newPlate.setPlateId(plate.getPlateId());
        if(StringUtils.isNotEmpty(plate.getPlateName())) {
            newPlate.setPlateName(plate.getPlateName());
        }
        if(StringUtils.isNotEmpty(plate.getPlateDesc())) {
            newPlate.setPlateDesc(plate.getPlateDesc());
        }
        if(StringUtils.isNotEmpty(plate.getPlateImg())) {
            newPlate.setPlateName(plate.getPlateImg());
        }
        ServerResponse<Plate> response = plateService.updatePlate(plate);
        return response;
    }

    /**
     * 版主修改博客信息(包含博客置顶，移动博客至其他板块，取消置顶)
     *
     * @param blog
     * @return
     */
    @RequestMapping(value = "/updateBlog.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Blog> updateBlog(@RequestBody Blog blog,HttpServletRequest httpServletRequest) {
        String loginToken = httpServletRequest.getHeader("token");
        String jsonStr = RedisPoolUtil.get(loginToken);
        if(StringUtils.isEmpty(jsonStr)) {
            return ServerResponse.createByErrorNotLogin();
        }
        ServerResponse<Blog> response = blogService.adminUpdateBlogByBlogId(blog);
        return response;
    }

    /**
     * 版主删除某博客
     *
     * @param blog
     * @return
     */
    @RequestMapping(value = "/delBlog.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> delBlog(@RequestBody Blog blog,HttpServletRequest httpServletRequest) {
        String loginToken = httpServletRequest.getHeader("token");
        String jsonStr = RedisPoolUtil.get(loginToken);
        if(StringUtils.isEmpty(jsonStr)) {
            return ServerResponse.createByErrorNotLogin();
        }
        int blogId = blog.getBlogId();
        ServerResponse<String> response = blogService.delBlogByBlogId(blogId);
        return response;
    }


}
