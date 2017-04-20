/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.event;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.model.ScreeingsModel;

/**
 * 
 * @author dongchunfu
 * @version $Id: ValidateScreeingsParam.java, v 0.1 2016年8月9日 下午6:31:20 dongchunfu Exp $
 */
@Component(value = "validateScreeingsInsertParam")
public class ValidateScreeingsInsertParam {

    public Boolean validateParam(ScreeingsModel model) {
        Long scenicId = model.getScenicId();
        if (CommonUtils.checkLongIsNull(scenicId, Boolean.TRUE)) {
            return Boolean.FALSE;
        }
        //name 不得为空
        String name = model.getName();
        if (CommonUtils.checkStringIsNull(name)) {
            return Boolean.FALSE;
        }
        //code 不得为空
        String code = model.getCode();
        if (CommonUtils.checkStringIsNull(code)) {
            return Boolean.FALSE;
        }

        //startTime 不得为空
        String startTime = model.getStartTime();

        if (!validateTime(startTime)) {
            return Boolean.FALSE;
        }
        // endTime 不得为空
        String endTime = model.getEndTime();

        if (!validateTime(endTime)) {
            return Boolean.FALSE;
        }
        String endSaleTime = model.getEndSaleTime();
        if (null != endSaleTime && "".equals(endSaleTime)) {
            if (!validateTime(endSaleTime)) {
                return Boolean.FALSE;
            }
        }
        // startTime 不得大于等于  endTime
        if (Integer.parseInt(startTime) >= Integer.parseInt(endTime)) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    private Boolean validateTime(String time) {
        if (null == time) {
            return Boolean.FALSE;
        }
        int parseInt = Integer.parseInt(time);
        if (parseInt < 0) {
            return Boolean.FALSE;
        }

        if (parseInt / 100 > 23) {
            return Boolean.FALSE;
        }
        if (parseInt % 100 > 59) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }
}
