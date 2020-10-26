package com.bluemsun.BBS.web.reception;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dto.PageDto;
import com.bluemsun.BBS.entity.User;
import com.bluemsun.BBS.service.PageService;
import com.bluemsun.BBS.util.JsonUtil;
import com.bluemsun.BBS.util.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@RequestMapping("/page")
@Controller
public class PageController {

    @Autowired
    private PageService pageService;

    /**
     * 首页博客分页展示
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/pageIndexBlog", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageDto> pageIndexBlog(HttpServletRequest httpServletRequest) {
        int pageNo = Integer.decode(httpServletRequest.getParameter("pageNo"));
        int pageSize = Integer.decode(httpServletRequest.getParameter("pageSize"));
        ServerResponse<PageDto> response = pageService.pageIndexBlog(pageNo, pageSize);
        return response;
    }

    /**
     * 个人博客分页展示
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/pageBlogByUserId.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageDto> pageBlogByUserId(HttpServletRequest httpServletRequest) {
        String loginToken = httpServletRequest.getHeader("token");
        String jsonStr = RedisPoolUtil.get(loginToken);
        if (StringUtils.isEmpty(jsonStr)) {
            return ServerResponse.createByErrorNotLogin();
        }
        User user = JsonUtil.string2Obj(jsonStr, User.class);
        int pageNo = Integer.decode(httpServletRequest.getParameter("pageNo"));
        int pageSize = Integer.decode(httpServletRequest.getParameter("pageSize"));
        ServerResponse<PageDto> response = pageService.pageBlogByUserId(user.getUserId(), pageNo, pageSize);
        return response;
    }

    /**
     * 博客选择板块时的分页
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/pagePlateWhenBlog.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageDto> pagePlateWhenBlog(HttpServletRequest httpServletRequest) {
        String loginToken = httpServletRequest.getHeader("token");
        String jsonStr = RedisPoolUtil.get(loginToken);
        if (StringUtils.isEmpty(jsonStr)) {
            return ServerResponse.createByErrorNotLogin();
        }
        int pageNo = Integer.decode(httpServletRequest.getParameter("pageNo"));
        int pageSize = Integer.decode(httpServletRequest.getParameter("pageSize"));
        // TODO: 2020/10/20
        return null;
    }
}
