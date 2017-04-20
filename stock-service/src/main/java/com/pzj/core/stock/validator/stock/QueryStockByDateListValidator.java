/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.validator.stock;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.ParamModel;
import com.pzj.core.stock.model.StockDateListQueryModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 
 * @author Administrator
 * @version $Id: QueryStockByDateListValidator.java, v 0.1 2016年8月29日 下午1:48:54 Administrator Exp $
 */
@Component("queryStockByDateListValidator")
public class QueryStockByDateListValidator implements ObjectConverter<StockDateListQueryModel, ServiceContext, ParamModel> {

    /** 
     * @see com.pzj.framework.converter.ObjectConverter#convert(java.lang.Object, java.lang.Object)
     */
    @Override
    public ParamModel convert(StockDateListQueryModel model, ServiceContext context) {
        ParamModel paramModel = ParamModel.getInstance();
        if (CommonUtils.checkObjectIsNull(model)) {
            paramModel.setErrorModel("查询库存通过日期集合传入StockDateListQueryModel对象为空!");
            return paramModel;
        }
        if (CommonUtils.checkLongIsNull(model.getStockRuleId(), true)) {
            paramModel.setErrorModel("查询库存通过日期集合传入stockRuleId库存规则ID为空!");
            return paramModel;
        }
        if (CommonUtils.checkObjectIsNull(model.getStockTimeList())) {
            paramModel.setErrorModel("查询库存通过日期集合传入stockTimeList日期对象集合为空!");
            return paramModel;
        }

        //校验日期格式是否合法
        int i = 0;
        for (String stockTime : model.getStockTimeList()) {
            model.getStockTimeList().set(i, CommonUtils.checkStockTime(stockTime));
            i++;
        }

        return paramModel;
    }

}
