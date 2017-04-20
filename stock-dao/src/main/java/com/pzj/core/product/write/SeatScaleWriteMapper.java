package com.pzj.core.product.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.product.entity.SeatScaleEntity;

public interface SeatScaleWriteMapper {
	int insertBatch(@Param(value = "seatScales") List<SeatScaleEntity> seatScales);
}
