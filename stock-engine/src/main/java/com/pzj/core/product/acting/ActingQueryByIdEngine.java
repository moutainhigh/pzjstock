/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.acting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.product.entity.Acting;
import com.pzj.core.product.read.ActingReadMapper;

/**
 * 
 * 
 * @author dongchunfu
 * @version $Id: ActingQueryByIdEngine.java, v 0.1 2016年8月8日 下午4:41:37 dongchunfu Exp $
 */
@Component("actingQueryByIdEngine")
public class ActingQueryByIdEngine {

    @Autowired
    private ActingReadMapper actingReadMapper;

    public Acting queryActingById(Long actingId) {
        return actingReadMapper.selectActingById(actingId);
    }
}