/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author dongchunfu
 * @version $Id: JobStart.java, v 0.1 2016年8月16日 下午4:50:51 dongchunfu Exp $
 */
public class JobStart {

    private static final Logger logger = LoggerFactory.getLogger(JobStart.class);

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/spring/stock-job-applicationContext-quartz.xml");
        logger.info("start stock job success.");

    }
}
