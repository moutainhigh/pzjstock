/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

import java.util.List;

/**
 * 
 * @author Administrator
 * @version $Id: OperateStockModel.java, v 0.1 2016年11月23日 下午6:16:19 Administrator Exp $
 */
public class OperateStockModel implements java.io.Serializable {

    private static final long             serialVersionUID = -1272349434879498600L;

    private List<OccupyStockRequestModel> orderStockModelList;

    /**
     * Getter method for property <tt>orderStockModelList</tt>.
     * 
     * @return property value of orderStockModelList
     */
    public List<OccupyStockRequestModel> getOrderStockModelList() {
        return orderStockModelList;
    }

    /**
     * Setter method for property <tt>orderStockModelList</tt>.
     * 
     * @param orderStockModelList value to be assigned to property orderStockModelList
     */
    public void setOrderStockModelList(List<OccupyStockRequestModel> orderStockModelList) {
        this.orderStockModelList = orderStockModelList;
    }

}
