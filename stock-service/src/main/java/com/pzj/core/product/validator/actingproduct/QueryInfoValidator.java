package com.pzj.core.product.validator.actingproduct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.model.ActingProductQueryRequstModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 创建演绎产品时收集产品的相关信息验证器
 * @author dongchunfu
 *
 */
@Component(value = "queryInfoValidator")
public class QueryInfoValidator implements ObjectConverter<ActingProductQueryRequstModel, ServiceContext, Boolean> {

    private static final Logger logger = LoggerFactory.getLogger(QueryInfoValidator.class);

    @Override
    public Boolean convert(ActingProductQueryRequstModel model, ServiceContext context) {

        if (Check.NuNObject(model)) {
            logger.error("illegal param,request:{},context:{}. ", model, context);
            return Boolean.FALSE;
        }

        Long supplierId = model.getSupplierId();
        if (CommonUtils.checkLongIsNull(supplierId, true)) {
            logger.error("illegal supplier id,context:{}. ", supplierId, context);
            return Boolean.FALSE;
        }

        Long scenicId = model.getScenicId();
        if (CommonUtils.checkLongIsNull(scenicId, true)) {
            logger.error("illegal scenic id,context:{}. ", scenicId, context);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
