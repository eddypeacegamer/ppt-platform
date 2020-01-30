package com.business.unknow.services.services.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.enums.FormaPagoEnum;
import com.business.unknow.enums.ResourceFileEnum;
import com.business.unknow.enums.TipoRecursoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.services.PagoDto;
import com.business.unknow.services.entities.cfdi.Cfdi;
import com.business.unknow.services.mapper.PagoMapper;
import com.business.unknow.services.mapper.factura.CfdiMapper;
import com.business.unknow.services.repositories.PagoRepository;
import com.business.unknow.services.repositories.facturas.CfdiRepository;
import com.business.unknow.services.services.AbstractService;

@Service
public class PagoExecutorService extends AbstractService {

	@Autowired
	protected PagoRepository pagoRepository;

	@Autowired
	protected CfdiRepository cfdiRepository;

	@Autowired
	protected PagoMapper pagoMapper;

	@Autowired
	protected CfdiMapper cfdiMapper;

	public void deletePagoPpdExecutor(FacturaContext context) {
		repository.delete(mapper.getEntityFromFacturaDto(context.getFacturaDto()));
		context.getPagoCredito().setMonto(context.getPagoCredito().getMonto().add(context.getCurrentPago().getMonto()));
		pagoRepository.save(pagoMapper.getEntityFromPagoDto(context.getPagoCredito()));
		pagoRepository.delete(pagoMapper.getEntityFromPagoDto(context.getCurrentPago()));
		deleteAllResourceFileByFolio(
				context.getFacturaDto().getFolio().concat("_").concat(context.getCurrentPago().getFormaPago()));
	}

	public void deletePagoPueExecutor(FacturaContext context) {
		pagoRepository.delete(pagoMapper.getEntityFromPagoDto(context.getCurrentPago()));
		if (!context.getCurrentPago().getFormaPago().equals(FormaPagoEnum.CREDITO.getPagoValue())
				&& context.getPagoCredito() != null) {
			context.getPagoCredito()
					.setMonto(context.getPagoCredito().getMonto().add(context.getCurrentPago().getMonto()));
			context.setPagoCredito(pagoMapper.getPagoDtoFromEntity(
					pagoRepository.save(pagoMapper.getEntityFromPagoDto(context.getPagoCredito()))));
		}
		deleteAllResourceFileByFolio(
				context.getFacturaDto().getFolio().concat("_").concat(context.getCurrentPago().getFormaPago()));
	}

	public PagoDto PagoCreation(FacturaContext context) {
		if (context.getCurrentPago().getDocumento() != null) {
			createResourceFile(context.getCurrentPago().getDocumento(),
					context.getFacturaDto().getFolio().concat("_").concat(context.getCurrentPago().getFormaPago()),
					TipoRecursoEnum.PAGO.name(), ResourceFileEnum.IMAGEN.name());
		}
		return mapper.getPagoDtoFromEntity(pagoRepository.save(mapper.getEntityFromPagoDto(context.getCurrentPago())));
	}

	public FacturaContext creaPagoPpdExecutor(FacturaContext context) {
		Cfdi cfdi= cfdiMapper.getEntityFromCfdiDto(context.getFacturaDto().getCfdi());
		
		context.setFacturaDto(mapper
				.getFacturaDtoFromEntity(repository.save(mapper.getEntityFromFacturaDto(context.getFacturaDto()))));
		context.getFacturaDto().setCfdi(cfdiMapper.getCfdiDtoFromEntity(
				cfdiRepository.save(cfdiMapper.getEntityFromCfdiDto(context.getFacturaDto().getCfdi()))));
		pagoRepository.save(pagoMapper.getEntityFromPagoDto(context.getPagoCredito()));
		return context;
	}

	public FacturaContext creaPagoPueExecutor(FacturaContext context) {
		if (context.getPagoCredito() != null) {
			pagoRepository.save(pagoMapper.getEntityFromPagoDto(context.getPagoCredito()));
		}
		return context;
	}

}
