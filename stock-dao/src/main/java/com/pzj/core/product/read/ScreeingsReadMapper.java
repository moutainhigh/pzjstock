package com.pzj.core.product.read;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.product.entity.Screeings;

public interface ScreeingsReadMapper {
    /**
     * 根据主键查询
     * 
     * @param screeingsId 场次ID
     * @return Screeings
     */
    public Screeings selectScreeingsById(Long screeingsId);

    /**
     * 根据场次参数查询
     * 
     * @param area 区域实体
     * @return List<Area>
     */
    ArrayList<Screeings> selectScreeingsesByParam(@Param(value = "screeings") Screeings screeings);

}