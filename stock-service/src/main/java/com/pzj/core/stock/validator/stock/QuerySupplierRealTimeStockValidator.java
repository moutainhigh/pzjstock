/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.validator.stock;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.ParamModel;
import com.pzj.core.stock.model.StockRealTimeQueryModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 
 * @author Administrator
 * @version $Id: QuerySupplierRealTimeStockValidator.java, v 0.1 2016年10月17日 下午2:31:37 Administrator Exp $
 */
@Component("querySupplierRealTimeStockValidator")
public class QuerySupplierRealTimeStockValidator implements ObjectConverter<StockRealTimeQueryModel, ServiceContext, ParamModel> {

    /** 
     * @see com.pzj.framework.converter.ObjectConverter#convert(java.lang.Object, java.lang.Object)
     */
    @Override
    public ParamModel convert(StockRealTimeQueryModel model, ServiceContext context) {
        ParamModel paramModel = ParamModel.getInstance();
        if (CommonUtils.checkObjectIsNull(model)) {
            paramModel.setErrorModel("查询供应商实时库存列表传入StockRealTimeQueryModel对象为空!");
            return paramModel;
        }
        if (CommonUtils.checkLongIsNull(model.getSupplierId(), true)) {
            paramModel.setErrorModel("查询供应商实时库存列表传入supplierId供应商ID为空!");
            return paramModel;
        }
        if (CommonUtils.checkObjectIsNull(model.getStockDate())) {
            paramModel.setErrorModel("查询供应商实时库存列表传入stockDate库存日期为空!");
            return paramModel;
        }
        if (CommonUtils.checkObjectIsNull(model.getStockRuleIdList())) {
            paramModel.setErrorModel("查询供应商实时库存列表传入stockRuleIdList库存规则ID集合为空!");
            return paramModel;
        }
        return paramModel;
    }

}
