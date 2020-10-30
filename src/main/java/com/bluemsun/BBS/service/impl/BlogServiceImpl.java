package com.bluemsun.BBS.service.impl;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dao.BlogDao;
import com.bluemsun.BBS.dao.PageDao;
import com.bluemsun.BBS.dao.PlateDao;
import com.bluemsun.BBS.dto.BlogAndUser;
import com.bluemsun.BBS.dto.BlogPage;
import com.bluemsun.BBS.dto.PageDto;
import com.bluemsun.BBS.dto.PlateIdAndName;
import com.bluemsun.BBS.entity.Blog;
import com.bluemsun.BBS.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private PlateDao plateDao;

    @Autowired
    private PageDao pageDao;

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
        blog.setLastEditTime(new Date());
        int result = blogDao.insertBlog(blog);
        if (result == 1) {
            return ServerResponse.createBySuccess("保存blog成功！", blog);
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
    public ServerResponse<BlogAndUser> viewBlogByBlogId(int blogId) {
        if (blogId == 0) {
            return ServerResponse.createByErrorMessage("blogId为0，错误传入");
        }
        BlogAndUser blogAndUser = blogDao.viewBlogByBlogId(blogId);
        if (blogAndUser == null) {
            return ServerResponse.createByErrorCodeMessage(2, "该博客已被删除，查询失败");
        }
        return ServerResponse.createBySuccess("查询成功", blogAndUser);
    }

    /**
     * 博客选择板块
     *
     * @return
     */
    @Override
    public ServerResponse<PageDto> selectList(String plateName, int pageNo, int pageSize) {
        int startIndex = (pageNo - 1) * pageSize;
        String newPlateName = null;
        if (plateName != null) {
            newPlateName = '%' + plateName + '%';
        } else {
            newPlateName = plateName;
        }
        List<PlateIdAndName> resultList = plateDao.selectList(newPlateName, startIndex, pageSize);
        int count = plateDao.countList(newPlateName);
        PageDto pageDto = new PageDto();
        pageDto.setList(resultList);
        pageDto.setCount(count);
        return ServerResponse.createBySuccess("板块分页", pageDto);
    }

    /**
     * 通过板块id展示该板块的博客分页
     *
     * @param plateId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public ServerResponse<PageDto> pageBlogByPlateId(int plateId, int pageNo, int pageSize) {
        int startIndex = (pageNo - 1) * pageSize;
        List<BlogPage> list = pageDao.pageIndexBlog(startIndex, pageSize, 0, plateId);
        int count = pageDao.pageIndexBlogCount(0, plateId);
        PageDto pageDto = new PageDto();
        pageDto.setList(list);
        pageDto.setCount(count);
        return ServerResponse.createBySuccess("通过板块id展示该板块的博客分页", pageDto);
    }

    /**
     * 自动增加博客热度
     *
     * @param blogId
     * @return
     */
    @Override
    public ServerResponse<String> increaseBlogHot(int blogId) {
        Blog blog = blogDao.blogInfoByBlogId(blogId);
        int blogHot = blog.getBlogHot();
        blogHot++;
        int result = blogDao.increaseBlogCommentOrHotOrLike(blogId, 0, blogHot, 0);
        if (result == 1) {
            return ServerResponse.createBySuccessMessage("热度自动+1");
        }
        return ServerResponse.createByErrorMessage("热度增加失败");
    }

    /**
     * 删除博客
     *
     * @param blogId
     * @return
     */
    @Override
    public ServerResponse<String> delBlogByBlogId(int blogId) {
        int result = blogDao.delBlogByBlogId(blogId);
        if (result == 1) {
            return ServerResponse.createBySuccess("管理员删除该博客成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    /**
     * 管理员更改博客信息
     *
     * @param blog
     * @return
     */
    @Override
    public ServerResponse<Blog> adminUpdateBlogByBlogId(Blog blog) {
        int blogId = blog.getBlogId();
        int result = blogDao.adminUpdateBlogByBlogId(blog);
        if (result == 1) {
            Blog newBlogInfo = blogDao.blogInfoByBlogId(blogId);
            return ServerResponse.createBySuccess("管理员修改博客信息成功", newBlogInfo);
        }
        return ServerResponse.createByErrorMessage("修改博客信息失败");
    }

    /**
     * 首页模糊搜索博客题目
     *
     * @param blogTitle
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public ServerResponse<PageDto> pageBlogByBlogTitle(String blogTitle, int pageNo, int pageSize) {
        int startIndex = (pageNo - 1) * pageSize;
        String newBlogTitle = '%' + blogTitle + '%';
        List<BlogPage> list = blogDao.pageBlogByBlogTitle(newBlogTitle,startIndex,pageSize);
        int count = blogDao.countPageBlogByBlogTitle(newBlogTitle);
        PageDto pageDto = new PageDto();
        pageDto.setList(list);
        pageDto.setCount(count);
        return ServerResponse.createBySuccess("首页博客模糊查询结果：",pageDto);
    }
}
