/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.vo;

/**
 * 
 * @author Administrator
 * @version $Id: AreaVo.java, v 0.1 2016年8月31日 下午3:22:01 Administrator Exp $
 */
public class AreaVo implements java.io.Serializable {

    private static final long serialVersionUID = -700775706567933458L;
    private Long              areaId;
    private String            areaName;
    private String            areaSign;

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaSign() {
        return areaSign;
    }

    public void setAreaSign(String areaSign) {
        this.areaSign = areaSign;
    }

}
