package com.bluemsun.BBS.service.impl;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dao.BlogDao;
import com.bluemsun.BBS.dao.CommentDao;
import com.bluemsun.BBS.dto.PageCommentAndUser;
import com.bluemsun.BBS.dto.PageDto;
import com.bluemsun.BBS.dto.SecondCommentAndUser;
import com.bluemsun.BBS.entity.Blog;
import com.bluemsun.BBS.entity.FirstComment;
import com.bluemsun.BBS.entity.SecondComment;
import com.bluemsun.BBS.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private BlogDao blogDao;

    /**
     * 新增一级评论
     *
     * @param firstComment
     * @return
     */
    @Override
    public ServerResponse<FirstComment> insertFirstComment(FirstComment firstComment) {
        if (firstComment == null) {
            return ServerResponse.createByErrorCodeMessage(3, "firstComment为空");
        }
        if (StringUtils.isEmpty(firstComment.getFirstContent())) {
            return ServerResponse.createByErrorCodeMessage(4, "这个评论内容为空哟，评论失败");
        }
        firstComment.setFirstCreateTime(new Date());
        int result = commentDao.insertFirstComment(firstComment);
        if (result == 1) {
            int blogId = firstComment.getFirstBlogId();
            Blog blog = blogDao.blogInfoByBlogId(blogId);
            int blogComment = blog.getBlogComment();
            blogComment++;
            blogDao.increaseBlogComment(blogId, blogComment);
            return ServerResponse.createBySuccessMessage("一级评论成功");
        }
        return ServerResponse.createByErrorMessage("发生错误，一级评论失败");
    }

    /**
     * 新增二级评论
     *
     * @param secondComment
     * @return
     */
    @Override
    public ServerResponse<SecondComment> insertSecondComment(SecondComment secondComment) {
        if (secondComment == null) {
            return ServerResponse.createByErrorCodeMessage(3, "secondComment为空");
        }
        if (StringUtils.isEmpty(secondComment.getSecondContent())) {
            return ServerResponse.createByErrorCodeMessage(4, "这个评论内容为空哟，评论失败");
        }
        secondComment.setSecondCreateTime(new Date());
        int result = commentDao.insertSecondComment(secondComment);
        if (result == 1) {
            int secondFirstId = secondComment.getSecondFirstId();
            int blogId = commentDao.secondCommentForBlog(secondFirstId);
            Blog blog = blogDao.blogInfoByBlogId(blogId);
            int blogComment = blog.getBlogComment();
            blogComment++;
            blogDao.increaseBlogComment(blogId, blogComment);
            return ServerResponse.createBySuccessMessage("二级评论成功");
        }
        return ServerResponse.createByErrorMessage("发生错误，二级评论失败");
    }


    /**
     * 评论分页
     *
     * @param firstBlogId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public ServerResponse<PageDto> pageFirstComment(int firstBlogId, int pageNo, int pageSize) {
        int startIndex = (pageNo - 1) * pageSize;
        List<PageCommentAndUser> list = commentDao.pageFirstCommentByTime(firstBlogId, startIndex, pageSize);
        int count = commentDao.pageCountFirstComment(firstBlogId);
        for (int i = 0; i < list.size(); i++) {
            int secondFirstId = list.get(i).getFirstCommentId();
            List<SecondCommentAndUser> list1 = commentDao.pageSecondCommentByCreateTimeForThree(secondFirstId);
            int secondCommentCount = commentDao.pageSecondCommentCount(secondFirstId);
            list.get(i).setSecondCommentAndUser(list1);
            list.get(i).setSecondCommentAndUserCount(secondCommentCount);
        }
        PageDto pageDto = new PageDto();
        pageDto.setList(list);
        pageDto.setCount(count);
        return ServerResponse.createBySuccess("评论展示", pageDto);
    }

    /**
     * 二级评论分页
     *
     * @param secondFirstId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public ServerResponse<PageDto> pageSecondComment(int secondFirstId, int pageNo, int pageSize) {
        int startIndex = (pageNo - 1) * pageSize;
        List<SecondCommentAndUser> list = commentDao.pageSecondCommentByCreateTime(secondFirstId, startIndex, pageSize);
        int count = commentDao.pageSecondCommentCount(secondFirstId);
        PageDto pageDto = new PageDto();
        pageDto.setList(list);
        pageDto.setCount(count);
        return ServerResponse.createBySuccess("二级评论分页", pageDto);
    }

    /**
     * 收起二级评论
     *
     * @param secondFirstId
     * @return
     */
    @Override
    public ServerResponse<PageDto> closeSecondComment(int secondFirstId) {
        List<SecondCommentAndUser> list = commentDao.pageSecondCommentByCreateTimeForThree(secondFirstId);
        int count = commentDao.pageSecondCommentCount(secondFirstId);
        PageDto pageDto = new PageDto();
        pageDto.setList(list);
        pageDto.setCount(count);
        return ServerResponse.createBySuccess("收起二级评论",pageDto);
    }


}
