package com.pzj.core.product.validator.acting;

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
@Component(value = "queryActingByIdValidator")
public class QueryActingByIdValidator implements ObjectConverter<ActingQueryRequestModel, ServiceContext, Boolean> {

    @Override
    public Boolean convert(ActingQueryRequestModel request, ServiceContext context) {

        if (Check.NuNObject(request)) {
            return Boolean.FALSE;
        }
        if (CommonUtils.checkLongIsNull(request.getActingId(), true)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}
