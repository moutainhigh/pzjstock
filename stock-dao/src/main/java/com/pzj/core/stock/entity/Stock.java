package com.pzj.core.stock.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Stock implements Serializable, Cloneable {

    private static final long serialVersionUID = -7676356309815698359L;

    /** 库存主键id*/
    private Long              id;
    /** 库存规则主键id */
    private Long              ruleId;
    /** 库存状态（1 正常 2 停用，默认为1） */
    private Integer           state;
    /** 库存时间 */
    private Integer           stockTime;
    /** 库存总数 */
    private Integer           totalNum;
    /** 已用库存数量 */
    private Integer           usedNum;
    /** 剩余库存数 */
    private Integer           remainNum;
    /** 创建时间 */
    private Date              createTime;
    /**更新时间 */
    private Date              updateTime;

    /**辅助查询*/
    /** 开始时间（含） */
    private Integer           beginTime;
    /** 结束时间（含） */
    private Integer           endTime;
    /** 按月查询参数 */
    private Integer           queryMonth;

    /** 库存id集合  */
    private List<Long>        stockIds;
    /**
     * 库存类型（  1、单一库存 2、每日库存）
     */
    private Integer           type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getStockTime() {
        return stockTime;
    }

    public void setStockTime(Integer stockTime) {
        this.stockTime = stockTime;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getUsedNum() {
        return usedNum;
    }

    public void setUsedNum(Integer usedNum) {
        this.usedNum = usedNum;
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

    public Integer getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Integer beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Integer getQueryMonth() {
        return queryMonth;
    }

    public void setQueryMonth(Integer queryMonth) {
        this.queryMonth = queryMonth;
    }

    public List<Long> getStockIds() {
        return stockIds;
    }

    public void setStockIds(List<Long> stockIds) {
        this.stockIds = stockIds;
    }

    @Override
    public Stock clone() throws CloneNotSupportedException {
        return (Stock) super.clone();
    }

    public Integer getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(Integer remainNum) {
        this.remainNum = remainNum;
    }

    /**
     * Getter method for property <tt>type</tt>.
     * 
     * @return property value of type
     */
    public Integer getType() {
        return type;
    }

    /**
     * Setter method for property <tt>type</tt>.
     * 
     * @param type value to be assigned to property type
     */
    public void setType(Integer type) {
        this.type = type;
    }

}