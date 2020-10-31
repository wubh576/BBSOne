package com.bluemsun.BBS.service.impl;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.dao.PageDao;
import com.bluemsun.BBS.dao.PlateDao;
import com.bluemsun.BBS.dto.*;
import com.bluemsun.BBS.entity.Plate;
import com.bluemsun.BBS.entity.User;
import com.bluemsun.BBS.service.PlateService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PlateServiceImpl implements PlateService {

    @Autowired
    private PlateDao plateDao;
    @Autowired
    private PageDao pageDao;

    private static Logger logger = LoggerFactory.getLogger(PlateServiceImpl.class);

    /**
     * 新建板块
     *
     * @param plate
     * @return
     */
    @Override
    public ServerResponse<String> insertPlate(Plate plate) {
        logger.info("start");
        if (plate == null) {
            return ServerResponse.createByErrorMessage("plate为空");
        }
        plate.setCreateTime(new Date());
        plate.setLastEditTime(new Date());
        // TODO: 2020/10/16 阿里云
        plate.setPlateImg("http://bluemsum.tech:8080/uploads/test.jpg");
        // TODO: 2020/10/16 线下
//        plate.setPlateImg("http://bluesun.natapp1.cc/uploads/plateDemo.jpg");
        int result = plateDao.insertPlate(plate);
        logger.debug("新建板块信息{}",plate.toString());
        if (result == 1) {
            return ServerResponse.createBySuccess("新建板块成功");
        }
        logger.info("end");
        return ServerResponse.createByErrorCodeMessage(3, "新建板块失败");
    }

    /**
     * 查看板块名是否存在
     *
     * @param plateName
     * @return
     */
    @Override
    public ServerResponse<String> checkPlateName(String plateName) {
        logger.info("start");
        if (StringUtils.isEmpty(plateName)) {
            return ServerResponse.createByErrorCodeMessage(3, "plateName为空");
        }
        int count = plateDao.checkPlateName(plateName);
        if (count == 1) {
            return ServerResponse.createByErrorCodeMessage(4, "该板块名已存在，请更换板块名");
        }
        if (count == 0) {
            logger.debug("所填板块名为{}",plateName);
            return ServerResponse.createBySuccessMessage("验证成功，该板块名可用");
        }
        logger.info("end");
        return ServerResponse.createByErrorMessage("该板块名不可用！");
    }

    /**
     * 首页热门板块展示
     *
     * @return
     */
    @Override
    public ServerResponse<PageDto> indexHotPlate() {
        logger.info("start");
        List<Plate> list = plateDao.indexHotPlate();
        PageDto pageDto = new PageDto();
        pageDto.setList(list);
        pageDto.setCount(10);
        logger.debug("展示数据为{}",pageDto.toString());
        logger.info("end");
        return ServerResponse.createBySuccess("热门板块", pageDto);
    }

    /**
     * 板块分页展示
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public ServerResponse<PageDto> pagePlate(int pageNo, int pageSize) {
        logger.info("start");
        int startIndex = (pageNo - 1) * pageSize;
        List<Plate> list = plateDao.pagePlateByCreateTime(startIndex, pageSize);
        int count = plateDao.pagePlateCount();
        PageDto pageDto = new PageDto();
        pageDto.setList(list);
        pageDto.setCount(count);
        logger.info("end");
        return ServerResponse.createBySuccess("板块分页展示", pageDto);
    }

    /**
     * 板块详细信息展示
     *
     * @param plateId
     * @return
     */
    @Override
    public ServerResponse<PlateShow> plateShow(int plateId) {
        logger.info("start");
        PlateShow plateShow = plateDao.plateShow(plateId);
        List<User> list = plateDao.plateShowUser(plateId);
        plateShow.setList(list);
        logger.info("end");
        return ServerResponse.createBySuccess("板块详细信息展示", plateShow);
    }

    /**
     * 增加板块热度
     *
     * @param plateId
     * @return
     */
    @Override
    public ServerResponse<String> increasePlateHot(int plateId) {
        logger.info("start");
        Plate plate = plateDao.selectPlate(plateId);
        int plateHot = plate.getPlateHot();
        plateHot++;
        int result = plateDao.increasePlateHot(plateId, plateHot);
        if (result == 1) {
            logger.debug("板块{}热度+1",plateId);
            return ServerResponse.createBySuccessMessage("板块热度+1");
        }
        logger.info("end");
        return ServerResponse.createByErrorMessage("板块增加热度失败");
    }

    /**
     * 更新板块信息
     *
     * @param plate
     * @return
     */
    @Override
    public ServerResponse<Plate> updatePlate(Plate plate) {
        logger.info("start");
        int count = plateDao.updatePlate(plate);
        if (count == 1) {
            int plateId = plate.getPlateId();
            Plate newPlate = plateDao.selectPlate(plateId);
            logger.debug("更新后的板块信息为{}",newPlate.toString());
            return ServerResponse.createBySuccess("更改板块信息成功", newPlate);
        }
        logger.info("end");
        return ServerResponse.createByErrorMessage("更新板块信息失败");
    }

    /**
     * 删除某板块
     *
     * @param plateId
     * @return
     */
    @Override
    public ServerResponse<String> deletePlate(int plateId) {
        logger.info("start");
        int result = plateDao.deletePlate(plateId);
        if (result == 1) {
            logger.debug("删除的板块id为{}",plateId);
            return ServerResponse.createBySuccessMessage("成功删除该板块");
        }
        logger.info("end");
        return ServerResponse.createByErrorMessage("删除失败");
    }

    /**
     * 板块分页展示给管理员
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public ServerResponse<PageDto> pagePlateForAdmin(int pageNo, int pageSize) {
        logger.info("start");
        int startIndex = (pageNo - 1) * pageSize;
        List<PlatePageForAdmin> list = plateDao.pagePlate(startIndex, pageSize, null);
        int count = plateDao.countPagePlate(null);
        for (int i = 0; i < list.size(); i++) {
            int plateId = list.get(i).getPlateId();
            List<UserIdAndName> list1 = plateDao.checkModerator(plateId);
            list.get(i).setList(list1);
        }
        PageDto pageDto = new PageDto();
        pageDto.setList(list);
        logger.debug("分页获得的列表为{}",list);
        pageDto.setCount(count);
        logger.debug("分页数据总量为{}",count);
        logger.info("end");
        return ServerResponse.createBySuccess("板块分页展示给管理员", pageDto);
    }

    @Override
    public ServerResponse<PageDto> pagePlateForAdminByPlateName(int pageNo, int pageSize, String plateName) {
        logger.info("start");
        int startIndex = (pageNo - 1) * pageSize;
        String newPlateName = '%' + plateName + '%';
        List<PlatePageForAdmin> list = plateDao.pagePlate(startIndex, pageSize, newPlateName);
        int count = plateDao.countPagePlate(newPlateName);

        for (int i = 0; i < list.size(); i++) {
            int plateId = list.get(i).getPlateId();
            List<UserIdAndName> list1 = plateDao.checkModerator(plateId);
            list.get(i).setList(list1);
        }
        PageDto pageDto = new PageDto();
        pageDto.setList(list);
        logger.debug("分页获得的列表为{}",list);
        pageDto.setCount(count);
        logger.debug("分页数据总量为{}",count);
        logger.info("end");
        return ServerResponse.createBySuccess("板块分页展示给管理员", pageDto);
    }

    /**
     * 管理员取消板块置顶
     *
     * @param plateId
     * @return
     */
    @Override
    public ServerResponse<Plate> delPlateTopping(int plateId) {
        logger.info("start");
        int result = plateDao.delPlateTopping(plateId);
        if(result == 1) {
            Plate plate = plateDao.selectPlate(plateId);
            logger.debug("取消板块成功，该板块信息为{}",plate.toString());
            return ServerResponse.createBySuccess("取消置顶成功",plate);
        }
        logger.info("end");
        return ServerResponse.createByErrorMessage("发生错误");
    }

    /**
     * 管理员查看我的板块
     *
     * @param userId
     * @return
     */
    @Override
    public ServerResponse<List<PlateIdAndName>> plateList(int userId) {
        List<PlateIdAndName> list = plateDao.plateList(userId);
        if(list.size() == 0) {
            return ServerResponse.createBySuccessCodeMessage(30,"您还未管理任何板块");
        }
        return ServerResponse.createBySuccess("您所管理的板块有：",list);
    }

    /**
     * 通过板块id返回板块详细信息
     *
     * @param plateId
     * @return
     */
    @Override
    public ServerResponse<Plate> plateInfo(int plateId) {
        Plate plate = plateDao.selectPlate(plateId);
        return ServerResponse.createBySuccess("板块详细信息为：",plate);
    }

    /**
     * 查看该板块所拥有的博客
     *
     * @param plateId
     * @return
     */
    @Override
    public ServerResponse<PageDto> pageBlogByPlateId(int plateId,int pageNo,int pageSize) {
        logger.info("start");
        int startIndex = (pageNo - 1) * pageSize;
        List<BlogPage> list = pageDao.pageIndexBlog(startIndex, pageSize, 0, plateId);
        logger.info("查询的列表信息为{}",list);
        int count = pageDao.pageIndexBlogCount(0, plateId);
        logger.info("查询列表总数量为",count);
        PageDto pageDto = new PageDto();
        pageDto.setList(list);
        pageDto.setCount(count);
        logger.info("end");
        return ServerResponse.createBySuccess("通过板块id展示该板块的博客分页", pageDto);
    }

}
