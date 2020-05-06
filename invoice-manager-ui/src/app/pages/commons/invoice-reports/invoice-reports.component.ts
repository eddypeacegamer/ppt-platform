import { Component, OnInit } from '@angular/core';
import { GenericPage } from '../../../models/generic-page';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { UsersData } from '../../../@core/data/users-data';
import { DownloadCsvService } from '../../../@core/util-services/download-csv.service';
import { Router } from '@angular/router';


@Component({
  selector: 'ngx-invoice-reports',
  templateUrl: './invoice-reports.component.html',
  styleUrls: ['./invoice-reports.component.scss'],
})
export class InvoiceReportsComponent implements OnInit {

  public module: string = 'promotor';
  public statusFlag = false;
  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';
  public filterParams: any = { emisor: '', remitente: '', folio: '', status: '*', since: '', to: '',lineaEmisor: 'A', solicitante: '' };
  public userEmail: string;

  constructor(private invoiceService: InvoicesData,
    private userService: UsersData,
    private donwloadService: DownloadCsvService,
    private router: Router) {}

    ngOnInit() {
      const date: Date = new Date();
      this.filterParams.to = new Date(date.getFullYear(), date.getMonth(), date.getDate() + 1);
      this.filterParams.since = new Date(date.getFullYear(), date.getMonth(), 1);

      this.module = this.router.url.split('/')[2];
      switch (this.module) {
        case 'promotor':
          this.userService.getUserInfo().then((user) => {
            this.userEmail = user.email;
            this.filterParams.solicitante = user.email;
            this.updateDataTable();
          });
        break;
        case 'operaciones':
          this.filterParams.lineaEmisor = 'A';
          this.filterParams.status = this.router.url.split('/')[4];
          this.statusFlag = this.filterParams.status !== '*';
          this.updateDataTable();
        break;
        case 'contabilidad':
          this.filterParams.lineaEmisor = 'B';
          this.filterParams.status = this.router.url.split('/')[4];
          this.statusFlag = false;
          this.updateDataTable();
        break;
        case 'administracion':
          this.filterParams.lineaEmisor = 'A';
          this.filterParams.status = '3';
          this.statusFlag = false;
          this.updateDataTable();
        break;
        default:
          this.updateDataTable();
        break;
      }

    }

    public onPayStatus(payStatus: string) {
        this.filterParams.payStatus = payStatus;
    }

    public onValidationStatus(validationStatus: string) {
        this.filterParams.status = validationStatus;
    }

    public redirectToCfdi(folio: string) {

      switch (this.module) {
        case 'promotor':
          this.router.navigate([`./pages/promotor/precfdi/${folio}`]);
        break;
        case 'operaciones':
          this.router.navigate([`./pages/operaciones/revision/${folio}`]);
        break;
        case 'contabilidad':
          this.router.navigate([`./pages/contabilidad/cfdi/${folio}`]);
        break;
        case 'administracion':
          this.router.navigate([`./pages/promotor/precfdi/${folio}`]);
        break;
        default:
          this.router.navigate([`./pages/promotor/precfdi/${folio}`]);
      }
    }

    public redirectToDevolutionDetails(folio: string) {
      switch (this.module) {
        case 'operaciones':
          this.router.navigate([`./pages/operaciones/facturas/${folio}/devoluciones`]);
          break;
        case 'administracion':
          this.router.navigate([`./pages/administracion/devoluciones/${folio}/ajustes`]);
          break;
        default:
          break;
      }
    }


    public redirectToPreferences(folio: string) {
      this.router.navigate([`./pages/promotor/precfdi/${folio}/preferencias`]);
    }

    public updateDataTable(currentPage?: number, pageSize?: number) {
      const pageValue = currentPage || 0;
      const sizeValue = pageSize || 10;
      this.invoiceService.getInvoices(pageValue, sizeValue, this.filterParams)
        .subscribe((result: GenericPage<any>) => this.page = result);
    }

    public onChangePageSize(pageSize: number) {
      this.updateDataTable(this.page.number, pageSize);
    }

    public downloadHandler() {
      this.invoiceService.getInvoices(0, 10000, this.filterParams).subscribe(result => {
        this.donwloadService.exportCsv(result.content, 'Facturas');
      });
    }

    public downloadInvoicesReports() {
      this.invoiceService.getInvoicesReports(0, 10000, this.filterParams).subscribe(result => {
        this.donwloadService.exportCsv(result.content, 'Facturas');
      });
    }

    public downloadComplementReports() {
      this.invoiceService.getComplementReports(0, 10000, this.filterParams).subscribe(result => {
        this.donwloadService.exportCsv(result.content, 'Complementos');
      });
    }

}
