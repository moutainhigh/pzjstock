package com.pzj.core.product.model.assign;

import com.pzj.framework.entity.QueryResult;

public class TheaterAssignRespModel implements java.io.Serializable {

	private static final long serialVersionUID = -6682311017613596108L;
	/**
	 * 统计订单、座位数据
	 */
	private AssignOrderCountRespModel assignOrderCount;
	/**
	 * 剧场场次分页对象
	 */
	private QueryResult<TheaterScreeingAssignModel> theaterScreeingAssigns;

	public AssignOrderCountRespModel getAssignOrderCount() {
		return assignOrderCount;
	}

	public void setAssignOrderCount(AssignOrderCountRespModel assignOrderCount) {
		this.assignOrderCount = assignOrderCount;
	}

	public QueryResult<TheaterScreeingAssignModel> getTheaterScreeingAssigns() {
		return theaterScreeingAssigns;
	}

	public void setTheaterScreeingAssigns(QueryResult<TheaterScreeingAssignModel> theaterScreeingAssigns) {
		this.theaterScreeingAssigns = theaterScreeingAssigns;
	}

}
