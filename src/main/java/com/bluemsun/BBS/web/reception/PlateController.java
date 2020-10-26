package com.bluemsun.BBS.web.reception;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dto.PageDto;
import com.bluemsun.BBS.service.PlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "/plate")
@Controller
public class PlateController {

    @Autowired
    private PlateService plateService;

    /**
     * 首页热门板块展示
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/indexHotPlate", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageDto> indexHotPlate(HttpServletRequest httpServletRequest) {
        ServerResponse<PageDto> response = plateService.indexHotPlate();
        return response;
    }

    /**
     * 板块分页展示
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/pagePlate", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageDto> pagePlate(HttpServletRequest httpServletRequest) {
        int pageNo = Integer.decode(httpServletRequest.getParameter("pageNo"));
        int pageSize = Integer.decode(httpServletRequest.getParameter("pageSize"));
        ServerResponse<PageDto> response = plateService.pagePlate(pageNo, pageSize);
        return response;
    }
}
