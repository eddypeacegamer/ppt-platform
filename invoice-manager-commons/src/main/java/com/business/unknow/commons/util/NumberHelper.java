package com.business.unknow.commons.util;

import java.math.BigDecimal;

public class NumberHelper {

	public Double assignPrecision(Double number, int scale) {
		BigDecimal tempBig = new BigDecimal(Double.toString(number));
		return tempBig.setScale(scale, BigDecimal.ROUND_HALF_EVEN).doubleValue();
	}

}
