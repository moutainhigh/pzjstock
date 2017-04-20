/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.actingproduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.product.entity.AreaScreeingsRel;
import com.pzj.core.product.write.AreaScreeingsRelWriteMapper;

/**
 * 
 * 
 * @author dongchunfu
 * @version $Id: AreaScreeingsRelWriteEngine.java, v 0.1 2016年8月8日 下午4:42:06 dongchunfu Exp $
 */
@Component(value = "areaScreeingsRelWriteEngine")
@Transactional
public class AreaScreeingsRelWriteEngine {
    @Autowired
    private AreaScreeingsRelWriteMapper areaScreeingsRelWriteMapper;

    public Integer insertBatch(List<AreaScreeingsRel> list) {
        areaScreeingsRelWriteMapper.insertRelBatch(list);
        return list.size();
    }
}
