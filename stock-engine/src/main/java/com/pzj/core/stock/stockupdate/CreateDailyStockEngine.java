/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.stockupdate;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.entity.StockRule;
import com.pzj.core.stock.enums.StockStateEnum;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.read.StockReadMapper;
import com.pzj.core.stock.write.StockWriteMapper;
import com.pzj.framework.context.ServiceContext;

/**
 * 创建单一库存记录操作
 * 
 * @author dongchunfu
 * @version $Id: CreateSingleStockEngine.java, v 0.1 2016年8月10日 下午2:34:41 dongchunfu Exp $
 */
@Component(value = "createDailyStockEngine")
public class CreateDailyStockEngine {

    private static final Logger logger = LoggerFactory.getLogger(CreateDailyStockEngine.class);

    @Autowired
    private StockWriteMapper    stockWriteMapper;
    @Autowired
    private StockReadMapper     stockReadMapper;

    /**
     * 创建日库存记录方法
     * 
     * @param rule              库存规则
     * @param usableStockTimes  依据产品有效期信息计算得到的 并集
     * @param defaultStockTimes 规定 生成3个月后减1天的库存记录
     * @param context           服务上下文
     * @return                  创建数量
     */
    public Integer createDailyStock(StockRule rule, TreeSet<Integer> usableStockTimes, ArrayList<Integer> defaultStockTimes, ServiceContext context) {

        List<Stock> list = new ArrayList<Stock>();

        // 补偿，避免重复创建 库存记录 根据库存规则ID过滤
        Stock stockParam = new Stock();
        stockParam.setRuleId(rule.getId());
        ArrayList<Stock> stockDB = stockReadMapper.selectStockListByParam(stockParam);
        List<Integer> stockTimesDB = getAllStockTimes(stockDB);

        //依据库存规则生成库存对应实体
        Stock stock = convertParam(rule);

        //设置库存时间
        for (Integer stockTime : defaultStockTimes) {

            //判断当前库存记录中是否已存在库存记录
            if (stockTimesDB != null) {
                if (stockTimesDB.contains(stockTime)) {
                    continue;
                }
            }

            // 判断产品有效期是否包含当前库存时间
            if (usableStockTimes.contains(stockTime)) {

                Stock clone = null;
                try {
                    clone = stock.clone();
                } catch (CloneNotSupportedException e) {
                    logger.error("stock clone exception", e);
                    throw new StockException(StockExceptionCode.STOCK_ERR_MSG);
                }
                clone.setStockTime(stockTime);
                list.add(clone);
            }
        }

        if (list.size() > 0) {
            stockWriteMapper.insertStockBatch(list);
            if (logger.isDebugEnabled()) {
                logger.debug("create new stocks:{}.", list);
            }
        }

        return list.size();
    }

    /**
     * 创建指定时间的库存
     * @param rule
     * @param stockTimes
     * @return 创建库存数量
     */
    public Integer createDailyStock(StockRule rule, ArrayList<Integer> stockTimes) {
        List<Stock> stockList = new ArrayList<Stock>();
        //设置库存时间
        for (Integer stockTime : stockTimes) {
            Stock stock = convertParam(rule);
            stock.setStockTime(stockTime);
            stockList.add(stock);
        }

        if (stockList.size() > 0) {
            stockWriteMapper.insertStockBatch(stockList);
            if (logger.isDebugEnabled()) {
                logger.debug("create new stocks:{}.", stockList);
            }
        }

        return stockList.size();
    }

    //参数转换
    private Stock convertParam(StockRule rule) {
        Stock stock = new Stock();
        stock.setRuleId(rule.getId());
        stock.setState(StockStateEnum.AVAILABLE.getState());
        stock.setTotalNum(rule.getUpperLimit());
        stock.setUsedNum(0);
        stock.setRemainNum(rule.getUpperLimit());
        return stock;
    }

    //获取所有库存规则的库存时间集合
    private List<Integer> getAllStockTimes(List<Stock> stocks) {
        if (null == stocks || stocks.size() == 0) {
            return null;
        }
        List<Integer> stockTimes = new ArrayList<Integer>();
        for (Stock stock : stocks) {
            stockTimes.add(stock.getStockTime());
        }
        return stockTimes;
    }

}
