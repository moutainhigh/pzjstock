package com.pzj.core.product.validator.area;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.model.AreaQueryRequestModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 占库存接口参数验证器.
 * @author YRJ
 *
 */
@Component(value = "queryAreasByIdValidator")
public class QueryAreasByIdValidator implements ObjectConverter<AreaQueryRequestModel, ServiceContext, Boolean> {

    private static final Logger logger = LoggerFactory.getLogger(QueryAreasByIdValidator.class);

    @Override
    public Boolean convert(AreaQueryRequestModel request, ServiceContext context) {

        if (Check.NuNObject(request, context)) {
            if (logger.isDebugEnabled()) {
                logger.warn("illegal param,request:{},context:{}. ", request, context);
            }
            return Boolean.FALSE;
        }
        if (CommonUtils.checkLongIsNull(request.getAreaId(), true)) {
            if (logger.isDebugEnabled()) {
                logger.warn("illegal area id {}.", request.getAreaId());
            }
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}
