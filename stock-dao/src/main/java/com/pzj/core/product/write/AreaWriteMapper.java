package com.pzj.core.product.write;

import java.util.List;

import com.pzj.core.product.entity.Area;

public interface AreaWriteMapper {
    /**
     * 主键删除
     */
    int deleteAreaById(Long areaId);

    /**
     * 新增区域
     */
    Long insertArea(Area area);

    /**
     * 更新区域
     */
    int updateAreaById(Area area);

    /**
     * 批量新增
     * 
     * @param list
     * @return
     */
    int addBatchArea(List<Area> list);

}