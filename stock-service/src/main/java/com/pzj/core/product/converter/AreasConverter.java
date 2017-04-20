/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.product.entity.Area;
import com.pzj.core.product.model.AreaModel;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 演绎区域转换工具
 * 
 * @author dongchunfu
 * @version $Id: AreaConverter.java, v 0.1 2016年8月1日 上午10:57:26 dongchunfu Exp $
 */
@Component(value = "areasConverter")
public class AreasConverter implements ObjectConverter<List<Area>, ServiceContext, ArrayList<AreaModel>> {

    private static final Logger logger = LoggerFactory.getLogger(AreasConverter.class);

    /** 底层实体转换为VO实体 */
    @Override
    public ArrayList<AreaModel> convert(List<Area> list, ServiceContext context) {
        if (null == list || list.size() == 0)
            return null;
        ArrayList<AreaModel> vos = new ArrayList<AreaModel>();
        for (Area area : list) {
            AreaModel model = new AreaModel();
            try {
                PropertyUtils.copyProperties(model, area);
            } catch (Exception e) {
                logger.error("copy Area properties to AreaModel error ,context:{}.", context, e);
                throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
            }
            vos.add(model);
        }
        return vos;
    }
}
