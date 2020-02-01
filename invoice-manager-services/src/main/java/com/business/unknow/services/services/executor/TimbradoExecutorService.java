package com.business.unknow.services.services.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.files.FacturaFileDto;
import com.business.unknow.services.entities.cfdi.Cfdi;
import com.business.unknow.services.entities.cfdi.TimbradoFiscalDigitial;
import com.business.unknow.services.mapper.FilesMapper;
import com.business.unknow.services.mapper.factura.CfdiMapper;
import com.business.unknow.services.mapper.factura.FacturaMapper;
import com.business.unknow.services.repositories.facturas.CfdiRepository;
import com.business.unknow.services.repositories.facturas.FacturaRepository;
import com.business.unknow.services.repositories.facturas.TimbradoFiscalDigitialRepository;
import com.business.unknow.services.repositories.files.FacturaFileRepository;

@Service
public class TimbradoExecutorService {

	@Autowired
	private FacturaRepository repository;

	@Autowired
	private CfdiRepository cfdiRepository;

	@Autowired
	private FacturaFileRepository facturaFileRepository;

	@Autowired
	private TimbradoFiscalDigitialRepository timbradoFiscalDigitialRepository;

	@Autowired
	private FacturaMapper mapper;

	@Autowired
	private CfdiMapper cfdiMapper;

	@Autowired
	private FilesMapper filesMapper;

	public void updateFacturaAndCfdiValues(FacturaContext context) {
		repository.save(mapper.getEntityFromFacturaDto(context.getFacturaDto()));
		Cfdi cfdi=cfdiRepository.save(cfdiMapper.getEntityFromCfdiDto(context.getFacturaDto().getCfdi()));
		TimbradoFiscalDigitial timbradoFiscalDigitial=cfdiMapper.getEntityFromComplementoDto(context.getFacturaDto().getCfdi().getComplemento().getTimbreFiscal());
		timbradoFiscalDigitial.setCfdi(cfdi);
		timbradoFiscalDigitialRepository.save(timbradoFiscalDigitial);
		for (FacturaFileDto facturaFileDto : context.getFacturaFilesDto())
			if (facturaFileDto != null) {
				facturaFileRepository.save(filesMapper.getFacturaFileFromDto(facturaFileDto));
			}
	}

	public void updateCanceladoValues(FacturaContext context) {
		repository.save(mapper.getEntityFromFacturaDto(context.getFacturaDto()));
	}
}
