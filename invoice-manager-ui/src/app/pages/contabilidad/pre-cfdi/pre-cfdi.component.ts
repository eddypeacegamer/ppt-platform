import { Component, OnInit, TemplateRef } from '@angular/core';
import { NbDialogService } from '@nebular/theme';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { CompaniesData } from '../../../@core/data/companies-data';
import { HttpErrorResponse } from '@angular/common/http';
import { Empresa } from '../../../models/empresa';
import { ClaveProductoServicio } from '../../../models/catalogos/producto-servicio';
import { Concepto } from '../../../models/factura/concepto';
import { ClaveUnidad } from '../../../models/catalogos/clave-unidad';
import { Cfdi } from '../../../models/factura/cfdi';
import { UsoCfdi } from '../../../models/catalogos/uso-cfdi';
import { Factura } from '../../../models/factura/factura';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { PagoFactura } from '../../../models/pago-factura';
import { ActivatedRoute } from '@angular/router';
import { Catalogo } from '../../../models/catalogos/catalogo';
import { map } from 'rxjs/operators';
import { DonwloadFileService } from '../../../@core/util-services/download-file-service';
import { UsersData, User } from '../../../@core/data/users-data';
import { FilesData } from '../../../@core/data/files-data';
import { CfdiValidatorService } from '../../../@core/util-services/cfdi-validator.service';

@Component({
  selector: 'ngx-pre-cfdi',
  templateUrl: './pre-cfdi.component.html',
  styleUrls: ['./pre-cfdi.component.scss']
})
export class PreCfdiComponent implements OnInit {
  public girosCat: Catalogo[] = [];
  public emisoresCat: Empresa[] = [];
  public receptoresCat: Empresa[] = [];
  public prodServCat: ClaveProductoServicio[] = [];
  public claveUnidadCat: ClaveUnidad[] = [];
  public usoCfdiCat: UsoCfdi[] = [];
  public validationCat: Catalogo[] = [];
  public payCat: Catalogo[] = [];
  public devolutionCat: Catalogo[] = [];
  public payTypeCat: Catalogo[] = [new Catalogo('01', 'Efectivo'), new Catalogo('02', 'Cheque nominativo'), new Catalogo('03', 'Transferencia electrónica de fondos'), new Catalogo('99', 'Por definir')];

  public newConcep: Concepto;
  public factura: Factura;
  public folioParam: string;
  public user: User;

  public complementos: Factura[] = [];

  public successMessage: string;
  public errorMessages: string[] = [];
  public conceptoMessages: string[] = [];
  public payErrorMessages: string[] = [];

  public formInfo = { emisorRfc: '*', receptorRfc: '*', giroReceptor: '*', giroEmisor: '*', lineaEmisor: 'B', lineaReceptor: 'A', usoCfdi: '*', payType: '*' };

  public clientInfo: Empresa;
  public companyInfo: Empresa;

  public loading: boolean = false;

  /** PAYMENT SECCTION**/

  public paymentForm = { coin: '*', payType: '*', bank: '*', filename: '', successPayment: false };
  public newPayment: PagoFactura;
  public invoicePayments = [];
  public paymentSum: number = 0;

  constructor(private dialogService: NbDialogService,
    private catalogsService: CatalogsData,
    private companiesService: CompaniesData,
    private invoiceService: InvoicesData,
    private filesService: FilesData,
    private userService: UsersData,
    private cfdiValidator: CfdiValidatorService,
    private downloadService: DonwloadFileService,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.userService.getUserInfo().then(user => this.user = user as User);
    this.initVariables();
    /* preloaded cats*/
    this.catalogsService.getStatusPago().then(cat => this.payCat = cat);
    this.catalogsService.getStatusDevolucion().then(cat => this.devolutionCat = cat);
    this.catalogsService.getStatusValidacion().then(cat => this.validationCat = cat);
    this.catalogsService.getFormasPago().then(cat => this.payTypeCat = cat);
    /* not pre-loaded cats*/
    this.catalogsService.getAllUsoCfdis().then(cat => this.usoCfdiCat = cat);
    this.catalogsService.getAllGiros().then(cat => this.girosCat = cat)
      .then(() => {
        this.route.paramMap.subscribe(route => {
          this.folioParam = route.get('folio');
          if (this.folioParam !== '*') {
            this.getInvoiceByFolio(this.folioParam);
          } else {
            this.initVariables();
          }
        });
      });
  }

