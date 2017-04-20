/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.acting;

import java.util.ArrayList;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.product.entity.Acting;
import com.pzj.core.product.model.ActingQueryRequestModel;
import com.pzj.core.product.read.ActingReadMapper;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * 
 * @author dongchunfu
 * @version $Id: ActingQueryByParamEngine.java, v 0.1 2016年8月8日 下午4:41:43 dongchunfu Exp $
 */
@Component("actingQueryByParamEngine")
public class ActingQueryByParamEngine {

    private static final Logger logger = LoggerFactory.getLogger(ActingQueryByParamEngine.class);

    @Autowired
    private ActingReadMapper    actingReadMapper;

    public ArrayList<Acting> queryActingByParam(ActingQueryRequestModel model, ServiceContext context) {
        Acting acting = new Acting();
        try {
            PropertyUtils.copyProperties(acting, model);
        } catch (Exception e) {
            logger.error(" copy acting query request model properties to acting error ", e);
            throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
        }
        return actingReadMapper.selectActingsByParam(acting);
    }

}