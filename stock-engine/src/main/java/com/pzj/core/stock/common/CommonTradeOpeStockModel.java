package com.pzj.core.stock.common;

import java.util.List;

import com.pzj.core.stock.model.OccupyStockRequestModel;

public class CommonTradeOpeStockModel implements java.io.Serializable {

	private static final long serialVersionUID = 5523822850915981288L;
	/**
	 * 占库存集合
	 */
	private List<OccupyStockRequestModel> occupyStocks;
	/**
	 * 释放库存集合
	 */
	private List<OccupyStockRequestModel> releaseStocks;
	/**
	 * 占库存、释放库存标识
	 */
	private String type;
	/**
	 * 操作库存数量
	 */
	private Integer opeNum;
	/**
	 * 交易id
	 */
	private String transactionId;
	/**
	 * 产品id
	 */
	private Long productId;

	/**
	 * 	占库存标识
	 */
	public static final String OCCUPY_STOCK = "occupy";
	/**
	 * 	释放库存标识
	 */
	public static final String RELEASE_STOCK = "release";

	public List<OccupyStockRequestModel> getOccupyStocks() {
		return occupyStocks;
	}

	public void setOccupyStocks(List<OccupyStockRequestModel> occupyStocks) {
		this.occupyStocks = occupyStocks;
	}

	public List<OccupyStockRequestModel> getReleaseStocks() {
		return releaseStocks;
	}

	public void setReleaseStocks(List<OccupyStockRequestModel> releaseStocks) {
		this.releaseStocks = releaseStocks;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getOpeNum() {
		return opeNum;
	}

	public void setOpeNum(Integer opeNum) {
		this.opeNum = opeNum;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
