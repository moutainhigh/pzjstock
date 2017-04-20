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
import com.pzj.core.stock.enums.OperateStockBussinessType;
import com.pzj.core.stock.enums.StockStateEnum;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.exception.stock.LimitedException;
import com.pzj.core.stock.exception.stock.NotFoundStockException;
import com.pzj.core.stock.exception.stock.ShortageStockException;
import com.pzj.core.stock.exception.stock.StockStateException;
import com.pzj.core.stock.model.OccupyStockRequestModel;
import com.pzj.core.stock.read.LockRecordReadMapper;
import com.pzj.core.stock.read.StockReadMapper;
import com.pzj.core.stock.write.LockRecordWriteMapper;
import com.pzj.core.stock.write.StockWriteMapper;

/**
 * 占库存执行引擎.
 * @author YRJ
 *
 */
@Component(value = "occupyStockEngine")
public class OccupyStockEngine {

    @Resource(name = "stockReadMapper")
    private StockReadMapper       stockReadMapper;

    @Resource(name = "stockWriteMapper")
    private StockWriteMapper      stockWriteMapper;

    @Resource(name = "lockRecordWriteMapper")
    private LockRecordWriteMapper lockRecordWriteMapper;

    @Resource(name = "lockRecordReadMapper")
    private LockRecordReadMapper  lockRecordReadMapper;

    @Transactional(value = "stock.transactionManager", timeout = 2)
    public void occupyStock(List<OccupyStockRequestModel> orderStockModelList) {
        //1. 根据库存ID获取库存的基本信息.
        List<Stock> stockList = getStockList(orderStockModelList);

        Map<Long, Integer> stockOccupyMap = getOccupyStockMap(orderStockModelList);
        //2. 判断是否可以占库存
        canOccupyStock(stockList, stockOccupyMap);

        //3. 判断库存是否已被锁定.
        if (!checkLock(orderStockModelList)) {
            return;//容错机制, 若相同交易流水, 产品已经锁定, 则认为成功.
        }

        //4. 计算库存数量.
        computeStockNum(stockList, stockOccupyMap);

        //5. 更新库存记录.
        List<LockRecord> lockRecordList = generateStockLockRecord(orderStockModelList);
        lockRecordWriteMapper.batchAddStockLockRecord(lockRecordList);
        stockWriteMapper.batchUpdateStockNum(stockList);

    }

    /**
     * 检查是否存在锁库存记录
     * @param orderStockModelList
     * @return Boolean
     */
    private Boolean checkLock(List<OccupyStockRequestModel> orderStockModelList) {
        int totalCount = 0;
        for (OccupyStockRequestModel occupyStockModel : orderStockModelList) {
            totalCount += lockRecordReadMapper.existLocked(occupyStockModel.getTransactionId(), occupyStockModel.getProductId());
            if (totalCount > 0) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    /**
     * 获取库存列表集合
     * @param orderStockModelList
     * @return List<Stock>
     */
    private List<Stock> getStockList(List<OccupyStockRequestModel> orderStockModelList) {
        List<Long> stockIds = new ArrayList<Long>();
        for (OccupyStockRequestModel orderStockModel : orderStockModelList) {
            stockIds.add(orderStockModel.getStockId());
        }
        return stockWriteMapper.queryStockByIdsForUpdate(stockIds);
    }

    /**
     * 获取占库存map对象
     * @param orderStockModelList
     * @return Map<Long, Integer>
     */
    private Map<Long, Integer> getOccupyStockMap(List<OccupyStockRequestModel> orderStockModelList) {
        Map<Long, Integer> stockOccupyMap = new HashMap<Long, Integer>();
        Long stockId = null;
        Integer hisOccupyNum = null, curOccupyNum = null;
        for (OccupyStockRequestModel occupyStockModel : orderStockModelList) {
            stockId = occupyStockModel.getStockId();
            curOccupyNum = occupyStockModel.getStockNum();
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
     * 判断库存可被占用.
     * @param stockList
     * @param orderStockModelList
     */
    private void canOccupyStock(List<Stock> stockList, Map<Long, Integer> stockOccupyMap) {
        Integer occupyNum = null;
        for (Stock stock : stockList) {
            if (stock == null) {
                throw new NotFoundStockException(StockExceptionCode.NOT_FOUND_STOCK_ERR_MSG);
            }

            int state = stock.getState();
            if (state != StockStateEnum.AVAILABLE.getState()) {
                throw new StockStateException(StockExceptionCode.STOCK_STATE_ERR_MSG);
            }

            occupyNum = stockOccupyMap.get(stock.getId());

            int remain = stock.getRemainNum();
            if (remain < occupyNum) {
                throw new ShortageStockException(StockExceptionCode.STOCK_SHORTAGE_ERR_MSG);
            }

            int total = stock.getTotalNum();
            int used = stock.getUsedNum() == null ? 0 : stock.getUsedNum();
            if ((used + occupyNum) > total) {
                throw new LimitedException(StockExceptionCode.MAX_STOCK_NUM_ERR_MSG);
            }
        }
    }

    /**
     * 生成锁库存记录.
     * @param orderStockModelList
     * @return
     */
    private List<LockRecord> generateStockLockRecord(List<OccupyStockRequestModel> orderStockModelList) {
        List<LockRecord> lockRecordList = new ArrayList<LockRecord>();
        for (OccupyStockRequestModel occupyStockModel : orderStockModelList) {
            LockRecord lockRecord = new LockRecord();
            lockRecord.setStockId(occupyStockModel.getStockId());
            lockRecord.setTransactionId(occupyStockModel.getTransactionId());
            lockRecord.setProductId(occupyStockModel.getProductId());
            lockRecord.setBizType(OperateStockBussinessType.ORDER_OCCUPY_STOCK.key);
            lockRecord.setLockNum(occupyStockModel.getStockNum());
            Long userId = occupyStockModel.getUserId();
            if (CommonUtils.checkLongIsNull(occupyStockModel.getUserId(), true)) {
                userId = 0L;
            }
            lockRecord.setOperatorId(userId);
            lockRecordList.add(lockRecord);
        }

        return lockRecordList;
    }

    /**
     * 计算库存数量.
     * @param stockList
     * @param stockOccupyMap
     */
    private void computeStockNum(List<Stock> stockList, Map<Long, Integer> stockOccupyMap) {
        for (Stock stock : stockList) {
            int used = stock.getUsedNum() == null ? 0 : stock.getUsedNum();
            int occupyNum = stockOccupyMap.get(stock.getId());
            stock.setUsedNum(used + occupyNum);
            stock.setRemainNum(stock.getRemainNum() - occupyNum);
        }
    }
}
