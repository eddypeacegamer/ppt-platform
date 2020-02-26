package com.business.unknow.services.services.executor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.enums.FormaPagoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.cfdi.ConceptoDto;
import com.business.unknow.model.dto.cfdi.ImpuestoDto;
import com.business.unknow.model.dto.services.PagoDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.cfdi.Cfdi;
import com.business.unknow.services.entities.cfdi.CfdiPago;
import com.business.unknow.services.entities.cfdi.Concepto;
import com.business.unknow.services.entities.cfdi.Emisor;
import com.business.unknow.services.entities.cfdi.Impuesto;
import com.business.unknow.services.entities.cfdi.Receptor;
import com.business.unknow.services.entities.factura.Factura;
import com.business.unknow.services.mapper.PagoMapper;
import com.business.unknow.services.mapper.factura.CfdiMapper;
import com.business.unknow.services.mapper.factura.FacturaMapper;
import com.business.unknow.services.repositories.PagoRepository;
import com.business.unknow.services.repositories.facturas.CfdiPagoRepository;
import com.business.unknow.services.repositories.facturas.CfdiRepository;
import com.business.unknow.services.repositories.facturas.ConceptoRepository;
import com.business.unknow.services.repositories.facturas.EmisorRepository;
import com.business.unknow.services.repositories.facturas.FacturaRepository;
import com.business.unknow.services.repositories.facturas.ImpuestoRepository;
import com.business.unknow.services.repositories.facturas.ReceptorRepository;
import com.business.unknow.services.services.CfdiService;

@Service
public class PagoExecutorService extends AbstractExecutorService {

	@Autowired
	private CfdiService cfdiService;
	
	@Autowired
	private FacturaRepository repository;

	@Autowired
	protected PagoRepository pagoRepository;

	@Autowired
	protected CfdiRepository cfdiRepository;
	
	@Autowired
	protected ConceptoRepository conceptoRepository;

	@Autowired
	private ImpuestoRepository impuestoRepository;
	
	@Autowired
	protected ReceptorRepository receptorRepository;

	@Autowired
	protected EmisorRepository emisorRepository;

	@Autowired
	private CfdiPagoRepository cfdiPagoRepository;


	@Autowired
	private FacturaMapper mapper;

	@Autowired
	protected PagoMapper pagoMapper;

	@Autowired
	protected CfdiMapper cfdiMapper;

	public void deletePagoPpdExecutor(FacturaContext context) throws InvoiceManagerException {
		repository.delete(mapper.getEntityFromFacturaDto(context.getFacturaDto()));
		context.getPagoCredito().setMonto(context.getPagoCredito().getMonto().add(context.getCurrentPago().getMonto()));
		pagoRepository.save(pagoMapper.getEntityFromPagoDto(context.getPagoCredito()));
		pagoRepository.delete(pagoMapper.getEntityFromPagoDto(context.getCurrentPago()));
		deleteAllResourceFileByFolio(
				context.getFacturaDto().getFolio().concat("_").concat(context.getCurrentPago().getFormaPago()));
		cfdiService.deleteCfdi(context.getFacturaDto().getFolio());
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


	public PagoDto creaPagoPpdExecutor(FacturaContext context) {
		Cfdi cfdi = cfdiMapper.getEntityFromCfdiDto(context.getFacturaDto().getCfdi());
		cfdi=cfdiRepository.save(cfdi);
		Factura factura=mapper.getEntityFromFacturaDto(context.getFacturaDto());
		factura.setIdCfdi(cfdi.getId());
		repository.save(factura);
		Receptor receptor = cfdiMapper.getEntityFromEmisorDto(context.getFacturaDto().getCfdi().getReceptor());
		receptor.setCfdi(cfdi);
		receptorRepository.save(receptor);
		Emisor emisor = cfdiMapper.getEntityFromEmisorDto(context.getFacturaDto().getCfdi().getEmisor());
		emisor.setCfdi(cfdi);
		emisorRepository.save(emisor);
		List<CfdiPago> cfdiPagos = cfdiMapper
				.getEntityFromCdfiPagosDtos(context.getFacturaDto().getCfdi().getComplemento().getPagos());
		for (ConceptoDto conceptoDto : context.getFacturaDto().getCfdi().getConceptos()) {
			Concepto concepto = cfdiMapper.getEntityFromConceptoDto(conceptoDto);
			concepto.setCfdi(cfdi);
			conceptoRepository.save(concepto);
			for (ImpuestoDto impuesto : conceptoDto.getImpuestos()) {
				Impuesto imp = cfdiMapper.getEntityFromImpuestoDto(impuesto);
				imp.setConcepto(concepto);
				impuestoRepository.save(imp);
			}
		}
		for (CfdiPago pago : cfdiPagos) {
			pago.setCfdi(cfdi);
			cfdiPagoRepository.save(pago);
		}
		
//		TimbradoFiscalDigitial timbradoFiscalDigitial = cfdiMapper
//				.getEntityFromComplementoDto(context.getFacturaDto().getCfdi().getComplemento().getTimbreFiscal());
//		timbradoFiscalDigitial.setCfdi(cfdi);
//		timbradoFiscalDigitialRepository.save(timbradoFiscalDigitial);
		
		pagoRepository.save(pagoMapper.getEntityFromPagoDto(context.getPagoCredito()));//Update credito payment
		
		return pagoMapper.getPagoDtoFromEntity(pagoRepository.save(pagoMapper.getEntityFromPagoDto(context.getCurrentPago())));
		
	}
	
	public PagoDto creaPagoPueExecutor(FacturaContext context) {
		if (context.getPagoCredito() != null) {
			pagoRepository.save(pagoMapper.getEntityFromPagoDto(context.getPagoCredito()));
		}
		return pagoMapper.getPagoDtoFromEntity(pagoRepository.save(pagoMapper.getEntityFromPagoDto(context.getCurrentPago())));
	}

	

}