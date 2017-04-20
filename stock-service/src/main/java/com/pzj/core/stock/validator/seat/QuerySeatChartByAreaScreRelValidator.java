/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.validator.seat;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.ParamModel;
import com.pzj.core.stock.model.SeatChartModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 
 * @author Administrator
 * @version $Id: QuerySeatChartByAreaScreRelValidator.java, v 0.1 2016年9月9日 下午3:18:44 Administrator Exp $
 */
@Component("querySeatChartByAreaScreRelValidator")
public class QuerySeatChartByAreaScreRelValidator implements ObjectConverter<SeatChartModel, ServiceContext, ParamModel> {

    /** 
     * @see com.pzj.framework.converter.ObjectConverter#convert(java.lang.Object, java.lang.Object)
     */
    @Override
    public ParamModel convert(SeatChartModel model, ServiceContext context) {
        ParamModel paramModel = ParamModel.getInstance();
        if (CommonUtils.checkObjectIsNull(model)) {
            paramModel.setErrorModel("查询座位图（通过产品关联区域场次演艺ID）传入参数SeatChartModel对象为空");
            return paramModel;
        }
        if (CommonUtils.checkLongIsNull(model.getAreaScreeningsId(), true)) {
            paramModel.setErrorModel("查询座位图（通过产品关联区域场次演艺ID）传入参数areaScreeningsId区域场次关联ID为空");
            return paramModel;
        }
        return paramModel;
    }

}
