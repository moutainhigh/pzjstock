/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Administrator
 * @version $Id: AssignedOrder.java, v 0.1 2017年3月23日 下午2:59:12 Administrator Exp $
 */
public class AssignedOrder implements Serializable {
	/**  */
	private static final long serialVersionUID = 2558474462241539126L;

	/**
	 * 主键id
	 */
	private Long assignedId;
	/**
	 * spuId
	 */
	private Long spuId;

	/**
	 * 分配状态
	 */
	private Integer state;

	/**
	 * 交易id
	 */
	private String transactionId;

	/**
	 * 场次id
	 */
	private Long screeningsId;

	/**
	 * 区域id
	 */
	private Long areaId;

	/**
	 * 场次名称
	 */
	private String screName;

	/**
	 * 区域名称
	 */
	private String areaName;

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 出游日期
	 */
	private Date travelDate;

	/**
	 * 已分配
	 */
	private Integer occupiedNum;

	/**
	 * 待分配(总需分配数量)
	 */
	private Integer unOccupiedNum;
	/**
	 * 待分配座位
	 */
	private Integer assignNum;

	private Date createTime;

	public AssignedOrder copy(){
		AssignedOrder assignedOrder = new AssignedOrder();
		assignedOrder.assignedId = assignedId;
		assignedOrder.spuId = spuId;
		assignedOrder.state = state;
		assignedOrder.transactionId = transactionId;
		assignedOrder.screeningsId = screeningsId;
		assignedOrder.areaId = areaId;
		assignedOrder.screName = screName;
		assignedOrder.areaName = areaName;
		assignedOrder.userId = userId;
		assignedOrder.travelDate = travelDate;
		assignedOrder.occupiedNum = occupiedNum;
		assignedOrder.unOccupiedNum = unOccupiedNum;
		assignedOrder.assignNum = assignNum;
		assignedOrder.createTime = createTime;
		return assignedOrder;
	}

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getScreeningsId() {
		return screeningsId;
	}

	public void setScreeningsId(Long screeningsId) {
		this.screeningsId = screeningsId;
	}

	public Date getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getSpuId() {
		return spuId;
	}

	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}

	public String getScreName() {
		return screName;
	}

	public void setScreName(String screName) {
		this.screName = screName;
	}

	public Integer getAssignNum() {
		return assignNum;
	}

	public void setAssignNum(Integer assignNum) {
		this.assignNum = assignNum;
	}

}
