/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.model.seat;

import java.io.Serializable;

/**
 * 座位基础属性
 * @author Administrator
 * @version $Id: SeatInfoModel.java, v 0.1 2017年3月10日 上午10:21:24 Administrator Exp $
 */
public class SeatInfoModel implements Serializable {

	/**  */
	private static final long serialVersionUID = -5435260895264699666L;

	/**
	 * 座位名称
	 */

	private String seatName;

	/**
	 * 座位id
	 */
	private Long seatId;

	/**
	 * 区域id
	 */
	private Long areaId;

	/**
	 * 场次id
	 */
	private Long screeingId;

	/**
	 * Getter method for property <tt>screeingId</tt>.
	 * 
	 * @return property value of screeingId
	 */
	public Long getScreeingId() {
		return screeingId;
	}

	/**
	 * Setter method for property <tt>screeingId</tt>.
	 * 
	 * @param screeingId value to be assigned to property screeingId
	 */
	public void setScreeingId(Long screeingId) {
		this.screeingId = screeingId;
	}

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
	 * Getter method for property <tt>seatName</tt>.
	 * 
	 * @return property value of seatName
	 */
	public String getSeatName() {
		return seatName;
	}

	/**
	 * Setter method for property <tt>seatName</tt>.
	 * 
	 * @param seatName value to be assigned to property seatName
	 */
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}

	/**
	 * Getter method for property <tt>seatId</tt>.
	 * 
	 * @return property value of seatId
	 */
	public Long getSeatId() {
		return seatId;
	}

	/**
	 * Setter method for property <tt>seatId</tt>.
	 * 
	 * @param seatId value to be assigned to property seatId
	 */
	public void setSeatId(Long seatId) {
		this.seatId = seatId;
	}

}
