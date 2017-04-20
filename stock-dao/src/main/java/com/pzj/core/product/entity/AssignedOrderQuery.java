/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 待分配列表查询类
 * @author Administrator
 * @version $Id: AssignedOrderQuery.java, v 0.1 2017年3月23日 下午2:52:36 Administrator Exp $
 */
public class AssignedOrderQuery implements Serializable {
	/**  */
	private static final long serialVersionUID = -8762537835001750694L;
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

	public AssignedOrderQuery() {
	};

	public AssignedOrderQuery(Long screeningId, Date theaterDate) {
		this.screeningId = screeningId;
		this.theaterDate = theaterDate;
	}
}
