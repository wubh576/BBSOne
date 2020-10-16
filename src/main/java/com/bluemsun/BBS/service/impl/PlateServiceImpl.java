package com.bluemsun.BBS.service.impl;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dao.PlateDao;
import com.bluemsun.BBS.entity.Plate;
import com.bluemsun.BBS.service.PlateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PlateServiceImpl implements PlateService {

    @Autowired
    private PlateDao plateDao;

    /**
     * 新建板块
     *
     * @param plate
     * @return
     */
    @Override
    public ServerResponse<String> insertPlate(Plate plate) {
        if (plate == null) {
            return ServerResponse.createByErrorMessage("plate为空");
        }
        plate.setCreateTime(new Date());
        plate.setLastEditTime(new Date());
        plate.setPlateImg("http://bluesun.natapp1.cc/uploads/test.jpg");
        int result = plateDao.insertPlate(plate);
        if (result == 1) {
            return ServerResponse.createBySuccess("新建板块成功");
        }
        return ServerResponse.createByErrorCodeMessage(3, "新建板块失败");
    }

    /**
     * 查看板块名是否存在
     * @param plateName
     * @return
     */
    @Override
    public ServerResponse<String> checkPlateName(String plateName) {
        if(StringUtils.isEmpty(plateName)) {
            return ServerResponse.createByErrorCodeMessage(3,"plateName为空");
        }
        int count = plateDao.checkPlateName(plateName);
        if(count == 0) {
            return ServerResponse.createBySuccessMessage("验证成功，该板块名可用");
        }
        return ServerResponse.createByErrorMessage("该板块名不可用！");
    }

}
