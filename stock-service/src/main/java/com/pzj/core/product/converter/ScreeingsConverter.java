/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.converter;

import org.springframework.stereotype.Component;

import com.pzj.core.product.entity.Screeings;
import com.pzj.core.product.model.ScreeingsModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 演绎场次转换工具
 * 
 * @author dongchunfu
 * @version $Id: AreaConverter.java, v 0.1 2016年8月1日 上午10:57:26 dongchunfu Exp $
 */
@Component(value = "screeingsConverter")
public class ScreeingsConverter implements ObjectConverter<Screeings, ServiceContext, ScreeingsModel> {
    @Override
    public ScreeingsModel convert(Screeings screeings, ServiceContext context) {
        if (null == screeings)
            return null;

        ScreeingsModel model = new ScreeingsModel();
        model.setId(screeings.getId());
        model.setCode(screeings.getCode());
        model.setName(screeings.getName());
        model.setScenicId(screeings.getScenicId());
        model.setStartTime(timerConvertor(screeings.getStartTime()));
        model.setEndTime(timerConvertor(screeings.getEndTime()));
        Integer endSaleTime = screeings.getEndSaleTime();

        if (null != endSaleTime) {
            model.setEndSaleTime(timerConvertor(endSaleTime));
        }
        return model;
    }

    private static String timerConvertor(Integer timer) {
        StringBuilder sb = new StringBuilder();
        String str = String.valueOf(timer);
        int length = str.length();
        sb.append(str);
        for (int i = 0; i < 4 - length; i++) {
            sb.insert(0, "0", 0, 1);
        }
        return sb.toString().intern();
    }

}
