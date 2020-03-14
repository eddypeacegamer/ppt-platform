import { Component, OnInit } from '@angular/core';

import { ClientsData } from '../../../@core/data/clients-data';
import { GenericPage } from '../../../models/generic-page';
import { Client } from '../../../models/client';
import { DownloadCsvService } from '../../../@core/util-services/download-csv.service'
import { Router } from '@angular/router';
import { UsersData, User } from '../../../@core/data/users-data';

@Component({
  selector: 'ngx-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.scss']
})
export class ClientesComponent implements OnInit {

  public user: User;
  public paths: string[] = [];
  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';

  public filterParams: any = {razonSocial: '', rfc: '', status: '*', promotor: ''};

  constructor(
    private userService: UsersData,
    private clientService: ClientsData,
    private donwloadService: DownloadCsvService,
    private router: Router) { }

  ngOnInit() {
    this.paths = this.router.url.split('/');
    this.userService.getUserInfo().toPromise()
      .then((user) => {
        this.user = user as User;
        if (this.paths[2] === 'promotor')  {
          this.filterParams.promotor = user.email;
        }
      }).then(() => this.updateDataTable(0, 10, this.filterParams));
  }

  public updateDataTable(currentPage?: number, pageSize?: number, filterParams?: any) {
    const pageValue = currentPage || 0;
    const sizeValue = pageSize || 10;
    this.clientService.getClients(pageValue, sizeValue, filterParams)
      .subscribe(result => this.page = result);
  }

  public onChangePageSize(pageSize: number) {
    this.updateDataTable(this.page.number, pageSize, this.filterParams);
  }


  public downloadHandler() {
    this.clientService.getClients(0, 10000, this.filterParams).subscribe((result: GenericPage<Client>) => {
      this.donwloadService.exportCsv(result.content.map(r => r.informacionFiscal), 'Clientes');
    });
  }

  public redirectToCliente(rfc: string) {
    this.router.navigate([`./pages/${this.paths[2]}/cliente/${rfc}`]);
  }

}
