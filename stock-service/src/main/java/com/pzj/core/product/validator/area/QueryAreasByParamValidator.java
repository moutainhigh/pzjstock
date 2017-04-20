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
@Component(value = "queryAreasByParamValidator")
public class QueryAreasByParamValidator implements ObjectConverter<AreaQueryRequestModel, ServiceContext, Boolean> {

    private static final Logger logger = LoggerFactory.getLogger(QueryAreasByParamValidator.class);

    @Override
    public Boolean convert(AreaQueryRequestModel request, ServiceContext context) {

        if (Check.NuNObject(request, context)) {
            if (logger.isDebugEnabled()) {
                logger.warn("illegal param,request:{},context:{}. ", request, context);
            }
            return Boolean.FALSE;
        }
        Long scenicId = request.getScenicId();
        if (CommonUtils.checkLongIsNull(scenicId, true)) {
            if (logger.isDebugEnabled()) {
                logger.warn(" illegal scenic id : {}.", scenicId);
            }
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}
