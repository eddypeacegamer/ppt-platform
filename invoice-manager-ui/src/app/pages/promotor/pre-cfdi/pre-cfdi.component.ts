import { Component, OnInit, OnDestroy} from '@angular/core';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { ClientsData } from '../../../@core/data/clients-data';
import { CompaniesData } from '../../../@core/data/companies-data';
import { HttpErrorResponse } from '@angular/common/http';
import { Contribuyente } from '../../../models/contribuyente';
import { Empresa } from '../../../models/empresa';
import { Cfdi } from '../../../models/factura/cfdi';
import { Client } from '../../../models/client';
import { Factura } from '../../../models/factura/factura';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { ActivatedRoute } from '@angular/router';
import { Catalogo } from '../../../models/catalogos/catalogo';
import { map } from 'rxjs/operators';
import { DonwloadFileService } from '../../../@core/util-services/download-file-service';
import { UsersData } from '../../../@core/data/users-data';
import { FilesData } from '../../../@core/data/files-data';
import { CfdiValidatorService } from '../../../@core/util-services/cfdi-validator.service';
import { GenericPage } from '../../../models/generic-page';
import { User } from '../../../models/user';
import { Pago } from '../../../models/factura/pago';
import { CfdiData } from '../../../@core/data/cfdi-data';


@Component({
  selector: 'ngx-pre-cfdi',
  templateUrl: './pre-cfdi.component.html',
  styleUrls: ['../../pages.component.scss'],
})
export class PreCfdiComponent implements OnInit, OnDestroy {

  public preFolio: string;
  public pagosCfdi: Pago[] = [];
  public girosCat: Catalogo[] = [];
  public companiesCat: Empresa[] = [];
  public validationCat: Catalogo[] = [];

  public clientsCat: Client[] = [];
  public factura: Factura = new Factura();
  public user: User;
  public successMessage: string;
  public errorMessages: string[] = [];
  public formInfo = { clientName: '', clientRfc: '*', companyRfc: '', giro: '*', empresa: '*' };
  public clientInfo: Contribuyente;
  public companyInfo: Empresa;

  public loading: boolean = false;
  public clientSearchMsg = '';

  constructor(
    private catalogsService: CatalogsData,
    private clientsService: ClientsData,
    private companiesService: CompaniesData,
    private invoiceService: InvoicesData,
    private cfdiService: CfdiData,
    private cfdiValidator: CfdiValidatorService,
    private userService: UsersData,
    private filesService: FilesData,
    private downloadService: DonwloadFileService,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.userService.getUserInfo().then(user => this.user = user as User);
    this.initVariables();
    /* preloaded cats*/
    this.catalogsService.getStatusValidacion().then(cat => this.validationCat = cat);
    this.catalogsService.getAllGiros().then(cat => this.girosCat = cat)
    .then(() => {
        this.route.paramMap.subscribe(route => {
          this.preFolio = route.get('folio');
          if (this.preFolio !== '*') {
            this.getInvoiceInfoByIdCdfi(this.preFolio);
          } else {
            this.initVariables();
          }
        });
      });
  }



  ngOnDestroy() {
    /** CLEAN VARIABLES **/
    this.factura = new Factura();
  }

  public initVariables() {
    /** INIT VARIABLES **/
    this.factura = new Factura();
    this.loading = false;
    this.factura.cfdi.moneda = 'MXN';
    this.factura.cfdi.metodoPago = '*';
    this.factura.cfdi.formaPago = '*';
    this.factura.cfdi.receptor.usoCfdi = '*';
    this.errorMessages = [];
    this.clientSearchMsg = '';
  }

  public getInvoiceInfoByIdCdfi(preFolio: string) {
    const idCfdi: number = +preFolio;
    this.preFolio = preFolio;
    this.pagosCfdi = [];
    this.cfdiService.getFacturaInfo(idCfdi).pipe(
      map((fac: Factura) => {
        fac.statusFactura = this.validationCat.find(v => v.id === fac.statusFactura).nombre;
        return fac;
      })).subscribe(invoice => {
        this.factura = invoice;
        this.cfdiService.getCfdiByFolio(idCfdi)
            .subscribe(cfdi => {
            this.factura.cfdi = cfdi;
            if (cfdi.metodoPago === 'PPD' && cfdi.tipoDeComprobante === 'P') {
              this.pagosCfdi = cfdi.complemento.pagos;
            }
          });
          if (invoice.metodoPago === 'PPD' && invoice.tipoDocumento === 'Factura') {
            this.cfdiService.findPagosPPD(idCfdi)
              .subscribe(pagos => this.pagosCfdi = pagos);
          }
      },
        error => {
          console.error('Info cant found, creating a new invoice:', error);
          this.initVariables();
        });
  }

