package com.pzj.core.product.read;

import java.util.List;

import com.pzj.core.product.entity.SeatCharQuery;
import org.apache.ibatis.annotations.Param;

import com.pzj.core.product.entity.SeatChar;

public interface SeatCharReadMapper {
	/**
	 * 统计区域座位数量
	 * @param areaId
	 * @return Integer
	 */
	Integer countAreaSeat(Long areaId);

	/**
	 * 根据区域id查询座位集合
	 * @param areaId
	 * @return List<SeatChar>
	 */
	List<SeatChar> querySeatByArea(Long areaId);

	/**
	 * 根据景区查询座位集合
	 * @param scenicId
	 * @return List<SeatChar>
	 */
	List<SeatChar> querySeatByScenic(Long scenicId);

	/**
	 * 根据对象查询座位信息
	 * 
	 * @param seatChar
	 * @return
	 */
	List<SeatChar> querySeatCharsByModel(@Param(value = "seatChar") SeatCharQuery seatChar);
}
