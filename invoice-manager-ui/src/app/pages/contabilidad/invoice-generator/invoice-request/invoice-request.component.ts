import { Component, OnInit, Input, TemplateRef } from '@angular/core';
import { NbDialogRef } from '@nebular/theme';
import { Transferencia } from '../../../../models/transferencia';
import { Giro } from '../../../../models/catalogos/giro';
import { Empresa } from '../../../../models/empresa';
import { ClaveProductoServicio } from '../../../../models/catalogos/producto-servicio';
import { ClaveUnidad } from '../../../../models/catalogos/clave-unidad';
import { UsoCfdi } from '../../../../models/catalogos/uso-cfdi';
import { Status } from '../../../../models/catalogos/status';
import { Concepto } from '../../../../models/factura/concepto';
import { Factura } from '../../../../models/factura/factura';
import { CatalogsData } from '../../../../@core/data/catalogs-data';
import { ClientsData } from '../../../../@core/data/clients-data';
import { CompaniesData } from '../../../../@core/data/companies-data';
import { InvoicesData } from '../../../../@core/data/invoices-data';
import { UsersData } from '../../../../@core/data/users-data';
import { HttpErrorResponse } from '@angular/common/http';
import { Impuesto } from '../../../../models/factura/impuesto';
import { Cfdi } from '../../../../models/factura/cfdi';
import { map } from 'rxjs/operators';

@Component({
  selector: 'ngx-invoice-request',
  templateUrl: './invoice-request.component.html',
  styleUrls: ['./invoice-request.component.scss']
})
export class InvoiceRequestComponent implements OnInit {

  @Input() transfer:Transferencia;


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
  public userEmail: string;

  public complementos: Factura[] = [];
  public headers: string[] = ['Producto Servicio', 'Cantidad', 'Clave Unidad', 'Unidad', 'Descripcion', 'Valor Unitario', 'Impuesto', 'Importe'];
  public successMessage: string;
  public errorMessages: string[] = [];
  public conceptoMessages: string[] = [];
  public payErrorMessages: string[] = [];

  public formInfo = { clientRfc: '', companyRfc: '', claveProdServ: '', giro: '*', empresa: '*', usoCfdi: '*', moneda: '*', payMethod: '*', payType: '*', prodServ: '*', unidad: '*' }

  public loading : boolean = false;

  /** PAYMENT SECCTION**/


  constructor(protected ref: NbDialogRef<InvoiceRequestComponent>,
    private catalogsService: CatalogsData,
    private companiesService: CompaniesData,
    private invoiceService: InvoicesData,
    private userService: UsersData,
    ) {}


