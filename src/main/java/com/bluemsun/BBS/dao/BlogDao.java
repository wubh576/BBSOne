package com.bluemsun.BBS.dao;

import com.bluemsun.BBS.entity.Blog;
import com.bluemsun.BBS.entity.BlogFile;

public interface BlogDao {
    /**
     * 新增博客
     *
     * @param blog
     * @return
     */
    int insertBlog(Blog blog);

    /**
     * 通过blogId查询blog信息
     *
     * @param blogId
     * @return
     */
    Blog viewBlogByBlogId(int blogId);

    /**
     * 存储blogFile
     *
     * @param blogFile
     * @return
     */
    int insertBlogFile(BlogFile blogFile);

}
