/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.entity;

import java.io.Serializable;

/**
 * 
 * @author Administrator
 * @version $Id: SeatRecordCollects.java, v 0.1 2017年3月24日 下午2:47:03 Administrator Exp $
 */
public class SeatRecordCollects implements Serializable {
	/**  */
	private static final long serialVersionUID = -670309422948284788L;
	/**
	 * 区域id
	 */
	private Long areaId;
	/**
	 * 区域名称
	 */
	private String areaName;
	/**
	 * 占座类型
	 */
	private Integer category;
	/**
	 * 占座数量
	 */
	private Integer seatNum;

	/**
	 * Getter method for property <tt>areaId</tt>.
	 * 
	 * @return property value of areaId
	 */
	public Long getAreaId() {
		return areaId;
	}

	/**
	 * Setter method for property <tt>areaId</tt>.
	 * 
	 * @param areaId value to be assigned to property areaId
	 */
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	/**
	 * Getter method for property <tt>areaName</tt>.
	 * 
	 * @return property value of areaName
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * Setter method for property <tt>areaName</tt>.
	 * 
	 * @param areaName value to be assigned to property areaName
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * Getter method for property <tt>category</tt>.
	 * 
	 * @return property value of category
	 */
	public Integer getCategory() {
		return category;
	}

	/**
	 * Setter method for property <tt>category</tt>.
	 * 
	 * @param category value to be assigned to property category
	 */
	public void setCategory(Integer category) {
		this.category = category;
	}

	/**
	 * Getter method for property <tt>seatNum</tt>.
	 * 
	 * @return property value of seatNum
	 */
	public Integer getSeatNum() {
		return seatNum;
	}

	/**
	 * Setter method for property <tt>seatNum</tt>.
	 * 
	 * @param seatNum value to be assigned to property seatNum
	 */
	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
	}

}
