/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.job;
import java.util.TimerTask;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.pzj.core.product.service.SeatRecordService;
import com.pzj.framework.context.ServiceContext;
/**
 * 
 * @author Administrator
 * @version $Id: SeatScanScheduledExecutor.java, v 0.1 2017年3月21日 上午11:05:33 Administrator Exp $
 */
@Component(value = "seatOverdueJob")
public class SeatScanScheduledExecutor{
	protected Logger logger = LoggerFactory.getLogger(SeatScanScheduledExecutor.class);
	@Resource
	private SeatRecordService seatRecordService;

	public void checkavailableSeatRecord(){
		try{
			seatRecordService.checkavailableSeatRecord(new ServiceContext());
		} catch (Throwable throwable) {
			logger.error(throwable.getMessage(), throwable);
		}
	}
}
