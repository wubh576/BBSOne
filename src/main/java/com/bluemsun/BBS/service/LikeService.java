package com.bluemsun.BBS.service;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.entity.LikeBlog;

import java.util.List;

public interface LikeService {

    /**
     * 博客点赞/取消点赞
     *
     * @param userId
     * @param blogId
     * @return
     */
    ServerResponse<String> blogLike(int userId, int blogId);


    List<LikeBlog> checkBlogLike(int userId);


}
