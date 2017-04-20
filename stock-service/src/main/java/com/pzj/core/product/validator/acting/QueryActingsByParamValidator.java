package com.pzj.core.product.validator.acting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.model.ActingQueryRequestModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 占库存接口参数验证器.
 * @author YRJ
 *
 */
@Component(value = "queryActingsByParamValidator")
public class QueryActingsByParamValidator implements ObjectConverter<ActingQueryRequestModel, ServiceContext, Boolean> {

    private static final Logger logger = LoggerFactory.getLogger(QueryActingsByParamValidator.class);

    @Override
    public Boolean convert(ActingQueryRequestModel request, ServiceContext context) {

        if (Check.NuNObject(request, context)) {
            if (logger.isDebugEnabled()) {
                logger.warn("illegal param,request:{},context:{}. ", request, context);
            }
            return Boolean.FALSE;
        }
        if (CommonUtils.checkLongIsNull(request.getSupplierId(), true)) {
            if (logger.isDebugEnabled()) {
                logger.warn("illegal supplier id {}.", request.getSupplierId());
            }
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}
