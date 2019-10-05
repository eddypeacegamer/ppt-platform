import { Component, OnInit } from '@angular/core';
import { ClientsData } from '../../../@core/data/clients-data';
import { Client } from '../../../models/client';
import { Contribuyente } from '../../../models/contribuyente';

@Component({
  selector: 'ngx-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.scss']
})
export class ClientesComponent implements OnInit {

  public clientInfo : Client;
  public rfc : string = '';
  
  constructor(private clientService:ClientsData) { }

  ngOnInit() {
    
  }

  public buscarClientePorRFC(){
    this.clientService.getClientByRFC(this.rfc)
      .subscribe(data => this.clientInfo = data);
  }

  public onRegitrarCall(){
    this.clientInfo = new Client();
    this.clientInfo.informacionFiscal= new Contribuyente();
    this.clientInfo.informacionFiscal.rfc= this.rfc;
  }

  public updateClient(){
    this.clientService.updateClient(this.clientInfo).subscribe(r=>console.log('upadted',r));
  }

  public insertClient(){
    this.clientService.insertNewClient(this.clientInfo).subscribe(r=>console.log('inserted',r));
  }

}
