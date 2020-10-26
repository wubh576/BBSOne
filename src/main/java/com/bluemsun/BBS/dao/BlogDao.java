package com.bluemsun.BBS.dao;

import com.bluemsun.BBS.dto.BlogAndUser;
import com.bluemsun.BBS.entity.Blog;
import com.bluemsun.BBS.entity.BlogFile;
import org.apache.ibatis.annotations.Param;

public interface BlogDao {
    /**
     * 新增博客
     *
     * @param blog
     * @return
     */
    int insertBlog(Blog blog);

    /**
     * 通过blogId查询博客信息
     *
     * @param blogId
     * @return
     */
    Blog blogInfoByBlogId(int blogId);

    /**
     * 通过blogId展示博客
     *
     * @param blogId
     * @return
     */
    BlogAndUser viewBlogByBlogId(int blogId);

    /**
     * 存储blogFile
     *
     * @param blogFile
     * @return
     */
    int insertBlogFile(BlogFile blogFile);

    /**
     * 该博客评论数+1
     *
     * @param blogId
     */
    void increaseBlogComment(@Param("blogId") int blogId,@Param("blogComment")int blogComment);


}
