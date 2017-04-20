package com.pzj.core.product.entity;

public class SeatScaleEntity implements java.io.Serializable {

	private static final long serialVersionUID = -6142709857870222635L;
	//	`scale_id` bigint(20) NOT NULL,
	//	  `type` tinyint(1) NOT NULL COMMENT '类型（x；y）',
	//	  `index` int(11) NOT NULL COMMENT '位置',
	//	  `scenic_id` bigint(20) NOT NULL COMMENT '剧场id',
	//	  `supplier_id` bigint(20) NOT NULL COMMENT '供应商id',
	//	  `name` varchar(5) NOT NULL COMMENT '名称',

	private Long id;

	private Integer type;

	private Integer position;

	private Long scenicId;

	private Long supplierId;

	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Long getScenicId() {
		return scenicId;
	}

	public void setScenicId(Long scenicId) {
		this.scenicId = scenicId;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
