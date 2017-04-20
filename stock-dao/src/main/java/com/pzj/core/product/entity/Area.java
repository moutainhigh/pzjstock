package com.pzj.core.product.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Area implements Serializable {

	private static final long serialVersionUID = -5581928289613845035L;

	/**
	 * 区域主键id
	 */
	private Long id;
	/**
	 * 区域名称
	 */
	private String name;
	/**
	 * 区域简称
	 */
	private String code;
	/**
	 * 景区id
	 */
	private Long scenicId;
	/**
	 * 供应商Id
	 */
	private Long supplierId;
	/**
	 * 座位类型。
	 * 1.	无座位
	 * 2.	有座位，只支持矩形
	 */
	private Integer seatType;

	/**
	 * 座位模式
	 * 1.	用户自选
	 * 2.	手动派座
	 * 3.	自动派座
	 */
	private Integer seatMode;

	/**
	 * 缩略图(路径)
	 */
	private String thumb;
	/**
	 * 创建时间 
	 */
	private Date createTime;
	/**
	 * 修改时间 
	 */
	private Date updateTime;
	/**
	 * 状态
	 */
	private Integer state;

	private List<String> areaNameList;

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

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
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

	public Long getScenicId() {
		return scenicId;
	}

	public void setScenicId(Long scenicId) {
		this.scenicId = scenicId;
	}

	public List<String> getAreaNameList() {
		return areaNameList;
	}

	public void setAreaNameList(List<String> areaNameList) {
		this.areaNameList = areaNameList;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getSeatType() {
		return seatType;
	}

	public void setSeatType(Integer seatType) {
		this.seatType = seatType;
	}

	public Integer getSeatMode() {
		return seatMode;
	}

	public void setSeatMode(Integer seatMode) {
		this.seatMode = seatMode;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}