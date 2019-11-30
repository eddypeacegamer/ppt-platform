import { Component, OnInit, TemplateRef, OnDestroy } from '@angular/core';
import { NbDialogService } from '@nebular/theme';
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
import { Status } from '../../../models/catalogos/status';
import { map} from 'rxjs/operators';
import { DownloadInvoiceFilesService } from '../../../@core/back-services/download-invoice-files';
import { PaymentsData } from '../../../@core/data/payments-data';
import { UsersData } from '../../../@core/data/users-data';
import { FilesData } from '../../../@core/data/files-data';
import { of } from 'rxjs';

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
  public validationCat: Status[] = [];
  public payCat: Status[] = [];
  public devolutionCat: Status[] = [];
  public payTypeCat: Status[] = [new Status('01', 'Efectivo'), new Status('02', 'Cheque nominativo'), new Status('03', 'Transferencia electrónica de fondos'), new Status('99', 'Por definir')];

  public newConcep: Concepto;
  public factura: Factura;
  public folioParam: string;
  public userEmail : string;

  public complementos: Factura[] = [];
  public headers: string[] = ['Producto Servicio', 'Cantidad', 'Clave Unidad', 'Unidad', 'Descripcion', 'Valor Unitario', 'Impuesto', 'Importe'];
  public successMessage : string;
  public errorMessages: string[] = [];
  public conceptoMessages: string[] = [];
  public payErrorMessages: string[] = [];

  public formInfo = { clientRfc: '', companyRfc: '', claveProdServ: '', giro: '*', empresa: '*', usoCfdi: '*', moneda: '*', payMethod: '*', payType: '*', prodServ: '*', unidad: '*' }
  public clientInfo: Contribuyente;
  public companyInfo: Empresa;

  /** PAYMENT SECCTION**/

  public paymentForm = { coin: '*', payType: '*', bank: '*', filename: '', successPayment: false };
  public newPayment: Pago;
  public invoicePayments = [];

  constructor(private dialogService: NbDialogService,
    private catalogsService: CatalogsData,
    private clientsService: ClientsData,
    private companiesService: CompaniesData,
    private invoiceService: InvoicesData,
    private paymentsService : PaymentsData,
    private userService : UsersData,
    private filesService : FilesData,
    private downloadService: DownloadInvoiceFilesService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.userService.getUserInfo().subscribe(user => this.userEmail = user.email);
    this.initVariables();
    this.route.paramMap.subscribe(route=>{
      this.folioParam = route.get('folio');
      console.log(`recovering ${this.folioParam} information`);
      this.catalogsService.getInvoiceCatalogs()
      .toPromise().then(results=>{
        this.girosCat = results[0];
        this.claveUnidadCat = results[1];
        this.usoCfdiCat = results[2];
        this.payCat = results[3];
        this.devolutionCat = results[4];
        this.validationCat = results[5];
      }).then(()=>{
          if(this.folioParam!='*'){
            this.invoiceService.getComplementosInvoice(this.folioParam)
            .pipe(
              map((facturas:Factura[]) =>{
                return facturas.map(record=>{
                  record.statusFactura = this.validationCat.find(v=>v.id==record.statusFactura).value;
                  record.statusPago = this.payCat.find(v=>v.id==record.statusPago).value;
                  record.statusDevolucion = this.devolutionCat.find(v=>v.id==record.statusDevolucion).value;
                  record.formaPago = this.payTypeCat.find(v => v.id == record.formaPago).value;
                  return record;})
              }))
            .subscribe(complementos => this.complementos = complementos);
            this.paymentsService.getPaymentsByFolio(this.folioParam).subscribe(payments => this.invoicePayments = payments);
            this.invoiceService.getInvoiceByFolio(this.folioParam).pipe(
              map((fac: Factura) => {
                fac.cfdi.usoCfdi = this.usoCfdiCat.find(u => u.clave == fac.cfdi.usoCfdi).descripcion;
                fac.statusFactura = this.validationCat.find(v => v.id == fac.statusFactura).value;
                fac.statusPago = this.payCat.find(v => v.id == fac.statusPago).value;
                fac.statusDevolucion = this.devolutionCat.find(v => v.id == fac.statusDevolucion).value;
                fac.formaPago = this.payTypeCat.find(v => v.id == fac.formaPago).value;
                return fac;
              })).subscribe(invoice => this.factura = invoice, 
                error => {
                console.error('Info cant found, creating a new invoice:', error)
                this.initVariables();
              });
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
  }

  onDeleteConfirm(event): void {
    if (window.confirm('Estas seguro de borar el elemento?')) {
      event.confirm.resolve();
    } else {
      event.confirm.reject();
    }
  }

  openBuscadorSAT(dialog: TemplateRef<any>) {
    this.dialogService.open(dialog);
  }

  onGiroSelection(giroId: string) {
   let  value = +giroId;
   if(isNaN(value)){
    this.companiesCat = [];
   }else{
    this.companiesService.getCompaniesByLineaAndGiro('A', Number(giroId)).subscribe(companies => this.companiesCat = companies,
      (error: HttpErrorResponse) => this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
   }
  }

  onCompanySelected(companyId: number) {
    this.companyInfo = this.companiesCat.find(c => c.id == companyId);
  }

  buscarClaveProdServSelected() {
    this.catalogsService.getProductoServiciosByDescription(this.formInfo.claveProdServ).subscribe(claves => {
      this.prodServCat = claves;
      if (claves.length < 1) {
        alert(`No se encontro ninguna clave que coincida con ${this.formInfo.claveProdServ}`);
      }
    });
  }

  buscarClientInfo() {
    this.errorMessages = [];
    this.clientsService.getClientByRFC(this.formInfo.clientRfc).subscribe(
      (client: Client) => { this.clientInfo = client.informacionFiscal },
      (error: HttpErrorResponse) => this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
  }

  onUsoCfdiSelected(clave: string) {
    this.factura.cfdi.usoCfdi = clave;
  }

  onMonedaSelected(clave: string) {
    this.factura.cfdi.moneda = clave;
  }

  onMetodoDePagoSelected(clave: string) {
    this.factura.metodoPago = clave;
  }

  onFormaDePagoSelected(clave: string) {
    this.factura.formaPago = clave;
  }

  onClaveProdServSelected(clave: string) {
    this.newConcep.claveProdServ = clave;

  }

  onImpuestoSelected(clave: string) {
    if (clave === '002') {
      this.newConcep.impuestos = [new Impuesto(clave, '0.160000')]
    }
  }

  onSelectUnidad(clave: string) {
    if (clave != '*') {
      this.newConcep.claveUnidad = clave;
      this.newConcep.unidad = this.claveUnidadCat.find(u => u.clave == clave).nombre;
    }
  }

  limpiarForma() {
    this.formInfo = { clientRfc: '', companyRfc: '', claveProdServ: '', giro: '*', empresa: '*', usoCfdi: '*', moneda: '*', payMethod: '*', payType: '*', prodServ: '*', unidad: '*' }
    this.clientInfo = undefined;
    this.companyInfo = undefined;
    this.newConcep = new Concepto();
    this.factura = new Factura();
    this.factura.cfdi = new Cfdi();
    this.factura.cfdi.conceptos = [];
  }

  removeConcepto(index: number) {
    this.factura.cfdi.conceptos.splice(index, 1);
    this.calcularImportes();
  }

  agregarConcepto() {
    this.conceptoMessages = [];
    let validConcept = true;
    if (this.newConcep.cantidad < 1) {
      this.conceptoMessages.push('La cantidad requerida debe ser igual o mayor a 1');
      validConcept = false;
    }
    if (this.newConcep.claveProdServ == undefined) {
      this.conceptoMessages.push('La clave producto servicio del conepto es un valor requerido.');
      validConcept = false;
    }
    if (this.newConcep.claveUnidad == undefined) {
      this.conceptoMessages.push('La clave de unidad y la unidad son campos requeridos.');
      validConcept = false;
    }
    if (this.newConcep.descripcion == undefined) {
      this.conceptoMessages.push('La descripción del concepto es un valor requerido.');
      validConcept = false;
    }
    if(this.newConcep.valorUnitario<=0){
      this.conceptoMessages.push('El valor unitario del  concepto no puede ser menor igual a 0 pesos.');
      validConcept = false;
    }
  
    if (validConcept) {
      this.newConcep.importe = this.newConcep.cantidad * this.newConcep.valorUnitario;
      const base = this.newConcep.importe - this.newConcep.descuento;
      const impuesto = base * 0.16;
      
      if(this.newConcep.iva){this.newConcep.impuestos = [new Impuesto('002', '0.160000', base, impuesto)];}//IVA is harcoded
      this.factura.cfdi.conceptos.push({ ... this.newConcep });
      this.calcularImportes();
      if(this.factura.formaPago ==='01' && this.factura.total >2000){
        alert('Para pagos en efectivo el monto total de la factura no puede superar los 2000 MXN');
        this.factura.cfdi.conceptos.pop();
      }
      this.formInfo.prodServ = '*';
      this.formInfo.unidad = '*';
      this.newConcep = new Concepto();
    }
  }

  calcularImportes(){
    this.factura.total = 0;
    this.factura.subtotal = 0;
    for (const concepto of this.factura.cfdi.conceptos) {

      const base = concepto.importe - concepto.descuento;
      this.factura.subtotal += base;
      let impuesto = 0;
      for (const imp of concepto.impuestos) {
        impuesto = (imp.importe*3 + impuesto * 3) / 3;
      }
      this.factura.total += (base *3 + impuesto * 3)/3;
    }
  }

  getImporteImpuestos(impuestos: Impuesto[]) {
    if(impuestos.length>0){
      return impuestos.map(i => i.importe).reduce((total, value) => total + value);
    }else{
      return 0;
    }
  }

  solicitarCfdi() {
    this.errorMessages = [];
    let validCdfi = true;
    this.factura.solicitante = this.userEmail;
    if (this.companyInfo == undefined) {
      this.errorMessages.push('La empresa emisora es requerida.');
      validCdfi = false;
    } else {
      this.factura.rfcEmisor = this.companyInfo.informacionFiscal.rfc;
      this.factura.razonSocialEmisor = this.companyInfo.informacionFiscal.razonSocial;
      this.factura.cfdi.regimenFiscal = this.companyInfo.regimenFiscal;
      this.factura.rfcEmisor = this.companyInfo.informacionFiscal.rfc;
      this.factura.razonSocialEmisor = this.companyInfo.informacionFiscal.razonSocial;
    }

    if (this.clientInfo == undefined) {
      this.errorMessages.push('La información del cliente es un valor solicitado');
      validCdfi = false;
    } else {
      this.factura.rfcRemitente = this.clientInfo.rfc;
      this.factura.razonSocialRemitente = this.clientInfo.razonSocial;
    }

    if (this.factura.cfdi.usoCfdi == undefined) {
      this.errorMessages.push('El uso del CFDI es un campo requerido.');
      validCdfi = false;
    }
    if (this.factura.cfdi.moneda == undefined) {
      this.errorMessages.push('La moneda es un campo requerido.');
      validCdfi = false;
    }

    if (this.factura.formaPago == undefined) {
      this.errorMessages.push('La forma de pago es un campo requerido.');
      validCdfi = false;
    }

    if (this.factura.metodoPago == undefined) {
      this.errorMessages.push('El metodo de pago es un campo requerido.');
      validCdfi = false;
    }

    if (this.factura.cfdi.conceptos.length < 1) {
      this.errorMessages.push('La factura debe contener a menos 1 concepto a declarar.');
      validCdfi = false;
    }

    if (validCdfi) {
      this.factura.cfdi = this.factura.cfdi;
      this.invoiceService.insertNewInvoice(this.factura).subscribe(
        (invoice: Factura) => { this.router.navigate([`./pages/promotor/precfdi/${invoice.folio}`])},(error: HttpErrorResponse) => {this.errorMessages.push((error.error!=null &&error.error!=undefined)? error.error.message : `${error.statusText} : ${error.message}`)});
    }
  }


  public downloadPdf(folio:string){
    this.filesService.getFacturaFile(folio,'PDF').subscribe(
      file => this.downloadService.downloadFile(file.data,`${this.factura.folio}-${this.factura.rfcEmisor}-${this.factura.rfcRemitente}.pdf`,'application/pdf;')
    )
  }
  public downloadXml(folio:string){
    this.filesService.getFacturaFile(folio,'XML').subscribe(
      file => this.downloadService.downloadFile(file.data,`${this.factura.folio}-${this.factura.rfcEmisor}-${this.factura.rfcRemitente}.xml`,'text/xml;charset=utf8;')
    )
  }


  /******* PAGOS ********/


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
      if(file.size > 100000){
        alert('El archivo demasiado grande, intenta con un archivo mas pequeño.');
      }else{
      reader.readAsDataURL(file);
      reader.onload = () => { this.paymentForm.filename = file.name + " " + file.type; this.newPayment.documento = reader.result.toString() }
      reader.onerror = (error) => { this.payErrorMessages.push('Error parsing image file'); console.error(error) };
      }
    }
  }

  deletePayment(paymentId) {
    this.paymentsService.deletePayment(this.factura.folio, paymentId).subscribe(
      result => { this.paymentsService.getPaymentsByFolio(this.factura.folio).subscribe(payments => this.invoicePayments = payments); },
      (error: HttpErrorResponse) => this.payErrorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
  }

  sendPayment() {
    this.paymentForm.successPayment = false;
    this.newPayment.folioPadre = this.factura.folio;
    this.newPayment.folio = this.factura.folio;
    this.payErrorMessages = [];
    let validPayment = true;
    if (this.newPayment.banco == undefined) {
      validPayment = false;
      this.payErrorMessages.push('El banco es un valor requerido');
    }
    if (this.newPayment.fechaPago == undefined) {
      validPayment = false;
      this.payErrorMessages.push('La fecha de pago es un valor requerido');
    }
    if (this.newPayment.moneda == undefined) {
      validPayment = false;
      this.payErrorMessages.push('Es necesario especificar la moneda con la que se realizo el pago.');
    }
    if (this.newPayment.monto == undefined) {
      validPayment = false;
      this.payErrorMessages.push('El monto del pago es requerido.');
    }
    if (this.newPayment.monto <= 1) {
      validPayment = false;
      this.payErrorMessages.push('El monto pagado es invalido');
    }
    if (this.newPayment.formaPago == undefined) {
      validPayment = false;
      this.payErrorMessages.push('El tipo de pago es requerido.');
    }
    if (this.newPayment.formaPago != 'EFECTIVO' && this.newPayment.documento == undefined) {
      validPayment = false;
      this.payErrorMessages.push('La imagen del documento de pago es requerida.');
    }
    if (this.factura.metodoPago == 'PUE' && Math.abs(this.factura.total - this.newPayment.monto) > 0.01) {
      validPayment = false;
      this.payErrorMessages.push('Para pagos en una unica exibicion, el monto del pago debe coincidir con el monto total de la factura.');
    }

    if (this.invoicePayments.length > 0 && this.invoicePayments.reduce((total, p) => (total) + p.monto) + this.newPayment.monto > this.factura.total) {
      validPayment = false;
      this.payErrorMessages.push('El pago actual o la suma de los pagos no puede ser superior al monto total de la factura.');
    }

    if (validPayment) {
      this.newPayment.tipoPago = 'INGRESO';
      this.newPayment.ultimoUsuario = this.userEmail;
      this.paymentsService.insertNewPayment(this.factura.folio, this.newPayment).subscribe(
        result => {
          this.paymentForm.successPayment = true; this.newPayment = new Pago();
          this.paymentsService.getPaymentsByFolio(this.factura.folio).subscribe(payments => this.invoicePayments = payments);
          this.invoiceService.getComplementosInvoice(this.factura.folio).subscribe(complementos => this.complementos = complementos);
        },
        (error: HttpErrorResponse) => this.payErrorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
    }
  }

}
