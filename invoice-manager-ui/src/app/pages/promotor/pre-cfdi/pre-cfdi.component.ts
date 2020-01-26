import { Component, OnInit, TemplateRef, OnDestroy } from '@angular/core';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { ClientsData } from '../../../@core/data/clients-data';
import { CompaniesData } from '../../../@core/data/companies-data';
import { Giro } from '../../../models/catalogos/giro';
import { HttpErrorResponse } from '@angular/common/http';
import { Contribuyente } from '../../../models/contribuyente';
import { Empresa } from '../../../models/empresa';
import { ClaveProductoServicio } from '../../../models/catalogos/producto-servicio';
import { Concepto } from '../../../models/factura/concepto';
import { ClaveUnidad } from '../../../models/catalogos/clave-unidad';
import { Impuesto } from '../../../models/factura/impuesto';
import { Cfdi } from '../../../models/factura/cfdi';
import { Client } from '../../../models/client';
import { UsoCfdi } from '../../../models/catalogos/uso-cfdi';
import { Factura } from '../../../models/factura/factura';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { Pago } from '../../../models/pago';
import { ActivatedRoute, Router } from '@angular/router';
import { Catalogo } from '../../../models/catalogos/catalogo';
import { map } from 'rxjs/operators';
import { DownloadInvoiceFilesService } from '../../../@core/util-services/download-invoice-files';
import { PaymentsData } from '../../../@core/data/payments-data';
import { UsersData } from '../../../@core/data/users-data';
import { FilesData } from '../../../@core/data/files-data';
import { PdfMakeService } from '../../../@core/util-services/pdf-make.service';
import { NbDialogService } from '@nebular/theme';
import { CfdiValidatorService } from '../../../@core/util-services/cfdi-validator.service';

@Component({
  selector: 'ngx-pre-cfdi',
  templateUrl: './pre-cfdi.component.html',
  styleUrls: ['./pre-cfdi.component.scss']
})
export class PreCfdiComponent implements OnInit, OnDestroy {

  public girosCat: Giro[] = [];
  public companiesCat: Empresa[] = [];
  public prodServCat: ClaveProductoServicio[] = [];
  public claveUnidadCat: ClaveUnidad[] = [];
  public usoCfdiCat: UsoCfdi[] = [];
  public validationCat: Catalogo[] = [];
  public payCat: Catalogo[] = [];
  public devolutionCat: Catalogo[] = [];
  public payTypeCat: Catalogo[];

  public newConcep: Concepto;
  public factura: Factura;
  public folioParam: string;
  public userEmail: string;

  public complementos: Factura[] = [];
  public successMessage: string;
  public errorMessages: string[] = [];
  public conceptoMessages: string[] = [];
  public payErrorMessages: string[] = [];

  public formInfo = { clientRfc: '', companyRfc: '', claveProdServ: '', giro: '*', empresa: '*', usoCfdi: '*', payType: '*', prodServ: '*', unidad: '*' }
  public clientInfo: Contribuyente;
  public companyInfo: Empresa;
  public loading: boolean = false;

  /** PAYMENT SECCTION**/

  public paymentForm = { coin: 'MXN', payType: '*', bank: '*', filename: '', successPayment: false };
  public newPayment: Pago;
  public invoicePayments: Pago[] = [];
  public paymentSum: number = 0;


  constructor(
    private dialogService: NbDialogService,
    private catalogsService: CatalogsData,
    private clientsService: ClientsData,
    private companiesService: CompaniesData,
    private invoiceService: InvoicesData,
    private paymentsService: PaymentsData,
    private cfdiValidator: CfdiValidatorService,
    private userService: UsersData,
    private filesService: FilesData,
    private downloadService: DownloadInvoiceFilesService,
    private pdfMakeService: PdfMakeService,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.userService.getUserInfo().subscribe(user => this.userEmail = user.email);
    this.initVariables();
    this.route.paramMap.subscribe(route => {
      this.folioParam = route.get('folio');
      this.catalogsService.getInvoiceCatalogs()
        .toPromise().then(results => {
          this.girosCat = results[0];
          this.claveUnidadCat = results[1];
          this.usoCfdiCat = results[2];
          this.payCat = results[3];
          this.devolutionCat = results[4];
          this.validationCat = results[5];
          this.payTypeCat = results[6];
        }).then(() => {
          if (this.folioParam !== '*') {
            this.paymentsService.getPaymentsByFolio(this.folioParam)
              .subscribe(payments => { this.invoicePayments = payments; this.calculatePayments(); });
            this.getInvoiceByFolio(this.folioParam);
          }
        });
    });
  }

  ngOnDestroy() {
    /** CLEAN VARIABLES **/
    this.newPayment = new Pago();
    this.newConcep = new Concepto();
    this.factura = new Factura();
  }

