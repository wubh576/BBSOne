package com.bluemsun.BBS.dao;

import com.bluemsun.BBS.dto.PageCommentAndUser;
import com.bluemsun.BBS.dto.SecondCommentAndUser;
import com.bluemsun.BBS.entity.FirstComment;
import com.bluemsun.BBS.entity.SecondComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentDao {


    /**
     * 新增一级评论
     *
     * @param firstComment
     * @return
     */
    int insertFirstComment(FirstComment firstComment);

    /**
     * 新增二级评论
     *
     * @param secondComment
     * @return
     */
    int insertSecondComment(SecondComment secondComment);


    /**
     * 按照一级评论的点赞数分页
     *
     * @param firstBlogId
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<PageCommentAndUser> pageFirstCommentByLike(@Param("firstBlogId") int firstBlogId, @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    /**
     * 按照一级评论创建时间分页
     *
     * @param firstBlogId
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<PageCommentAndUser> pageFirstCommentByTime(@Param("firstBlogId") int firstBlogId, @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    /**
     * 一级评论分页总条数
     *
     * @param firstBlogId
     * @return
     */
    int pageCountFirstComment(int firstBlogId);

    int secondCommentForBlog(int secondFirstId);

    List<SecondCommentAndUser> pageSecondCommentByCreateTimeForThree(@Param("secondFirstId") int secondFirstId);

    int pageSecondCommentCount(@Param("secondFirstId") int secondFirstId);

    /**
     * 二级评论分页
     *
     * @param secondFirstId
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<SecondCommentAndUser> pageSecondCommentByCreateTime(@Param("secondFirstId") int secondFirstId, @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    int checkDelComment(@Param("userId") int userId,@Param("blogId") int blogId);

    int delFirstComment(int firstCommentId);

    int delSecondComment(int secondCommentId);
}
