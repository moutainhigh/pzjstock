/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.model.theater;

import java.io.Serializable;

/**
 * 剧场基础信息修改实体
 * @author Administrator
 * @version $Id: TheaterCreateReqModel.java, v 0.1 2017年3月29日 上午10:36:28 Administrator Exp $
 */
public class TheaterCreateReqModel implements Serializable {
	/**  */
	private static final long serialVersionUID = -922117408922674532L;
	/**
	 * 剧场id
	 */
	private Long theaterId;
	/**
	 * 行数量
	 */
	private Integer lineNumber;
	/**
	 * 列数量
	 */
	private Integer columnNumber;
	/**
	 * 座位排序规则  StockGlobalDict.theaterSort
	 */
	private Integer sortType;
	/**
	 * 座位状态  StockGlobalDict.seatType
	 */

	private Integer seatState;

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
	 * Getter method for property <tt>lineNumber</tt>.
	 * 
	 * @return property value of lineNumber
	 */
	public Integer getLineNumber() {
		return lineNumber;
	}

	/**
	 * Setter method for property <tt>lineNumber</tt>.
	 * 
	 * @param lineNumber value to be assigned to property lineNumber
	 */
	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	/**
	 * Getter method for property <tt>columnNumber</tt>.
	 * 
	 * @return property value of columnNumber
	 */
	public Integer getColumnNumber() {
		return columnNumber;
	}

	/**
	 * Setter method for property <tt>columnNumber</tt>.
	 * 
	 * @param columnNumber value to be assigned to property columnNumber
	 */
	public void setColumnNumber(Integer columnNumber) {
		this.columnNumber = columnNumber;
	}

	/**
	 * Getter method for property <tt>sortType</tt>.
	 * 
	 * @return property value of sortType
	 */
	public Integer getSortType() {
		return sortType;
	}

	/**
	 * Setter method for property <tt>sortType</tt>.
	 * 
	 * @param sortType value to be assigned to property sortType
	 */
	public void setSortType(Integer sortType) {
		this.sortType = sortType;
	}

	/**
	 * Getter method for property <tt>seatState</tt>.
	 * 
	 * @return property value of seatState
	 */
	public Integer getSeatState() {
		return seatState;
	}

	/**
	 * Setter method for property <tt>seatState</tt>.
	 * 
	 * @param seatState value to be assigned to property seatState
	 */
	public void setSeatState(Integer seatState) {
		this.seatState = seatState;
	}

}
