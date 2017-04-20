/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.model.seatRecord;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.pzj.core.product.enums.LockSeatType;
import com.pzj.core.product.enums.RecordCategory;
import com.pzj.core.product.model.seat.SeatInfoModel;

/**
 * 占座记录实体
 * @author Administrator
 * @version $Id: SeatRecordCreateReqModel.java, v 0.1 2017年3月8日 下午3:48:55 Administrator Exp $
 */
public class SeatRecordCreateReqModel implements Serializable {

	/**  */
	private static final long serialVersionUID = 2870894394568609338L;
	/**
	 * 场次id
	 * 
	 */
	private Long screeingId;
	/**
	 * 演艺时间
	 */
	private Date theaterDate;

	/**
	 * 交易id
	 */
	private String transactionId;

	/**
	 * 占座用户id
	 */
	private Long operateUserId;

	/**
	 * 锁座操作类型
	 */
	private LockSeatType lockSeatType;

	/**
	 * 占座的座位集合
	 */
	private List<SeatInfoModel> seatInfos;
	/**
	 * 占座类型
	 */
	private RecordCategory recordCategory;

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
	 * Getter method for property <tt>seatInfos</tt>.
	 * 
	 * @return property value of seatInfos
	 */
	public List<SeatInfoModel> getSeatInfos() {
		return seatInfos;
	}

	/**
	 * Setter method for property <tt>seatInfos</tt>.
	 * 
	 * @param seatInfos value to be assigned to property seatInfos
	 */
	public void setSeatInfos(List<SeatInfoModel> seatInfos) {
		this.seatInfos = seatInfos;
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
	 * Getter method for property <tt>lockSeatType</tt>.
	 * 
	 * @return property value of lockSeatType
	 */
	public LockSeatType getLockSeatType() {
		return lockSeatType;
	}

	/**
	 * Setter method for property <tt>lockSeatType</tt>.
	 * 
	 * @param lockSeatType value to be assigned to property lockSeatType
	 */
	public void setLockSeatType(LockSeatType lockSeatType) {
		this.lockSeatType = lockSeatType;
	}

}
