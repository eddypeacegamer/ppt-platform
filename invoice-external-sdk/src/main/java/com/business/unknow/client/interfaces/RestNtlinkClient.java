package com.business.unknow.client.interfaces;

import com.business.unknow.client.ntlink.model.NtlinkCancelRequestModel;
import com.business.unknow.client.ntlink.model.NtlinkCancelResponseModel;
import com.business.unknow.client.ntlink.model.NtlinkClientException;
import com.business.unknow.client.ntlink.model.NtlinkRequestModel;
import com.business.unknow.client.ntlink.model.NtlinkResponseModel;

public interface RestNtlinkClient {

	NtlinkResponseModel stamp(NtlinkRequestModel request) throws NtlinkClientException;
	NtlinkCancelResponseModel cancelar(NtlinkCancelRequestModel request)
			throws NtlinkClientException ;

}
