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
 * @version $Id: RollbackOccupySeatValidator.java, v 0.1 2016年8月22日 下午3:24:06 Administrator Exp $
 */
@Component("rollbackOccupySeatValidator")
public class RollbackOccupySeatValidator implements ObjectConverter<ShowModel, ServiceContext, ParamModel> {

    /** 
     * @see com.pzj.framework.converter.ObjectConverter#convert(java.lang.Object, java.lang.Object)
     */
    @Override
    public ParamModel convert(ShowModel showModel, ServiceContext context) {
        ParamModel paramModel = ParamModel.getInstance();
        flag: {
            if (CommonUtils.checkObjectIsNull(showModel)) {
                paramModel.setErrorModel("回滚占用的座位传入的ShowModel对象为空!");
                break flag;
            }

            //            if (CommonUtils.checkLongIsNull(showModel.getStockId(), true)) {
            //                paramModel.setErrorModel("回滚占用的座位传入的stockId库存ID为空!");
            //                break flag;
            //            }

            if (CommonUtils.checkStringIsNullStrict(showModel.getTransactionId())) {
                paramModel.setErrorModel("回滚占用的座位传入的transactionId交易ID为空!");
                break flag;
            }

            //            OperateSeatBussinessType osbt = OperateSeatBussinessType.getSeatBussinessType(showModel.getOperateBusiness());
            //            if (osbt.key == OperateSeatBussinessType.ORDER_OCCUPY_SEAT.key) {
            //                if (CommonUtils.checkLongIsNull(showModel.getProductId(), true)) {
            //                    paramModel.setErrorModel("回滚占用的座位传入的productId产品ID为空!");
            //                    break flag;
            //                }
            //            } else if (osbt.key == OperateSeatBussinessType.APPOINMENT_OCCUPY_SEAT.key) {
            //                if (CommonUtils.checkLongIsNull(showModel.getUserId(), true)) {
            //                    paramModel.setErrorModel("回滚预约占用的座位传入的userId用户ID为空!");
            //                    break flag;
            //                }
            //            }

        }
        return paramModel;
    }

}
