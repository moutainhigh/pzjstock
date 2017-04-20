/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.job;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pzj.core.product.service.SeatRecordService;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * @author Administrator
 * @version $Id: SeatScanScheduledExecutor.java, v 0.1 2017年3月21日 上午11:05:33 Administrator Exp $
 */
public class SeatScanScheduledExecutor {
	protected Logger logger = LoggerFactory.getLogger(SeatScanScheduledExecutor.class);
	@Resource
	private SeatRecordService seatRecordService;
	//定义任务调度池
	private final ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
	//定义扫描任务延迟
	private final long initialDelay = 60 * 1000;
	//定义扫描周期是一个小时
	private long period = 1 * 60 * 60 * 1000;
	//控制开关是否启动扫描任务调度
	private boolean start = false;
	//测试开关，开启后将在20s后执行结算任务，并以30s为周期
	private boolean TEST_MODE = false;

	public SeatScanScheduledExecutor() {
		//读取扫描全局配置
		initConfig();
		//执行订单扫描任务
		execTask();
	}

	private void initConfig() {
		try {
			Properties p = new Properties();
			InputStream is = SeatScanScheduledExecutor.class.getClassLoader().getResourceAsStream(
					"seat-overdue.properties");
			p.load(is);
			period = Integer.valueOf(p.getProperty("scan.task.period", "1")) * 60 * 60 * 1000;
			start = p.getProperty("scan.task.start", "false").equals("true") ? true : false;
			TEST_MODE = p.getProperty("scan.task.test", "false").equals("true") ? true : false;
			if (TEST_MODE) {
				period = 120 * 1000;
			}
			is.close();
			p.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void execTask() {
		logger.info("SeatScanScheduledExecutor is running......");
		if (start) {
			Runnable command = new seatScanScheduledExecutor();
			pool.scheduleAtFixedRate(command, initialDelay, period, TimeUnit.MILLISECONDS);
		}
	}

	class seatScanScheduledExecutor implements Runnable {

		@Override
		public void run() {
			//过期锁定座位
			checkavailableSeatRecord();
		}

		private void checkavailableSeatRecord() {
			logger.info("开始执行过期取消锁定座位方法，执行时间是{}", new SimpleDateFormat("yyyyMMdd").format(new Date()));
			try {
				Result<Boolean> checkResult = seatRecordService.checkavailableSeatRecord(new ServiceContext());
				logger.info("自动取消锁定座位执行完成！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
