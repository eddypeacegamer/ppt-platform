/**
 * 
 */
package com.business.unknow.services.services;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.business.unknow.enums.TipoDocumentoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.services.DevolucionDto;
import com.business.unknow.model.dto.services.PagoDto;
import com.business.unknow.model.dto.services.SolicitudDevolucionDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.Client;
import com.business.unknow.services.entities.Devolucion;
import com.business.unknow.services.mapper.DevolucionMapper;
import com.business.unknow.services.repositories.ClientRepository;
import com.business.unknow.services.repositories.facturas.DevolucionRepository;
import com.business.unknow.services.services.builder.DevolucionesBuilderService;
import com.business.unknow.services.services.evaluations.DevolucionEvaluatorService;
import com.business.unknow.services.services.executor.DevolucionExecutorService;

/**
 * @author ralfdemoledor
 *
 */
@Service
public class DevolucionService {

	@Autowired
	private DevolucionRepository repository;
	
	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private PagoService pagosService;
	
	@Autowired
	private DevolucionMapper mapper;
	
	@Autowired
	private DevolucionesBuilderService devolucionesBuilderService;
	
	@Autowired
	private DevolucionEvaluatorService devolucionEvaluatorService;
	
	@Autowired
	private DevolucionExecutorService devolucionExecutorService;

	public Page<DevolucionDto> getDevolucionesByParams(Optional<String> receptorType, Optional<String> idReceptor,
			Optional<String> statusPay, int page, int size) {
		Page<Devolucion> result = null;
		if (!receptorType.isPresent() && !idReceptor.isPresent()
				&& (!statusPay.isPresent() || statusPay.get().isEmpty())) {
			result = repository.findAll(PageRequest.of(page, size));
		} else if (receptorType.isPresent() && idReceptor.isPresent()
				&& (!statusPay.isPresent() || statusPay.get().isEmpty())) {
			result = repository.findDevolucionesByParams(receptorType.get(), idReceptor.get(),
					PageRequest.of(page, size));
		} else {
			result = repository.findDevolucionesByParamsWithStatus(receptorType.get(), idReceptor.get(),
					String.format("%%%s%%", statusPay.get()), PageRequest.of(page, size));
		}
		return new PageImpl<>(mapper.getDevolucionesDtoFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

	public List<DevolucionDto> getDevolucionesPorReceptor(String tipoReceptor, String idReceptor,
			String statusDevolucion) {
		return mapper.getDevolucionesDtoFromEntities(repository.findDevolucionesByParams(tipoReceptor, idReceptor,
				String.format("%%%s%%", statusDevolucion)));
	}

	public List<DevolucionDto> getDevolucionesByPagoDestino(Integer idPagoDestino) {
		return mapper.getDevolucionesDtoFromEntities(repository.findByIdPagoDestino(idPagoDestino));
	}

	public DevolucionDto insertDevolution(DevolucionDto devolucion) {
		devolucion.setStatusDevolucion("PENDIENTE");
		return mapper.getDevolucionDtoFromEntity(repository.save(mapper.getEntityFromDevolucionDto(devolucion)));
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public PagoDto solicitudDevolucion(SolicitudDevolucionDto solicitud) throws InvoiceManagerException {

		BigDecimal montoDevolucion = solicitud.getDevoluciones().stream().map(d -> d.getMonto()).reduce(new BigDecimal(0.0),
				(s, d) -> s.add(d));
		PagoDto payment = new PagoDto();
		payment.setBanco(solicitud.getBanco());
		payment.setComentarioPago(solicitud.getBeneficiario());
		payment.setCreateUser(solicitud.getUser());
		payment.setCuenta(solicitud.getCuenta());
		payment.setFormaPago(solicitud.getFormaPago());
		payment.setMoneda(solicitud.getMoneda());
		payment.setStatusPago("DEVOLUCION");
		payment.setTipoPago("EGRESO");
		payment.setTipoDeCambio(new BigDecimal(solicitud.getTipoCambio()));
		payment.setUltimoUsuario(solicitud.getUser());
		payment.setMonto(montoDevolucion);
		PagoDto pago = pagosService.insertNewPayment(payment);

		for (DevolucionDto devolucion : solicitud.getDevoluciones()) {
			Devolucion dev = repository.findById(devolucion.getId())
					.orElseThrow(() -> new InvoiceManagerException("Devolucion invalida",
							String.format("La devolucion con id %d no esiste en el sistema", devolucion.getId()),
							HttpStatus.CONFLICT.value()));
			dev.setIdPagoDestino(pago.getId());
			dev.setStatusDevolucion("SOLICITUD");
			repository.save(dev);
		}
		return pago;
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public PagoDto pagoSolicitudDevolucion(PagoDto payment) throws InvoiceManagerException {
		payment.setStatusPago("PAGADO");
		PagoDto upadtePayment = pagosService.upadtePayment(payment.getId(), payment);
		for (Devolucion devolucion : repository.findByIdPagoDestino(payment.getId())) {
			devolucion.setStatusDevolucion("PAGADA");
			repository.save(devolucion);
			// TODO EVALUATE IF UPDATE FACTURA STATUS
		}
		return upadtePayment;
	}

	public void generarDevolucionesPorPago(FacturaDto facturaDto, PagoDto pagoDto) throws InvoiceManagerException {
		Client client = clientRepository.findByRfc(facturaDto.getRfcRemitente())
				.orElseThrow(() -> new InvoiceManagerException("The type of document not supported",
						String.format("The type of document %s not valid", facturaDto.getTipoDocumento()),
						HttpStatus.BAD_REQUEST.value()));
		FacturaContext context;
		BigDecimal baseComisiones;
		switch (TipoDocumentoEnum.findByDesc(facturaDto.getTipoDocumento())) {
		case FACRTURA:
			 baseComisiones = facturaDto.getCfdi().getTotal().subtract(facturaDto.getCfdi().getSubtotal());
			 context=devolucionesBuilderService.buildFacturaContextForPueDevolution(facturaDto, pagoDto);
			 devolucionEvaluatorService.devolucionPueValidation(context);
			 devolucionExecutorService.executeDevolucionForPue(context, client, baseComisiones);
			break;
		case COMPLEMENTO:
			context=devolucionesBuilderService.buildFacturaContextForComplementoDevolution(facturaDto, pagoDto);
			baseComisiones= context.getFacturaPadreDto().getCfdi().getTotal()
			.subtract(context.getFacturaPadreDto().getCfdi().getSubtotal())
			.divide(context.getFacturaPadreDto().getCfdi().getTotal());
			devolucionEvaluatorService.devolucionPpdValidation(context);
			devolucionExecutorService.executeDevolucionForPpd(context, client, baseComisiones);
			break;
		default:
			throw new InvoiceManagerException("The type of document not supported",
					String.format("The type of document %s not valid", facturaDto.getTipoDocumento()),
					HttpStatus.BAD_REQUEST.value());
		}
	}
	
	
}
