package com.bluemsun.BBS.web.backstage.blogger;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.entity.Blog;
import com.bluemsun.BBS.entity.FirstComment;
import com.bluemsun.BBS.entity.SecondComment;
import com.bluemsun.BBS.service.BlogService;
import com.bluemsun.BBS.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "/blogger")
@Controller
public class BloggerController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    /**
     * 删除一级评论
     *
     * @param firstComment
     * @return
     */
    @RequestMapping(value = "/delFirstComment",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> delFirstComment(@RequestBody FirstComment firstComment) {
        ServerResponse<String> response = commentService.delFirstComment(firstComment.getFirstCommentId());
        return response;
    }

    /**
     * 删除二级评论
     *
     * @param secondComment
     * @return
     */
    @RequestMapping(value = "/delSecondComment",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> delSecondComment(@RequestBody SecondComment secondComment) {
        ServerResponse<String> response = commentService.delSecondComment(secondComment.getSecondCommentId());
        return response;
    }

    /**
     * 更改博客
     *
     * @param blog
     * @return
     */
    @RequestMapping(value = "/updateBlog.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Blog> updateBlog(@RequestBody Blog blog) {
        ServerResponse<Blog> response = blogService.updateBlog(blog);
        return response;
    }

    /**
     * 删除博客
     *
     * @param blog
     * @return
     */
    @RequestMapping(value = "/deBlog.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> delBlog(@RequestBody Blog blog) {
        ServerResponse<String> response = blogService.delBlogByBlogId(blog.getBlogId());
        return response;
    }

}
