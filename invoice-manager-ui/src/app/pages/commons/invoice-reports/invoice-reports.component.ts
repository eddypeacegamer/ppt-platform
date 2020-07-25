import { Component, OnInit, OnDestroy } from '@angular/core';
import { GenericPage } from '../../../models/generic-page';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { UsersData } from '../../../@core/data/users-data';
import { DownloadCsvService } from '../../../@core/util-services/download-csv.service';
import { Router, ActivatedRoute } from '@angular/router';
import { DonwloadFileService } from '../../../@core/util-services/download-file-service';
import { FilesData } from '../../../@core/data/files-data';
import { Factura } from '../../../models/factura/factura';

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
  public filterParams: any = { emisor: '', remitente: '', prefolio: '', status: '*',since: '', to: '', lineaEmisor: 'A', solicitante: '', page:0, size:10};
  public userEmail: string;
  public loading = false;

  constructor(private invoiceService: InvoicesData,
    private userService: UsersData,
    private downloadCsvService: DownloadCsvService,
    private router: Router,
    private downloadService: DonwloadFileService,
    private filesService: FilesData,
    private route: ActivatedRoute) { }

  ngOnInit() {

    const date: Date = new Date();
    this.filterParams.to = new Date(date.getFullYear(), date.getMonth(), date.getDate() + 1);
    this.filterParams.since = new Date(date.getFullYear(), date.getMonth(), 1);

    this.module = this.router.url.split('/')[2];

    this.route.queryParams
      .subscribe(params => {
        switch (this.module) {
          case 'promotor':
            this.userService.getUserInfo().then((user) => {
              this.userEmail = user.email;
              this.filterParams = {...this.filterParams, ...params};
              this.filterParams.solicitante = user.email;
              this.updateDataTable(0, 10);
            });
            break;
          case 'operaciones':
            this.statusFlag = this.filterParams.status !== '*';
            this.updateDataTable(0, 10);
            break;
          case 'contabilidad':

            this.statusFlag = false;
            this.updateDataTable(0, 10);
            break;
          case 'administracion':
            this.filterParams.status = status;
            this.statusFlag = false;
            this.updateDataTable(0, 10);
            break;
          case 'tesoreria':
            this.statusFlag = false;
            this.updateDataTable(0, 10);
            break;
          default:
            this.userService.getUserInfo().then((user) => {
              this.userEmail = user.email;
              this.filterParams = {...this.filterParams, ...params};
              this.filterParams.solicitante = user.email;
              this.updateDataTable(0, 10);
            });
            break;
        }
      });
  }

  public onPayStatus(payStatus: string) {
    this.filterParams.payStatus = payStatus;
  }

  public onValidationStatus(validationStatus: string) {
    this.filterParams.status = validationStatus;
  }


  public searchData(currentPage?: number, pageSize?: number) {
    const params: any = {};
    const pageValue = currentPage || this.filterParams.page;
    const sizeValue = pageSize || this.filterParams.size;
    this.filterParams.page = pageValue;
    this.filterParams.size = sizeValue;

    for (const key in this.filterParams) {
      if (this.filterParams[key] !== undefined) {
        let value: string = this.filterParams[key];
      if ( this.filterParams[key] instanceof Date) {
        const date: Date = this.filterParams[key] as Date;
        value = `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`;
      }
      if ( value !== null && value.length > 0) {
          params[key] = value;
        }
      }
    }
    switch (this.module) {
      case 'promotor':
        this.router.navigate([`./pages/promotor/reportes`],
            { queryParams: params , queryParamsHandling: 'merge' });
        this.updateDataTable();
        break;
      case 'tesoreria':
        this.router.navigate([`./pages/promotor/reportes`]);
        break;
      case 'operaciones':
        if (this.filterParams.lineaEmisor === 'A') {
          this.router.navigate([`./pages/operaciones/reportes`]);
        } else if (this.filterParams.lineaEmisor === 'B') {
          this.router.navigate([`./pages/operaciones/reportes`]);
        } else if (this.filterParams.lineaEmisor === 'C') {
          this.router.navigate([`./pages/operaciones/reportes`]);
        } else {
          this.router.navigate([`./pages/operaciones/reportes`]);
        }
        break;
      case 'contabilidad':
        this.router.navigate([`./pages/contabilidad/reportes/B/8`]);
        break;
      case 'administracion':
        this.router.navigate([`./pages/administracion/reportes/A/3`]);
        break;
      default:
        this.router.navigate([`./pages/pages/promotor/reportes/A/*`]);
    }
  }


  public redirectToCfdi(folio: string) {

    switch (this.module) {
      case 'promotor':
        this.router.navigate([`./pages/promotor/precfdi/${folio}`]);
        break;
      case 'tesoreria':
        this.router.navigate([`./pages/promotor/precfdi/${folio}`]);
        break;
      case 'operaciones':
        if (this.filterParams.lineaEmisor === 'A') {
          this.router.navigate([`./pages/operaciones/revision/${folio}`]);
        } else if (this.filterParams.lineaEmisor === 'B') {
          this.router.navigate([`./pages/operaciones/linea-b/${folio}`]);
        } else if (this.filterParams.lineaEmisor === 'C') {
          this.router.navigate([`./pages/operaciones/linea-c/${folio}`]);
        } else {
          this.router.navigate([`./pages/promotor/precfdi/${folio}`]);
        }
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
      case 'tesoreria':
        this.router.navigate([`./pages/tesoreria/facturas/${folio}/devoluciones`]);
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

  public updateDataTable() {
    console.log('FilterParams :', this.filterParams);
    this.invoiceService.getInvoices(this.filterParams.page, this.filterParams.size, this.filterParams)
      .subscribe((result: GenericPage<any>) => this.page = result);
  }

  public onChangePageSize(pageSize: number) {
    this.searchData(this.page.number, pageSize);
  }

  public downloadHandler() {
    this.invoiceService.getInvoices(0, 10000, this.filterParams).subscribe(result => {
      this.downloadCsvService.exportCsv(result.content, 'Facturas');
    });
  }

  public downloadInvoicesReports() {
    this.invoiceService.getInvoicesReports(0, 10000, this.filterParams).subscribe(result => {
      this.downloadCsvService.exportCsv(result.content, 'Facturas');
    });
  }

  public downloadComplementReports() {
    this.invoiceService.getComplementReports(0, 10000, this.filterParams).subscribe(result => {
      this.downloadCsvService.exportCsv(result.content, 'Complementos');
    });
  }


  public downloadPdf(emisor: string, receptor: string, folio: string) {
    this.loading = true;
    this.filesService.getFacturaFile(folio, 'PDF').subscribe(
      file => {
        this.downloadService.downloadFile(file.data, `${emisor}_${receptor}_${folio}.pdf`, 'application/pdf;');
        this.loading = false;
      }, error => {
        console.error('Error recovering PDF file:', error);
        this.loading = false;
      });
  }
  public downloadXml(emisor: string, receptor: string, folio: string) {
    this.loading = true;
    this.filesService.getFacturaFile(folio, 'XML').subscribe(
      file => {
        this.downloadService.downloadFile(file.data, `${emisor}_${receptor}_${folio}.xml`, 'text/xml;charset=utf8;');
        this.loading = false;
      }, error => {
        console.error('Error recovering XML file', error);
        this.loading = false;
      });
  }

  public reSendEmail(folio: string) {
    this.loading = true;
    this.invoiceService.reSendEmail(folio,new Factura).subscribe(
      factura => {
        this.loading = false;
      }, error => {
        console.error('Error sending email', error);
        this.loading = false;
      });
  }

}
