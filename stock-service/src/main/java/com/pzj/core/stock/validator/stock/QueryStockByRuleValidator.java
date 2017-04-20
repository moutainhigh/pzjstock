/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.validator.stock;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.ParamModel;
import com.pzj.core.stock.model.StockQueryRequestModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 
 * @author Administrator
 * @version $Id: QueryStockByRuleValidator.java, v 0.1 2016年9月9日 下午2:54:56 Administrator Exp $
 */
@Component("queryStockByRuleValidator")
public class QueryStockByRuleValidator implements ObjectConverter<StockQueryRequestModel, ServiceContext, ParamModel> {

    /** 
     * @see com.pzj.framework.converter.ObjectConverter#convert(java.lang.Object, java.lang.Object)
     */
    @Override
    public ParamModel convert(StockQueryRequestModel model, ServiceContext context) {
        ParamModel paramModel = ParamModel.getInstance();
        if (CommonUtils.checkObjectIsNull(model)) {
            paramModel.setErrorModel("查询库存传入StockQueryRequestModel对象为空!");
            return paramModel;
        }
        if (CommonUtils.checkLongIsNull(model.getRuleId(), true)) {
            paramModel.setErrorModel("查询库存传入ruleId库存规则ID为空!");
            return paramModel;
        }
        return paramModel;
    }

}
