/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.read;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.product.entity.AssignedOrder;
import com.pzj.core.product.entity.AssignedOrderQuery;
import com.pzj.core.product.entity.PageEntity;

/**
 * 
 * @author Administrator
 * @version $Id: AssignedOrderReadMapper.java, v 0.1 2017年3月23日 下午2:57:55 Administrator Exp $
 */
public interface AssignedOrderReadMapper {
	List<AssignedOrder> queryAssignedOrdersByModel(@Param(value = "assignedOrder") AssignedOrderQuery assignedOrderQuery);

	List<AssignedOrder> querySpuAssignByPage(@Param(value = "pageEntity") PageEntity pageEntity,
			@Param(value = "showDate") Date showDate);

	Integer countSpuAssign(@Param(value = "showDate") Date showDate);

	Integer countTotalAssignNum();

	Integer countShowSeat(@Param(value = "showDate") Date showDate);

	Integer countShowOrder(@Param(value = "showDate") Date showDate);

	List<AssignedOrder> queryCalendarAssignCount(@Param(value = "showStartDate") Date showStartDate,
			@Param(value = "showEndDate") Date showEndDate);
}
