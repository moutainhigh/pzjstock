/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.common.utils;

/**
 * 
 * @author dongchunfu
 * @version $Id: StockRuleConstant.java, v 0.1 2016年7月27日 上午10:56:33 dongchunfu Exp $
 */
public class StockRuleConstant {

    /** 库存规则类型 */
    public static class StockRuleType {
        /** 单一库存 */
        public static final int SINGLE = 1;
        /** 日库存 */
        public static final int DAILY  = 2;
    }

    /** 库存规则状态常量定义*/
    public static class StockRuleState {
        /** 正常 */
        public static final int NORMAL    = 1;
        /** 停用 */
        public static final int FORBIDDEN = 2;
    }

    /** 是否归还库存 */
    public static class WhetherReturn {

        /** 需要归还 */
        public static final int RETURN   = 1;
        /** 不需要归还 */
        public static final int NORETURN = 2;
    }
}
