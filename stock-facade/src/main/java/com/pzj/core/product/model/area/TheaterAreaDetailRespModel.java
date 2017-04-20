package com.pzj.core.product.model.area;

import java.util.List;

public class TheaterAreaDetailRespModel implements java.io.Serializable {

	private static final long serialVersionUID = -8875529224377966641L;

	/**
	 * 供应商id
	 */
	private Long supplierId;
	/**
	 * 剧场id
	 */
	private Long theaterId;
	/**
	 * 座位图类型;1:无座位图，2:矩形座位图
	 */
	private Integer seatChartType;
	/**
	 * 座位图缩影
	 */
	private String seatChartIcon;
	/**
	 * 选座模式;1:用户自选，2:后台分配
	 */
	private Integer fetchSeatModel;
	/**
	 * 剧场下的区域集合
	 */
	private List<AreaModel> areas;

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Long getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(Long theaterId) {
		this.theaterId = theaterId;
	}

	public Integer getSeatChartType() {
		return seatChartType;
	}

	public void setSeatChartType(Integer seatChartType) {
		this.seatChartType = seatChartType;
	}

	public String getSeatChartIcon() {
		return seatChartIcon;
	}

	public void setSeatChartIcon(String seatChartIcon) {
		this.seatChartIcon = seatChartIcon;
	}

	public Integer getFetchSeatModel() {
		return fetchSeatModel;
	}

	public void setFetchSeatModel(Integer fetchSeatModel) {
		this.fetchSeatModel = fetchSeatModel;
	}

	public List<AreaModel> getAreas() {
		return areas;
	}

	public void setAreas(List<AreaModel> areas) {
		this.areas = areas;
	}

}
