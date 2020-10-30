package com.bluemsun.BBS.dao;

import com.bluemsun.BBS.dto.BlogPage;
import com.bluemsun.BBS.dto.PlateIdAndName;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PageDao {

    /**
     * 首页博客分页查询
     *
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<BlogPage> pageIndexBlog(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize,@Param("userId") int userId,@Param("plateId") int plateId);

    /**
     * 首页博客分页的总数量
     *
     * @return
     */
    int pageIndexBlogCount(@Param("userId") int userId,@Param("plateId") int plateId);

}
