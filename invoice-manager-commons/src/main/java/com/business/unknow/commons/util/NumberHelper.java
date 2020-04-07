package com.business.unknow.commons.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberHelper {

	public Double assignPrecision(Double number, int scale) {
		BigDecimal tempBig = new BigDecimal(Double.toString(number));
		return tempBig.setScale(scale, BigDecimal.ROUND_HALF_EVEN).doubleValue();
	}
	
	public BigDecimal assignBigDecimalPrecision(BigDecimal number, int scale) {
		return  number.setScale(scale, RoundingMode.DOWN);
	}
	

}
