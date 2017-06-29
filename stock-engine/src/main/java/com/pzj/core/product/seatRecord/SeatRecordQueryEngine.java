/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.seatRecord;

import com.pzj.commons.utils.CheckUtils;
import com.pzj.core.common.exception.StockException;
import com.pzj.core.product.common.exception.TheaterException;
import com.pzj.core.product.common.exception.TheaterExceptionCode;
import com.pzj.core.product.entity.*;
import com.pzj.core.product.enums.RecordCategory;
import com.pzj.core.product.enums.RecordState;
import com.pzj.core.product.enums.SeatType;
import com.pzj.core.product.model.QuerySeatRecordResponse;
import com.pzj.core.product.model.QueryValidSeatRecordResponse;
import com.pzj.core.product.model.assign.AssignedOrderQueryReqModel;
import com.pzj.core.product.model.seat.SeatInfoModel;
import com.pzj.core.product.model.seat.SeatRespModel;
import com.pzj.core.product.model.seatRecord.SeatRecordReqModel;
import com.pzj.core.product.model.statistics.AreaCollectRespModel;
import com.pzj.core.product.read.AreaReadMapper;
import com.pzj.core.product.read.AssignedOrderReadMapper;
import com.pzj.core.product.read.SeatRecordReadMapper;
import com.pzj.core.product.screeings.ScreeingsQueryByIdEngine;
import com.pzj.core.product.seatchar.QuerySeatchartEngine;
import com.pzj.core.product.write.SeatRecordWriteMapper;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 
 * @author Administrator
 * @version $Id: SeatRecordQueryEngine.java, v 0.1 2017年3月17日 下午3:05:12 Administrator Exp $
 */
@Component("seatRecordQueryEngine")
@Transactional(value = "stock.transactionManager")
public class SeatRecordQueryEngine {
	private static final Logger logger = LoggerFactory.getLogger(SeatRecordQueryEngine.class);
	@Resource
	private SeatRecordReadMapper seatRecordReadMapper;
	@Resource
	private QuerySeatchartEngine querySeatchartEngine;
	@Resource
	private SeatRecordWriteMapper seatRecordWriteMapper;
	@Resource
	private ScreeingsQueryByIdEngine screeingsQueryByIdEngine;
	@Resource
	private AssignedOrderReadMapper assignedOrderReadMapper;

	@Autowired
	private AreaReadMapper areaReadMapper;

	/**
	 * 根据订单id查询占座记录
	 * 
	 * @param transactionId
	 * @param serviceContext
	 * @return
	 */
	public Result<ArrayList<SeatInfoModel>> querySeatInfoByTransactionId(String transactionId,
			ServiceContext serviceContext) {
		try {
			List<SeatRecord> seatRecords = seatRecordReadMapper.querySeatInfoByTransactionId(transactionId);
			ArrayList<SeatInfoModel> seatInfoModels = convertSeatInfoModels(seatRecords);
			return new Result<>(seatInfoModels);
		} catch (Exception e) {
			logger.error("modified seat record error ", e);
			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}
	}

	public QueryValidSeatRecordResponse queryValidSeatRecordByTransactionId(String transactionId) {
		if (transactionId == null) {
			throw new TheaterException(TheaterExceptionCode.PARAMETER_EMPTY);
		}

		SeatRecord seatRecordParam = new SeatRecord();
		seatRecordParam.setTransactionId(transactionId);
		seatRecordParam.setState(RecordState.EFFECTIVER.getState());


		// 查询交易id的所有座位记录
		List<SeatRecord> seatRecords = seatRecordReadMapper.querySeatRecordByModel(seatRecordParam);

		if (seatRecords != null && !seatRecords.isEmpty()) {
			List<Area> areas = queryAreaBySeatRecord(seatRecords);

			SeatRecord seatRecord = seatRecords.get(0);
			Screeings screeings = screeingsQueryByIdEngine.queryScreeingsById(seatRecord.getScreeningId());

			return convertToQueryValidSeatRecordResponse(seatRecords, screeings, areas);
		}

		List<AssignedOrder> assignedOrders = assignedOrderReadMapper.selectAssignedOrderByTransaction(transactionId);
		if (assignedOrders != null && !assignedOrders.isEmpty()){
			List<Area> areas = queryAreaBySeatRecord2(assignedOrders);

			AssignedOrder assignedOrder = assignedOrders.get(0);
			Screeings screeings = screeingsQueryByIdEngine.queryScreeingsById(assignedOrder.getScreeningsId());

			return convertToQueryValidSeatRecordResponse2(screeings, assignedOrders, areas);
		}

		return null;
	}

