package com.bluemsun.BBS.service.impl;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dao.BlogDao;
import com.bluemsun.BBS.entity.Blog;
import com.bluemsun.BBS.entity.BlogFile;
import com.bluemsun.BBS.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    /**
     * 新增博客
     *
     * @param blog
     * @return
     */
    @Override
    public ServerResponse<Blog> insertBlog(Blog blog) {
        if (blog == null) {
            return ServerResponse.createByErrorMessage("blog为空");
        }
        blog.setCreateTime(new Date());
        int result = blogDao.insertBlog(blog);
        if (result == 1) {
            return ServerResponse.createBySuccess("保存blog成功！",blog);
        }
        return ServerResponse.createByErrorMessage("其他错误，保存blog失败！");
    }

    /**
     * 通过blogId查询博客信息
     *
     * @param blogId
     * @return
     */
    @Override
    public ServerResponse<Blog> viewBlogByBlogId(int blogId) {
        if(blogId == 0) {
            return ServerResponse.createByErrorMessage("blogId为0，错误传入");
        }
        Blog blog = null;
        blog = blogDao.viewBlogByBlogId(blogId);
        if (blog == null) {
            return  ServerResponse.createByErrorCodeMessage(2,"该博客已被删除，查询失败");
        }
        return ServerResponse.createBySuccess("查询成功",blog);
    }

    /**
     * 插入Blog图片
     * @param blogFile
     * @return
     */
    @Override
    public ServerResponse<String> insertBlogFile(BlogFile blogFile) {
        if(blogFile == null) {
            return ServerResponse.createByErrorMessage("blogFile为空，错误传入");
        }
        int result = blogDao.insertBlogFile(blogFile);
        if(result == 1) {
            return ServerResponse.createBySuccess("保存blogFile成功",blogFile.getUri());
        }
        return ServerResponse.createByErrorCodeMessage(2,"其他错误，保存blogFile失败");
    }
}
