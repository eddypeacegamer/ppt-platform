package com.business.unknow.rules.suites.payments;

import org.jeasy.rules.api.Rules;

import com.business.unknow.rules.payments.ConflicPaymentRuleValidation;
import com.business.unknow.rules.payments.DoubleValidatesPaymentRule;
import com.business.unknow.rules.payments.PaymentOrderValidationRule;
import com.business.unknow.rules.suites.InvoiceManagerSuite;


public class PaymentUpdateSuite implements InvoiceManagerSuite {

		private Rules rules = new Rules();

		public PaymentUpdateSuite() {
			rules.register(new ConflicPaymentRuleValidation());
			rules.register(new DoubleValidatesPaymentRule());
			rules.register(new PaymentOrderValidationRule());
		}

		@Override
		public Rules getSuite() {
			return rules;
		}

}
