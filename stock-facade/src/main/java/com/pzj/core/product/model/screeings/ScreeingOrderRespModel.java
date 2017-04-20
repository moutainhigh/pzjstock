package com.pzj.core.product.model.screeings;

public class ScreeingOrderRespModel implements java.io.Serializable {

	private static final long serialVersionUID = -4438231071583735439L;

	/**
	 * 场次主键id
	 */
	private Long id;
	/**
	 * 景区id
	 */
	private Long scenicId;
	/**
	 * 场次名称
	 */
	private String name;

	/**
	 * 演出开始时间
	 */
	private String startTime;
	/**
	 * 演出结束时间
	 */
	private String endTime;
	/**
	 * 销售数量
	 */
	private Integer saleNum;
	/**
	 * 销售率
	 */
	private Double saleRate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getScenicId() {
		return scenicId;
	}

	public void setScenicId(Long scenicId) {
		this.scenicId = scenicId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}

	public Double getSaleRate() {
		return saleRate;
	}

	public void setSaleRate(Double saleRate) {
		this.saleRate = saleRate;
	}

}
