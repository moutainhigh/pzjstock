package com.pzj.core.product.model.screeings;

import java.util.Date;

import com.pzj.framework.entity.QueryResult;

public class TheaterScreeingOrderRespModel implements java.io.Serializable {

	private static final long serialVersionUID = -3751368351703016689L;
	/**
	 * 剧场数量
	 */
	private Integer theaterNum;
	/**
	 * 场次数量
	 */
	private Integer screeingNum;
	/**
	 * 演出时间
	 */
	private Date showTime;
	/**
	 * 剧场场次预定率分页对象
	 */
	private QueryResult<ArtSpuScreeingOrderModel> theatherScreeingOrders;

	public Integer getTheaterNum() {
		return theaterNum;
	}

	public void setTheaterNum(Integer theaterNum) {
		this.theaterNum = theaterNum;
	}

	public Integer getScreeingNum() {
		return screeingNum;
	}

	public void setScreeingNum(Integer screeingNum) {
		this.screeingNum = screeingNum;
	}

	public Date getShowTime() {
		return showTime;
	}

	public void setShowTime(Date showTime) {
		this.showTime = showTime;
	}

	public QueryResult<ArtSpuScreeingOrderModel> getTheatherScreeingOrders() {
		return theatherScreeingOrders;
	}

	public void setTheatherScreeingOrders(QueryResult<ArtSpuScreeingOrderModel> theatherScreeingOrders) {
		this.theatherScreeingOrders = theatherScreeingOrders;
	}

}
