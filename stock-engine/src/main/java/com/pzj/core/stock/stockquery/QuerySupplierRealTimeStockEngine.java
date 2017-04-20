/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.stockquery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.entity.LockRecord;
import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.entity.StockRule;
import com.pzj.core.stock.enums.OperateStockBussinessType;
import com.pzj.core.stock.model.StockRealTimeModel;
import com.pzj.core.stock.model.StockRealTimeQueryModel;
import com.pzj.core.stock.read.LockRecordReadMapper;
import com.pzj.core.stock.read.StockReadMapper;
import com.pzj.core.stock.read.StockRuleReadMapper;

/**
 * 
 * @author Administrator
 * @version $Id: QuerySupplierRealTimeStockEngine.java, v 0.1 2016年10月17日 下午2:56:58 Administrator Exp $
 */
@Component("querySupplierRealTimeStockEngine")
public class QuerySupplierRealTimeStockEngine {
    @Resource(name = "stockRuleReadMapper")
    private StockRuleReadMapper  stockRuleReadMapper;

    @Resource(name = "stockReadMapper")
    private StockReadMapper      stockReadMapper;

    @Resource(name = "lockRecordReadMapper")
    private LockRecordReadMapper lockRecordReadMapper;

    public ArrayList<StockRealTimeModel> querySupplierRealTimeStock(StockRealTimeQueryModel model) {
        Integer stockTime = CommonUtils.getStockDateInteger(model.getStockDate());
        Long supplierId = model.getSupplierId();
        List<Long> stockRuleIds = model.getStockRuleIdList();

        //获取可用的库存规则列表
        List<StockRule> stockRuleList = stockRuleReadMapper.querySupplierStockRules(stockRuleIds, supplierId);
        List<Long> avaiStockRuleIds = new ArrayList<Long>();
        Map<Long, StockRule> stockRuleMap = new HashMap<Long, StockRule>();
        filterStockRule(stockRuleList, avaiStockRuleIds, stockRuleMap);
        if (CommonUtils.checkObjectIsNull(avaiStockRuleIds)) {
            return null;
        }

        //获取当前供应商关联库存集合
        List<Stock> stockList = stockReadMapper.querySupplierRealTimeStock(avaiStockRuleIds, stockTime);
        List<Long> stockIds = new ArrayList<Long>();
        if (CommonUtils.checkObjectIsNull(stockList)) {
            return null;
        } else {
            for (Stock stock : stockList) {
                stockIds.add(stock.getId());
            }
        }

        //获取当前供应商已经锁定的库存
        List<LockRecord> lockRecordList = lockRecordReadMapper.queryLockRecordByUser(stockIds, supplierId,
            OperateStockBussinessType.SUPPLIER_MANUAL_LOCK_STOCK.key);
        Map<Long, Integer> lockRecordMap = new HashMap<Long, Integer>();
        if (!CommonUtils.checkObjectIsNull(lockRecordList)) {
            for (LockRecord lockRecord : lockRecordList) {
                lockRecordMap.put(lockRecord.getStockId(), lockRecord.getLockNum());
            }
        }

        //组织生成实时库存对象集合
        ArrayList<StockRealTimeModel> stockRealModelList = new ArrayList<StockRealTimeModel>();
        StockRealTimeModel stockRealTimeModel = null;
        Long stockId = null;
        StockRule stockRule = null;
        Integer lockNum = null;
        for (Stock stock : stockList) {
            stockId = stock.getId();
            stockRule = stockRuleMap.get(stock.getRuleId());
            if (lockRecordMap.containsKey(stockId)) {
                lockNum = lockRecordMap.get(stockId);
            } else {
                lockNum = 0;
            }
            stockRealTimeModel = new StockRealTimeModel();
            stockRealTimeModel.setStockId(stock.getId());
            stockRealTimeModel.setStockDate(CommonUtils.integerConvertDate(stock.getStockTime()));
            stockRealTimeModel.setStockRuleId(stock.getRuleId());
            stockRealTimeModel.setStockRuleName(stockRule.getName());
            stockRealTimeModel.setStockRuleType(stockRule.getType());
            stockRealTimeModel.setTotalStockNum(stock.getTotalNum());
            stockRealTimeModel.setRemainStockNum(stock.getRemainNum());
            stockRealTimeModel.setUserLockStockNum(lockNum);
            stockRealModelList.add(stockRealTimeModel);
        }
        return stockRealModelList;
    }

    /**
     * 过滤库存规则集合
     * @param stockRuleList
     * @param avaiStockRuleIds
     */
    private void filterStockRule(List<StockRule> stockRuleList, List<Long> avaiStockRuleIds, Map<Long, StockRule> stockRuleMap) {
        if (!CommonUtils.checkObjectIsNull(stockRuleList)) {
            Iterator<StockRule> itera = stockRuleList.iterator();
            StockRule stockRule = null;
            while (itera.hasNext()) {
                stockRule = itera.next();
                if (CommonUtils.checkObjectIsNull(stockRule)) {
                    itera.remove();
                    continue;
                }
                avaiStockRuleIds.add(stockRule.getId());
                stockRuleMap.put(stockRule.getId(), stockRule);
            }
        }
    }
}
