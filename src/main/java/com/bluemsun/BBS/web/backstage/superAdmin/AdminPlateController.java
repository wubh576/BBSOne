package com.bluemsun.BBS.web.backstage.superAdmin;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dto.PageDto;
import com.bluemsun.BBS.entity.Plate;
import com.bluemsun.BBS.service.PlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = "/admin/plate")
public class AdminPlateController {

    @Autowired
    private PlateService plateService;

    /**
     * 插入新板块
     *
     * @param plate
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/insertPlate.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> insertPlate(@RequestBody Plate plate, HttpServletRequest httpServletRequest) {
        ServerResponse<String> response = plateService.insertPlate(plate);
        return response;
    }

    /**
     * 查看该板块名是否已经存在
     *
     * @param plate
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/checkPlateName.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkPlateName(@RequestBody Plate plate, HttpServletRequest httpServletRequest) {
        ServerResponse<String> response = plateService.checkPlateName(plate.getPlateName());
        return response;
    }

    /**
     * 更新板块信息
     *
     * @param plate
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Plate> updatePlate(@RequestBody Plate plate, HttpServletRequest httpServletRequest) {
        ServerResponse<Plate> response = plateService.updatePlate(plate);
        return response;
    }

    /**
     * 删除某板块
     *
     * @param plate
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> deletePlate(@RequestBody Plate plate, HttpServletRequest httpServletRequest) {
        ServerResponse<String> response = plateService.deletePlate(plate.getPlateId());
        return response;
    }

    /**
     * 管理员分页查看板块
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/pagePlate", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageDto> pagePlate(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize,HttpServletRequest httpServletRequest) {
        ServerResponse<PageDto> response = plateService.pagePlateForAdmin(pageNo, pageSize);
        return response;
    }

    /**
     * 管理员分页查看板块（模糊搜索）
     *
     * @param pageNo
     * @param pageSize
     * @param plateName
     * @return
     */
    @RequestMapping(value = "/pagePlateByPlateName", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageDto> pagePlateByPlateName(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize, @RequestParam("plateName") String plateName,HttpServletRequest httpServletRequest) {
        ServerResponse<PageDto> response = plateService.pagePlateForAdminByPlateName(pageNo, pageSize, plateName);
        return response;
    }

    /**
     * 取消板块置顶
     *
     * @param plate
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/delPlateTopping.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Plate> delPlateTopping(@RequestBody Plate plate, HttpServletRequest httpServletRequest) {
        ServerResponse<Plate> response = plateService.delPlateTopping(plate.getPlateId());
        return response;
    }

}
