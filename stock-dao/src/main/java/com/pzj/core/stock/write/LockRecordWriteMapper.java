package com.pzj.core.stock.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.stock.entity.LockRecord;

public interface LockRecordWriteMapper {
	/**
	 *  根据主键ID删除
	 * 
	 * @param lockRecordId 锁记录实体
	 * @return 影响行数
	 */
	int deleteLockRecordById(Long lockRecordId);

	/**
	 * 新增锁记录实体
	 * 
	 * @param lockRecord 锁记录实体
	 * @return 影响行数
	 */
	int insertLockRecord(LockRecord lockRecord);

	/** 根据主键更新非空字段 */
	int updateLockRecordByIdSelective(LockRecord lockRecord);

	/** 根据主键更新全部字段 */
	int updateLockRecordById(LockRecord lockRecord);

	/**
	 * 根据id查询锁库存记录 加锁
	 * @param id
	 * @return
	 */
	LockRecord queryLockRecordByIdForUpdate(Long id);

	/**
	 * 根据id集合查询锁库存记录 加锁
	 * @param list
	 * @return
	 */
	List<LockRecord> queryLockRecordByIdsForUpdate(List<Long> list);

	/**
	 * 更新库存记录锁库存数量.
	 * @param stockId
	 * @param lockNum
	 * @return
	 */
	int updateLockRecordNum(@Param(value = "id") Long id, @Param(value = "lockNum") int lockNum);

	/**
	 * 用户批量插入锁库存记录
	 * @param list
	 * @return
	 */
	int userAddBatchLock(List<LockRecord> list);

	/**
	 * 用户批量修改锁库存记录
	 * @param list
	 * @return
	 */
	int userUpdateBatchLock(List<LockRecord> list);

	/**
	 * 批量添加锁库存记录
	 * @param list
	 * @return
	 */
	int insertBatch(List<LockRecord> list);

	/**
	 * 添加锁库存记录
	 * @param lockRecord
	 * @return
	 */
	int insert(LockRecord lockRecord);

	/**
	 * 批量删除锁库存记录信息
	 * @param list
	 * @return
	 */
	int delBatchLockByIds(List<Long> list);

	/**
	 * 批量添加锁库存记录
	 * @param list
	 * @return int
	 */
	public int batchAddStockLockRecord(List<LockRecord> list);

	/**
	 * 通过交易ID获取锁库存记录数量
	 * @param transactionId
	 * @return List<LockRecord>
	 */
	public List<LockRecord> queryStockRecordByTranId(String transactionId);

	/**
	 * 查询占库存记录并修改
	 * @param transactionId
	 * @param productId
	 * @return
	 */
	public LockRecord queryLockedRecordForUpdate(@Param(value = "transactionId") String transactionId,
			@Param(value = "productId") Long productId);

	/**
	 *  查询占库存记录
	 * @param transactionId
	 * @param productId
	 * @return
	 */
	public LockRecord queryLockRecordByTranPro(@Param(value = "transactionId") String transactionId,
			@Param(value = "productId") Long productId);
}