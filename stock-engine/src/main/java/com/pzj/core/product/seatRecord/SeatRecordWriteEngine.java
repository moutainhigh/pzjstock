package com.pzj.core.product.seatRecord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.pzj.core.product.area.AreaWriteEngine;
import com.pzj.core.product.assigned.AssignedOrderWriteEngine;
import com.pzj.core.product.common.exception.TheaterException;
import com.pzj.core.product.common.exception.TheaterExceptionCode;
import com.pzj.core.product.entity.AssignedOrder;
import com.pzj.core.product.entity.SeatChar;
import com.pzj.core.product.entity.SeatRecord;
import com.pzj.core.product.model.OccupyStockReqModel;
import com.pzj.core.product.model.OccupyStockReqsModel;
import com.pzj.core.product.seatchar.SeatCharWriteEngine;
import com.pzj.core.product.write.SeatRecordWriteMapper;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.idgen.IDGenerater;

/**
 * Created by Administrator on 2017-3-26.
 */
@Component
public class SeatRecordWriteEngine {
	private static final Logger logger = LoggerFactory.getLogger(SeatRecordWriteEngine.class);

	@Resource
	private IDGenerater idGenerater;
	@Resource
	private SeatRecordWriteMapper seatRecordWriteMapper;
	@Resource
	private AssignedOrderWriteEngine assignedOrderWriteEngine;
	@Resource
	private AreaWriteEngine areaWriteEngine;
	@Resource
	private SeatCharWriteEngine seatCharWriteEngine;
	@Resource
	private SeatStockEngine seatStockEngine;

	public Boolean occupyStock(OccupyStockReqsModel occupyStockReqsModel) {
		if (occupyStockReqsModel == null) {
			throw new TheaterException(TheaterExceptionCode.PARAMETER_EMPTY);
		}

		List<OccupyStockReqModel> occupyStockReqs = occupyStockReqsModel.getOccupyStockReqs();
		if (occupyStockReqs == null || occupyStockReqs.isEmpty()) {
			throw new TheaterException(TheaterExceptionCode.PARAMETER_EMPTY);
		}

		List<AssignedOrder> assignedOrders = new ArrayList<>();
		for (OccupyStockReqModel occupyStockReqModel : occupyStockReqs) {
			AssignedOrder assignedOrder = occupyStock(occupyStockReqModel);
			if (assignedOrder != null) {
				assignedOrders.add(assignedOrder);
			}
		}

		saveAssignedOrder(assignedOrders);
		return true;
	}

	private AssignedOrder occupyStock(OccupyStockReqModel occupyStockReqModel) {
		// 占库存
		seatStockEngine.occupyStock(occupyStockReqModel);

		// 是否需要占座
		boolean needOccupySeat = areaWriteEngine.needOccupySeat(occupyStockReqModel.getAreaId());
		if (!needOccupySeat) {
			return null;
		}

		checkTheaterParam(occupyStockReqModel);

		// 占座和释放座位
		occupyingAndReleaseSeats(occupyStockReqModel.getTransactionId(), occupyStockReqModel.getScreeningsId(),
				occupyStockReqModel.getAreaId(), occupyStockReqModel.getTravelDate(),
				occupyStockReqModel.getOperator(), occupyStockReqModel.getOccupyType(),
				occupyStockReqModel.getOccupySeatIds(), occupyStockReqModel.getReleaseSeatIds());

		// 获取占座任务的基本描述信息
		return assignedOrderWriteEngine.createAssignedOrderFrom(occupyStockReqModel);
	}

	private void saveAssignedOrder(List<AssignedOrder> assignedOrdersNoGroup) {
		if (assignedOrdersNoGroup == null || assignedOrdersNoGroup.isEmpty()) {
			return;
		}

		String transactionId = assignedOrdersNoGroup.get(0).getTransactionId();

		List<AssignedOrder> assignedOrders = assignedOrderWriteEngine.queryAssignedOrderByTransaction(transactionId);
		if (assignedOrders == null || assignedOrders.isEmpty()) {
			assignedOrders = groupAssignedOrder(assignedOrdersNoGroup);
		} else {
			for (AssignedOrder assignedOrder : assignedOrders) {
				if (assignedOrder.getState() != 1) {
					TheaterExceptionCode code = TheaterExceptionCode.ASSIGNED_RULE_NO_REDISTRIBUTION;
					String message = code.getTemplateMessage(assignedOrder.getAssignedId(), assignedOrder.getState());
					throw new TheaterException(code.getCode(), message);
				}
			}
		}

		for (AssignedOrder assignedOrder : assignedOrders) {
			// 更新待分配记录
			updateAssignedOrderOccupiedNum(assignedOrder);
			// 保存占座任务的基本描述信息
			assignedOrderWriteEngine.saveAssignedOrder(assignedOrder);
		}
	}

