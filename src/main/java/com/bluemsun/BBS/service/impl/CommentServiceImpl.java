package com.bluemsun.BBS.service.impl;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dao.CommentDao;
import com.bluemsun.BBS.entity.FirstComment;
import com.bluemsun.BBS.entity.SecondComment;
import com.bluemsun.BBS.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

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
        if(StringUtils.isEmpty(firstComment.getFirstContent())) {
            return ServerResponse.createByErrorCodeMessage(4, "这个评论内容为空哟，评论失败");
        }
        firstComment.setFirstCreateTime(new Date());
        int result = commentDao.insertFirstComment(firstComment);
        if (result == 1) {
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
        if(StringUtils.isEmpty(secondComment.getSecondContent())) {
            return ServerResponse.createByErrorCodeMessage(4, "这个评论内容为空哟，评论失败");
        }
        secondComment.setSecondCreateTime(new Date());
        int result = commentDao.insertSecondComment(secondComment);
        if (result == 1) {
            return ServerResponse.createBySuccessMessage("二级评论成功");
        }
        return ServerResponse.createByErrorMessage("发生错误，二级评论失败");
    }
}
