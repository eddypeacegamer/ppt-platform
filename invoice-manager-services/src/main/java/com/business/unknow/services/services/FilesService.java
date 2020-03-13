/**
 *
 */
package com.business.unknow.services.services;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import com.business.unknow.model.cfdi.Cfdi;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.cfdi.CfdiPagoDto;
import com.business.unknow.services.mapper.xml.CfdiXmlMapper;
import com.business.unknow.services.util.pdf.PDFGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.business.unknow.commons.builder.FacturaPdfModelDtoBuilder;
import com.business.unknow.commons.util.FacturaHelper;
import com.business.unknow.commons.util.FileHelper;
import com.business.unknow.commons.util.NumberTranslatorHelper;
import com.business.unknow.enums.MetodosPagoEnum;
import com.business.unknow.enums.TipoArchivoEnum;
import com.business.unknow.enums.TipoComprobanteEnum;
import com.business.unknow.enums.TipoDocumentoEnum;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.FacturaPdfModelDto;
import com.business.unknow.model.dto.files.FacturaFileDto;
import com.business.unknow.model.dto.files.ResourceFileDto;
import com.business.unknow.model.error.InvoiceCommonException;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.catalogs.FormaPago;
import com.business.unknow.services.entities.catalogs.RegimenFiscal;
import com.business.unknow.services.entities.catalogs.UsoCfdi;
import com.business.unknow.services.entities.files.FacturaFile;
import com.business.unknow.services.entities.files.ResourceFile;
import com.business.unknow.services.mapper.FilesMapper;
import com.business.unknow.services.repositories.files.FacturaFileRepository;
import com.business.unknow.services.repositories.files.ResourceFileRepository;

/**
 * @author ralfdemoledor
 *
 */
@Service
public class FilesService {

	@Autowired
	private FacturaFileRepository facturaRepo;

	@Autowired
	private ResourceFileRepository resourceRepo;


	@Autowired
	private FilesMapper mapper;

	@Autowired
	private PDFGenerator pdfGenerator;

	@Autowired
	private FacturaHelper facturaHelper;

	@Autowired
	private FileHelper fileHelper;

	@Autowired
	private CatalogCacheService catalogCacheService;

	@Autowired
	private CfdiXmlMapper cfdiXmlMapper;

	@Autowired
	private NumberTranslatorHelper numberTranslatorHelper;
	
	
	
	private static final Logger log = LoggerFactory.getLogger(FilesService.class);


	public FacturaFileDto getFileByFolioAndType(String folio, String type) throws InvoiceManagerException {
		Optional<FacturaFile> file = facturaRepo.findByFolioAndTipoArchivo(folio, type);
		if (file.isPresent()) {
			return mapper.getFacturaFileDtoFromEntity(file.get());
		} else {
			throw new InvoiceManagerException("El recurso solicitado no existe.", HttpStatus.NOT_FOUND.value());
		}
	}

	public ResourceFileDto getFileByResourceReferenceAndType(String resource, String referencia, String type)
			throws InvoiceManagerException {
		Optional<ResourceFile> file = resourceRepo.findByTipoRecursoAndReferenciaAndTipoArchivo(resource, referencia,
				type);
		if (file.isPresent()) {
			return mapper.getResourceFileDtoFromEntity(file.get());
		} else {
			throw new InvoiceManagerException("El recurso solicitado no existe.", HttpStatus.NOT_FOUND.value());
		}
	}
	public ResourceFileDto insertResourceFile(ResourceFileDto resourceFile) {

		Optional<ResourceFile> resource = resourceRepo.findByTipoRecursoAndReferenciaAndTipoArchivo(
				resourceFile.getTipoRecurso(), resourceFile.getReferencia(), resourceFile.getTipoArchivo());
		if (resource.isPresent()) {
			resourceFile.setId(resource.get().getId());
			resourceFile.setFechaCreacion(new Date());
		}
		return mapper.getResourceFileDtoFromEntity(resourceRepo.save(mapper.getResourceFileFromDto(resourceFile)));
	}

