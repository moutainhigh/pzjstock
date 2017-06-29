package com.pzj.core.product.read;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.product.entity.Area;
import com.pzj.core.product.entity.AreaQuery;
import com.pzj.core.product.entity.PageEntity;

public interface AreaReadMapper {
	/**
	 * 根据主键查询
	 * 
	 * @param areaId 区域ID
	 * @return Area
	 */
	Area selectAreaById(Long areaId);

	/**
	 * 通过区域集合查找区域列表
	 * @param list
	 * @return
	 */
	List<Area> queryAreaByIds(List<Long> list);

	/**
	 * 根据区域参数查询
	 * 
	 * @param area 区域实体
	 * @return List<Area>
	 */
	ArrayList<Area> selectAreasByParam(@Param(value = "areaQuery") AreaQuery area);

	/**
	 * 根据供应商id查询供应商下发布过区域的景区，分页
	 * 
	 * @param supplierId
	 * @return
	 */
	List<Area> queryScenicBySupplierId(@Param(value = "supplierId") Long supplierId,
			@Param(value = "page") PageEntity page);

	/**
	 * 
	 * 根据供应商id查询供应商下发布过区域的景区 统计
	 * @param supplierId
	 * @return
	 */
	Integer queryCountScenicBySupplierId(@Param(value = "supplierId") Long supplierId);

	/**
	 * 查询剧场区域详情
	 * @param supplierId
	 * @param scenicId
	 * @return List<Area>
	 */
	List<Area> queryAreaDetail(@Param(value = "supplierId") Long supplierId, @Param(value = "scenicId") Long scenicId);
}