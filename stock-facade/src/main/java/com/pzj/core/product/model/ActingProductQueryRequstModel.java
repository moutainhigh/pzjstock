package com.pzj.core.product.model;

import java.io.Serializable;

/**
 * 演艺产品查询参数
 * 
 * @author dongchunfu
 * @version $Id: ActingProductQueryRequstModel.java, v 0.1 2016年8月8日 下午5:58:09 dongchunfu Exp $
 */
public class ActingProductQueryRequstModel implements Serializable {

    private static final long serialVersionUID = 6940657521957560903L;

    /** 景区id */
    private Long              scenicId;

    /** 供应商id */
    private Long              supplierId;

    /** 演绎产品ID (暂为中间表ID) */

    private Long              actProId;

    public Long getScenicId() {
        return scenicId;
    }

    public void setScenicId(Long scenicId) {
        this.scenicId = scenicId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getActProId() {
        return actProId;
    }

    public void setActProId(Long actProId) {
        this.actProId = actProId;
    }

    @Override
    public String toString() {
        StringBuilder tostr = new StringBuilder(ActingProductModel.class.getSimpleName());
        tostr.append("[");
        tostr.append("scenicId: ").append(scenicId).append(",");
        tostr.append("supplierId: ").append(supplierId).append(",");
        tostr.append("actProId: ").append(actProId).append(",");
        tostr.append("]");
        return tostr.toString().intern();
    }
}