	/**
	 * 将交易ID、场次ID、区域ID相同的待分配合并，合并是将总分配数合并。
	 * @param assignedOrdersNoGroup
	 * @return
	 */
	private List<AssignedOrder> groupAssignedOrder(List<AssignedOrder> assignedOrdersNoGroup) {
		List<AssignedOrder> assignedOrders = new ArrayList<>();

		A: for (AssignedOrder assignedOrder : assignedOrdersNoGroup) {
			AssignedOrder copy = assignedOrder.copy();

			B: for (AssignedOrder ad : assignedOrders) {
				if (ad.getTransactionId().equals(copy.getTransactionId())
						&& ad.getScreeningsId().equals(copy.getScreeningsId())
						&& ad.getAreaId().equals(copy.getAreaId())) {
					ad.setUnOccupiedNum(ad.getUnOccupiedNum() + copy.getUnOccupiedNum());
					continue A;
				}
			}

			assignedOrders.add(copy);
		}

		return assignedOrders;
	}

	/**
	 * 校验占座参数是否完整
	 * @param occupyStockReqModel
	 */
	private void checkTheaterParam(OccupyStockReqModel occupyStockReqModel) {
		if (occupyStockReqModel.getAreaId() == null || occupyStockReqModel.getAreaId() == 0) {
			throw new TheaterException(TheaterExceptionCode.SEAT_RECORD_NULL_AREA);
		}
		if (occupyStockReqModel.getScreeningsId() == null || occupyStockReqModel.getScreeningsId() == 0) {
			throw new TheaterException(TheaterExceptionCode.SEAT_RECORD_NULL_SCREENING);
		}

		Integer occupyType = occupyStockReqModel.getOccupyType();
		if (occupyType == null) {
			throw new TheaterException(TheaterExceptionCode.SEAT_RECORD_NULL_CATEGORY);
		}

		SeatRecordCategory.checkSeatRecordCategoryValue(occupyType);

		Date travelDate = occupyStockReqModel.getTravelDate();
		if (travelDate == null) {
			throw new TheaterException(TheaterExceptionCode.SEAT_RECORD_NULL_TRAVEL_DATE);
		}
	}

	/**
	 * 对座位进行校验，释放座位，占座位。
	 *  @param transactionId
	 * @param screeningsId
	 * @param areaId
	 * @param travelDate
	 * @param operatorId
	 * @param occupyType
	 * @param occupySeatIds
	 * @param releaseSeatIds
	 */
	private int occupyingAndReleaseSeats(String transactionId, Long screeningsId, Long areaId, Date travelDate,
			Long operatorId, Integer occupyType, List<Long> occupySeatIds, List<Long> releaseSeatIds) {

		if (occupySeatIds == null) {
			occupySeatIds = Collections.EMPTY_LIST;
		}
		if (releaseSeatIds == null) {
			occupySeatIds = Collections.EMPTY_LIST;
		}

		if (Collections.EMPTY_LIST == occupySeatIds && Collections.EMPTY_LIST == releaseSeatIds) {
			return 0;
		}

		// 获取需要占的座位信息
		List<SeatChar> occupySeats = seatCharWriteEngine.querySeatCharByIds(occupySeatIds);
		checkForInvalidSeatId(occupySeatIds, occupySeats, areaId);

		// 获取需要释放的座位信息
		List<SeatChar> releaseSeats = seatCharWriteEngine.querySeatCharByIds(releaseSeatIds);
		checkForInvalidSeatId(releaseSeatIds, releaseSeats, areaId);

		// 查询场次、座位、游玩时间的有效的占座记录，所有需要占的和释放的
		List<SeatRecord> existSeatRecords = null;

		List<Long> seatIds = mergeList(occupySeatIds, releaseSeatIds);
		if (seatIds != null && !seatIds.isEmpty()) {
			existSeatRecords = seatRecordWriteMapper.selectExistValidSeatRecord(screeningsId, travelDate, seatIds);
		}

		if (existSeatRecords != null && !existSeatRecords.isEmpty()) {
			// 检查是否有权占座
			checkWhetherTheRightToOccupySeats(existSeatRecords, transactionId, operatorId);

			// 过滤掉已占的座位记录
			filterOutTheOccupiedSeatRecords(existSeatRecords, occupySeats, occupyType);

			// 取消座位记录
			releaseSeatRecord(existSeatRecords);
		}

		// 占座位
		return occupyingSeats(transactionId, screeningsId, travelDate, operatorId, occupyType, occupySeats);
	}