  public initVariables() {
    /** INIT VARIABLES **/
    this.newPayment = new Pago();
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
        fac.cfdi.usoCfdi = this.usoCfdiCat.find(u => u.clave === fac.cfdi.usoCfdi).descripcion;
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
            .subscribe(complementos => this.complementos = complementos);
        }
      },
        error => {
          console.error('Info cant found, creating a new invoice:', error);
          this.initVariables();
        });
  }



  public buscarClaveProductoServicio(claveProdServ: string) {
    this.conceptoMessages = [];
    const value = +claveProdServ;
    if (isNaN(value)) {
      if (claveProdServ.length > 5) {
        this.catalogsService.getProductoServiciosByDescription(claveProdServ).subscribe(claves => {
          this.prodServCat = claves;
          this.formInfo.prodServ = claves[0].clave.toString();
          this.newConcep.claveProdServ = claves[0].clave.toString();
          this.newConcep.descripcionCUPS = claves[0].descripcion;
        }, (error: HttpErrorResponse) => {
          this.conceptoMessages = [];
          this.conceptoMessages.push(error.error.message || `${error.statusText} : ${error.message}`)
        });
      }
    } else {
      if (claveProdServ.length > 5) {
        this.catalogsService.getProductoServiciosByClave(claveProdServ).subscribe(claves => {
          this.prodServCat = claves;
          this.formInfo.prodServ = claves[0].clave.toString();
          this.newConcep.claveProdServ = claves[0].clave.toString();
          this.newConcep.descripcionCUPS = claves[0].descripcion;
        }, (error: HttpErrorResponse) => {
          this.conceptoMessages = [];
          this.conceptoMessages.push(error.error.message || `${error.statusText} : ${error.message}`);
          });
      }
    }
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
  }

  onPayMethodSelected(clave: string) {
    this.catalogsService.getFormasPago(clave)
    .subscribe(cat => {
      this.payTypeCat = cat;
      this.formInfo.payType = this.payTypeCat[0].id;
      if (clave === 'PPD') {
        this.factura.cfdi.formaPago = '99';
        this.factura.cfdi.metodoPago = 'PPD';
      } else {
        this.factura.cfdi.metodoPago = 'PUE';
        this.factura.cfdi.formaPago = '01';
      }
    });
  }

  buscarClientInfo() {
    this.errorMessages = [];
    this.clientsService.getClientByRFC(this.formInfo.clientRfc).subscribe(
      (client: Client) => {
        if (client.activo === true) {
          this.clientInfo = client.informacionFiscal;
        } else {
          alert(`El cliente ${client.informacionFiscal.razonSocial} no se encuentra activo en el sistema`);
          this.formInfo.clientRfc = '';
        }
      }, (error: HttpErrorResponse) => this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
  }

  openSatCatalog(dialog: TemplateRef<any>) {
    this.dialogService.open(dialog);
  }

  onUsoCfdiSelected(clave: string) {
    this.factura.cfdi.usoCfdi = clave;
  }

  onFormaDePagoSelected(clave: string) {
    this.factura.cfdi.formaPago = clave;
  }

  onClaveProdServSelected(clave: string) {
    this.newConcep.claveProdServ = clave;
    this.newConcep.descripcionCUPS = this.prodServCat.find(c => c.clave === clave).descripcion;
  }

  onImpuestoSelected(clave: string) {
    if (clave === '002') {
      this.newConcep.impuestos = [new Impuesto(clave, '0.160000')]
    }
  }

  onSelectUnidad(clave: string) {
    if (clave != '*') {
      this.newConcep.claveUnidad = clave;
      this.newConcep.unidad = this.claveUnidadCat.find(u => u.clave === clave).nombre;
    }
  }

  limpiarForma() {
    this.initVariables();
    this.clientInfo = undefined;
    this.companyInfo = undefined;
    this.conceptoMessages = [];
    this.successMessage = undefined;
    this.newConcep = new Concepto();
    this.factura = new Factura();
    this.factura.cfdi = new Cfdi();
    this.factura.cfdi.conceptos = [];
    this.errorMessages = [];
  }

  removeConcepto(index: number) {
    this.errorMessages = [];
    this.conceptoMessages = [];
    this.successMessage = undefined;
    if (this.factura.folio !== undefined) {
      this.invoiceService.deleteConcepto(this.factura.folio, this.factura.cfdi.conceptos[index].id)
      .subscribe((cfdi: Cfdi) => this.factura.cfdi = cfdi,
      (error: HttpErrorResponse) => { this.errorMessages.push((error.error != null && error.error !== undefined)
          ? error.error.message : `${error.statusText} : ${error.message}`)});
    }else {
      this.factura.cfdi.conceptos.splice(index, 1);
      this.factura.cfdi = this.cfdiValidator.calcularImportes(this.factura.cfdi);
    }
  }

  updateConcepto(concepto: Concepto) {
    this.newConcep = {... concepto};
    if (concepto.impuestos.length > 0 ) {
      this.newConcep.iva = true;
    }
    this.formInfo.unidad = concepto.claveUnidad;
    this.formInfo.claveProdServ = concepto.claveProdServ;
    this.buscarClaveProductoServicio(concepto.claveProdServ);
  }

  agregarConcepto(id?: number) {
    this.conceptoMessages = [];
    this.errorMessages = [];
    this.successMessage = undefined;
    const concepto = this.cfdiValidator.buildConcepto({... this.newConcep});
    this.conceptoMessages = this.cfdiValidator.validarConcepto(concepto);
    if (this.conceptoMessages.length === 0) {
      if (this.factura.folio !== undefined) {
        let promise;
        if (id === undefined) {
          promise = this.invoiceService.insertConcepto(this.factura.folio, concepto).toPromise();
        }else {
          promise = this.invoiceService.updateConcepto(this.factura.folio, id, concepto).toPromise();
        }
        promise.then((cfdi) => {
              this.formInfo.prodServ = '*';
              this.formInfo.unidad = '*';
              this.newConcep = new Concepto();
            }, (error: HttpErrorResponse) => {
              this.conceptoMessages.push((error.error != null && error.error !== undefined)
                ? error.error.message : `${error.statusText} : ${error.message}`);
            }).then(() => {
              this.invoiceService.getCfdiByFolio(this.factura.folio)
              .subscribe((cfdi: Cfdi) => this.factura.cfdi = cfdi,
              (error: HttpErrorResponse) => { this.errorMessages.push((error.error != null && error.error !== undefined) 
                  ? error.error.message : `${error.statusText} : ${error.message}`)});
            });
      }else {
        this.factura.cfdi.conceptos.push(concepto);
        this.factura.cfdi = this.cfdiValidator.calcularImportes(this.factura.cfdi);
        this.formInfo.prodServ = '*';
        this.formInfo.unidad = '*';
        this.newConcep = new Concepto();
      }
    }else {
      this.formInfo.prodServ = '*';
      this.formInfo.unidad = '*';
      this.newConcep = new Concepto();
    }
  }

  public getImporteImpuestos(impuestos: Impuesto[]): number {
    if (impuestos.length > 0) {
      return impuestos.map(i => i.importe).reduce((total, value) => total + value);
    } else {
      return 0;
    }
  }

  solicitarCfdi() {
    this.errorMessages = [];
    this.factura.solicitante = this.userEmail;
    this.factura.lineaEmisor = 'A';
    this.factura.lineaRemitente = 'CLIENTE';
    this.factura.rfcEmisor = this.companyInfo.informacionFiscal.rfc;
    this.factura.razonSocialEmisor = this.companyInfo.informacionFiscal.razonSocial;
    this.factura.cfdi.regimenFiscal = this.companyInfo.regimenFiscal;
    this.factura.rfcEmisor = this.companyInfo.informacionFiscal.rfc;
    this.factura.razonSocialEmisor = this.companyInfo.informacionFiscal.razonSocial;
    this.factura.cfdi.emisor = this.companyInfo.informacionFiscal.rfc;
    this.factura.rfcRemitente = this.clientInfo.rfc;
    this.factura.razonSocialRemitente = this.clientInfo.razonSocial;
    this.factura.cfdi.receptor = this.clientInfo.rfc;
    this.errorMessages = this.cfdiValidator.validarCfdi({...this.factura.cfdi});
    if (this.errorMessages.length < 1) {
      this.invoiceService.insertNewInvoice(this.factura)
      .subscribe((invoice: Factura) => {
        this.factura.folio = invoice.folio;
        this.successMessage = 'Solicitud de factura enviada correctamente';
      }, (error: HttpErrorResponse) => { this.errorMessages.push((error.error != null && error.error !== undefined) ?
        error.error.message : `${error.statusText} : ${error.message}`);
      });
    }
  }


  public downloadPdf(folio: string) {
    //console.log('calling pdfMakeService for :', folio)
    //this.pdfMakeService.generatePdf(this.factura);
    this.filesService.getFacturaFile(folio, 'PDF').subscribe(
      file => this.downloadService.downloadFile(file.data, `${this.factura.folio}-${this.factura.rfcEmisor}-${this.factura.rfcRemitente}.pdf`, 'application/pdf;')
    );
  }
  public downloadXml(folio: string) {
    this.filesService.getFacturaFile(folio, 'XML').subscribe(
      file => this.downloadService.downloadFile(file.data, `${this.factura.folio}-${this.factura.rfcEmisor}-${this.factura.rfcRemitente}.xml`, 'text/xml;charset=utf8;')
    )
  }


  /******* PAGOS ********/

  calculatePayments() {
    if (this.invoicePayments.length === 0) {
      this.paymentSum = 0;
    } else {
      const payments: Pago[] = this.invoicePayments.filter(p => p.formaPago !== 'CREDITO');
      if (payments.length === 0) {
        this.paymentSum = 0;
      } else {
        this.paymentSum = payments.map((p: Pago) => p.monto).reduce((total, p) => total + p);
      }
    }
  }

  onPaymentCoinSelected(clave: string) {
    this.newPayment.moneda = clave;
  }

  onPaymentTypeSelected(clave: string) {
    this.newPayment.formaPago = clave;
  }

  onPaymentBankSelected(clave: string) {
    this.newPayment.banco = clave;
  }

  fileUploadListener(event: any): void {
    let reader = new FileReader();
    if (event.target.files && event.target.files.length > 0) {
      let file = event.target.files[0];
      if (file.size > 100000) {
        alert('El archivo demasiado grande, intenta con un archivo mas pequeño.');
      } else {
        reader.readAsDataURL(file);
        reader.onload = () => { this.paymentForm.filename = file.name; this.newPayment.documento = reader.result.toString() }
        reader.onerror = (error) => { this.payErrorMessages.push('Error parsing image file'); console.error(error) };
      }
    }
  }

  deletePayment(paymentId) {
    this.paymentsService.deletePayment(this.factura.folio, paymentId).subscribe(
      result => {
        this.paymentsService.getPaymentsByFolio(this.factura.folio).subscribe(payments => { this.invoicePayments = payments; this.calculatePayments() });
        this.invoiceService.getComplementosInvoice(this.factura.folio).subscribe(complementos => this.complementos = complementos);
      }, (error: HttpErrorResponse) => this.payErrorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
  }

  sendPayment() {
    this.paymentForm.successPayment = false;
    this.newPayment.folioPadre = this.factura.folio;
    this.newPayment.folio = this.factura.folio;
    this.payErrorMessages = [];
    let validPayment = true;
    if (this.newPayment.banco === undefined) {
      validPayment = false;
      this.payErrorMessages.push('El banco es un valor requerido');
    }
    if (this.newPayment.fechaPago === undefined) {
      validPayment = false;
      this.payErrorMessages.push('La fecha de pago es un valor requerido');
    }
    if (this.newPayment.moneda === undefined) {
      validPayment = false;
      this.payErrorMessages.push('Es necesario especificar la moneda con la que se realizo el pago.');
    }
    if (this.newPayment.monto === undefined) {
      validPayment = false;
      this.payErrorMessages.push('El monto del pago es requerido.');
    }
    if (this.newPayment.monto <= 1) {
      validPayment = false;
      this.payErrorMessages.push('El monto pagado es invalido');
    }
    if (this.newPayment.formaPago === undefined) {
      validPayment = false;
      this.payErrorMessages.push('El tipo de pago es requerido.');
    }
    if (this.newPayment.formaPago != 'CREDITO' && this.newPayment.documento == undefined) {
      validPayment = false;
      this.payErrorMessages.push('La imagen del documento de pago es requerida.');
    }
    if (this.factura.cfdi.metodoPago === 'PUE' && Math.abs(this.factura.cfdi.total - this.newPayment.monto) > 0.01) {
      validPayment = false;
      this.payErrorMessages.push('Para pagos en una unica exibicion, el monto del pago debe coincidir con el monto total de la factura.');
    }

    if ((this.paymentSum + this.newPayment.monto - this.factura.cfdi.total) > 0.01) {
      validPayment = false;
      this.payErrorMessages.push('La suma de los pagos no puede ser superior al monto total de la factura.');
    }

    if (validPayment) {
      this.loading = true;
      this.newPayment.tipoPago = 'INGRESO';
      this.newPayment.ultimoUsuario = this.userEmail;
      const payment = { ... this.newPayment };

      this.paymentsService.insertNewPayment(this.factura.folio, payment).subscribe(
        result => {
          this.paymentForm.successPayment = true; this.newPayment = new Pago();
          this.paymentsService.getPaymentsByFolio(this.factura.folio).subscribe(payments => { this.invoicePayments = payments; this.calculatePayments(); this.loading = false; });
          this.invoiceService.getComplementosInvoice(this.factura.folio).subscribe(complementos => this.complementos = complementos);
        },
        (error: HttpErrorResponse) => { this.payErrorMessages.push(error.error.message || `${error.statusText} : ${error.message}`); this.loading = false; });
    }
    this.newPayment = new Pago();
    this.paymentForm = { coin: 'MXN', payType: '*', bank: '*', filename: '', successPayment: false };
  }

}
