/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.converter;

import java.util.ArrayList;

import javax.annotation.Resource;

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
@Component(value = "screeingsesConverter")
public class ScreeingsesConverter implements ObjectConverter<ArrayList<Screeings>, ServiceContext, ArrayList<ScreeingsModel>> {

    @Resource(name = "screeingsConverter")
    private ScreeingsConverter screeingsConverter;

    /** 底层实体转换为VO实体 */
    @Override
    public ArrayList<ScreeingsModel> convert(ArrayList<Screeings> list, ServiceContext context) {
        if (null == list || list.size() == 0)
            return null;
        ArrayList<ScreeingsModel> vos = new ArrayList<ScreeingsModel>();
        for (Screeings screeings : list) {
            ScreeingsModel model = screeingsConverter.convert(screeings, context);
            vos.add(model);
        }
        return vos;
    }
}