  ngOnDestroy() {
    /** CLEAN VARIABLES **/
    this.newConcep = new Concepto();
    this.factura = new Factura();
  }

  public initVariables() {
    /** INIT VARIABLES **/
    this.newConcep = new Concepto();
    this.factura = new Factura();
    this.errorMessages = [];
    this.loading = false;
    this.factura.cfdi.moneda = 'MXN';
    this.factura.cfdi.metodoPago = '*';
  }

  public getInvoiceByFolio(folio: string) {
    this.invoiceService.getInvoiceByFolio(folio).pipe(
      map((fac: Factura) => {
        fac.cfdi.receptor.usoCfdi = this.usoCfdiCat.find(u => u.clave === fac.cfdi.receptor.usoCfdi).descripcion;
        fac.statusFactura = this.validationCat.find(v => v.id === fac.statusFactura).nombre;
        fac.statusPago = this.payCat.find(v => v.id === fac.statusPago).nombre;
        fac.statusDevolucion = this.devolutionCat.find(v => v.id === fac.statusDevolucion).nombre;
        fac.cfdi.formaPago = this.payTypeCat.find(v => v.id === fac.cfdi.formaPago).nombre;
        return fac;
      })).subscribe(invoice => {
        this.factura = invoice;
        if (invoice.cfdi.metodoPago === 'PPD') {
          this.invoiceService.getComplementosInvoice(folio)
            .pipe(
              map((facturas: Factura[]) => {
                return facturas.map(record => {
                  record.statusFactura = this.validationCat.find(v => v.id === record.statusFactura).nombre;
                  record.statusPago = this.payCat.find(v => v.id === record.statusPago).nombre;
                  record.statusDevolucion = this.devolutionCat.find(v => v.id === record.statusDevolucion).nombre;
                  record.cfdi.formaPago = this.payTypeCat.find(v => v.id === record.cfdi.formaPago).nombre;
                  return record;
                })
              }))
            .subscribe(complementos => this.factura.complementos = complementos);
        }
      },
        error => {
          console.error('Info cant found, creating a new invoice:', error);
          this.initVariables();
        });
  }

