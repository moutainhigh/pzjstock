/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.seat;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.stock.model.ShowModel;
import com.pzj.core.stock.write.StockSeatRelWriteMapper;

/**
 * 
 * @author Administrator
 * @version $Id: RollbackOccupySeatEngine.java, v 0.1 2016年8月22日 下午3:38:43 Administrator Exp $
 */
@Component("rollbackOccupySeatEngine")
public class RollbackOccupySeatEngine {
    @Resource(name = "stockSeatRelWriteMapper")
    private StockSeatRelWriteMapper stockSeatRelWriteMapper;

    @Transactional(value = "stock.transactionManager", timeout = 2)
    public void rollbackOccupySeat(ShowModel showModel) {
        //回滚交易占座信息
        stockSeatRelWriteMapper.rollbackTransactionSeatInfo(showModel.getTransactionId());
    }
}
