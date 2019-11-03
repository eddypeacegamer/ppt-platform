import { Component, OnInit } from '@angular/core';
import { Client } from '../../../models/client';
import { ClientsData } from '../../../@core/data/clients-data';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { ZipCodeInfo } from '../../../models/zip-code-info';

@Component({
  selector: 'ngx-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.scss']
})
export class ClienteComponent implements OnInit {

  public clientInfo : Client;
  public formInfo : any = {rfc:'',message:'',coloniaId:'*', success:''};
  public coloniaId: number=0;
  public colonias = [];
  public paises = ['México'];
  public porcentajes = {promotor:25,cliente:25,despacho:25,contacto:25};
  constructor(private clientService:ClientsData,private catalogsService:CatalogsData,private route: ActivatedRoute) { }

  ngOnInit() {
    this.clientInfo = new Client();
    /** recovering folio info**/
    this.route.paramMap.subscribe(route => {
      let rfc = route.get('rfc');
      this.clientService.getClientByRFC(rfc)
      .subscribe((data:Client) => {this.clientInfo = data, this.formInfo.rfc = rfc;},
      (error : HttpErrorResponse)=>{this.formInfo.message = error.error.message || `${error.statusText} : ${error.message}`; this.formInfo.status = error.status});  
      });
  }

  public updateClient(){
    this.formInfo.success='';
    this.formInfo.message='';
    this.clientService.updateClient(this.clientInfo).subscribe(success=> {this.formInfo.success = 'Cliente actualizado exitosamente';},
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
