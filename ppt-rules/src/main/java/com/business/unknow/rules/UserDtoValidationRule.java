package com.business.unknow.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.model.UserDto;
import com.business.unknow.rules.common.Constants;

/**
 * @author eej000f
 *
 */
@Rule(name = Constants.USER_DTO_VALIDATION, description = Constants.USER_DTO_VALIDATION_RULE_DESC)
public class UserDtoValidationRule {

	
		@Condition
		public boolean firstNameCondition(@Fact("userDto") UserDto rc) {
			return rc==null;
		}

		@Action
		public void execute(@Fact("userDto") UserDto rc) {
			System.out.println("error during validation");
		}

}