	private QueryValidSeatRecordResponse convertToQueryValidSeatRecordResponse2(Screeings screeings, List<AssignedOrder> assignedOrders, List<Area> areas) {
		if (assignedOrders == null) {
			return null;
		}
		if (assignedOrders == null){
			assignedOrders = Collections.EMPTY_LIST;
		}

		List<QuerySeatRecordResponse> querySeatRecordResponses = new ArrayList<>();

		for (AssignedOrder seatRecord : assignedOrders) {
			QuerySeatRecordResponse res = new QuerySeatRecordResponse();

			res.setTransactionId(seatRecord.getTransactionId());
			res.setScreeningId(screeings.getId());
			res.setScreeningName(screeings.getName());

			res.setTravelDate(seatRecord.getTravelDate());


			for (Area area : areas){
				if (area.getId().equals(seatRecord.getAreaId())){
					res.setAreaId(area.getId());
					res.setAreaName(area.getName());
				}
			}

			querySeatRecordResponses.add(res);
		}

		QueryValidSeatRecordResponse response = new QueryValidSeatRecordResponse();
		response.setQuerySeatRecordResponses(querySeatRecordResponses);
		return response;
	}

	private List<Area> queryAreaBySeatRecord(List<SeatRecord> seatRecords){
		List<Long> areaIds = new ArrayList<>();
		for (SeatRecord seatRecord : seatRecords){
			areaIds.add(seatRecord.getAreaId());
		}

		if (areaIds.isEmpty()){
			return null;
		}

		List<Area> areas = areaReadMapper.queryAreaByIds(areaIds);
		return areas;
	}

	private List<Area> queryAreaBySeatRecord2(List<AssignedOrder> assignedOrders){
		List<Long> areaIds = new ArrayList<>();
		for (AssignedOrder assignedOrder : assignedOrders){
			areaIds.add(assignedOrder.getAreaId());
		}

		if (areaIds.isEmpty()){
			return null;
		}

		List<Area> areas = areaReadMapper.queryAreaByIds(areaIds);
		return areas;
	}

	private QueryValidSeatRecordResponse convertToQueryValidSeatRecordResponse(List<SeatRecord> seatRecords,
			Screeings screeings, List<Area> areas) {
		if (seatRecords == null) {
			return null;
		}
		if (areas == null){
			areas = Collections.EMPTY_LIST;
		}

		List<QuerySeatRecordResponse> querySeatRecordResponses = new ArrayList<>();

		for (SeatRecord seatRecord : seatRecords) {
			QuerySeatRecordResponse res = new QuerySeatRecordResponse();
			/*			if (screeings.getId().equals(seatRecord.getScreeningId())){
							throw new TheaterException();
						}
						if (area.getId().equals(seatRecord.getAreaId())){
							throw new TheaterException();
						}*/

			res.setRecordId(seatRecord.getRecordId());
			res.setTransactionId(seatRecord.getTransactionId());
			res.setScreeningId(screeings.getId());
			res.setScreeningName(screeings.getName());
			res.setSeatId(seatRecord.getSeatId());
			res.setSeatName(seatRecord.getSeatName());
			res.setTravelDate(seatRecord.getTravelDate());
			res.setOperatorId(seatRecord.getOperatorId());
			res.setCategory(seatRecord.getCategory());

			for (Area area : areas){
				if (area.getId().equals(seatRecord.getAreaId())){
					res.setAreaId(area.getId());
					res.setAreaName(area.getName());
				}
			}

			querySeatRecordResponses.add(res);
		}

		QueryValidSeatRecordResponse response = new QueryValidSeatRecordResponse();
		response.setQuerySeatRecordResponses(querySeatRecordResponses);
		return response;
	}

