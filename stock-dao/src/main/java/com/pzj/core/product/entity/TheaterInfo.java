/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.entity;

import java.io.Serializable;

/**
 * 剧场基础信息数据库实体
 * @author Administrator
 * @version $Id: TheaterInfo.java, v 0.1 2017年3月29日 下午1:57:21 Administrator Exp $
 */
public class TheaterInfo implements Serializable {

	/**  */
	private static final long serialVersionUID = 596579793719639632L;
	/**
	 * 剧场id
	 */
	private Long theaterId;
	/**
	 * 行数量
	 */
	private Integer lineNum;
	/**
	 * 列数量
	 */
	private Integer columnNum;
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
	 * Getter method for property <tt>lineNum</tt>.
	 * 
	 * @return property value of lineNum
	 */
	public Integer getLineNum() {
		return lineNum;
	}

	/**
	 * Setter method for property <tt>lineNum</tt>.
	 * 
	 * @param lineNum value to be assigned to property lineNum
	 */
	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}

	/**
	 * Getter method for property <tt>columnNum</tt>.
	 * 
	 * @return property value of columnNum
	 */
	public Integer getColumnNum() {
		return columnNum;
	}

	/**
	 * Setter method for property <tt>columnNum</tt>.
	 * 
	 * @param columnNum value to be assigned to property columnNum
	 */
	public void setColumnNum(Integer columnNum) {
		this.columnNum = columnNum;
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

	public TheaterInfo() {
	};

	public TheaterInfo(Long theaterId, Integer lineNum, Integer columnNum, Integer sortType, Integer seatState) {
		this.theaterId = theaterId;
		this.lineNum = lineNum;
		this.columnNum = columnNum;
		this.seatState = seatState;
		this.sortType = sortType;
	}

}
