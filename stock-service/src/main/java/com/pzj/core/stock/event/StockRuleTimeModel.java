package com.pzj.core.stock.event;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class StockRuleTimeModel implements java.io.Serializable {

	private static final long serialVersionUID = 2093756506972879168L;
	/**
	 * 场次id
	 */
	private Long screeingId;
	/**
	 * 区域id
	 */
	private Long areaId;
	/**
	 * 库存规则id
	 */
	private Long stockRuleId;
	/**
	 * 可用日期
	 */
	private List<Integer> avaiDates;

	private Date sDate;

	private Date eDate;

	private List<Date> closeDates;

	private Set<String> delWeeks;

	public Long getStockRuleId() {
		return stockRuleId;
	}

	public void setStockRuleId(Long stockRuleId) {
		this.stockRuleId = stockRuleId;
	}

	public List<Integer> getAvaiDates() {
		return avaiDates;
	}

	public void setAvaiDates(List<Integer> avaiDates) {
		this.avaiDates = avaiDates;
	}

	public Long getScreeingId() {
		return screeingId;
	}

	public void setScreeingId(Long screeingId) {
		this.screeingId = screeingId;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public Date getsDate() {
		return sDate;
	}

	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}

	public Date geteDate() {
		return eDate;
	}

	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}

	public List<Date> getCloseDates() {
		return closeDates;
	}

	public void setCloseDates(List<Date> closeDates) {
		this.closeDates = closeDates;
	}

	public Set<String> getDelWeeks() {
		return delWeeks;
	}

	public void setDelWeeks(Set<String> delWeeks) {
		this.delWeeks = delWeeks;
	}

}