	/**
	 * 过滤掉已占的座位记录
	 * @param existSeatRecords
	 * @param occupySeats
	 * @param occupyType
	 */
	private void filterOutTheOccupiedSeatRecords(List<SeatRecord> existSeatRecords, List<SeatChar> occupySeats,
			Integer occupyType) {
		if (existSeatRecords == null || existSeatRecords.isEmpty() || occupySeats == null || occupySeats.isEmpty()) {
			return;
		}
		Map<Long, SeatRecord> existSeatRecordsMap = new HashMap<>(existSeatRecords.size());
		for (SeatRecord seatRecord : existSeatRecords) {
			existSeatRecordsMap.put(seatRecord.getSeatId(), seatRecord);
		}
		Iterator<SeatChar> iterator = occupySeats.iterator();
		while (iterator.hasNext()) {
			SeatChar next = iterator.next();
			if (existSeatRecordsMap.containsKey(next.getId())) {
				SeatRecord seatRecord = existSeatRecordsMap.get(next.getId());
				// 请求的座位如果已经是占座状态（occupyType），则过滤掉，不需要重复占
				if (seatRecord.getCategory().equals(occupyType)) {
					existSeatRecords.remove(seatRecord);
					iterator.remove();
				}
			}
		}
	}

	/**
	 * 合并List
	 * @param list1
	 * @param list2
	 * @param <T>
	 * @return
	 */
	private <T> List<T> mergeList(List<T> list1, List<T> list2) {
		List<T> mergeList = new ArrayList<>(list1.size() + list2.size());
		mergeList.addAll(list1);
		mergeList.addAll(list2);
		return mergeList;
	}

	/**
	 * 检查出无效的座位id
	 * @param seatIds
	 * @param seatChars
	 */
	private void checkForInvalidSeatId(List<Long> seatIds, List<SeatChar> seatChars, Long areaId) {
		ArrayList<Long> tmp = new ArrayList<>(seatIds);
		for (SeatChar seat : seatChars) {
			if (!seat.getAreaId().equals(areaId)) {
				TheaterExceptionCode code = TheaterExceptionCode.SEAT_RULE_AREA;
				String msg = code.getTemplateMessage(seat.getId(), areaId, seat.getAreaId());
				throw new TheaterException(code.getCode(), msg);
			}
			tmp.remove(seat.getId());
		}
		if (tmp.size() > 0) {
			TheaterExceptionCode code = TheaterExceptionCode.SEAT_ILLEGAL_ID;
			String msg = code.getTemplateMessage(seatIds);
			throw new TheaterException(code.getCode(), msg);
		}
	}

	/**
	 * 检查是否有权占座
	 * @param existSeatRecords
	 * @param transactionId
	 * @param operator
	 */
	private void checkWhetherTheRightToOccupySeats(List<SeatRecord> existSeatRecords, String transactionId,
			Long operator) {
		if (existSeatRecords == null || existSeatRecords.isEmpty()) {
			return;
		}

		for (SeatRecord seatRecord : existSeatRecords) {
			Integer category = seatRecord.getCategory();
			if (SeatRecordCategory.LockSeat.equals(category)) {
				if (!operator.equals(seatRecord.getOperatorId())) {
					// 不是当前操作人锁定的座位
					TheaterExceptionCode code = TheaterExceptionCode.SEAT_RECORD_RULE_NOT_THE_OWNER;
					String msg = code
							.getTemplateMessage(seatRecord.getRecordId(), seatRecord.getOperatorId(), operator);
					throw new TheaterException(code.getCode(), msg);
				}
			} else if (SeatRecordCategory.PreemptionSeat.equals(category)
					|| SeatRecordCategory.OccupyingSeat.equals(category)) {
				if (!transactionId.equals(seatRecord.getTransactionId())) {
					// 不是当前交易ID预占的座位
					TheaterExceptionCode code = TheaterExceptionCode.SEAT_RECORD_RULE_NOT_THE_TRANSACTION;
					String msg = code.getTemplateMessage(seatRecord.getRecordId(), seatRecord.getTransactionId(),
							transactionId);
					throw new TheaterException(code.getCode(), msg);
				}
			} else {
				throw new TheaterException(TheaterExceptionCode.SEAT_RECORD_ILLEGAL_CATEGORY);
			}
		}
	}

