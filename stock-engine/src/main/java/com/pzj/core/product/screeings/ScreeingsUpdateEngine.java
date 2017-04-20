/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.screeings;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.product.entity.Screeings;
import com.pzj.core.product.model.ScreeingsModel;
import com.pzj.core.product.write.ScreeingsWriteMapper;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * @author dongchunfu
 * @version $Id: ScreeingsUpdateEngine.java, v 0.1 2016年8月8日 下午4:42:50 dongchunfu Exp $
 */
@Component("screeingsUpdateEngine")
@Transactional(value = "stock.transactionManager")
public class ScreeingsUpdateEngine {

    private static final Logger  logger = LoggerFactory.getLogger(ScreeingsUpdateEngine.class);

    @Autowired
    private ScreeingsWriteMapper screeingsWriteMapper;

    public int updateScreeings(ScreeingsModel model, ServiceContext context) {
        Screeings screeings = new Screeings();
        try {
            PropertyUtils.copyProperties(screeings, model);
        } catch (Exception e) {
            logger.error(" copy screeings model properties to screeings error,request:{},context:{}. ", model, context, e);
            throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
        }
        int count = screeingsWriteMapper.updateScreeingsById(screeings);
        return count;
    }
}