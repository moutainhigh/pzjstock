package com.pzj.core.stock.validator.rule;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.StockRuleModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 库存规则已绑定产品时只允许修改 库存上限，名称
 * @author YRJ
 *
 */
@Component(value = "updateStockRuleYetBindValidator")
public class UpdateStockRuleYetBindValidator implements ObjectConverter<StockRuleModel, ServiceContext, Boolean> {

    @Override
    public Boolean convert(StockRuleModel model, ServiceContext context) {

        if (Check.NuNObject(model)) {
            return Boolean.FALSE;
        }
        Long id = model.getId();
        if (CommonUtils.checkLongIsNull(id, Boolean.TRUE)) {
            return Boolean.FALSE;
        }
        //        Integer upperLimit = model.getUpperLimit();
        //        if (CommonUtils.checkIntegerIsNull(upperLimit, Boolean.TRUE)) {
        //            return Boolean.FALSE;
        //        }
        return Boolean.TRUE;
    }
}
