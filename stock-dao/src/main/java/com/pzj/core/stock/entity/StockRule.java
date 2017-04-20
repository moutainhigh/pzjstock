package com.pzj.core.stock.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 库存规则实体
 * 映射stock.stock_rule表
 * @author dongchunfu
 * @version $Id: StockRule.java, v 0.1 2016年7月25日 上午10:14:12 dongchunfu Exp $
 */
public class StockRule implements Serializable {

    private static final long serialVersionUID = -5705665461927749543L;

    /**库存规则ID*/
    private Long              id;
    /**库存规则状态（  1 正常 2 停用）*/
    private Integer           state;
    /**库存规则名称 */
    private String            name;
    /**库存规则类别 */
    private Integer           category;
    /**库存规则类型 （ 1、单一库存 2、每日库存）*/
    private Integer           type;
    /**库存上限*/
    private Integer           upperLimit;
    /**供应商ID*/
    private Long              supplierId;
    /**是否归还库存（ 1、是 2、否）*/
    private Integer           whetherReturn;
    /**库存规则创建时间*/
    private Date              createTime;
    /**库存规则更新时间*/
    private Date              updateTime;

    /**辅助查询参数*/
    private List<Long>        ruleIds;
    /**
     * 库存规则模糊查询名称
     */
    private String            vagueName;

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
        StringBuilder tostr = new StringBuilder(StockRule.class.getSimpleName());
        tostr.append("[");
        tostr.append("id: ").append(id).append(",");
        tostr.append("name: ").append(name).append(",");
        tostr.append("category: ").append(category).append(",");
        tostr.append("type: ").append(type).append(",");
        tostr.append("upperLimit: ").append(upperLimit).append(",");
        tostr.append("state: ").append(state).append(",");
        tostr.append("supplierId: ").append(supplierId).append(",");
        tostr.append("whetherReturn").append(whetherReturn).append(",");
        tostr.append("createTime: ").append(createTime).append(",");
        tostr.append("updateTime: ").append(updateTime).append(",");
        tostr.append("]");
        return tostr.toString();
    }

}