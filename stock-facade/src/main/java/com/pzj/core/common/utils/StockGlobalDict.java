/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.common.utils;

import com.pzj.core.product.service.SeatRecordService;

/**
 * 
 * @author Administrator
 * @version $Id: StockDict.java, v 0.1 2017年3月29日 上午10:40:26 Administrator Exp $
 */
public class StockGlobalDict {

	/**
	 * 剧场排序类型
	 * 
	 * @author Administrator
	 * @version $Id: StockGlobalDict.java, v 0.1 2017年3月29日 上午10:43:16 Administrator Exp $
	 */
	public static class theaterSort {
		public static final Integer left_to_right = 1;
		public static final Integer odd_left = 2;
		public static final Integer orr_right = 3;

	}

	/**
	 * 座位类型
	 * 
	 * @author Administrator
	 * @version $Id: StockGlobalDict.java, v 0.1 2017年3月29日 上午10:50:40 Administrator Exp $
	 */
	public static class seatType {
		public static final Integer candidate = 1;
		public static final Integer lock = 2;
		public static final Integer preemption = 3;
		public static final Integer occupying = 4;
	}

	/**
	 * 占座类型
	 * 使用的接口是 {@link SeatRecordService#occupyStock}
	 */
	public static class ooccupyType{
		// 无需占座
		public static final Integer noNeedSeat = 0;
		// 预选
		public static final Integer preemptionSeat = 30;
		// 占座
		public static final Integer occupyingSeat = 40;
	}
}
