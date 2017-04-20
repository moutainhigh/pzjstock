package com.pzj.core.stock.model;

import java.util.Date;
import java.util.List;

public class StockRulesDateReqModel implements java.io.Serializable {

	private static final long serialVersionUID = 2631736858791186194L;
	/**
	 * 库存规则id集合
	 */
	private List<Long> ruleIds;
	/**
	 * 库存时间（针对非每日库存可空）
	 */
	private Date showTime;

	public List<Long> getRuleIds() {
		return ruleIds;
	}

	public void setRuleIds(List<Long> ruleIds) {
		this.ruleIds = ruleIds;
	}

	public Date getShowTime() {
		return showTime;
	}

	public void setShowTime(Date showTime) {
		this.showTime = showTime;
	}

}
