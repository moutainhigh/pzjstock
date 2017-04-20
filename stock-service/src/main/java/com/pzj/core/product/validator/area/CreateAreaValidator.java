package com.pzj.core.product.validator.area;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.model.AreaModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 创建区域参数验证
 * 
 * @author dongchunfu
 * @version $Id: CreateAreaValidator.java, v 0.1 2016年8月6日 下午2:25:41 dongchunfu Exp $
 */
@Component(value = "createAreaValidator")
public class CreateAreaValidator implements ObjectConverter<AreaModel, ServiceContext, Boolean> {

    @Override
    public Boolean convert(AreaModel model, ServiceContext context) {

        if (Check.NuNObject(model, context)) {
            return Boolean.FALSE;
        }
        if (!validateParam(model)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private Boolean validateParam(AreaModel AreaModel) {
        if (null == AreaModel) {
            return Boolean.FALSE;
        }
        Long scenicId = AreaModel.getScenicId();
        if (CommonUtils.checkLongIsNull(scenicId, true)) {
            return Boolean.FALSE;
        }
        String name = AreaModel.getName();
        if (CommonUtils.checkStringIsNull(name)) {
            return Boolean.FALSE;
        }
        String code = AreaModel.getCode();
        if (CommonUtils.checkStringIsNull(code)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
