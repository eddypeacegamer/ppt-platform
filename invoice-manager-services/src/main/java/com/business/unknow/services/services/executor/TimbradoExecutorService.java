package com.business.unknow.services.services.executor;

import java.math.BigDecimal;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.commons.builder.EmailConfigBuilder;
import com.business.unknow.commons.util.MailHelper;
import com.business.unknow.enums.LineaEmpresaEnum;
import com.business.unknow.enums.TipoArchivoEnum;
import com.business.unknow.enums.TipoDocumentoEnum;
import com.business.unknow.enums.TipoEmail;
import com.business.unknow.model.config.FileConfig;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.cfdi.CfdiPagoDto;
import com.business.unknow.model.dto.files.FacturaFileDto;
import com.business.unknow.model.error.InvoiceCommonException;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.Client;
import com.business.unknow.services.entities.cfdi.CfdiPago;
import com.business.unknow.services.entities.factura.Factura;
import com.business.unknow.services.mapper.factura.CfdiMapper;
import com.business.unknow.services.mapper.factura.FacturaMapper;
import com.business.unknow.services.repositories.ClientRepository;
import com.business.unknow.services.repositories.facturas.CfdiPagoRepository;
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
	private CfdiPagoRepository cfdiPagoRepository;

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

	public void updateFacturaAndCfdiValues(FacturaContext context) {

		repository.save(mapper.getEntityFromFacturaDto(context.getFacturaDto()));
		cfdiRepository.save(cfdiMapper.getEntityFromCfdiDto(context.getFacturaDto().getCfdi()));
		for (FacturaFileDto facturaFileDto : context.getFacturaFilesDto()) {
			if (facturaFileDto != null) {
				filesService.upsertFacturaFile(facturaFileDto);
			}
		}
	}

	public void sentEmail(FacturaContext context, TipoEmail tipoEmail) throws InvoiceManagerException {
		if (context.getFacturaDto().getLineaEmisor().equals(LineaEmpresaEnum.A.name())) {
			Client client = clientRepository
					.findByCorreoPromotorAndClient(context.getFacturaDto().getSolicitante(),
							context.getFacturaDto().getRfcRemitente())
					.orElseThrow(() -> new InvoiceManagerException("Error sending the email",
							String.format("The client %s does not exists", context.getFacturaDto().getRfcEmisor()),
							HttpStatus.SC_CONFLICT));
			FacturaFileDto xml = context.getFacturaFilesDto().stream()
					.filter(a -> a.getTipoArchivo().equals(TipoArchivoEnum.XML.name())).findFirst()
					.orElseThrow(() -> new InvoiceManagerException("Error getting XML",
							"No se guardo el XML correctamente", HttpStatus.SC_CONFLICT));
			FacturaFileDto pdf = context.getFacturaFilesDto().stream()
					.filter(a -> a.getTipoArchivo().equals(TipoArchivoEnum.PDF.name())).findFirst()
					.orElseThrow(() -> new InvoiceManagerException("Error getting PDF",
							"No se guardo el PDF correctamente", HttpStatus.SC_CONFLICT));
			EmailConfigBuilder emailBuilder = new EmailConfigBuilder()
					.setEmisor(tipoEmail.equals(TipoEmail.SEMEL_JACK) ? context.getEmpresaDto().getCorreo()
							: tipoEmail.getEmail())
					.setPwEmisor(tipoEmail.equals(TipoEmail.SEMEL_JACK) ? context.getEmpresaDto().getPwCorreo()
							: tipoEmail.getPw())
					.setAsunto(String.format("Factura %s", context.getFacturaDto().getFolio()))
					.addReceptor(client.getCorreoPromotor()).addReceptor(client.getInformacionFiscal().getCorreo())
					.addReceptor(context.getEmpresaDto().getCorreo()).setPort(tipoEmail.getPort())
					.setDominio(tipoEmail.equals(TipoEmail.SEMEL_JACK) ? context.getEmpresaDto().getDominioCorreo()
							: tipoEmail.getHost())
					.addArchivo(new FileConfig(TipoArchivoEnum.XML,
							context.getFacturaDto().getFolio().concat(TipoArchivoEnum.XML.getFormat()), xml.getData()))
					.addArchivo(new FileConfig(TipoArchivoEnum.PDF,
							context.getFacturaDto().getFolio().concat(TipoArchivoEnum.PDF.getFormat()), pdf.getData()))
					.setCuerpo("Su factura timbrada es:");
			try {
				mailHelper.enviarCorreo(emailBuilder.build());
			} catch (InvoiceCommonException e) {
				e.printStackTrace();
				throw new InvoiceManagerException(e.getMessage(), e.getErrorMessage().getDeveloperMessage(),
						HttpStatus.SC_CONFLICT);
			}
		}
	}

	public void updateCanceladoValues(FacturaContext context) {
		repository.save(mapper.getEntityFromFacturaDto(context.getFacturaDto()));
		if (context.getFacturaDto().getTipoDocumento().equals(TipoDocumentoEnum.COMPLEMENTO.getDescripcion())) {

			if (context.getFacturaDto().getCfdi() != null && context.getFacturaDto().getCfdi().getComplemento() != null
					&& context.getFacturaDto().getCfdi().getComplemento().getPagos() != null) {
				for (CfdiPagoDto cfdiPagoDto : context.getFacturaDto().getCfdi().getComplemento().getPagos()) {
					CfdiPago cfdiPago = cfdiMapper.getEntityFromCdfiPagosDto(cfdiPagoDto);
					cfdiPago.setValido(false);
					cfdiPagoRepository.save(cfdiPago);
					if (cfdiPagoDto.getImporteSaldoAnterior().compareTo(BigDecimal.ZERO) > 0) {
						Optional<Factura> factura = repository.findByFolio(cfdiPagoDto.getFolio());
						if (factura.isPresent()) {
							factura.get().setSaldoPendiente(cfdiPagoDto.getImporteSaldoAnterior());
							repository.save(factura.get());
						}
					}
				}

			}

		}
	}
}