  onGiroEmisorSelection(giroId: string) {
    const value = +giroId;
    if (isNaN(value)) {
      this.emisoresCat = [];
    } else {
      this.companiesService.getCompaniesByLineaAndGiro(this.formInfo.lineaEmisor, Number(giroId))
        .subscribe(companies => this.emisoresCat = companies,
          (error: HttpErrorResponse) =>
            this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
    }
  }

  onGiroReceptorSelection(giroId: string) {
    const value = +giroId;
    if (isNaN(value)) {
      this.receptoresCat = [];
    } else {
      this.companiesService.getCompaniesByLineaAndGiro(this.formInfo.lineaReceptor, Number(giroId))
        .subscribe(companies => this.receptoresCat = companies,
          (error: HttpErrorResponse) =>
            this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
    }
  }

  onEmnisorSelected(companyId: string) {
    this.companyInfo = this.emisoresCat.find(c => c.id === Number(companyId));
  }

  onReceptorSelected(companyId: string) {
    this.clientInfo = this.receptoresCat.find(c => c.id === Number(companyId));
  }

  onPayMethodSelected(clave: string) {
    if (clave === 'PPD') {
      this.payTypeCat = [new Catalogo('99', 'Por definir')];
      this.factura.cfdi.formaPago = '99';
      this.factura.cfdi.metodoPago = 'PPD';
      this.factura.metodoPago = 'PPD';
      this.formInfo.payType = '99';
    } else {
      this.payTypeCat = [new Catalogo('02', 'Cheque nominativo'),
      new Catalogo('03', 'Transferencia electrónica de fondos')];
      this.factura.metodoPago = 'PUE';
      this.factura.cfdi.metodoPago = 'PUE';
      this.factura.cfdi.formaPago = '03';
      this.formInfo.payType = '03';
    }
  }

  onUsoCfdiSelected(clave: string) {
    this.factura.cfdi.receptor.usoCfdi = clave;
  }

  onFormaDePagoSelected(clave: string) {
    this.factura.cfdi.formaPago = clave;
  }

  limpiarForma() {
    this.initVariables();
    this.clientInfo = undefined;
    this.companyInfo = undefined;
    this.successMessage = undefined;
    this.newConcep = new Concepto();
    this.factura = new Factura();
    this.factura.cfdi = new Cfdi();
    this.factura.cfdi.conceptos = [];
    this.errorMessages = [];
  }

  public downloadPdf(folio: string) {
    this.filesService.getFacturaFile(folio, 'PDF').subscribe(
      file => this.downloadService.downloadFile(file.data, `${this.factura.folio}-${this.factura.rfcEmisor}-${this.factura.rfcRemitente}.pdf`, 'application/pdf;')
    )
  }
  public downloadXml(folio: string) {
    this.filesService.getFacturaFile(folio, 'XML').subscribe(
      file => this.downloadService.downloadFile(file.data, `${this.factura.folio}-${this.factura.rfcEmisor}-${this.factura.rfcRemitente}.xml`, 'text/xml;charset=utf8;')
    )
  }

  public solicitarCfdi() {
    this.loading = true;
    this.errorMessages = [];
    this.factura.solicitante = this.user.email;
    if (this.clientInfo === undefined || this.clientInfo.informacionFiscal.rfc === undefined) {
      this.errorMessages.push('La informacion del cliente es insuficiente o no esta presente.');
    } else if (this.companyInfo === undefined || this.companyInfo.informacionFiscal === undefined) {
      this.errorMessages.push('La informacion de la empresa es insuficiente o no esta presente.');
    } else {
      this.factura.rfcEmisor = this.companyInfo.informacionFiscal.rfc;
      this.factura.razonSocialEmisor = this.companyInfo.informacionFiscal.razonSocial;
      this.factura.cfdi.emisor.regimenFiscal = this.companyInfo.regimenFiscal;
      this.factura.cfdi.emisor.rfc = this.companyInfo.informacionFiscal.rfc;
      this.factura.cfdi.emisor.nombre = this.companyInfo.informacionFiscal.razonSocial;
      this.factura.rfcEmisor = this.companyInfo.informacionFiscal.rfc;
      this.factura.razonSocialEmisor = this.companyInfo.informacionFiscal.razonSocial;
      this.factura.rfcRemitente = this.clientInfo.informacionFiscal.rfc;
      this.factura.razonSocialRemitente = this.clientInfo.informacionFiscal.razonSocial;
      this.factura.cfdi.receptor.rfc = this.clientInfo.informacionFiscal.rfc;
      this.factura.cfdi.receptor.nombre = this.clientInfo.informacionFiscal.razonSocial;

      this.factura.cfdi.emisor.direccion = `${this.companyInfo.informacionFiscal.calle} 
        ${this.companyInfo.informacionFiscal.noExterior}, ${this.companyInfo.informacionFiscal.noInterior},
        ${this.companyInfo.informacionFiscal.localidad} , ${this.companyInfo.informacionFiscal.municipio}, 
        ${this.companyInfo.informacionFiscal.estado}, C.P. ${this.companyInfo.informacionFiscal.cp}`;
      this.factura.cfdi.receptor.direccion = `${this.clientInfo.informacionFiscal.calle} ${this.clientInfo.informacionFiscal.noExterior}, 
        ${this.clientInfo.informacionFiscal.noInterior}, ${this.clientInfo.informacionFiscal.localidad} , ${this.clientInfo.informacionFiscal.municipio}, 
        ${this.clientInfo.informacionFiscal.estado}, C.P. ${this.clientInfo.informacionFiscal.cp}`;

      this.factura.lineaEmisor = this.formInfo.lineaEmisor || 'B';
      this.factura.lineaRemitente = this.formInfo.lineaReceptor || 'A';
      this.factura.statusFactura = '8'; // sets automatically to stamp directly
      this.errorMessages = this.cfdiValidator.validarCfdi({ ...this.factura.cfdi });
    }
    if (this.errorMessages.length === 0) {
      this.invoiceService.insertNewInvoice(this.factura)
        .subscribe((invoice: Factura) => {
          this.factura.folio = invoice.folio;
          this.loading = false;
          this.successMessage = 'Solicitud de factura enviada correctamente';
        }, (error: HttpErrorResponse) => {
          this.loading = false;
          this.errorMessages.push((error.error != null && error.error !== undefined) ?
            error.error.message : `${error.statusText} : ${error.message}`);
        });
    } else {
      this.loading = false;
    }
  }

  public aceptarFactura() {
    this.loading = true;
    this.successMessage = undefined;
    this.errorMessages = [];
    const fact = { ...this.factura };
    if (fact.cfdi.metodoPago === 'PPD') {
      fact.statusFactura = '4'; // update to por timbrar
    } else {
      fact.statusFactura = '2'; // update to validacion tesoreria
    }
    fact.statusPago = this.payCat.find(v => v.nombre === fact.statusPago).id;
    fact.statusDevolucion = this.devolutionCat.find(v => v.nombre == fact.statusDevolucion).id;
    fact.cfdi.formaPago = this.payTypeCat.find(v => v.nombre == fact.cfdi.formaPago).id;
    this.invoiceService.updateInvoice(fact).subscribe(result => {
      this.loading = false;
      this.getInvoiceByFolio(fact.folioPadre || fact.folio);
    },
      (error: HttpErrorResponse) => {
        this.loading = false;
        this.errorMessages.push((error.error != null && error.error != undefined) ? error.error.message : `${error.statusText} : ${error.message}`);
      });
  }

  public rechazarFactura() {
    this.loading = true;
    this.successMessage = undefined;
    this.errorMessages = [];
    let fact = { ...this.factura };
    fact.statusFactura = '9';// update to recahzo operaciones
    fact.statusPago = this.payCat.find(v => v.nombre === fact.statusPago).id;
    fact.statusDevolucion = this.devolutionCat.find(v => v.nombre === fact.statusDevolucion).id;
    this.invoiceService.updateInvoice(fact).subscribe(result => {
      this.loading = false;
      this.getInvoiceByFolio(fact.folioPadre || fact.folio);
    },
      (error: HttpErrorResponse) => {
        this.loading = false;
        this.errorMessages.push((error.error != null && error.error !== undefined)
          ? error.error.message : `${error.statusText} : ${error.message}`);
      });
  }

  public timbrarFactura(factura: Factura, dialog: TemplateRef<any>) {
    this.loading = true;
    this.successMessage = undefined;
    this.errorMessages = [];
    const fact = { ...factura };
    fact.cfdi = null;
    fact.statusFactura = this.validationCat.find(v => v.nombre === fact.statusFactura).id;
    fact.statusPago = this.payCat.find(v => v.nombre === fact.statusPago).id;
    fact.statusDevolucion = this.devolutionCat.find(v => v.nombre === fact.statusDevolucion).id;

    this.dialogService.open(dialog, { context: fact })
      .onClose.subscribe(invoice => {
        if (invoice !== undefined) {
          this.invoiceService.timbrarFactura(fact.folio, invoice)
            .subscribe(result => {
              this.loading = false;
              this.getInvoiceByFolio(fact.folioPadre || fact.folio);
            },
              (error: HttpErrorResponse) => {
                this.loading = false;
                this.errorMessages.push((error.error != null && error.error !== undefined)
                  ? error.error.message : `${error.statusText} : ${error.message}`);
              });
        } else {
          this.loading = false;
        }
      }
      );
  }

  public cancelarFactura(factura: Factura) {
    this.loading = true;
    this.successMessage = undefined;
    this.errorMessages = [];
    let fact = { ...factura };
    fact.cfdi = null;
    console.log(fact);
    fact.statusFactura = this.validationCat.find(v => v.nombre === fact.statusFactura).id;
    fact.statusPago = this.payCat.find(v => v.nombre === fact.statusPago).id;
    fact.statusDevolucion = this.devolutionCat.find(v => v.nombre == fact.statusDevolucion).id;
    
    this.invoiceService.cancelarFactura(fact.folio, fact)
      .subscribe(success => {
      this.successMessage = 'Factura correctamente cancelada';
        this.getInvoiceByFolio(fact.folioPadre || fact.folio);
        this.loading = false;
      },
        (error: HttpErrorResponse) => {
          this.errorMessages.push((error.error != null && error.error != undefined) ? error.error.message : `${error.statusText} : ${error.message}`);
          this.loading = false;
          console.error(this.errorMessages);
        });
  }

}