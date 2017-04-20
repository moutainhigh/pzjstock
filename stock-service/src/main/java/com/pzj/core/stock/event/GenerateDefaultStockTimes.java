/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.event;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.util.DateUtil;

/**
 * 
 * @author dongchunfu
 * @version $Id: GenerateDefaultStockTimes.java, v 0.1 2016年8月10日 下午7:41:50 dongchunfu Exp $
 */
@Component(value = "generateDefaultStockTimes")
public class GenerateDefaultStockTimes {

    private static final Logger logger = LoggerFactory.getLogger(GenerateDefaultStockTimes.class);

    @Resource(name = "dateUtil")
    private DateUtil            dateUtil;

    public ArrayList<Integer> generateDefaultStockTimes() {

        ArrayList<Integer> stockTimes = new ArrayList<Integer>();
        Date date = new Date();
        Date monthLater = dateUtil.monthLater(date, 3);
        int daysDif = 0;
        try {
            daysDif = dateUtil.daysDif(date, monthLater);
        } catch (ParseException e) {
            logger.error("", e);
        }

        for (int i = 0; i < daysDif; i++) {
            Date dayLater = dateUtil.dayLater(date, i);
            Integer integerTime = dateUtil.getIntegerTime(dayLater);
            stockTimes.add(integerTime);
        }
        return stockTimes;
    }

}
