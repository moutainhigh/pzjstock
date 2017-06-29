package com.pzj.core.dock.service;

import com.pzj.core.context.StockConfig;
import com.pzj.core.product.common.exception.TheaterException;
import com.pzj.core.product.seatRecord.SeatRecordCategory;
import com.pzj.core.product.seatRecord.events.OccupySeatEvent;
import com.pzj.core.product.seatRecord.events.ReleaseSeatEvent;
import com.pzj.core.product.seatRecord.events.SeatEvent;
import com.pzj.core.util.LogUtil;
import com.pzj.core.work.Event;
import com.pzj.dock.supplier.service.ISeatDockingService;
import com.pzj.dock.supplier.vo.DockSeatVO;
import com.pzj.framework.context.Result;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-5-9.
 */
@Service
public class DockService {
    private static final Logger logger = LoggerFactory.getLogger(DockService.class);

    @Resource
    private ISeatDockingService seatDockingService;

    @Resource
    private StockConfig stockConfig;

    public void handleSeatEvent(List<Event> events){
        if (stockConfig.getEnableDock()){
            Map<String, List<OccupySeatEvent>> occupySeatEventMap = filterSeatEventMap(events, OccupySeatEvent.class);
            Map<String, List<ReleaseSeatEvent>> releaseSeatEventMap = filterSeatEventMap(events, ReleaseSeatEvent.class);

            callDockRefundDockSeat(releaseSeatEventMap);
            callDockHavedockSeat(occupySeatEventMap);
        }
       }

    private  <T extends SeatEvent> Map<String, List<T>> filterSeatEventMap(List<Event> events, Class<T> tClass){
        Map<String, List<T>> map = null;

        for (Event event : events){
            if (tClass.equals(event.getClass())){
                SeatEvent seatEvent = (SeatEvent)event;

                if (map == null){
                    map = new HashedMap();
                }

                List<T> eventsOfTransaction = map.get(seatEvent.getTransactionId());
                if (eventsOfTransaction == null){
                    eventsOfTransaction = new ArrayList<>();
                    map.put(seatEvent.getTransactionId(), eventsOfTransaction);
                }

                eventsOfTransaction.add((T)event);
            }
        }

        return map;
    }

    private void callDockHavedockSeat(Map<String, List<OccupySeatEvent>> occupySeatEventMap){
        if(occupySeatEventMap == null){
            return;
        }

        Iterator<Map.Entry<String, List<OccupySeatEvent>>> iterator = occupySeatEventMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, List<OccupySeatEvent>> next = iterator.next();

            List<OccupySeatEvent> events = next.getValue();

            DockSeatVO dockSeat = build(events);

            if (dockSeat == null){
                continue;
            }

            logger.info("开始调用Dock占库存，参数为 {}", dockSeat);

            Result result = seatDockingService.havedockSeat(dockSeat);

            if (!result.isOk()){
                logger.error("调用Dock占库存失败： {}", result.getErrorMsg());
                throw new TheaterException(result.getErrorCode(), result.getErrorMsg());
            } else {
                logger.info("调用Dock占库存成功");
            }
        }
    }

    private DockSeatVO build(List<OccupySeatEvent> events) {
        Map<Long, Integer> products = new HashedMap(events.size());

        for (OccupySeatEvent occupySeatEvent : events){
            Integer occupyType = occupySeatEvent.getOccupyType();

            if (occupyType != null && SeatRecordCategory.PreemptionSeat.equals(occupyType)){
                if(!stockConfig.getEnableDockPreemption()){
                    continue;
                }
            }

            Long productId = occupySeatEvent.getProductId();
            Integer outQuantity = occupySeatEvent.getOutQuantity();
            products.put(productId, outQuantity);
        }

        if (products.isEmpty()){
            return null;
        }

        OccupySeatEvent occupySeatEvent = events.get(0);

        DockSeatVO dockSeat = new DockSeatVO();
        dockSeat.setProductMap(products);
        dockSeat.setTransactionId(occupySeatEvent.getTransactionId());
        dockSeat.setScreenings(occupySeatEvent.getScreeningsId());
        dockSeat.setShowTime(occupySeatEvent.getTravelDate());
        if (occupySeatEvent.getAreaId() != null){
            dockSeat.setArea(occupySeatEvent.getAreaId().toString());
        }

        return dockSeat;
    }

    private void callDockRefundDockSeat(Map<String, List<ReleaseSeatEvent>> releaseSeatEventMap){
        if(releaseSeatEventMap == null){
            return;
        }

        Iterator<Map.Entry<String, List<ReleaseSeatEvent>>> iterator = releaseSeatEventMap.entrySet().iterator();
        A : while (iterator.hasNext()){
            Map.Entry<String, List<ReleaseSeatEvent>> next = iterator.next();

            List<ReleaseSeatEvent> releaseSeatEvents = next.getValue();

            if (releaseSeatEvents != null){
                B : for (ReleaseSeatEvent releaseSeatEvent : releaseSeatEvents){
                    Integer occupyType = releaseSeatEvent.getOccupyType();
                    if (occupyType != null && SeatRecordCategory.PreemptionSeat.equals(occupyType)){
                        if(!stockConfig.getEnableDockPreemption()){
                            continue A;
                        }
                    }
                }
            }

            String transactionId = next.getKey();

            logger.info("开始调用Dock释放库存，参数为 {}", transactionId);

            Result result = seatDockingService.refunddockSeat(transactionId);

            if (!result.isOk()){
                logger.error("调用Dock释放库存失败： {}", result.getErrorMsg());
                throw new TheaterException(result.getErrorCode(), result.getErrorMsg());
            } else {
                logger.error("调用Dock释放库存成功");
            }
        }
    }
}
