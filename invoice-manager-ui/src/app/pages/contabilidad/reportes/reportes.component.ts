import { Component, OnInit } from '@angular/core';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { GenericPage } from '../../../models/generic-page';
import { DownloadCsvService } from '../../../@core/util-services/download-csv.service'
import { Router } from '@angular/router';
import { Catalogo } from '../../../models/catalogos/catalogo';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { Factura } from '../../../models/factura/factura';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Component({
  selector: 'ngx-reportes',
  templateUrl: './reportes.component.html',
  styleUrls: ['./reportes.component.scss']
})
export class ReportesComponent implements OnInit {

  public headers: string[] = ['Folio', 'RFC Emisor','Emisor', 'RFC Remitente','Remitente','Tipo','Metodo pago', 'Estatus Validacion', 'Estatus Pago','Total','Fecha Solicitud', 'Fecha Timbrado'];
  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';
  public filterParams: any = { emisor: '', remitente: '', folio: '', status: '1', since: '', to: '', lineaEmisor:'B' };

  public validationCat: Catalogo[] = [];
  public payCat: Catalogo[] = [];
  public devolutionCat: Catalogo[] = [];

  constructor(private invoiceService: InvoicesData,
    private catalogService: CatalogsData,
    private donwloadService: DownloadCsvService,
    private router: Router) { }

  ngOnInit() {
    this.catalogService.getInvoiceCatalogs().toPromise().then(results => {
      this.payCat = results[3];
      this.devolutionCat = results[4];
      this.validationCat = results[5];
    }).then(() => this.updateDataTable());
  }

  public onPayStatus(payStatus: string) {
    this.filterParams.payStatus = payStatus;
  }

  public onValidationStatus(validationStatus: string) {
    this.filterParams.status = validationStatus;
    console.log(this.filterParams);
  }

  public redirectToCfdi(folio: string) {
    this.router.navigate([`./pages/contabilidad/cfdi/${folio}`])
  }


  /***     Funciones tabla      ***/

  /* Mapping function to  return correct information to view */
  public getInvoiceData(pageValue?: number, sizeValue?: number, filterParams?: any): Observable<GenericPage<Factura>> {
    return this.invoiceService.getInvoices(pageValue, sizeValue, filterParams)
      .pipe(
        map((page: GenericPage<Factura>) => {
          let records: Factura[] = page.content.map(record => {
            record.statusFactura = this.validationCat.find(v => v.id == record.statusFactura).nombre;
            record.statusPago = this.payCat.find(v => v.id == record.statusPago).nombre;
            record.statusDevolucion = this.devolutionCat.find(v => v.id == record.statusDevolucion).nombre;
            return record;
          });
          page.content = records;
          return page;
        })
      )
  }

  public updateDataTable(currentPage?: number, pageSize?: number, filterParams?: any) {
    const pageValue = currentPage || 0;
    const sizeValue = pageSize || 10;
    console.log(filterParams)
    this.getInvoiceData(pageValue, sizeValue, filterParams)
      .subscribe((result: GenericPage<any>) => this.page = result);
  }

  public onChangePageSize(pageSize: number) {
    this.updateDataTable(this.page.number, pageSize);
  }

  public downloadHandler() {
    this.getInvoiceData(0, 10000, this.filterParams).subscribe(result => {
      this.donwloadService.exportCsv(result.content, 'Facturas')
    });
  }

}
