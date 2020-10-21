package com.bluemsun.BBS.service;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dto.PageDto;

public interface PageService {

    /**
     * 首页博客分页
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    ServerResponse<PageDto> pageIndexBlog(int pageNo, int pageSize);

    /**
     * 个人中心博客分页
     *
     * @param userId
     * @return
     */
    ServerResponse<PageDto> pageBlogByUserId(int userId,int pageNo, int pageSize);

}
