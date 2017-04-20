/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.entity;

/**
 * 产品 与 库存规则的绑定关系表
 * @author dongchunfu
 * @version $Id: SkuRuleRel.java, v 0.1 2016年8月15日 下午4:28:05 dongchunfu Exp $
 */
public class SkuRuleRel {
    /**
     * 关系表ID
     */
    private Long id;
    /**
     * 产品ID
     */
    private Long productId;
    /**
     * 规则ID
     */
    private Long ruleId;

    public SkuRuleRel() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    @Override
    public String toString() {
        StringBuilder tostr = new StringBuilder(SkuRuleRel.class.getSimpleName());
        tostr.append("[");
        tostr.append("id: ").append(id).append(",");
        tostr.append("productId: ").append(productId).append(",");
        tostr.append("ruleId: ").append(ruleId).append(".");
        tostr.append("]");
        return tostr.toString().intern();
    }
}
