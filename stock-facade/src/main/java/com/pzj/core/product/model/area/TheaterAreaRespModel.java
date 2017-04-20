/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.model.area;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Administrator
 * @version $Id: TheaterAreaRespModel.java, v 0.1 2017年3月8日 下午2:15:55 Administrator Exp $
 */
public class TheaterAreaRespModel implements Serializable {

	/**  */
	private static final long serialVersionUID = -6991727140966086370L;
	/**
	 * 供应商id
	 */
	private Long supplierId;
	/**
	 * 演艺剧场id
	 */
	private Long theaterId;
	/**
	 * 演艺剧场名称
	 */
	private String theaterName;

	/**
	 * 区域集合
	 */
	private List<AreaModel> areas;

	/**
	 * Getter method for property <tt>supplierId</tt>.
	 * 
	 * @return property value of supplierId
	 */
	public Long getSupplierId() {
		return supplierId;
	}

	/**
	 * Setter method for property <tt>supplierId</tt>.
	 * 
	 * @param supplierId value to be assigned to property supplierId
	 */
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
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
	 * Getter method for property <tt>theaterName</tt>.
	 * 
	 * @return property value of theaterName
	 */
	public String getTheaterName() {
		return theaterName;
	}

	/**
	 * Setter method for property <tt>theaterName</tt>.
	 * 
	 * @param theaterName value to be assigned to property theaterName
	 */
	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	/**
	 * Getter method for property <tt>areas</tt>.
	 * 
	 * @return property value of areas
	 */
	public List<AreaModel> getAreas() {
		return areas;
	}

	/**
	 * Setter method for property <tt>areas</tt>.
	 * 
	 * @param areas value to be assigned to property areas
	 */
	public void setAreas(List<AreaModel> areas) {
		this.areas = areas;
	}

}
