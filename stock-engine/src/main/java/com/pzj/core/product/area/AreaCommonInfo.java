package com.pzj.core.product.area;

/**
 * Created by Administrator on 2017-3-21.
 */
public class AreaCommonInfo {
	/**
	 * 剧场id
	 */
	private Long theaterId;

	/**
	 * 供应商id
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

	public Long getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(Long theaterId) {
		this.theaterId = theaterId;
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
}
