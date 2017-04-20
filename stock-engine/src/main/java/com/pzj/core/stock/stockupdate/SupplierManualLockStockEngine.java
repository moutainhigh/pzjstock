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
import com.pzj.core.stock.exception.stock.LockedNumException;
import com.pzj.core.stock.exception.stock.LockedNumSmallerExistException;
import com.pzj.core.stock.exception.stock.NonOpeHistoryStockException;
import com.pzj.core.stock.exception.stock.NotFoundStockException;
import com.pzj.core.stock.exception.stock.StockStateException;
import com.pzj.core.stock.model.SupplierManualLockModel;
import com.pzj.core.stock.read.LockRecordReadMapper;
import com.pzj.core.stock.write.LockRecordWriteMapper;
import com.pzj.core.stock.write.StockWriteMapper;

/**
 * 
 * @author Administrator
 * @version $Id: SupplierManualLockStockEngine.java, v 0.1 2016年10月17日 下午5:39:57 Administrator Exp $
 */
@Component("supplierManualLockStockEngine")
public class SupplierManualLockStockEngine {
    @Resource(name = "stockWriteMapper")
    private StockWriteMapper      stockWriteMapper;
    @Resource(name = "lockRecordWriteMapper")
    private LockRecordWriteMapper lockRecordWriteMapper;
    @Resource(name = "lockRecordReadMapper")
    private LockRecordReadMapper  lockRecordReadMapper;

    @Transactional(value = "stock.transactionManager", timeout = 2)
    public void supplierManualLockStock(SupplierManualLockModel model) {
        //获取并锁定库存对象
        Stock stock = stockWriteMapper.queryStockByIdForUpdate(model.getStockId());
        LockRecord lockRecord = getLockRecord(model);

        //获取当前锁定库存数量
        Integer curLockNum = getCurLockNum(lockRecord);

        //检查库存是否可以锁定
        checkStock(stock, curLockNum);

        //锁定库存
        lockStock(stock, curLockNum);

        //更新数据库
        stockWriteMapper.updateStockNum(stock.getId(), stock.getUsedNum(), stock.getRemainNum());
        if (CommonUtils.checkLongIsNull(lockRecord.getId(), true)) {
            lockRecordWriteMapper.insert(lockRecord);
        } else {
            lockRecordWriteMapper.updateLockRecordNum(lockRecord.getId(), lockRecord.getLockNum());
        }

    }

    /**
     * 检查库存释放可以手动锁定
     * @param stock
     * @param lockNum
     */
    private void checkStock(Stock stock, Integer curLockNum) {
        //未找到锁定库存对象
        if (CommonUtils.checkObjectIsNull(stock)) {
            throw new NotFoundStockException(StockExceptionCode.NOT_FOUND_STOCK_ERR_MSG);
        }

        //库存状态不可用
        Integer state = stock.getState();
        if (CommonUtils.checkObjectIsNull(state) || state != StockStateEnum.AVAILABLE.getState()) {
            throw new StockStateException(StockExceptionCode.STOCK_STATE_ERR_MSG);
        }

        //检查库存是否足够
        int usedNum = stock.getUsedNum() == null ? 0 : stock.getUsedNum();
        if (curLockNum + usedNum > stock.getTotalNum()) {
            throw new LockedNumException(StockExceptionCode.LOCKNUM_OUT_OF_STOCK_ERR_MSG);
        }

        Integer stockTime = stock.getStockTime();
        if (!CommonUtils.checkIntegerIsNull(stockTime, true) && !CommonUtils.checkStockTimeIsAvai(stockTime)) {
            throw new NonOpeHistoryStockException(StockExceptionCode.NON_OPERATE_HISTORY_STOCK_ERR_MSG);
        }
    }

    /**
     * 锁定库存
     * @param stock
     * @param lockNum
     */
    private void lockStock(Stock stock, Integer curLockNum) {
        int usedNum = stock.getUsedNum() == null ? 0 : stock.getUsedNum();
        int remainNum = stock.getRemainNum();
        usedNum += curLockNum;
        remainNum -= curLockNum;

        stock.setUsedNum(usedNum);
        stock.setRemainNum(remainNum);
    }

    /**
     * 初始化锁库存记录
     * @param model
     * @return
     */
    private LockRecord initLockRecord(SupplierManualLockModel model) {
        LockRecord lockRecord = new LockRecord();
        lockRecord.setBizType(OperateStockBussinessType.SUPPLIER_MANUAL_LOCK_STOCK.key);
        lockRecord.setLockNum(model.getLockNum());
        lockRecord.setOperatorId(model.getSupplierId());
        lockRecord.setStockId(model.getStockId());
        lockRecord.setTransactionId("0");
        lockRecord.setProductId(0L);
        return lockRecord;
    }

    /**
     * 获取锁库存记录
     * @param model
     * @return
     */
    private LockRecord getLockRecord(SupplierManualLockModel model) {
        List<Long> stockIds = new ArrayList<Long>();
        stockIds.add(model.getStockId());
        List<LockRecord> lockRecordList = lockRecordReadMapper.queryLockRecordByUser(stockIds, model.getSupplierId(),
            OperateStockBussinessType.SUPPLIER_MANUAL_LOCK_STOCK.key);
        if (!CommonUtils.checkObjectIsNull(lockRecordList)) {
            LockRecord lockRecord = lockRecordWriteMapper.queryLockRecordByIdForUpdate(lockRecordList.get(0).getId());
            int lockHistory = lockRecord.getLockNum() == null ? 0 : lockRecord.getLockNum();
            if (lockHistory >= model.getLockNum()) {
                throw new LockedNumSmallerExistException(StockExceptionCode.LOCKNUM_SMALLER_EXISTLOCK_STOCK_ERR_MSG);
            }
            lockRecord.setHistoryLockNum(lockHistory);
            lockRecord.setLockNum(model.getLockNum());
            return lockRecord;
        } else {
            return initLockRecord(model);
        }
    }

    /**
     * 获取当前锁定库存数
     * @param lockRecord
     * @return Integer
     */
    private Integer getCurLockNum(LockRecord lockRecord) {
        int lockNum = lockRecord.getLockNum();
        if (!CommonUtils.checkIntegerIsNull(lockRecord.getHistoryLockNum(), true)) {
            int lockHistory = lockRecord.getHistoryLockNum();
            return lockNum - lockHistory;
        } else {
            return lockNum;
        }
    }
}
