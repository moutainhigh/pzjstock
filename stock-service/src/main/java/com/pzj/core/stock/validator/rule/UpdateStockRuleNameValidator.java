/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.validator.rule;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.ParamModel;
import com.pzj.core.stock.model.StockRuleModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 
 * @author Administrator
 * @version $Id: QueryStockRuleByIdsValidator.java, v 0.1 2016年10月21日 上午10:19:47 Administrator Exp $
 */
@Component("updateStockRuleNameValidator")
public class UpdateStockRuleNameValidator implements ObjectConverter<StockRuleModel, ServiceContext, ParamModel> {

    /** 
     * @see com.pzj.framework.converter.ObjectConverter#convert(java.lang.Object, java.lang.Object)
     */
    @Override
    public ParamModel convert(StockRuleModel model, ServiceContext context) {
        ParamModel paramModel = ParamModel.getInstance();
        if (CommonUtils.checkObjectIsNull(model)) {
            paramModel.setErrorModel("根据库存规则ID更新库存规则name传入StockRuleModel对象为空!");
            return paramModel;
        }
        if (CommonUtils.checkLongIsNull(model.getId(), true)) {
            paramModel.setErrorModel("根据库存规则ID更新库存规则name传入库存规则ID为空!");
            return paramModel;
        }
        if (Check.NuNStrStrict(model.getName())) {
            paramModel.setErrorModel("据库存规则ID更新库存规则name传入修改库存规则名称不允许为空");
            return paramModel;
        }
        return paramModel;
    }

}
