package com.pzj.core.stock.write;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.stock.entity.StockRule;

public interface StockRuleWriteMapper {
    //插入实体
    int insertStockRule(StockRule rule);

    //根据主键更新非空字段
    int updateStockRuleById(StockRule rule);

    //更改库存规则状态
    int updateStockRuleStateById(StockRule rule);

    //伪删除库存规则
    int fakeDeleteStockRule(Long id);
    
    //更改库存规则名称
    int updateStockRuleName(@Param("id") Long id,@Param("name") String name);

    StockRule selectStockRuleById(Long id);
}