package com.pzj.core.stock.write;

import java.util.List;

import com.pzj.core.stock.entity.StockSeatRel;

public interface StockSeatRelWriteMapper {
    /**
     * 批量添加
     * 
     * @param list 库存实体
     * @return
     */
    Integer addBatch(List<StockSeatRel> list);

    /**
     * 批量修改库存座位状态
     * @param stockSeatList
     * @return
     */
    Integer batchUpdateStockSeatState(List<StockSeatRel> stockSeatList);

    /**
     * 回滚交易占座信息
     * @param transactionId
     * @return
     */
    Integer rollbackTransactionSeatInfo(String transactionId);
}