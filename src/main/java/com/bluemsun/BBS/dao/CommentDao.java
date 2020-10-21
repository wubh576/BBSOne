package com.bluemsun.BBS.dao;

import com.bluemsun.BBS.entity.FirstComment;
import com.bluemsun.BBS.entity.SecondComment;

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

}
