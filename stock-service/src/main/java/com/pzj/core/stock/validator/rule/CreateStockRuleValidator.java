package com.pzj.core.stock.validator.rule;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.StockRuleConstant;
import com.pzj.core.stock.model.StockRuleModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 占库存接口参数验证器.
 * @author YRJ
 *
 */
@Component(value = "createStockRuleValidator")
public class CreateStockRuleValidator implements ObjectConverter<StockRuleModel, ServiceContext, Boolean> {

    @Override
    public Boolean convert(StockRuleModel model, ServiceContext context) {

        if (Check.NuNObject(model)) {
            return Boolean.FALSE;
        }

        if (null == model.getSupplierId() || model.getSupplierId() < 1) {
            return Boolean.FALSE;
        }
        if (null == model.getName() || "".equals(model.getName())) {
            return Boolean.FALSE;
        }
        if (null == model.getCategory()) {
            return Boolean.FALSE;
        }
        if (null == model.getUpperLimit() || model.getUpperLimit() < 1) {
            return Boolean.FALSE;
        }
        if (null != model.getType()) {
            if (1 != model.getType() && 2 != model.getType()) {
                return Boolean.FALSE;//类型未按约定值传递
            }
        }
        if (null != model.getState()) {
            if (1 != model.getState() && 2 != model.getState()) {
                return Boolean.FALSE;//状态未按约定值传递
            }
        }
        if (null != model.getWhetherReturn()) {
            if (1 != model.getWhetherReturn() && 2 != model.getWhetherReturn()) {
                return Boolean.FALSE;//是否归还未按约定值传递
            }

            //为日库存类型时不能选取归还库存操作
            if (model.getType() == StockRuleConstant.StockRuleType.DAILY || null == model.getType()) {
                if (model.getWhetherReturn() == StockRuleConstant.WhetherReturn.RETURN) {
                    return Boolean.FALSE;//只有当库存类型为单一库存时
                }
            }
        }
        return Boolean.TRUE;
    }

}
