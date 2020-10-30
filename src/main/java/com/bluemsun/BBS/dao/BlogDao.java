package com.bluemsun.BBS.dao;

import com.bluemsun.BBS.dto.BlogAndUser;
import com.bluemsun.BBS.dto.BlogPage;
import com.bluemsun.BBS.entity.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * 该博客评论数/点赞数/热度+1
     *
     * @param blogId
     * @param blogHot
     * @param blogLike
     */
    int increaseBlogCommentOrHotOrLike(@Param("blogId") int blogId, @Param("blogComment") int blogComment,@Param("blogHot") int blogHot,@Param("blogLike")int blogLike);

    int decreaseBlogLikeToZero(int blogId);

    int delBlogByBlogId(int blogId);

    /**
     * 管理员用更改博客信息
     *
     * @param blog
     * @return
     */
    int adminUpdateBlogByBlogId(Blog blog);

    List<BlogPage> pageBlogByBlogTitle(@Param("blogTitle")String blogTitle,@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    int countPageBlogByBlogTitle(@Param("blogTitle")String blogTitle);

}
