package com.bluemsun.BBS.service.impl;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dao.PageDao;
import com.bluemsun.BBS.dto.BlogPage;
import com.bluemsun.BBS.dto.PageDto;
import com.bluemsun.BBS.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private PageDao pageDao;

    /**
     * 首页博客分页
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public ServerResponse<PageDto> pageIndexBlog(int pageNo, int pageSize) {
        int startIndex = (pageNo - 1) * pageSize;
        int userId = 0;
        List<BlogPage> list = pageDao.pageIndexBlog(startIndex, pageSize, userId,0);
        int count = pageDao.pageIndexBlogCount(userId,0);
        PageDto pageDto = new PageDto();
        pageDto.setCount(count);
        pageDto.setList(list);
        return ServerResponse.createBySuccess("分页结果", pageDto);
    }

    /**
     * 个人中心博客分页
     *
     * @param userId
     * @return
     */
    @Override
    public ServerResponse<PageDto> pageBlogByUserId(int userId, int pageNo, int pageSize) {
        int startIndex = (pageNo - 1) * pageSize;
        List<BlogPage> list = pageDao.pageIndexBlog(startIndex, pageSize, userId,0);
        int count = pageDao.pageIndexBlogCount(userId,0);
        PageDto pageDto = new PageDto();
        pageDto.setCount(count);
        pageDto.setList(list);
        return ServerResponse.createBySuccess("分页结果",pageDto);
    }

}
