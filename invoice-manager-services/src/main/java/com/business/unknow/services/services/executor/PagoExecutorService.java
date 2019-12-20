package com.business.unknow.services.services.executor;

import org.springframework.stereotype.Service;

import com.business.unknow.enums.FormaPagoEnum;
import com.business.unknow.enums.ResourceFileEnum;
import com.business.unknow.enums.TipoRecursoEnum;
import com.business.unknow.model.PagoDto;
import com.business.unknow.model.context.FacturaContext;

@Service
public class PagoExecutorService extends AbstractExecutorService {

	public void deletePagoPpdExecutor(FacturaContext context) {
		repository.delete(mapper.getEntityFromFacturaDto(context.getFacturaDto()));
		context.getPagoCredito().setMonto(context.getPagoCredito().getMonto() + context.getCurrentPago().getMonto());
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
					.setMonto(context.getPagoCredito().getMonto() + context.getCurrentPago().getMonto());
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
					TipoRecursoEnum.PAGO.getDescripcion(), ResourceFileEnum.IMAGEN.getDescripcion());
		}
		return mapper.getPagoDtoFromEntity(pagoRepository.save(mapper.getEntityFromPagoDto(context.getCurrentPago())));
	}

	public FacturaContext creaPapoPpdExecutor(FacturaContext context) {
		context.setFacturaDto(mapper
				.getFacturaDtoFromEntity(repository.save(mapper.getEntityFromFacturaDto(context.getFacturaDto()))));
		pagoRepository.save(pagoMapper.getEntityFromPagoDto(context.getPagoCredito()));
		return context;
	}

	public FacturaContext creaPapoPueExecutor(FacturaContext context) {
		if (context.getPagoCredito() != null) {
			pagoRepository.save(pagoMapper.getEntityFromPagoDto(context.getPagoCredito()));
		}
		return context;
	}

}
