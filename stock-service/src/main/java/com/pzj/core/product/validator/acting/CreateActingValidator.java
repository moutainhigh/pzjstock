package com.pzj.core.product.validator.acting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.enums.ActingStateEnum;
import com.pzj.core.product.enums.PramLengthEnum;
import com.pzj.core.product.enums.WhetherSelectSeatEnum;
import com.pzj.core.product.model.ActingModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 占库存接口参数验证器.
 * @author YRJ
 *
 */
@Component(value = "createActingValidator")
public class CreateActingValidator implements ObjectConverter<ActingModel, ServiceContext, Boolean> {
    private static final Logger logger = LoggerFactory.getLogger(CreateActingValidator.class);

    @Override
    public Boolean convert(ActingModel model, ServiceContext context) {

        if (Check.NuNObject(model, context)) {
            if (logger.isDebugEnabled()) {
                logger.debug("error param ,model:{},context:{}", model, context);
            }
            return Boolean.FALSE;
        }
        if (!validateParam(model, context)) {
            if (logger.isDebugEnabled()) {
                logger.debug("error param ,model:{},context:{}", model, context);
            }
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    //参数验证
    private Boolean validateParam(ActingModel model, ServiceContext context) {

        Long supplierId = model.getSupplierId();
        if (CommonUtils.checkLongIsNull(supplierId, true)) {
            if (logger.isDebugEnabled()) {
                logger.debug("error param supplierId:{}, ,model:{},context:{}", supplierId, model, context);
            }
            return Boolean.FALSE;
        }

        Long scenicId = model.getScenicId();
        if (CommonUtils.checkLongIsNull(scenicId, true)) {
            if (logger.isDebugEnabled()) {
                logger.debug("error param scenicId:{}, ,model:{},context:{}", scenicId, model, context);
            }
            return Boolean.FALSE;
        }

        String name = model.getName();
        if (!CommonUtils.checkStrLengthAndNull(name, PramLengthEnum.NAME.getLength())) {
            if (logger.isDebugEnabled()) {
                logger.debug("error param name:{}, ,model:{},context:{}", name, model, context);
            }
            return Boolean.FALSE;
        }
        Integer state = model.getState();
        if (null != state) {
            if (ActingStateEnum.AVAILABLE.getState() != state && ActingStateEnum.DISABLED.getState() != state) {
                if (logger.isDebugEnabled()) {
                    logger.debug("error param state:{}, ,model:{},context:{}", state, model, context);
                }
                return Boolean.FALSE;//状态未按约定值传递
            }
        }
        Integer whetherNeedSeat = model.getWhetherNeedSeat();
        if (null != whetherNeedSeat) {
            if (WhetherSelectSeatEnum.NEED.getSelect() != whetherNeedSeat && WhetherSelectSeatEnum.NOT_NEED.getSelect() != whetherNeedSeat) {
                if (logger.isDebugEnabled()) {
                    logger.debug("error param whetherNeedSeat:{}, ,model:{},context:{}", whetherNeedSeat, model, context);
                }
                return Boolean.FALSE;//状态未按约定值传递
            }
        }
        return Boolean.TRUE;
    }
}
