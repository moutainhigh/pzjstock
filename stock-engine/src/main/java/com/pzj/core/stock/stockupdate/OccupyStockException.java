package com.pzj.core.stock.stockupdate;

import com.pzj.core.common.exception.StockException;

/**
 * Created by Administrator on 2017-6-7.
 */
public class OccupyStockException extends StockException {

	public int errCode;

	public OccupyStockException(StockException exception) {
		super(exception.getMessage(), exception);
		this.errCode = exception.getErrCode();
	}

	/**
	 * 库存id
	 */
	private Long stockId;
	/**
	 * 库存类型（  1、单一库存 2、每日库存）
	 */
	private Integer stockType;
	/**
	 * 剩余库存数
	 */
	private Integer remainNum;
	/**
	 * 库存规则主键id
	 */
	private Long stockRuleId;
	/**
	 * 库存名称
	 */
	private String stockName;

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

	public Long getStockRuleId() {
		return stockRuleId;
	}

	public void setStockRuleId(Long stockRuleId) {
		this.stockRuleId = stockRuleId;
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

}
