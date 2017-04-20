package com.pzj.core.product.seatRecord;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.pzj.core.common.utils.CRCUtils;
import com.pzj.core.product.area.AreaCommonInfo;
import com.pzj.core.product.area.AreaSeatMode;
import com.pzj.core.product.area.AreaSeatType;
import com.pzj.core.product.area.AreaWriteEngine;
import com.pzj.core.product.assigned.AssignedOrderWriteEngine;
import com.pzj.core.product.common.exception.TheaterException;
import com.pzj.core.product.common.exception.TheaterExceptionCode;
import com.pzj.core.product.entity.AssignedOrder;
import com.pzj.core.product.entity.SeatChar;
import com.pzj.core.product.entity.SeatRecord;
import com.pzj.core.product.enums.SeatType;
import com.pzj.core.product.model.OccupyStockReqModel;
import com.pzj.core.product.model.OccupyStockReqsModel;
import com.pzj.core.product.model.area.AreaModel;
import com.pzj.core.product.seatchar.SeatCharWriteEngine;
import com.pzj.core.product.write.SeatRecordWriteMapper;
import com.pzj.core.stock.entity.Seat;
import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.model.OccupyStockRequestModel;
import com.pzj.core.stock.model.StockQueryRequestModel;
import com.pzj.core.stock.service.StockService;
import com.pzj.core.stock.stockquery.QueryStockByRuleEngine;
import com.pzj.core.stock.stockquery.StockQueryParamEngine;
import com.pzj.core.stock.stockupdate.CommonTradeStockEngine;
import com.pzj.core.stock.write.StockRuleWriteMapper;
import com.pzj.core.stock.write.StockWriteMapper;
import com.pzj.framework.context.Result;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.idgen.IDGenerater;
import com.pzj.framework.toolkit.TimeHelper;
import org.joda.time.DateTime;
import org.omg.CORBA.TIMEOUT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017-3-26.
 */
