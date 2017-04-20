/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.area;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.product.entity.Acting;
import com.pzj.core.product.entity.Area;
import com.pzj.core.product.entity.AreaScreeingsRel;
import com.pzj.core.product.enums.ActingStateEnum;
import com.pzj.core.product.exception.NotFoundActingException;
import com.pzj.core.product.exception.NotFoundAreaScreeingsRelException;
import com.pzj.core.product.exception.errorcode.ActingExceptionCode;
import com.pzj.core.product.model.AreaQueryRequestModel;
import com.pzj.core.product.read.ActingReadMapper;
import com.pzj.core.product.read.AreaReadMapper;
import com.pzj.core.product.read.AreaScreeingsRelReadMapper;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.toolkit.Check;

/**
 * 
 * @author Administrator
 * @version $Id: AreasQueryByWebSeatChartEngine.java, v 0.1 2016年9月7日 下午5:22:52 Administrator Exp $
 */
@Component("areasQueryByWebSeatChartEngine")
public class AreasQueryByWebSeatChartEngine {
    @Autowired
    private AreaReadMapper             areaReadMapper;
    @Autowired
    private ActingReadMapper           actingReadMapper;
    @Autowired
    private AreaScreeingsRelReadMapper areaScreeingsRelReadMapper;

    public List<Area> queryAreaByScenic(AreaQueryRequestModel model, ServiceContext context) {
        List<Area> areaList = new ArrayList<Area>();
        //通过景区拿到演艺信息
        Acting acting = new Acting();
        acting.setScenicId(model.getScenicId());
        ArrayList<Acting> actingList = actingReadMapper.selectActingsByParam(acting);
        checkActing(actingList);

        //通过演艺拿到可用区域信息
        ArrayList<Long> actingId = new ArrayList<Long>();
        actingId.add(actingList.get(0).getId());
        ArrayList<AreaScreeingsRel> areaSceeningRelList = areaScreeingsRelReadMapper.selectAreaScreeingsRelByActingIds(actingId);
        Set<Long> areaId = checkAreaScreeningsRel(areaSceeningRelList);

        //通过区域ID拿到区域信息
        List<Long> areaIdList = new ArrayList<Long>();
        areaIdList.addAll(areaId);
        areaList = areaReadMapper.queryAreaByIds(areaIdList);
        return areaList;
    }

    /**
     * 检查区域场次关联演艺信息
     * 
     * @param areaSceeningRelList
     */
    private Set<Long> checkAreaScreeningsRel(ArrayList<AreaScreeingsRel> areaSceeningRelList) {
        Set<Long> areaIdSet = new HashSet<Long>();
        if (Check.NuNCollections(areaSceeningRelList)) {
            throw new NotFoundAreaScreeingsRelException(ActingExceptionCode.NOT_FOUND_AREA_SCREEINGS_REL_ERR_MSG);
        }
        Iterator<AreaScreeingsRel> itera = areaSceeningRelList.iterator();
        while (itera.hasNext()) {
            AreaScreeingsRel areaScreeingsRel = itera.next();
            if (null == areaScreeingsRel) {
                itera.remove();
                continue;
            }
            if (areaScreeingsRel.getState() != ActingStateEnum.AVAILABLE.getState()) {
                itera.remove();
                continue;
            }
            areaIdSet.add(areaScreeingsRel.getAreaId());

        }
        if (Check.NuNCollections(areaIdSet)) {
            throw new NotFoundAreaScreeingsRelException(ActingExceptionCode.NOT_FOUND_AREA_SCREEINGS_REL_ERR_MSG);
        }
        return areaIdSet;
    }

    /**
     * 检查演艺数据是否存在
     * 
     * @param actingList
     */
    private void checkActing(ArrayList<Acting> actingList) {
        if (Check.NuNCollections(actingList)) {
            throw new NotFoundActingException(ActingExceptionCode.NOT_FOUND_ACTING_ERR_MSG);
        }
        Iterator<Acting> itera = actingList.iterator();
        while (itera.hasNext()) {
            Acting acting = itera.next();
            if (null == acting) {
                itera.remove();
                continue;
            }
            if (acting.getState() != ActingStateEnum.AVAILABLE.getState()) {
                itera.remove();
                continue;
            }
        }

        if (Check.NuNCollections(actingList)) {
            throw new NotFoundActingException(ActingExceptionCode.NOT_FOUND_ACTING_ERR_MSG);
        }
    }
}
