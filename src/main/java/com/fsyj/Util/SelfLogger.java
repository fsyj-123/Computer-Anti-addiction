package com.fsyj.Util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SelfLogger {
    private static Logger logger;
    static {
        logger = LogManager.getLogger(SelfLogger.class);
    }
    public static Logger getLogger() {
        return logger;
    }
}
