/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Administrator
 * @version $Id: SeatRecord.java, v 0.1 2017年3月16日 上午11:26:17 Administrator Exp $
 */
public class SeatRecord implements Serializable {

	/**  */
	private static final long serialVersionUID = 5866704355542444250L;
	/**
	 * 主键id
	 */
	private Long recordId;
	/**
	 * 占座记录唯一值
	 */
	private Long recordUnique;
	/**
	 * 场次id
	 */
	private Long screeningId;
	/**
	 * 区域id
	 */
	private Long areaId;
	/**
	 * 交易id
	 */
	private String transactionId;
	/**
	 * 座位id
	 */
	private Long seatId;
	/**
	 * 座位名称
	 */
	private String seatName;
	/**
	 * 占座用户id
	 */
	private Long operatorId;
	/**
	 * 类别 1.待选 2.已占 3.锁定4.预选
	 */
	private Integer category;
	/**
	 * 记录是否有效 0.	无效1.	有效
	 */
	private Integer state;
	/**
	 * 游玩日期
	 */
	private Date travelDate;
	/**
	 * 创建日期
	 */
	private Date createTime;
	/**
	 * 过期时间
	 */
	private Date expirationTime;
	/**
	 * 更新日期
	 */
	private Date updateTime;
	/**
	 * 座位id集合
	 */
	private List<Long> seatIds;

	/**
	 * 主键id集合
	 */
	private List<Long> recordIds;

	/**
	 * Getter method for property <tt>recordIds</tt>.
	 * 
	 * @return property value of recordIds
	 */
	public List<Long> getRecordIds() {
		return recordIds;
	}

	/**
	 * Setter method for property <tt>recordIds</tt>.
	 * 
	 * @param recordIds value to be assigned to property recordIds
	 */
	public void setRecordIds(List<Long> recordIds) {
		this.recordIds = recordIds;
	}

	/**
	 * Getter method for property <tt>recordId</tt>.
	 * 
	 * @return property value of recordId
	 */
	public Long getRecordId() {
		return recordId;
	}

	/**
	 * Setter method for property <tt>recordId</tt>.
	 * 
	 * @param recordId value to be assigned to property recordId
	 */
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	/**
	 * Getter method for property <tt>screeningId</tt>.
	 * 
	 * @return property value of screeningId
	 */
	public Long getScreeningId() {
		return screeningId;
	}

	/**
	 * Setter method for property <tt>screeningId</tt>.
	 * 
	 * @param screeningId value to be assigned to property screeningId
	 */
	public void setScreeningId(Long screeningId) {
		this.screeningId = screeningId;
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
	 * Getter method for property <tt>operatorId</tt>.
	 * 
	 * @return property value of operatorId
	 */
	public Long getOperatorId() {
		return operatorId;
	}

	/**
	 * Setter method for property <tt>operatorId</tt>.
	 * 
	 * @param operatorId value to be assigned to property operatorId
	 */
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
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
	 * Getter method for property <tt>travelDate</tt>.
	 * 
	 * @return property value of travelDate
	 */
	public Date getTravelDate() {
		return travelDate;
	}

	/**
	 * Setter method for property <tt>travelDate</tt>.
	 * 
	 * @param travelDate value to be assigned to property travelDate
	 */
	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}

	/**
	 * Getter method for property <tt>createTime</tt>.
	 * 
	 * @return property value of createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * Setter method for property <tt>createTime</tt>.
	 * 
	 * @param createTime value to be assigned to property createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * Getter method for property <tt>expirationTime</tt>.
	 * 
	 * @return property value of expirationTime
	 */
	public Date getExpirationTime() {
		return expirationTime;
	}

	/**
	 * Setter method for property <tt>expirationTime</tt>.
	 * 
	 * @param expirationTime value to be assigned to property expirationTime
	 */
	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	/**
	 * Getter method for property <tt>updateTime</tt>.
	 * 
	 * @return property value of updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * Setter method for property <tt>updateTime</tt>.
	 * 
	 * @param updateTime value to be assigned to property updateTime
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	public List<Long> getSeatIds() {
		return seatIds;
	}

	public void setSeatIds(List<Long> seatIds) {
		this.seatIds = seatIds;
	}

	public Long getRecordUnique() {
		return recordUnique;
	}

	public void setRecordUnique(Long recordUnique) {
		this.recordUnique = recordUnique;
	}
}
