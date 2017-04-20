/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.context;

import java.util.List;

/**
 * 
 * @author Administrator
 * @version $Id: UserSeatResponse.java, v 0.1 2016年8月22日 下午5:30:27 Administrator Exp $
 */
public class UserSeatResponse implements java.io.Serializable {

    /**  */
    private static final long serialVersionUID = 2445473712150131067L;
    /**  已经被占用的座位集合*/
    private List<String>      occupySeat;

    public List<String> getOccupySeat() {
        return occupySeat;
    }

    public void setOccupySeat(List<String> occupySeat) {
        this.occupySeat = occupySeat;
    }

}
