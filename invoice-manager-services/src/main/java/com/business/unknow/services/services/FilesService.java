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
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import com.business.unknow.model.cfdi.Cfdi;
import com.business.unknow.services.mapper.xml.CfdiXmlMapper;
import com.business.unknow.services.util.pdf.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.business.unknow.commons.builder.FacturaPdfModelDtoBuilder;
import com.business.unknow.commons.util.FacturaHelper;
import com.business.unknow.commons.util.FileHelper;
import com.business.unknow.enums.MetodosPagoEnum;
import com.business.unknow.enums.TipoArchivoEnum;
import com.business.unknow.enums.TipoComprobanteEnum;
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
	private FacturaService facturaService;

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

	public FacturaPdfModelDto getPdfFromFactura(String folio) throws InvoiceCommonException {
		FacturaDto facturaDto = facturaService.getFacturaByFolio(folio);
		FacturaPdfModelDtoBuilder fBuilder = new FacturaPdfModelDtoBuilder();
		try {
			FacturaFileDto xml = getFileByFolioAndType(folio, TipoArchivoEnum.XML.name());
			fBuilder.setQr(getFileByFolioAndType(folio, TipoArchivoEnum.QR.name()).getData())
					.setFactura(facturaHelper.getFacturaFromString(fileHelper.stringDecodeBase64(xml.getData())))
					.setMetodoPagoDesc(
							MetodosPagoEnum.findByValor(facturaDto.getCfdi().getMetodoPago()).getDescripcion())

					.setLogotipo(
							getFileByResourceReferenceAndType("Empresa", facturaDto.getRfcEmisor(), "LOGO").getData())
					.setTipoDeComprobanteDesc(TipoComprobanteEnum
							.findByValor(facturaDto.getCfdi().getTipoDeComprobante()).getDescripcion());
			FormaPago formaPago = catalogCacheService.getFormaPagoMappings().get(facturaDto.getCfdi().getFormaPago());
			RegimenFiscal regimenFiscal = catalogCacheService.getRegimenFiscalPagoMappings()
					.get(facturaDto.getCfdi().getEmisor().getRegimenFiscal());
			UsoCfdi usoCfdi = catalogCacheService.getUsoCfdiMappings()
					.get(facturaDto.getCfdi().getReceptor().getUsoCfdi());
			fBuilder.setFormaPagoDesc(formaPago == null ? null : formaPago.getDescripcion());
			fBuilder.setRegimenFiscalDesc(regimenFiscal == null ? null : regimenFiscal.getDescripcion());
			fBuilder.setUsoCfdiDesc(usoCfdi == null ? null : usoCfdi.getDescripcion());
			if (facturaDto.getCfdi().getComplemento().getTimbreFiscal() != null) {
				fBuilder.setCadenaOriginal(facturaDto.getCfdi().getComplemento().getTimbreFiscal().getCadenaOriginal());
			}
			return fBuilder.build();
		} catch (InvoiceManagerException e) {
			try {
				Cfdi cfdi = cfdiXmlMapper.getEntityFromCfdiDto(facturaDto.getCfdi());
				cfdi.setConceptos(facturaDto.getCfdi().getConceptos().stream()
						.map(cfdiXmlMapper::getEntityFromConceptoDto)
						.collect(Collectors.toList()));
				cfdi.setFecha(facturaDto.getFechaCreacion().toString());
				cfdi.getImpuestos().setTotalImpuestosTrasladados(cfdi.getTotal().subtract(cfdi.getSubtotal()));
				fBuilder.setFactura(cfdi)
						.setMetodoPagoDesc(
								MetodosPagoEnum.findByValor(facturaDto.getCfdi().getMetodoPago()).getDescripcion())

						.setLogotipo(
								getFileByResourceReferenceAndType("Empresa", facturaDto.getRfcEmisor(), "LOGO").getData())
						.setTipoDeComprobanteDesc(TipoComprobanteEnum
								.findByValor(facturaDto.getCfdi().getTipoDeComprobante()).getDescripcion());
				FormaPago formaPago = catalogCacheService.getFormaPagoMappings().get(facturaDto.getCfdi().getFormaPago());
				RegimenFiscal regimenFiscal = catalogCacheService.getRegimenFiscalPagoMappings()
						.get(facturaDto.getCfdi().getEmisor().getRegimenFiscal());
				UsoCfdi usoCfdi = catalogCacheService.getUsoCfdiMappings()
						.get(facturaDto.getCfdi().getReceptor().getUsoCfdi());
				fBuilder.setFormaPagoDesc(formaPago == null ? null : formaPago.getDescripcion());
				fBuilder.setRegimenFiscalDesc(regimenFiscal == null ? null : regimenFiscal.getDescripcion());
				fBuilder.setUsoCfdiDesc(usoCfdi == null ? null : usoCfdi.getDescripcion());
				return fBuilder.build();
			} catch (InvoiceManagerException e2) {
				//TODO: Run !
				return null;
			}
		}

	}

	public byte[] generateInvoicePDF(String folio) {
		try {
			FacturaPdfModelDto model = getPdfFromFactura(folio);
			String xmlContent = new FacturaHelper().facturaPdfToXml(model);

			String xslfoTemplate = getXSLFOTemplate(model);
			Reader templateReader = new FileReader(new File(ClassLoader.getSystemResource("pdf-config/" + xslfoTemplate).getFile()));
			//Reader templateReader = new FileReader(new File("/Users/vvo0002/Documents/Temp/Invoice/temporal.xml"));
			Reader inputReader = new StringReader(xmlContent);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			pdfGenerator.render(inputReader, outputStream, templateReader);
			return outputStream.toByteArray();
		} catch (FileNotFoundException | InvoiceCommonException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The PDF cannot be created");
		}
	}

	private String getXSLFOTemplate(FacturaPdfModelDto model) {
		if(model.getCadenaOriginal() != null) {
			return "factura-timbrada.xml";
		} else {
			return "factura-sin-timbrar.xml";
		}
	}
}
