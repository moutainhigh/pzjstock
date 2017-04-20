package com.pzj.core.product.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017-3-20.
 */
public class ReleaseStockReqsModel implements Serializable {
	/**  */
	private static final long serialVersionUID = 2572938571490432976L;

	/**
	 * 交易id
	 */
	private String transactionId;
	private List<ReleaseStockReqModel> releaseStockReqs;

	public List<ReleaseStockReqModel> getReleaseStockReqs() {
		return releaseStockReqs;
	}

	public void setReleaseStockReqs(List<ReleaseStockReqModel> releaseStockReqs) {
		this.releaseStockReqs = releaseStockReqs;
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
