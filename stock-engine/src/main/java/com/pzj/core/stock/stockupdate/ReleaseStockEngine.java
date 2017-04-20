package com.pzj.core.stock.stockupdate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.entity.LockRecord;
import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.entity.StockRule;
import com.pzj.core.stock.enums.StockStateEnum;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.exception.errcode.StockRuleExceptionCode;
import com.pzj.core.stock.exception.rule.NotFoundStockRuleException;
import com.pzj.core.stock.exception.stock.NotFoundStockException;
import com.pzj.core.stock.exception.stock.ReleaseNumException;
import com.pzj.core.stock.exception.stock.StockStateException;
import com.pzj.core.stock.model.OccupyStockRequestModel;
import com.pzj.core.stock.read.LockRecordReadMapper;
import com.pzj.core.stock.read.StockReadMapper;
import com.pzj.core.stock.read.StockRuleReadMapper;
import com.pzj.core.stock.write.LockRecordWriteMapper;
import com.pzj.core.stock.write.StockWriteMapper;

/**
 * 释放库存执行引擎.
 * @author YRJ
 *
 */
@Component(value = "releaseStockEngine")
public class ReleaseStockEngine {

    @Resource(name = "lockRecordReadMapper")
    private LockRecordReadMapper  lockRecordReadMapper;

    @Resource(name = "stockReadMapper")
    private StockReadMapper       stockReadMapper;

    @Resource(name = "lockRecordWriteMapper")
    private LockRecordWriteMapper lockRecordWriteMapper;

    @Resource(name = "stockWriteMapper")
    private StockWriteMapper      stockWriteMapper;

    @Resource(name = "stockRuleReadMapper")
    private StockRuleReadMapper   stockRuleReadMapper;

    public void releaseStock(List<OccupyStockRequestModel> requestModelList) {
        //1. 判断库存是否已被锁定, 无被锁库存则无需释放.
        List<LockRecord> lockRecordList = checkExistLockRecords(requestModelList);
        if (CommonUtils.checkCollectionIsNull(lockRecordList)) {
            return; // 容错机制, 当锁库存记录不存在时, 无需释放.
        }

        //2. 获取库存和库存记录信息 加锁.
        lockRecordList = getLockRecords(lockRecordList);
        List<Stock> stockList = getStocks(lockRecordList);

        //3. 获取操作库存对象map
        Map<String, Integer> releaseRecordMap = new HashMap<String, Integer>();
        Map<Long, Integer> releaseStockMap = new HashMap<Long, Integer>();
        getReleaseStockMap(requestModelList, releaseRecordMap, releaseStockMap);

        //4. 判断和计算是否可以释放锁库存记录
        checkAndComputeRecord(lockRecordList, releaseRecordMap);
        checkAndComputeStock(stockList, releaseStockMap);

        //5. 更新库存记录.
        lockRecordWriteMapper.userUpdateBatchLock(lockRecordList);
        stockWriteMapper.batchUpdateStockNum(stockList);

    }

    /**
     * 检查并计算库存
     * @param stockList
     * @param releaseStockMap
     */
    private void checkAndComputeStock(List<Stock> stockList, Map<Long, Integer> releaseStockMap) {
        for (Stock stock : stockList) {
            canReleaseStock(stock);

            Integer releaseNum = releaseStockMap.get(stock.getId());
            if (CommonUtils.checkIntegerIsNull(releaseNum, true)) {
                throw new NotFoundStockException(StockExceptionCode.NOT_FOUND_STOCK_ERR_MSG);
            }
            computeStockNum(stock, releaseNum);
        }
    }

    /**
     * 检查并计算锁库存记录
     * @param lockRecordList
     * @param releaseMap
     */
    private void checkAndComputeRecord(List<LockRecord> lockRecordList, Map<String, Integer> releaseRecordMap) {
        for (LockRecord lockRecord : lockRecordList) {
            String tranId = lockRecord.getTransactionId();
            Long productId = lockRecord.getProductId();
            Integer releaseNum = releaseRecordMap.get(tranId + productId);

            canReleaseStockRecord(lockRecord, releaseNum);

            computeLockRecordNum(lockRecord, releaseNum);
        }
    }

