/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.ruleupdate;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.common.utils.MFBeanCopier;
import com.pzj.core.common.utils.StockRuleConstant;
import com.pzj.core.stock.entity.StockRule;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.model.StockRuleModel;
import com.pzj.core.stock.write.StockRuleWriteMapper;
import com.pzj.framework.context.ServiceContext;

/**
 * 创建库存规则引擎
 * @author dongchunfu
 * @version $Id: CreateStockRuleEngine.java, v 0.1 2016年8月4日 下午2:03:42 dongchunfu Exp $
 */
@Component(value = "createStockRuleEngine")
@Transactional(value = "stock.transactionManager")
public class CreateStockRuleEngine {

    private static final Logger  logger = LoggerFactory.getLogger(CreateStockRuleEngine.class);

    @Resource
    private StockRuleWriteMapper stockRuleWriteMapper;

    public Long createStockRule(StockRuleModel model, ServiceContext context) {

        defaultValue(model);//赋予默认值
        StockRule rule = new StockRule();//实体转换
        try {
            MFBeanCopier.copyProperties(rule, model);
        } catch (Exception e) {
            logger.error(" copy stock rule model properties to stock rule error ", e);
            throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
        }

        stockRuleWriteMapper.insertStockRule(rule);
        return rule.getId();
    }

    //为库存规则赋予默认值操作
    private void defaultValue(StockRuleModel vo) {
        if (null == vo.getType())//默认设置库存类型为日库存类型
            vo.setType(StockRuleConstant.StockRuleType.DAILY);
        if (null == vo.getState())//设置默认状态为启用状态
            vo.setState(StockRuleConstant.StockRuleState.NORMAL);
        if (null == vo.getWhetherReturn())//设置默认归还规则为不需归还
            vo.setWhetherReturn(StockRuleConstant.WhetherReturn.NORETURN);
    }
}
