import { Component, OnInit } from '@angular/core';
import { ClientsData } from '../../../@core/data/clients-data';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { Client } from '../../../models/client';
import { Contribuyente } from '../../../models/contribuyente';
import { HttpErrorResponse } from '@angular/common/http';
import { ZipCodeInfo } from '../../../models/zip-code-info';
import { UsersData } from '../../../@core/data/users-data';

@Component({
  selector: 'ngx-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.scss']
})
export class ClientesComponent implements OnInit {

  public clientInfo: Client;
  public formInfo: any = { rfc: '', message: '', coloniaId: '*', success: '' };
  public coloniaId: number = 0;
  public colonias = [];
  public paises = ['México'];
  constructor(private clientService: ClientsData, private catalogsService: CatalogsData, private userService: UsersData) { }

  ngOnInit() {
  }

  public buscarClientePorRFC() {
    this.formInfo.message = '';
    this.formInfo.success = '';
    
    this.clientService.getClientByRFC(this.formInfo.rfc)
      .subscribe((data: Client) => {this.clientInfo = data;},
        (error: HttpErrorResponse) => { this.clientInfo = new Client();
          this.clientInfo.informacionFiscal.rfc = this.formInfo.rfc;
          this.clientInfo.informacionFiscal.pais = 'México';this.formInfo.message = error.error.message || `${error.statusText} : ${error.message}`; 
          this.formInfo.status = error.status });
  }

  public updateClient() {
    this. validatePercentages();
    this.clientService.updateClient(this.clientInfo).subscribe(success => { this.formInfo.success = 'Cliente actualizado exitosamente'; this.clientInfo = undefined; },
      (error: HttpErrorResponse) => { this.formInfo.message = error.error.message || `${error.statusText} : ${error.message}`; this.formInfo.status = error.status });
  }

  public insertClient() {
    this.formInfo.message = '';
    this. validatePercentages();
    this.userService.getUserInfo().toPromise().then(user => this.clientInfo.correoPromotor = user.email)
      .then(() => {
        this.clientService.insertNewClient(this.clientInfo).subscribe(success => { this.formInfo.success = 'Cliente guardado exitosamente'; this.clientInfo = undefined; },
          (error: HttpErrorResponse) => { this.formInfo.message = error.error.message || `${error.statusText} : ${error.message}`; this.formInfo.status = error.status });
      });
  }
  public validatePercentages(){
    console.log(this.clientInfo)
    if(this.clientInfo.correoContacto == undefined || this.clientInfo.correoContacto.length<1){
      this.clientInfo.correoContacto = 'Sin asignar';
      this.clientInfo.porcentajeContacto = 0;
      this.clientInfo.porcentajeDespacho = 16 -this.clientInfo.porcentajeCliente -this.clientInfo.porcentajePromotor;
    }
  }

  public zipCodeInfo(zipcode: String) {
    if (zipcode.length > 4 && zipcode.length < 6) {
      this.colonias = [];
      this.catalogsService.getZipCodeInfo(zipcode).subscribe(
        (data: ZipCodeInfo) => {
          this.clientInfo.informacionFiscal.estado = data.estado;
          this.clientInfo.informacionFiscal.municipio = data.municipio; this.colonias = data.colonias;
          this.clientInfo.informacionFiscal.localidad = data.colonias[0];
          if (data.colonias.length < 1) {
            alert(`No se ha encontrado información pata el codigo postal ${zipcode}`);
          }
        }, (error: HttpErrorResponse) => console.error(error));
    }
  }

  public onLocation(index: string) {
    this.clientInfo.informacionFiscal.localidad = this.colonias[index];
  }

}
