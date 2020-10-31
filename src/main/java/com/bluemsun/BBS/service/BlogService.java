package com.bluemsun.BBS.service;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dto.BlogAndUser;
import com.bluemsun.BBS.dto.PageDto;
import com.bluemsun.BBS.entity.Blog;

public interface BlogService {

    /**
     * 新增博客
     *
     * @param blog
     * @return
     */
    ServerResponse<Blog> insertBlog(Blog blog);

    /**
     * 通过blogId查询blog信息
     *
     * @param blogId
     * @param userId
     * @return
     */
    ServerResponse<BlogAndUser> viewBlogByBlogId(int blogId,int userId);

    /**
     * 选择博客对应板块
     *
     * @return
     */
    ServerResponse<PageDto> selectList(String plateName, int pageNo, int pageSize);

    /**
     * 通过板块id展示该板块的博客分页
     *
     * @param plateId
     * @param pageNo
     * @param pageSize
     * @return
     */
    ServerResponse<PageDto> pageBlogByPlateId(int plateId, int pageNo, int pageSize);

    ServerResponse<String> increaseBlogHot(int blogId);

    ServerResponse<String> delBlogByBlogId(int blogId);

    /**
     * 管理员更改博客信息
     *
     * @param blog
     * @return
     */
    ServerResponse<Blog> adminUpdateBlogByBlogId(Blog blog);

    /**
     * 首页模糊搜索博客题目
     *
     * @param blogTitle
     * @param pageNo
     * @param pageSize
     * @return
     */
    ServerResponse<PageDto> pageBlogByBlogTitle(String blogTitle, int pageNo, int pageSize);

    ServerResponse<Blog> updateBlog(Blog blog);
}
