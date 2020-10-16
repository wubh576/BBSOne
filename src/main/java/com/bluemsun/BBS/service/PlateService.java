package com.bluemsun.BBS.service;

import com.bluemsun.BBS.common.ServerResponse;
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


}
