package com.pzj.core.stock.validator.rule;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.enums.StockReturnTypeEnum;
import com.pzj.core.stock.enums.StockRuleTypeEnum;
import com.pzj.core.stock.enums.StockStateEnum;
import com.pzj.core.stock.model.StockRuleModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 库存规则已绑定产品时只允许修改 库存上限，名称
 * @author YRJ
 *
 */
@Component(value = "updateStockRuleNotBindValidator")
public class UpdateStockRuleNotBindValidator implements ObjectConverter<StockRuleModel, ServiceContext, Boolean> {

    @Override
    public Boolean convert(StockRuleModel model, ServiceContext context) {

        if (Check.NuNObject(model)) {
            return Boolean.FALSE;
        }

        if (CommonUtils.checkLongIsNull(model.getId(), true)) {
            return Boolean.FALSE;
        }
        Integer upperLimit = model.getUpperLimit();
        if (null != upperLimit && upperLimit < 1) {
            return Boolean.FALSE;
        }
        Integer type = model.getType();
        if (null != type) {
            if (StockRuleTypeEnum.SINGLE.getRuleType() != type.intValue() && StockRuleTypeEnum.DAILY.getRuleType() != type.intValue())
                return Boolean.FALSE;
        }
        Integer state = model.getState();
        if (null != state) {
            if (StockStateEnum.AVAILABLE.getState() != state.intValue() && StockStateEnum.DISABLED.getState() != state.intValue())
                return Boolean.FALSE;
        }
        Integer ifreturn = model.getWhetherReturn();
        if (null != ifreturn) {
            if (StockReturnTypeEnum.NEED_RETURN.getReturnType() != ifreturn.intValue()
                && StockReturnTypeEnum.NOT_NEED_RETURN.getReturnType() != ifreturn.intValue())
                return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
