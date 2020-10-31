package com.bluemsun.BBS.service.impl;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dao.*;
import com.bluemsun.BBS.dto.*;
import com.bluemsun.BBS.entity.Blog;
import com.bluemsun.BBS.entity.User;
import com.bluemsun.BBS.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private UserDao userDao;

    private static Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);

    /**
     * 新增博客
     *
     * @param blog
     * @return
     */
    @Override
    public ServerResponse<Blog> insertBlog(Blog blog) {
        logger.info("start");
        if (blog == null) {
            return ServerResponse.createByErrorMessage("blog为空");
        }
        blog.setCreateTime(new Date());
        blog.setLastEditTime(new Date());
        int result = blogDao.insertBlog(blog);
        logger.debug("你保存的blog信息为{}",blog.toString());
        if (result == 1) {
            return ServerResponse.createBySuccess("保存blog成功！", blog);
        }
        logger.info("end");
        return ServerResponse.createByErrorMessage("其他错误，保存blog失败！");
    }

    /**
     * 通过blogId查询博客信息
     *
     * @param blogId
     * @param userId
     * @return
     */
    @Override
    public ServerResponse<BlogAndUser> viewBlogByBlogId(int blogId, int userId) {
        logger.info("start");
        BlogAndUser blogAndUser = blogDao.viewBlogByBlogId(blogId);
        if (userId == 0) {
            return ServerResponse.createBySuccessCodeMessage(50,"未登录状态下查看博客信息",blogAndUser);
        }
        if (userId != 0) {
            // 是博主查看博客
            int result = commentDao.checkDelComment(userId,blogId);
            if(result == 1) {
                return ServerResponse.createBySuccessCodeMessage(51,"博主身份",blogAndUser);
            }
            // 是超级管理员
            User user = userDao.selectByUserId(userId);
            if(user.getUserType() == 3) {
                return ServerResponse.createBySuccessCodeMessage(53,"超级管理员身份",blogAndUser);
            }
            int plateId = blogAndUser.getPlateId();
            List<UserIdAndName> list = userDao.checkModerator(plateId);
            int flag = 0;
            for(int i=0;i<list.size();i++) {
                if(userId == list.get(i).getUserId()) {
                    flag++;
                }
            }
            if(flag != 0 ) {
                return ServerResponse.createBySuccessCodeMessage(52,"版主身份",blogAndUser);
            }
        }
        return ServerResponse.createBySuccessCodeMessage(54,"普通用户身份",blogAndUser);
    }

    /**
     * 博客选择板块
     *
     * @return
     */
    @Override
    public ServerResponse<PageDto> selectList(String plateName, int pageNo, int pageSize) {
        logger.info("start");
        int startIndex = (pageNo - 1) * pageSize;
        String newPlateName = null;
        if (plateName != null) {
            newPlateName = '%' + plateName + '%';
        } else {
            newPlateName = plateName;
        }
        List<PlateIdAndName> resultList = plateDao.selectList(newPlateName, startIndex, pageSize);
        logger.info("查询的列表信息为{}",resultList);
        int count = plateDao.countList(newPlateName);
        logger.info("查询列表总数量为",count);
        PageDto pageDto = new PageDto();
        pageDto.setList(resultList);
        pageDto.setCount(count);
        logger.info("end");
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
        logger.info("start");
        int startIndex = (pageNo - 1) * pageSize;
        List<BlogPage> list = pageDao.pageIndexBlog(startIndex, pageSize, 0, plateId);
        logger.info("查询的列表信息为{}",list);
        int count = pageDao.pageIndexBlogCount(0, plateId);
        logger.info("查询列表总数量为",count);
        PageDto pageDto = new PageDto();
        pageDto.setList(list);
        pageDto.setCount(count);
        logger.info("end");
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
        logger.info("start");
        logger.debug("删除博客id为{}",blogId);
        int result = blogDao.delBlogByBlogId(blogId);
        if (result == 1) {
            return ServerResponse.createBySuccess("删除该博客成功");
        }
        logger.info("end");
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
        logger.info("start");
        logger.debug("修改前博客信息为{}",blog.toString());
        int blogId = blog.getBlogId();
        int result = blogDao.adminUpdateBlogByBlogId(blog);
        if (result == 1) {
            Blog newBlogInfo = blogDao.blogInfoByBlogId(blogId);
            logger.debug("修改后端博客信息为{}",newBlogInfo.toString());
            return ServerResponse.createBySuccess("管理员修改博客信息成功", newBlogInfo);
        }
        logger.info("end");
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
        logger.info("start");
        int startIndex = (pageNo - 1) * pageSize;
        String newBlogTitle = '%' + blogTitle + '%';
        List<BlogPage> list = blogDao.pageBlogByBlogTitle(newBlogTitle,startIndex,pageSize);
        logger.debug("查询列表为{}",list);
        int count = blogDao.countPageBlogByBlogTitle(newBlogTitle);
        logger.debug("查询列表总数为{}",count);
        PageDto pageDto = new PageDto();
        pageDto.setList(list);
        pageDto.setCount(count);
        logger.info("end");
        return ServerResponse.createBySuccess("首页博客模糊查询结果：",pageDto);
    }

    /**
     * 更新博客
     *
     * @param blog
     * @return
     */
    @Override
    public ServerResponse<Blog> updateBlog(Blog blog) {
        logger.info("start");
        int blogId = blog.getBlogId();
        int result = blogDao.adminUpdateBlogByBlogId(blog);
        if (result == 1) {
            Blog newBlogInfo = blogDao.blogInfoByBlogId(blogId);
            logger.debug("修改后端博客信息为{}",newBlogInfo.toString());
            return ServerResponse.createBySuccess("博主修改博客信息成功", newBlogInfo);
        }
        return ServerResponse.createByError();
    }


}