	/**
	 * 根据游玩时间和座位id查询有效的记录
	 * 
	 * @param theaterDate
	 * @param seatIds
	 * @return
	 */
	public List<SeatRecord> queryValidSeatRecord(Date theaterDate, List<Long> seatIds) {
		List<SeatRecord> seatRecords = seatRecordWriteMapper.queryValidSeatRecord(theaterDate, seatIds);
		return seatRecords;
	}

	public List<SeatRecord> querySeatRecordByModel(SeatRecord updaetRecord) {
		List<SeatRecord> seatRecords = seatRecordWriteMapper.querySeatRecordByModel(updaetRecord);
		return seatRecords;
	}

	/**
	 * 查询占座记录
	 * 
	 * @param model
	 * @param serviceContext
	 * @return
	 */
	public ArrayList<SeatRespModel> querySeatStateByRecord(SeatRecordReqModel model, ServiceContext serviceContext) {
		SeatRecord seatRecord = convertSeatRecord(model);
		List<SeatRecord> seatRecords = seatRecordReadMapper.querySeatRecordByModel(seatRecord);
		ArrayList<SeatRespModel> seatRespModels = convertSeatRespModels(seatRecords);
		return seatRespModels;
	}

	/**
	 * 场次下区域的各状态座位数量汇总
	 * 
	 * @param model
	 * @param context
	 * @return
	 */
	public ArrayList<AreaCollectRespModel> queryAreaCollects(SeatRecordReqModel model, ServiceContext context) {
		if (logger.isDebugEnabled()) {
			logger.debug("The parameter of the query seats number is {} ", JSONConverter.toJson(model));
		}
		SeatRecord seatRecord = convertSeatRecord(model);
		List<SeatRecordCollects> collects = seatRecordReadMapper.queryAreaSeatCollects(seatRecord);
		Map<Long, AreaCollectRespModel> respMap = new HashMap<Long, AreaCollectRespModel>();

		for (SeatRecordCollects seatRecordCollects : collects) {
			Long areaId = seatRecordCollects.getAreaId();
			AreaCollectRespModel collectRespModel = new AreaCollectRespModel();
			if (CheckUtils.isNull(respMap.get(areaId))) {
				collectRespModel.setAreaId(areaId);
				collectRespModel.setAreaName(seatRecordCollects.getAreaName());
				//首次需要查询区域下的座位
				SeatCharQuery seatChar = new SeatCharQuery();
				seatChar.setAreaId(areaId);
				seatChar.setType(SeatType.IS_SEAT.getState());
				List<SeatChar> seatChars = querySeatchartEngine.querySeatCharsByModel(seatChar, context);
				collectRespModel.setTotalNum(CheckUtils.isNull(seatChars) ? 0 : seatChars.size());
			} else {
				collectRespModel = respMap.get(areaId);
			}
			setSeatNum(collectRespModel, seatRecordCollects);
			respMap.put(areaId, collectRespModel);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("The areaId-AreaCollectRespModel mappings {} ", JSONConverter.toJson(respMap));
		}
		ArrayList<AreaCollectRespModel> resultLists = new ArrayList<>();
		Iterator<Long> seatNumIt = respMap.keySet().iterator();
		while (seatNumIt.hasNext()) {
			Long areaId = seatNumIt.next();
			resultLists.add(respMap.get(areaId));
		}
		return resultLists;
	}

