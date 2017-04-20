/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.enums;

import com.pzj.core.stock.exception.errcode.StockRuleExceptionCode;
import com.pzj.core.stock.exception.rule.StockRuleCategoryException;

/**
 * 
 * @author Administrator
 * @version $Id: StockRuleTypeEnum.java, v 0.1 2016年8月3日 下午6:01:18 Administrator Exp $
 */
public enum StockRuleCategoryEnum {
    /** 通用*/
    COMMON(1, "通用"),
    /** 景区*/
    SCENIC(2, "景区"),
    /** 演艺*/
    ACTING(3, "演艺"),
    /** 住宿*/
    HOTEL(4, "住宿"),
    /** 一日游*/
    ONE_DAY_TOUR(5, "一日游"),
    /** 交通*/
    TRAFFIC(6, "交通"),
    /** 导游*/
    GUIDE(7, "导游"),
    /** 特产*/
    SPECIALTY(8, "特产");

    private int    categoryCode;
    private String categoryName;

    private StockRuleCategoryEnum(int categoryCode, String categoryName) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public static String getCategoryName(int categoryCode) {
        for (StockRuleCategoryEnum srte : StockRuleCategoryEnum.values()) {
            if (categoryCode == srte.getCategoryCode()) {
                return srte.getCategoryName();
            }
        }
        throw new StockRuleCategoryException(StockRuleExceptionCode.CATEGORY_NOTEXIST_ERR_MSG);
    }
}
