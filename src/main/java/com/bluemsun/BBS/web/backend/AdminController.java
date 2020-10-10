package com.bluemsun.BBS.web.backend;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.entity.Blog;
import com.bluemsun.BBS.entity.Plate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {




    public ServerResponse<Blog> insert(@RequestBody Plate plate, HttpServletRequest httpServletRequest) {
        if(plate == null){
            return ServerResponse.createByErrorMessage("plate为空");
        }


        return null;
    }


}
