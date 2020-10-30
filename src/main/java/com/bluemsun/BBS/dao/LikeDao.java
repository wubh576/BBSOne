package com.bluemsun.BBS.dao;

import com.bluemsun.BBS.entity.LikeBlog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LikeDao {

    int insertBlogLike(@Param("userId") int userId, @Param("blogId") int blogId);

    List<LikeBlog> selectLike(int userId);

    int deleteBlogLike(@Param("userId") int userId, @Param("blogId") int blogId);

}
