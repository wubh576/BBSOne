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
    ServerResponse<Plate> insertPlate(Plate plate);

}
