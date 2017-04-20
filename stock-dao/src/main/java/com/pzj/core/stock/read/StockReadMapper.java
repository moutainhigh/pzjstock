package com.pzj.core.stock.read;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.stock.entity.Stock;

public interface StockReadMapper {
    /**
     * 根据主键查询
     * 
     * @param stockId 库存ID
     * @return Stock
     */
    public Stock selectStockById(Long stockId);

    /**
     * 根据参数精确查询
     * 
     * @param stock 库存实体
     * @return List<Stock>
     */
    public ArrayList<Stock> selectStockListByParam(Stock stock);

    // 根据库存规则id及库存时间 查询库存规则id集合
    ArrayList<Long> selectIdsInRuleIdsAndStockTime(@Param(value = "ruleIds") List<Long> ruleIds, @Param(value = "stockTime") Integer stockTime);

    /**
     * 通过库存规则查询可用库存集合
     * @param stockRuleId
     * @return List<Stock>
     */
    public List<Stock> queryStockByRuleId(@Param(value = "stockRuleId") Long stockRuleId);

    /**
     * 通过库存规则和时间查查库存
     * @param stockRuleId
     * @param stockTime
     * @return
     */
    public Stock queryStockByRuleAndTime(@Param(value = "stockRuleId") Long stockRuleId, @Param(value = "stockTime") Integer stockTime);

    /**
     * 通过库存规则和时间集合查询库存列表
     * @param stockRuleId
     * @param stockTimeList
     * @return
     */
    public List<Stock> queryStockByDateList(@Param(value = "stockRuleId") Long stockRuleId, @Param(value = "list") Set<Integer> list);

    /**
     * 查询供应商实时库存集合
     * @param ruleIds
     * @param stockTime
     * @return List<Stock>
     */
    public List<Stock> querySupplierRealTimeStock(@Param(value = "ruleIds") List<Long> ruleIds, @Param(value = "stockTime") Integer stockTime);
}