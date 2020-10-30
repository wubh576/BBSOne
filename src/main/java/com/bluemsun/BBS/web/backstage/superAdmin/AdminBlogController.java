package com.bluemsun.BBS.web.backstage.superAdmin;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dto.PageDto;
import com.bluemsun.BBS.entity.Blog;
import com.bluemsun.BBS.service.BlogService;
import com.bluemsun.BBS.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/admin/blog")
@Controller
public class AdminBlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private PageService pageService;


    /**
     * 删除某博客
     *
     * @param blog
     * @return
     */
    @RequestMapping(value = "/del.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> delBlog(@RequestBody Blog blog) {
        int blogId = blog.getBlogId();
        ServerResponse<String> response = blogService.delBlogByBlogId(blogId);
        return response;
    }

    /**
     * 管理员查看博客分页
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/pageBlogAdmin.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageDto> pageBlogAdmin(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        ServerResponse<PageDto> response = pageService.pageIndexBlog(pageNo, pageSize);
        return response;
    }

    /**
     * 管理员修改博客信息(包含博客置顶，移动博客至其他板块，取消置顶)
     *
     * @param blog
     * @return
     */
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Blog> updateBlog(@RequestBody Blog blog) {
        ServerResponse<Blog> response = blogService.adminUpdateBlogByBlogId(blog);
        return response;
    }

    /**
     * 管理员模糊搜索博客分页
     *
     * @param blogTitle
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/pageBlogByBlogTitle.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageDto> adminPageBlogByBlogTitle(@RequestParam("blogTitle") String blogTitle, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        ServerResponse<PageDto> response = blogService.pageBlogByBlogTitle(blogTitle, pageNo, pageSize);
        return response;
    }

}
