/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

import java.util.List;

/**
 * 
 * @author Administrator
 * @version $Id: SeatsOptionalModel.java, v 0.1 2016年10月26日 上午11:47:20 Administrator Exp $
 */
public class SeatsOptionalResponse implements java.io.Serializable {

    private static final long serialVersionUID = -5934401934066010208L;
    /** 座位是否可选返回标识 可选：true；不可选：false*/
    private Boolean           flag;
    /** 返回不可选座位集合*/
    private List<String>      notOptionalSeats;

    /**
     * Getter method for property <tt>flag</tt>.
     * 
     * @return property value of flag
     */
    public Boolean getFlag() {
        return flag;
    }

    /**
     * Setter method for property <tt>flag</tt>.
     * 
     * @param flag value to be assigned to property flag
     */
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    /**
     * Getter method for property <tt>notOptionalSeats</tt>.
     * 
     * @return property value of notOptionalSeats
     */
    public List<String> getNotOptionalSeats() {
        return notOptionalSeats;
    }

    /**
     * Setter method for property <tt>notOptionalSeats</tt>.
     * 
     * @param notOptionalSeats value to be assigned to property notOptionalSeats
     */
    public void setNotOptionalSeats(List<String> notOptionalSeats) {
        this.notOptionalSeats = notOptionalSeats;
    }

}
