package com.pzj.core.stock.validator.rule;

import org.springframework.stereotype.Component;

import com.pzj.core.stock.enums.StockStateEnum;
import com.pzj.core.stock.model.StockRuleModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 占库存接口参数验证器.
 * @author YRJ
 *
 */
@Component(value = "updateStockRuleStateValidator")
public class UpdateStockRuleStateValidator implements ObjectConverter<StockRuleModel, ServiceContext, Boolean> {

    @Override
    public Boolean convert(StockRuleModel model, ServiceContext context) {

        if (Check.NuNObject(model)) {
            return Boolean.FALSE;
        }
        if (null == model.getId() || model.getId() < 1) {
            return Boolean.FALSE;
        }
        Integer state = model.getState();
        if (null == state) {
            return Boolean.FALSE;
        }
        if (null != state) {
            if (StockStateEnum.AVAILABLE.getState() != state && StockStateEnum.DISABLED.getState() != state) {
                return Boolean.FALSE;//状态未按约定值传递
            }
        }
        return Boolean.TRUE;
    }
}
