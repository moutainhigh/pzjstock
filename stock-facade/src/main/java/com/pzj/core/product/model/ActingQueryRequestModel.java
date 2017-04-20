/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.model;

import java.io.Serializable;

/**
 * 演绎查询参数对象.
 * 
 * @author dongchunfu
 * @version $Id: ActingQueryRequestBean.java, v 0.1 2016年8月1日 下午3:00:23 dongchunfu Exp $
 */
public class ActingQueryRequestModel implements Serializable {

    private static final long serialVersionUID = -3350463562988230226L;

    /**
     * 演绎ID
     */
    private Long              actingId;
    /**
     * 供应商ID
     */
    private Long              supplierId;
    /**
     * 景区id
     */
    private Long              scenicId;
    /**
     *  状态(1 正常 2 停用，默认为1)
     */
    private Integer           state;
    /**
     * 演艺名称
     */
    private String            name;
    /**
     *  是否需要选座(1 需要选座 2 不需要选座，默认2)
     */
    private Integer           whetherNeedSeat;

    public ActingQueryRequestModel() {
        super();
    }

    public Long getActingId() {
        return actingId;
    }

    public void setActingId(Long actingId) {
        this.actingId = actingId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getScenicId() {
        return scenicId;
    }

    public void setScenicId(Long scenicId) {
        this.scenicId = scenicId;
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
        this.name = name;
    }

    public Integer getWhetherNeedSeat() {
        return whetherNeedSeat;
    }

    public void setWhetherNeedSeat(Integer whetherNeedSeat) {
        this.whetherNeedSeat = whetherNeedSeat;
    }

    @Override
    public String toString() {
        StringBuilder tostr = new StringBuilder(ActingQueryRequestModel.class.getSimpleName());
        tostr.append("[");
        tostr.append("actingId: ").append(actingId).append(",");
        tostr.append("supplierId: ").append(supplierId).append(",");
        tostr.append("name: ").append(name).append(",");
        tostr.append("state: ").append(state).append(",");
        tostr.append("scenicId: ").append(scenicId).append(",");
        tostr.append("whetherNeedSeat: ").append(whetherNeedSeat).append(",");
        tostr.append("]");
        return tostr.toString().intern();
    }

}
