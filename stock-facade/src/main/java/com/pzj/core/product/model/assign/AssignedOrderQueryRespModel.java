/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.model.assign;

import java.io.Serializable;

/**
 * 
 * @author Administrator
 * @version $Id: AssignedOrderQueryRespModel.java, v 0.1 2017年3月8日 下午2:47:53 Administrator Exp $
 */
public class AssignedOrderQueryRespModel implements Serializable {

	/**  */
	private static final long serialVersionUID = 1743706026173055049L;

	/**
	 * 主键id
	 */
	private Long assignedId;

	/**
	 * 分配状态
	 */
	private Integer state;

	/**
	 * 交易id
	 */
	private String transactionId;

	/**
	 * 区域id
	 */
	private Long areaId;

	/**
	 * 区域名称
	 */
	private String areaName;

	/**
	 * 已分配
	 */
	private Integer occupiedNum;

	/**
	 * 待分配(总需分配数量)
	 */
	private Integer unOccupiedNum;

	/**
	 * Getter method for property <tt>assignedId</tt>.
	 * 
	 * @return property value of assignedId
	 */
	public Long getAssignedId() {
		return assignedId;
	}

	/**
	 * Setter method for property <tt>assignedId</tt>.
	 * 
	 * @param assignedId value to be assigned to property assignedId
	 */
	public void setAssignedId(Long assignedId) {
		this.assignedId = assignedId;
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
	 * Getter method for property <tt>occupiedNum</tt>.
	 * 
	 * @return property value of occupiedNum
	 */
	public Integer getOccupiedNum() {
		return occupiedNum;
	}

	/**
	 * Setter method for property <tt>occupiedNum</tt>.
	 * 
	 * @param occupiedNum value to be assigned to property occupiedNum
	 */
	public void setOccupiedNum(Integer occupiedNum) {
		this.occupiedNum = occupiedNum;
	}

	/**
	 * Getter method for property <tt>unOccupiedNum</tt>.
	 * 
	 * @return property value of unOccupiedNum
	 */
	public Integer getUnOccupiedNum() {
		return unOccupiedNum;
	}

	/**
	 * Setter method for property <tt>unOccupiedNum</tt>.
	 * 
	 * @param unOccupiedNum value to be assigned to property unOccupiedNum
	 */
	public void setUnOccupiedNum(Integer unOccupiedNum) {
		this.unOccupiedNum = unOccupiedNum;
	}

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

	public AssignedOrderQueryRespModel() {
	};

	public AssignedOrderQueryRespModel(Long assignedId, Integer state, String transactionId, Long areaId,
			String areaName, Integer occupiedNum, Integer unOccupiedNum) {
		this.assignedId = assignedId;
		this.state = state;
		this.transactionId = transactionId;
		this.areaId = areaId;
		this.areaName = areaName;
		this.occupiedNum = occupiedNum;
		this.unOccupiedNum = unOccupiedNum;
	};
}
