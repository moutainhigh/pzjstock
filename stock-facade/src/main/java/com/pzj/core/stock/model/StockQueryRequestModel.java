/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

import java.io.Serializable;
import java.util.List;

import com.pzj.core.common.utils.CommonUtils;

/**
 * 库存查询参数对象.
 * 
 * @author YRJ
 * @version $Id: StockQueryRequestBean.java, v 0.1 2016年8月2日 上午11:44:18 dongchunfu Exp $
 */

public class StockQueryRequestModel implements Serializable {

	private static final long serialVersionUID = -6199871189702057720L;

	/**
	 * 库存ID.
	 */
	private Long stockId;
	/**
	 * 库存规则ID.
	 */
	private Long ruleId;

	/**
	 * 日库存时间,格式：20170424
	 */
	private String stockTime;
	/** 
	 * 库存状态
	 */
	private Integer state;
	/**
	 * 范围查询开始时间（含）
	 */
	private String beginStockTime;
	/**
	 * 范围查询结束时间（含）
	 */
	private String endStockTime;

	/**
	 * 查询某月（当前日期（含）之后）范围内的所有库存信息
	 */
	private String queryMonth;

	/**
	 * 库存规则ID集合
	 */
	private List<Long> stockIds;

	public StockQueryRequestModel() {
		super();
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public String getStockTime() {
		return stockTime;
	}

	public int getStockTimeInt() {
		return CommonUtils.strDate2Int(stockTime);
	}

	public void setStockTime(String stockTime) {
		this.stockTime = stockTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public int getBeginStockTime() {
		return CommonUtils.strDate2Int(beginStockTime);
	}

	public void setBeginStockTime(String beginStockTime) {
		this.beginStockTime = beginStockTime;
	}

	public int getEndStockTime() {
		return CommonUtils.strDate2Int(endStockTime);
	}

	public void setEndStockTime(String endStockTime) {
		this.endStockTime = endStockTime;
	}

	public Integer getQueryMonth() {
		//        return CommonUtils.strDate2Int(queryMonth);
		return CommonUtils.convertStringToInteger(this.queryMonth);
	}

	public void setQueryMonth(String queryMonth) {
		if (!CommonUtils.checkStringIsNullStrict(queryMonth)) {
			this.queryMonth = CommonUtils.checkStockTime(queryMonth);
		}
		//        if (queryMonth.contains("-")) {
		//            this.queryMonth = (queryMonth + "-01").intern();
		//        } else if (queryMonth.contains("/")) {
		//            this.queryMonth = (queryMonth + "/01").intern();
		//        } else {
		//            this.queryMonth = (queryMonth + "01").intern();
		//        }
	}

	public List<Long> getStockIds() {
		return stockIds;
	}

	public void setStockIds(List<Long> stockIds) {
		this.stockIds = stockIds;
	}

	@Override
	public String toString() {
		StringBuilder tostr = new StringBuilder(StockQueryRequestModel.class.getSimpleName());
		tostr.append("[");
		tostr.append("stockId: ").append(stockId).append(",");
		tostr.append("ruleId: ").append(ruleId).append(",");
		tostr.append("stockTime: ").append(stockTime).append(",");
		tostr.append("queryMonth: ").append(queryMonth).append(",");
		tostr.append("beginStockTime: ").append(beginStockTime).append(",");
		tostr.append("endStockTime: ").append(endStockTime).append(",");
		tostr.append("state: ").append(state).append(",");
		tostr.append("stockIds: ").append(stockIds).append(".");
		tostr.append("]");

		return tostr.toString().intern();
	}

}
