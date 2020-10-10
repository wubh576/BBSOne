package com.bluemsun.BBS.web;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.entity.Blog;
import com.bluemsun.BBS.entity.User;
import com.bluemsun.BBS.service.BlogService;
import com.bluemsun.BBS.util.CookieUtil;
import com.bluemsun.BBS.util.JsonUtil;
import com.bluemsun.BBS.util.RedisPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public void refreshLogin() {
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
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        String jsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(jsonStr, User.class);
        blog.setBlogAuthorId(user.getUserId());
//        blog.setBlogAuthorId(10);
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
}
