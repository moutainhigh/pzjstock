/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.provider;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author Administrator
 * @version $Id: StockProvider.java, v 0.1 2016年7月22日 下午7:43:56 Administrator Exp $
 */
public class StockProvider {
    private static ApplicationContext context;

    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/applicationContext.xml");
        System.out.println("启动成功：" + context);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //        System.setProperty("dubbo.spring.config", "-Ddubbo.spring.config=classpath*:META-INF/spring/applicationContext.xml");
        //        com.alibaba.dubbo.container.Main.main(args);
    }
}
