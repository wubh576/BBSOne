package com.bluemsun.BBS.dao;

import com.bluemsun.BBS.dto.PlateIdAndName;
import com.bluemsun.BBS.entity.Plate;

import java.util.List;

public interface PlateDao {

    /**
     * 新增板块
     *
     * @param plate
     * @return
     */
    int insertPlate(Plate plate);

    /**
     * 查看板块名是否存在
     *
     * @param plateName
     * @return
     */
    int checkPlateName(String plateName);

    /**
     * 返回板块id及名称
     *
     * @return
     */
    List<PlateIdAndName> selectList(String plateName);

}
