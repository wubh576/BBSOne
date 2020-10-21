package com.bluemsun.BBS.web.automation;

import com.bluemsun.BBS.common.ServerResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/auto")
@Controller
public class HotController {

    @RequestMapping(value = "/hotBlog", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> hotBlog() {


        return null;
    }
}
