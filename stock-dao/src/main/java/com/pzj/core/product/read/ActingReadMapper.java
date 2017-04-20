package com.pzj.core.product.read;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.product.entity.Acting;

public interface ActingReadMapper {
    /**
     * 根据主键查询
     * 
     * @param actingId 演绎ID
     * @return Acting
     */
    public Acting selectActingById(Long actingId);

    /**
     * 根据演绎参数查询
     * 
     * @param acting 区域实体
     * @return List<Acting>
     */
    ArrayList<Acting> selectActingsByParam(@Param(value = "acting") Acting acting);
}