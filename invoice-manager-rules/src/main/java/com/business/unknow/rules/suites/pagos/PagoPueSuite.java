package com.business.unknow.rules.suites.pagos;

import org.jeasy.rules.api.Rules;

import com.business.unknow.rules.pago.create.pue.CreateCreditValidationRule;
import com.business.unknow.rules.pago.create.pue.PaymantPueWithCreditRule;
import com.business.unknow.rules.pago.create.pue.PaymentPueNotCreditRule;
import com.business.unknow.rules.suites.InvoiceManagerSuite;


public class PagoPueSuite implements InvoiceManagerSuite {

		private Rules rules = new Rules();

		public PagoPueSuite() {
			rules.register(new PaymantPueWithCreditRule());
			rules.register(new CreateCreditValidationRule());
			rules.register(new PaymentPueNotCreditRule());
		}

		@Override
		public Rules getSuite() {
			return rules;
		}

}
