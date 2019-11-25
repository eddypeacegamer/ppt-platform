package com.business.unknow.commons.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.business.unknow.Constants;

public class DateHelper {

	public String getStringFromFecha(Date date,String stringFormat) {
		SimpleDateFormat format = new SimpleDateFormat(stringFormat);
		return format.format(date);
	}
	
	public Date dateMinusDays(Date date,int days) {
		return new Date(date.getTime()-(Constants.MILISECONDS_PER_DAY*days));
	}
	
	public boolean isMyDateAfterDaysInPast(Date date,int days) {
		Date newDate=dateMinusDays(new Date(), days);
		return date.after(newDate);
	}
}
