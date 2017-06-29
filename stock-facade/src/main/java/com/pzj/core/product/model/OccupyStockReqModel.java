package com.pzj.core.product.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017-3-8.
 */
public class OccupyStockReqModel implements Serializable {
	/**  */
	private static final long serialVersionUID = -4234935455915425552L;

	/**
	 * 操作人ID
	 */
	private Long operator;

	/**
	 * 产品id
	 */
	private Long productId;

	/**
	 * 库存规则id
	 */
	private Long stockRuleId;

	/**
	 * 减库存数量
	 */
	private Integer outQuantity;

	/**
	 * 出游日期
	 */
	private Date travelDate;

	/**
	 * 交易id
	 */
	private String transactionId;

	/**
	 * 场次id
	 */
	private Long screeningsId;

	/**
	 * 区域id
	 */
	private Long areaId;

	/**
	 * 占座类型
	 * 20：下单占座
	 * 40：预约占座
	 */
	private Integer occupyType;

	/**
	 * 需要占座的座位id
	 */
	private List<Long> occupySeatIds;

	/**
	 * 需要释放的座位id
	 */
	private List<Long> releaseSeatIds;

	/**
	 * spuId
	 */
	private Long spuId;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public List<Long> getOccupySeatIds() {
		return occupySeatIds;
	}

	public void setOccupySeatIds(List<Long> occupySeatIds) {
		this.occupySeatIds = occupySeatIds;
		if (occupySeatIds != null && !occupySeatIds.isEmpty()) {
			setOutQuantity(occupySeatIds.size());
		}
	}

	public List<Long> getReleaseSeatIds() {
		return releaseSeatIds;
	}

	public void setReleaseSeatIds(List<Long> releaseSeatIds) {
		this.releaseSeatIds = releaseSeatIds;
	}

	public Integer getOutQuantity() {
		return outQuantity;
	}

	public void setOutQuantity(Integer outQuantity) {
		if (outQuantity != null && outQuantity >= occupySeatIdsSize()) {
			this.outQuantity = outQuantity;
		}
	}

	private int occupySeatIdsSize() {
		if (this.occupySeatIds != null) {
			return occupySeatIds.size();
		}
		return 0;
	}

	public Integer getOccupyType() {
		return occupyType;
	}

	public void setOccupyType(Integer occupyType) {
		this.occupyType = occupyType;
	}

	public Long getStockRuleId() {
		return stockRuleId;
	}

	public void setStockRuleId(Long stockRuleId) {
		this.stockRuleId = stockRuleId;
	}

	public Date getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}

	public Long getScreeningsId() {
		return screeningsId;
	}

	public void setScreeningsId(Long screeningsId) {
		this.screeningsId = screeningsId;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getOperator() {
		return operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

	public Long getSpuId() {
		return spuId;
	}

	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}
}
