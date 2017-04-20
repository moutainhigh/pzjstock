/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.model.seatRecord;

import java.io.Serializable;
import java.util.Date;

/**
 * 占座记录查询实体
 * @author Administrator
 * @version $Id: SeatRecordQueryReqModel.java, v 0.1 2017年3月8日 下午3:19:27 Administrator Exp $
 */
public class SeatRecordQueryReqModel implements Serializable {

	/**  */
	private static final long serialVersionUID = 1592062847600789477L;

	/**
	 * 剧场id
	 */
	private Long theaterId;
	/**
	 * 场次id
	 * 
	 */
	private Long screeingId;
	/**
	 * 区域id
	 */
	private Long areaId;
	/**
	 * 演艺时间
	 */
	private Date theaterDate;

	/**
	 * 交易id
	 */
	private String transactionId;
	/**
	 * 座位号
	 */
	private String seatName;
	/**
	 * 座位id
	 */
	private Long seatId;

	/**
	 * 操作人id
	 */

	private Long operateUserId;

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
	 * Getter method for property <tt>operateUserId</tt>.
	 * 
	 * @return property value of operateUserId
	 */
	public Long getOperateUserId() {
		return operateUserId;
	}

	/**
	 * Setter method for property <tt>operateUserId</tt>.
	 * 
	 * @param operateUserId value to be assigned to property operateUserId
	 */
	public void setOperateUserId(Long operateUserId) {
		this.operateUserId = operateUserId;
	}

	/**
	 * Getter method for property <tt>theaterId</tt>.
	 * 
	 * @return property value of theaterId
	 */
	public Long getTheaterId() {
		return theaterId;
	}

	/**
	 * Setter method for property <tt>theaterId</tt>.
	 * 
	 * @param theaterId value to be assigned to property theaterId
	 */
	public void setTheaterId(Long theaterId) {
		this.theaterId = theaterId;
	}

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
	 * Getter method for property <tt>transactionId</tt>.
	 * 
	 * @return property value of transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * Setter method for property <tt>transactionId</tt>.
	 * 
	 * @param transactionId value to be assigned to property transactionId
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
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
