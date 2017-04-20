/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.event;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.model.query.StockListParam;
import com.pzj.core.stock.model.result.SkuStockListResult;
import com.pzj.core.stock.service.ISkuStockService;
import com.pzj.framework.context.Result;

/**
 * 
 * @author dongchunfu
 * @version $Id: CallSkuService4CreateStock.java, v 0.1 2016年8月10日 上午10:53:07 dongchunfu Exp $
 */
@Component(value = "callSkuSrvFilterRuleIdsEvent")
public class CallSkuSrvFilterRuleIdsEvent {

    private static final Logger logger = LoggerFactory.getLogger(CallSkuSrvFilterRuleIdsEvent.class);

    @Resource(name = "skuStockService")
    private ISkuStockService    skuStockService;

    @Resource(name = "statisticDateEvent")
    private StatisticDateEvent  statisticDateEvent;

    public ArrayList<Long> callSkuSrvFilterRuleIds(List<Long> ruleIds, Integer stockTime) {

        if (null == ruleIds || ruleIds.size() == 0 || stockTime == null) {
            return null;
        }
        //结果封装
        ArrayList<Long> remainIds = new ArrayList<Long>();

        //调用产品服务，获取库存规则绑定的产品信息
        StockListParam param = new StockListParam();
        param.setStockIdList(ruleIds);
        Result<ArrayList<SkuStockListResult>> skuResult = skuStockService.findStockSkuByStockRuleIds(param);

        if (skuResult.isOk()) {

            ArrayList<SkuStockListResult> data = skuResult.getData();

            if (null == data || data.size() == 0) {
                if (logger.isDebugEnabled()) {
                    logger.debug(" cale sku service result is null.ruleIds:{}", ruleIds);
                }
                return null;
            }
            for (SkuStockListResult sku : data) {

                //生成产品的有效期范围的并集时间范围
                TreeSet<Integer> statisticDate = statisticDateEvent.statisticDate(sku);
                //如果有效期范围包含当前预生成的库存记录时间，则纳入返回结果
                if (statisticDate.contains(stockTime)) {
                    remainIds.add(sku.getStockRuleId());
                }
            }
            return remainIds;
        } else {
            logger.error("call sku service failed,message:{}", skuResult.getErrorMsg());
            throw new StockException(StockExceptionCode.STOCK_ERR_MSG);
        }
    }
}
