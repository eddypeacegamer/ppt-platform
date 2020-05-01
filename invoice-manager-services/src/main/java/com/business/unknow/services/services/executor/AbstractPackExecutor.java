package com.business.unknow.services.services.executor;

import com.business.unknow.model.cfdi.Cfdi;

public class AbstractPackExecutor {

	protected String getCadenaOriginalTimbrado(Cfdi currentCfdi) {
		return new StringBuilder().append("||")
				.append(currentCfdi.getComplemento().getTimbreFiscalDigital().getVersion()).append("|")
				.append(currentCfdi.getComplemento().getTimbreFiscalDigital().getUuid()).append("|")
				.append(currentCfdi.getComplemento().getTimbreFiscalDigital().getFechaTimbrado()).append("|")
				.append(currentCfdi.getComplemento().getTimbreFiscalDigital().getRfcProvCertif()).append("|")
				.append(currentCfdi.getComplemento().getTimbreFiscalDigital().getSelloSAT()).append("|")
				.append(currentCfdi.getComplemento().getTimbreFiscalDigital().getNoCertificadoSAT()).append("||")
				.toString();
	}

}
