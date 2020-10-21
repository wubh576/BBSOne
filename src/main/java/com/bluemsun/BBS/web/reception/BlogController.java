package com.bluemsun.BBS.web.reception;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dto.PlateIdAndName;
import com.bluemsun.BBS.entity.Blog;
import com.bluemsun.BBS.entity.Plate;
import com.bluemsun.BBS.entity.User;
import com.bluemsun.BBS.service.BlogService;
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
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    /**
     * 刷新用户有效登陆时常
     */
    @RequestMapping(value = "/refresh.do", method = RequestMethod.POST)
    @ResponseBody
    public void refreshLogin(HttpServletRequest httpServletRequest) {
        // TODO: 2020/10/16 没写完没写完没写完
    }

    /**
     * 新增博客
     *
     * @param blog
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Blog> insert(@RequestBody Blog blog, HttpServletRequest httpServletRequest) {
        String loginToken = httpServletRequest.getHeader("token");
        String jsonStr = RedisPoolUtil.get(loginToken);
        if (StringUtils.isEmpty(jsonStr)) {
            return ServerResponse.createByErrorNotLogin();
        }
        User user = JsonUtil.string2Obj(jsonStr, User.class);
        blog.setBlogAuthorId(user.getUserId());
        ServerResponse<Blog> response = blogService.insertBlog(blog);
        return response;
    }

    /**
     * 通过blogId查询blog信息
     *
     * @param blog
     * @return
     */
    @RequestMapping(value = "/viewBlogByBlogId.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Blog> viewBlogByBlogId(@RequestBody Blog blog) {
        ServerResponse<Blog> response = blogService.viewBlogByBlogId(blog.getBlogId());
        return response;
    }

    /**
     * 博客选择所属板块
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/selectPlate.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<PlateIdAndName>> selectPlate(HttpServletRequest httpServletRequest) {
        String loginToken = httpServletRequest.getHeader("token");
        String jsonStr = RedisPoolUtil.get(loginToken);
        if (StringUtils.isEmpty(jsonStr)) {
            return ServerResponse.createByErrorNotLogin();
        }
        String str = null;
        ServerResponse<List<PlateIdAndName>> response = blogService.selectList(str);
        return response;
    }

    /**
     * 简单模糊查询
     *
     * @param plate
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/searchPlateForBlog.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<PlateIdAndName>> searchPlateForBlog(@RequestBody Plate plate, HttpServletRequest httpServletRequest) {
        String loginToken = httpServletRequest.getHeader("token");
        String jsonStr = RedisPoolUtil.get(loginToken);
        if (StringUtils.isEmpty(jsonStr)) {
            return ServerResponse.createByErrorNotLogin();
        }
        ServerResponse<List<PlateIdAndName>> response = blogService.selectList(plate.getPlateName());
        return response;
    }
}
