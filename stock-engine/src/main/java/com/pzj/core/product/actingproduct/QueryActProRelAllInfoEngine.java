/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.actingproduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.product.entity.Acting;
import com.pzj.core.product.entity.AreaScreeingsRel;
import com.pzj.core.product.enums.ActingStateEnum;
import com.pzj.core.product.exception.AreaScreeningsRelStateException;
import com.pzj.core.product.exception.NotFoundActingException;
import com.pzj.core.product.exception.NotFoundAreaScreeingsRelException;
import com.pzj.core.product.exception.StateException;
import com.pzj.core.product.exception.errorcode.ActingExceptionCode;
import com.pzj.core.product.model.ActingProductQueryRequstModel;
import com.pzj.core.product.model.AreaScreeingsRelModel;
import com.pzj.core.product.read.ActingReadMapper;
import com.pzj.core.product.read.AreaScreeingsRelReadMapper;

/**
 * 
 * @author Administrator
 * @version $Id: QueryActProRelAllInfoEngine.java, v 0.1 2016年9月13日 下午4:46:58 Administrator Exp $
 */
@Component("queryActProRelAllInfoEngine")
public class QueryActProRelAllInfoEngine {
    @Autowired
    private AreaScreeingsRelReadMapper areaScreeingsRelReadMapper;
    @Autowired
    private ActingReadMapper           actingReadMapper;

    public AreaScreeingsRelModel queryOccupySeatActProRel(ActingProductQueryRequstModel model) {
        //1、获取预约场次关联信息
        AreaScreeingsRel areaScreeingsRel = areaScreeingsRelReadMapper.selectAreaScreeingsRelById(model.getActProId());
        checkAreaScreeningsRel(areaScreeingsRel);

        //2、获取演艺信息
        Acting acting = actingReadMapper.selectActingById(areaScreeingsRel.getActingId());
        checkActing(acting);

        AreaScreeingsRelModel areaScreeRel = new AreaScreeingsRelModel();
        areaScreeRel.setScenicId(acting.getScenicId());
        areaScreeRel.setSupplierId(acting.getSupplierId());
        areaScreeRel.setAreaId(areaScreeingsRel.getAreaId());
        areaScreeRel.setScreeingsId(areaScreeingsRel.getScreeingsId());
        return areaScreeRel;
    }

    /**
     * 检查区域场次关联信息
     * @param areaScreeingsRel
     */
    private void checkAreaScreeningsRel(AreaScreeingsRel areaScreeingsRel) {
        if (null == areaScreeingsRel) {
            throw new NotFoundAreaScreeingsRelException(ActingExceptionCode.NOT_FOUND_AREA_SCREEINGS_REL_ERR_MSG);
        }
        if (areaScreeingsRel.getState() != ActingStateEnum.AVAILABLE.getState()) {
            throw new AreaScreeningsRelStateException(ActingExceptionCode.AREA_SCREEINGS_STATE_ERR_MSG);
        }
    }

    /**
     * 检查演艺数据
     * @param acting
     */
    private void checkActing(Acting acting) {
        if (null == acting) {
            throw new NotFoundActingException(ActingExceptionCode.NOT_FOUND_ACTING_ERR_MSG);
        }
        if (acting.getState() != ActingStateEnum.AVAILABLE.getState()) {
            throw new StateException(ActingExceptionCode.ACTING_STATE_ERR_MSG);
        }
    }
}
