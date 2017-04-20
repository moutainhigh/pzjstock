/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.enums;

import com.pzj.core.stock.exception.errcode.StockRuleExceptionCode;
import com.pzj.core.stock.exception.rule.StockRuleTypeException;

/**
 * 
 * @author Administrator
 * @version $Id: StockRuleTypeEnum.java, v 0.1 2016年8月3日 下午6:01:18 Administrator Exp $
 */
public enum WhetherSelectSeatEnum {
                                   /**
                                    * 需要选择
                                    */
                                   NEED(1),
                                   /**
                                    * 不需选座
                                    */
                                   NOT_NEED(2);

    private int select;

    private WhetherSelectSeatEnum(int select) {
        this.select = select;
    }

    public int getSelect() {
        return select;
    }

    public static WhetherSelectSeatEnum getStockRuleTypeEnum(int select) {
        for (WhetherSelectSeatEnum srte : WhetherSelectSeatEnum.values()) {
            if (select == srte.getSelect()) {
                return srte;
            }
        }
        throw new StockRuleTypeException(StockRuleExceptionCode.RETURN_TYPE_ERR_MSG);
    }
}
