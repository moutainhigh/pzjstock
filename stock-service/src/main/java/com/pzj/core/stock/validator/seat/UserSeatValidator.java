/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.validator.seat;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.ParamModel;
import com.pzj.core.stock.model.UserSeatModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 
 * @author Administrator
 * @version $Id: UserSceneSeatValidator.java, v 0.1 2016年8月22日 下午5:05:10 Administrator Exp $
 */
@Component("userSeatValidator")
public class UserSeatValidator implements ObjectConverter<UserSeatModel, ServiceContext, ParamModel> {

    /** 
     * @see com.pzj.framework.converter.ObjectConverter#convert(java.lang.Object, java.lang.Object)
     */
    @Override
    public ParamModel convert(UserSeatModel userSeatModel, ServiceContext context) {
        ParamModel paramModel = ParamModel.getInstance();
        if (CommonUtils.checkObjectIsNull(userSeatModel)) {
            paramModel.setErrorModel("查询已经被占的座位集合传入UserSeatModel对象为空!");
            return paramModel;
        }
        if (CommonUtils.checkLongIsNull(userSeatModel.getStockId(), true)) {
            paramModel.setErrorModel("查询已经被占的座位集合传入stockId库存ID为空!");
            return paramModel;
        }
        //        if (CommonUtils.checkLongIsNull(userSeatModel.getOperateUserId(), true)) {
        //            paramModel.setErrorModel("查询已经被占的座位集合传入operateUserId占座用户ID为空!");
        //            return paramModel;
        //        }
        return paramModel;
    }

}
