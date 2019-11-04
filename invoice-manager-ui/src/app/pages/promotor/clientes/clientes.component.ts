import { Component, OnInit } from '@angular/core';
import { ClientsData } from '../../../@core/data/clients-data';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { Client } from '../../../models/client';
import { Contribuyente } from '../../../models/contribuyente';
import { HttpErrorResponse } from '@angular/common/http';
import { ZipCodeInfo } from '../../../models/zip-code-info';

@Component({
  selector: 'ngx-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.scss']
})
export class ClientesComponent implements OnInit {

  public clientInfo : Client;
  public formInfo : any = {rfc:'',message:'',coloniaId:'*', success:''};
  public coloniaId: number=0;
  public colonias = [];
  public paises = ['México'];
  public porcentajes = {promotor:25,cliente:25,despacho:25,contacto:25};
  constructor(private clientService:ClientsData,private catalogsService:CatalogsData) { }

  ngOnInit() {
  
  }

  public buscarClientePorRFC(){
    this.formInfo.message = '';
    this.formInfo.success = '';
    this.onRegitrarCall();
    this.clientService.getClientByRFC(this.formInfo.rfc)
      .subscribe((data:Client) => this.clientInfo = data,
      (error : HttpErrorResponse)=>{this.formInfo.message = error.error.message || `${error.statusText} : ${error.message}`; this.formInfo.status = error.status});
  }

  public onRegitrarCall(){
    this.clientInfo = new Client();
    this.clientInfo.informacionFiscal= new Contribuyente();
    this.clientInfo.informacionFiscal.rfc= this.formInfo.rfc;
    this.clientInfo.porcentajeCliente =4;
    this.clientInfo.porcentajeContacto =4;
    this.clientInfo.porcentajePromotor =4;
    this.clientInfo.porcentajeDespacho =4;
    this.clientInfo.informacionFiscal.pais= 'México';
  }

  public updateClient(){
    this.clientService.updateClient(this.clientInfo).subscribe(success=> {this.formInfo.success = 'Cliente actualizado exitosamente';this.clientInfo = undefined;},
    (error : HttpErrorResponse)=>{this.formInfo.message = error.error.message || `${error.statusText} : ${error.message}`; this.formInfo.status = error.status});
  }

  public insertClient(){
    this.formInfo.message = '';
    this.clientInfo.correoPromotor = 'promotor@gmail.com';
    this.clientService.insertNewClient(this.clientInfo).subscribe(success=> {this.formInfo.success = 'Cliente guardado exitosamente';this.clientInfo = undefined;},
    (error : HttpErrorResponse)=>{this.formInfo.message = error.error.message || `${error.statusText} : ${error.message}`; this.formInfo.status = error.status}); 
  }

  public zipCodeInfo(zipcode:String){
    let zc = new String(zipcode);
    if(zc.length>4 && zc.length <6){
      this.colonias = [];
      this.catalogsService.getZipCodeInfo(zipcode).subscribe(
          (data:ZipCodeInfo) => {this.clientInfo.informacionFiscal.estado = data.estado;
          this.clientInfo.informacionFiscal.municipio= data.municipio;this.colonias=data.colonias; 
          this.clientInfo.informacionFiscal.localidad=data.colonias[0];},
          (error: HttpErrorResponse) => console.error(error));
    }
  }

  public onLocation(index:string){
    this.clientInfo.informacionFiscal.localidad = this.colonias[index];
  }

}
