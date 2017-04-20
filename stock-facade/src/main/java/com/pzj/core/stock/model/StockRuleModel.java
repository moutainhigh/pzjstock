/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 库存规则VO
 * 
 * @author dongchunfu
 * @version $Id: StockRuleModel.java, v 0.1 2016年7月25日 上午10:26:27 dongchunfu Exp $
 */
public class StockRuleModel implements java.io.Serializable {

    private static final long                          serialVersionUID = 5159533569671244427L;

    /**
     * 库存规则ID
     */
    private Long                                       id;
    /**
     * 库存规则名称 
     */
    private String                                     name;
    /**
     * 库存规则类别 
     */
    private Integer                                    category;
    /**
     * 库存规则类型（  1、单一库存 2、每日库存）
     */
    private Integer                                    type;
    /**
     * 库存上限
     */
    private Integer                                    upperLimit;
    /**
     * 库存规则状态（  1 正常 2 停用）
     */
    private Integer                                    state;
    /**
     * 供应商ID
     */
    private Long                                       supplierId;
    /**
     * 是否归还库存（ 1、是 2、否）
     */
    private Integer                                    whetherReturn;
    /**
     * 库存规则创建时间
     */
    private Date                                       createTime;
    /**
     * 库存规则更新时间
     */
    private Date                                       updateTime;

    private HashSet<String>                            categories;
    private HashMap<String, ArrayList<StockRuleModel>> mappings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public Integer getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Integer upperLimit) {
        this.upperLimit = upperLimit;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public HashSet<String> getCategories() {
        return categories;
    }

    public void setCategories(HashSet<String> categories) {
        this.categories = categories;
    }

    public HashMap<String, ArrayList<StockRuleModel>> getMappings() {
        return mappings;
    }

    public void setMappings(HashMap<String, ArrayList<StockRuleModel>> mappings) {
        this.mappings = mappings;
    }

    @Override
    public String toString() {
        StringBuilder tostr = new StringBuilder(StockRuleModel.class.getSimpleName());
        tostr.append("[");
        tostr.append("id: ").append(id).append(",");
        tostr.append("name: ").append(name).append(",");
        tostr.append("category: ").append(category).append(",");
        tostr.append("type: ").append(type).append(",");
        tostr.append("upperLimit: ").append(upperLimit).append(",");
        tostr.append("state: ").append(state).append(",");
        tostr.append("supplierId: ").append(supplierId).append(",");
        tostr.append("whetherReturn:").append(whetherReturn).append(",");
        tostr.append("createTime: ").append(createTime).append(",");
        tostr.append("updateTime: ").append(updateTime).append(",");
        tostr.append("categories: ").append(categories).append(",");
        tostr.append("mappings: ").append(mappings).append(",");
        tostr.append("]");
        return tostr.toString().intern();
    }

}