package com.pzj.core.product.write;

import com.pzj.core.product.entity.AssignedOrder;

/**
 * Created by Administrator on 2017-3-26.
 */
public interface AssignedOrderWriteMapper {

    AssignedOrder selectAssignedOrderByTransaction(String transactionId);

    int insertAssignedOrder(AssignedOrder assignedOrder);

    int updateAssignedOrder(AssignedOrder assignedOrder);
}
