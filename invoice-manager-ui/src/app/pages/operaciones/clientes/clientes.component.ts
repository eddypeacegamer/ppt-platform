import { Component, OnInit } from '@angular/core';

import { ClientsData } from '../../../@core/data/clients-data';
import { GenericPage } from '../../../models/generic-page';
import { DownloadCsvService } from '../../../@core/back-services/download-csv.service'

@Component({
  selector: 'ngx-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.scss']
})
export class ClientesComponent implements OnInit {


  public headers: string[] = ['RFC', 'Razon Social', 'Contacto', 'Email', 'No Ext', 'Calle', 'Localidad', 'Municipio', 'C.Postal'];
  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';

  public filterParams : any = {razonSocial:'',rfc:''};
   
  constructor(private clientsService: ClientsData,
    private donwloadService: DownloadCsvService) { }

  ngOnInit() {
    this.updateDataTable(0,10);
  }

  public updateDataTable(currentPage?: number, pageSize?: number,filterParams?:any) {
    const pageValue = currentPage || 0;
    const sizeValue = pageSize || 10;
    this.clientsService.getClients(pageValue, sizeValue, filterParams)
      .subscribe(result => this.page = result);
  }

  public onChangePageSize(pageSize: number) {
    this.updateDataTable(this.page.number, pageSize);
  }


  public downloadHandler() {
    this.clientsService.getClients(0, 10000, this.filterParams).subscribe(result => {
      this.donwloadService.exportCsv(result.content,'Clientes')
    });
  }

}
