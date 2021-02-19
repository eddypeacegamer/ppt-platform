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
import { PagoBase } from '../../../models/pago-base';
import { ActivatedRoute, Router } from '@angular/router';
import { Catalogo } from '../../../models/catalogos/catalogo';
import { map } from 'rxjs/operators';
import { DonwloadFileService } from '../../../@core/util-services/download-file-service';
import { UsersData } from '../../../@core/data/users-data';
import { FilesData } from '../../../@core/data/files-data';
import { CfdiValidatorService } from '../../../@core/util-services/cfdi-validator.service';
import { Pago } from '../../../models/factura/pago';
import { PaymentsData } from '../../../@core/data/payments-data';
import { ClientsData } from '../../../@core/data/clients-data';
import { GenericPage } from '../../../models/generic-page';
import { Client } from '../../../models/client';
import { Contribuyente } from '../../../models/contribuyente';
import { User } from '../../../models/user';
import { CfdiData } from '../../../@core/data/cfdi-data';


@Component({
  selector: 'ngx-pre-cfdi',
  templateUrl: './pre-cfdi.component.html',
  styleUrls: ['./pre-cfdi.component.scss']
})
export class PreCfdiComponent implements OnInit {
  public girosCat: Catalogo[] = [];
  public emisoresCat: Empresa[] = [];
  public receptoresCat: Empresa[] = [];
  public clientsCat: Client[] = [];
  public prodServCat: ClaveProductoServicio[] = [];
  public claveUnidadCat: ClaveUnidad[] = [];
  public usoCfdiCat: UsoCfdi[] = [];
  public validationCat: Catalogo[] = [];
  public payCat: Catalogo[] = [];
  public devolutionCat: Catalogo[] = [];
  public payTypeCat: Catalogo[] = [new Catalogo('01', 'Efectivo'), new Catalogo('02', 'Cheque nominativo'),
    new Catalogo('03', 'Transferencia electrónica de fondos'), new Catalogo('99', 'Por definir')];

  public complementPayTypeCat: Catalogo[] = [];
  public newConcep: Concepto;
  public payment: Pago;
  public factura: Factura;
  public folioParam: string;
  public user: User;
  public preFolio: string;

  public complementos: Factura[] = [];

  public successMessage: string;
  public errorMessages: string[] = [];
  public conceptoMessages: string[] = [];
  public payErrorMessages: string[] = [];

  public formInfo = { emisorRfc: '*', receptorRfc: '*', giroReceptor: '*', giroEmisor: '*',
    lineaEmisor: 'B', lineaReceptor: 'A', usoCfdi: '*', payType: '*',
    clientRfc: '*',clientName: '', companyRfc: '', giro: '*', empresa: '*'  };

  public clientInfo: Contribuyente;
  public companyInfo: Empresa;

  public loading: boolean = false;

  /** PAYMENT SECCTION**/

  public paymentForm = { coin: '*', payType: '*', bank: '*', filename: '', successPayment: false};
  public newPayment: PagoBase;
  public invoicePayments = [];
  public paymentSum: number = 0;

  //
  public pagosCfdi: Pago[] = [];

  public companiesCat: Empresa[] = [];

