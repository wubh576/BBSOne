package com.bluemsun.BBS.web.reception;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dto.BlogAndUser;
import com.bluemsun.BBS.dto.PageDto;
import com.bluemsun.BBS.entity.Blog;
import com.bluemsun.BBS.entity.User;
import com.bluemsun.BBS.service.BlogService;
import com.bluemsun.BBS.util.JsonUtil;
import com.bluemsun.BBS.util.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    @RequestMapping(value = "/viewBlogByBlogId", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<BlogAndUser> viewBlogByBlogId(@RequestBody Blog blog,HttpServletRequest httpServletRequest) {
        String loginToken = httpServletRequest.getHeader("token");
        String jsonStr = RedisPoolUtil.get(loginToken);
        int userId;
        if(StringUtils.isEmpty(jsonStr)) {
            userId = 0;
        } else {
            User user = JsonUtil.string2Obj(jsonStr,User.class);
            userId = user.getUserId();
        }
        ServerResponse<BlogAndUser> response = blogService.viewBlogByBlogId(blog.getBlogId(), userId);
        return response;
    }

    /**
     * 博客选择所属板块
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/selectPlate.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageDto> selectPlate(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize, HttpServletRequest httpServletRequest) {
        String loginToken = httpServletRequest.getHeader("token");
        String jsonStr = RedisPoolUtil.get(loginToken);
        if (StringUtils.isEmpty(jsonStr)) {
            return ServerResponse.createByErrorNotLogin();
        }
        String str = null;
        ServerResponse<PageDto> response = blogService.selectList(str, pageNo, pageSize);
        return response;
    }

    /**
     * 简单模糊查询
     *
     * @param plateName
     * @param pageNo
     * @param pageSize
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/searchPlateForBlog.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageDto> searchPlateForBlog(@RequestParam("plateName") String plateName, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize, HttpServletRequest httpServletRequest) {
        String loginToken = httpServletRequest.getHeader("token");
        String jsonStr = RedisPoolUtil.get(loginToken);
        if (StringUtils.isEmpty(jsonStr)) {
            return ServerResponse.createByErrorNotLogin();
        }
        ServerResponse<PageDto> response = blogService.selectList(plateName, pageNo, pageSize);
        return response;
    }

    /**
     * 通过板块id展示该板块的博客分页
     *
     * @param plateId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/pageBlogByPlateId", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageDto> pageBlogByPlateId(@RequestParam("plateId") int plateId, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        ServerResponse<PageDto> response = blogService.pageBlogByPlateId(plateId, pageNo, pageSize);
        return response;
    }

    /**
     * 首页模糊搜索博客题目
     *
     * @param blogTitle
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/pageBlogByBlogTitle", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageDto> pageBlogByBlogTitle(@RequestParam("blogTitle") String blogTitle, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        ServerResponse<PageDto> response = blogService.pageBlogByBlogTitle(blogTitle, pageNo, pageSize);
        return response;
    }
}
