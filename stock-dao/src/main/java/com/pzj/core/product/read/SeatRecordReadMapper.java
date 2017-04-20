/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.product.entity.SeatRecord;
import com.pzj.core.product.entity.SeatRecordCollects;

/**
 * 
 * @author Administrator
 * @version $Id: SeatRecordReadMapper.java, v 0.1 2017年3月17日 下午2:58:02 Administrator Exp $
 */
public interface SeatRecordReadMapper {
	List<SeatRecord> querySeatInfoByTransactionId(@Param(value = "transactionId") String transactionId);

	List<SeatRecord> querySeatRecordByModel(@Param(value = "seatRecord") SeatRecord seatRecord);

	List<SeatRecord> queryOverdueSeatRecords();

	List<SeatRecordCollects> queryAreaSeatCollects(@Param(value = "seatRecord") SeatRecord seatRecord);
}
