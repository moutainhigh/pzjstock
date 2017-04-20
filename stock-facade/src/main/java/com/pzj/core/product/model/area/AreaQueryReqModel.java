/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.model.area;

import java.io.Serializable;

import com.pzj.framework.entity.PageableRequestBean;

/**
 * 
 * @author Administrator
 * @version $Id: AreaQueryReqModel.java, v 0.1 2017年3月8日 下午2:18:20 Administrator Exp $
 */
public class AreaQueryReqModel extends PageableRequestBean implements Serializable {

	/**  */
	private static final long serialVersionUID = 3778237327870824606L;

	private Long supplierId;

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

}
