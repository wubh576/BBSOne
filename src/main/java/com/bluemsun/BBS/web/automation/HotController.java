package com.bluemsun.BBS.web.automation;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.entity.Blog;
import com.bluemsun.BBS.entity.Plate;
import com.bluemsun.BBS.service.BlogService;
import com.bluemsun.BBS.service.PlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/auto/hot")
@Controller
public class HotController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private PlateService plateService;

    /**
     * 浏览博客热度增加
     *
     * @param blog
     * @return
     */
    @RequestMapping(value = "/blog", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> blog(@RequestBody Blog blog) {
        ServerResponse<String> response = blogService.increaseBlogHot(blog.getBlogId());
        return response;
    }

    /**
     * 浏览板块热度增加
     *
     * @param plate
     * @return
     */
    @RequestMapping(value = "/plate", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> plate(@RequestBody Plate plate) {
        ServerResponse<String> response = plateService.increasePlateHot(plate.getPlateId());
        return response;
    }
}
