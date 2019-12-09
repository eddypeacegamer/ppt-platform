package com.business.unknow.services.services.evaluations;

import org.apache.http.HttpStatus;
import org.jeasy.rules.api.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;

import com.business.unknow.commons.util.FacturaCalculator;
import com.business.unknow.commons.util.FileHelper;
import com.business.unknow.enums.ResourceFileEnum;
import com.business.unknow.enums.TipoRecursoEnum;
import com.business.unknow.model.EmpresaDto;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.model.factura.FacturaDto;
import com.business.unknow.model.files.FacturaFileDto;
import com.business.unknow.services.entities.files.ResourceFile;
import com.business.unknow.services.mapper.CfdiMapper;
import com.business.unknow.services.mapper.ConceptoMapper;
import com.business.unknow.services.mapper.EmpresaMapper;
import com.business.unknow.services.mapper.FacturaMapper;
import com.business.unknow.services.mapper.FilesMapper;
import com.business.unknow.services.mapper.ImpuestoMapper;
import com.business.unknow.services.mapper.PagoMapper;
import com.business.unknow.services.repositories.EmpresaRepository;
import com.business.unknow.services.repositories.facturas.CfdiRepository;
import com.business.unknow.services.repositories.facturas.ConceptoRepository;
import com.business.unknow.services.repositories.facturas.ImpuestoRepository;
import com.business.unknow.services.repositories.facturas.PagoRepository;
import com.business.unknow.services.services.AbstractService;
import com.business.unknow.services.util.FacturaDefaultValues;;

public class AbstractEvaluatorService extends AbstractService {

	@Autowired
	protected CfdiRepository cfdiRepository;

	@Autowired
	protected ConceptoRepository conceptoRepository;

	@Autowired
	protected ImpuestoRepository impuestoRepository;

	@Autowired
	protected PagoRepository pagoRepository;

	@Autowired
	protected EmpresaRepository empresaRepository;

	@Autowired
	protected FacturaMapper mapper;

	@Autowired
	protected FilesMapper filesMapper;

	@Autowired
	protected CfdiMapper cfdiMapper;

	@Autowired
	protected ConceptoMapper conceptoMapper;

	@Autowired
	protected ImpuestoMapper impuestoMapper;

	@Autowired
	protected PagoMapper pagoMapper;

	@Autowired
	protected EmpresaMapper empresaMapper;

	@Autowired
	protected RulesEngine rulesEngine;

	protected FacturaCalculator facturaCalculator = new FacturaCalculator();

	protected FacturaDefaultValues facturaDefaultValues = new FacturaDefaultValues();

	protected FileHelper fileHelper = new FileHelper();

	protected void validateFacturaContext(FacturaContext facturaContexrt) throws InvoiceManagerException {
		if (!facturaContexrt.isValid()) {
			throw new InvoiceManagerException(facturaContexrt.getRuleErrorDesc(), facturaContexrt.getSuiteError(),
					HttpStatus.SC_BAD_REQUEST);
		}
	}

	protected void updateFacturaValues(FacturaContext context) {
		repository.save(mapper.getEntityFromFacturaDto(context.getFacturaDto()));
	}

	protected void updateFacturaAndCfdiValues(FacturaContext context) {
		repository.save(mapper.getEntityFromFacturaDto(context.getFacturaDto()));
		cfdiRepository.save(cfdiMapper.getEntityFromCfdiDto(context.getFacturaDto().getCfdi()));
		for (FacturaFileDto facturaFileDto : context.getFacturaFilesDto())
			if (facturaFileDto != null) {
				facturaFileRepository.save(filesMapper.getFacturaFileFromDto(facturaFileDto));
			}
	}

	protected void updateCanceladoValues(FacturaContext context) {
		repository.save(mapper.getEntityFromFacturaDto(context.getFacturaDto()));
	}

	protected void getEmpresaFiles(EmpresaDto empresaDto, FacturaDto facturaDto) throws InvoiceManagerException {
		ResourceFile certFile = resourceFileRepository
				.findByTipoRecursoAndReferenciaAndTipoArchivo(TipoRecursoEnum.EMPRESA.getDescripcion(),
						facturaDto.getRfcEmisor(), ResourceFileEnum.CERT.getDescripcion())
				.orElseThrow(() -> new InvoiceManagerException("Empresa certificate not found",
						String.format("La empresa con el rfc no tiene certificado", facturaDto.getRfcEmisor()),
						HttpStatus.SC_NOT_FOUND));
		ResourceFile keyFile = resourceFileRepository
				.findByTipoRecursoAndReferenciaAndTipoArchivo(TipoRecursoEnum.EMPRESA.getDescripcion(),
						facturaDto.getRfcEmisor(), ResourceFileEnum.KEY.getDescripcion())
				.orElseThrow(() -> new InvoiceManagerException("Empresa certificate not found",
						String.format("La empresa con el rfc no tiene certificado", facturaDto.getRfcEmisor()),
						HttpStatus.SC_NOT_FOUND));
		empresaDto.setCertificado(fileHelper.getStringFileSource((certFile.getData())));
		empresaDto.setLlavePrivada(fileHelper.getStringFileSource((keyFile.getData())));
	}

}
