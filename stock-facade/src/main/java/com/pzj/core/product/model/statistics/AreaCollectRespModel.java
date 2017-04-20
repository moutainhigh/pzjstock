/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.model.statistics;

import java.io.Serializable;

/**
 * 区域汇总实体
 * @author Administrator
 * @version $Id: AreaStatisticsRespModel.java, v 0.1 2017年3月8日 下午3:20:58 Administrator Exp $
 */
public class AreaCollectRespModel implements Serializable {

	/**  */
	private static final long serialVersionUID = -3925715334059903824L;

	/**
	 * 区域id
	 */

	private Long areaId;
	/**
	 * 区域名称
	 */
	private String areaName;
	/**
	 * 已占数量
	 */
	private Integer occupiedNum;
	/**
	 * 可选数量
	 */
	private Integer unOccupiedNum;
	/**
	 * 已锁
	 */
	private Integer lockedNum;
	/**
	 *预占
	 */
	private Integer preOccupiedNum;
	/**
	 * 总座位数
	 */
	private Integer totalNum;

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
		return occupiedNum == null ? 0 : occupiedNum;
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
		return unOccupiedNum == null ? 0 : unOccupiedNum;
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
	 * Getter method for property <tt>lockedNum</tt>.
	 * 
	 * @return property value of lockedNum
	 */
	public Integer getLockedNum() {
		return lockedNum == null ? 0 : lockedNum;
	}

	/**
	 * Setter method for property <tt>lockedNum</tt>.
	 * 
	 * @param lockedNum value to be assigned to property lockedNum
	 */
	public void setLockedNum(Integer lockedNum) {
		this.lockedNum = lockedNum;
	}

	/**
	 * Getter method for property <tt>preOccupiedNum</tt>.
	 * 
	 * @return property value of preOccupiedNum
	 */
	public Integer getPreOccupiedNum() {
		return preOccupiedNum == null ? 0 : preOccupiedNum;
	}

	/**
	 * Setter method for property <tt>preOccupiedNum</tt>.
	 * 
	 * @param preOccupiedNum value to be assigned to property preOccupiedNum
	 */
	public void setPreOccupiedNum(Integer preOccupiedNum) {
		this.preOccupiedNum = preOccupiedNum;
	}

	/**
	 * Getter method for property <tt>totalNum</tt>.
	 * 
	 * @return property value of totalNum
	 */
	public Integer getTotalNum() {
		return totalNum == null ? 0 : totalNum;
	}

	/**
	 * Setter method for property <tt>totalNum</tt>.
	 * 
	 * @param totalNum value to be assigned to property totalNum
	 */
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

}
