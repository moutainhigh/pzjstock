package com.pzj.core.stock.write;

import java.util.List;

import com.pzj.core.stock.entity.SkuRuleRel;

public interface SkuRuleRelWriteMapper {
    //批量添加关联关系
    void addBatchSkuRuleRel(List<SkuRuleRel> rels);

    //清空关系表
    int clearSkuAndRuleRel();
}