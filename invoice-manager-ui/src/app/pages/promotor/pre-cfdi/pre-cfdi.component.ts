import { Component, OnInit , TemplateRef } from '@angular/core';
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

@Component({
  selector: 'ngx-pre-cfdi',
  templateUrl: './pre-cfdi.component.html',
  styleUrls: ['./pre-cfdi.component.scss']
})
export class PreCfdiComponent implements OnInit {

  public girosCat : Giro[];
  public companiesCat : Empresa[];
  public prodServCat : ClaveProductoServicio[];
  public claveUnidadCat : ClaveUnidad [];
  public usoCfdiCat : UsoCfdi [];
  
  public newConcep : Concepto ;
  public factura : Factura;

  public headers: string[] = ['Producto Servicio', 'Cantidad','Clave Unidad', 'Unidad','Descripcion', 'Valor Unitario', 'Importe','Descuento'];
  public errorMessage : string = '';

  public formInfo = {clientRfc:'',companyRfc:'',claveProdServ:''}
  public clientInfo : Contribuyente;
  public companyInfo : Empresa;
  

  constructor(private dialogService: NbDialogService, 
              private catalogsService : CatalogsData,
              private clientsService : ClientsData,
              private companiesService:CompaniesData){}

  ngOnInit() {

    /**** LOADING CAT INFO ****/ 
    this.catalogsService.getAllGiros().subscribe((giros:Giro[])=>this.girosCat= giros,
      (error : HttpErrorResponse)=>this.errorMessage = error.error.message || `${error.statusText} : ${error.message}`);
    this.catalogsService.getClaveUnidadByName('').subscribe((unidades:ClaveUnidad[])=>this.claveUnidadCat=unidades,
    (error : HttpErrorResponse)=>this.errorMessage = error.error.message || `${error.statusText} : ${error.message}`);
    this.catalogsService.getAllUsoCfdis().subscribe((usos:UsoCfdi[])=>this.usoCfdiCat = usos,
    (error : HttpErrorResponse)=>this.errorMessage = error.error.message || `${error.statusText} : ${error.message}`);
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

  onGiroSelection(giroId:any){
    this.companiesService.getCompaniesByLineaAndGiro('A', Number(giroId)).subscribe(companies=>this.companiesCat = companies,
      (error : HttpErrorResponse)=>this.errorMessage = error.error.message || `${error.statusText} : ${error.message}`);
  }

  onCompanySelected(companyId:number){
    this.companyInfo = this.companiesCat.find(c=>c.id == companyId);
    console.log(this.companyInfo)
  }

  buscarClaveProdServSelected(){
    console.log(this.formInfo.claveProdServ)
    this.catalogsService.getProductoServiciosByDescription(this.formInfo.claveProdServ).subscribe(claves=>this.prodServCat=claves);
  }

  buscarClientInfo(){
    this.errorMessage = '';
    this.clientsService.getClientByRFC(this.formInfo.clientRfc).subscribe(
      (client:Client) => {this.clientInfo=client.informacionFiscal},
      (error : HttpErrorResponse)=>this.errorMessage = error.error.message || `${error.statusText} : ${error.message}`);
  }

  onUsoCfdiSelected(clave:string){
    this.factura.cfdi.usoCfdi = clave;
  }

  onMonedaSelected(clave:string){
    this.factura.cfdi.moneda = clave;
  }

  onMetodoDePagoSelected(clave:string){
    this.factura.cfdi.metodoPago = clave;
  }

  onFormaDePagoSelected(clave:string){
    this.factura.cfdi.formaPago = clave;
  }

  onClaveProdServSelected(clave:string){
    this.newConcep.claveProdServ = clave;
  }

  onImpuestoSelected(clave:string){
    if(clave === '002'){
      this.newConcep.impuestos = [new Impuesto(clave,'0.160000')]
    }
  }

  onSelectUnidad(clave:string){
    this.newConcep.claveUnidad = clave;
    this.newConcep.unidad = this.claveUnidadCat.find(u=>u.clave==clave).nombre;
  }

  agregarConcepto(){
    this.newConcep.importe = this.newConcep.cantidad * this.newConcep.valorUnitario;
    const base = this.newConcep.importe -this.newConcep.descuento;
    const impuesto = base * 0.16;
    this.newConcep.impuestos = [new Impuesto('002','0.160000',base,impuesto)];//IVA is harcoded
    this.factura.cfdi.conceptos.push({... this.newConcep});
    this.newConcep = new Concepto();
  }

  solicitarCfdi(){
    
    this.factura.cfdi.rfcEmisor = this.companyInfo.informacionFiscal.rfc;
    this.factura.cfdi.nombreEmisor = this.companyInfo.informacionFiscal.razonSocial;
    this.factura.cfdi.regimenFiscal = this.companyInfo.regimenFiscal;
    this.factura.cfdi.rfcReceptor = this.clientInfo.rfc;
    this.factura.cfdi.nombreReceptor = this.clientInfo.razonSocial;
    

    this.factura.cfdi = this.factura.cfdi;
    this.factura.rfcEmisor = this.companyInfo.informacionFiscal.rfc;
    this.factura.razonSocialEmisor = this.companyInfo.informacionFiscal.razonSocial;
    this.factura.rfcRemitente = this.clientInfo.rfc;
    this.factura.razonSocialRemitente = this.clientInfo.razonSocial;

    console.log(this.factura);
  }


}
