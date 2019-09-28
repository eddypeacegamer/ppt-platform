import { Component, OnInit } from '@angular/core';
import { ClientsData } from '../../../@core/data/clients-data';
import { Client } from '../../../models/client';

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
    this.clientInfo = new Client();
  }

  public buscarClientePorRFC(){
    this.clientService.getClientByRFC(this.rfc)
      .subscribe(data => this.clientInfo = data);
  }

}
