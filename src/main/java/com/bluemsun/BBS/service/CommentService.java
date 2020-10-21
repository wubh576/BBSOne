package com.bluemsun.BBS.service;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.entity.FirstComment;
import com.bluemsun.BBS.entity.SecondComment;

public interface CommentService {

    /**
     * 新增一级评论
     *
     * @param firstComment
     * @return
     */
    ServerResponse<FirstComment> insertFirstComment(FirstComment firstComment);

    /**
     * 新增二级评论
     *
     * @param secondComment
     * @return
     */
    ServerResponse<SecondComment> insertSecondComment(SecondComment secondComment);

}
