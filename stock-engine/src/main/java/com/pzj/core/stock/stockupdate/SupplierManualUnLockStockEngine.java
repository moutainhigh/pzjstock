/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.stockupdate;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.entity.LockRecord;
import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.enums.OperateStockBussinessType;
import com.pzj.core.stock.enums.StockStateEnum;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.exception.stock.NonOpeHistoryStockException;
import com.pzj.core.stock.exception.stock.NotFoundStockException;
import com.pzj.core.stock.exception.stock.NotFoundStockRecordException;
import com.pzj.core.stock.exception.stock.ReleaseNumException;
import com.pzj.core.stock.exception.stock.StockStateException;
import com.pzj.core.stock.model.SupplierManualUnlockModel;
import com.pzj.core.stock.read.LockRecordReadMapper;
import com.pzj.core.stock.write.LockRecordWriteMapper;
import com.pzj.core.stock.write.StockWriteMapper;

/**
 * 
 * @author Administrator
 * @version $Id: SupplierManualUnLockStockEngine.java, v 0.1 2016年10月17日 下午5:41:56 Administrator Exp $
 */
@Component("supplierManualUnLockStockEngine")
public class SupplierManualUnLockStockEngine {
    @Resource(name = "stockWriteMapper")
    private StockWriteMapper      stockWriteMapper;
    @Resource(name = "lockRecordWriteMapper")
    private LockRecordWriteMapper lockRecordWriteMapper;
    @Resource(name = "lockRecordReadMapper")
    private LockRecordReadMapper  lockRecordReadMapper;

    @Transactional(value = "stock.transactionManager", timeout = 2)
    public void supplierManualUnlockStock(SupplierManualUnlockModel model) {
        //获取并锁定库存对象
        Stock stock = stockWriteMapper.queryStockByIdForUpdate(model.getStockId());

        //获取并锁定锁库存记录对象
        LockRecord lockRecord = getLockRecord(model);

        Integer releaseNum = model.getUnlockNum();
        //检查库存和锁库存记录是否可用
        checkStock(stock);
        checkLockRecord(lockRecord, releaseNum);

        //计算库存数量
        computeStock(stock, releaseNum);
        computeLockRecord(lockRecord, releaseNum);

        //更新锁库存数据库记录
        stockWriteMapper.updateStockNum(stock.getId(), stock.getUsedNum(), stock.getRemainNum());
        lockRecordWriteMapper.updateLockRecordNum(lockRecord.getId(), lockRecord.getLockNum());
    }

    /**
     * 检查库存是否可用
     * @param stock
     */
    private void checkStock(Stock stock) {
        if (CommonUtils.checkObjectIsNull(stock)) {
            throw new NotFoundStockException(StockExceptionCode.NOT_FOUND_STOCK_ERR_MSG);
        }

        Integer state = stock.getState();
        if (CommonUtils.checkIntegerIsNull(state, true) || state != StockStateEnum.AVAILABLE.getState()) {
            throw new StockStateException(StockExceptionCode.STOCK_STATE_ERR_MSG);
        }

        Integer stockTime = stock.getStockTime();
        if (!CommonUtils.checkIntegerIsNull(stockTime, true) && !CommonUtils.checkStockTimeIsAvai(stockTime)) {
            throw new NonOpeHistoryStockException(StockExceptionCode.NON_OPERATE_HISTORY_STOCK_ERR_MSG);
        }
    }

    /**
     * 检查锁库存记录
     * @param lockRecord
     * @param model
     */
    private void checkLockRecord(LockRecord lockRecord, Integer releaseNum) {
        if (lockRecord.getLockNum().intValue() < releaseNum.intValue()) {
            throw new ReleaseNumException(StockExceptionCode.RELEASE_STOCK_NUM_ERR_MSG);
        }
    }

    /**
     * 获取锁库存记录对象
     * @param model
     * @return LockRecord
     */
    private LockRecord getLockRecord(SupplierManualUnlockModel model) {
        List<Long> stockIds = new ArrayList<Long>();
        stockIds.add(model.getStockId());
        List<LockRecord> lockRecordList = lockRecordReadMapper.queryLockRecordByUser(stockIds, model.getSupplierId(),
            OperateStockBussinessType.SUPPLIER_MANUAL_LOCK_STOCK.key);
        if (!CommonUtils.checkObjectIsNull(lockRecordList)) {
            Long lockRecordId = lockRecordList.get(0).getId();
            LockRecord lockRecord = lockRecordWriteMapper.queryLockRecordByIdForUpdate(lockRecordId);
            Integer historyLockNum = lockRecord.getLockNum();
            if (historyLockNum < model.getUnlockNum()) {
                throw new ReleaseNumException(StockExceptionCode.RELEASE_STOCK_NUM_ERR_MSG);
            }
            return lockRecord;
        } else {
            throw new NotFoundStockRecordException(StockExceptionCode.NOT_FOUND_STOCK_RECORD_ERR_MSG);
        }
    }

    /**
     * 计算库存数量
     * @param stock
     * @param releaseNum
     */
    private void computeStock(Stock stock, Integer releaseNum) {
        int usedNum = stock.getUsedNum();
        int remainNum = stock.getRemainNum();
        remainNum += releaseNum;
        usedNum -= releaseNum;
        stock.setRemainNum(remainNum);
        stock.setUsedNum(usedNum);
    }

    /**
     * 计算锁库存记录数量
     * @param lockRecord
     * @param releaseNum
     */
    private void computeLockRecord(LockRecord lockRecord, Integer releaseNum) {
        int hisLockNum = lockRecord.getLockNum();
        lockRecord.setLockNum(hisLockNum - releaseNum);
    }
}
