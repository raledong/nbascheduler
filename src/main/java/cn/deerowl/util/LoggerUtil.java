package cn.deerowl.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {

    public static Logger getLogger(Class tClass) {
        return LoggerFactory.getLogger(tClass);
    }

    public static void info(Logger logger, String line, Object... parameters) {
        if (logger.isInfoEnabled() ) {
            logger.info(line, parameters);
        }
    }

    public static void info(Logger logger, Exception e, String line, Object... parameters) {
        if (logger.isInfoEnabled() ) {
            logger.info(line, parameters);
            logger.info(e.toString());
        }
    }

    public static void debug(Logger logger, String line, Object... parameters) {
        if (logger.isDebugEnabled()) {
            logger.debug(line, parameters);
        }
    }

    public static void debug(Logger logger, Exception e, String line, Object... parameters) {
        if (logger.isDebugEnabled()) {
            logger.debug(line, parameters);
            logger.debug(e.toString());
        }
    }

    public static void error(Logger logger, String line, Object... parameters) {
        if (logger.isErrorEnabled()) {
            logger.error(line, parameters);
        }
    }

    public static void error(Logger logger, Exception e, String line, Object... parameters) {
        if (logger.isErrorEnabled()) {
            logger.error(line, parameters);
            logger.error(e.toString());
        }
    }

    public static void warn(Logger logger, String line, Object... parameters) {
        if (logger.isWarnEnabled()) {
            logger.warn(line, parameters);
        }
    }

    public static void warn(Logger logger, Exception e, String line, Object... parameters) {
        if (logger.isWarnEnabled()) {
            logger.warn(line, parameters);
            logger.warn(e.toString());
        }
    }
}
