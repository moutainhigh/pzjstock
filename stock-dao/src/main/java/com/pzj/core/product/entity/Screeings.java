package com.pzj.core.product.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Screeings implements Serializable {

	private static final long serialVersionUID = 6126137696744312135L;

	/** 场次主键id */
	private Long id;
	/** 供应商id */
	private Long supplierId;
	/** 景区id */
	private Long scenicId;
	/** 场次简称 */
	private String code;
	/** 场次名称 */
	private String name;
	/** 状态；1 正常 0 禁用*/
	private Integer state;
	/** 演出开始时间 */
	private Integer startTime;
	/** 演出结束时间 */
	private Integer endTime;
	/** 演出停售时间 */
	private Integer endSaleTime;
	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date updateTime;

	private List<String> screeingsNameList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getName() {
		return name;
	}

	public Long getScenicId() {
		return scenicId;
	}

	public void setScenicId(Long scenicId) {
		this.scenicId = scenicId;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public Integer getEndSaleTime() {
		return endSaleTime;
	}

	public void setEndSaleTime(Integer endSaleTime) {
		this.endSaleTime = endSaleTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<String> getScreeingsNameList() {
		return screeingsNameList;
	}

	public void setScreeingsNameList(List<String> screeingsNameList) {
		this.screeingsNameList = screeingsNameList;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Screeings [id=" + id + ", supplierId=" + supplierId + ", scenicId=" + scenicId + ", code=" + code
				+ ", name=" + name + ", state=" + state + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", endSaleTime=" + endSaleTime + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", screeingsNameList=" + screeingsNameList + "]";
	}
}