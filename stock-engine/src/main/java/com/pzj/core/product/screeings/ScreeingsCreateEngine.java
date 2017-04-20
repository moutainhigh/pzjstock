/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.screeings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.product.entity.Screeings;
import com.pzj.core.product.model.ScreeingsModel;
import com.pzj.core.product.write.ScreeingsWriteMapper;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * 
 * @author dongchunfu
 * @version $Id: ScreeingsCreateEngine.java, v 0.1 2016年8月8日 下午4:42:30 dongchunfu Exp $
 */
@Component("screeingsCreateEngine")
@Transactional(value = "stock.transactionManager")
public class ScreeingsCreateEngine {
    @Autowired
    private ScreeingsWriteMapper screeingsWriteMapper;

    public Long insertScreeings(ScreeingsModel model, ServiceContext context) {
        Screeings screeings = convertScreeingsModel(model);
        screeingsWriteMapper.insertScreeings(screeings);
        return screeings.getId();
    }

    private Screeings convertScreeingsModel(ScreeingsModel model) {
        Screeings screeings = new Screeings();
        screeings.setScenicId(model.getScenicId());
        screeings.setCode(model.getCode());
        screeings.setName(model.getName());
        screeings.setStartTime(Integer.parseInt(model.getStartTime()));
        screeings.setEndTime(Integer.parseInt(model.getEndTime()));
        String endSaleTime = model.getEndSaleTime();
        if (null == endSaleTime || "".equals(endSaleTime)) {
            screeings.setEndSaleTime(Integer.parseInt(model.getEndTime()));
        } else {
            screeings.setEndSaleTime(Integer.parseInt(model.getEndSaleTime()));
        }
        return screeings;
    }
}