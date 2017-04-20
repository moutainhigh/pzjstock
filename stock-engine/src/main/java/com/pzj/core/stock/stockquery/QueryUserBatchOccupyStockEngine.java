/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.stockquery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pzj.core.stock.entity.LockRecord;
import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.enums.OperateStockBussinessType;
import com.pzj.core.stock.model.StockModel;
import com.pzj.core.stock.model.UserOccupyStockModel;
import com.pzj.core.stock.read.LockRecordReadMapper;
import com.pzj.core.stock.read.StockReadMapper;
import com.pzj.framework.toolkit.Check;

/**
 * 
 * @author Administrator
 * @version $Id: QueryUserBatchOccupyStockEngine.java, v 0.1 2016年8月26日 上午11:36:53 Administrator Exp $
 */
@Component("queryUserBatchOccupyStockEngine")
public class QueryUserBatchOccupyStockEngine {

    @Resource(name = "stockReadMapper")
    private StockReadMapper      stockReadMapper;
    @Resource(name = "lockRecordReadMapper")
    private LockRecordReadMapper lockRecordReadMapper;

    public void queryUserOneRuleOccupyStock(UserOccupyStockModel userOccupyStockModel) {
        //查找可用的库存列表
        List<Stock> stockList = stockReadMapper.queryStockByRuleId(userOccupyStockModel.getStockRuleId());
        if (Check.NuNCollections(stockList)) {
            return; //找不到库存规则对应库存
        }

        //查找用户批量占用的库存集合
        Map<Long, Stock> stockMap = new HashMap<Long, Stock>();
        List<Long> stockIds = new ArrayList<Long>();
        for (Stock stock : stockList) {
            stockIds.add(stock.getId());
            stockMap.put(stock.getId(), stock);
        }
        int bizType = OperateStockBussinessType.BATCH_OCCUPY_STOCK.key;
        List<LockRecord> lockRecordList = lockRecordReadMapper.queryLockRecordByUser(stockIds, userOccupyStockModel.getUserId(), bizType);
        if (Check.NuNCollections(lockRecordList)) {
            return; //找不到库存规则对应库存
        }

        //设置用户批量占用库存集合
        userOccupyStockModel.setTotalStockNum(stockList.get(0).getTotalNum());
        List<StockModel> stockModelList = new ArrayList<StockModel>();
        StockModel stockModel = null;
        Stock stock = null;
        for (LockRecord lockRecord : lockRecordList) {
            stock = stockMap.get(lockRecord.getStockId());
            stockModel = new StockModel();
            stockModel.setId(lockRecord.getStockId());
            stockModel.setRuleId(userOccupyStockModel.getStockRuleId());
            stockModel.setStockTime(stock.getStockTime());
            stockModel.setTotalNum(stock.getTotalNum());
            stockModel.setRemainNum(stock.getRemainNum());
            stockModel.setUsedNum(lockRecord.getLockNum());
            stockModelList.add(stockModel);
        }
        userOccupyStockModel.setStockModelList(stockModelList);
    }
}
