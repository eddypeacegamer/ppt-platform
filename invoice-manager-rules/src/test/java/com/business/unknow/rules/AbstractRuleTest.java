package com.business.unknow.rules;

import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;


/**
 * @author eej000f
 *
 */
public abstract class AbstractRuleTest {
	protected RulesEngine rulesEngine = new DefaultRulesEngine();
	protected Rules rules = new Rules();
}

