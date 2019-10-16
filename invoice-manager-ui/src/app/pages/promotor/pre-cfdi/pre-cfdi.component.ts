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
  public concentos : Concepto [] =[];
  public newConcep : Concepto ;
  public headers: string[] = ['Producto Servicio', 'Cantidad','Clave Unidad', 'Unidad','Deescripcion', 'Valor Unitario', 'Importe','Descuento'];
  public errorMessage : string = '';

  public formInfo = {clientRfc:'',companyRfc:'',claveProdServ:''}
  public clientInfo : Contribuyente;
  public companyInfo : Contribuyente;

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

    /** INIT VARIABLES **/
    this.newConcep = new Concepto();
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

  onCompanySelection(companyId:number){
    console.log(this.companyInfo)
    this.companyInfo = this.companiesCat.find(c=>c.id == companyId).informacionFiscal;
  }

  buscarClaveProdServSelected(){
    console.log(this.formInfo.claveProdServ)
    this.catalogsService.getProductoServiciosByDescription(this.formInfo.claveProdServ).subscribe(claves=>this.prodServCat=claves);
  }

  buscarClientInfo(){
    this.errorMessage = '';
    this.clientsService.getClientByRFC(this.formInfo.clientRfc).subscribe(client=>this.clientInfo=client.informacionFiscal,
      (error : HttpErrorResponse)=>this.errorMessage = error.error.message || `${error.statusText} : ${error.message}`);
  }

  onClaveProdServSelected(clave:string){
    this.newConcep.claveProdServ = clave;
  }

  onSelectUnidad(clave:string){
    this.newConcep.claveUnidad = clave;
    this.newConcep.unidad = this.claveUnidadCat.find(u=>u.clave==clave).nombre;
  }

  agregarConcepto(){
    this.newConcep.importe = this.newConcep.cantidad * this.newConcep.valorUnitario - this.newConcep.descuento;
    this.concentos.push({... this.newConcep});
    this.newConcep = new Concepto();
  }



}
