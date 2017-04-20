/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.validator.seat;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.ParamModel;
import com.pzj.core.stock.model.ShowModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 
 * @author Administrator
 * @version $Id: RandomAssignSeatValidator.java, v 0.1 2016年8月9日 上午10:54:21 Administrator Exp $
 */
@Component("randomAssignSeatValidator")
public class RandomAssignSeatValidator implements ObjectConverter<ShowModel, ServiceContext, ParamModel> {

    /** 
     * @see com.pzj.framework.converter.ObjectConverter#convert(java.lang.Object, java.lang.Object)
     */
    @Override
    public ParamModel convert(ShowModel showModel, ServiceContext context) {
        ParamModel paramModel = ParamModel.getInstance();
        if (CommonUtils.checkObjectIsNull(showModel)) {
            paramModel.setErrorModel("随机配座传入ShowModel对象为空!");
            return paramModel;
        }

        if (CommonUtils.checkIntegerIsNull(showModel.getRandomNum(), true)) {
            paramModel.setErrorModel("随机配座传入randomNum动态配坐数量为空!");
            return paramModel;
        }
        if (CommonUtils.checkLongIsNull(showModel.getStockId(), true)) {
            paramModel.setErrorModel("随机配座传入stockId库存ID为空!");
            return paramModel;
        }
        if (CommonUtils.checkLongIsNull(showModel.getAreaScreeingsId(), true)) {
            paramModel.setErrorModel("随机配座传入areaScreeingsId产品对应区域场次关联演艺ID为空!");
            return paramModel;
        }

        return paramModel;
    }
}
