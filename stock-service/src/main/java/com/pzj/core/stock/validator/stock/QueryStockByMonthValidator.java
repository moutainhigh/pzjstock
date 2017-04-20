/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.validator.stock;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.StockQueryRequestModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 
 * @author dongchunfu
 * @version $Id: QueryStockRuleValidator.java, v 0.1 2016年8月3日 下午7:44:45 dongchunfu Exp $
 */
@Component(value = "queryStockByMonthValidator")
public class QueryStockByMonthValidator implements ObjectConverter<StockQueryRequestModel, ServiceContext, Boolean> {

    private static final Logger logger = LoggerFactory.getLogger(QueryStockByMonthValidator.class);

    @Override
    public Boolean convert(StockQueryRequestModel model, ServiceContext context) {
        if (Check.NuNObject(model)) {
            return Boolean.FALSE;
        }

        Integer queryMonth = model.getQueryMonth();
        if (CommonUtils.checkIntegerIsNull(queryMonth, true) || String.valueOf(queryMonth).length() != 8) {
            if (logger.isDebugEnabled()) {
                logger.debug("illegal param ,queryMonth:{},context:{}.", queryMonth, context);
            }
            return Boolean.FALSE;
        }

        Integer month = Integer.valueOf(String.valueOf(queryMonth).substring(4, 6));//查询月份
        Integer year = Integer.valueOf(String.valueOf(queryMonth).substring(0, 4));//查询年份
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH) + 1;//当前月份
        int currentYear = calendar.get(Calendar.YEAR);//当前年份

        if (year < currentYear) {
            if (logger.isDebugEnabled()) {
                logger.debug("illegal param ,year:{},context:{}.", year, context);
            }
            return Boolean.FALSE;
        }

        if (year == currentYear) {
            if (month < currentMonth) {
                if (logger.isDebugEnabled()) {
                    logger.debug("illegal param ,year:{},month:{},context:{}.", year, month, context);
                }
                return Boolean.FALSE;
            }
        }

        //只允许跨一年
        if ((year - currentYear) > 1) {
            if (logger.isDebugEnabled()) {
                logger.debug("illegal param ,year:{},context:{}.", year, context);
            }
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}
