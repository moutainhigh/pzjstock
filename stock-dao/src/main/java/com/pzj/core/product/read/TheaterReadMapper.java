/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.read;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.product.entity.TheaterInfo;

/**
 * 
 * @author Administrator
 * @version $Id: TheaterReadMapper.java, v 0.1 2017年3月29日 下午2:04:16 Administrator Exp $
 */
public interface TheaterReadMapper {
	TheaterInfo queryTheaterByTheaterId(@Param(value = "theaterId") Long theaterId);
}
