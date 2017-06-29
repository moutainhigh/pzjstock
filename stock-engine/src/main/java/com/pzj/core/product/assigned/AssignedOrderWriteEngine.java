package com.pzj.core.product.assigned;

import com.pzj.core.product.common.exception.TheaterException;
import com.pzj.core.product.common.exception.TheaterExceptionCode;
import com.pzj.core.product.entity.AssignedOrder;
import com.pzj.core.product.model.OccupyStockReqModel;
import com.pzj.core.product.write.AssignedOrderWriteMapper;
import com.pzj.framework.idgen.IDGenerater;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017-3-27.
 */
@Component
@Transactional(value = "stock.transactionManager")
public class AssignedOrderWriteEngine {
    @Resource
    private AssignedOrderWriteMapper assignedOrderWriteMapper;

    @Resource
    private IDGenerater idGenerater;

    public void createAssignedOrder(AssignedOrder assignedOrder){
        long newId = idGenerater.nextId();
        assignedOrder.setAssignedId(newId);
        assignedOrderWriteMapper.insertAssignedOrder(assignedOrder);
    }

    public void modifyAssignedOrder(AssignedOrder assignedOrder){
        assignedOrderWriteMapper.updateAssignedOrder(assignedOrder);
    }


    public List<AssignedOrder> queryAssignedOrderByTransaction(String transactionId) {
        List<AssignedOrder> assignedOrders = assignedOrderWriteMapper.selectAssignedOrderByTransaction(transactionId);
        return assignedOrders;
    }

    public AssignedOrder queryAssignedOrderByTransactionScreeningsArea(String transactionId, Long screeningsId, Long areaId){
        AssignedOrder assignedOrder = assignedOrderWriteMapper.selectAssignedOrderByTransactionScreeningsArea(transactionId, screeningsId, areaId);
        return assignedOrder;
    }


    public AssignedOrder createAssignedOrderFrom(OccupyStockReqModel occupyStockReqModel){
        AssignedOrder assignedOrder = new AssignedOrder();
        assignedOrder.setTransactionId(occupyStockReqModel.getTransactionId());
        assignedOrder.setScreeningsId(occupyStockReqModel.getScreeningsId());
        assignedOrder.setAreaId(occupyStockReqModel.getAreaId());

        assignedOrder.setUserId(occupyStockReqModel.getOperator());
        assignedOrder.setTravelDate(occupyStockReqModel.getTravelDate());
        assignedOrder.setUserId(occupyStockReqModel.getOperator());
        assignedOrder.setUnOccupiedNum(occupyStockReqModel.getOutQuantity());
        assignedOrder.setSpuId(occupyStockReqModel.getSpuId());

        assignedOrder.setCreateTime(new Date());
        assignedOrder.setOccupiedNum(0);
        assignedOrder.setState(1);
        return assignedOrder;
    }

    public void saveAssignedOrder(AssignedOrder assignedOrder) {
        if (assignedOrder == null) {
            return;
        }
        if (assignedOrder.getAssignedId() == null && assignedOrder.getState() == 1) {
            createAssignedOrder(assignedOrder);
        } else if (assignedOrder.getAssignedId() != null) {
            modifyAssignedOrder(assignedOrder);
        }
    }
}
