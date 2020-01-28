package com.business.unknow.services.services.executor;

import org.springframework.beans.factory.annotation.Autowired;

import com.business.unknow.services.mapper.PagoMapper;
import com.business.unknow.services.repositories.PagoRepository;
import com.business.unknow.services.services.AbstractService;

public class AbstractExecutorService extends AbstractService{


	@Autowired
	protected PagoRepository pagoRepository;

	@Autowired
	protected PagoMapper pagoMapper;

}
