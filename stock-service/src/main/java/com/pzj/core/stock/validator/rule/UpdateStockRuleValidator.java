package com.pzj.core.stock.validator.rule;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.StockRuleModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 更新库存规则基础验证
 * @author YRJ
 *
 */
@Component(value = "updateStockRuleValidator")
public class UpdateStockRuleValidator implements ObjectConverter<StockRuleModel, ServiceContext, Boolean> {

    @Override
    public Boolean convert(StockRuleModel model, ServiceContext context) {

        if (Check.NuNObject(model)) {
            return Boolean.FALSE;
        }

        if (CommonUtils.checkLongIsNull(model.getId(), true)) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }
}
