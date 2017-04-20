/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.stockupdate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.common.exception.ParameterErrorException;
import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.entity.LockRecord;
import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.entity.StockRule;
import com.pzj.core.stock.enums.OperateStockBussinessType;
import com.pzj.core.stock.enums.StockStateEnum;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.exception.stock.AddRepeatStockException;
import com.pzj.core.stock.exception.stock.LimitedException;
import com.pzj.core.stock.exception.stock.NoExistBatchLockStockRecordException;
import com.pzj.core.stock.exception.stock.NonOpeHistoryStockException;
import com.pzj.core.stock.exception.stock.NotFoundStockException;
import com.pzj.core.stock.exception.stock.ReleaseNumException;
import com.pzj.core.stock.exception.stock.ShortageStockException;
import com.pzj.core.stock.exception.stock.StockStateException;
import com.pzj.core.stock.model.StockBatchLockModel;
import com.pzj.core.stock.read.LockRecordReadMapper;
import com.pzj.core.stock.read.StockRuleReadMapper;
import com.pzj.core.stock.write.LockRecordWriteMapper;
import com.pzj.core.stock.write.StockWriteMapper;
import com.pzj.framework.toolkit.Check;

/**
 * 
 * @author Administrator
 * @version $Id: StockBatchLockEngine.java, v 0.1 2016年8月3日 下午7:46:47 Administrator Exp $
 */
@Component("stockBatchLockEngine")
public class StockBatchLockEngine {
    @Resource(name = "stockWriteMapper")
    private StockWriteMapper      stockWriteMapper;

    @Resource(name = "lockRecordWriteMapper")
    private LockRecordWriteMapper lockRecordWriteMapper;

    @Resource(name = "lockRecordReadMapper")
    private LockRecordReadMapper  lockRecordReadMapper;

    @Resource(name = "stockRuleReadMapper")
    private StockRuleReadMapper   stockRuleReadMapper;

