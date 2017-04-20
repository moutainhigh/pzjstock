/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.actingproduct;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.common.exception.ParameterErrorException;
import com.pzj.core.common.exception.StockException;
import com.pzj.core.product.entity.Acting;
import com.pzj.core.product.entity.Area;
import com.pzj.core.product.entity.AreaScreeingsRel;
import com.pzj.core.product.entity.Screeings;
import com.pzj.core.product.exception.NotFoundAreaScreeingsRelException;
import com.pzj.core.product.exception.errorcode.ActingExceptionCode;
import com.pzj.core.product.model.ActingModel;
import com.pzj.core.product.model.ActingProductModel;
import com.pzj.core.product.model.ActingProductQueryRequstModel;
import com.pzj.core.product.model.AreaModel;
import com.pzj.core.product.model.ScreeingsModel;
import com.pzj.core.product.read.ActingReadMapper;
import com.pzj.core.product.read.AreaReadMapper;
import com.pzj.core.product.read.AreaScreeingsRelReadMapper;
import com.pzj.core.product.read.ScreeingsReadMapper;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.framework.context.ServiceContext;

/**
 * 查询演绎产品 关联信息引擎
 * 
 * @author dongchunfu
 * @version $Id: QueryActProInfoEngine.java, v 0.1 2016年8月21日 下午3:30:08 dongchunfu Exp $
 */

@Component(value = "queryActProInfoEngine")
public class QueryActProInfoEngine {

    private static final Logger        logger = LoggerFactory.getLogger(QueryActProInfoEngine.class);

    @Autowired
    private ActingReadMapper           actingReadMapper;
    @Autowired
    private AreaReadMapper             areaReadMapper;
    @Autowired
    private ScreeingsReadMapper        screeingsReadMapper;

    @Autowired
    private AreaScreeingsRelReadMapper areaScreeingsRelReadMapper;

    public ActingProductModel qureyActProInfo(ActingProductQueryRequstModel model, ServiceContext context) {

        Long actProId = model.getActProId();
        if (null == actProId || actProId < 0) {
            logger.error("illegal param ,actProId:{}.", actProId);
            throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
        }

        AreaScreeingsRel rel = areaScreeingsRelReadMapper.selectAreaScreeingsRelById(actProId);

        if (null == rel) {
            logger.warn("do not exit AreaScreeingsRel ,id:{}.", actProId);
            throw new NotFoundAreaScreeingsRelException(ActingExceptionCode.NOT_FOUND_AREA_SCREEINGS_REL_ERR_MSG);
        }
        Long actingId = rel.getActingId();
        Long areaId = rel.getAreaId();
        Long screeingsId = rel.getScreeingsId();

        if (null == actingId || null == areaId || null == screeingsId) {
            logger.warn("remain rel error ,actingId:{},areaId:{},screeingsId:{}.", actingId, areaId, screeingsId);
            throw new StockException(StockExceptionCode.STOCK_ERR_MSG);
        }
        Acting acting = actingReadMapper.selectActingById(actingId);

        if (null == acting) {
            logger.warn("do not exit Acting,actingId:{}.", actingId);
            throw new StockException(StockExceptionCode.STOCK_ERR_MSG);
        }
        Area area = areaReadMapper.selectAreaById(areaId);
        if (null == area) {
            logger.warn("do not exit Area ,areaId:{}.", areaId);
            throw new StockException(StockExceptionCode.STOCK_ERR_MSG);
        }

        Screeings screeings = screeingsReadMapper.selectScreeingsById(screeingsId);
        if (null == screeings) {
            logger.warn("do not exit Screeings, screeingsId:{}.", screeingsId);
            throw new StockException(StockExceptionCode.STOCK_ERR_MSG);
        }

        return packageResult(acting, area, screeings);

    }

    private ActingProductModel packageResult(Acting acting, Area area, Screeings screeings) {

        ActingModel actingModel = new ActingModel();
        AreaModel areaModel = new AreaModel();
        ScreeingsModel screeingsModel;
        try {
            PropertyUtils.copyProperties(actingModel, acting);

            PropertyUtils.copyProperties(areaModel, area);
            //时间类型 数据库为Integer 实体为String
            //PropertyUtils.copyProperties(screeingsModel, screeings);
            screeingsModel = packageScreeings(screeings);
        } catch (Throwable t) {
            if (logger.isDebugEnabled()) {
                logger.debug("copy properties error", t);
            }
            throw new StockException(StockExceptionCode.STOCK_ERR_MSG, t);
        }
        ActingProductModel apm = new ActingProductModel();
        apm.setAreaModel(areaModel);
        apm.setScreeingsModel(screeingsModel);
        apm.setActingModel(actingModel);

        apm.setScenicId(actingModel.getScenicId());
        apm.setSupplierId(actingModel.getSupplierId());
        return apm;
    }

    private ScreeingsModel packageScreeings(Screeings screeings) {
        ScreeingsModel screeingsModel = new ScreeingsModel();

        screeingsModel.setId(screeings.getId());
        screeingsModel.setScenicId(screeings.getScenicId());
        screeingsModel.setCode(screeings.getCode());
        screeingsModel.setName(screeings.getName());
        screeingsModel.setStartTime(String.valueOf(screeings.getStartTime()));
        screeingsModel.setEndTime(String.valueOf(screeings.getEndTime()));
        screeingsModel.setEndSaleTime(String.valueOf(screeings.getEndSaleTime()));

        return screeingsModel;
    }

}
