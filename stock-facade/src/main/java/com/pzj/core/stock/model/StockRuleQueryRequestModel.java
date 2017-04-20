/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

import java.util.List;

import com.pzj.framework.entity.PageableRequestBean;

/**
 * 库存查询参数对象.
 * 
 * @author YRJ
 * @version $Id: StockRuleQueryRequestBean.java, v 0.1 2016年8月2日 上午11:44:41 YRJ Exp $
 */

public class StockRuleQueryRequestModel extends PageableRequestBean {

    private static final long serialVersionUID = 7175222046952911273L;

    /**
     * 库存规则ID
     */
    private Long              ruleId;
    /**
     * 库存规则名称
     */
    private String            name;
    /**
     * 库存规则模糊查询名称
     */
    private String            vagueName;
    /**
     * 库存规则类别
     */
    private Integer           category;
    /**
     * 库存规则类型（1、单一库存 2、每日库存）
     */
    private Integer           type;
    /**
     * 库存规则状态（  1 正常 2 停用）
     */
    private Integer           state;
    /**
     * 供应商ID
     */
    private Long              supplierId;
    /**
     * 是否归还库存（ 1、是 2、否）
     */
    private Integer           whetherReturn;

    private List<Long>        ruleIds;

    public StockRuleQueryRequestModel() {
        super();
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getWhetherReturn() {
        return whetherReturn;
    }

    public void setWhetherReturn(Integer whetherReturn) {
        this.whetherReturn = whetherReturn;
    }

    public List<Long> getRuleIds() {
        return ruleIds;
    }

    public void setRuleIds(List<Long> ruleIds) {
        this.ruleIds = ruleIds;
    }

    public String getVagueName() {
        return vagueName;
    }

    public void setVagueName(String vagueName) {
        this.vagueName = vagueName;
    }

    @Override
    public String toString() {
        StringBuilder tostr = new StringBuilder(StockRuleQueryRequestModel.class.getSimpleName());
        tostr.append("[");
        if (null != ruleId) {
            tostr.append("ruleId: ").append(ruleId).append(",");
        }
        if (null != name) {
            tostr.append("name: ").append(name).append(",");
        }
        if (null != category) {
            tostr.append("category: ").append(category).append(",");
        }
        if (null != type) {
            tostr.append("type: ").append(type).append(",");
        }
        if (null != state) {
            tostr.append("state: ").append(state).append(",");
        }
        if (null != supplierId) {
            tostr.append("supplierId: ").append(supplierId).append(",");
        }
        if (null != whetherReturn) {
            tostr.append("whetherReturn: ").append(whetherReturn).append(",");
        }
        tostr.append("currentPage: ").append(getCurrentPage()).append(",");
        tostr.append("pageSize: ").append(getPageSize()).append(",");
        tostr.append("]");
        return tostr.toString();
    }

}
