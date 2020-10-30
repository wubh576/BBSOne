package com.bluemsun.BBS.service.impl;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dao.BlogDao;
import com.bluemsun.BBS.dao.LikeDao;
import com.bluemsun.BBS.entity.Blog;
import com.bluemsun.BBS.entity.LikeBlog;
import com.bluemsun.BBS.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeDao likeDao;
    @Autowired
    private BlogDao blogDao;

    /**
     * 博客点赞/取消点赞
     *
     * @param userId
     * @param blogId
     * @return
     */
    @Override
    public ServerResponse<String> blogLike(int userId, int blogId) {
        //先判断该用户是否已经点过赞
        List<LikeBlog> list = likeDao.selectLike(userId);
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            int oldBlogId = list.get(i).getBlogId();
            //如果查询到了，count++，即count的值变为1
            //如果循环结束，count == 1，表示该用户已经点过赞，要执行取消点赞操作
            //如果循环结束，count == 0，表示该用户还未点赞，要执行点赞操作
            if (oldBlogId == blogId) {
                count++;
            }
        }

        //如果count == 1，表示该用户已经点过赞
        if (count == 1) {
            //在点赞表中删除该条点赞记录
            int result = likeDao.deleteBlogLike(userId, blogId);
            if (result == 1) {
                //删除记录成功
                Blog blog = blogDao.blogInfoByBlogId(blogId);
                int blogLike = blog.getBlogLike();
                blogLike--;
                //点赞数减为0，将无法使用动态sql，要重新写一个
                if (blogLike == 0) {
                    //更新该博客点赞数
                    int result1 = blogDao.decreaseBlogLikeToZero(blogId);
                    if (result1 == 1) {
                        return ServerResponse.createBySuccessCodeMessage(20, "取消点赞成功");
                    }
                }
                if (blogLike > 0) {
                    //更新该博客点赞数
                    int result1 = blogDao.increaseBlogCommentOrHotOrLike(blogId, 0, 0, blogLike);
                    if (result1 == 1) {
                        return ServerResponse.createBySuccessCodeMessage(20, "取消点赞成功");
                    }
                }
            }
        }

        //如果count == 0，表示该用户还未点赞
        if (count == 0) {
            //添加记录至点赞表
            int result = likeDao.insertBlogLike(userId, blogId);
            if (result == 1) {
                //如果记录添加成功
                Blog blog = blogDao.blogInfoByBlogId(blogId);
                //查询到原来有多少赞
                int blogLike = blog.getBlogLike();
                //点赞数+1
                blogLike++;
                //更新该博客点赞数
                int result1 = blogDao.increaseBlogCommentOrHotOrLike(blogId, 0, 0, blogLike);
                if (result1 == 1) {
                    return ServerResponse.createBySuccessCodeMessage(21, "点赞成功");
                }
            }
        }

        return ServerResponse.createByErrorMessage("发生错误");
    }

    /**
     * 查看是否点赞
     *
     * @param userId
     * @return
     */
    @Override
    public List<LikeBlog> checkBlogLike(int userId) {
        List<LikeBlog> list = likeDao.selectLike(userId);
        return list;
    }
}