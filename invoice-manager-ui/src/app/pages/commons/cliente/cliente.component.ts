import { Component, OnInit } from '@angular/core';
import { Client } from '../../../models/client';
import { ClientsData } from '../../../@core/data/clients-data';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { ZipCodeInfo } from '../../../models/zip-code-info';
import { UsersData } from '../../../@core/data/users-data';
import { ClientsValidatorService } from '../../../@core/util-services/clients-validator.service';

@Component({
  selector: 'ngx-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.scss']
})
export class ClienteComponent implements OnInit {

  public module: string = 'promotor';
  public clientInfo: Client;
  public messages: string[] = [];
  public formInfo: any = {rfc: '', coloniaId: '*', success: ''};
  public coloniaId: number= 0;
  public colonias = [];
  public paises = ['México'];
  constructor(private clientService: ClientsData,
              private clientValidatorService: ClientsValidatorService,
              private userService: UsersData,
              private catalogsService: CatalogsData,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    this.module = this.router.url.split('/')[2];
    this.clientInfo = new Client();
    this.clientInfo.informacionFiscal.pais = 'México';
    /** recovering folio info**/
    this.route.paramMap.subscribe(route => {
      const rfc = route.get('rfc');
      if (rfc !== '*') {
        this.clientService.getClientByRFC(rfc)
        .subscribe((client: Client) => {
          this.clientInfo = client;
          this.formInfo.rfc = rfc;
          this.catalogsService.getZipCodeInfo(client.informacionFiscal.cp).then((data: ZipCodeInfo) => {
            this.colonias = data.colonias;
            let index = 0;
            this.formInfo.coloniaId = '*';
            data.colonias.forEach(element => {
              if ( data.colonias[index] === client.informacionFiscal.localidad){
                this.formInfo.coloniaId = index;
              }
              index ++;
            });
          });
        }, (error: HttpErrorResponse) => {this.formInfo.message = error.error.message ||
                  `${error.statusText} : ${error.message}`; this.formInfo.status = error.status;});
        }});
  }

  public updateClient() {
    this.formInfo.success = '';
    this.messages = [];
    this.messages = this.clientValidatorService.validarCliente(this.clientInfo);
    this.clientService.updateClient(this.clientInfo).subscribe(client => { this.formInfo.success = 'Cliente actualizado exitosamente'; this.clientInfo = client; },
      (error: HttpErrorResponse) => {this.messages.push(error.error.message); this.formInfo.message = error.error.message || `${error.statusText} : ${error.message}`; this.formInfo.status = error.status });
  }

  public insertClient() {
    this.formInfo.success = '';
    this.messages = [];
    
    this.userService.getUserInfo().then(user => this.clientInfo.correoPromotor = user.email)
      .then(() => {
        this.messages = this.clientValidatorService.validarCliente(this.clientInfo);
        if (this.messages.length === 0) {
        this.clientService.insertNewClient(this.clientInfo)
          .subscribe(client => { this.formInfo.success = 'Cliente guardado exitosamente'; this.clientInfo = client; },
          (error: HttpErrorResponse) => this.messages.push(error.error.message || `${error.statusText} : ${error.message}`));
        }
      });
  }

  public onLocation(index: string) {
    if (index !== 'other' && index !== '*') {
      this.clientInfo.informacionFiscal.localidad = this.colonias[index];
    }
  }

  public zipCodeInfo(zc: string){
    if( zc.length > 4 && zc.length < 6) {
      this.colonias = [];
      this.catalogsService.getZipCodeInfo(zc).then(
          (data:ZipCodeInfo) => {
          this.clientInfo.informacionFiscal.estado = data.estado;
          this.clientInfo.informacionFiscal.municipio = data.municipio;
          this.colonias = data.colonias; 
          this.clientInfo.informacionFiscal.localidad = data.colonias[0];
          if (data.colonias.length < 1 ) {
            alert(`No se ha encontrado información pata el codigo postal ${zc}`);
          }
          }, (error: HttpErrorResponse) => alert(error.error.message || error.statusText));
    }
  }

  public validatePercentages() {
    if ( this.clientInfo.correoContacto === undefined || this.clientInfo.correoContacto.length < 1) {
      this.clientInfo.correoContacto = 'Sin asignar';
      this.clientInfo.porcentajeContacto = 0;
      this.clientInfo.porcentajeDespacho = 16 - this.clientInfo.porcentajeCliente - this.clientInfo.porcentajePromotor;
    }
  }

  public toggleOn() {
    this.clientInfo.activo = true;
    this.formInfo.success = '';
    this.messages = [];
    this.messages = this.clientValidatorService.validarCliente(this.clientInfo);
    this.clientService.updateClient(this.clientInfo)
    .subscribe(client => { this.formInfo.success = 'Cliente activado exitosamente'; this.clientInfo = client; },
      (error: HttpErrorResponse) => {
        this.messages.push(error.error.message || `${error.statusText} : ${error.message}`);
        this.clientInfo.activo = false;
        this.formInfo.status = error.status;
      });
  }

  public toggleOff() {
    this.clientInfo.activo = false;
    this.formInfo.success = '';
    this.messages = [];
    this.clientService.updateClient(this.clientInfo)
    .subscribe(client => { this.formInfo.success = 'Cliente desactivado exitosamente'; this.clientInfo = client; },
      (error: HttpErrorResponse) => {
        this.messages.push(error.error.message || `${error.statusText} : ${error.message}`);
        this.clientInfo.activo = true;
        this.formInfo.status = error.status;
      });
  }

}
