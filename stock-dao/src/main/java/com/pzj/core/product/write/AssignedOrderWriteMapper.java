package com.pzj.core.product.write;

import com.pzj.core.product.entity.AssignedOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017-3-26.
 */
public interface AssignedOrderWriteMapper {

    List<AssignedOrder> selectAssignedOrderByTransaction(@Param("transactionId") String transactionId);

    AssignedOrder selectAssignedOrderByTransactionScreeningsArea(@Param("transactionId") String transactionId,
                                                                 @Param("screeningsId") Long screeningsId,
                                                                 @Param("areaId") Long areaId);

    int insertAssignedOrder(AssignedOrder assignedOrder);

    int updateAssignedOrder(AssignedOrder assignedOrder);
}
