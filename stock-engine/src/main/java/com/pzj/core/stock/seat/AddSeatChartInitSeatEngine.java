/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.seat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.entity.Area;
import com.pzj.core.product.read.AreaReadMapper;
import com.pzj.core.stock.entity.Seat;
import com.pzj.core.stock.entity.SeatChart;
import com.pzj.core.stock.exception.errcode.SeatExceptionCode;
import com.pzj.core.stock.exception.seat.SeatChartDataErrorException;
import com.pzj.core.stock.model.SeatChartModel;
import com.pzj.core.stock.write.SeatChartWriteMapper;
import com.pzj.core.stock.write.SeatWriteMapper;
import com.pzj.framework.toolkit.Check;

/**
 * 
 * @author Administrator
 * @version $Id: AddSeatChartInitSeatEngine.java, v 0.1 2016年9月2日 下午6:01:04 Administrator Exp $
 */
@Component("addSeatChartInitSeatEngine")
public class AddSeatChartInitSeatEngine {
	@Resource(name = "seatChartWriteMapper")
	private SeatChartWriteMapper seatChartWriteMapper;
	@Autowired
	private SeatWriteMapper seatWriteMapper;
	@Autowired
	private AreaReadMapper areaReadMapper;

	@Transactional(value = "stock.transactionManager", timeout = 1)
	public boolean addSeatChartInitSeat(SeatChartModel seatChartModel) {
		//验证景区对应的区域是否存在
		Area area = new Area();
		area.setId(seatChartModel.getAreaId());
		area.setScenicId(seatChartModel.getScenicId());
		ArrayList<Area> areaList = areaReadMapper.selectAreasByParam(area);
		if (Check.NuNCollections(areaList)) {
			return Boolean.FALSE;
		}

		//初始化座位图信息
		SeatChart seatChart = initSeatChart(seatChartModel);
		//验证座位图是否合规
		Set<String> seatSet = checkSeatChart(seatChart);

		//座位图入库
		seatChartWriteMapper.insert(seatChart);

		//初始化座位图数据
		List<Seat> seatList = initSeatList(seatSet, seatChart.getId());

		//批量添加座位
		seatWriteMapper.addBatchSeat(seatList);

		return Boolean.TRUE;
	}

	/**
	 * 初始化座位图数据
	 * @param seatSet
	 * @param seatChartId
	 * @return
	 */
	private List<Seat> initSeatList(Set<String> seatSet, Long seatChartId) {
		List<Seat> seatList = new ArrayList<Seat>();
		Iterator<String> itera = seatSet.iterator();
		while (itera.hasNext()) {
			String seatNum = itera.next();
			Seat seat = new Seat();
			seat.setStockSeatChartId(seatChartId);
			seat.setSeatNum(seatNum);
			seatList.add(seat);
		}
		return seatList;
	}

	/**
	 * 检查解析座位图
	 * @param seatChart
	 * @return
	 */
	private Set<String> checkSeatChart(SeatChart seatChart) {
		Set<String> seatSet = new HashSet<String>();
		String seatMap = seatChart.getSeatMaps();
		if (seatMap.indexOf(";") < 0 || seatMap.indexOf(",") < 0) {
			throw new SeatChartDataErrorException(SeatExceptionCode.SEAT_CHART_DATA_ERR_MSG);
		}
		String[] rowData = seatMap.split(";");
		for (String row : rowData) {
			String[] seatInfo = row.split(",");
			String prefixSeat = null;

			for (String seat : seatInfo) {
				if (CommonUtils.checkSeatRowLegal(seat)) {
					prefixSeat = seat;
				}
				if (seat.matches("^[0-9]+$")) {
					String seatNum = prefixSeat + "_" + seat;
					seatSet.add(seatNum);
				}
			}
		}
		if (seatSet.size() == 0) {
			throw new SeatChartDataErrorException(SeatExceptionCode.SEAT_CHART_DATA_ERR_MSG);
		}
		return seatSet;
	}

	/**
	 * 初始化座位图信息
	 * @param seatChartModel
	 * @return
	 */
	private SeatChart initSeatChart(SeatChartModel seatChartModel) {
		SeatChart seatChart = new SeatChart();
		seatChart.setScenicId(seatChartModel.getScenicId());
		seatChart.setAreaId(seatChartModel.getAreaId());
		seatChart.setSeatMaps(seatChartModel.getSeatMaps());
		seatChart.setTotalSeats(countSeatNum(seatChartModel.getSeatMaps()));
		seatChart.setSort(seatChartModel.getSort());
		seatChart.setCode(seatChartModel.getCode());
		return seatChart;
	}

	/**
	 * 统计座位图中座位数量
	 * @param seatMap
	 * @return
	 */
	private int countSeatNum(String seatMap) {
		int totalCount = 0;
		String[] rowArr = seatMap.split(";");
		for (String row : rowArr) {
			String[] lineArr = row.split(",");
			for (int i = 0; i < lineArr.length; i++) {
				if (i != 0) {
					if (lineArr[i].matches("^[0-9]+$")) {
						totalCount++;
					}
				}
			}
		}
		return totalCount;
	}
}
