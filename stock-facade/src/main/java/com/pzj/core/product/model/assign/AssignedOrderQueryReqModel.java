/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.model.assign;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Administrator
 * @version $Id: AssignedOrderReqModel.java, v 0.1 2017年3月8日 下午2:38:52 Administrator Exp $
 */
public class AssignedOrderQueryReqModel implements Serializable {

	/**  */
	private static final long serialVersionUID = -3570251356325081703L;
	/**
	 * 场次id
	 * 
	 */
	private Long screeningId;
	/**
	 * 演艺时间
	 */
	private Date theaterDate;

	/**
	 * Getter method for property <tt>screeningId</tt>.
	 * 
	 * @return property value of screeningId
	 */
	public Long getScreeningId() {
		return screeningId;
	}

	/**
	 * Setter method for property <tt>screeningId</tt>.
	 * 
	 * @param screeningId value to be assigned to property screeningId
	 */
	public void setScreeningId(Long screeningId) {
		this.screeningId = screeningId;
	}

	/**
	 * Getter method for property <tt>theaterDate</tt>.
	 * 
	 * @return property value of theaterDate
	 */
	public Date getTheaterDate() {
		return theaterDate;
	}

	/**
	 * Setter method for property <tt>theaterDate</tt>.
	 * 
	 * @param theaterDate value to be assigned to property theaterDate
	 */
	public void setTheaterDate(Date theaterDate) {
		this.theaterDate = theaterDate;
	}

}
