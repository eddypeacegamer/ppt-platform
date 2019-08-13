package com.business.unknow.rules;

import org.jeasy.rules.api.Facts;
import org.junit.Before;
import org.junit.Test;

import com.business.unknow.model.UserDto;

/**
 * @author eej000f
 *
 */
public class UserDtoValidationRuleTest extends AbstractRuleTest {
	

		@Before
		public void init() {
			rules.register(new UserDtoValidationRule());
		}
		
		@Test
		public void succesCase() {
			UserDto user = new UserDto();
			Facts facts = new Facts();
			facts.put("userDto", user);
			rulesEngine.fire(rules, facts);
		}
		
		@Test
		public void failCase() {
			UserDto user = null;
			Facts facts = new Facts();
			facts.put("userDto", user);
			rulesEngine.fire(rules, facts);
		}

}
