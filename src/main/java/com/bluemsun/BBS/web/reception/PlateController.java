package com.bluemsun.BBS.web.reception;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dto.PageDto;
import com.bluemsun.BBS.dto.PlateShow;
import com.bluemsun.BBS.entity.Plate;
import com.bluemsun.BBS.service.PlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/plate")
@Controller
public class PlateController {

    @Autowired
    private PlateService plateService;

    /**
     * 首页热门板块展示
     *
     * @return
     */
    @RequestMapping(value = "/indexHotPlate", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageDto> indexHotPlate() {
        ServerResponse<PageDto> response = plateService.indexHotPlate();
        return response;
    }

    /**
     * 板块分页展示
     *
     * @return
     */
    @RequestMapping(value = "/pagePlate", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageDto> pagePlate(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        ServerResponse<PageDto> response = plateService.pagePlate(pageNo, pageSize);
        return response;
    }

    /**
     * 通过板块id展示板块信息
     *
     * @param plate
     * @return
     */
    @RequestMapping(value = "/pageBlogByPlateId", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PlateShow> pageBlogByPlateId(@RequestBody Plate plate) {
        ServerResponse<PlateShow> response = plateService.plateShow(plate.getPlateId());
        return response;
    }
}
