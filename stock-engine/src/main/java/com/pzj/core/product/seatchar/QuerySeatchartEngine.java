package com.pzj.core.product.seatchar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.product.entity.SeatChar;
import com.pzj.core.product.enums.ShowSeatStateEnum;
import com.pzj.core.product.model.seat.SeatReqModel;
import com.pzj.core.product.model.seat.SeatRespModel;
import com.pzj.core.product.model.seatRecord.SeatRecordReqModel;
import com.pzj.core.product.read.SeatCharReadMapper;
import com.pzj.core.product.seatRecord.SeatRecordQueryEngine;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.framework.context.ServiceContext;

@Component("querySeatchartEngine")
public class QuerySeatchartEngine {
	private static final Logger logger = LoggerFactory.getLogger(QuerySeatchartEngine.class);
	@Resource(name = "seatRecordQueryEngine")
	private SeatRecordQueryEngine seatRecordQueryEngine;
	@Resource(name = "seatCharReadMapper")
	private SeatCharReadMapper seatCharReadMapper;

	public ArrayList<SeatRespModel> queryNewestTheaterSeatChart(SeatReqModel reqModel, ServiceContext serviceContext) {
		ArrayList<SeatRespModel> seats = new ArrayList<SeatRespModel>();
		List<SeatChar> seatChars = seatCharReadMapper.querySeatByScenic(reqModel.getScenicId());
		if (seatChars != null && !seatChars.isEmpty()) {
			SeatRecordReqModel seatRecord = new SeatRecordReqModel();
			seatRecord.setTheaterDate(reqModel.getShowTime());
			seatRecord.setScreeingId(reqModel.getScreeingId());
			Map<Long, SeatRespModel> occupySeats = getOccupySeat(seatRecord, serviceContext);

			for (SeatChar seatChar : seatChars) {
				seats.add(initSeatResp(seatChar, occupySeats));
			}
		}

		return seats;
	}

	public ArrayList<SeatRespModel> queryNewestAreaSeatChart(SeatReqModel reqModel, ServiceContext serviceContext) {
		ArrayList<SeatRespModel> seats = new ArrayList<SeatRespModel>();
		List<SeatChar> seatChars = seatCharReadMapper.querySeatByArea(reqModel.getAreaId());
		if (seatChars != null && !seatChars.isEmpty()) {
			SeatRecordReqModel seatRecord = new SeatRecordReqModel();
			seatRecord.setScreeingId(reqModel.getScreeingId());
			seatRecord.setTheaterDate(reqModel.getShowTime());
			seatRecord.setAreaId(reqModel.getAreaId());
			Map<Long, SeatRespModel> seatState = getOccupySeat(seatRecord, serviceContext);
			for (SeatChar seatChar : seatChars) {
				seats.add(initSeatResp(seatChar, seatState));
			}
		}

		return seats;
	}

	private Map<Long, SeatRespModel> getOccupySeat(SeatRecordReqModel seatRecord, ServiceContext serviceContext) {
		ArrayList<SeatRespModel> occupySeats = seatRecordQueryEngine.querySeatStateByRecord(seatRecord, serviceContext);
		Map<Long, SeatRespModel> seatState = new HashMap<Long, SeatRespModel>();
		if (occupySeats != null && !occupySeats.isEmpty()) {
			for (SeatRespModel occupySeat : occupySeats) {
				seatState.put(occupySeat.getSeatId(), occupySeat);
			}
		}
		return seatState;
	}

	private SeatRespModel initSeatResp(SeatChar seatChar, Map<Long, SeatRespModel> occupySeats) {
		if (seatChar == null) {
			return null;
		}
		SeatRespModel seatRespModel = new SeatRespModel();
		seatRespModel.setSeatId(seatChar.getId());
		seatRespModel.setAreaId(seatChar.getAreaId());
		seatRespModel.setSeatName(seatChar.getColumnName() + "_" + seatChar.getLineName());
		seatRespModel.setSaleState(1);
		seatRespModel.setxPos(seatChar.getAbscissa());
		seatRespModel.setyPos(seatChar.getOrdinate());
		SeatRespModel occupySeat = occupySeats.get(seatChar.getId());
		Integer showState = 1;
		if (occupySeat != null) {
			showState = occupySeat.getShowState();
			if (ShowSeatStateEnum.SELECTED.getShowSeatState() == showState) {
				seatRespModel.setTransactionId(occupySeat.getTransactionId());
			}
		}
		seatRespModel.setShowState(showState);

		return seatRespModel;
	}

	public List<SeatChar> querySeatCharsByModel(SeatChar seatChar, ServiceContext context) {
		try {
			List<SeatChar> seatChars = seatCharReadMapper.querySeatCharsByModel(seatChar);
			return seatChars;
		} catch (Exception e) {
			logger.error("query seats by model acting error ", e);
			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

	}

	/**
	 * 統計区域座位总数
	 * @param areaId
	 * @return Integer
	 */
	public Integer totalAreaSeat(Long areaId) {
		return seatCharReadMapper.countAreaSeat(areaId);
	}
}
