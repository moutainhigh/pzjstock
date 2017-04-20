/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.validator.seat;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.enums.OperateSeatBussinessType;
import com.pzj.core.stock.model.ParamModel;
import com.pzj.core.stock.model.ShowModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 
 * @author Administrator
 * @version $Id: ReleaseSeatValidator.java, v 0.1 2016年8月6日 下午6:41:45 Administrator Exp $
 */
@Component(value = "releaseSeatValidator")
public class ReleaseSeatValidator implements ObjectConverter<ShowModel, ServiceContext, ParamModel> {

    /** 
     * @see com.pzj.framework.converter.ObjectConverter#convert(java.lang.Object, java.lang.Object)
     */

    @Override
    public ParamModel convert(ShowModel showModel, ServiceContext context) {
        ParamModel paramModel = ParamModel.getInstance();
        if (CommonUtils.checkObjectIsNull(showModel)) {
            paramModel.setErrorModel("释放座位传入ShowModel对象为空!");
            return paramModel;
        }

        if (CommonUtils.checkStringIsNullStrict(showModel.getTransactionId())) {
            paramModel.setErrorModel("释放座位传入transactionId交易ID为空!");
            return paramModel;
        }

        if (CommonUtils.checkLongIsNull(showModel.getStockId(), true)) {
            paramModel.setErrorModel("释放座位传入stockId库存ID为空!");
            return paramModel;
        }

        if (CommonUtils.checkLongIsNull(showModel.getAreaScreeingsId(), true)) {
            paramModel.setErrorModel("释放座位传入areaScreeingsId演艺ID为空!");
            return paramModel;
        }

        //过滤重复座位
        showModel.setSeats(CommonUtils.filterListRepeat(showModel.getSeats()));

        List<String> seats = showModel.getSeats();

        if (CommonUtils.checkObjectIsNull(seats)) {
            paramModel.setErrorModel("释放座位传入seats座位集合对象为空!");
            return paramModel;
        }

        //判断座位格式是否合法
        if (!CommonUtils.checkSeatIfLegal(seats)) {
            paramModel.setErrorModel("释放座位传入seats座位不合法!");
            return paramModel;
        }

        if (CommonUtils.checkIntegerIsNull(showModel.getOperateBusiness(), true)) {
            paramModel.setErrorModel("释放座位传入operateBusiness释放座位业务类型为空!");
            return paramModel;
        }

        OperateSeatBussinessType osbt = OperateSeatBussinessType.getSeatBussinessType(showModel.getOperateBusiness());
        if (osbt.key == OperateSeatBussinessType.APPOINMENT_OCCUPY_SEAT.key) {
            if (CommonUtils.checkLongIsNull(showModel.getUserId(), true)) {
                paramModel.setErrorModel("释放预约座位传入userId用户ID为空!");
                return paramModel;
            }
        }

        return paramModel;
    }
}
