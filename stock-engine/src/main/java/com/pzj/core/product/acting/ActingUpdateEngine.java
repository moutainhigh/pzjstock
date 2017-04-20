/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.acting;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.product.entity.Acting;
import com.pzj.core.product.model.ActingModel;
import com.pzj.core.product.write.ActingWriteMapper;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * 
 * @author dongchunfu
 * @version $Id: ActingUpdateEngine.java, v 0.1 2016年8月8日 下午4:41:48 dongchunfu Exp $
 */
@Component("actingUpdateEngine")
@Transactional(value = "stock.transactionManager")
public class ActingUpdateEngine {

    private static final Logger logger = LoggerFactory.getLogger(ActingUpdateEngine.class);

    @Autowired
    private ActingWriteMapper   actingWriteMapper;

    public Integer updateActing(ActingModel model, ServiceContext context) {
        Acting acting = new Acting();
        try {
            PropertyUtils.copyProperties(acting, model);
        } catch (Exception e) {
            logger.error(" copy acting model properties to acting error ", e);
            throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
        }
        if (logger.isDebugEnabled()) {
            logger.debug(" update acting by acting id ,acting:{}.", acting);
        }
        int count = actingWriteMapper.updateActingById(acting);

        return count;
    }
}