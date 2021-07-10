package com.dff.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.Status;
import com.dff.reports.ReportManager;

import io.qameta.allure.Attachment;

public class Log {
	private static final Logger Log =  LogManager.getLogger(Log.class.getName());
    //Info Level Logs
    public static void info (String message) {
        Log.info(message);
        ReportManager.getTest().log(Status.INFO, message);
    }
    //Warn Level Logs
    public static void warn (String message) {
        Log.warn(message);
        ReportManager.getTest().log(Status.WARNING, message);
    }
    //Error Level Logs
    public static void error (String message, Throwable exp) {
        Log.error(message, exp);
    }
    //Fatal Level Logs
    public static void fatal (String message) {
        Log.fatal(message);
    }
    //Debug Level Logs
    public static void debug (String message) {
        Log.debug(message);
    }
    
    public static void messageLog(String message) {
    	Log.info("============================================================================================================");
    	Log.info("\t\t\t"+message.toUpperCase());
    	Log.info("============================================================================================================");
    }
    	
	@Attachment(value = "Error Log", type = "text/plain")
	public static String attachLogToAllure(Throwable err) {
		return err.toString();
	}
}
