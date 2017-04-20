package com.pzj.core.product.validator.screeings;

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
@Component(value = "queryScreeingsByParamValidator")
public class QueryScreeingsByParamValidator implements ObjectConverter<ScreeingsQueryRequestModel, ServiceContext, Boolean> {

    @Override
    public Boolean convert(ScreeingsQueryRequestModel request, ServiceContext context) {
        if (Check.NuNObject(request)) {
            return Boolean.FALSE;
        }
        Long scenicId = request.getScenicId();
        if (CommonUtils.checkLongIsNull(scenicId, Boolean.TRUE)) {
            return Boolean.FALSE;
        }

        String startTime = request.getStartTime();
        if (null != startTime) {
            if (!validateTime(startTime)) {
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }

    private Boolean validateTime(String time) {
        if (null == time) {
            return Boolean.FALSE;
        }
        int parseInt = Integer.parseInt(time);
        if (parseInt < 0) {
            return Boolean.FALSE;
        }

        if (parseInt / 100 > 23) {
            return Boolean.FALSE;
        }
        if (parseInt % 100 > 59) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

}
