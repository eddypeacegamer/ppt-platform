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
import java.io.StringWriter;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

import com.business.unknow.services.util.pdf.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.business.unknow.commons.util.FacturaHelper;
import com.business.unknow.commons.util.FileHelper;
import com.business.unknow.enums.TipoArchivoEnum;
import com.business.unknow.model.cfdi.Cfdi;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.FacturaPdfModelDto;
import com.business.unknow.model.dto.files.FacturaFileDto;
import com.business.unknow.model.dto.files.ResourceFileDto;
import com.business.unknow.model.error.InvoiceCommonException;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.files.FacturaFile;
import com.business.unknow.services.entities.files.ResourceFile;
import com.business.unknow.services.mapper.FilesMapper;
import com.business.unknow.services.repositories.files.FacturaFileRepository;
import com.business.unknow.services.repositories.files.ResourceFileRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

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
		try {
			FacturaFileDto qr = getFileByFolioAndType(folio, TipoArchivoEnum.QR.name());
			FacturaFileDto xml = getFileByFolioAndType(folio, TipoArchivoEnum.XML.name());
			ResourceFileDto logotipo = getFileByResourceReferenceAndType("Empresa", facturaDto.getRfcEmisor(), "LOGO");
			Cfdi cfdi = facturaHelper.getFacturaFromString(fileHelper.stringDecodeBase64(xml.getData()));
			return new FacturaPdfModelDto(qr.getData(), logotipo.getData(), cfdi);
		} catch (InvoiceManagerException e) {
			// TODO: create logic for pre pdf
			return new FacturaPdfModelDto();
		}

	}

	public byte[] generateInvoicePDF(String folio) {
		try {
			FacturaPdfModelDto model = getPdfFromFactura(folio);

			String xslfoTemplate = getXSLFOTemplate(model);
			//Reader templateReader = new FileReader(new File(ClassLoader.getSystemResource("pdf-config/" + xslfoTemplate).getFile()));
			Reader templateReader = new FileReader(new File("/Users/vvo0002/Documents/Temp/Invoice/temporal.xml"));
			Reader inputReader = new StringReader(getXmlContent(model));
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			pdfGenerator.render(inputReader, outputStream, templateReader);
			return outputStream.toByteArray();
		} catch (FileNotFoundException | InvoiceCommonException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The PDF cannot be created");
		}
	}

	private String getXSLFOTemplate(FacturaPdfModelDto model) {
		/*if(model.getFactura().getCfdi().getComplemento().getTimbreFiscal() == null) {
			return "pue_sin_timbrado.xml";
		} else {
			return "pue.xml";
		}*/
		return "pue.xml";
	}

	private String getXmlContent(FacturaPdfModelDto model) {
		Function<FacturaPdfModelDto, String> mapper = new Function<FacturaPdfModelDto, String>() {
			@Override
			public String apply(FacturaPdfModelDto facturaPdfModelDto) {

				try {
					StringWriter stringWriter = new StringWriter();

					JAXBContext jaxbContext = JAXBContext.newInstance(FacturaPdfModelDto.class);

					Marshaller marshaller = jaxbContext.createMarshaller();
					marshaller.marshal(new JAXBElement<FacturaPdfModelDto>(new QName("", "FacturaPdfModelDto"),
									FacturaPdfModelDto.class,
									null,
									facturaPdfModelDto),
							stringWriter);

					System.out.println(stringWriter.toString());
					return stringWriter.toString();
				} catch (JAXBException e) {
					//TODO: Handle Error
				}
				return null;
			}
		};

		return mapper.apply(model);
	}
}
