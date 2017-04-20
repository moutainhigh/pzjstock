/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Administrator
 * @version $Id: AreaQuery.java, v 0.1 2017年3月22日 下午2:51:43 Administrator Exp $
 */
public class AreaQuery extends Area implements Serializable {

	/**  */
	private static final long serialVersionUID = -9059315226482053722L;

	//景区id集合
	private List<Long> scenicIds;

	/**
	 * Getter method for property <tt>scenicIds</tt>.
	 * 
	 * @return property value of scenicIds
	 */
	public List<Long> getScenicIds() {
		return scenicIds;
	}

	/**
	 * Setter method for property <tt>scenicIds</tt>.
	 * 
	 * @param scenicIds value to be assigned to property scenicIds
	 */
	public void setScenicIds(List<Long> scenicIds) {
		this.scenicIds = scenicIds;
	}

}
