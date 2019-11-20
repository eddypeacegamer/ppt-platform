package com.business.unknow.rules;

import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author eej000f
 *
 */
public abstract class AbstractRuleTest {
	protected RulesEngine rulesEngine = new DefaultRulesEngine();
	protected Rules rules = new Rules();
	protected ObjectMapper objectMapper= new ObjectMapper();
}