    @SuppressWarnings("unchecked")
    @Transactional(value = "stock.transactionManager", timeout = 2)
    public void batchLockAndReleaseStock(StockBatchLockModel stockBatchLockModel) {
        //1、获取库存id集合
        List<Long> stockIds = getStockIds(stockBatchLockModel);

        //2、查询库存集合
        List<Stock> stockList = null;
        if (!CommonUtils.checkObjectIsNull(stockIds)) {
            stockList = stockWriteMapper.queryStockByIdsForUpdate(stockIds);
        }

        if (CommonUtils.checkObjectIsNull(stockList) || stockIds.size() != stockList.size()) {
            throw new NotFoundStockException(StockExceptionCode.NOT_FOUND_STOCK_ERR_MSG);
        }

        int operateType = stockBatchLockModel.getOperateType();
        Map<Long, Integer> operateStockMap = stockBatchLockModel.getOperateStockMap();
        Long userId = stockBatchLockModel.getUserId();
        List<LockRecord> lockRecordList = null;

        //获取用户锁库存记录信息
        List<LockRecord> lockRecordHistory = null;
        if (!CommonUtils.checkObjectIsNull(stockIds)) {
            lockRecordHistory = lockRecordReadMapper.queryLockRecordByUser(stockIds, userId, OperateStockBussinessType.BATCH_OCCUPY_STOCK.key);
        }

        if (operateType == OperateStockBussinessType.BATCH_OCCUPY_STOCK.key) {
            List<LockRecord> historyLocks = null;
            if (!CommonUtils.checkObjectIsNull(lockRecordHistory)) {
                List<Long> lockRecordIds = new ArrayList<Long>();
                for (LockRecord lockRecord : lockRecordHistory) {
                    lockRecordIds.add(lockRecord.getId());
                }

                historyLocks = lockRecordWriteMapper.queryLockRecordByIdsForUpdate(lockRecordIds);
            }

            List<LockRecord> updHistoryLockRecordList = null;
            if (!CommonUtils.checkObjectIsNull(stockList)) {
                //组织生成锁库存记录
                Object[] lockObjects = organizeLockRecord(stockList, historyLocks, operateStockMap, userId);
                lockRecordList = (List<LockRecord>) lockObjects[0];
                updHistoryLockRecordList = (List<LockRecord>) lockObjects[1];

                //判断是否可以锁库存
                canLockStock(stockList, operateStockMap);

                //计算锁库存数量
                lockStockNum(stockList, operateStockMap);
            }

            //获取操作未生成的库存数据
            List<Stock> addStockList = batchCreateStock(stockBatchLockModel);
            if (!CommonUtils.checkObjectIsNull(addStockList)) {
                for (Stock stock : addStockList) {
                    try {
                        stockWriteMapper.insertStock(stock);
                    } catch (DuplicateKeyException dupKeyE) {
                        throw new AddRepeatStockException(StockExceptionCode.STOCK_REPEAT_ADD_ERR_MSG);
                    }
                }
                //生成用户占库存记录
                if (CommonUtils.checkObjectIsNull(lockRecordList)) {
                    lockRecordList = new ArrayList<LockRecord>();
                }
                batchCreateLockRecord(lockRecordList, addStockList, userId);
            }

            //更新数据库记录
            if (!CommonUtils.checkObjectIsNull(updHistoryLockRecordList)) {
                lockRecordWriteMapper.userUpdateBatchLock(updHistoryLockRecordList);
            }
            if (!CommonUtils.checkObjectIsNull(lockRecordList)) {
                lockRecordWriteMapper.userAddBatchLock(lockRecordList);
            }

            //批量修改占库存数
            if (!CommonUtils.checkObjectIsNull(stockList)) {
                stockWriteMapper.batchUpdateStockNum(stockList);
            }

        } else if (operateType == OperateStockBussinessType.BATCH_RELEASE_STOCK.key) {
            if (CommonUtils.checkObjectIsNull(lockRecordHistory)) {
                throw new NoExistBatchLockStockRecordException(StockExceptionCode.NOTEXIST_BATCH_LOCK_STOCK_ERR_MSG);
            }
            if (CommonUtils.checkObjectIsNull(stockList)) {
                throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
            }
            List<Long> lockRecordIds = new ArrayList<Long>();
            for (LockRecord lockRecord : lockRecordHistory) {
                lockRecordIds.add(lockRecord.getId());
            }
            lockRecordList = lockRecordWriteMapper.queryLockRecordByIdsForUpdate(lockRecordIds);

            //判断是否可以释放库存
            canReleaseStockRecord(lockRecordList, operateStockMap);
            canReleaseStock(stockList, operateStockMap);

            //计算释放库存数量
            releaseStockRecordNum(lockRecordList, operateStockMap);
            releaseStockNum(stockList, operateStockMap);

            List<Long> delLockRecord = new ArrayList<Long>();
            Iterator<LockRecord> itera = lockRecordList.iterator();
            while (itera.hasNext()) {
                LockRecord lockRecord = itera.next();
                if (lockRecord.getLockNum() == 0) {
                    delLockRecord.add(lockRecord.getId());
                    itera.remove();
                }
            }

            //更新数据库记录
            if (delLockRecord.size() > 0) {
                lockRecordWriteMapper.delBatchLockByIds(delLockRecord);
            }
            if (lockRecordList.size() > 0) {
                lockRecordWriteMapper.userUpdateBatchLock(lockRecordList);
            }
            stockWriteMapper.batchUpdateStockNum(stockList);
        }

    }

    /**
     * 初始化占库存记录集合
     * @param lockRecordList
     * @param addStockList
     * @param userId
     */
    private void batchCreateLockRecord(List<LockRecord> lockRecordList, List<Stock> addStockList, Long userId) {
        for (Stock stock : addStockList) {
            LockRecord lockRecord = new LockRecord();
            lockRecord.setBizType(OperateStockBussinessType.BATCH_OCCUPY_STOCK.key);
            lockRecord.setOperatorId(userId);
            lockRecord.setStockId(stock.getId());
            lockRecord.setLockNum(stock.getUsedNum());
            lockRecordList.add(lockRecord);
        }
    }

    /**
     * 生成未来占库存数据
     * @param stockBatchLockModel
     * @return List<Stock>
     */
    private List<Stock> batchCreateStock(StockBatchLockModel stockBatchLockModel) {
        Map<String, Integer> stockRuleDateMap = stockBatchLockModel.getOperateNotExistsStockMap();
        if (!CommonUtils.checkObjectIsNull(stockRuleDateMap)) {
            List<Stock> stockList = new ArrayList<Stock>();
            StockRule stockRule = null;
            Iterator<Entry<String, Integer>> itera = stockRuleDateMap.entrySet().iterator();
            while (itera.hasNext()) {
                Entry<String, Integer> entry = itera.next();
                String key = entry.getKey();
                Integer lockNum = entry.getValue();

                String[] ruleDate = key.split(",");
                Long stockRuleId = Long.valueOf(ruleDate[0]);
                Integer stockTime = Integer.parseInt(CommonUtils.checkStockTime(ruleDate[1]));

                if (CommonUtils.checkObjectIsNull(stockRule)) {
                    stockRule = stockRuleReadMapper.selectRuleById(stockRuleId);
                }

                //初始化库存数据
                stockList.add(initStock(stockTime, stockRule, lockNum));
            }
            return stockList;
        }
        return null;
    }

