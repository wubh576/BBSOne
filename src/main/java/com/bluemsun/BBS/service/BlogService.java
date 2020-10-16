package com.bluemsun.BBS.service;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dto.PlateIdAndName;
import com.bluemsun.BBS.entity.Blog;
import com.bluemsun.BBS.entity.BlogFile;

import java.util.List;

public interface BlogService {

    /**
     * 新增博客
     *
     * @param blog
     * @return
     */
    ServerResponse<Blog> insertBlog(Blog blog);

    /**
     * 通过blogId查询blog信息
     *
     * @param blogId
     * @return
     */
    ServerResponse<Blog> viewBlogByBlogId(int blogId);

    /**
     * 插入Blog图片
     *
     * @param blogFile
     * @return
     */
    ServerResponse<String> insertBlogFile(BlogFile blogFile);

    /**
     * 选择博客对应板块
     *
     * @return
     */
    ServerResponse<List<PlateIdAndName>> selectList(String plateName);
}
