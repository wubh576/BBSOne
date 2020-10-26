package com.bluemsun.BBS.web.reception;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dto.PageDto;
import com.bluemsun.BBS.entity.FirstComment;
import com.bluemsun.BBS.entity.SecondComment;
import com.bluemsun.BBS.entity.User;
import com.bluemsun.BBS.service.CommentService;
import com.bluemsun.BBS.util.JsonUtil;
import com.bluemsun.BBS.util.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "/comment")
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;


    /**
     * 新增一级评论
     *
     * @param firstComment
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/insertFirstComment.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<FirstComment> insertFirstComment(@RequestBody FirstComment firstComment, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        String jsonStr = RedisPoolUtil.get(token);
        if (StringUtils.isEmpty(jsonStr)) {
            return ServerResponse.createByErrorNotLogin();
        }
        User user = JsonUtil.string2Obj(jsonStr, User.class);
        firstComment.setFirstUserId(user.getUserId());
        ServerResponse<FirstComment> response = commentService.insertFirstComment(firstComment);
        return response;
    }

    /**
     * 新增二级评论
     *
     * @param secondComment
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/insertSecondComment.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<SecondComment> insertSecondComment(@RequestBody SecondComment secondComment, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        String jsonStr = RedisPoolUtil.get(token);
        if (StringUtils.isEmpty(jsonStr)) {
            return ServerResponse.createByErrorNotLogin();
        }
        User user = JsonUtil.string2Obj(jsonStr, User.class);
        secondComment.setSecondUserId(user.getUserId());
        ServerResponse<SecondComment> response = commentService.insertSecondComment(secondComment);
        return response;
    }

    /**
     * 一级评论分页
     *
     * @param firstBlogId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/pageFirstComment", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageDto> pageFirstComment(@RequestParam("firstBlogId") int firstBlogId, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        ServerResponse<PageDto> response = commentService.pageFirstComment(firstBlogId, pageNo, pageSize);
        return response;
    }

    /**
     * 二级评论分页
     *
     * @param firstCommendId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/pageSecondComment", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageDto> pageSecondComment(@RequestParam("firstCommentId") int firstCommendId, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        ServerResponse<PageDto> response = commentService.pageSecondComment(firstCommendId, pageNo, pageSize);
        return response;
    }

    // TODO: 2020/10/24 收起，前端传一级评论id，后端反前三条
    /**
     * 收起二级评论
     *
     * @param firstCommendId
     * @return
     */
    @RequestMapping(value = "/closePageSecondComment", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageDto> closePageSecondComment(@RequestParam("firstCommendId") int firstCommendId) {
        ServerResponse<PageDto> response = commentService.closeSecondComment(firstCommendId);
        return response;
    }
}
