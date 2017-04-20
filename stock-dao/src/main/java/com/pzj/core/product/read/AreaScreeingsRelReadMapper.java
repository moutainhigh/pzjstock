package com.pzj.core.product.read;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.product.entity.AreaScreeingsRel;

public interface AreaScreeingsRelReadMapper {

    //根据演绎id集合查询
    ArrayList<AreaScreeingsRel> selectAreaScreeingsRelByActingIds(ArrayList<Long> actingId);

    //根据演艺区域场次查询中间表信息
    AreaScreeingsRel queryAvaibleAreaScreeingsRelByUniqueKey(@Param(value = "scenicId") Long scenicId, @Param(value = "supplierId") Long supplierId,
                                                             @Param(value = "areaId") Long areaId, @Param(value = "screeingsId") Long screeingsId);

    //根据ID 查询实体
    AreaScreeingsRel selectAreaScreeingsRelById(Long relId);
}