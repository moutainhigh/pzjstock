package com.pzj.core.product.read;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.product.entity.PageEntity;
import com.pzj.core.product.entity.Screeings;

public interface ScreeingsReadMapper {
	/**
	 * 根据主键查询
	 * 
	 * @param screeingsId 场次ID
	 * @return Screeings
	 */
	public Screeings selectScreeingsById(Long screeingsId);

	/**
	 * 根据场次参数查询
	 * 
	 * @param area 区域实体
	 * @return List<Area>
	 */
	ArrayList<Screeings> selectScreeingsesByParam(@Param(value = "screeings") Screeings screeings);

	/**
	 * 分页查找剧场场次列表信息
	 * @param pageEntity
	 * @param supplierId
	 * @return List<Screeings>
	 */
	public List<Screeings> queryTheaterScreeingByPage(@Param(value = "pageEntity") PageEntity pageEntity,
			@Param(value = "supplierId") Long supplierId);

	/**
	 * 统计供应商下有多少剧场场次
	 * @param supplierId
	 * @return int
	 */
	public int countTheaterScreeing(Long supplierId);

	/**
	 * 查找供应商下所有的场次集合
	 * @param supplierId
	 * @return List<Screeings>
	 */
	public List<Screeings> queryUserOfScreeing(Long supplierId);

	/**
	 * 查询场次预订率
	 * @param pageEntity
	 * @param scenicIds
	 * @param screeingIds
	 * @return List<Screeings>
	 */
	public List<Screeings> queryScreeingOrders(@Param(value = "pageEntity") PageEntity pageEntity,
			@Param(value = "scenicIds") Set<Long> scenicIds, @Param(value = "screeingIds") List<Long> screeingIds);

	/**
	 * 通过场次id集合获取场次集合
	 * @param list
	 * @return List<Screeings>
	 */
	public List<Screeings> queryScreeingByIds(@Param(value = "list") List<Long> list);
}