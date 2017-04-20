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
 * @version $Id: AreaCreateEngine.java, v 0.1 2016年8月8日 下午4:41:56 dongchunfu Exp $
 */
@Component("areaCreateEngine")
@Transactional(value = "stock.transactionManager")
public class AreaCreateEngine {
    private static final Logger logger = LoggerFactory.getLogger(AreaCreateEngine.class);
    @Autowired
    private AreaWriteMapper     areaWriteMapper;

    public Long createArea(AreaModel model, ServiceContext context) {

        Area area = new Area();
        try {
            PropertyUtils.copyProperties(area, model);
        } catch (Exception e) {
            logger.error(" copy area model properties to acting error ", e);
            throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
        }
        area.setId(null);//id自增
        areaWriteMapper.insertArea(area);
        return area.getId();
    }
}