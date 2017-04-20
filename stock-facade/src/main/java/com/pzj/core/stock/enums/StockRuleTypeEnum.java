/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.enums;

import com.pzj.core.stock.exception.errcode.StockRuleExceptionCode;
import com.pzj.core.stock.exception.rule.StockRuleTypeException;

/**
 * 
 * @author Administrator
 * @version $Id: StockRuleTypeEnum.java, v 0.1 2016年8月3日 下午6:01:18 Administrator Exp $
 */
public enum StockRuleTypeEnum {
    /**
     * 单一库存
     */
    SINGLE(1, "单一库存"),
    /**
     * 每日库存
     */
    DAILY(2, "每日库存");

    private int    ruleType;
    private String ruleName;

    private StockRuleTypeEnum(int ruleType, String ruleName) {
        this.ruleType = ruleType;
        this.ruleName = ruleName;
    }

    public int getRuleType() {
        return ruleType;
    }

    public String getRuleName() {
        return ruleName;
    }

    public static StockRuleTypeEnum getStockRuleTypeEnum(int ruleCode) {
        for (StockRuleTypeEnum srte : StockRuleTypeEnum.values()) {
            if (ruleCode == srte.getRuleType()) {
                return srte;
            }
        }
        throw new StockRuleTypeException(StockRuleExceptionCode.RETURN_TYPE_ERR_MSG);
    }

    public static String getRuleName(int ruleCode) {
        for (StockRuleTypeEnum srte : StockRuleTypeEnum.values()) {
            if (ruleCode == srte.getRuleType()) {
                return srte.getRuleName();
            }
        }
        throw new StockRuleTypeException(StockRuleExceptionCode.RETURN_TYPE_ERR_MSG);
    }
}
