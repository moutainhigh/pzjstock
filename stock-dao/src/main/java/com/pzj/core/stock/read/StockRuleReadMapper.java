package com.pzj.core.stock.read;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.stock.entity.StockRule;

/**
 * 底层库存规则查询接口
 * 
 * @author dongchunfu
 * @version $Id: StockRuleReadMapper.java, v 0.1 2016年7月25日 下午3:55:33 dongchunfu Exp $
 */
public interface StockRuleReadMapper {

    /**
     * 根据ID获得可用状态的库存规则
     * 
     * @param ruleId 规则ID
     * @param state 状态
     * @return 库存规则实体
     */
    StockRule selectUsableRule(@Param(value = "ruleId") Long ruleId, @Param(value = "state") int state);

    /** 根据主键查询 */
    StockRule selectRuleById(Long ruleId);

    /**
     * 
     * 操作库存时获取规则类型相关校验信息
     * @param id
     * @return
     */
    StockRule queryRuleValidateStockById(@Param(value = "id") long id);

    /**
     * 分页查询
     * 
     * @param stockRule 库存规则实体
     * @param pageModel 分页查询
     * @return 库存规则实体集合
     */
    ArrayList<StockRule> selectRuleByPage(@Param(value = "stockRule") StockRule stockRule, @Param(value = "startIndex") Integer startIndex,
                                          @Param(value = "pageSize") Integer pageSize);

    /**
     * 统计查询
     * 
     * @param stockRule 库存规则实体
     * @return int 符合条件的条数
     */
    int countForPage(@Param(value = "stockRule") StockRule stockRule);

    //过滤规则id，筛选日类型的库存规则ID
    ArrayList<Long> filterDailyRule(List<Long> ruleIds);

    //根据库存规则ID集合 批量查询库存规则
    ArrayList<StockRule> queryStockRuleByIds(List<Long> ruleIds);

    /**
     * 查询供应商对应库存规则集合
     * @param ruleIds
     * @param supplierId
     * @return List<StockRule>
     */
    List<StockRule> querySupplierStockRules(@Param(value = "ruleIds") List<Long> ruleIds, @Param(value = "supplierId") Long supplierId);

    /**
     * 
     * 操作库存规则name中的sku id
     * @param id
     * @return 
     */
    String queryStockRuleNameById(@Param(value = "id") Long id);
    
    /**
     * 
     * 操作库存规则name中的sku id
     * @param id
     * @return 
     */
    int queryStockRuleNameRepeat(@Param(value = "id") Long id,@Param(value = "name") String name);
}