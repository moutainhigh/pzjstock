/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.area;

import java.util.ArrayList;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.product.entity.Area;
import com.pzj.core.product.model.AreaQueryRequestModel;
import com.pzj.core.product.read.AreaReadMapper;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.framework.context.ServiceContext;

/**
 * 根据参数查询区域信息
 * 
 * @author dongchunfu
 * @version $Id: AreaQueryByParamEngine.java, v 0.1 2016年8月1日 上午10:33:15 dongchunfu Exp $
 */
@Component("areaQueryByParamEngine")
public class AreaQueryByParamEngine {

    private static final Logger logger = LoggerFactory.getLogger(AreaQueryByParamEngine.class);

    @Autowired
    private AreaReadMapper      areaReadMapper;

    public ArrayList<Area> queryAreaByParam(AreaQueryRequestModel model, ServiceContext context) {
        Area area = new Area();
        try {
            PropertyUtils.copyProperties(area, model);
        } catch (Exception e) {
            logger.error(" copy area model properties to area error ", e);
            throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
        }
        return areaReadMapper.selectAreasByParam(area);
    }
}