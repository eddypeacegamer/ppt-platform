package com.business.unknow.client.interfaces;

import com.business.unknow.client.model.facturacionmoderna.FacturaModernaCancelResponseModel;
import com.business.unknow.client.model.facturacionmoderna.FacturaModernaClientException;
import com.business.unknow.client.model.facturacionmoderna.FacturaModernaRequestModel;
import com.business.unknow.client.model.facturacionmoderna.FacturaModernaResponseModel;

public interface RestFacturacionModernaClient {

	public FacturaModernaResponseModel stamp(FacturaModernaRequestModel requestModel) throws FacturaModernaClientException;
	public FacturaModernaCancelResponseModel cancelar(FacturaModernaRequestModel requestModel) throws FacturaModernaClientException;
}
