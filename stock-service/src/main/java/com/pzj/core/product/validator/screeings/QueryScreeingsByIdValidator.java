package com.pzj.core.product.validator.screeings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.model.ScreeingsQueryRequestModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 占库存接口参数验证器.
 * @author YRJ
 *
 */
@Component(value = "queryScreeingsByIdValidator")
public class QueryScreeingsByIdValidator implements ObjectConverter<ScreeingsQueryRequestModel, ServiceContext, Boolean> {
    private static final Logger logger = LoggerFactory.getLogger(QueryScreeingsByIdValidator.class);

    @Override
    public Boolean convert(ScreeingsQueryRequestModel request, ServiceContext context) {

        if (Check.NuNObject(request, context)) {
            if (logger.isDebugEnabled()) {
                logger.warn("illegal param,request:{},context:{}. ", request, context);
            }
            return Boolean.FALSE;
        }
        Long screeingsId = request.getScreeingsId();
        if (CommonUtils.checkLongIsNull(screeingsId, true)) {
            if (logger.isDebugEnabled()) {
                logger.warn("illegal screeings id {},context:{}.", screeingsId, context);
            }
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}
