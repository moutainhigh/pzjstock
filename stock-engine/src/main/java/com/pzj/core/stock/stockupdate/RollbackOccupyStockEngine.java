/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.stockupdate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.entity.LockRecord;
import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.enums.StockStateEnum;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.exception.stock.NotFoundStockException;
import com.pzj.core.stock.exception.stock.StockStateException;
import com.pzj.core.stock.model.OccupyStockRequestModel;
import com.pzj.core.stock.read.LockRecordReadMapper;
import com.pzj.core.stock.write.LockRecordWriteMapper;
import com.pzj.core.stock.write.StockWriteMapper;

/**
 * 
 * @author Administrator
 * @version $Id: RollbackOccupyStockEngine.java, v 0.1 2016年8月19日 下午3:18:02 Administrator Exp $
 */
@Component("rollbackOccupyStockEngine")
public class RollbackOccupyStockEngine {
    @Resource(name = "lockRecordReadMapper")
    private LockRecordReadMapper  lockRecordReadMapper;
    @Resource(name = "lockRecordWriteMapper")
    private LockRecordWriteMapper lockRecordWriteMapper;
    @Resource(name = "stockWriteMapper")
    private StockWriteMapper      stockWriteMapper;

    @Transactional(value = "stock.transactionManager", timeout = 2)
    public void rollbackOccupyStock(OccupyStockRequestModel requestModel) {
        //1. 查看回滚库存数据是否存在.
        List<LockRecord> lockList = lockRecordReadMapper.queryStockRecordByTranId(requestModel.getTransactionId());
        if (CommonUtils.checkCollectionIsNull(lockList)) {
            return;
        }

        //2. 获取修改库存数据
        List<Stock> stockList = getStockList(lockList);
        canOperateStock(stockList);

        //3. 计算库存
        computeStockNum(stockList, lockList);

        //4. 操作数据库回滚数据.
        stockWriteMapper.batchUpdateStockNum(stockList);
        List<Long> lockIds = new ArrayList<Long>();
        for (LockRecord lockRecord : lockList) {
            lockIds.add(lockRecord.getId());
        }
        lockRecordWriteMapper.delBatchLockByIds(lockIds);

    }

    /**
     * 获取库存集合
     * @param lockList
     * @return List<Stock>
     */
    private List<Stock> getStockList(List<LockRecord> lockList) {
        List<Long> stockIds = new ArrayList<Long>();
        for (LockRecord lockRecord : lockList) {
            if (!stockIds.contains(lockRecord.getStockId())) {
                stockIds.add(lockRecord.getStockId());
            }
        }
        return stockWriteMapper.queryStockByIdsForUpdate(stockIds);
    }

    /**
     * 判断库存是否可以回滚
     * @param stockList
     */
    private void canOperateStock(List<Stock> stockList) {
        if (CommonUtils.checkCollectionIsNull(stockList)) {
            throw new NotFoundStockException(StockExceptionCode.NOT_FOUND_STOCK_ERR_MSG);
        }
        for (Stock stock : stockList) {
            if (stock == null) {
                throw new NotFoundStockException(StockExceptionCode.NOT_FOUND_STOCK_ERR_MSG);
            }

            int state = stock.getState();
            if (state != StockStateEnum.AVAILABLE.getState()) {
                throw new StockStateException(StockExceptionCode.STOCK_STATE_ERR_MSG);
            }
        }
    }

    /**
     * 获取释放库存数
     * @param lockList
     * @return Map
     */
    private Map<Long, Integer> getOccupyStockMap(List<LockRecord> lockList) {
        Map<Long, Integer> stockOccupyMap = new HashMap<Long, Integer>();
        Long stockId = null;
        Integer hisOccupyNum = null, curOccupyNum = null;
        for (LockRecord lockRecord : lockList) {
            stockId = lockRecord.getStockId();
            curOccupyNum = lockRecord.getLockNum();
            if (stockOccupyMap.containsKey(stockId)) {
                hisOccupyNum = stockOccupyMap.get(stockId);
                stockOccupyMap.put(stockId, hisOccupyNum + curOccupyNum);
            } else {
                stockOccupyMap.put(stockId, curOccupyNum);
            }
        }
        return stockOccupyMap;
    }

    /**
     * 计算库存数量.
     * @param stockList
     * @param lockList
     */
    private void computeStockNum(List<Stock> stockList, List<LockRecord> lockList) {
        Map<Long, Integer> stockOccupyMap = getOccupyStockMap(lockList);
        for (Stock stock : stockList) {
            int releaseNum = stockOccupyMap.get(stock.getId());
            int used = stock.getUsedNum() == null ? 0 : stock.getUsedNum();
            stock.setUsedNum(used - releaseNum);
            stock.setRemainNum(stock.getRemainNum() + releaseNum);
        }
    }
}
