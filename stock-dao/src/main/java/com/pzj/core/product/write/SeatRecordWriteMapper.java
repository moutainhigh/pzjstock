/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.write;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.product.entity.SeatRecord;
import com.pzj.core.product.entity.SeatRecordUpdate;

/**
 * 
 * @author Administrator
 * @version $Id: SeatRecordWriteMapper.java, v 0.1 2017年3月16日 下午1:50:41 Administrator Exp $
 */
public interface SeatRecordWriteMapper {
	//新增
	Integer insertBatchSeatRecord(@Param(value = "seatRecords") List<SeatRecord> seatRecords);

	Integer sortModifySeatRecord(@Param(value = "recordUpdate") SeatRecordUpdate recordUpdate);


	List<SeatRecord> queryOverdueSeatRecords();

	int updateSeatRecords(List<SeatRecord> seatRecords);

	List<SeatRecord> selectExistValidSeatRecord(@Param("screeningId") Long screeningId,
			@Param("travelDate") Date travelDate, @Param("seatIds") List<Long> seatIds);

	int countExistValidSeatRecordByTransactionScreeningsArea(@Param("transactionId") String transactionId,
															 @Param("screeningsId") Long screeningsId,
															 @Param("areaId") Long areaId);

	/**
	 * 根据演出时间和座位id查询有效记录
	 * 
	 * @param theaterDate
	 * @param seatIds
	 * @return
	 */
	List<SeatRecord> queryValidSeatRecord(@Param(value = "theaterDate") Date theaterDate,
			@Param(value = "seatIds") List<Long> seatIds);

	/**
	 * 根据对象查询占座记录
	 * 
	 * @param seatRecord
	 * @return
	 */
	List<SeatRecord> querySeatRecordByModel(@Param(value = "seatRecord") SeatRecord seatRecord);
}
