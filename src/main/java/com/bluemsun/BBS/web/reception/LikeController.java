package com.bluemsun.BBS.web.reception;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dto.ListDto;
import com.bluemsun.BBS.entity.Blog;
import com.bluemsun.BBS.entity.LikeBlog;
import com.bluemsun.BBS.entity.User;
import com.bluemsun.BBS.service.LikeService;
import com.bluemsun.BBS.util.JsonUtil;
import com.bluemsun.BBS.util.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/like")
@Controller
public class LikeController {

    @Autowired
    private LikeService likeService;

    /**
     * 博客点赞/取消点赞
     *
     * @param blog
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/blog.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> blogLike(@RequestBody Blog blog, HttpServletRequest httpServletRequest) {
        String loginToken = httpServletRequest.getHeader("token");
        String jsonStr = RedisPoolUtil.get(loginToken);
        if (StringUtils.isEmpty(jsonStr)) {
            return ServerResponse.createByErrorNotLogin();
        }
        User user = JsonUtil.string2Obj(jsonStr, User.class);
        ServerResponse<String> response = likeService.blogLike(user.getUserId(), blog.getBlogId());
        return response;
    }

    /**
     * 检查用户是否点赞
     *
     * @param listDto
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/checkBlogLike", method = RequestMethod.POST)
    @ResponseBody
    // 0表示未点赞
    // 1表示已点赞
    public Map<String, Object> checkBlogLike(@RequestBody ListDto listDto, HttpServletRequest httpServletRequest) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List signList = new ArrayList();
        List<Integer> newlist = listDto.getList();
        String loginToken = httpServletRequest.getHeader("token");
        String jsonStr = RedisPoolUtil.get(loginToken);
        //如果用户未登陆，状态列全返回 0
        if (StringUtils.isEmpty(jsonStr)) {
            for (int i = 0; i < newlist.size(); i++) {
                signList.add(i, 0);
            }
            modelMap.put("signList", signList);
            modelMap.put("msg", "该用户未登陆，因此所有博客均未点赞");
            return modelMap;
        }
        //如果用户已登陆
        User user = JsonUtil.string2Obj(jsonStr, User.class);
        int userId = user.getUserId();
        List blogLikeList = likeService.checkBlogLike(userId);
        //根据前端传过来的list作为外层来进行遍历
        for (int i = 0; i < newlist.size(); i++) {
            //点赞标志
            int flag = 0;
            //对userId的点赞表进行遍历
            for (int j = 0; j < blogLikeList.size(); j++) {
                LikeBlog likeBlog = (LikeBlog) blogLikeList.get(j);
                //如果找到匹配的，flag++
                if (newlist.get(i) == likeBlog.getBlogId()) {
                    flag++;
                }
            }
            //如果flag != 0 表示该用户已经点过赞了，标志列置为1
            //如果flag == 0 表示该用户未点赞，标志列置为0
            if (flag != 0) {
                signList.add(i, 1);
            } else {
                signList.add(i, 0);
            }
        }
        modelMap.put("signList", signList);
        modelMap.put("msg", "返回用户点赞列");
        return modelMap;
    }
}
