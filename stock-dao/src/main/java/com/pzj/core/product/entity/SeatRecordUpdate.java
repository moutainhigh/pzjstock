/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.entity;

import java.io.Serializable;

/**
 * 
 * @author Administrator
 * @version $Id: SeatRecordUpdate.java, v 0.1 2017年3月30日 下午1:57:58 Administrator Exp $
 */
public class SeatRecordUpdate implements Serializable {
	/**  */
	private static final long serialVersionUID = -8463660738584901955L;
	/**
	 * 交易id
	 */
	private String transactionId;
	/**
	 * 修改的记录数量
	 */
	private Integer seatNum;

	/**
	 * 状态
	 */
	private Integer state;

	/**
	 * Getter method for property <tt>state</tt>.
	 * 
	 * @return property value of state
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * Setter method for property <tt>state</tt>.
	 * 
	 * @param state value to be assigned to property state
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * Getter method for property <tt>seatNum</tt>.
	 * 
	 * @return property value of seatNum
	 */
	public Integer getSeatNum() {
		return seatNum == null ? 0 : seatNum;
	}

	/**
	 * Setter method for property <tt>seatNum</tt>.
	 * 
	 * @param seatNum value to be assigned to property seatNum
	 */
	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
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

}
