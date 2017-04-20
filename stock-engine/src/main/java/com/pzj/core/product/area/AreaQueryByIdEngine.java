/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.area;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.product.entity.Area;
import com.pzj.core.product.read.AreaReadMapper;

/**
 * 
 * 
 * @author dongchunfu
 * @version $Id: AreaQueryByIdEngine.java, v 0.1 2016年8月8日 下午4:41:23 dongchunfu Exp $
 */
@Component("areaQueryByIdEngine")
public class AreaQueryByIdEngine {
    @Autowired
    private AreaReadMapper areaReadMapper;

    public Area queryAreaById(Long areaId) {

        return areaReadMapper.selectAreaById(areaId);
    }

}