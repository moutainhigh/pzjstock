package com.pzj.core.stock.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.stock.entity.LockRecord;

public interface LockRecordReadMapper {

    /**
     * 根据主键查询
     * 
     * @param lockRecordId 锁记录ID
     * @return LockRecord
     */
    public LockRecord selectLockRecordById(Long lockRecordId);

    /**
     * 根据参数精确查询
     * 
     * @param lockRecord 锁记录实体
     * @return List<LockRecord> 
     */
    public List<LockRecord> selectLockRecordListByParam(LockRecord lockRecord);

    /**
     * 判断是否存在库存被锁定的记录.
     * @param transactionId
     * @param productId
     * @return
     */
    int existLocked(@Param(value = "transactionId") String transactionId, @Param(value = "productId") long productId);

    /**
     * 查询已锁定的库存记录.
     * @param transactionId
     * @param productId
     * @return
     */
    LockRecord queryLockedRecord(@Param(value = "transactionId") String transactionId, @Param(value = "productId") long productId);

    /**
     * 获取用户的锁库存记录
     * @param stockId
     * @param operatorId
     * @return LockRecord
     */
    List<LockRecord> queryLockRecordByUser(@Param(value = "stockIds") List<Long> stockIds, @Param(value = "operatorId") Long operatorId,
                                           @Param(value = "bizType") Integer bizType);

    /**
     * 通过交易ID获取锁库存记录数量
     * @param transactionId
     * @return Long
     */
    public List<LockRecord> queryStockRecordByTranId(String transactionId);
}