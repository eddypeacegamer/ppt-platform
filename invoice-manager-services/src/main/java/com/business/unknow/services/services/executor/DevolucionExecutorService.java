package com.business.unknow.services.services.executor;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.business.unknow.enums.ContactoDevolucionEnum;
import com.business.unknow.enums.DevolucionStatusEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.Client;
import com.business.unknow.services.entities.Devolucion;
import com.business.unknow.services.mapper.factura.FacturaMapper;
import com.business.unknow.services.repositories.PagoRepository;
import com.business.unknow.services.repositories.facturas.DevolucionRepository;
import com.business.unknow.services.repositories.facturas.FacturaRepository;
import com.business.unknow.services.services.builder.DevolucionesBuilderService;

@Service
public class DevolucionExecutorService {

	@Autowired
	private DevolucionRepository devolucionRepository;

	@Autowired
	private PagoRepository pagoRepository;

	@Autowired
	private FacturaRepository facturaRepository;

	@Autowired
	private FacturaMapper facturaMapper;

	@Autowired
	private DevolucionesBuilderService devolucionesBuilderService;

	public void executeDevolucionForPue(FacturaContext context, Client client,BigDecimal total, BigDecimal baseComisiones)
			throws InvoiceManagerException {
		devolucionRepository.save(devolucionesBuilderService.buildDevolucion(total,context.getFacturaDto().getFolio(),
				context.getFacturaDto().getId(), baseComisiones, client.getPorcentajePromotor(),
				client.getCorreoPromotor(), ContactoDevolucionEnum.PROMOTOR.name()));
		devolucionRepository.save(devolucionesBuilderService.buildDevolucion(total,context.getFacturaDto().getFolio(),
				context.getCurrentPago().getId(), baseComisiones, client.getPorcentajeDespacho(),
				"invoice-manager@gmail.com", ContactoDevolucionEnum.DESPACHO.name()));
		if (client.getPorcentajeCliente().compareTo(BigDecimal.ZERO)>0) {
			Devolucion devolucion = devolucionesBuilderService.buildDevolucion(total,context.getFacturaDto().getFolio(),
					context.getCurrentPago().getId(), baseComisiones, client.getPorcentajeCliente(),
					client.getInformacionFiscal().getRfc(), ContactoDevolucionEnum.CLIENTE.name());
			devolucion.setMonto(context.getFacturaDto().getCfdi().getSubtotal().add(devolucion.getMonto()));
			devolucionRepository.save(devolucion);
		}else {
			Devolucion devolucion = devolucionesBuilderService.buildDevolucion(total,context.getFacturaDto().getFolio(),
					context.getCurrentPago().getId(), baseComisiones, client.getPorcentajeCliente(),
					client.getInformacionFiscal().getRfc(), ContactoDevolucionEnum.CLIENTE.name());
			devolucion.setMonto(context.getFacturaDto().getCfdi().getSubtotal());
			devolucionRepository.save(devolucion);
		}
		if (client.getPorcentajeContacto().compareTo(BigDecimal.ZERO)> 0) {
			devolucionRepository.save(devolucionesBuilderService.buildDevolucion(total,context.getFacturaDto().getFolio(),
					context.getCurrentPago().getId(), baseComisiones, client.getPorcentajeContacto(),
					client.getCorreoContacto(), ContactoDevolucionEnum.CONTACTO.name()));
		}
		pagoRepository.findById(context.getCurrentPago().getId())
				.orElseThrow(() -> new InvoiceManagerException("No se pueden generar devoluciones a pagos inexistentes",
						String.format("El pago %s  no existe", context.getCurrentPago().toString()),
						HttpStatus.CONFLICT.value()));
		context.getFacturaDto().setStatusDevolucion(DevolucionStatusEnum.DEVUELTA.getValor());
		facturaRepository.save(facturaMapper.getEntityFromFacturaDto(context.getFacturaDto()));
	}

	public void executeDevolucionForPpd(FacturaContext context, Client client,BigDecimal  total, BigDecimal baseComisiones)
			throws InvoiceManagerException {
		devolucionRepository.save(devolucionesBuilderService.buildDevolucion(total,context.getFacturaDto().getFolio(), context.getFacturaDto().getId(),
				context.getCurrentPago().getMonto().multiply(baseComisiones), client.getPorcentajePromotor(),
				client.getCorreoPromotor(), ContactoDevolucionEnum.PROMOTOR.name()));
		devolucionRepository.save(devolucionesBuilderService.buildDevolucion(total,context.getFacturaDto().getFolioPadre(),
				context.getCurrentPago().getId(), context.getCurrentPago().getMonto().multiply(baseComisiones),
				client.getPorcentajeDespacho(), "invoice-manager@gmail.com", ContactoDevolucionEnum.DESPACHO.name()));
		if (client.getPorcentajeCliente().compareTo(BigDecimal.ZERO)> 0) {
			Devolucion devolucion = devolucionesBuilderService.buildDevolucion(total,context.getFacturaDto().getFolio(),
					context.getCurrentPago().getId(), context.getCurrentPago().getMonto().multiply(baseComisiones),
					client.getPorcentajeCliente(), client.getInformacionFiscal().getRfc(),
					ContactoDevolucionEnum.CLIENTE.name());
			devolucion.setMonto(context.getCurrentPago().getMonto()
					.subtract(context.getCurrentPago().getMonto().multiply(baseComisiones))
					.add(devolucion.getMonto()));
			devolucionRepository.save(devolucion);
		}
		if (client.getPorcentajeContacto().compareTo(BigDecimal.ZERO)> 0) {
			devolucionRepository
					.save(devolucionesBuilderService.buildDevolucion(total,context.getFacturaDto().getFolio(), context.getCurrentPago().getId(),
							context.getCurrentPago().getMonto().multiply(baseComisiones), client.getPorcentajeContacto(),
							client.getCorreoContacto(), ContactoDevolucionEnum.CONTACTO.name()));
		}
		pagoRepository.findById(context.getCurrentPago().getId())
				.orElseThrow(() -> new InvoiceManagerException("No se pueden generar devoluciones a pagos inexistentes",
						String.format("El pago %s  no existe", context.getCurrentPago().toString()),
						HttpStatus.CONFLICT.value()));
		context.getFacturaDto().setStatusDevolucion(DevolucionStatusEnum.DEVUELTA.getValor());
		facturaRepository.save(facturaMapper.getEntityFromFacturaDto(context.getFacturaDto()));
		context.getFacturaPadreDto().setStatusDevolucion(DevolucionStatusEnum.PARCIALMENTE_DEVUELTA.getValor());
		facturaRepository.save(facturaMapper.getEntityFromFacturaDto(context.getFacturaPadreDto()));
	}

}
