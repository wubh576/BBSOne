package com.bluemsun.BBS.dao;

import com.bluemsun.BBS.dto.PlateIdAndName;
import com.bluemsun.BBS.dto.PlatePageForAdmin;
import com.bluemsun.BBS.dto.PlateShow;
import com.bluemsun.BBS.dto.UserIdAndName;
import com.bluemsun.BBS.entity.Plate;
import com.bluemsun.BBS.entity.PlateAndUser;
import com.bluemsun.BBS.entity.User;
import org.apache.ibatis.annotations.Param;

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
     * 返回板块
     *
     * @return
     */
    List<PlateIdAndName> selectList(@Param("plateName") String plateName, @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    int countList(String plateName);

    /**
     * 首页热门板块展示
     *
     * @return
     */
    List<Plate> indexHotPlate();

    /**
     * 板块分页展示
     * 按照创建时间
     *
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<Plate> pagePlateByCreateTime(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    /**
     * 板块分页展示
     * 按照热度
     *
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<Plate> pagePlateByHot(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    /**
     * 板块分页总数
     *
     * @return
     */
    int pagePlateCount();

    /**
     * 展示板块id
     *
     * @param plateId
     * @return
     */
    PlateShow plateShow(@Param("plateId") int plateId);

    List<User> plateShowUser(@Param("plateId") int plateId);

    int increasePlateHot(@Param("plateId") int plateId, @Param("plateHot") int plateHot);

    Plate selectPlate(int plateId);

    List<PlateIdAndName> listPlateName(int userId);

    /**
     * 更新板块信息
     *
     * @param plate
     * @return
     */
    int updatePlate(Plate plate);

    /**
     * 删除某板块
     *
     * @param plateId
     * @return
     */
    int deletePlate(int plateId);

    List<PlatePageForAdmin> pagePlate(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize,@Param("plateName")String plateName);

    int countPagePlate(@Param("plateName")String plateName);

    List<UserIdAndName> checkModerator(int plateId);

    int delPlateTopping(int plateId);


}
