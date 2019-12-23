package com.business.unknow.services.services.evaluations;

import org.jeasy.rules.api.Facts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.business.unknow.Constants;
import com.business.unknow.enums.ContactoDevolucionEnum;
import com.business.unknow.enums.DevolucionStatusEnum;
import com.business.unknow.enums.TipoDocumentoEnum;
import com.business.unknow.model.PagoDto;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.model.factura.FacturaDto;
import com.business.unknow.rules.suites.DevolucionSuite;
import com.business.unknow.services.entities.Devolucion;
import com.business.unknow.services.entities.Pago;

@Service
public class DevolucionEvaluatorService extends AbstractDevolucionesEvaluatorService {

	@Autowired
	private DevolucionSuite devolucionSuite;

	public void generarDevolucionesPorPago(FacturaDto facturaDto, PagoDto pagoDto) throws InvoiceManagerException {
		switch (TipoDocumentoEnum.findByDesc(facturaDto.getTipoDocumento())) {
		case FACRTURA:
			generaDevolucionPue(facturaDto, pagoDto);
			break;
		case COMPLEMENTO:
			generaDevolucionComplemento(facturaDto, pagoDto);
			break;
		default:
			throw new InvoiceManagerException("The type of document not supported",
					String.format("The type of document %s not valid", facturaDto.getTipoDocumento()),
					HttpStatus.BAD_REQUEST.value());
		}

	}

	public void generaDevolucionComplemento(FacturaDto facturaDto, PagoDto pagoDto) throws InvoiceManagerException {
		FacturaContext context = buildFacturaContextForComplementoDevolution(facturaDto, pagoDto);
		Double porcentajeComisiones = (context.getFacturaPadreDto().getTotal()
				- context.getFacturaPadreDto().getSubtotal()) / context.getFacturaPadreDto().getTotal();
		Facts facts = new Facts();
		facts.put("facturaContext", context);
		rulesEngine.fire(devolucionSuite.getSuite(), facts);
		validateFacturaContext(context);
		devolucionRepository
				.save(buildDevolucion(context.getFacturaDto().getFolio(), context.getFacturaDto().getId(),
						numberHelper.assignPrecision(pagoDto.getMonto() * porcentajeComisiones,
								Constants.DEFAULT_SCALE),
						context.getClientDto().getPorcentajePromotor(), context.getClientDto().getCorreoPromotor(),
						ContactoDevolucionEnum.PROMOTOR.getDescripcion()));
		devolucionRepository
				.save(buildDevolucion(context.getFacturaDto().getFolio(), context.getCurrentPago().getId(),
						numberHelper.assignPrecision(pagoDto.getMonto() * porcentajeComisiones,
								Constants.DEFAULT_SCALE),
						context.getClientDto().getPorcentajeDespacho(), "invoice-manager@gmail.com",
						ContactoDevolucionEnum.DESPACHO.getDescripcion()));
		if (context.getClientDto().getPorcentajeCliente() > 0) {
			Devolucion devolucion = buildDevolucion(context.getFacturaDto().getFolio(),
					context.getCurrentPago().getId(),
					numberHelper.assignPrecision(pagoDto.getMonto() * porcentajeComisiones, Constants.DEFAULT_SCALE),
					context.getClientDto().getPorcentajeCliente(),
					context.getClientDto().getInformacionFiscal().getRfc(),
					ContactoDevolucionEnum.CLIENTE.getDescripcion());
			devolucion.setMonto(numberHelper.assignPrecision((context.getCurrentPago().getMonto()
					- (context.getCurrentPago().getMonto() * porcentajeComisiones) + devolucion.getMonto()),Constants.DEFAULT_SCALE));
			devolucionRepository.save(devolucion);
		}
		if (context.getClientDto().getPorcentajeContacto() > 0) {
			devolucionRepository
					.save(buildDevolucion(context.getFacturaDto().getFolio(), context.getCurrentPago().getId(),
							numberHelper.assignPrecision(pagoDto.getMonto() * porcentajeComisiones,
									Constants.DEFAULT_SCALE),
							context.getClientDto().getPorcentajeContacto(), context.getClientDto().getCorreoContacto(),
							ContactoDevolucionEnum.CONTACTO.getDescripcion()));
		}
		Pago payment = pagoRepository.findById(context.getCurrentPago().getId())
				.orElseThrow(() -> new InvoiceManagerException("No se pueden generar devoluciones a pagos inexistentes",
						String.format("El pago %s  no existe", context.getCurrentPago().toString()),
						HttpStatus.CONFLICT.value()));
		payment.setStatusPago("DEVOLUCION");
		pagoRepository.save(payment);
		context.getFacturaDto().setStatusDevolucion(DevolucionStatusEnum.DEVUELTA.getValor());
		repository.save(mapper.getEntityFromFacturaDto(context.getFacturaDto()));
		context.getFacturaPadreDto().setStatusDevolucion(DevolucionStatusEnum.PARCIALMENTE_DEVUELTA.getValor());
		repository.save(mapper.getEntityFromFacturaDto(context.getFacturaPadreDto()));
	}

