package com.business.unknow.services.services.executor;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.business.unknow.services.entities.cfdi.Cfdi;
import com.business.unknow.services.entities.cfdi.TimbradoFiscalDigitial;
import com.business.unknow.services.mapper.FilesMapper;
import com.business.unknow.services.mapper.factura.CfdiMapper;
import com.business.unknow.services.mapper.factura.FacturaMapper;
import com.business.unknow.services.repositories.ClientRepository;
import com.business.unknow.services.repositories.facturas.CfdiRepository;
import com.business.unknow.services.repositories.facturas.FacturaRepository;
import com.business.unknow.services.repositories.facturas.TimbradoFiscalDigitialRepository;
import com.business.unknow.services.repositories.files.FacturaFileRepository;

@Service
public class TimbradoExecutorService {

	@Autowired
	private FacturaRepository repository;

	@Autowired
	private CfdiRepository cfdiRepository;
	@Autowired
	private FacturaFileRepository facturaFileRepository;

	@Autowired
	private TimbradoFiscalDigitialRepository timbradoFiscalDigitialRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private FacturaMapper mapper;

	@Autowired
	private CfdiMapper cfdiMapper;

	@Autowired
	private FilesMapper filesMapper;

	@Autowired
	private MailHelper mailHelper;

	public void updateFacturaAndCfdiValues(FacturaContext context) throws InvoiceManagerException {
		repository.save(mapper.getEntityFromFacturaDto(context.getFacturaDto()));
		Cfdi cfdi = cfdiRepository.save(cfdiMapper.getEntityFromCfdiDto(context.getFacturaDto().getCfdi()));
		TimbradoFiscalDigitial timbradoFiscalDigitial = cfdiMapper
				.getEntityFromComplementoDto(context.getFacturaDto().getCfdi().getComplemento().getTimbreFiscal());
		timbradoFiscalDigitial.setCfdi(cfdi);
		timbradoFiscalDigitialRepository.save(timbradoFiscalDigitial);

		for (FacturaFileDto facturaFileDto : context.getFacturaFilesDto()) {
			if (facturaFileDto != null) {
				facturaFileRepository.save(filesMapper.getFacturaFileFromDto(facturaFileDto));
			}
		}
		if (context.getEmpresaDto().getTipo().equals(LineaEmpresaEnum.A.name())) {
			Client client = clientRepository.findByRfc(context.getFacturaDto().getRfcRemitente())
					.orElseThrow(() -> new InvoiceManagerException("Error sending the email",
							String.format("The client %s does not exists", context.getFacturaDto().getRfcEmisor()),
							HttpStatus.SC_CONFLICT));
			FacturaFileDto xml = context.getFacturaFilesDto().stream()
					.filter(a -> a.getTipoArchivo().equals(TipoArchivoEnum.XML.name())).findFirst()
					.orElseThrow(() -> new InvoiceManagerException("Error getting xml",
							"No se guardo el xml correctamente", HttpStatus.SC_CONFLICT));
			FacturaFileDto pdf = context.getFacturaFilesDto().stream()
					.filter(a -> a.getTipoArchivo().equals(TipoArchivoEnum.PDF.name())).findFirst()
					.orElseThrow(() -> new InvoiceManagerException("Error getting xml",
							"No se guardo el xml correctamente", HttpStatus.SC_CONFLICT));
			EmailConfigBuilder emailBuilder = new EmailConfigBuilder().setEmisor(context.getEmpresaDto().getCorreo())
					.setPwEmisor(context.getEmpresaDto().getPwCorreo())
					.setAsunto(String.format("Factura %s", context.getFacturaDto().getFolio()))
					.addReceptor(client.getCorreoPromotor()).addReceptor(client.getInformacionFiscal().getCorreo())
					.addReceptor(client.getCorreoContacto())
					.addArchivo(new FileConfig(TipoArchivoEnum.XML,
							context.getFacturaDto().getFolio().concat(TipoArchivoEnum.XML.getFormat()), xml.getData()))
					.addArchivo(new FileConfig(TipoArchivoEnum.PDF,
							context.getFacturaDto().getFolio().concat(TipoArchivoEnum.PDF.getFormat()), pdf.getData()))
					.setCuerpo("Su factura timbrada es:");
			try {
				mailHelper.enviarCorreo(emailBuilder.build());
			} catch (InvoiceCommonException e) {
				e.printStackTrace();
				new InvoiceManagerException(e.getMessage(), e.getErrorMessage().getDeveloperMessage(),
						HttpStatus.SC_CONFLICT);
			}
		}
	}

	public void updateCanceladoValues(FacturaContext context) {
		repository.save(mapper.getEntityFromFacturaDto(context.getFacturaDto()));
	}
}
