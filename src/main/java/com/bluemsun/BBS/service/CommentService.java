package com.bluemsun.BBS.service;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dto.PageDto;
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

    /**
     * 评论分页
     *
     * @param firstBlogId
     * @param pageNo
     * @param pageSize
     * @return
     */
    ServerResponse<PageDto> pageFirstComment(int firstBlogId, int pageNo, int pageSize);

    /**
     * 二级评论分页
     *
     * @param secondFirstId
     * @param pageNo
     * @param pageSize
     * @return
     */
    ServerResponse<PageDto> pageSecondComment(int secondFirstId, int pageNo, int pageSize);

    /**
     * 收起二级评论
     *
     * @param secondFirstId
     * @return
     */
    ServerResponse<PageDto> closeSecondComment(int secondFirstId);

}
