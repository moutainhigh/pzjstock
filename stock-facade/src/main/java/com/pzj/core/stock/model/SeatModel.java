/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

import java.io.Serializable;

/**
 * 座位VO
 * 
 * @author dongchunfu
 * @version $Id: SeatVO.java, v 0.1 2016年8月2日 上午11:18:50 dongchunfu Exp $
 */
public class SeatModel implements Serializable {

    private static final long serialVersionUID = -836111523766680727L;

    /**
     * 座位图主键id
     */
    private Long              seatChartId;

    public Long getSeatChartId() {
        return seatChartId;
    }

    public void setSeatChartId(Long seatChartId) {
        this.seatChartId = seatChartId;
    }

    @Override
    public String toString() {
        return "SeatModel [seatChartId=" + seatChartId + "]";
    }

}