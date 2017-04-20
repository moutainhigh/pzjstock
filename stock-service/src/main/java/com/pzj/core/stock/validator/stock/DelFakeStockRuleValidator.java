package com.pzj.core.stock.validator.stock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.StockRuleModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 查询库存参数验证器.
 * @author dongchunfu
 *
 */
@Component(value = "delFakeStockRuleValidator")
public class DelFakeStockRuleValidator implements ObjectConverter<StockRuleModel, ServiceContext, Boolean> {

    private static final Logger logger = LoggerFactory.getLogger(DelFakeStockRuleValidator.class);

    @Override
    public Boolean convert(StockRuleModel model, ServiceContext context) {
        if (Check.NuNObject(model, context)) {
            if (logger.isDebugEnabled()) {
                logger.debug("illeage param ,request:{},context:{}.", model, context);
            }
            return Boolean.FALSE;
        }
        Long id = model.getId();
        if (CommonUtils.checkLongIsNull(id, true)) {
            if (logger.isDebugEnabled()) {
                logger.debug("illeage param ,id:{},context:{}.", id, context);
            }
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