    /**
     * 初始化库存对象
     * @param stockTime
     * @param stockRule
     * @param lockNum
     * @return Stock
     */
    private Stock initStock(Integer stockTime, StockRule stockRule, Integer lockNum) {
        int totalNum = stockRule.getUpperLimit();
        if (lockNum > totalNum) {
            throw new ShortageStockException(StockExceptionCode.STOCK_SHORTAGE_ERR_MSG);
        }
        Stock stock = new Stock();
        stock.setRuleId(stockRule.getId());
        stock.setStockTime(stockTime);
        stock.setState(StockStateEnum.AVAILABLE.getState());
        stock.setTotalNum(totalNum);
        stock.setRemainNum(totalNum - lockNum);
        stock.setUsedNum(lockNum);
        return stock;
    }

    /**
     * 锁库存操作.
     * @param stockList
     * @param operateStockMap
     */
    private void lockStockNum(List<Stock> stockList, Map<Long, Integer> operateStockMap) {
        for (Stock stock : stockList) {
            int used = stock.getUsedNum() == null ? 0 : stock.getUsedNum();
            int occupyNum = operateStockMap.get(stock.getId());
            stock.setUsedNum(used + occupyNum);
            stock.setRemainNum(stock.getRemainNum() - occupyNum);
        }

    }

    /**
     * 释放库存操作.
     * @param stockList
     * @param operateStockMap
     */
    private void releaseStockNum(List<Stock> stockList, Map<Long, Integer> operateStockMap) {
        for (Stock stock : stockList) {
            int releaseNum = operateStockMap.get(stock.getId());
            stock.setUsedNum(stock.getUsedNum() - releaseNum);
            stock.setRemainNum(stock.getRemainNum() + releaseNum);
        }
    }

    /**
     * 释放库存记录操作
     * @param lockRecordList
     * @param operateStockMap
     */
    private void releaseStockRecordNum(List<LockRecord> lockRecordList, Map<Long, Integer> operateStockMap) {
        for (LockRecord lockRecord : lockRecordList) {
            int releaseNum = operateStockMap.get(lockRecord.getStockId());
            lockRecord.setLockNum(lockRecord.getLockNum() - releaseNum);
        }
    }

    /**
     * 获取库存id集合
     * @param stockBatchLockModel
     * @return 库存ID集合
     */
    private List<Long> getStockIds(StockBatchLockModel stockBatchLockModel) {
        if (!CommonUtils.checkObjectIsNull(stockBatchLockModel.getOperateStockMap())) {
            List<Long> stockIds = new ArrayList<Long>();
            Iterator<Long> itera = stockBatchLockModel.getOperateStockMap().keySet().iterator();
            while (itera.hasNext()) {
                Long stockId = itera.next();
                if (CommonUtils.checkLongIsNull(stockId, true)) {
                    throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
                }
                stockIds.add(stockId);
            }
            return stockIds;
        }
        return null;
    }

    /**
     * 组织生成锁库存记录
     * @param stockList
     * @param historyStockMap
     * @param operateStockMap
     * @param userId
     * @return Object[]
     */
    private Object[] organizeLockRecord(List<Stock> stockList, List<LockRecord> historyLocks, Map<Long, Integer> operateStockMap, Long userId) {
        List<LockRecord> addLockRecordList = new ArrayList<LockRecord>();
        List<LockRecord> updHistoryLockRecordList = new ArrayList<LockRecord>();
        Long stockId = 0l;
        int historyLockNum = 0;
        LockRecord historyLockRecord = null;
        for (Stock stock : stockList) {
            stockId = stock.getId();
            historyLockNum = 0;
            historyLockRecord = null;

            if (!Check.NuNCollections(historyLocks)) {
                for (LockRecord lockRecord : historyLocks) {
                    if (stockId == lockRecord.getStockId().longValue()) {
                        historyLockRecord = lockRecord;
                        historyLockNum = lockRecord.getLockNum();
                        break;
                    }
                }
            }

            int lockNum = operateStockMap.get(stockId);

            if (historyLockNum > 0) {
                historyLockRecord.setLockNum(lockNum + historyLockNum);
                updHistoryLockRecordList.add(historyLockRecord);
            } else {
                LockRecord lockRecord = new LockRecord();
                lockRecord.setBizType(OperateStockBussinessType.BATCH_OCCUPY_STOCK.key);
                lockRecord.setOperatorId(userId);
                lockRecord.setStockId(stockId);
                lockRecord.setLockNum(lockNum);
                addLockRecordList.add(lockRecord);
            }
        }

        return new Object[] { addLockRecordList, updHistoryLockRecordList };
    }