  onGiroSelection(giroId: string) {
    const value = +giroId;
    if (isNaN(value)) {
      this.companiesCat = [];
    } else {
      this.companiesService.getCompaniesByLineaAndGiro('A', Number(giroId))
        .subscribe(companies => this.companiesCat = companies,
          (error: HttpErrorResponse) =>
            this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
    }
  }

  onCompanySelected(companyId: string) {
    this.companyInfo = this.companiesCat.find(c => c.id === Number(companyId));
    // TODO Mover todo esta logica a un servicio de contrsuccion
    this.factura.rfcEmisor = this.companyInfo.informacionFiscal.rfc;
    this.factura.razonSocialEmisor = this.companyInfo.informacionFiscal.razonSocial.toUpperCase();
    this.factura.cfdi.emisor.regimenFiscal = this.companyInfo.regimenFiscal;
    this.factura.cfdi.emisor.rfc = this.companyInfo.informacionFiscal.rfc;
    this.factura.cfdi.emisor.nombre = this.companyInfo.informacionFiscal.razonSocial.toUpperCase();
    this.factura.cfdi.lugarExpedicion = this.companyInfo.informacionFiscal.cp;
    this.factura.cfdi.emisor.direccion = this.cfdiValidator.generateAddress(this.companyInfo.informacionFiscal);
  }

  buscarClientInfo(razonSocial: string) {
    if ( razonSocial !== undefined && razonSocial.length >= 5) {
      this.clientsService.getClients( { promotor: this.user.email, razonSocial: razonSocial, page: '0', size: '20' })
          .pipe(map((clientsPage: GenericPage<Client>) => clientsPage.content))
          .subscribe(clients => {
            this.clientsCat = clients;
            if ( clients.length > 0) {
              this.formInfo.clientRfc = clients[0].id.toString();
              this.onClientSelected(this.formInfo.clientRfc);
            } else {
              this.clientSearchMsg = `No se encuentran  clientes con nombre ${razonSocial}`;
            }
          }, (error: HttpErrorResponse) => {
            this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`);
            this.clientsCat = [];
            this.clientInfo = undefined;
          });
    }else {
      this.clientsCat = [];
      this.clientInfo = undefined;
      this.clientSearchMsg = '';
    }
  }

  onClientSelected(id: string) {
    const value = +id;
    this.clientSearchMsg = '';
    if (!isNaN(value)) {
      const client = this.clientsCat.find(c => c.id === Number(value));
      this.clientInfo = client.informacionFiscal;
      // mover esta logica a un servicio de construccion
      this.factura.rfcRemitente = this.clientInfo.rfc;
      this.factura.razonSocialRemitente = this.clientInfo.razonSocial.toUpperCase();
      this.factura.cfdi.receptor.rfc = this.clientInfo.rfc;
      this.factura.cfdi.receptor.nombre = this.clientInfo.razonSocial.toUpperCase();
      this.factura.cfdi.receptor.direccion = this.cfdiValidator.generateAddress(this.clientInfo);
      if (!client.activo) {
        this.clientSearchMsg = `El cliente ${client.informacionFiscal.razonSocial} no se encuentra activo,notifique a operciones para activarlo`;
      }
    }
  }



  limpiarForma() {
    this.initVariables();
    this.clientInfo = undefined;
    this.companyInfo = undefined;
    this.successMessage = undefined;
    this.factura = new Factura();
    this.factura.cfdi = new Cfdi();
    this.factura.cfdi.conceptos = [];
    this.errorMessages = [];
  }

  isValidCfdi(): boolean {
    return this.cfdiValidator.validarCfdi(this.factura.cfdi).length === 0;
  }

  solicitarCfdi() {
    this.loading = true;
    this.errorMessages = [];
    this.factura.solicitante = this.user.email;
    this.factura.lineaEmisor = 'A';
    this.factura.lineaRemitente = 'CLIENTE';
    this.factura.metodoPago = this.factura.cfdi.metodoPago;
    this.errorMessages = this.cfdiValidator.validarCfdi({ ...this.factura.cfdi });
    if (this.errorMessages.length === 0) {
      this.invoiceService.insertNewInvoice(this.factura)
        .subscribe((invoice: Factura) => {
          this.loading = false;
          this.getInvoiceInfoByIdCdfi(invoice.cfdi.id.toString());
          this.successMessage = 'Solicitud de factura enviada correctamente';
        }, (error: HttpErrorResponse) => {
          this.loading = false;
          this.errorMessages.push((error.error != null && error.error !== undefined) ?
            error.error.message : `${error.statusText} : ${error.message}`);
        });
    }else {
      this.loading = false;
    }
  }

  public revalidateInvoice() {
    this.factura.statusFactura = '1';
    this.factura.validacionOper = false;
    this.factura.validacionTeso = false;
    this.invoiceService.updateInvoice(this.factura)
    .subscribe((invoice: Factura) => {
      this.successMessage = 'Factura recuperada exitosamente';
      this.getInvoiceInfoByIdCdfi(this.preFolio);
    }, (error: HttpErrorResponse) => {
      this.loading = false;
      this.errorMessages.push((error.error != null && error.error !== undefined) ?
        error.error.message : `${error.statusText} : ${error.message}`);
    });
  }

  public downloadPdf(folio: string) {
    this.filesService.getFacturaFile(folio, 'PDF').subscribe(
      file => this.downloadService.downloadFile(file.data,
        `${this.factura.folio}-${this.factura.rfcEmisor}-${this.factura.rfcRemitente}.pdf`, 'application/pdf;')
    );
  }
  public downloadXml(folio: string) {
    this.filesService.getFacturaFile(folio, 'XML').subscribe(
      file => this.downloadService.downloadFile(file.data,
        `${this.factura.folio}-${this.factura.rfcEmisor}-${this.factura.rfcRemitente}.xml`, 'text/xml;charset=utf8;')
    );
  }
}
