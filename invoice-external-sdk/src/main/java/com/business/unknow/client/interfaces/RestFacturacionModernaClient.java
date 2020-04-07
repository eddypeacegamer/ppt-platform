package com.business.unknow.client.interfaces;

import com.business.unknow.client.facturacionmoderna.model.FacturaModernaCancelResponseModel;
import com.business.unknow.client.facturacionmoderna.model.FacturaModernaRequestModel;
import com.business.unknow.client.facturacionmoderna.model.FacturaModernaResponseModel;
import com.business.unknow.client.facturacionmoderna.util.FacturaModernaClientException;

public interface RestFacturacionModernaClient {

	public FacturaModernaResponseModel stamp(FacturaModernaRequestModel requestModel) throws FacturaModernaClientException;
	public FacturaModernaCancelResponseModel cancelar(FacturaModernaRequestModel requestModel) throws FacturaModernaClientException;
}
