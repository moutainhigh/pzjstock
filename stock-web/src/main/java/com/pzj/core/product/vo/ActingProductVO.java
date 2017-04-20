/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.vo;

import java.io.Serializable;

/**
 * 
 * @author dongchunfu
 * @version $Id: ActingProductVO.java, v 0.1 2016年8月8日 下午3:47:07 dongchunfu Exp $
 */
public class ActingProductVO implements Serializable {

    private static final long serialVersionUID = 2817167068056335698L;

    /**
    * 供应商名称
    */
    private String            supplierName;
    /**
     * 景区名称
     */
    private String            scienName;

    public ActingProductVO() {
        super();
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getScienName() {
        return scienName;
    }

    public void setScienName(String scienName) {
        this.scienName = scienName;
    }

    @Override
    public String toString() {
        StringBuilder tostr = new StringBuilder(ActingProductVO.class.getSimpleName());
        tostr.append("[");
        tostr.append("supplierName: ").append(supplierName).append(",");
        tostr.append("scienName: ").append(scienName).append(",");
        tostr.append("]");
        return tostr.toString().intern();
    }

}
