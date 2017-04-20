/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.actingproduct;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.product.entity.AreaScreeingsRel;
import com.pzj.core.product.model.ActingProductModel;
import com.pzj.core.product.model.AreaModel;
import com.pzj.core.product.model.ScreeingsModel;
import com.pzj.core.product.write.AreaScreeingsRelWriteMapper;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * 
 * @author dongchunfu
 * @version $Id: ActingCreateEngine.java, v 0.1 2016年8月8日 下午4:41:31 dongchunfu Exp $
 */
@Component("actingProductCreateEngine")
@Transactional(value = "stock.transactionManager")
public class ActingProductCreateEngine {

    private static final Logger         logger = LoggerFactory.getLogger(ActingProductCreateEngine.class);

    @Autowired
    private AreaScreeingsRelWriteMapper areaScreeingsRelWriteMapper;

    public Integer insertActingProduct(ActingProductModel model, ServiceContext context) {

        List<AreaScreeingsRel> rels = generaterRel(model, context);

        areaScreeingsRelWriteMapper.insertRelBatch(rels);
        return rels.size();

    }

    private List<AreaScreeingsRel> generaterRel(ActingProductModel model, ServiceContext context) {
        ArrayList<AreaModel> areas = model.getAreas();
        ArrayList<ScreeingsModel> screeingses = model.getScreeingses();

        List<AreaScreeingsRel> result = new ArrayList<AreaScreeingsRel>(areas.size() * screeingses.size());
        AreaScreeingsRel rel = new AreaScreeingsRel();
        rel.setActingId(model.getActingId());

        for (AreaModel area : areas) {
            Long areaId = area.getId();
            for (ScreeingsModel screeings : screeingses) {
                try {
                    AreaScreeingsRel clone = rel.clone();
                    clone.setAreaId(areaId);
                    clone.setAreaName(area.getName());
                    clone.setScreeingsId(screeings.getId());
                    clone.setScreeingsName(screeings.getName());
                    result.add(clone);

                } catch (CloneNotSupportedException e) {
                    logger.error(" clone area screeings error ", e);
                    throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
                }
            }
        }
        return result;
    }
}