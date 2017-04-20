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
 * @version $Id: OccupySeatValidator.java, v 0.1 2016年8月6日 上午10:53:02 Administrator Exp $
 */
@Component("occupySeatValidator")
public class OccupySeatValidator implements ObjectConverter<ShowModel, ServiceContext, ParamModel> {

    /** 
     * @see com.pzj.framework.converter.ObjectConverter#convert(java.lang.Object, java.lang.Object)
     */
    @Override
    public ParamModel convert(ShowModel showModel, ServiceContext context) {
        ParamModel paramModel = ParamModel.getInstance();
        if (CommonUtils.checkObjectIsNull(showModel)) {
            paramModel.setErrorModel("占座传入ShowModel对象为空!");
        } else {
            flag: {
                //过滤重复座位
                showModel.setSeats(CommonUtils.filterListRepeat(showModel.getSeats()));

                List<String> seats = showModel.getSeats();

                if (CommonUtils.checkObjectIsNull(seats)) {
                    paramModel.setErrorModel("占座传入seats集合为空!");
                    break flag;
                }

                //判断座位格式是否合法
                if (!CommonUtils.checkSeatIfLegal(seats)) {
                    paramModel.setErrorModel("占座传入seats集合座位不合法!");
                    break flag;
                }

                if (CommonUtils.checkLongIsNull(showModel.getStockId(), true)) {
                    paramModel.setErrorModel("占座传入stockId库存ID为空!");
                    break flag;
                }

                if (CommonUtils.checkStringIsNullStrict(showModel.getTransactionId())) {
                    paramModel.setErrorModel("占座传入transactionId交易ID为空!");
                    break flag;
                }

                if (CommonUtils.checkLongIsNull(showModel.getAreaScreeingsId(), true)) {
                    paramModel.setErrorModel("占座传入areaScreeingsId演艺ID为空!");
                    break flag;
                }

                if (CommonUtils.checkIntegerIsNull(showModel.getOperateBusiness(), true)) {
                    paramModel.setErrorModel("占座传入operateBusiness占座业务类型为空!");
                    break flag;
                }

                OperateSeatBussinessType osbt = OperateSeatBussinessType.getSeatBussinessType(showModel.getOperateBusiness());
                if (osbt.key == OperateSeatBussinessType.ORDER_OCCUPY_SEAT.key) {
                    if (CommonUtils.checkLongIsNull(showModel.getProductId(), true)) {
                        paramModel.setErrorModel("占座传入productId产品ID为空!");
                        break flag;
                    }
                } else if (osbt.key == OperateSeatBussinessType.APPOINMENT_OCCUPY_SEAT.key) {
                    if (CommonUtils.checkLongIsNull(showModel.getUserId(), true)) {
                        paramModel.setErrorModel("预约占座传入userId用户ID为空!");
                        break flag;
                    }
                }

            }
        }
        return paramModel;
    }

}
