package com.business.unknow.rules.suites;

import org.jeasy.rules.api.Rules;


public class PagoPueSuite implements InvoiceManagerSuite {

		private Rules rules = new Rules();

		public PagoPueSuite() {
		}

		@Override
		public Rules getSuite() {
			return rules;
		}

}
