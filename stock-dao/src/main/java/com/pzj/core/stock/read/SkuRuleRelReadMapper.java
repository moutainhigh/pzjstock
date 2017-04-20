package com.pzj.core.stock.read;

import java.util.List;

/**
 * 查询 产品与库存规则 绑定关系
 * 
 * @author dongchunfu
 * @version $Id: SkuRuleRelReadMapper.java, v 0.1 2016年8月15日 下午4:53:59 dongchunfu Exp $
 */
public interface SkuRuleRelReadMapper {

    List<Long> selectAllRuleOrderedIds();

}