package com.bluemsun.BBS.dao;

import com.bluemsun.BBS.dto.PlateIdAndName;
import com.bluemsun.BBS.entity.Plate;
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
     * 返回板块id及名称
     *
     * @return
     */
    List<PlateIdAndName> selectList(String plateName);

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

}
