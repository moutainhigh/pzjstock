/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.stock.entity.StockRule;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.model.StockRuleModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 库存规则转换类
 * @author dongchunfu
 * @version $Id: StockRuleConverter.java, v 0.1 2016年7月22日 下午7:32:27 dongchunfu Exp $
 */
@Component(value = "stockRulesConverter")
public class StockRulesConverter implements ObjectConverter<List<StockRule>, ServiceContext, ArrayList<StockRuleModel>> {

    private static final Logger logger = LoggerFactory.getLogger(StockRulesConverter.class);

    /**底层库存规则  转换为  库存规则VO*/
    @Override
    public ArrayList<StockRuleModel> convert(List<StockRule> list, ServiceContext context) {

        if (null == list || list.size() == 0) {
            return null;
        }
        ArrayList<StockRuleModel> vos = new ArrayList<StockRuleModel>();
        for (StockRule rule : list) {
            StockRuleModel model = new StockRuleModel();
            try {
                PropertyUtils.copyProperties(model, rule);
            } catch (Exception e) {
                logger.error(" copy stock rule properties to stock rule model error ", e);
                throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
            }
            vos.add(model);
        }
        return vos;

    }
}