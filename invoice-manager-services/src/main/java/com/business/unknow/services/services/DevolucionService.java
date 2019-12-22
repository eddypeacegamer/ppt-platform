/**
 * 
 */
package com.business.unknow.services.services;

import java.sql.SQLException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.business.unknow.model.ClientDto;
import com.business.unknow.model.DevolucionDto;
import com.business.unknow.model.PagoDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.model.factura.FacturaDto;
import com.business.unknow.services.entities.Devolucion;
import com.business.unknow.services.entities.Pago;
import com.business.unknow.services.mapper.DevolucionMapper;
import com.business.unknow.services.repositories.facturas.DevolucionRepository;
import com.business.unknow.services.repositories.facturas.PagoRepository;

/**
 * @author ralfdemoledor
 *
 */
@Service
public class DevolucionService {

	@Autowired
	private DevolucionRepository repository;
	
	@Autowired
	private PagoRepository paymentsRepo;

	@Autowired
	private DevolucionMapper mapper;

	@Autowired
	private FacturaService facturaService;

	@Autowired
	private ClientService clientService;
	
	
	private static final Logger log = LoggerFactory.getLogger(DevolucionService.class);


	public Page<DevolucionDto> getDevolucionesByParams(Optional<String> receptorType, Optional<String> idReceptor,
			Optional<String> statusPay, int page, int size) {
		Page<Devolucion> result = null;
		if (!receptorType.isPresent() && !idReceptor.isPresent() && !statusPay.isPresent()) {
			result = repository.findAll(PageRequest.of(page, size));
		} else {
			result = repository.findDevolucionesByParams(receptorType.get(), idReceptor.get(),
					String.format("%%%s%%", statusPay.get()), PageRequest.of(page, size));
		}
		return new PageImpl<>(mapper.getDevolucionesDtoFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

	public DevolucionDto insertDevolution(DevolucionDto devolucion) {
		devolucion.setStatusPago("VALIDACION");
		return mapper.getDevolucionDtoFromEntity(repository.save(mapper.getEntityFromDevolucionDto(devolucion)));
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public void generarDevolucionesPorPago(PagoDto pago) throws InvoiceManagerException {

		FacturaDto factura = facturaService.getfacturaByFolio(pago.getFolioPadre());
		ClientDto client = clientService.getClientByRFC(factura.getRfcRemitente());
		if (client.getActivo()) {
			Double baseComisiones = factura
					.getCfdi().getConceptos().stream().map(c -> c.getImpuestos().stream().map(i -> i.getImporte())
							.reduce(0.0, (sub, importe) -> sub + importe))
					.reduce(0.0, (subtotal, impuesto) -> subtotal + impuesto);

			repository.save(buildDevolucion(factura.getFolio(), pago.getId(), baseComisiones,
					client.getPorcentajePromotor(), client.getCorreoPromotor(), "PROMOTOR"));
			repository.save(buildDevolucion(factura.getFolio(), pago.getId(), baseComisiones,
					client.getPorcentajeDespacho(), "invoice-manager@gmail.com", "DESPACHO"));
			if (client.getPorcentajeCliente() > 0) {
				Devolucion devolucion = buildDevolucion(factura.getFolio(), pago.getId(), baseComisiones,
						client.getPorcentajeCliente(), client.getInformacionFiscal().getRfc(), "CLIENTE");
				devolucion.setMonto(factura.getSubtotal() + devolucion.getMonto());
				repository.save(devolucion);
			}
			if (client.getPorcentajeContacto() > 0) {
				repository.save(buildDevolucion(factura.getFolio(), pago.getId(), baseComisiones,
						client.getPorcentajeContacto(), client.getCorreoContacto(), "CONTACTO"));
			}
			
			Pago payment = paymentsRepo.findById(pago.getId()).orElseThrow(()->new InvoiceManagerException("No se pueden generar devoluciones a pagos inexistentes",
					String.format("El pago %s  no existe", pago.toString()),
					HttpStatus.CONFLICT.value()));
			
			payment.setStatusPago("DEVOLUCION");
			
			paymentsRepo.save(payment);
			
		} else {
			throw new InvoiceManagerException("No se pueden generar devoluciones a clientes inactivos",
					String.format("%s esta inactiva", client.getInformacionFiscal().getRfc()),
					HttpStatus.CONFLICT.value());
		}
	}

	private Devolucion buildDevolucion(String foliofFact, Integer idPago, Double montoBase, Integer porcentaje,
			String receptor, String tipoReceptor) {

		Double monto = montoBase * porcentaje / 16;
		Devolucion devolucion = new Devolucion();
		devolucion.setFolio(foliofFact);
		devolucion.setIdPago(idPago);
		devolucion.setMonto(monto);
		devolucion.setReceptor(receptor);
		devolucion.setStatusPago("PENDIENTE");
		devolucion.setTipoReceptor(tipoReceptor);
		
		log.info("Generando devolucion para {} por un monto de {}", receptor, monto);
		
		return devolucion;
	}

}
