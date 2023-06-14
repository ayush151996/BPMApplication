
package com.newgen.bpm.logger;

import java.io.InputStream;
import org.apache.log4j.PropertyConfigurator;
import java.util.Properties;
import java.io.FileInputStream;
import org.apache.log4j.Logger;

public class BPMApplicationLogger
{
    public static final int LOG_LEVEL_INFO = 1;
    public static final int LOG_LEVEL_ERROR = 2;
    private static Logger log4j;
    private static Logger log4jerror;
    private boolean enableConsoleLogging;
    private static BPMApplicationLogger instance;
    
    static {
        BPMApplicationLogger.instance = new BPMApplicationLogger();
    }
    
    public void enableConsoleLogging() {
        this.enableConsoleLogging = true;
    }
    
    public void disableConsoleLogging() {
        this.enableConsoleLogging = false;
    }
    
    public static BPMApplicationLogger getInstance() {
        return BPMApplicationLogger.instance;
    }
    
    private BPMApplicationLogger() {
        this.enableConsoleLogging = false;
        this.intitalizeLog();
    }
    
    public void intitalizeLog() {
        try {
            final StringBuilder log4JPropertyFile = new StringBuilder(System.getProperty("user.dir")).append(System.getProperty("file.separator")).append("WebServiceConf").append(System.getProperty("file.separator")).append("BPMApplication").append(System.getProperty("file.separator")).append("BPMApplication_log4j.properties");
            System.out.println("Path of log4j file: " + (Object)log4JPropertyFile);
            final InputStream stream = new FileInputStream(log4JPropertyFile.toString());
            if (stream == null) {
                System.out.println("log4j.properties not found");
            }
            final Properties properties = new Properties();
            properties.load(stream);
            stream.close();
            PropertyConfigurator.configure(properties);
            if (BPMApplicationLogger.log4j == null) {
                (BPMApplicationLogger.log4j = Logger.getLogger("BPMApplication")).info((Object)"Logger is configured successfully");
            }
            if (BPMApplicationLogger.log4jerror == null) {
                (BPMApplicationLogger.log4jerror = Logger.getLogger("BPMApplicationError")).error((Object)"Error Logger is configured successfully");
            }
        }
        catch (Throwable t) {
            System.out.println(t);
        }
    }
    
    public static void logMe(final int level, final Throwable throwable) {
        try {
            BPMApplicationLogger.instance.log(level, throwable);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void logMe(final int level, final String message) {
        try {
            BPMApplicationLogger.instance.log(level, message);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void log(final int level, final String logText) throws Exception {
        final String formattedLogText = this.formatLogText(level, logText);
        if (this.enableConsoleLogging) {
            System.out.println(formattedLogText);
        }
        this.log4j(level, formattedLogText);
    }
    
    public void log(final int level, final Throwable throwable) {
        try {
            final String logText = this.buildExceptionText(throwable);
            final String formattedLogText = this.formatLogText(level, logText);
            if (this.enableConsoleLogging) {
                System.out.println(formattedLogText);
            }
            this.log4j(level, formattedLogText);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private String buildExceptionText(final Throwable t) throws Exception {
        if (t == null) {
            return "<Throwable is null>";
        }
        t.printStackTrace();
        String toReturn = "\n" + t.toString() + "(" + t.getMessage() + ")";
        final StackTraceElement[] trace = t.getStackTrace();
        StackTraceElement[] array;
        for (int length = (array = trace).length, i = 0; i < length; ++i) {
            final StackTraceElement element = array[i];
            toReturn = String.valueOf(toReturn) + "\n" + element.getClassName() + "." + element.getMethodName() + "[" + element.getLineNumber() + "]";
        }
        return toReturn;
    }
    
    private String formatLogText(final int level, final String logText) throws Exception {
        final Thread currentThread = Thread.currentThread();
        final StackTraceElement[] stackElements = currentThread.getStackTrace();
        StackTraceElement stackElement = stackElements[4];
        if (stackElement.getClassName().indexOf("CBGLogMe") >= 0 && stackElement.getMethodName().indexOf("logMe") >= 0) {
            stackElement = stackElements[5];
        }
        return "[" + currentThread.getId() + "]" + "[" + stackElement.getClassName() + "." + stackElement.getMethodName() + "()]" + "[#" + stackElement.getLineNumber() + "]" + " => " + logText;
    }
    
    private void log4j(final int level, final String logText) throws Exception {
        if (level == 1) {
            BPMApplicationLogger.log4j.info((Object)logText);
            return;
        }
        if (level == 2) {
            BPMApplicationLogger.log4jerror.error((Object)logText);
            return;
        }
        throw new Exception("Unrecognized Log Level");
    }
}
