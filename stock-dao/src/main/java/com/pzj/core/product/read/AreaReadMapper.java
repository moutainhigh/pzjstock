package com.pzj.core.product.read;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.product.entity.Area;

public interface AreaReadMapper {
    /**
     * 根据主键查询
     * 
     * @param areaId 区域ID
     * @return Area
     */
    Area selectAreaById(Long areaId);

    /**
     * 通过区域集合查找区域列表
     * @param list
     * @return
     */
    List<Area> queryAreaByIds(List<Long> list);

    /**
     * 根据区域参数查询
     * 
     * @param area 区域实体
     * @return List<Area>
     */
    ArrayList<Area> selectAreasByParam(@Param(value = "area") Area area);
}