    /**
     * 判断库存可被占用.
     * @param stockList
     * @param opeStockMap
     */
    private void canLockStock(List<Stock> stockList, Map<Long, Integer> opeStockMap) {
        for (Stock stock : stockList) {
            if (stock == null) {
                throw new NotFoundStockException(StockExceptionCode.NOT_FOUND_STOCK_ERR_MSG);
            }

            int state = stock.getState();
            if (state != StockStateEnum.AVAILABLE.getState()) {
                throw new StockStateException(StockExceptionCode.STOCK_STATE_ERR_MSG);
            }

            int remain = stock.getRemainNum();
            Integer lockNum = opeStockMap.get(stock.getId());
            if (CommonUtils.checkIntegerIsNull(lockNum, true)) {
                throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
            }

            if (remain < lockNum) {
                throw new ShortageStockException(StockExceptionCode.STOCK_SHORTAGE_ERR_MSG);
            }

            int total = stock.getTotalNum();
            int used = stock.getUsedNum() == null ? 0 : stock.getUsedNum();
            if ((used + lockNum) > total) {
                throw new LimitedException(StockExceptionCode.MAX_STOCK_NUM_ERR_MSG);
            }

            Integer stockTime = stock.getStockTime();
            if (!CommonUtils.checkIntegerIsNull(stockTime, true) && !CommonUtils.checkStockTimeIsAvai(stockTime)) {
                throw new NonOpeHistoryStockException(StockExceptionCode.NON_OPERATE_HISTORY_STOCK_ERR_MSG);
            }
        }
    }

    /**
     * 判断库存可被释放.
     * @param stockList
     */
    private void canReleaseStock(List<Stock> stockList, Map<Long, Integer> opeStockMap) {
        for (Stock stock : stockList) {
            if (stock == null) {
                throw new NotFoundStockException(StockExceptionCode.NOT_FOUND_STOCK_ERR_MSG);
            }

            int state = stock.getState();
            if (state != StockStateEnum.AVAILABLE.getState()) {
                throw new StockStateException(StockExceptionCode.STOCK_STATE_ERR_MSG);
            }

            Integer unlockNum = opeStockMap.get(stock.getId());
            if (CommonUtils.checkIntegerIsNull(unlockNum, true)) {
                throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
            }

            Integer usedNum = stock.getUsedNum() == null ? 0 : stock.getUsedNum();
            Integer remainNum = stock.getRemainNum() == null ? 0 : stock.getRemainNum();
            if (usedNum - unlockNum < 0 || (remainNum + unlockNum) > stock.getTotalNum()) {
                throw new ReleaseNumException(StockExceptionCode.RELEASE_STOCK_NUM_ERR_MSG);
            }

            Integer stockTime = stock.getStockTime();
            if (!CommonUtils.checkIntegerIsNull(stockTime, true) && !CommonUtils.checkStockTimeIsAvai(stockTime)) {
                throw new NonOpeHistoryStockException(StockExceptionCode.NON_OPERATE_HISTORY_STOCK_ERR_MSG);
            }
        }
    }

    /**
     * 判断库存记录是否可被释放
     * @param recordList
     * @param opeStockMap
     */
    private void canReleaseStockRecord(List<LockRecord> recordList, Map<Long, Integer> opeStockMap) {
        for (LockRecord record : recordList) {
            //            if (record == null) {
            //                throw new NotFoundStockRecordException(StockExceptionCode.NOT_FOUND_STOCK_RECORD_ERR_MSG);
            //            }

            Integer releaseNum = opeStockMap.get(record.getStockId());
            if (CommonUtils.checkIntegerIsNull(releaseNum, true)) {
                throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
            }

            if (record.getLockNum() < releaseNum) {
                throw new ReleaseNumException(StockExceptionCode.RELEASE_STOCK_NUM_ERR_MSG);
            }
        }
    }

}
