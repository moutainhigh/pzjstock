package com.pzj.core.product.write;

import java.util.List;

import com.pzj.core.product.entity.AreaScreeingsRel;

public interface AreaScreeingsRelWriteMapper {
    /**
     * 批量插入关系
     * 
     * @param list 关系实体集合
     * 
     */
    void insertRelBatch(List<AreaScreeingsRel> list);

    /**
     * 删除演艺相关的关系
     * 
     * @param actingId 演艺ID
     * @return int 删除数量
     */
    int deleteRelByActingId(Long actingId);

}