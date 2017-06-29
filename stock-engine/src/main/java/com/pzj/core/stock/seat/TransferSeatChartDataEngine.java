package com.pzj.core.stock.seat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.common.utils.CRCUtils;
import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.entity.Acting;
import com.pzj.core.product.entity.PageEntity;
import com.pzj.core.product.entity.SeatChar;
import com.pzj.core.product.entity.SeatScaleEntity;
import com.pzj.core.product.entity.TheaterInfo;
import com.pzj.core.product.read.ActingReadMapper;
import com.pzj.core.product.write.SeatCharWriteMapper;
import com.pzj.core.product.write.SeatScaleWriteMapper;
import com.pzj.core.product.write.TheaterWriteMapper;
import com.pzj.core.stock.entity.SeatChart;
import com.pzj.core.stock.read.SeatChartReadMapper;
import com.pzj.framework.idgen.IDGenerater;

@Component("transferSeatChartDataEngine")
public class TransferSeatChartDataEngine {
	private final Logger logger = LoggerFactory.getLogger(TransferSeatChartDataEngine.class);
	@Resource(name = "seatChartReadMapper")
	private SeatChartReadMapper seatChartReadMapper;
	@Resource(name = "actingReadMapper")
	private ActingReadMapper actingReadMapper;

	@Resource(name = "seatCharWriteMapper")
	private SeatCharWriteMapper seatCharWriteMapper;
	@Resource(name = "seatScaleWriteMapper")
	private SeatScaleWriteMapper seatScaleWriteMapper;
	@Resource(name = "theaterWriteMapper")
	private TheaterWriteMapper theaterWriteMapper;

	@Resource(name = "idGenerater")
	private IDGenerater idGenerater;

	@Transactional(value = "stock.transactionManager", timeout = 2)
	public void transferSeatChart() {
		int totalScenic = seatChartReadMapper.countScenicNum();
		if (totalScenic <= 0) {
			return;
		}
		int curPage = 1, pageSize = 10, pageBase = totalScenic / pageSize;
		int pageNum = (totalScenic % pageSize == 0) ? pageBase : pageBase + 1;
		List<SeatChar> seatChars = new ArrayList<SeatChar>();
		do {
			PageEntity pageEntity = new PageEntity(curPage, pageSize);
			List<SeatChart> seatCharts = seatChartReadMapper.queryScenicSeatChart(pageEntity);
			if (!CommonUtils.checkCollectionIsNull(seatCharts)) {
				for (SeatChart seatChart : seatCharts) {
					List<SeatChar> tempSeats = checkSeatChart(seatChart);
					if (!tempSeats.isEmpty()) {
						seatChars.addAll(tempSeats);
					}
				}
			}
		} while (curPage++ < pageNum);

		if (seatChars.size() > 0) {
			//批量添加座位
			int fromIndex = 0, toIndex = 200, remainSize = seatChars.size();
			toIndex = toIndex > remainSize ? remainSize : toIndex;
			do {
				List<SeatChar> tempSeatChars = seatChars.subList(fromIndex, toIndex);
				seatCharWriteMapper.insertBatch(tempSeatChars);
				toIndex = toIndex + 200 > remainSize ? remainSize : toIndex + 200;
				fromIndex += 200;
				logger.info("transferSeatChart tempSeatChars size:{}", tempSeatChars.size());
			} while (fromIndex < remainSize);

		}

		//批量添加坐标
		List<Acting> actings = actingReadMapper.queryAllActing();
		if (null != actings && !actings.isEmpty()) {
			List<SeatScaleEntity> seatScales = initSeatScale(actings);
			int fromIndex = 0, toIndex = 200, remainSize = seatScales.size();
			toIndex = toIndex > remainSize ? remainSize : toIndex;
			do {
				List<SeatScaleEntity> tempSeatScales = seatScales.subList(fromIndex, toIndex);
				seatScaleWriteMapper.insertBatch(tempSeatScales);
				toIndex = toIndex + 200 > remainSize ? remainSize : toIndex + 200;
				fromIndex += 200;
				logger.info("transferSeatChart tempSeatScales size:{}", tempSeatScales.size());
			} while (fromIndex < remainSize);

			//批量添加剧场信息
			List<TheaterInfo> theaterInfos = initTheaters(actings);
			theaterWriteMapper.insertBatch(theaterInfos);
		}
	}

	private List<TheaterInfo> initTheaters(List<Acting> actings) {
		List<TheaterInfo> theaterInfos = new ArrayList<TheaterInfo>();
		Map<Long, Boolean> scenicMap = new HashMap<>();
		TheaterInfo theater = null;
		for (Acting acting : actings) {
			if (scenicMap.get(acting.getScenicId()) == null) {
				theater = new TheaterInfo();
				theater.setTheaterId(acting.getScenicId());
				theater.setSeatState(1);
				theater.setSortType(1);
				theater.setLineNum(100);
				theater.setColumnNum(100);
				theaterInfos.add(theater);
				scenicMap.put(acting.getScenicId(), true);
			}

		}
		return theaterInfos;
	}

	/**
	 * 初始化横纵坐标
	 * @param actings
	 * @return
	 */
	private List<SeatScaleEntity> initSeatScale(List<Acting> actings) {
		List<SeatScaleEntity> seatScales = new ArrayList<SeatScaleEntity>();
		for (Acting acting : actings) {
			int count = 0, type = 0;
			while (++count < 101) {
				SeatScaleEntity seatScale = new SeatScaleEntity();
				seatScale.setId(idGenerater.nextId());
				seatScale.setName(count + "");
				seatScale.setPosition(count);
				seatScale.setScenicId(acting.getScenicId());
				seatScale.setSupplierId(acting.getSupplierId());
				seatScale.setType(type);
				seatScales.add(seatScale);
				if (count == 100 && type == 0) {
					count = 0;
					type = 1;
				}
			}
		}
		return seatScales;
	}

	/**
	 * 检查解析座位图
	 * @param seatChart
	 * @return
	 */
	private List<SeatChar> checkSeatChart(SeatChart seatChart) {
		List<SeatChar> seatChars = new ArrayList<SeatChar>();
		String seatMap = seatChart.getSeatMaps();
		if (seatMap.indexOf(";") < 0 || seatMap.indexOf(",") < 0) {
			return seatChars;
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
					SeatChar seatChar = new SeatChar();
					seatChar.setId(idGenerater.nextId());
					seatChar.setAbscissa(CommonUtils.getLinePos(prefixSeat));
					seatChar.setAreaId(seatChart.getAreaId());
					seatChar.setColumnName(seat);
					seatChar.setLineName(prefixSeat);
					seatChar.setOrdinate(Integer.parseInt(seat));
					seatChar.setScenicId(seatChart.getScenicId());
					seatChar.setNameType(0);
					seatChar.setSeatName(seatNum);
					seatChar.setType(1);
					seatChar.setSeatUnique(seatUnique(seatChar));
					seatChars.add(seatChar);
				}
			}
		}
		return seatChars;
	}

	private Long seatUnique(SeatChar seatChar) {
		StringBuffer sb = new StringBuffer();
		sb.append(seatChar.getScenicId());
		sb.append(":");
		sb.append(seatChar.getAbscissa());
		sb.append(":");
		sb.append(seatChar.getOrdinate());
		sb.append(":");
		sb.append(seatChar.getAreaId());

		Long crcUniq = CRCUtils.convertUniqueLong(sb.toString());

		return crcUniq;
	}
}
