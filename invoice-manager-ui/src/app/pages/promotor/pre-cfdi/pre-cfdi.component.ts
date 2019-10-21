import { Component, OnInit, TemplateRef } from '@angular/core';
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

@Component({
  selector: 'ngx-pre-cfdi',
  templateUrl: './pre-cfdi.component.html',
  styleUrls: ['./pre-cfdi.component.scss']
})
export class PreCfdiComponent implements OnInit {




  public girosCat: Giro[] = [];
  public companiesCat: Empresa[] = [];
  public prodServCat: ClaveProductoServicio[] = [];
  public claveUnidadCat: ClaveUnidad[] = [];
  public usoCfdiCat: UsoCfdi[] = [];

  public newConcep: Concepto;
  public factura: Factura;

  public headers: string[] = ['Producto Servicio', 'Cantidad', 'Clave Unidad', 'Unidad', 'Descripcion', 'Valor Unitario', 'Impuesto', 'Importe'];
  public errorMessages: string[] = [];
  public conceptoMessages : string[] = [];

  public formInfo = { clientRfc: '', companyRfc: '', claveProdServ: '', giro: '*', empresa: '*', usoCfdi: '*', moneda: '*', payMethod: '*', payType: '*', prodServ: '*', unidad: '*' }
  public clientInfo: Contribuyente;
  public companyInfo: Empresa;

  /** PAYMENT SECCTION**/
  public filename;


  constructor(private dialogService: NbDialogService,
    private catalogsService: CatalogsData,
    private clientsService: ClientsData,
    private companiesService: CompaniesData,
    private invoiceService: InvoicesData) { }

