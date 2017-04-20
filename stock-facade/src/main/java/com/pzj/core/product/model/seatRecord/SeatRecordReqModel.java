/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.model.seatRecord;

import java.io.Serializable;
import java.util.Date;

import com.pzj.core.product.enums.RecordCategory;

/**
 * 查询占座记录实体
 * @author Administrator
 * @version $Id: SeatRecordReqModel.java, v 0.1 2017年3月20日 上午10:59:43 Administrator Exp $
 */
public class SeatRecordReqModel implements Serializable {

	/**  */
	private static final long serialVersionUID = 9155454600419655429L;
	/**
	 * 场次id
	 */
	private Long screeingId;
	/**
	 * 区域id
	 */
	private Long areaId;
	/**
	 * 游玩时间
	 */
	private Date theaterDate;
	/**
	 * 占座类型
	 */
	private RecordCategory recordCategory;

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

	/**
	 * Getter method for property <tt>recordCategory</tt>.
	 * 
	 * @return property value of recordCategory
	 */
	public RecordCategory getRecordCategory() {
		return recordCategory;
	}

	/**
	 * Setter method for property <tt>recordCategory</tt>.
	 * 
	 * @param recordCategory value to be assigned to property recordCategory
	 */
	public void setRecordCategory(RecordCategory recordCategory) {
		this.recordCategory = recordCategory;
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

}