@Component
@Transactional(value = "stock.transactionManager")
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
    private CommonTradeStockEngine commonTradeStockEngine;

    public Boolean occupyStock(OccupyStockReqsModel occupyStockReqsModel) {
        if (occupyStockReqsModel == null){
            throw new TheaterException(TheaterExceptionCode.PARAMETER_EMPTY);
        }

        List<OccupyStockReqModel> occupyStockReqs = occupyStockReqsModel.getOccupyStockReqs();
        if (occupyStockReqs == null || occupyStockReqs.isEmpty()){
            throw new TheaterException(TheaterExceptionCode.PARAMETER_EMPTY);
        }

        for (OccupyStockReqModel occupyStockReqModel : occupyStockReqs){
            Boolean occupy = occupyStock(occupyStockReqModel);
            if (!occupy){
                throw new TheaterException();
            }
        }

        return true;
    }

    public Boolean occupyStock(OccupyStockReqModel occupyStockReqModel) {
        checkOccupyParam(occupyStockReqModel);

        // 占库存
        accountingForStock(occupyStockReqModel);

        // 是否需要占座。
        if (!needOccupySeat(occupyStockReqModel)){
             return true;
        }

        checkTheaterParam(occupyStockReqModel);

        Integer occupyType = occupyStockReqModel.getOccupyType();

        // 获取占座任务的基本描述信息
        AssignedOrder assignedOrder = assignedOrder(occupyType, occupyStockReqModel);

        // 占座和释放座位
        occupyingAndReleaseSeats(assignedOrder, occupyType, occupyStockReqModel.getOccupySeatIds(), occupyStockReqModel.getReleaseSeatIds());

        // 保存占座任务的基本描述信息
        saveAssignedOrder(assignedOrder);

        return true;
    }

    private void checkOccupyParam(OccupyStockReqModel occupyStockReqModel){
        if (occupyStockReqModel == null){
            throw new TheaterException(TheaterExceptionCode.PARAMETER_EMPTY);
        }
        if (occupyStockReqModel.getTransactionId() == null){
            throw new TheaterException(TheaterExceptionCode.SEAT_RECORD_NULL_TRANSACTION);
        }
        if (occupyStockReqModel.getTransactionId().trim().equals("")){
            throw new TheaterException(TheaterExceptionCode.SEAT_RECORD_NULL_TRANSACTION);
        }
        if (occupyStockReqModel.getOperator() == null || occupyStockReqModel.getOperator() == 0){
            throw new TheaterException(TheaterExceptionCode.SEAT_RECORD_NULL_OPERATOR);
        }
        if (occupyStockReqModel.getOutQuantity() == null){
            throw new TheaterException(TheaterExceptionCode.OCCUPYED_STOCK_NULL_OUT_QUANTITY);
        }
        if (occupyStockReqModel.getStockRuleId() == null || occupyStockReqModel.getStockRuleId() == 0){
            throw new TheaterException(TheaterExceptionCode.OCCUPYED_STOCK_NULL_RULE_ID);
        }

        Date travelDate = occupyStockReqModel.getTravelDate();
        if (travelDate != null){
            travelDate = formatTravelDate(travelDate);
            occupyStockReqModel.setTravelDate(travelDate);

            Date currentDate = formatTravelDate(new Date());
            if (travelDate.getTime() < currentDate.getTime()){
                throw new TheaterException(TheaterExceptionCode.SEAT_RECORD_RULE_TRAVEL_DATE_EXPIRED);
            }
        }

        if (occupyStockReqModel.getOccupySeatIds() == null){
            occupyStockReqModel.setOccupySeatIds(Collections.EMPTY_LIST);
        }
        if (occupyStockReqModel.getReleaseSeatIds() == null){
            occupyStockReqModel.setReleaseSeatIds(Collections.EMPTY_LIST);
        }

    }

    private boolean needOccupySeat(OccupyStockReqModel occupyStockReqModel){
        Long areaId = occupyStockReqModel.getAreaId();
        if (areaId == null || areaId == 0){
            return false;
        }
        AreaCommonInfo areaCommonInfo = areaWriteEngine.queryAreaCommonInfoByAreaId(areaId);
        if (areaCommonInfo == null){
            TheaterExceptionCode code = TheaterExceptionCode.AREA_NOT_EXIST;
            String msg = code.getTemplateMessage(areaId);
            throw new TheaterException(code.getCode(), msg);
        }
        boolean equals = AreaSeatType.RectangularSeat.equals(areaCommonInfo.getSeatType());
        return equals;
    }

    private Date formatTravelDate(Date travelDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(travelDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
            /*DateTime dateTime = new DateTime(travelDate).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
            travelDate = new Date(dateTime.getMillis());*/
        return calendar.getTime();
    }


    private void accountingForStock(OccupyStockReqModel occupyStockReqModel) {
        OccupyStockRequestModel occupyStockRequestModel = new OccupyStockRequestModel();
        occupyStockRequestModel.setTransactionId(occupyStockReqModel.getTransactionId());
        occupyStockRequestModel.setProductId(occupyStockReqModel.getProductId());
        occupyStockRequestModel.setUserId(occupyStockReqModel.getOperator());
        occupyStockRequestModel.setStockNum(occupyStockReqModel.getOutQuantity());
        occupyStockRequestModel.setStockRuleId(occupyStockReqModel.getStockRuleId());
        occupyStockRequestModel.setShowDate(occupyStockReqModel.getTravelDate());
        List<OccupyStockRequestModel> orderStockModelList = new ArrayList();
        orderStockModelList.add(occupyStockRequestModel);

        commonTradeStockEngine.occupyStock(orderStockModelList);
    }

    private void checkTheaterParam(OccupyStockReqModel occupyStockReqModel){
        if (occupyStockReqModel.getAreaId() == null || occupyStockReqModel.getAreaId() == 0){
            throw new TheaterException(TheaterExceptionCode.SEAT_RECORD_NULL_AREA);
        }
        if (occupyStockReqModel.getScreeningsId() == null || occupyStockReqModel.getScreeningsId() == 0){
            throw new TheaterException(TheaterExceptionCode.SEAT_RECORD_NULL_SCREENING);
        }

        Integer occupyType = occupyStockReqModel.getOccupyType();
        if (occupyType == null){
            throw new TheaterException(TheaterExceptionCode.SEAT_RECORD_NULL_CATEGORY);
        }
        SeatRecordCategory.checkSeatRecordCategoryValue(occupyType);
        if (occupyStockReqModel.getTravelDate() == null){
            throw new TheaterException(TheaterExceptionCode.SEAT_RECORD_NULL_TRAVEL_DATE);
        }
    }

    private AssignedOrder assignedOrder(Integer occupyType, OccupyStockReqModel occupyStockReqModel){
        AssignedOrder assignedOrder = null;
        if (occupyType == 2) {
            assignedOrder = assignedOrderWriteEngine.queryAssignedOrderByTransaction(occupyStockReqModel.getTransactionId());
        }

        if (assignedOrder == null) {
            assignedOrder = new AssignedOrder();
            assignedOrder.setTransactionId(occupyStockReqModel.getTransactionId());
            assignedOrder.setUserId(occupyStockReqModel.getOperator());
            assignedOrder.setScreeningsId(occupyStockReqModel.getScreeningsId());
            assignedOrder.setAreaId(occupyStockReqModel.getAreaId());
            assignedOrder.setTravelDate(occupyStockReqModel.getTravelDate());
            assignedOrder.setUserId(occupyStockReqModel.getOperator());
            assignedOrder.setCreateTime(new Date());
            assignedOrder.setOccupiedNum(0);
            assignedOrder.setUnOccupiedNum(occupyStockReqModel.getOutQuantity());
            assignedOrder.setState(1);
            assignedOrder.setSpuId(occupyStockReqModel.getSpuId());
        } else if (assignedOrder.getState() != 1){
            TheaterExceptionCode code = TheaterExceptionCode.ASSIGNED_RULE_NO_REDISTRIBUTION;
            String message = code.getTemplateMessage(assignedOrder.getAssignedId(), assignedOrder.getState());
            throw new TheaterException(code.getCode(), message);
        }
        return assignedOrder;
    }


    /**
     * 对座位进行校验，释放座位，占座位。
     * @param assignedOrder
     * @param occupyType
     * @param occupySeatIds
     * @param releaseSeatIds
     */
    private void occupyingAndReleaseSeats(AssignedOrder assignedOrder, Integer occupyType, List<Long> occupySeatIds, List<Long> releaseSeatIds) {
        if ((occupySeatIds == null || occupySeatIds.isEmpty()) && (releaseSeatIds == null || releaseSeatIds.isEmpty())){
            return;
        }

        List<SeatChar> occupySeats = seatCharWriteEngine.querySeatCharByIds(occupySeatIds);
        List<SeatChar> releaseSeats = seatCharWriteEngine.querySeatCharByIds(releaseSeatIds);

        {
            List<Long> seatIds = mergeList(occupySeatIds, releaseSeatIds);

            checkSeats(seatIds, occupySeats, releaseSeats);

            // 查询场次、座位、游玩时间的有效的占座记录
            List<SeatRecord> existSeatRecords = seatRecordWriteMapper.selectExistValidSeatRecord(assignedOrder.getScreeningsId(), assignedOrder.getTravelDate(), seatIds);

            if (existSeatRecords != null && !existSeatRecords.isEmpty()){
                // 检查位是否座能被占
                checkWhetherCanSeat(existSeatRecords, assignedOrder.getUserId(), assignedOrder.getTransactionId());

                filter(existSeatRecords, occupySeats, occupyType);

                // 取消座位记录
                cancelTheSeatRecord(existSeatRecords);
            }
        }

        // 占座位
        occupyingSeats(assignedOrder, occupySeats, occupyType);

        int latestOccupiedNum = seatRecordWriteMapper.countExistValidSeatRecordByTransactionId(assignedOrder.getTransactionId());

        if (latestOccupiedNum == assignedOrder.getUnOccupiedNum()){
            assignedOrder.setState(0);
        } else if (latestOccupiedNum > assignedOrder.getUnOccupiedNum()){
            throw new TheaterException(TheaterExceptionCode.SEAT_RECORD_RULE_SEATS_GREATER_STOCK);
        }

        assignedOrder.setOccupiedNum(latestOccupiedNum);
    }

    private void filter(List<SeatRecord> existSeatRecords, List<SeatChar> occupySeats, Integer occupyType) {
        if (existSeatRecords == null || existSeatRecords.isEmpty() || occupySeats == null || occupySeats.isEmpty()){
            return;
        }
        Map<Long, SeatRecord> existSeatRecordsMap = new HashMap<>(existSeatRecords.size());
        for (SeatRecord seatRecord : existSeatRecords){
            existSeatRecordsMap.put(seatRecord.getSeatId(), seatRecord);
        }
        Iterator<SeatChar> iterator = occupySeats.iterator();
        while (iterator.hasNext()){
            SeatChar next = iterator.next();
            if (existSeatRecordsMap.containsKey(next.getId())){
                SeatRecord seatRecord = existSeatRecordsMap.get(next.getId());
                if (seatRecord.getCategory().equals(occupyType)){
                    existSeatRecords.remove(seatRecord);
                    iterator.remove();
                }
            }
        }
    }

    private <T> List<T> mergeList( List<T> list1, List<T> list2){
        int occupySeatIdsSize = listSize(list1);
        int releaseSeatIdsSize = listSize(list2);
        if (occupySeatIdsSize == 0 && releaseSeatIdsSize == 0){
            return null;
        }

        List<T> mergeList = new ArrayList<>(occupySeatIdsSize + occupySeatIdsSize);
        if (occupySeatIdsSize > 0) {
            mergeList.addAll(list1);
        }
        if (releaseSeatIdsSize > 0) {
            mergeList.addAll(list2);
        }
        return mergeList;
    }

    private int listSize(List list){
        if (list != null && list.size() > 0){
            return list.size();
        }
        return 0;
    }

    private void checkSeats(List<Long> seatIds, List<SeatChar> occupySeats, List<SeatChar> releaseSeats) {
        ArrayList<Long> tmp = new ArrayList<>(seatIds);
        removeSeatIdForList(occupySeats, tmp);
        removeSeatIdForList(releaseSeats, tmp);
        if (tmp.size() > 0) {
            TheaterExceptionCode code = TheaterExceptionCode.SEAT_ILLEGAL_ID;
            String msg = code.getTemplateMessage(seatIds);
            throw new TheaterException(code.getCode(), msg);
        }
    }

    private void removeSeatIdForList(List<SeatChar> occupySeats, ArrayList<Long> tmp) {
        if (occupySeats != null && !occupySeats.isEmpty()){
            for (SeatChar seat : occupySeats){
                tmp.remove(seat.getId());
            }
        }
    }

    private void checkWhetherCanSeat(List<SeatRecord> existSeatRecords, Long operator, String transactionId){
        if (existSeatRecords == null || existSeatRecords.isEmpty()) {
            return;
        }

        for (SeatRecord seatRecord : existSeatRecords){
            Integer category = seatRecord.getCategory();
            if (SeatRecordCategory.LockSeat.equals(category)){
                if(!operator.equals(seatRecord.getOperatorId())){
                    // 不是当前操作人锁定的座位
                    TheaterExceptionCode code = TheaterExceptionCode.SEAT_RECORD_RULE_NOT_THE_OWNER;
                    String msg = code.getTemplateMessage(seatRecord.getRecordId(), seatRecord.getOperatorId(), operator);
                    throw new TheaterException(code.getCode(), msg);
                }
            } else if (SeatRecordCategory.PreemptionSeat.equals(category) || SeatRecordCategory.OccupyingSeat.equals(category)){
                if (!transactionId.equals(seatRecord.getTransactionId())){
                    // 不是当前交易ID预占的座位
                    TheaterExceptionCode code = TheaterExceptionCode.SEAT_RECORD_RULE_NOT_THE_TRANSACTION;
                    String msg = code.getTemplateMessage(seatRecord.getRecordId(), seatRecord.getTransactionId(), transactionId);
                    throw new TheaterException(code.getCode(), msg);
                }
            } else {
                throw new TheaterException(TheaterExceptionCode.SEAT_RECORD_ILLEGAL_CATEGORY);
            }
        }
    }

    private void cancelTheSeatRecord(List<SeatRecord> existSeatRecords) {
        if (existSeatRecords == null || existSeatRecords.isEmpty()){
            return;
        }
        Date currentDate = new Date();
        List<SeatRecord> updateSeatRecords = new ArrayList<>(existSeatRecords.size());
        for (SeatRecord seatRecord : existSeatRecords){
            SeatRecord updateSeatRecord = new SeatRecord();
            updateSeatRecord.setRecordId(seatRecord.getRecordId());
            updateSeatRecord.setState(0);
            updateSeatRecord.setUpdateTime(currentDate);

            Long recordUnique = recordUnique(seatRecord.getScreeningId(), seatRecord.getSeatId(), seatRecord.getTravelDate(), seatRecord.getRecordId().toString());
            updateSeatRecord.setRecordUnique(recordUnique);

            updateSeatRecords.add(updateSeatRecord);
        }
        seatRecordWriteMapper.updateSeatRecords(updateSeatRecords);
    }

    private void occupyingSeats(AssignedOrder assignedOrder, List<SeatChar> occupySeats, Integer occupyType) {
        if (occupySeats == null || occupySeats.isEmpty()){
            return;
        }

        List<SeatRecord> insertSeatRecords = new ArrayList<>(occupySeats.size());

        Date currentDate = new Date();
        for (SeatChar seat : occupySeats){
            Long screeningsId = assignedOrder.getScreeningsId();
            Long seatId = seat.getId();
            Date travelDate = assignedOrder.getTravelDate();

            long newId = idGenerater.nextId();
            SeatRecord seatRecord = new SeatRecord();
            seatRecord.setRecordId(newId);
            seatRecord.setTravelDate(travelDate);
            seatRecord.setTransactionId(assignedOrder.getTransactionId());
            seatRecord.setScreeningId(screeningsId);
            seatRecord.setOperatorId(assignedOrder.getUserId());
            seatRecord.setAreaId(assignedOrder.getAreaId());
            seatRecord.setCategory(occupyType);
            seatRecord.setSeatId(seatId);
            seatRecord.setState(1);
            seatRecord.setCreateTime(currentDate);
            seatRecord.setSeatName(seat.getSeatName());

            Long hashCode = recordUnique(screeningsId, seatId, travelDate, null);
            seatRecord.setRecordUnique(hashCode);

            insertSeatRecords.add(seatRecord);
        }

        try {
            seatRecordWriteMapper.insertBatchSeatRecord(insertSeatRecords);
        } catch (Throwable throwable){
            logger.error(throwable.getMessage(), throwable);
            if (throwable instanceof DuplicateKeyException || throwable instanceof MySQLIntegrityConstraintViolationException) {
                logger.error("尝试占座时失败，已经被其他人抢占。参数为 {}", JSONConverter.toJson(insertSeatRecords));
                TheaterException theaterException = new TheaterException(TheaterExceptionCode.SEAT_RECORD_OCCUPYING_FILURE);
                throw theaterException;
            }
            throw new TheaterException(throwable);
        }
    }


    private void saveAssignedOrder(AssignedOrder assignedOrder) {
        if (assignedOrder.getAssignedId() == null && assignedOrder.getState() == 1) {
            assignedOrderWriteEngine.createAssignedOrder(assignedOrder);
        } else if (assignedOrder.getAssignedId() != null) {
            assignedOrderWriteEngine.modifyAssignedOrder(assignedOrder);
        }
    }

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public Long recordUnique(Long screeningsId, Long seatId, Date travelDate, String random){
        StringBuilder sb = new StringBuilder();
        sb.append(screeningsId).append(":");
        sb.append(seatId).append(":");
        sb.append(sdf.format(travelDate)).append(":");
        if (random != null){
            sb.append(":");
            sb.append(random);
        }
        Long recordUnique = CRCUtils.convertUniqueLong(sb.toString());
        return recordUnique;
    }
}