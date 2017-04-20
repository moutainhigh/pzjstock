package com.pzj.core.product.write;

import java.util.List;

import com.pzj.core.product.entity.Screeings;

public interface ScreeingsWriteMapper {
    //删除
    int deleteScreeingsById(Long screeingsId);

    //新增
    Long insertScreeings(Screeings screeings);

    //更新
    int updateScreeingsById(Screeings screeings);

    //批量新增
    int addBatchScreeings(List<Screeings> list);
}
