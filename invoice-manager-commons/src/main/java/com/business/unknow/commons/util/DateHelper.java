package com.business.unknow.commons.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.business.unknow.model.error.InvoiceCommonException;

public class DateHelper {

	public String getStringFromFecha(Date date, String stringFormat) {
		SimpleDateFormat format = new SimpleDateFormat(stringFormat);
		return format.format(date);
	}

	public Date getDateFromString(String date, String stringFormat) throws InvoiceCommonException {
		try {
			SimpleDateFormat format = new SimpleDateFormat(stringFormat);
			return format.parse(date);
		} catch (ParseException e) {
			throw new InvoiceCommonException(e.getMessage());
		}
	}
}
