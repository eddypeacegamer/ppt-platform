package com.business.unknow.services.services.executor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.enums.FormaPagoEnum;
import com.business.unknow.enums.TipoDocumentoEnum;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.services.PagoDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.mapper.PagoMapper;
import com.business.unknow.services.repositories.PagoRepository;
import com.business.unknow.services.services.FacturaService;

@Service
public class PagoExecutorService extends AbstractExecutorService {

	@Autowired
	private FacturaService facturaService;
	
	@Autowired
	protected PagoRepository pagoRepository;

	@Autowired
	protected PagoMapper pagoMapper;

	public void deletePagoExecutor(PagoDto payment,List<PagoDto> payments,FacturaDto factura) throws InvoiceManagerException {
		if(TipoDocumentoEnum.COMPLEMENTO.equals(TipoDocumentoEnum.findByDesc(factura.getTipoDocumento()))) {
			facturaService.deleteFactura(payment.getFolio());
		}
		Optional<PagoDto> creditPayment = payments.stream().filter(p->FormaPagoEnum.CREDITO.getPagoValue().equals(p.getFormaPago())).findAny();
		if (creditPayment.isPresent()) { // updating credit payment
			creditPayment.get().setMonto(creditPayment.get().getMonto().add(payment.getMonto()));
			pagoRepository.save(pagoMapper.getEntityFromPagoDto(creditPayment.get()));
		}
		deleteAllResourceFileByFolio(
				payment.getId().toString().concat("_").concat(payment.getFolio()));
		
		pagoRepository.delete(pagoMapper.getEntityFromPagoDto(payment));

	}


	public PagoDto createPagoExecutor(PagoDto payment,List<PagoDto> payments) {
		Optional<PagoDto> creditPayment = payments.stream().filter(p->FormaPagoEnum.CREDITO.getPagoValue().equals(p.getFormaPago())).findAny();
		if (creditPayment.isPresent()) { // updating credit payment
			if(payment.getMonto().compareTo(creditPayment.get().getMonto())>0) {
				creditPayment.get().setMonto(BigDecimal.ZERO);
			}else {
				creditPayment.get().setMonto(creditPayment.get().getMonto().subtract(payment.getMonto()));
				pagoRepository.save(pagoMapper.getEntityFromPagoDto(creditPayment.get()));	
			}
		}
		return pagoMapper.getPagoDtoFromEntity(pagoRepository.save(pagoMapper.getEntityFromPagoDto(payment)));
	}

	

}