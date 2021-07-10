package com.dff.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getStringDate(String dateFormat) {
		SimpleDateFormat dtf = new SimpleDateFormat(dateFormat);  
	    Date localDate = new Date();  
		return dtf.format(localDate).toString();
	}
}
