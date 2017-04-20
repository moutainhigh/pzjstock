/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.area;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.product.entity.Area;
import com.pzj.core.product.model.AreaModel;
import com.pzj.core.product.write.AreaWriteMapper;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * 
 * @author dongchunfu
 * @version $Id: AreaUpdateEngine.java, v 0.1 2016年8月8日 下午4:42:23 dongchunfu Exp $
 */
@Component("areaUpdateEngine")
@Transactional(value = "stock.transactionManager")
public class AreaUpdateEngine {

    private static final Logger logger = LoggerFactory.getLogger(AreaUpdateEngine.class);

    @Autowired
    private AreaWriteMapper     areaWriteMapper;

    public int updateArea(AreaModel model, ServiceContext context) {
        Area area = new Area();
        try {
            PropertyUtils.copyProperties(area, model);
        } catch (Exception e) {
            logger.error(" copy area model properties to area error,request:{},context:{}. ", model, context, e);
            throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
        }

        int count = areaWriteMapper.updateAreaById(area);

        return count;
    }
}