/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.model.StockDateListQueryModel;
import com.pzj.core.stock.model.query.StockListParam;
import com.pzj.core.stock.model.result.SkuStockListResult;
import com.pzj.core.stock.model.result.SkuStockResult;
import com.pzj.core.stock.service.ISkuStockService;
import com.pzj.framework.context.Result;
import com.pzj.framework.converter.JSONConverter;

/**
 * 
 * @author Administrator
 * @version $Id: QueryRuleRelAllAvaiStockTime.java, v 0.1 2016年9月19日 上午11:47:57 Administrator Exp $
 */
@Component("queryRuleRelAllAvaiStockTime")
public class QueryRuleRelAllAvaiStockTime {
    private static final Logger logger = LoggerFactory.getLogger(QueryRuleRelAllAvaiStockTime.class);
    @Resource(name = "skuStockService")
    private ISkuStockService    skuStockService;

    public void queryRuleAllAvaiStockTime(StockDateListQueryModel model) {
        if (CommonUtils.checkObjectIsNull(model, model.getStockRuleId(), model.getStockTimeList())) {
            return;
        }
        List<Long> stockRuleIdList = new ArrayList<Long>();
        stockRuleIdList.add(model.getStockRuleId());
        StockListParam stockListParam = new StockListParam();
        stockListParam.setStockIdList(stockRuleIdList);
        Result<ArrayList<SkuStockListResult>> stockProResult = skuStockService.findStockSkuByStockRuleIds(stockListParam);
        if (logger.isDebugEnabled()) {
            logger.debug("---通过库存规则ID查询产品列表，stockProResult:{}", JSONConverter.toJson(stockProResult));
        }
        if (stockProResult.isOk()) {
            ArrayList<SkuStockListResult> skuStockList = stockProResult.getData();
            if (!CommonUtils.checkCollectionIsNull(skuStockList)) {
                ArrayList<SkuStockResult> skuList = skuStockList.get(0).getSkuList();
                if (CommonUtils.checkCollectionIsNull(skuList)) {
                    return;
                }

                Set<Integer> stockTimeSet = new LinkedHashSet<Integer>();
                ArrayList<Integer> curStockTimeOpeList = new ArrayList<Integer>();
                ArrayList<String> curStockTimeList = model.getStockTimeList();
                for (String timeStr : curStockTimeList) {
                    curStockTimeOpeList.add(CommonUtils.convertStringToInteger(timeStr));
                }

                for (SkuStockResult skuStock : skuList) {
                    Date minDate = skuStock.getStartDate();
                    Date maxDate = skuStock.getEndDate();
                    if (null != minDate && null != maxDate) {
                        //判断最小日期是否小于当前日期
                        if (!CommonUtils.checkStockMinTime(minDate)) {
                            minDate = new Date();
                        }
                        Integer[] threeMonthDay = CommonUtils.dateAddMonthDayArr(minDate, 3);
                        Integer proMaxDate = CommonUtils.getStockDateInteger(maxDate);
                        for (Integer threeDay : threeMonthDay) {
                            if (threeDay.intValue() <= proMaxDate.intValue() && curStockTimeOpeList.contains(threeDay)) {
                                stockTimeSet.add(threeDay);
                            }
                        }
                    }
                }
                model.setAvaiStockTimeSet(stockTimeSet);
            }
        } else {
            logger.error("---通过库存规则ID查询产品列表异常，stockProResult:{}", stockProResult);
            throw new StockException(StockExceptionCode.STOCK_ERR_MSG);
        }

    }
}
