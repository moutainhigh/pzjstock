package com.pzj.core.product.assigned;

import com.pzj.core.product.entity.AssignedOrder;
import com.pzj.core.product.write.AssignedOrderWriteMapper;
import com.pzj.framework.idgen.IDGenerater;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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

    public AssignedOrder queryAssignedOrderByTransaction(String transactionId){
        AssignedOrder assignedOrder = assignedOrderWriteMapper.selectAssignedOrderByTransaction(transactionId);
        return assignedOrder;
    }
}
