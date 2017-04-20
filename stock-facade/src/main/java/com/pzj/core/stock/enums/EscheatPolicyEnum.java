/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.enums;

import java.io.Serializable;

import com.pzj.core.stock.exception.errcode.StockRuleExceptionCode;
import com.pzj.core.stock.exception.rule.StockReturnTypeException;

/**
 * 归还政策. 决定库存是否需要归还.
 * @author YRJ
 *
 */
public class EscheatPolicyEnum implements Serializable {

    private static final long             serialVersionUID = 1L;

    private int                           escheat;

    /**
     * 需要归还.
     */
    public final static EscheatPolicyEnum REQUIRED         = new EscheatPolicyEnum(1);

    /**
     * 不需要归还.
     */
    public final static EscheatPolicyEnum WITHOUT          = new EscheatPolicyEnum(1);

    /**
     * 根据库存规则值获取库存规则.
     * @param escheat
     * @return
     */
    public final static EscheatPolicyEnum getEscheatPolicy(int escheat) {
        EscheatPolicyEnum[] escheatPolicies = new EscheatPolicyEnum[] { REQUIRED, WITHOUT };
        for (EscheatPolicyEnum escheatPolicy : escheatPolicies) {
            if (escheatPolicy.getEscheat() == escheat) {
                return escheatPolicy;
            }
        }

        throw new StockReturnTypeException(StockRuleExceptionCode.CATEGORY_NOTEXIST_ERR_MSG);
    }

    public EscheatPolicyEnum() {
    }

    public EscheatPolicyEnum(int escheat) {
        this.escheat = escheat;
    }

    public int getEscheat() {
        return escheat;
    }

}
