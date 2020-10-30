package com.bluemsun.BBS.dao;

import com.bluemsun.BBS.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class BlogDaoTest extends BaseTest {

    @Autowired
    private BlogDao blogDao;

    @Test
    public void increaseBlogCommentOrHotOrLike() {
        blogDao.increaseBlogCommentOrHotOrLike(1,1,0,0);
    }
}