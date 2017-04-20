package com.pzj.core.product.validator.actingproduct;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.model.ActingProductQueryRequstModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 查询演绎产品关联区域 及场次的相关信息
 *  必填参数：演绎产品ID 即 中间表ID
 * @author dongchunfu
 */
@Component(value = "queryActProInfoValidator")
public class QueryActProInfoValidator implements ObjectConverter<ActingProductQueryRequstModel, ServiceContext, Boolean> {

    @Override
    public Boolean convert(ActingProductQueryRequstModel model, ServiceContext context) {

        if (Check.NuNObject(model)) {
            return Boolean.FALSE;
        }

        Long actProId = model.getActProId();
        if (CommonUtils.checkLongIsNull(actProId, Boolean.TRUE)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
