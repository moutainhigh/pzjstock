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
 * 更新演绎验证器
 * 
 * @author dongchunfu
 * @version $Id: UpdateAreaValidator.java, v 0.1 2016年8月5日 下午2:32:51 dongchunfu Exp $
 */
@Component(value = "updateActingValidator")
public class UpdateActingValidator implements ObjectConverter<ActingModel, ServiceContext, Boolean> {

    private static final Logger logger = LoggerFactory.getLogger(UpdateActingValidator.class);

    @Override
    public Boolean convert(ActingModel model, ServiceContext context) {

        if (Check.NuNObject(model, context)) {
            if (logger.isDebugEnabled()) {
                logger.debug("illegal param ,request:{},context:{}.", model, context);
            }
            return Boolean.FALSE;
        }
        Long id = model.getId();
        if (CommonUtils.checkLongIsNull(id, true)) {
            if (logger.isDebugEnabled()) {
                logger.debug("illegal param ,id:{},context:{}.", id, context);
            }
            return Boolean.FALSE;
        }

        Integer state = model.getState();
        if (state != ActingStateEnum.AVAILABLE.getState() && state != ActingStateEnum.DISABLED.getState()) {
            if (logger.isDebugEnabled()) {
                logger.debug(" illeagal param ,state:{},context:{}.", state, context);
            }
            return Boolean.FALSE;
        }
        String name = model.getName();
        if (name != null) {
            int length = name.length();
            if (length > PramLengthEnum.NAME.getLength()) {
                if (logger.isDebugEnabled()) {
                    logger.debug(" param name length ,length:{},context:{}.", length, context);
                }
                return Boolean.FALSE;
            }
        }

        if (!whetherNeedUpdate(model)) {
            if (logger.isDebugEnabled()) {
                logger.debug(" param is null ,request:{},context:{}.", id, context);
            }
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    private Boolean whetherNeedUpdate(ActingModel model) {

        Boolean flag = Boolean.FALSE;

        Long scenicId = model.getScenicId();
        if (!CommonUtils.checkLongIsNull(scenicId, Boolean.TRUE)) {
            flag = Boolean.TRUE;
        }

        String name = model.getName();
        if (!CommonUtils.checkStringIsNull(name)) {
            flag = Boolean.TRUE;
        }

        Integer state = model.getState();
        if (!CommonUtils.checkIntegerIsNull(state, Boolean.TRUE)) {
            flag = Boolean.FALSE;
        }

        String remarks = model.getRemarks();
        if (CommonUtils.checkStringIsNull(remarks)) {
            flag = Boolean.TRUE;
        }

        Integer whetherNeedSeat = model.getWhetherNeedSeat();
        if (whetherNeedSeat != WhetherSelectSeatEnum.NEED.getSelect() && whetherNeedSeat != WhetherSelectSeatEnum.NOT_NEED.getSelect()) {
            flag = Boolean.TRUE;
        }
        return flag;
    }
}
