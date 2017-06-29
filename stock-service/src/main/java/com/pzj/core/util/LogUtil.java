package com.pzj.core.util;

import com.pzj.framework.converter.JSONConverter;
import org.slf4j.Logger;

/**
 * Created by Administrator on 2017-3-21.
 */
public class LogUtil {

    public static void loggerPrintInfo(Logger logger, String format, Object param){
        if (logger.isInfoEnabled()){
            logger.info(format, JSONConverter.toJson(param));
        }
    }

    public static void loggerPrintInfo(Logger logger, String format, Object param1, Object param2, Object param3){
        if (logger.isInfoEnabled()){
            logger.info(format, JSONConverter.toJson(param1), JSONConverter.toJson(param2), JSONConverter.toJson(param3));
        }
    }
}
