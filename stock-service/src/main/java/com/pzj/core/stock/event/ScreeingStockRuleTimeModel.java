package com.pzj.core.stock.event;

import java.util.List;

public class ScreeingStockRuleTimeModel implements java.io.Serializable {

	private static final long serialVersionUID = -9032657003227409705L;
	/**
	 * 剧场id
	 */
	private Long theaterId;
	/**
	 * 场次id
	 */
	private Long screeingId;
	/**
	 * 库存规则可用时间集合
	 */
	private List<StockRuleTimeModel> stockRuleTimes;

	public Long getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(Long theaterId) {
		this.theaterId = theaterId;
	}

	public Long getScreeingId() {
		return screeingId;
	}

	public void setScreeingId(Long screeingId) {
		this.screeingId = screeingId;
	}

	public List<StockRuleTimeModel> getStockRuleTimes() {
		return stockRuleTimes;
	}

	public void setStockRuleTimes(List<StockRuleTimeModel> stockRuleTimes) {
		this.stockRuleTimes = stockRuleTimes;
	}

}
