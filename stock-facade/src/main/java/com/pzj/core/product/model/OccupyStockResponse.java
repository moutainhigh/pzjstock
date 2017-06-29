package com.pzj.core.product.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017-6-7.
 */
public class OccupyStockResponse implements Serializable {

	private static final long serialVersionUID = -2500417768271972147L;
	/**
	 * 产品id
	 */
	private Long productId;
	/**
	 * 库存id
	 */
	private Long stockId;
	/**
	 * 库存名称
	 */
	private String stockName;
	/**
	 * 库存规则id
	 */
	private Long stockRuleId;
	/**
	 * 库存类型（  1、单一库存 2、每日库存）
	 */
	private Integer stockType;
	/**
	 * 剩余库存数
	 */
	private Integer remainNum;
	/**
	 * 出游日期
	 */
	private Date travelDate;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Integer getStockType() {
		return stockType;
	}

	public void setStockType(Integer stockType) {
		this.stockType = stockType;
	}

	public Integer getRemainNum() {
		return remainNum;
	}

	public void setRemainNum(Integer remainNum) {
		this.remainNum = remainNum;
	}

	public Date getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}

	public Long getStockRuleId() {
		return stockRuleId;
	}

	public void setStockRuleId(Long stockRuleId) {
		this.stockRuleId = stockRuleId;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

}