  constructor(private dialogService: NbDialogService,
    private catalogsService: CatalogsData,
    private clientsService: ClientsData,
    private companiesService: CompaniesData,
    private invoiceService: InvoicesData,
    private filesService: FilesData,
    private cfdiService: CfdiData,
    private userService: UsersData,
    private cfdiValidator: CfdiValidatorService,
    private paymentsService: PaymentsData,

    private downloadService: DonwloadFileService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {

    this.userService.getUserInfo().then(user => this.user = user as User);
    this.initVariables();
    this.paymentsService.getFormasPago().subscribe(payTypes => this.complementPayTypeCat = payTypes);
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
          this.preFolio = route.get('folio');
          if (this.preFolio !== '*') {
            this.getInvoiceByIdCdfi(this.preFolio);
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
    this.successMessage = undefined;
    this.loading = false;
    this.factura.cfdi.moneda = 'MXN';
    this.factura.cfdi.metodoPago = '*';
    this.payment = new Pago();
    this.payment.formaPago = '*';
    this.factura.cfdi.formaPago = '*';
    this.factura.cfdi.receptor.usoCfdi = '*';
  }

  public getInvoiceByIdCdfi(preFolio: string) {
    const idCfdi: number = +preFolio;
    this.preFolio = preFolio;
    this.pagosCfdi = [];
    this.cfdiService.getFacturaInfo(idCfdi).pipe(
      map((fac: Factura) => {

        fac.statusFactura = this.validationCat.find(v => v.id === fac.statusFactura).nombre;
        return fac;
      })).subscribe(invoice => {
        this.factura = invoice;
        console.log(this.factura.tipoDocumento);
        console.log("sta  "+this.factura.lineaEmisor);
        this.cfdiService.getCfdiByFolio(idCfdi)
        .subscribe(cfdi => {
        this.factura.cfdi = cfdi;
        if (invoice.cfdi.metodoPago === 'PPD') {
        /*  this.loadConceptos(); */
         this.pagosCfdi = cfdi.complemento.pagos; 
        }
      });
      if (invoice.metodoPago === 'PPD' && invoice.tipoDocumento === 'Factura') {
        this.cfdiService.findPagosPPD(idCfdi)
          .subscribe(pagos => {this.pagosCfdi = pagos; });
      }      
    
      setTimeout(() => { this.loading = false; }, 1800);
   
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
    this.factura.rfcEmisor = this.companyInfo.informacionFiscal.rfc;
    this.factura.razonSocialEmisor = this.companyInfo.informacionFiscal.razonSocial.toUpperCase();
    this.factura.cfdi.emisor.regimenFiscal = this.companyInfo.regimenFiscal;
    this.factura.cfdi.emisor.rfc = this.companyInfo.informacionFiscal.rfc;
    this.factura.cfdi.emisor.nombre = this.companyInfo.informacionFiscal.razonSocial.toUpperCase();
    this.factura.cfdi.lugarExpedicion = this.companyInfo.informacionFiscal.cp;
    this.factura.cfdi.emisor.direccion = this.cfdiValidator.generateAddress(this.companyInfo.informacionFiscal);
  }

  onReceptorSelected(companyId: string) {
    this.clientInfo = this.receptoresCat.find(c => c.id === Number(companyId)).informacionFiscal;
    this.factura.rfcRemitente = this.clientInfo.rfc;
    this.factura.razonSocialRemitente = this.clientInfo.razonSocial.toUpperCase();
    this.factura.cfdi.receptor.rfc = this.clientInfo.rfc;
    this.factura.cfdi.receptor.nombre = this.clientInfo.razonSocial.toUpperCase();
    this.factura.cfdi.receptor.direccion = this.cfdiValidator.generateAddress(this.clientInfo);
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
    if (this.clientInfo === undefined || this.clientInfo.rfc === undefined) {
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

      this.factura.rfcRemitente = this.clientInfo.rfc;
      this.factura.razonSocialRemitente = this.clientInfo.razonSocial;
      this.factura.cfdi.receptor.rfc = this.clientInfo.rfc;
      this.factura.cfdi.receptor.nombre = this.clientInfo.razonSocial;
      this.factura.cfdi.emisor.direccion = this.cfdiValidator.generateAddress(this.companyInfo.informacionFiscal);
      this.factura.cfdi.receptor.direccion = this.cfdiValidator.generateAddress(this.clientInfo);

      this.factura.lineaEmisor = this.formInfo.lineaEmisor || 'B';
      this.factura.lineaRemitente = this.formInfo.lineaReceptor || 'A';
      this.factura.metodoPago = this.factura.cfdi.metodoPago;
      this.factura.statusFactura = '8'; // sets automatically to stamp directly
      this.errorMessages = this.cfdiValidator.validarCfdi({ ...this.factura.cfdi });
    }
    if (this.errorMessages.length === 0) {
      this.invoiceService.insertNewInvoice(this.factura)
        .subscribe((invoice: Factura) => {
          this.factura.folio = invoice.folio;
          this.getInvoiceByIdCdfi(invoice.cfdi.id.toString());
          this.loading = false;
        }, (error: HttpErrorResponse) => {
          this.loading = false;
          this.errorMessages.push((error.error != null && error.error !== undefined) ?
            error.error.message : `${error.statusText} : ${error.message}`);
        });
    } else {
      this.loading = false;
    }
  }

  public goToRelacionado(idCfdi:number){
    this.router.navigate([`./pages/operaciones/revision/${idCfdi}`]);
  }

  public linkInvoice(factura: Factura){
    this.loading = true;
    this.errorMessages = [];
    this.successMessage = undefined;
    const fact = { ...factura };
    fact.cfdi = null;
    fact.statusFactura = this.validationCat.find(v => v.nombre === fact.statusFactura).id;

    this.invoiceService.generateReplacement(factura.folio, fact).subscribe(result =>{
      this.successMessage = 'El documento relacionado se ha generado exitosamente';
      this.getInvoiceByIdCdfi(this.preFolio);
      this.loading = false;
    },(error: HttpErrorResponse) => {
      this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`);
      this.loading = false;
    });
  }

  public rechazarFactura() {
    this.loading = true;
    this.successMessage = undefined;
    this.errorMessages = [];
    let fact = { ...this.factura };
    fact.statusFactura = '9';// update to recahzo contabilidad
    this.invoiceService.updateInvoice(fact).subscribe(result => {
      this.getInvoiceByIdCdfi(this.preFolio);
      this.loading = false;
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
    console.log(fact)
    this.dialogService.open(dialog, { context: fact })
      .onClose.subscribe(invoice => {
        if (invoice !== undefined) {
          this.invoiceService.timbrarFactura(fact.folio, invoice)
            .subscribe(result => {
              this.loading = false;
              this.getInvoiceByIdCdfi(this.preFolio);
            },
              (error: HttpErrorResponse) => {
                this.loading = false;
                this.errorMessages.push((error.error != null && error.error !== undefined)
                  ? error.error.message : `${error.statusText} : ${error.message}`);
              });
        } else {
          this.loading = false;
        }
      });
  }

  public cancelarFactura(factura: Factura) {
    this.loading = true;
    this.successMessage = undefined;
    this.errorMessages = [];
    let fact = { ...factura };
    fact.cfdi = null;
    fact.statusFactura = this.validationCat.find(v => v.nombre === fact.statusFactura).id;
    
    this.invoiceService.cancelarFactura(fact.folio, fact)
      .subscribe(success => {
      this.successMessage = 'Factura correctamente cancelada';
      this.getInvoiceByIdCdfi(this.preFolio);
        this.loading = false;
      },(error: HttpErrorResponse) => {
          this.errorMessages.push((error.error != null && error.error != undefined) ? error.error.message : `${error.statusText} : ${error.message}`);
          this.loading = false;
        });
  }

  generateComplement() {
    this.loading = true;
    this.errorMessages = [];
    if (this.payment.monto === undefined) {
      this.errorMessages.push('El monto del complemento es un valor requerido');
    }
    if (this.payment.monto <= 0) {
      this.errorMessages.push('El monto del complemento no puede ser igual a 0');
    }
    if (this.payment.monto + this.paymentSum > this.factura.cfdi.total) {
      this.errorMessages.push('El monto del complemento no puede ser superior al monto total de la factura');
    }
    if (this.payment.moneda !== this.factura.cfdi.moneda) {
      this.errorMessages.push('El monto del complemento no puede ser superior al monto total de la factura');
    }
    if (this.payment.formaPago === undefined) {
      this.errorMessages.push('La forma de pago es requerida');
    }
    if (this.payment.fechaPago === undefined || this.payment.fechaPago === null) {
      this.errorMessages.push('La fecha de pago es un valor requerido');
    }
    if (this.errorMessages.length === 0) {
        this.invoiceService.generateInvoiceComplement(this.factura.folio, this.payment)
        .subscribe(complement => {
          this.getInvoiceByIdCdfi(this.preFolio);
        /*   this.loadConceptos(); */
        }, ( error: HttpErrorResponse) => {
          this.errorMessages.push((error.error != null && error.error !== undefined)
            ? error.error.message : `${error.statusText} : ${error.message}`);
        /*   this.loadConceptos(); */
          this.loading = false;
        });
      }else {
        this.loading = false;
      }
  }

  /* private loadConceptos() {
    this.invoiceService.getInvoiceSaldo(this.preFolio).subscribe(a => this.payment.monto = a);
          this.invoiceService.getComplementosInvoice(this.preFolio)
          .pipe(
            map((facturas: Factura[]) => {
              return facturas.map(record => {
                record.statusFactura = this.validationCat.find(v => v.id === record.statusFactura).nombre;
                return record;
              });
            })).subscribe(complementos => {
            this.factura.complementos = complementos;
            this.calculatePaymentSum(complementos);
            this.loading = false;
          });
  } */

  calculatePaymentSum(complementos: Factura[]){
    if (complementos.length === 0) {
      this.paymentSum = 0;
    } else {
      this.paymentSum = complementos.map((c: Factura) => c.total).reduce((total, c) => total + c);
    }
  }



  isValidCfdi(): boolean {
    return this.cfdiValidator.validarCfdi(this.factura.cfdi).length === 0;
}

onGiroSelection(giroId: string) {
  const value = +giroId;
  if (isNaN(value)) {
    this.emisoresCat = [];
  } else {
    this.companiesService.getCompaniesByLineaAndGiro(this.formInfo.lineaEmisor, Number(giroId))
      .subscribe(companies => this.emisoresCat = companies,
        (error: HttpErrorResponse) =>
          this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
  }
  console.log("giro " +JSON.stringify(this.companiesCat));
}

onCompanySelected(companyId: string) {
  this.companyInfo = this.emisoresCat.find(c => c.id === Number(companyId));
  this.companyInfo = this.companiesCat.find(c => c.id === Number(companyId));
  // TODO Mover todo esta logica a un servicio de contrsuccion
  this.factura.rfcEmisor = this.companyInfo.informacionFiscal.rfc;
  this.factura.razonSocialEmisor = this.companyInfo.informacionFiscal.razonSocial.toUpperCase();
  this.factura.cfdi.emisor.regimenFiscal = this.companyInfo.regimenFiscal;
  this.factura.cfdi.emisor.rfc = this.companyInfo.informacionFiscal.rfc;
  this.factura.cfdi.emisor.nombre = this.companyInfo.informacionFiscal.razonSocial.toUpperCase();
  this.factura.cfdi.lugarExpedicion = this.companyInfo.informacionFiscal.cp;
  this.factura.cfdi.emisor.direccion = this.cfdiValidator.generateAddress(this.companyInfo.informacionFiscal);

  console.log("selected " +this.factura.cfdi.emisor.rfc);
}

buscarClientInfo( razonSocial: string) {
  if ( razonSocial !== undefined && razonSocial.length > 5) {
    this.clientsService.getClients({ razonSocial: razonSocial, page: '0', size: '20'  })
        .pipe(map((clientsPage: GenericPage<Client>) => clientsPage.content))
        .subscribe(clients => {
          this.clientsCat = clients;
          if ( clients.length > 0) {
            this.formInfo.clientRfc = clients[0].id.toString();
            this.onClientSelected(this.formInfo.clientRfc);
          }
        }, (error: HttpErrorResponse) => {
          this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`);
          this.clientsCat = [];
          this.clientInfo = undefined;
        });
  }else {
    this.clientsCat = [];
    this.clientInfo = undefined;
  }
}

onClientSelected(id: string) {
  const value = +id;
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
      this.errorMessages.push(`El cliente ${client.informacionFiscal.razonSocial} no se encuentra activo en el sistema.`);
      this.errorMessages.push('Notifique al departamento de operaciones,puede proceder a solicitar el pre-CFDI');
    }
  }
}

}