    /**
     * 获取释放库存map对象
     * @param requestModelList
     * @param releaseRecordMap
     * @param releaseStockMap
     */
    private void getReleaseStockMap(List<OccupyStockRequestModel> requestModelList, Map<String, Integer> releaseRecordMap, Map<Long, Integer> releaseStockMap) {
        Long stockId = null;
        Integer curNum = null;
        for (OccupyStockRequestModel model : requestModelList) {
            stockId = model.getStockId();
            curNum = model.getStockNum();
            releaseRecordMap.put(model.getTransactionId() + model.getProductId(), model.getStockNum());
            if (releaseStockMap.containsKey(stockId)) {
                releaseStockMap.put(stockId, curNum + releaseStockMap.get(stockId));
            } else {
                releaseStockMap.put(stockId, curNum);
            }
        }
    }

    /**
     * 获取操作库存数据
     * @param lockRecordList
     * @return List<Stock>
     */
    private List<Stock> getStocks(List<LockRecord> lockRecordList) {
        List<Long> stockIds = new ArrayList<Long>();
        for (LockRecord lockRecord : lockRecordList) {
            stockIds.add(lockRecord.getStockId());
        }
        return stockWriteMapper.queryStockByIdsForUpdate(stockIds);
    }

    /**
     * 获取操作锁库存记录
     * @param lockRecordList
     * @return List<LockRecord>
     */
    private List<LockRecord> getLockRecords(List<LockRecord> lockRecordList) {
        List<Long> lockRecordIds = new ArrayList<Long>();
        for (LockRecord lockRecord : lockRecordList) {
            lockRecordIds.add(lockRecord.getId());
        }
        return lockRecordWriteMapper.queryLockRecordByIdsForUpdate(lockRecordIds);
    }

    /**
     * 判断是否存在锁库存记录
     * @param requestModelList
     * @return List<LockRecord>
     */
    private List<LockRecord> checkExistLockRecords(List<OccupyStockRequestModel> requestModelList) {
        List<LockRecord> lockRecordList = new ArrayList<LockRecord>();
        for (OccupyStockRequestModel releaseModel : requestModelList) {
            LockRecord record = lockRecordReadMapper.queryLockedRecord(releaseModel.getTransactionId(), releaseModel.getProductId());
            if (!CommonUtils.checkObjectIsNull(record)) {
                lockRecordList.add(record);
            }
        }
        return lockRecordList;
    }

    /**
     * 根据锁库存记录判断是否可以释放库存
     * @param record
     * @param releaseNum
     */
    private void canReleaseStockRecord(LockRecord record, int releaseNum) {
        if (record.getLockNum() < releaseNum) {
            throw new ReleaseNumException(StockExceptionCode.RELEASE_STOCK_NUM_ERR_MSG);
        }
    }

    /**
     * 判断库存可被释放.
     * @param stock
     */
    private void canReleaseStock(Stock stock) {
        if (null == stock) {
            throw new NotFoundStockException(StockExceptionCode.NOT_FOUND_STOCK_ERR_MSG);
        }

        int state = stock.getState();
        if (state != StockStateEnum.AVAILABLE.getState()) {
            throw new StockStateException(StockExceptionCode.STOCK_STATE_ERR_MSG);
        }

        StockRule stockRule = stockRuleReadMapper.queryRuleValidateStockById(stock.getRuleId());
        if (null == stockRule) {
            throw new NotFoundStockRuleException(StockRuleExceptionCode.NOT_FOUND_STOCK_RULE_ERR_MSG);
        }

        //        int ruleType = stockRule.getType() == null ? 0 : stockRule.getType();
        //        int isNeedReturn = stockRule.getWhetherReturn() == null ? 0 : stockRule.getWhetherReturn();
        //        if (ruleType == StockRuleTypeEnum.SINGLE.getRuleType() && isNeedReturn == StockReturnTypeEnum.NOT_NEED_RETURN.getReturnType()) {
        //            throw new StockReturnTypeException(StockRuleExceptionCode.RETURN_TYPE_ERR_MSG);
        //        }

    }

    /**
     * 计算锁库存记录.
     * @param record
     * @param releaseNum
     */
    private void computeLockRecordNum(LockRecord record, int releaseNum) {
        record.setLockNum(record.getLockNum() - releaseNum);
    }

    /**
     * 计算库存数量.
     * @param stock
     * @param releaseNum
     */
    private void computeStockNum(Stock stock, int releaseNum) {
        stock.setUsedNum(stock.getUsedNum() - releaseNum);
        stock.setRemainNum(stock.getRemainNum() + releaseNum);
    }

}
