package com.business.unknow.rules.suites.pagos;

import org.jeasy.rules.api.Rules;

import com.business.unknow.rules.suites.InvoiceManagerSuite;


public class PagoPueSuite implements InvoiceManagerSuite {

		private Rules rules = new Rules();

		public PagoPueSuite() {
		}

		@Override
		public Rules getSuite() {
			return rules;
		}

}