	public FacturaFileDto insertfacturaFile(FacturaFileDto facturaFile) {
		Optional<FacturaFile> file  = facturaRepo.findByFolioAndTipoArchivo(facturaFile.getFolio(), facturaFile.getTipoArchivo());
		if (file.isPresent()) {
			facturaFile.setId(file.get().getId());
			facturaFile.setFechaCreacion(new Date());
		}
		return mapper.getFacturaFileDtoFromEntity(facturaRepo.save(mapper.getFacturaFileFromDto(facturaFile)));
	}

	public void deleteFacturaFile(Integer id) {
		Optional<FacturaFile> entity = facturaRepo.findById(id);
		if (entity.isPresent()) {
			facturaRepo.delete(entity.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El recurso solicitado no existe.");
		}
	}

	public void deleteResourceFile(Integer id) {
		Optional<ResourceFile> entity = resourceRepo.findById(id);
		if (entity.isPresent()) {
			resourceRepo.delete(entity.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El recurso solicitado no existe.");
		}
	}

	public FacturaPdfModelDto getPdfFromFactura(FacturaDto facturaDto) throws InvoiceCommonException {
		
		FacturaPdfModelDtoBuilder fBuilder = new FacturaPdfModelDtoBuilder();

		fBuilder.setFactura(getCfdiModelFromFacturaDto(facturaDto))
				.setFolioPadre(facturaDto.getFolioPadre())
				.setQr(getQRData(facturaDto.getFolio()))
				.setMetodoPagoDesc(MetodosPagoEnum.findByValor(facturaDto.getCfdi().getMetodoPago()).getDescripcion())
				.setLogotipo(getLogoData(facturaDto.getRfcEmisor()))
				.setTipoDeComprobanteDesc(TipoComprobanteEnum.findByValor(facturaDto.getCfdi().getTipoDeComprobante()).getDescripcion())
				.setTotalDesc(numberTranslatorHelper.getStringNumber(facturaDto.getCfdi().getTotal()))
				.setSubTotalDesc(numberTranslatorHelper.getStringNumber(facturaDto.getCfdi().getSubtotal()));

		RegimenFiscal regimenFiscal = catalogCacheService.getRegimenFiscalPagoMappings()
				.get(facturaDto.getCfdi().getEmisor().getRegimenFiscal());
		UsoCfdi usoCfdi = catalogCacheService.getUsoCfdiMappings()
				.get(facturaDto.getCfdi().getReceptor().getUsoCfdi());
		if (facturaDto.getTipoDocumento().equals(TipoDocumentoEnum.COMPLEMENTO.getDescripcion())) {
			FormaPago formaPago = catalogCacheService.getFormaPagoMappings()
					.get(facturaDto.getCfdi().getComplemento().getPagos().get(0).getFormaPago());
			fBuilder.setFormaPagoDesc(formaPago == null ? null : formaPago.getDescripcion());
		} else {
			FormaPago formaPago = catalogCacheService.getFormaPagoMappings()
					.get(facturaDto.getCfdi().getFormaPago());
			fBuilder.setFormaPagoDesc(formaPago == null ? null : formaPago.getDescripcion());
		}
		fBuilder.setRegimenFiscalDesc(regimenFiscal == null ? null : regimenFiscal.getDescripcion());
		fBuilder.setUsoCfdiDesc(usoCfdi == null ? null : usoCfdi.getDescripcion());

		if (facturaDto.getCfdi().getComplemento().getTimbreFiscal() != null) {
			fBuilder.setCadenaOriginal(facturaDto.getCfdi().getComplemento().getTimbreFiscal().getCadenaOriginal());
		}

		if(facturaDto.getFolioPadre() != null && !facturaDto.getCfdi().getComplemento().getPagos().isEmpty()) {
			CfdiPagoDto pagoComplemento = facturaDto.getCfdi().getComplemento().getPagos().get(0);
			fBuilder.setPagoComplemento(pagoComplemento)
					.setTotalDesc(numberTranslatorHelper.getStringNumber(
							pagoComplemento.getImporteSaldoAnterior().subtract(pagoComplemento.getImporteSaldoInsoluto())));
		}
		return fBuilder.build();
	}
	
public FacturaPdfModelDto getPdfFromFactura(FacturaContext context) throws InvoiceCommonException {
		
		FacturaPdfModelDtoBuilder fBuilder = new FacturaPdfModelDtoBuilder();
		FacturaDto facturaDto = context.getFacturaDto();

		fBuilder.setFactura(getCfdiModelFromContext(context))
				.setFolioPadre(facturaDto.getFolioPadre())
				.setQr(getQRData(facturaDto.getFolio()))
				.setMetodoPagoDesc(MetodosPagoEnum.findByValor(facturaDto.getCfdi().getMetodoPago()).getDescripcion())
				.setLogotipo(getLogoData(facturaDto.getRfcEmisor()))
				.setTipoDeComprobanteDesc(TipoComprobanteEnum.findByValor(facturaDto.getCfdi().getTipoDeComprobante()).getDescripcion())
				.setTotalDesc(numberTranslatorHelper.getStringNumber(facturaDto.getCfdi().getTotal()))
				.setSubTotalDesc(numberTranslatorHelper.getStringNumber(facturaDto.getCfdi().getSubtotal()));

		RegimenFiscal regimenFiscal = catalogCacheService.getRegimenFiscalPagoMappings()
				.get(facturaDto.getCfdi().getEmisor().getRegimenFiscal());
		UsoCfdi usoCfdi = catalogCacheService.getUsoCfdiMappings()
				.get(facturaDto.getCfdi().getReceptor().getUsoCfdi());
		if (facturaDto.getTipoDocumento().equals(TipoDocumentoEnum.COMPLEMENTO.getDescripcion())) {
			FormaPago formaPago = catalogCacheService.getFormaPagoMappings()
					.get(facturaDto.getCfdi().getComplemento().getPagos().get(0).getFormaPago());
			fBuilder.setFormaPagoDesc(formaPago == null ? null : formaPago.getDescripcion());
		} else {
			FormaPago formaPago = catalogCacheService.getFormaPagoMappings()
					.get(facturaDto.getCfdi().getFormaPago());
			fBuilder.setFormaPagoDesc(formaPago == null ? null : formaPago.getDescripcion());
		}
		fBuilder.setRegimenFiscalDesc(regimenFiscal == null ? null : regimenFiscal.getDescripcion());
		fBuilder.setUsoCfdiDesc(usoCfdi == null ? null : usoCfdi.getDescripcion());

		if (facturaDto.getCfdi().getComplemento().getTimbreFiscal() != null) {
			fBuilder.setCadenaOriginal(facturaDto.getCfdi().getComplemento().getTimbreFiscal().getCadenaOriginal());
		}

		if(facturaDto.getFolioPadre() != null && !facturaDto.getCfdi().getComplemento().getPagos().isEmpty()) {
			CfdiPagoDto pagoComplemento = facturaDto.getCfdi().getComplemento().getPagos().get(0);
			fBuilder.setPagoComplemento(pagoComplemento)
					.setTotalDesc(numberTranslatorHelper.getStringNumber(
							pagoComplemento.getImporteSaldoAnterior().subtract(pagoComplemento.getImporteSaldoInsoluto())));
		}
		return fBuilder.build();
	}

	private Cfdi getCfdiModelFromFacturaDto(FacturaDto dto) {
		try {
			FacturaFileDto xml = getFileByFolioAndType(dto.getFolio(), TipoArchivoEnum.XML.name());
			return facturaHelper.getFacturaFromString(fileHelper.stringDecodeBase64(xml.getData()));
		} catch (InvoiceManagerException | InvoiceCommonException e) {
			Cfdi cfdi = cfdiXmlMapper.getEntityFromCfdiDto(dto.getCfdi());
			cfdi.setConceptos(dto.getCfdi().getConceptos().stream()
					.map(cfdiXmlMapper::getEntityFromConceptoDto)
					.collect(Collectors.toList()));
			cfdi.setFecha((dto.getFechaCreacion()==null)?new Date().toString():dto.getFechaCreacion().toString());
			cfdi.getImpuestos().setTotalImpuestosTrasladados(cfdi.getTotal().subtract(cfdi.getSubtotal()));
			return cfdi;
		}
	}
	
	private Cfdi getCfdiModelFromContext(FacturaContext context) {
		try {
			FacturaFileDto xml = context.getFacturaFilesDto().stream().filter(t -> "XML".equalsIgnoreCase(t.getTipoArchivo())).findFirst().get();
			return facturaHelper.getFacturaFromString(fileHelper.stringDecodeBase64(xml.getData()));
		} catch (InvoiceCommonException e) {
			Cfdi cfdi = cfdiXmlMapper.getEntityFromCfdiDto(context.getFacturaDto().getCfdi());
			cfdi.setConceptos(context.getFacturaDto().getCfdi().getConceptos().stream()
					.map(cfdiXmlMapper::getEntityFromConceptoDto)
					.collect(Collectors.toList()));
			cfdi.setFecha((context.getFacturaDto().getFechaCreacion()==null)?new Date().toString():context.getFacturaDto().getFechaCreacion().toString());
			cfdi.getImpuestos().setTotalImpuestosTrasladados(cfdi.getTotal().subtract(cfdi.getSubtotal()));
			return cfdi;
		}
	}

	private String getQRData(String folio) {
		try {
			return getFileByFolioAndType(folio, TipoArchivoEnum.QR.name()).getData();
		} catch (InvoiceManagerException e) {
			return null;
		}
	}

	private String getLogoData(String rfcEmisor){
		try {
			return getFileByResourceReferenceAndType("Empresa", rfcEmisor, "LOGO").getData();
		} catch (InvoiceManagerException e) {
			return null;
		}
	}

	public FacturaFileDto generateInvoicePDF(FacturaDto factura) {
		try {
			FacturaPdfModelDto model = getPdfFromFactura(factura);
			String xmlContent = new FacturaHelper().facturaPdfToXml(model);

			String xslfoTemplate = getXSLFOTemplate(model);
			Reader templateReader = new FileReader(new File(ClassLoader.getSystemResource("pdf-config/" + xslfoTemplate).getFile()));
			Reader inputReader = new StringReader(xmlContent);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			pdfGenerator.render(inputReader, outputStream, templateReader);
			 String data = Base64.getEncoder().encodeToString(outputStream.toByteArray());
			 FacturaFileDto factFile = new FacturaFileDto();
			 factFile.setData(data);
			 factFile.setFolio(factura.getFolio());
			 factFile.setTipoArchivo("PDF");
			 insertfacturaFile(factFile);
			 log.info("PDF for factura {} was generated successfully", factura.getFolio());
			 return factFile;
		} catch (FileNotFoundException | InvoiceCommonException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The PDF cannot be created");
		}
	}
	
	public FacturaFileDto generateInvoicePDF(FacturaContext context) {
		try {
			FacturaPdfModelDto model = getPdfFromFactura(context.getFacturaDto());
			model.setQr(context.getFacturaFilesDto().stream().filter(f->"QR".equalsIgnoreCase(f.getTipoArchivo())).findFirst().get().getData());
			String xmlContent = new FacturaHelper().facturaPdfToXml(model);

			String xslfoTemplate = getXSLFOTemplate(model);
			Reader templateReader = new FileReader(new File(ClassLoader.getSystemResource("pdf-config/" + xslfoTemplate).getFile()));
			Reader inputReader = new StringReader(xmlContent);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			pdfGenerator.render(inputReader, outputStream, templateReader);
			 String data = Base64.getEncoder().encodeToString(outputStream.toByteArray());
			 FacturaFileDto factFile = new FacturaFileDto();
			 factFile.setData(data);
			 factFile.setFolio(context.getFacturaDto().getFolio());
			 factFile.setTipoArchivo("PDF");
			 insertfacturaFile(factFile);
			 log.info("PDF for factura {} was generated successfully", context.getFacturaDto().getFolio());
			 return factFile;
		} catch (FileNotFoundException | InvoiceCommonException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The PDF cannot be created");
		}
	}

	private String getXSLFOTemplate(FacturaPdfModelDto model) {
		if(model.getCadenaOriginal() != null) {
			return model.getFolioPadre() == null ? "factura-timbrada.xml" : "complemento-timbrado.xml";
		} else {
			return model.getFolioPadre() == null ? "factura-sin-timbrar.xml" : "complemento-sin-timbrar.xml";
		}
	}
}
