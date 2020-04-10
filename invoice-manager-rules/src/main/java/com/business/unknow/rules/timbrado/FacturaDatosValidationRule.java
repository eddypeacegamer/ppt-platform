package com.business.unknow.rules.timbrado;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.enums.MetodosPagoEnum;
import com.business.unknow.enums.TipoDocumentoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.rules.common.Constants.Timbrado;

@Rule(name = Timbrado.TIMBRADO_DATOS_VALIDATION, description = Timbrado.TIMBRADO_DATOS_VALIDATION_RULE)
public class FacturaDatosValidationRule {

	@Condition
	public boolean condition(@Fact("facturaContext") FacturaContext fc) {

		if (fc.getFacturaDto().getCfdi().getComplemento() != null && fc.getFacturaDto().getFechaTimbrado() != null
				|| fc.getFacturaDto().getUuid() != null) {
			return true;
		} else {
			if (fc.getFacturaDto().getTipoDocumento().equals(TipoDocumentoEnum.FACTURA.getDescripcion())) {
				return fc.getFacturaDto().getFolioPadre() != null;
			}
			if (fc.getFacturaDto().getTipoDocumento().equals(TipoDocumentoEnum.COMPLEMENTO.getDescripcion())) {
				return fc.getFacturaDto().getFolioPadre() == null
						&& MetodosPagoEnum.PPD.name().equals(fc.getFacturaDto().getCfdi().getMetodoPago());
			}
			return true;// invalid document type
		}
	}

	@Action
	public void execute(@Fact("facturaContext") FacturaContext fc) {
		fc.setRuleErrorDesc(Timbrado.TIMBRADO_DATOS_VALIDATION_RULE_DESC);
		fc.setSuiteError(String.format("Error durante : %s", Timbrado.TIMBRADO_SUITE));
		fc.setValid(false);
	}
}