    ngOnInit() {
      
      this.userService.getUserInfo().subscribe(user => this.userEmail = user.email);
      this.initVariables();
        this.catalogsService.getInvoiceCatalogs()
        .subscribe(results=>{
          this.girosCat = results[0];
          this.claveUnidadCat = results[1];
          this.usoCfdiCat = results[2];
          this.payCat = results[3];
          this.devolutionCat = results[4];
          this.validationCat = results[5];
        })
      this.companiesService.getCompanyByRFC(this.transfer.rfcRetiro).subscribe(
        emisor => {this.factura.rfcEmisor = emisor.informacionFiscal.rfc;
          this.factura.razonSocialEmisor = emisor.informacionFiscal.razonSocial;
          this.factura.cfdi.regimenFiscal = emisor.regimenFiscal;},
        (error: HttpErrorResponse) => this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`)
      );

      this.companiesService.getCompanyByRFC(this.transfer.rfcDeposito).subscribe(
        receptor => {this.factura.rfcRemitente = receptor.informacionFiscal.rfc;
          this.factura.razonSocialRemitente = receptor.informacionFiscal.razonSocial;},
        (error: HttpErrorResponse) => this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`)
      );
    }
  
    ngOnDestroy() {
      /** CLEAN VARIABLES **/
      this.newConcep = new Concepto();
      this.factura = new Factura();
    }
  
    public getInvoiceByFolio(folio:string){
      this.invoiceService.getInvoiceByFolio(folio).pipe(
        map((fac: Factura) => {
          fac.cfdi.usoCfdi = this.usoCfdiCat.find(u => u.clave == fac.cfdi.usoCfdi).descripcion;
          fac.statusFactura = this.validationCat.find(v => v.id == fac.statusFactura).value;
          fac.statusPago = this.payCat.find(v => v.id == fac.statusPago).value;
          fac.statusDevolucion = this.devolutionCat.find(v => v.id == fac.statusDevolucion).value;
          fac.formaPago = this.payTypeCat.find(v => v.id == fac.formaPago).value;
          return fac;
        }))
        .subscribe(invoice => {this.factura = invoice;
          if(invoice.metodoPago == 'PPD'){
            this.invoiceService.getComplementosInvoice(folio)
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
          }
          },
          error => {
            console.error('Info cant found, creating a new invoice:', error)
            this.initVariables();
          });
    }
  
    public initVariables() {
      /** INIT VARIABLES **/
      this.newConcep = new Concepto();
      this.factura = new Factura();
      this.errorMessages = [];
      this.loading = false;
      this.factura.cfdi.moneda = 'MXN';
    }
  
    onDeleteConfirm(event): void {
      if (window.confirm('Estas seguro de borar el elemento?')) {
        event.confirm.resolve();
      } else {
        event.confirm.reject();
      }
    }
  
  
  
    onGiroSelection(giroId: string) {
      let value = +giroId;
      if (isNaN(value)) {
        this.companiesCat = [];
      } else {
        this.companiesService.getCompaniesByLineaAndGiro('A', Number(giroId)).subscribe(companies => this.companiesCat = companies,
          (error: HttpErrorResponse) => this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
      }
    }
  
    buscarClaveProdServSelected() {
      this.catalogsService.getProductoServiciosByDescription(this.formInfo.claveProdServ).subscribe(claves => {
        this.prodServCat = claves;
        if (claves.length < 1) {
          alert(`No se encontro ninguna clave que coincida con ${this.formInfo.claveProdServ}`);
        }
      });
    }
  
    onUsoCfdiSelected(clave: string) {
      this.factura.cfdi.usoCfdi = clave;
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
      this.formInfo = { clientRfc: '', companyRfc: '', claveProdServ: '', giro: '*', empresa: '*', usoCfdi: '*', moneda: '*', payMethod: '*', payType: '*', prodServ: '*', unidad: '*' };
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
        console.log('impuesto',impuesto);
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

    calcularPrecioUnitario(concepto : Concepto){
      if(concepto.cantidad < 1){
        alert('NO es posible calcular montos unitarios para valores menores a 1');
      }else{
        const restante  = this.transfer.importe - this.factura.total;
        if(concepto.iva == true){
          concepto.valorUnitario = restante/(1.16 * concepto.cantidad)
        }else{
          concepto.valorUnitario = restante / concepto.cantidad;
        }
      }
    }

   validarRestante():boolean{
     return Math.abs(this.transfer.importe - this.factura.total)<0.01
   }
  
    solicitarCfdi() {
      this.errorMessages = [];
      this.successMessage = undefined;
      let validCdfi = true;
      this.factura.lineaEmisor = this.transfer.lineaRetiro;
      this.factura.lineaRemitente = this.transfer.lineaDeposito;
      this.factura.solicitante = this.userEmail;
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
          (invoice: Factura) => {
            this.factura.folio = invoice.folio; 
            this.successMessage = `La solicitud del CFDI ha sido generada correctamente con el folio ${invoice.folio}`;
          }, (error: HttpErrorResponse) => { this.errorMessages.push((error.error != null && error.error != undefined) ? error.error.message : `${error.statusText} : ${error.message}`) });
      }
    }
}
