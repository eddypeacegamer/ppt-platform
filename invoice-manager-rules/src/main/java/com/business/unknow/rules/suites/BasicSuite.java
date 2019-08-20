package com.business.unknow.rules.suites;

import org.jeasy.rules.api.Rules;

import com.business.unknow.rules.UserDtoValidationRule;

/**
 * @author eej000f
 *
 */
public class BasicSuite implements PptSuite  {

	private Rules rules = new Rules();

	public BasicSuite() {
		rules.register(new UserDtoValidationRule());
		
	}
	@Override
	public Rules getSuite() {
		return rules;
	}

	
	
}
