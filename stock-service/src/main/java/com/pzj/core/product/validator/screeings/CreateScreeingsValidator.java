package com.pzj.core.product.validator.screeings;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.common.exception.ParameterErrorException;
import com.pzj.core.product.event.ValidateScreeingsInsertParam;
import com.pzj.core.product.model.ScreeingsModel;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 占库存接口参数验证器.
 * @author YRJ
 *
 */
@Component(value = "createScreeingsValidator")
public class CreateScreeingsValidator implements ObjectConverter<ScreeingsModel, ServiceContext, Boolean> {

    private static final Logger          logger = LoggerFactory.getLogger(CreateScreeingsValidator.class);

    @Resource(name = "validateScreeingsInsertParam")
    private ValidateScreeingsInsertParam validateScreeingsInsertParam;

    @Override
    public Boolean convert(ScreeingsModel model, ServiceContext context) {

        if (Check.NuNObject(model, context)) {
            return Boolean.FALSE;
        }
        try {
            if (!validateScreeingsInsertParam.validateParam(model)) {
                return Boolean.FALSE;
            }
        } catch (Throwable t) {
            logger.error(" create Screeings time error,context:{}. ", context, t);
            throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG, t);
        }
        return Boolean.TRUE;
    }

}
