/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.product.entity.TheaterInfo;

/**
 * 
 * @author Administrator
 * @version $Id: TheaterWriteMapper.java, v 0.1 2017年3月29日 下午2:05:08 Administrator Exp $
 */
public interface TheaterWriteMapper {
	Integer createTheaterInfo(@Param(value = "theater") TheaterInfo theaterInfo);

	Integer updateTheaterInfo(@Param(value = "theater") TheaterInfo theaterInfo);

	TheaterInfo queryTheaterByTheaterId(@Param(value = "theaterId") Long theaterId);

	void insertBatch(@Param(value = "theaterInfos") List<TheaterInfo> theaterInfos);
}
