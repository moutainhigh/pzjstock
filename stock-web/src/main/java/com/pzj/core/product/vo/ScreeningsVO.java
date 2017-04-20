/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.vo;

/**
 * 
 * @author Administrator
 * @version $Id: ScreeningsVO.java, v 0.1 2016年8月31日 下午3:22:14 Administrator Exp $
 */
public class ScreeningsVO implements java.io.Serializable {

    private static final long serialVersionUID = -8902336994859157435L;
    private String            screeningsName;
    private String            screeningsSign;
    private String            screeningsStart;
    private String            screeningsEnd;
    private String            screeningsSaleEnd;

    public String getScreeningsName() {
        return screeningsName;
    }

    public void setScreeningsName(String screeningsName) {
        this.screeningsName = screeningsName;
    }

    public String getScreeningsSign() {
        return screeningsSign;
    }

    public void setScreeningsSign(String screeningsSign) {
        this.screeningsSign = screeningsSign;
    }

    public String getScreeningsStart() {
        return screeningsStart;
    }

    public void setScreeningsStart(String screeningsStart) {
        this.screeningsStart = screeningsStart;
    }

    public String getScreeningsEnd() {
        return screeningsEnd;
    }

    public void setScreeningsEnd(String screeningsEnd) {
        this.screeningsEnd = screeningsEnd;
    }

    public String getScreeningsSaleEnd() {
        return screeningsSaleEnd;
    }

    public void setScreeningsSaleEnd(String screeningsSaleEnd) {
        this.screeningsSaleEnd = screeningsSaleEnd;
    }

}
