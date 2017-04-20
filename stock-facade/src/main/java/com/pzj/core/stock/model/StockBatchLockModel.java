/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

import java.util.Map;

/**
 * 
 * @author Administrator
 * @version $Id: StockBatchLockModel.java, v 0.1 2016年7月28日 下午6:00:42 Administrator Exp $
 */
public class StockBatchLockModel implements java.io.Serializable {

    private static final long    serialVersionUID = -8845830128791513769L;
    /**
     * 操作库存的map对象，针对某个库存锁定或者释放库存数量
     * key:库存ID value:操作库存数量
     */
    private Map<Long, Integer>   operateStockMap;
    /**
     * 操作未存在库存map对象，针对某个库存锁定库存数量
     * key:库存规则ID+","+库存时间(格式：20160920)
     */
    private Map<String, Integer> operateNotExistsStockMap;

    /**
     * 操作库存业务类型(4 批量占用库存; 5 批量释放库存)
     */
    private Integer              operateType;

    /**
     * 用户id
     */
    private Long                 userId;

    public Map<Long, Integer> getOperateStockMap() {
        return operateStockMap;
    }

    public void setOperateStockMap(Map<Long, Integer> operateStockMap) {
        this.operateStockMap = operateStockMap;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Map<String, Integer> getOperateNotExistsStockMap() {
        return operateNotExistsStockMap;
    }

    public void setOperateNotExistsStockMap(Map<String, Integer> operateNotExistsStockMap) {
        this.operateNotExistsStockMap = operateNotExistsStockMap;
    }

    @Override
    public String toString() {
        return "StockBatchLockModel [operateStockMap=" + operateStockMap + ", operateNotExistsStockMap=" + operateNotExistsStockMap + ", operateType="
               + operateType + ", userId=" + userId + "]";
    }

}
