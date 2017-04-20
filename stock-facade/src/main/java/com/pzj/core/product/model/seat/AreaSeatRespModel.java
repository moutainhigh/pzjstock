package com.pzj.core.product.model.seat;

public class AreaSeatRespModel implements java.io.Serializable {

	private static final long serialVersionUID = -5616887006587446402L;
	/**
	 * 座位id
	 */
	private Long seatId;
	/**
	 * 区域id
	 */
	private Long areaId;
	/**
	 * 座位号;空区域：1，空被删区域：2；其他默认有组织好的座位号例如：A1_1
	 */
	private String seatNum;
	/**
	 * 座位名称
	 */
	private String seatName;
	/**
	 * 列
	 */
	private Integer column;
	/**
	 * 行
	 */
	private Integer row;
	/**
	 * 1：可售；2：锁定（此处锁定是座位图的锁定）
	 */
	private Integer saleState;
	/**
	 * 1:可选；2：已选；3：锁定；4：预选
	 */
	private Integer showState;
}
