/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

import java.io.Serializable;
import java.util.List;

/**
 * 创建库存参数Model,为支持批量创建操作，只接收规则ID集合
 * @author dongchunfu
 * @version $Id: CreateStockModel.java, v 0.1 2016年8月23日 下午2:26:57 dongchunfu Exp $
 */
public class CreateStockModel implements Serializable {

    private static final long serialVersionUID = -1274153582556393369L;

    /** 库存规则主键id */
    private List<Long>        ruleIds;

    /** 无参构造  */
    public CreateStockModel() {
        super();
    }

    /** get/set 方法 BEGIN */
    public List<Long> getRuleIds() {
        return ruleIds;
    }

    public void setRuleIds(List<Long> ruleIds) {
        this.ruleIds = null == ruleIds || ruleIds.size() == 0 ? null : ruleIds;
    }

    /** get/set 方法 END */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(CreateStockModel.class.getSimpleName());
        sb.append("[");
        if (null != ruleIds) {
            sb.append("ruleIds: ").append(ruleIds).append(".");
        }
        sb.append("]");
        return sb.toString().intern();
    }

}