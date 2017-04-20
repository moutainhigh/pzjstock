/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.vo;

import java.util.List;

/**
 * 
 * @author Administrator
 * @version $Id: ActingVo.java, v 0.1 2016年8月31日 下午3:19:33 Administrator Exp $
 */
public class ActingVo implements java.io.Serializable {

    private static final long  serialVersionUID = -6847921655275823L;

    private String             supplierName;
    private String             scenicName;
    private Integer            whetherSeat;

    private List<AreaVo>       areaVoList;

    private List<ScreeningsVO> screeningsVOList;

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getScenicName() {
        return scenicName;
    }

    public void setScenicName(String scenicName) {
        this.scenicName = scenicName;
    }

    public Integer getWhetherSeat() {
        return whetherSeat;
    }

    public void setWhetherSeat(Integer whetherSeat) {
        this.whetherSeat = whetherSeat;
    }

    public List<AreaVo> getAreaVoList() {
        return areaVoList;
    }

    public void setAreaVoList(List<AreaVo> areaVoList) {
        this.areaVoList = areaVoList;
    }

    public List<ScreeningsVO> getScreeningsVOList() {
        return screeningsVOList;
    }

    public void setScreeningsVOList(List<ScreeningsVO> screeningsVOList) {
        this.screeningsVOList = screeningsVOList;
    }

}