	public void generaDevolucionPue(FacturaDto facturaDto, PagoDto pagoDto) throws InvoiceManagerException {
		Double baseComisiones = facturaDto.getTotal() - facturaDto.getSubtotal();
		FacturaContext context = buildFacturaContextForPueDevolution(facturaDto, pagoDto);
		Facts facts = new Facts();
		facts.put("facturaContext", context);
		rulesEngine.fire(devolucionSuite.getSuite(), facts);
		validateFacturaContext(context);
		devolucionRepository.save(buildDevolucion(context.getFacturaDto().getFolio(), context.getFacturaDto().getId(),
				baseComisiones, context.getClientDto().getPorcentajePromotor(),
				context.getClientDto().getCorreoPromotor(), ContactoDevolucionEnum.PROMOTOR.getDescripcion()));
		devolucionRepository.save(buildDevolucion(context.getFacturaDto().getFolio(), context.getCurrentPago().getId(),
				baseComisiones, context.getClientDto().getPorcentajeDespacho(), "invoice-manager@gmail.com",
				ContactoDevolucionEnum.DESPACHO.getDescripcion()));
		if (context.getClientDto().getPorcentajeCliente() > 0) {
			Devolucion devolucion = buildDevolucion(context.getFacturaDto().getFolio(),
					context.getCurrentPago().getId(), baseComisiones, context.getClientDto().getPorcentajeCliente(),
					context.getClientDto().getInformacionFiscal().getRfc(),
					ContactoDevolucionEnum.CLIENTE.getDescripcion());
			devolucion.setMonto(context.getFacturaDto().getSubtotal() + devolucion.getMonto());
			devolucionRepository.save(devolucion);
		}
		if (context.getClientDto().getPorcentajeContacto() > 0) {
			devolucionRepository.save(buildDevolucion(context.getFacturaDto().getFolio(),
					context.getCurrentPago().getId(), baseComisiones, context.getClientDto().getPorcentajeContacto(),
					context.getClientDto().getCorreoContacto(), ContactoDevolucionEnum.CONTACTO.getDescripcion()));
		}
		Pago payment = pagoRepository.findById(context.getCurrentPago().getId())
				.orElseThrow(() -> new InvoiceManagerException("No se pueden generar devoluciones a pagos inexistentes",
						String.format("El pago %s  no existe", context.getCurrentPago().toString()),
						HttpStatus.CONFLICT.value()));
		payment.setStatusPago("DEVOLUCION");
		pagoRepository.save(payment);
		context.getFacturaDto().setStatusDevolucion(DevolucionStatusEnum.DEVUELTA.getValor());
		repository.save(mapper.getEntityFromFacturaDto(context.getFacturaDto()));
	}

}
