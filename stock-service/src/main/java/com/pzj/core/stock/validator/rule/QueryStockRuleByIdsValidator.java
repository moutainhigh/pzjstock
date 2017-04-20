/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.validator.rule;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.enums.StockRuleStateEnum;
import com.pzj.core.stock.exception.errcode.StockRuleExceptionCode;
import com.pzj.core.stock.exception.rule.StockRuleStateIntoException;
import com.pzj.core.stock.model.ParamModel;
import com.pzj.core.stock.model.QueryStockRuleByIdsModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 
 * @author Administrator
 * @version $Id: QueryStockRuleByIdsValidator.java, v 0.1 2016年10月21日 上午10:19:47 Administrator Exp $
 */
@Component("queryStockRuleByIdsValidator")
public class QueryStockRuleByIdsValidator implements ObjectConverter<QueryStockRuleByIdsModel, ServiceContext, ParamModel> {

    /** 
     * @see com.pzj.framework.converter.ObjectConverter#convert(java.lang.Object, java.lang.Object)
     */
    @Override
    public ParamModel convert(QueryStockRuleByIdsModel model, ServiceContext context) {
        ParamModel paramModel = ParamModel.getInstance();
        if (CommonUtils.checkObjectIsNull(model)) {
            paramModel.setErrorModel("根据库存规则ID集合查询库存规则列表传入QueryStockRuleByIdsModel对象为空!");
            return paramModel;
        }
        if (CommonUtils.checkObjectIsNull(model.getStockRuleIds())) {
            paramModel.setErrorModel("根据库存规则ID集合查询库存规则列表传入stockRuleIds库存规则ID集合为空!");
            return paramModel;
        }
        Integer ruleState = model.getState();
        if (!CommonUtils.checkIntegerIsNull(ruleState, true)) {
            boolean flag = true;
            for (StockRuleStateEnum srse : StockRuleStateEnum.values()) {
                if (srse.getState() == ruleState) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                throw new StockRuleStateIntoException(StockRuleExceptionCode.STOCK_RULE_STATE_INTO_ERR_MSG);
            }
        }
        return paramModel;
    }

}
