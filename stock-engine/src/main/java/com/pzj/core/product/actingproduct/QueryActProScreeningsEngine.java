/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.actingproduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.entity.AreaScreeingsRel;
import com.pzj.core.product.entity.Screeings;
import com.pzj.core.product.enums.ActingStateEnum;
import com.pzj.core.product.exception.AreaScreeningsRelStateException;
import com.pzj.core.product.exception.NotFoundAreaScreeingsRelException;
import com.pzj.core.product.exception.NotFoundScreeningsException;
import com.pzj.core.product.exception.errorcode.ActingExceptionCode;
import com.pzj.core.product.model.ProScreeningsQueryModel;
import com.pzj.core.product.model.ScreeingsModel;
import com.pzj.core.product.read.AreaScreeingsRelReadMapper;
import com.pzj.core.product.read.ScreeingsReadMapper;
import com.pzj.framework.toolkit.Check;

/**
 * 
 * @author Administrator
 * @version $Id: QueryActProScreeningsEngine.java, v 0.1 2016年8月31日 下午4:11:05 Administrator Exp $
 */
@Component("queryActProScreeningsEngine")
public class QueryActProScreeningsEngine {
    @Autowired
    private AreaScreeingsRelReadMapper areaScreeingsRelReadMapper;
    @Autowired
    private ScreeingsReadMapper        screeingsReadMapper;

    public ScreeingsModel queryActProScreenings(ProScreeningsQueryModel model) {
        AreaScreeingsRel areaScreeingsRel = areaScreeingsRelReadMapper.selectAreaScreeingsRelById(model.getActProId());
        checkAreaScreeningsRel(areaScreeingsRel);

        Screeings screeings = screeingsReadMapper.selectScreeingsById(areaScreeingsRel.getScreeingsId());

        return initScreengs(screeings);
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
     * 转换场次信息
     * @param screeings
     */
    private ScreeingsModel initScreengs(Screeings screeings) {
        if (null == screeings) {
            throw new NotFoundScreeningsException(ActingExceptionCode.NOT_FOUND_SCREENINGS_ERR_MSG);
        }
        ScreeingsModel screeingsModel = new ScreeingsModel();
        screeingsModel.setId(screeings.getId());
        screeingsModel.setName(screeings.getName());
        screeingsModel.setCode(screeings.getCode());
        screeingsModel.setScenicId(screeings.getScenicId());
        screeingsModel.setStartTime(CommonUtils.screeningsTimeConvert(screeings.getStartTime()));
        screeingsModel.setEndTime(CommonUtils.screeningsTimeConvert(screeings.getEndTime()));
        String endSaleTime = CommonUtils.screeningsTimeConvert(screeings.getEndSaleTime());
        if (Check.NuNStrStrict(endSaleTime)) {
            endSaleTime = screeingsModel.getEndTime();
        }
        screeingsModel.setEndSaleTime(endSaleTime);
        return screeingsModel;
    }
}
