import { Component, OnInit } from '@angular/core';

import { CatalogsData } from '../../../@core/data/catalogs-data';
import { GenericPage } from '../../../models/generic-page';
import {DownloadCsvService } from '../../../@core/back-services/download-csv.service'
import { InvoicesData } from '../../../@core/data/invoices-data';

@Component({
  selector: 'ngx-pagos',
  templateUrl: './pagos.component.html',
  styleUrls: ['./pagos.component.scss']
})
export class PagosComponent implements OnInit {

  public headers: string[] = ['Folio', 'Moneda', 'Banco', 'Monto','Estatus Pago','Revision 1','Revision 2','Tipo pago', 'Forma de pago', 'Fecha pago'];
  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';

  constructor(private donwloadService:DownloadCsvService,
    private invoiceService : InvoicesData) {}

  ngOnInit() {
    this.updateDataTable();
  }

  public updateDataTable(currentPage?: number, pageSize?: number) {
    const pageValue = currentPage || 0;
    const sizeValue = pageSize || 10;
    this.invoiceService.getAllPayments(pageValue,sizeValue)
      .subscribe((result:GenericPage<any>) => this.page = result);
  }

  public onChangePageSize(pageSize: number) {
    this.updateDataTable(this.page.number, pageSize);
  }


}