	/**
	 * 取消座位记录
	 * @param existSeatRecords
	 */
	private void releaseSeatRecord(List<SeatRecord> existSeatRecords) {
		if (existSeatRecords == null || existSeatRecords.isEmpty()) {
			return;
		}
		Date currentDate = new Date();
		List<SeatRecord> cancelSeatRecords = new ArrayList<>(existSeatRecords.size());
		for (SeatRecord seatRecord : existSeatRecords) {
			SeatRecord updateSeatRecord = new SeatRecord();
			updateSeatRecord.setRecordId(seatRecord.getRecordId());
			updateSeatRecord.setState(0);
			updateSeatRecord.setUpdateTime(currentDate);

			Long recordUnique = SeatRecordUtil.recordUnique(seatRecord.getScreeningId(), seatRecord.getSeatId(),
					seatRecord.getTravelDate(), UUID.randomUUID().toString());

			updateSeatRecord.setRecordUnique(recordUnique);

			cancelSeatRecords.add(updateSeatRecord);
		}
		seatRecordWriteMapper.updateSeatRecords(cancelSeatRecords);
	}

	/**
	 * 占座位
	 * @param transactionId
	 * @param screeningsId
	 * @param travelDate
	 * @param operatorId
	 * @param occupyType
	 * @param occupySeats
	 */
	private int occupyingSeats(String transactionId, Long screeningsId, Date travelDate, Long operatorId,
			Integer occupyType, List<SeatChar> occupySeats) {

		if (occupySeats == null || occupySeats.isEmpty()) {
			return 0;
		}

		List<SeatRecord> insertSeatRecords = new ArrayList<>(occupySeats.size());

		Date currentDate = new Date();

		for (SeatChar seat : occupySeats) {
			Long seatId = seat.getId();

			SeatRecord seatRecord = new SeatRecord();
			seatRecord.setTravelDate(travelDate);
			seatRecord.setTransactionId(transactionId);
			seatRecord.setScreeningId(screeningsId);
			seatRecord.setOperatorId(operatorId);
			seatRecord.setCategory(occupyType);
			seatRecord.setSeatId(seatId);
			seatRecord.setState(1);
			seatRecord.setCreateTime(currentDate);
			seatRecord.setAreaId(seat.getAreaId());
			seatRecord.setSeatName(seat.getSeatName());

			long newId = idGenerater.nextId();
			seatRecord.setRecordId(newId);

			Long hashCode = SeatRecordUtil.recordUnique(screeningsId, seatId, travelDate, null);
			seatRecord.setRecordUnique(hashCode);

			insertSeatRecords.add(seatRecord);
		}

		try {
			seatRecordWriteMapper.insertBatchSeatRecord(insertSeatRecords);
			return insertSeatRecords.size();
		} catch (Throwable throwable) {
			logger.error(throwable.getMessage(), throwable);
			if (throwable instanceof DuplicateKeyException
					|| throwable instanceof MySQLIntegrityConstraintViolationException) {
				logger.error("尝试占座时失败，已经被其他人抢占。参数为 {}", JSONConverter.toJson(insertSeatRecords));
				TheaterException theaterException = new TheaterException(
						TheaterExceptionCode.SEAT_RECORD_OCCUPYING_FILURE);
				throw theaterException;
			}
			throw new TheaterException(throwable);
		}
	}

	/**
	 * 更新待分配记录的已分配数量
	 * @param assignedOrder
	 */
	private void updateAssignedOrderOccupiedNum(AssignedOrder assignedOrder) {
		int latestOccupiedNum = seatRecordWriteMapper.countExistValidSeatRecordByTransactionScreeningsArea(
				assignedOrder.getTransactionId(), assignedOrder.getScreeningsId(), assignedOrder.getAreaId());

		if (latestOccupiedNum == assignedOrder.getUnOccupiedNum()) {
			assignedOrder.setState(0);
		} else if (latestOccupiedNum > assignedOrder.getUnOccupiedNum()) {
			throw new TheaterException(TheaterExceptionCode.SEAT_RECORD_RULE_SEATS_GREATER_STOCK);
		}

		assignedOrder.setOccupiedNum(latestOccupiedNum);
	}
}