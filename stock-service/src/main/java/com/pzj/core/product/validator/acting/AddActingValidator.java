/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.validator.acting;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.model.AddActingModel;
import com.pzj.core.product.model.AreaModel;
import com.pzj.core.product.model.ScreeingsModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 
 * @author Administrator
 * @version $Id: AddActingValidator.java, v 0.1 2016年9月1日 下午4:39:26 Administrator Exp $
 */
@Component("addActingValidator")
public class AddActingValidator implements ObjectConverter<AddActingModel, ServiceContext, Boolean> {

    /** 
     * @see com.pzj.framework.converter.ObjectConverter#convert(java.lang.Object, java.lang.Object)
     */
    @Override
    public Boolean convert(AddActingModel model, ServiceContext context) {
        if (Check.NuNObject(model)) {
            return Boolean.FALSE;
        }
        if (CommonUtils.checkLongIsNull(model.getSupplierId(), true)) {
            return Boolean.FALSE;
        }
        if (CommonUtils.checkLongIsNull(model.getScenicId(), true)) {
            return Boolean.FALSE;
        }

        List<AreaModel> areaModelList = model.getAreaModelList();
        if (Check.NuNCollections(areaModelList)) {
            return Boolean.FALSE;
        } else {
            for (AreaModel areaModel : areaModelList) {
                if (CommonUtils.checkStringIsNull(areaModel.getName())) {
                    return Boolean.FALSE;
                }
                if (CommonUtils.checkLongIsNull(areaModel.getScenicId(), true)) {
                    return Boolean.FALSE;
                }
            }
        }

        List<ScreeingsModel> screeingsModelList = model.getScreeingsModelList();
        if (Check.NuNCollections(screeingsModelList)) {
            return Boolean.FALSE;
        } else {
            for (ScreeingsModel screeingsModel : screeingsModelList) {
                if (CommonUtils.checkStringIsNull(screeingsModel.getName())) {
                    return Boolean.FALSE;
                }
                if (CommonUtils.checkLongIsNull(screeingsModel.getScenicId(), true)) {
                    return Boolean.FALSE;
                }
                if (!CommonUtils.checkScreeningsTime(screeingsModel.getStartTime())) {
                    return Boolean.FALSE;
                }
                if (!CommonUtils.checkScreeningsTime(screeingsModel.getEndTime())) {
                    return Boolean.FALSE;
                }
                if (CommonUtils.checkStringIsNull(screeingsModel.getEndSaleTime())) {
                    screeingsModel.setEndSaleTime(screeingsModel.getEndTime());
                } else {
                    if (!CommonUtils.checkScreeningsTime(screeingsModel.getEndSaleTime())) {
                        return Boolean.FALSE;
                    }
                }
            }
        }
        return Boolean.TRUE;
    }
}
