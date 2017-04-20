/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.model;

/**
 * 
 * @author Administrator
 * @version $Id: ProScreeningsQueryModel.java, v 0.1 2016年8月31日 下午4:03:43 Administrator Exp $
 */
public class ProScreeningsQueryModel implements java.io.Serializable {

    private static final long serialVersionUID = 6767876972728247335L;
    /** 演艺跟产品关联ID*/
    private long              actProId;

    public long getActProId() {
        return actProId;
    }

    public void setActProId(long actProId) {
        this.actProId = actProId;
    }

}
