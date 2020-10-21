package com.bluemsun.BBS.web.reception;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dto.PageDto;
import com.bluemsun.BBS.entity.Blog;
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
        User user = JsonUtil.string2Obj(jsonStr,User.class);
        secondComment.setSecondUserId(user.getUserId());
        ServerResponse<SecondComment> response = commentService.insertSecondComment(secondComment);
        return response;
    }

    /**
     * 一级评论分页
     * @param firstBlogId
     * @param pageNo
     * @param pageSize
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/pageFirstComment.do" ,method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageDto> pageFirstComment(@RequestParam("firstBlogId") int firstBlogId,@RequestParam("pageNo") int pageNo,@RequestParam("pageSize") int pageSize, HttpServletRequest httpServletRequest) {
        // TODO: 2020/10/21
        return null;
    }
}
