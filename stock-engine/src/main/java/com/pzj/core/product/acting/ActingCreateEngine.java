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
import com.pzj.core.product.enums.WhetherSelectSeatEnum;
import com.pzj.core.product.model.ActingModel;
import com.pzj.core.product.write.ActingWriteMapper;
import com.pzj.core.stock.enums.StockStateEnum;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * 
 * @author dongchunfu
 * @version $Id: ActingCreateEngine.java, v 0.1 2016年8月8日 下午4:41:31 dongchunfu Exp $
 */
@Component("actingCreateEngine")
@Transactional(value = "stock.transactionManager")
public class ActingCreateEngine {

    private static final Logger logger = LoggerFactory.getLogger(ActingCreateEngine.class);

    @Autowired
    private ActingWriteMapper   actingWriteMapper;

    public Long insertActing(ActingModel model, ServiceContext context) {
        Acting acting = new Acting();
        try {
            PropertyUtils.copyProperties(acting, model);
        } catch (Exception e) {
            logger.error(" copy acting model properties to acting error ", e);
            throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
        }
        defaultValue(acting);//赋默认值
        acting.setId(null);//id自增
        actingWriteMapper.insertActing(acting);
        return acting.getId();

    }

    private void defaultValue(Acting acting) {
        if (null == acting.getState()) {
            acting.setState(StockStateEnum.AVAILABLE.getState());
        }
        if (null == acting.getWhetherNeedSeat()) {
            acting.setWhetherNeedSeat(WhetherSelectSeatEnum.NOT_NEED.getSelect());
        }
    }
}