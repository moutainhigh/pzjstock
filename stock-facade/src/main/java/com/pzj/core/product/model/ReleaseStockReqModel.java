package com.pzj.core.product.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-3-20.
 */
public class ReleaseStockReqModel implements Serializable {

	/**  */
	private static final long serialVersionUID = 839100951551100025L;

	/**
	 * 产品id
	 */
	private Long productId;

	/**
	 * 释放的座位数量
	 */
	private Integer seatNum;

	/**
	 * 释放的库存数量
	 */
	private Integer stockNum;

	/**
	 * Getter method for property <tt>productId</tt>.
	 * 
	 * @return property value of productId
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * Setter method for property <tt>productId</tt>.
	 * 
	 * @param productId value to be assigned to property productId
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * Getter method for property <tt>seatNum</tt>.
	 * 
	 * @return property value of seatNum
	 */
	public Integer getSeatNum() {
		return seatNum == null ? 0 : seatNum;
	}

	/**
	 * Setter method for property <tt>seatNum</tt>.
	 * 
	 * @param seatNum value to be assigned to property seatNum
	 */
	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
	}

	/**
	 * Getter method for property <tt>stockNum</tt>.
	 * 
	 * @return property value of stockNum
	 */
	public Integer getStockNum() {
		return stockNum == null ? 0 : stockNum;
	}

	/**
	 * Setter method for property <tt>stockNum</tt>.
	 * 
	 * @param stockNum value to be assigned to property stockNum
	 */
	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}

}
