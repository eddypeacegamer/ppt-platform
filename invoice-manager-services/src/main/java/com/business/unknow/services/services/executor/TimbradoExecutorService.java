package com.business.unknow.services.services.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.business.unknow.commons.builder.EmailConfigBuilder;
import com.business.unknow.commons.util.MailHelper;
import com.business.unknow.enums.LineaEmpresaEnum;
import com.business.unknow.enums.TipoArchivoEnum;
import com.business.unknow.model.config.FileConfig;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.files.FacturaFileDto;
import com.business.unknow.model.error.InvoiceCommonException;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.Client;
import com.business.unknow.services.mapper.factura.CfdiMapper;
import com.business.unknow.services.mapper.factura.FacturaMapper;
import com.business.unknow.services.repositories.ClientRepository;
import com.business.unknow.services.repositories.facturas.CfdiRepository;
import com.business.unknow.services.repositories.facturas.FacturaRepository;
import com.business.unknow.services.services.FilesService;

@Service
public class TimbradoExecutorService {

	@Autowired
	private FacturaRepository repository;

	@Autowired
	private CfdiRepository cfdiRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private FacturaMapper mapper;

	@Autowired
	private CfdiMapper cfdiMapper;

	@Autowired
	private MailHelper mailHelper;
	
	@Autowired
	private FilesService filesService;
	
	
	
	private static final Logger log = LoggerFactory.getLogger(TimbradoExecutorService.class);


	public void updateFacturaAndCfdiValues(FacturaContext context) {
		context.getFacturaDto().setTotal(context.getFacturaDto().getCfdi().getTotal());
		repository.save(mapper.getEntityFromFacturaDto(context.getFacturaDto()));
		cfdiRepository.save(cfdiMapper.getEntityFromCfdiDto(context.getFacturaDto().getCfdi()));
		for (FacturaFileDto facturaFileDto : context.getFacturaFilesDto()) {
			if (facturaFileDto != null) {
				filesService.upsertFacturaFile(facturaFileDto);
			}
		}

	}

	public void createFilesAndSentEmail(FacturaContext context) throws InvoiceManagerException {
		if (context.getEmpresaDto().getTipo().equals(LineaEmpresaEnum.A.name())) {
			Client client = clientRepository.findByRfc(context.getFacturaDto().getRfcRemitente())
					.orElseThrow(() -> new InvoiceManagerException("Error sending the email",
							String.format("The client %s does not exists", context.getFacturaDto().getRfcEmisor()),
							HttpStatus.CONFLICT.value()));
			FacturaFileDto xml = context.getFacturaFilesDto().stream()
					.filter(a -> a.getTipoArchivo().equals(TipoArchivoEnum.XML.name())).findFirst()
					.orElseThrow(() -> new InvoiceManagerException("Error getting XML",
							"No se guardo el XML correctamente", HttpStatus.CONFLICT.value()));
			FacturaFileDto pdf = context.getFacturaFilesDto().stream()
					.filter(a -> a.getTipoArchivo().equals(TipoArchivoEnum.PDF.name())).findFirst()
					.orElseThrow(() -> new InvoiceManagerException("Error getting PDF",
							"No se guardo el PDF correctamente", HttpStatus.CONFLICT.value()));
			EmailConfigBuilder emailBuilder = new EmailConfigBuilder().setEmisor(context.getEmpresaDto().getCorreo())
					.setPwEmisor(context.getEmpresaDto().getPwCorreo())
					.setAsunto(String.format("Factura %s", context.getFacturaDto().getFolio()))
					.addReceptor(client.getCorreoPromotor()).addReceptor(client.getInformacionFiscal().getCorreo())
					.addReceptor(context.getEmpresaDto().getCorreo())
					.setDominio(context.getEmpresaDto().getDominioCorreo())
					.addArchivo(new FileConfig(TipoArchivoEnum.XML,
							context.getFacturaDto().getFolio().concat(TipoArchivoEnum.XML.getFormat()), xml.getData()))
					.addArchivo(new FileConfig(TipoArchivoEnum.PDF,
							context.getFacturaDto().getFolio().concat(TipoArchivoEnum.PDF.getFormat()), pdf.getData()))
					.setCuerpo("Su factura timbrada es:");
			try {
				mailHelper.enviarCorreo(emailBuilder.build());
			} catch (InvoiceCommonException e) {
				log.error(e.getMessage(),e);
				throw new InvoiceManagerException(e.getMessage(), e.getErrorMessage().getDeveloperMessage(),HttpStatus.CONFLICT.value());
			}
		}
	}

	public void updateCanceladoValues(FacturaContext context) {
		repository.save(mapper.getEntityFromFacturaDto(context.getFacturaDto()));
	}
}
