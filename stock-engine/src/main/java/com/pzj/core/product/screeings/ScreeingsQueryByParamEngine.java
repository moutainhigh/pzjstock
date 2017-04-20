/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.screeings;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.common.exception.ParameterErrorException;
import com.pzj.core.product.entity.Screeings;
import com.pzj.core.product.model.ScreeingsQueryRequestModel;
import com.pzj.core.product.read.ScreeingsReadMapper;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * 
 * @author dongchunfu
 * @version $Id: ScreeingsQueryByParamEngine.java, v 0.1 2016年8月8日 下午4:42:43 dongchunfu Exp $
 */
@Component("screeingsQueryByParamEngine")
public class ScreeingsQueryByParamEngine {

    private static final Logger logger = LoggerFactory.getLogger(ScreeingsQueryByParamEngine.class);

    @Autowired
    private ScreeingsReadMapper screeingsReadMapper;

    public ArrayList<Screeings> queryScreeingsByParam(ScreeingsQueryRequestModel model, ServiceContext context) {

        Screeings screeings = null;
        try {
            screeings = convertRequest(model);
        } catch (Throwable t) {
            logger.error(" set Screeings startTime error,context:{}. ", context, t);
            throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG, t);
        }

        return screeingsReadMapper.selectScreeingsesByParam(screeings);
    }

    public Screeings convertRequest(ScreeingsQueryRequestModel model) {
        Screeings screeings = new Screeings();
        screeings.setCode(model.getCode());
        screeings.setName(model.getName());
        screeings.setScenicId(model.getScenicId());
        String startTime = model.getStartTime();
        if (null != startTime && !"".equals(startTime)) {
            screeings.setStartTime(Integer.valueOf(startTime));
        }
        return screeings;

    }
}