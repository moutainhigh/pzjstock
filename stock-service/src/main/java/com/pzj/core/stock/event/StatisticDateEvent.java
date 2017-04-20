/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.event;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.stock.model.result.SkuStockListResult;
import com.pzj.core.stock.model.result.SkuStockResult;
import com.pzj.core.util.DateUtil;

/**
 * 
 * @author dongchunfu
 * @version $Id: StatisticDateEvent.java, v 0.1 2016年8月10日 上午11:22:13 dongchunfu Exp $
 */
@Component(value = "statisticDateEvent")
public class StatisticDateEvent {

    @Resource(name = "dateUtil")
    private DateUtil            dateUtil;

    private static final Logger logger = LoggerFactory.getLogger(StatisticDateEvent.class);

    public TreeSet<Integer> statisticDate(SkuStockListResult result) {

        ArrayList<SkuStockResult> skuList = result.getSkuList();
        if (null == skuList) {
            return null;
        }
        //TODO 未对关闭事件进行筛选

        TreeSet<Integer> usable = new TreeSet<Integer>();
        for (SkuStockResult sku : skuList) {
            Date startDate = sku.getStartDate();
            Date endDate = sku.getEndDate();

            if (null == startDate || null == endDate) {
                logger.error("prodct no expiry date ,sku:{}.", sku);
                continue;
                //                throw new GainProductDateDataException(StockRuleExceptionCode.GET_PRODUCT_DATE_ERR_MSG);
            }

            List<Integer> stockTimes;
            try {
                stockTimes = produceStockTime(startDate, endDate);
            } catch (ParseException e) {
                logger.error(" date parse exception ", e);
                throw new RuntimeException(e.getMessage());
            }
            usable.addAll(stockTimes);
        }
        return usable;
    }

    //创建日库存集合
    private List<Integer> produceStockTime(Date begin, Date end) throws ParseException {
        long size = dateUtil.daysDif(begin, end);
        List<Integer> stockTimes = new ArrayList<Integer>();
        for (int i = 0; i <= size; i++) {
            Date dayLater = dateUtil.dayLater(begin, i);
            Integer integerTime = dateUtil.getIntegerTime(dayLater);
            stockTimes.add(integerTime);
        }
        return stockTimes;
    }

}
