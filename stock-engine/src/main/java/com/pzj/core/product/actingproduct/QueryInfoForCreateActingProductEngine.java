/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.actingproduct;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.product.entity.Acting;
import com.pzj.core.product.entity.Area;
import com.pzj.core.product.entity.Screeings;
import com.pzj.core.product.model.ActingProductQueryRequstModel;
import com.pzj.core.product.model.ThreeDulpe;
import com.pzj.core.product.read.ActingReadMapper;
import com.pzj.core.product.read.AreaReadMapper;
import com.pzj.core.product.read.ScreeingsReadMapper;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * @author dongchunfu
 * @version $Id: QueryInfo4CreateActingProductEngine.java, v 0.1 2016年8月8日 下午6:17:18 dongchunfu Exp $
 */
@Component(value = "queryInfoForCreateActingProductEngine")
public class QueryInfoForCreateActingProductEngine {

    @Autowired
    private ActingReadMapper    actingReadMapper;
    @Autowired
    private AreaReadMapper      areaReadMapper;
    @Autowired
    private ScreeingsReadMapper screeingsReadMapper;

    public ThreeDulpe<ArrayList<Acting>, ArrayList<Screeings>, ArrayList<Area>> selectInfoForSkuProductEngine(ActingProductQueryRequstModel model,
                                                                                                              ServiceContext context) {

        Acting acting = new Acting();
        Long scenicId = model.getScenicId();
        acting.setScenicId(scenicId);
        acting.setSupplierId(model.getSupplierId());

        ArrayList<Acting> actings = actingReadMapper.selectActingsByParam(acting);

        Screeings screeings = new Screeings();
        screeings.setScenicId(scenicId);
        ArrayList<Screeings> screeingses = screeingsReadMapper.selectScreeingsesByParam(screeings);

        Area area = new Area();
        area.setScenicId(scenicId);
        ArrayList<Area> areas = areaReadMapper.selectAreasByParam(area);

        ThreeDulpe<ArrayList<Acting>, ArrayList<Screeings>, ArrayList<Area>> result = new ThreeDulpe<ArrayList<Acting>, ArrayList<Screeings>, ArrayList<Area>>();

        result.setE(screeingses);
        result.setF(areas);
        result.setT(actings);

        return result;
    }

}
