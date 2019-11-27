package com.business.unknow.client.interfaces;

import com.business.unknow.client.model.facturacionmoderna.FacturaModernaClientException;
import com.business.unknow.client.model.facturacionmoderna.FacturaModernaRequestModel;

public interface RestFacturacionModernaClient {

	public void stamp(FacturaModernaRequestModel requestModel) throws FacturaModernaClientException;
}