	private void setSeatNum(AreaCollectRespModel collectRespModel, SeatRecordCollects seatRecordCollects) {
		if (seatRecordCollects.getCategory() == RecordCategory.SOLD.getState()) {
			collectRespModel.setOccupiedNum(seatRecordCollects.getSeatNum());
		} else if (seatRecordCollects.getCategory() == RecordCategory.LOCKING.getState()) {
			collectRespModel.setLockedNum(seatRecordCollects.getSeatNum());
		} else if (seatRecordCollects.getCategory() == RecordCategory.RESERVED.getState()) {
			collectRespModel.setPreOccupiedNum(seatRecordCollects.getSeatNum());
		}
		collectRespModel.setUnOccupiedNum(collectRespModel.getTotalNum() - collectRespModel.getPreOccupiedNum()
				- collectRespModel.getLockedNum() - collectRespModel.getOccupiedNum());
	}

	private SeatRecord convertSeatRecord(SeatRecordReqModel model) {
		SeatRecord seatRecord = new SeatRecord();
		seatRecord.setScreeningId(model.getScreeingId());
		seatRecord.setTravelDate(model.getTheaterDate());
		seatRecord.setState(RecordState.EFFECTIVER.getState());
		seatRecord.setAreaId(model.getAreaId());
		if (CheckUtils.isNotNull(model.getRecordCategory())) {
			RecordCategory recordCategory = model.getRecordCategory();
			seatRecord.setCategory(recordCategory.getState());
		}
		return seatRecord;
	}

	private ArrayList<SeatRespModel> convertSeatRespModels(List<SeatRecord> seatRecords) {
		ArrayList<SeatRespModel> seatRespModels = new ArrayList<>();
		for (SeatRecord seatRecord : seatRecords) {
			SeatRespModel respModel = new SeatRespModel();
			respModel.setSeatId(seatRecord.getSeatId());
			respModel.setAreaId(seatRecord.getAreaId());
			respModel.setSeatName(seatRecord.getSeatName());
			respModel.setShowState(seatRecord.getCategory());
			respModel.setTransactionId(seatRecord.getTransactionId());
			respModel.setOperateUserId(seatRecord.getOperatorId());
			seatRespModels.add(respModel);
		}
		return seatRespModels;
	}

	private ArrayList<SeatInfoModel> convertSeatInfoModels(List<SeatRecord> seatRecords) {
		ArrayList<SeatInfoModel> seatInfoModels = new ArrayList<SeatInfoModel>();
		for (SeatRecord seatRecord : seatRecords) {
			SeatInfoModel seatInfoModel = new SeatInfoModel();
			seatInfoModel.setAreaId(seatRecord.getAreaId());
			seatInfoModel.setScreeingId(seatRecord.getScreeningId());
			seatInfoModel.setSeatId(seatRecord.getSeatId());
			seatInfoModel.setSeatName(seatRecord.getSeatName());
			seatInfoModels.add(seatInfoModel);
		}
		return seatInfoModels;
	}

	public QueryValidSeatRecordResponse queryOverdueSeatRecords(){
		List<SeatRecord> seatRecords = seatRecordWriteMapper.queryOverdueSeatRecords();
		if(seatRecords == null || seatRecords.isEmpty()){
			return new QueryValidSeatRecordResponse();
		}
		return 	convertToQueryValidSeatRecordResponse(seatRecords);
	}

	private QueryValidSeatRecordResponse convertToQueryValidSeatRecordResponse(List<SeatRecord> seatRecords){
		List<QuerySeatRecordResponse> querySeatRecordResponses = new ArrayList<>();

		for (SeatRecord seatRecord : seatRecords) {
			QuerySeatRecordResponse res = new QuerySeatRecordResponse();
			res.setRecordId(seatRecord.getRecordId());
			res.setScreeningId(seatRecord.getScreeningId());
			res.setSeatId(seatRecord.getSeatId());
			res.setSeatName(seatRecord.getSeatName());
			res.setTravelDate(seatRecord.getTravelDate());
			res.setOperatorId(seatRecord.getOperatorId());
			res.setAreaId(seatRecord.getAreaId());
			querySeatRecordResponses.add(res);
		}
		QueryValidSeatRecordResponse response = new QueryValidSeatRecordResponse();
		response.setQuerySeatRecordResponses(querySeatRecordResponses);
		return response;
	}
}
