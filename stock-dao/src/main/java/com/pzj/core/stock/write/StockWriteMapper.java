package com.pzj.core.stock.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.stock.entity.Stock;

public interface StockWriteMapper {
    /**
     * 根据库存ID集合查询库存基本信息, 加锁.
     * @param stockIds
     * @return
     */
    public List<Stock> queryStockByIdsForUpdate(@Param(value = "stockIds") List<Long> stockIds);

    /**
     * 根据库存ID查询库存基本信息, 加锁.
     * @param stockId
     * @return
     */
    Stock queryStockByIdForUpdate(@Param(value = "stockId") long stockId);

    /**
     * 批量添加
     * 
     * @param list 库存实体
     * @return
     */
    Integer insertStockBatch(@Param(value = "list") List<Stock> list);

    /**
              * 批量修改
     * 
     * @param list 库存实体
     * @return
     */
    Integer updateBatch(List<Stock> list);

    /**
     * 更新库存数量.
     * @param stockId
     * @param usedNum
     * @param remainNum
     * @return
     */
    int updateStockNum(@Param(value = "stockId") long stockId, @Param(value = "usedNum") int usedNum, @Param(value = "remainNum") int remainNum);

    /**
     * 根据库存规则id修改库存状态
     * 
     * @param stockRuleId 库存规则id
     * @param state 库存规则状态
     * @return int 影响行数
     */
    int updateStockStateByStockRuleId(@Param(value = "ruleId") Long ruleId, @Param(value = "state") Integer state);

    /**
     * 根据库存规则库存上限
     * 
     * @param stockRuleId 库存规则id
     * @param upperLimit 库存规则库存上限
     * @return int 影响行数
     */
    int updateStockUpperLimitByStockRuleId(@Param(value = "ruleId") Long ruleId, @Param(value = "upperLimit") Integer upperLimit);

    /**
     * 批量修改库存数量
     * @param list
     * @return
     */
    int batchUpdateStockNum(@Param(value = "list") List<Stock> list);

    /**
     * 添加库存数据
     * @param stock
     * @return
     */
    int insertStock(Stock stock);
}