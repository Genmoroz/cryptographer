package com.frost.temp.logging;

import java.util.logging.Level;

public class Logger {

    private static final java.util.logging.Logger LOGGER =
            java.util.logging.Logger.getLogger(Logger.class.getSimpleName());

    public static void log(Level level, String message, Class cl) {
        LOGGER.log(level, cl.getName() + ": msg: " + message);
    }

    public static void logAndPrint(Level level, String message, Class cl) {
        LOGGER.log(level, cl.getName() + ": msg: " + message);
        System.out.println(cl.getName() + ": msg: " + message);
    }

    public static void logAndPrint(Level level, Exception ex, String message, Class cl) {
        LOGGER.log(level, cl.getName() + ": msg: " + message, ex);
        ex.printStackTrace(System.err);
    }

}
