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
import com.pzj.core.stock.model.AddStockModel;
import com.pzj.core.stock.model.query.StockListParam;
import com.pzj.core.stock.model.result.SkuStockListResult;
import com.pzj.core.stock.model.result.SkuStockResult;
import com.pzj.core.stock.service.ISkuStockService;
import com.pzj.framework.context.Result;

/**
 * 
 * @author Administrator
 * @version $Id: FilterStockRuleTimer.java, v 0.1 2016年9月8日 下午2:21:17 Administrator Exp $
 */
@Component("filterStockRuleTimer")
public class FilterStockRuleDateTimer {
    private static final Logger logger = LoggerFactory.getLogger(FilterStockRuleDateTimer.class);
    @Resource(name = "skuStockService")
    private ISkuStockService    skuStockService;

    public List<AddStockModel> filterStockRuleDate(List<Long> ruleIds) {
        if (CommonUtils.checkObjectIsNull(ruleIds)) {
            return null;
        }

        StockListParam stockListParam = new StockListParam();
        stockListParam.setStockIdList(ruleIds);
        Result<ArrayList<SkuStockListResult>> stockProResult = skuStockService.findStockSkuByStockRuleIds(stockListParam);
        if (stockProResult.isOk()) {
            ArrayList<SkuStockListResult> stockProList = stockProResult.getData();
            if (CommonUtils.checkObjectIsNull(stockProList)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("库存规则查询不到可用的产品列表---stockRuleIds:{},result:{}", ruleIds, stockProResult);
                }
                return null;
            }

            //过滤时间
            List<AddStockModel> addStockModelList = new ArrayList<AddStockModel>();
            ArrayList<SkuStockResult> skuList = null;
            Long stockRuleId = null;
            ArrayList<Integer> stockTimeList = null;
            for (SkuStockListResult stockPro : stockProList) {
                stockRuleId = stockPro.getStockRuleId();
                skuList = stockPro.getSkuList();
                if (CommonUtils.checkObjectIsNull(skuList) || CommonUtils.checkLongIsNull(stockRuleId, true)) {
                    continue;
                }
                Set<Integer> stockTimeSet = new LinkedHashSet<Integer>();
                for (SkuStockResult skuStock : skuList) {
                    Date minDate = skuStock.getStartDate();
                    Date maxDate = skuStock.getEndDate();
                    if (null != minDate && null != maxDate) {
                        //判断最小日期是否小于当前日期
                        if (!CommonUtils.checkStockMinTime(minDate)) {
                            minDate = new Date();
                        }
                        Integer threeMonthMaxDay = CommonUtils.dateAddMonthMaxDay(minDate, 3);
                        Integer proMaxDate = CommonUtils.getStockDateInteger(maxDate);
                        if (threeMonthMaxDay.intValue() <= proMaxDate.intValue()) {
                            stockTimeSet.add(threeMonthMaxDay);
                        }
                    }
                }

                AddStockModel addStockModel = new AddStockModel();
                stockTimeList = new ArrayList<Integer>();
                stockTimeList.addAll(stockTimeSet);
                addStockModel.setStockRuleId(stockRuleId);
                addStockModel.setStockTimeIntList(stockTimeList);
                addStockModelList.add(addStockModel);

            }
            return addStockModelList;
        } else {
            logger.error("---定时通过库存规则ID查询产品列表异常，stockProResult:{}", stockProResult);
            throw new StockException(StockExceptionCode.STOCK_ERR_MSG);
        }
    }
}
