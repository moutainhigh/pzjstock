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

}
