package com.business.unknow.services.services.executor;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.enums.MetodosPagoEnum;
import com.business.unknow.enums.TipoDocumentoEnum;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.pagos.PagoDto;
import com.business.unknow.model.dto.pagos.PagoFacturaDto;
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

	public void deletePagoExecutor(PagoDto payment) throws InvoiceManagerException {
		for (PagoFacturaDto factRef : payment.getFacturas()) {
			FacturaDto fact = facturaService.getFacturaBaseByPrefolio(factRef.getIdCfdi());
			if(TipoDocumentoEnum.COMPLEMENTO.equals(TipoDocumentoEnum.findByDesc(fact.getTipoDocumento()))) {
				facturaService.deleteFactura(fact.getFolio());
				deleteAllResourceFileByFolio(
						payment.getId().toString().concat("_").concat(fact.getFolio()));
			}
			if(TipoDocumentoEnum.FACTURA.equals(TipoDocumentoEnum.findByDesc(fact.getTipoDocumento())) &&
					MetodosPagoEnum.PUE.getClave().equals(fact.getMetodoPago())) {
				fact.setSaldoPendiente(fact.getSaldoPendiente().add(factRef.getMonto()));
				facturaService.updateFactura(fact.getIdCfdi(), fact);
			}
			
		}
		pagoRepository.delete(pagoMapper.getEntityFromPagoDto(payment));

	}


	public void createPagoExecutor(PagoFacturaDto payment) {
		
		FacturaDto factura = facturaService.getFacturaBaseByPrefolio(payment.getIdCfdi());
		
		BigDecimal saldoInsoluto = factura.getSaldoPendiente().subtract(payment.getMonto());
		
		factura.setSaldoPendiente(saldoInsoluto);
		
		//TODO 
		//updateFActuraBase()
		
//		Optional<PagoDto> creditPayment = payments.stream().filter(p->FormaPagoEnum.CREDITO.getPagoValue().equals(p.getFormaPago())).findAny();
//		if (creditPayment.isPresent()) { // updating credit payment
//			if(payment.getMonto().compareTo(creditPayment.get().getMonto())>0) {
//				creditPayment.get().setMonto(BigDecimal.ZERO);
//			}else {
//				creditPayment.get().setMonto(creditPayment.get().getMonto().subtract(payment.getMonto()));
//				pagoRepository.save(pagoMapper.getEntityFromPagoDto(creditPayment.get()));	
//			}
//		}
	}
	
}