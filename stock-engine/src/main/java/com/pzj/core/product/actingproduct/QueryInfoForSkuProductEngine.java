/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.actingproduct;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.product.entity.Acting;
import com.pzj.core.product.entity.AreaScreeingsRel;
import com.pzj.core.product.exception.NotFoundActingException;
import com.pzj.core.product.exception.NotFoundAreaScreeingsRelException;
import com.pzj.core.product.exception.errorcode.ActingExceptionCode;
import com.pzj.core.product.model.ActingProductQueryRequstModel;
import com.pzj.core.product.read.ActingReadMapper;
import com.pzj.core.product.read.AreaScreeingsRelReadMapper;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * @author dongchunfu
 * @version $Id: QueryInfo4CreateActingProductEngine.java, v 0.1 2016年8月8日 下午6:17:18 dongchunfu Exp $
 */
@Component(value = "queryInfoForSkuProductEngine")
public class QueryInfoForSkuProductEngine {

    private static final Logger        logger = LoggerFactory.getLogger(QueryInfoForSkuProductEngine.class);

    @Autowired
    private ActingReadMapper           actingReadMapper;
    @Autowired
    private AreaScreeingsRelReadMapper areaScreeingsRelReadMapper;

    public ArrayList<AreaScreeingsRel> selectInfoForSkuProductEngine(ActingProductQueryRequstModel model, ServiceContext context) {

        Acting acting = new Acting();
        acting.setScenicId(model.getScenicId());
        acting.setSupplierId(model.getSupplierId());

        if (logger.isDebugEnabled()) {
            logger.debug("query acting by param,request:{},context:{}.", acting, context);
        }

        ArrayList<Acting> actings = actingReadMapper.selectActingsByParam(acting);

        if (null == actings || actings.size() == 0) {
            if (logger.isDebugEnabled()) {
                logger.debug("query acting result is null,request:{},context:{}.", acting, context);
            }
            throw new NotFoundActingException(ActingExceptionCode.NOT_FOUND_ACTING_ERR_MSG);
        }

        ArrayList<Long> ids = getIds(actings);

        ArrayList<AreaScreeingsRel> rels = areaScreeingsRelReadMapper.selectAreaScreeingsRelByActingIds(ids);

        if (null == rels || rels.size() == 0) {
            if (logger.isDebugEnabled()) {
                logger.debug("query rels result is null,request:{},context:{}.", ids, context);
            }
            throw new NotFoundAreaScreeingsRelException(ActingExceptionCode.NOT_FOUND_AREA_SCREEINGS_REL_ERR_MSG);
        }

        return rels;
    }

    private ArrayList<Long> getIds(ArrayList<Acting> list) {
        if (null == list || list.size() == 0) {
            return null;
        }
        ArrayList<Long> result = new ArrayList<Long>(list.size());
        for (Acting acting : list) {
            result.add(acting.getId());
        }
        return result;
    }
}
