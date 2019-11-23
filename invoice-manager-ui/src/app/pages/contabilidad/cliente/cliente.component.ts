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
    this.validatePercentages();
    this.clientService.updateClient(this.clientInfo).subscribe(success=> {this.formInfo.success = 'Cliente actualizado exitosamente';},
    (error : HttpErrorResponse)=>{this.formInfo.message = error.error.message || `${error.statusText} : ${error.message}`; this.formInfo.status = error.status});
  }

  public zipCodeInfo(zipcode:String){
    let zc = new String(zipcode);
    if(zc.length>4 && zc.length <6){
      this.colonias = [];
      this.catalogsService.getZipCodeInfo(zipcode).subscribe(
          (data:ZipCodeInfo) => {
            console.log(data)
          this.clientInfo.informacionFiscal.estado = data.estado;
          this.clientInfo.informacionFiscal.municipio= data.municipio;
          this.colonias=data.colonias; 
          this.clientInfo.informacionFiscal.localidad=data.colonias[0];
          
          if(data.colonias.length<1){
            alert(`No se ha encontrado información pata el codigo postal ${zipcode}`);
          }
        
          },(error: HttpErrorResponse) => alert(error.error.message || error.statusText));
    }
  }

  public onLocation(index:string){
    this.clientInfo.informacionFiscal.localidad = this.colonias[index];
  }

  public validatePercentages(){
    console.log(this.clientInfo)
    if(this.clientInfo.correoContacto == undefined || this.clientInfo.correoContacto.length<1){
      this.clientInfo.correoContacto = 'Sin asignar';
      this.clientInfo.porcentajeContacto = 0;
      this.clientInfo.porcentajeDespacho = 16 -this.clientInfo.porcentajeCliente -this.clientInfo.porcentajePromotor;
    }
  }


}
