/**
 * 
 */
package com.business.unknow.services.services;

import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.business.unknow.model.DevolucionDto;
import com.business.unknow.model.PagoDto;
import com.business.unknow.model.SolicitudDevolucionDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.Devolucion;
import com.business.unknow.services.entities.Pago;
import com.business.unknow.services.mapper.PagoMapper;
import com.business.unknow.services.repositories.facturas.DevolucionRepository;
import com.business.unknow.services.repositories.facturas.PagoRepository;

/**
 * @author ralfdemoledor
 *
 */
@Service
public class PagoService {

	@Autowired
	private PagoRepository repository;

	@Autowired
	private DevolucionRepository devRepository;

	@Autowired
	private PagoMapper mapper;

	private static final Logger log = LoggerFactory.getLogger(PagoService.class);

	public Page<PagoDto> getPaginatedPayments(Optional<String> folio, String formaPago, String status, String banco,
			Date since, Date to, int page, int size) {
		Date start = (since == null) ? new DateTime().minusYears(1).toDate() : since;
		Date end = (to == null) ? new Date() : to;
		Page<Pago> result;
		if (folio.isPresent()) {
			log.info("Searching PaymentsByFolio {}", folio.get());
			result = repository.findByFolioIgnoreCaseContaining(folio.get(), PageRequest.of(0, 10));
		} else {
			log.info("Search payments by status {}, formapago {}, banco {} and start {} y end {}", status, formaPago,
					banco, start, end);
			result = repository.findPagosByFilterParams(String.format("%%%s%%", status),
					String.format("%%%s%%", formaPago), String.format("%%%s%%", banco), start, end,
					PageRequest.of(page, size));
		}

		return new PageImpl<>(mapper.getPagosDtoFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

	public Page<PagoDto> getIngresosPaginados(String formaPago, String status, String banco, Date since, Date to,
			int page, int size) {
		Date start = (since == null) ? new DateTime().minusYears(1).toDate() : since;
		Date end = (to == null) ? new Date() : to;
		log.info("Search ingresos by status {}, formapago {}, banco {} and start {} y end {}", status, formaPago, banco,
				start, end);
		Page<Pago> result = repository.findIngresosByFilterParams(String.format("%%%s%%", status),
				String.format("%%%s%%", formaPago), String.format("%%%s%%", banco), start, end,
				PageRequest.of(page, size));

		return new PageImpl<>(mapper.getPagosDtoFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}
	
	public Page<PagoDto> getEgresosPaginados(String formaPago, String status, String banco, Date since, Date to,
			int page, int size) {
		Date start = (since == null) ? new DateTime().minusYears(1).toDate() : since;
		Date end = (to == null) ? new Date() : to;
		log.info("Search egresos by status {}, formapago {}, banco {} and start {} y end {}", status, formaPago, banco,
				start, end);
		Page<Pago> result = repository.findEgresosByFilterParams(String.format("%%%s%%", status),
				String.format("%%%s%%", formaPago), String.format("%%%s%%", banco), start, end,
				PageRequest.of(page, size));

		return new PageImpl<>(mapper.getPagosDtoFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public PagoDto solicitudDevolucion(SolicitudDevolucionDto solicitud) throws InvoiceManagerException {

		Double montoDevolucion = solicitud.getDevoluciones().stream().map(d -> d.getMonto()).reduce(0.0,
				(s, d) -> s + d);
		Pago payment = new Pago();
		payment.setBanco(solicitud.getBanco());
		payment.setComentarioPago("Solicitud devolucion.");
		payment.setCreateUser(solicitud.getUser());
		payment.setCuenta(solicitud.getCuenta());
		payment.setFormaPago(solicitud.getFormaPago());
		payment.setMoneda(solicitud.getMoneda());
		payment.setStatusPago("DEVOLUCION");
		payment.setTipoPago("EGRESO");
		payment.setTipoDeCambio(solicitud.getTipoCambio());
		payment.setUltimoUsuario(solicitud.getUser());
		payment.setMonto(montoDevolucion);

		PagoDto pago = mapper.getPagoDtoFromEntity(repository.save(payment));

		for (DevolucionDto devolucion : solicitud.getDevoluciones()) {
			Devolucion dev = devRepository.findById(devolucion.getId())
					.orElseThrow(() -> new InvoiceManagerException("Devolucion invalida",
							String.format("La devolucion con id %d no esiste en el sistema", devolucion.getId()),
							HttpStatus.CONFLICT.value()));
			dev.setIdPagoDestino(pago.getId());
			dev.setStatusDevolucion("SOLICITUD");
			devRepository.save(dev);
		}
		return pago;
	}

}
