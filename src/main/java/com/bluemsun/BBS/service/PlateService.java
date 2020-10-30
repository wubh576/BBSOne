package com.bluemsun.BBS.service;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dto.PageDto;
import com.bluemsun.BBS.dto.PlateShow;
import com.bluemsun.BBS.entity.Plate;

public interface PlateService {

    /**
     * 新增板块
     *
     * @param plate
     * @return
     */
    ServerResponse<String> insertPlate(Plate plate);

    /**
     * 查看板块名是否存在
     *
     * @param plateName
     * @return
     */
    ServerResponse<String> checkPlateName(String plateName);

    /**
     * 首页热门板块展示
     *
     * @return
     */
    ServerResponse<PageDto> indexHotPlate();

    /**
     * 板块分页
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    ServerResponse<PageDto> pagePlate(int pageNo, int pageSize);

    /**
     * 板块详细信息展示
     *
     * @param plateId
     * @return
     */
    ServerResponse<PlateShow> plateShow(int plateId);

    /**
     * 增加板块热度
     *
     * @param plateId
     * @return
     */
    ServerResponse<String> increasePlateHot(int plateId);

    /**
     * 更新板块信息
     *
     * @param plate
     * @return
     */
    ServerResponse<Plate> updatePlate(Plate plate);

    /**
     * 删除某板块
     *
     * @param plateId
     * @return
     */
    ServerResponse<String> deletePlate(int plateId);

    /**
     * 板块分页展示给管理员
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    ServerResponse<PageDto> pagePlateForAdmin(int pageNo, int pageSize);

    /**
     * 模糊搜索板块分页展示给管理员
     *
     * @param pageNo
     * @param pageSize
     * @param plateName
     * @return
     */
    ServerResponse<PageDto> pagePlateForAdminByPlateName(int pageNo, int pageSize, String plateName);

    /**
     * 管理员取消板块置顶
     *
     * @param plateId
     * @return
     */
    ServerResponse<Plate> delPlateTopping(int plateId);
}
