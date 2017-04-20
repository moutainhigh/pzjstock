/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

/**
 * 
 * @author Administrator
 * @version $Id: UserSceneSeatModel.java, v 0.1 2016年8月22日 下午4:57:40 Administrator Exp $
 */
public class UserSeatModel implements java.io.Serializable {

    /**  */
    private static final long serialVersionUID = -6018714260068138596L;
    /**  库存ID*/
    private Long              stockId;
    /**  占座用户ID*/
    private Long              operateUserId;

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(Long operateUserId) {
        this.operateUserId = operateUserId;
    }

}
