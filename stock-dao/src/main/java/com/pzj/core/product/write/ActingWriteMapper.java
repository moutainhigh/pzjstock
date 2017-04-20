package com.pzj.core.product.write;

import com.pzj.core.product.entity.Acting;

public interface ActingWriteMapper {
    /**
     * 根据主键ID删除
     * 
     * @param actingId 演绎ID
     * @return 影响行数
     */
    int deleteActingById(Long actingId);

    /**
     * 新增演绎
     * 
     * @param acting 演绎实体
     * @return 影响行数
     */
    Long insertActing(Acting acting);

    /**
     * 根据主键更新全部字段
     * 
     * @param acting 演绎实体
     * @return 影响行数
     */
    int updateActingById(Acting acting);
}