  ngOnInit() {

    /**** LOADING CAT INFO ****/
    this.catalogsService.getAllGiros().subscribe((giros: Giro[]) => this.girosCat = giros,
      (error: HttpErrorResponse) => this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
    this.catalogsService.getClaveUnidadByName('').subscribe((unidades: ClaveUnidad[]) => this.claveUnidadCat = unidades,
    (error: HttpErrorResponse) => this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
    this.catalogsService.getAllUsoCfdis().subscribe((usos: UsoCfdi[]) => this.usoCfdiCat = usos,
    (error: HttpErrorResponse) => this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
    /** INIT VARIABLES **/
    this.newConcep = new Concepto();
    this.factura = new Factura();
    this.factura.cfdi = new Cfdi();
    this.factura.cfdi.conceptos = [];

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

  onGiroSelection(giroId: any) {
    this.companiesService.getCompaniesByLineaAndGiro('A', Number(giroId)).subscribe(companies => this.companiesCat = companies,
      (error: HttpErrorResponse) => this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
  }

  onCompanySelected(companyId: number) {
    this.companyInfo = this.companiesCat.find(c => c.id == companyId);
  }

  buscarClaveProdServSelected() {
    this.catalogsService.getProductoServiciosByDescription(this.formInfo.claveProdServ).subscribe(claves => this.prodServCat = claves);
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
    this.factura.cfdi.metodoPago = clave;
  }

  onFormaDePagoSelected(clave: string) {
    this.factura.cfdi.formaPago = clave;
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

  limpiarForma(){
    this.formInfo = { clientRfc: '', companyRfc: '', claveProdServ: '', giro: '*', empresa: '*', usoCfdi: '*', moneda: '*', payMethod: '*', payType: '*', prodServ: '*', unidad: '*' }
    this.clientInfo = undefined;
    this.companyInfo = undefined;
    this.newConcep = new Concepto();
    this.factura = new Factura();
    this.factura.cfdi = new Cfdi();
    this.factura.cfdi.conceptos = [];
  }

  agregarConcepto() {
    this.conceptoMessages = [];
    let validConcept= true;
    if(this.newConcep.cantidad<1){
      this.conceptoMessages.push('La cantidad requerida debe ser igual o mayor a 1');
      validConcept = false;
    }
    if(this.newConcep.claveProdServ==undefined){
      this.conceptoMessages.push('La clave producto servicio del conepto es un valor requerido.');
      validConcept = false;
    }
    if(this.newConcep.claveUnidad==undefined){
      this.conceptoMessages.push('La clave de unidad y la unidad son campos requeridos.');
      validConcept = false;
    }
    if(this.newConcep.descripcion == undefined){
      this.conceptoMessages.push('La descripción del concepto es un valor requerido.');
      validConcept = false;
    }else if(this.newConcep.descripcion.length<10){
      this.conceptoMessages.push('La descripción del concepto es muy corta.');
      validConcept = false;
    }
    if(this.newConcep.valorUnitario<1){
      this.conceptoMessages.push('El valor unitario de un concepto no puede ser menor a 1.00$');
      validConcept = false;
    }

    if(validConcept){
      this.newConcep.importe = this.newConcep.cantidad * this.newConcep.valorUnitario;
      const base = this.newConcep.importe - this.newConcep.descuento;
      const impuesto = base * 0.16;
      this.factura.cfdi.impuestos += impuesto;
      this.factura.cfdi.subtotal += base;
      this.factura.cfdi.total = (this.factura.cfdi.total * 3 + base * 3 + impuesto * 3) / 3;
      this.newConcep.impuestos = [new Impuesto('002', '0.160000', base, impuesto)];//IVA is harcoded
      this.factura.cfdi.conceptos.push({ ... this.newConcep });
      this.formInfo.prodServ = '*';
      this.formInfo.unidad = '*';
      this.newConcep = new Concepto();
    }
  }

  getImporteImpuestos(impuestos: Impuesto[]) {
    return impuestos.map(i => i.importe).reduce((total, value) => total + value);
  }

  solicitarCfdi() {
    this.errorMessages = [];
    let validCdfi = true;
    if (this.companyInfo == undefined) {
      this.errorMessages.push('La empresa emisora es requerida.');
      validCdfi = false;
    } else {
      this.factura.cfdi.rfcEmisor = this.companyInfo.informacionFiscal.rfc;
      this.factura.cfdi.nombreEmisor = this.companyInfo.informacionFiscal.razonSocial;
      this.factura.cfdi.regimenFiscal = this.companyInfo.regimenFiscal;
      this.factura.rfcEmisor = this.companyInfo.informacionFiscal.rfc;
      this.factura.razonSocialEmisor = this.companyInfo.informacionFiscal.razonSocial;
    }

    if(this.clientInfo == undefined){
      this.errorMessages.push('La información del cliente es un valor solicitado');
      validCdfi = false;
    }else{
      this.factura.cfdi.rfcReceptor = this.clientInfo.rfc;
      this.factura.cfdi.nombreReceptor = this.clientInfo.razonSocial;
      this.factura.rfcRemitente = this.clientInfo.rfc;
      this.factura.razonSocialRemitente = this.clientInfo.razonSocial;
    }

    if(this.factura.cfdi.usoCfdi == undefined){
      this.errorMessages.push('El uso del CFDI es un campo requerido.');
      validCdfi = false;
    }
    if(this.factura.cfdi.moneda == undefined){
      this.errorMessages.push('La moneda es un campo requerido.');
      validCdfi = false;
    }

    if(this.factura.cfdi.formaPago == undefined){
      this.errorMessages.push('La forma de pago es un campo requerido.');
      validCdfi = false;
    }

    if(this.factura.cfdi.metodoPago == undefined){
      this.errorMessages.push('El metodo de pago es un campo requerido.');
      validCdfi = false;
    }

    if(this.factura.cfdi.conceptos.length<1){
      this.errorMessages.push('La factura debe contener a menos 1 concepto a declarar.');
      validCdfi = false;
    }

    if(validCdfi){
      this.factura.cfdi = this.factura.cfdi;
      this.invoiceService.insertNewInvoice(this.factura).subscribe(
        (invoice: Factura) => this.factura.folio = invoice.folio,
        (error: HttpErrorResponse) => this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
    }
  }


  /******* PAGOS ********/
  public fileUploadListener($event: any): void {
    const file = $event.target.files[0];
    this.filename = file.name;
    const reader = new FileReader();

  reader.onloadend = function () {
    // Since it contains the Data URI, we should remove the prefix and keep only Base64 string
    var b64 = reader.result;
    console.log(b64); //-> "R0lGODdhAQABAPAAAP8AAAAAACwAAAAAAQABAAACAkQBADs="
  };

  reader.readAsDataURL(file);
    
    
  }

  public toBase64 = file => new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = error => reject(error);
  });


}
