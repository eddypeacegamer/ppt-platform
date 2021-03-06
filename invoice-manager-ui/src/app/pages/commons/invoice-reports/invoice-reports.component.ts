import { Component, OnInit, OnDestroy } from '@angular/core';
import { GenericPage } from '../../../models/generic-page';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { UsersData } from '../../../@core/data/users-data';
import { DownloadCsvService } from '../../../@core/util-services/download-csv.service';
import { Router, ActivatedRoute } from '@angular/router';
import { DonwloadFileService } from '../../../@core/util-services/download-file-service';
import { FilesData } from '../../../@core/data/files-data';
import { Factura } from '../../../models/factura/factura';
import { UtilsService } from '../../../@core/util-services/utils.service';

@Component({
  selector: 'ngx-invoice-reports',
  templateUrl: './invoice-reports.component.html',
  styleUrls: ['./invoice-reports.component.scss'],
})
export class InvoiceReportsComponent implements OnInit {

  public module: string = 'promotor';
  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';
  public filterParams: any = { emisor: '', remitente: '', prefolio: '', status: '*', since: undefined, to: undefined,
        tipoDocumento: '*', metodoPago: '*', saldoPendiente: '0' , lineaEmisor: '', solicitante: '', page: '0', size: '10' };
  public userEmail: string;
  public loading = false;

  constructor(private invoiceService: InvoicesData,
    private userService: UsersData,
    private downloadCsvService: DownloadCsvService,
    private router: Router,
    private downloadService: DonwloadFileService,
    private filesService: FilesData,
    private utilsService: UtilsService,
    private route: ActivatedRoute) { }


  ngOnInit() {

    const date: Date = new Date();
    const offsetHrs = date.getTimezoneOffset() / 60;
    this.module = this.router.url.split('/')[2];

    this.route.queryParams
      .subscribe(params => {
        if (!this.utilsService.compareParams(params, this.filterParams)) {
          this.filterParams = { ...this.filterParams, ...params };
          this.filterParams.to = params.to === undefined ?
            new Date(date.getFullYear(), date.getMonth(), date.getDate() + 1) : new Date(`${this.filterParams.to}T00:00:00-0${offsetHrs}:00`);
          this.filterParams.since = params.since === undefined ?
            new Date(date.getFullYear(), date.getMonth(), 1) : new Date(`${this.filterParams.since}T00:00:00-0${offsetHrs}:00`);

          switch (this.module) {
            case 'promotor':
              this.userService.getUserInfo().then((user) => {
                this.userEmail = user.email;
                this.filterParams.solicitante = user.email;
                this.updateDataTable();
              });
              break;
            case 'operaciones':
              this.updateDataTable();
              break;
            case 'contabilidad':
              this.updateDataTable();
              break;
            case 'administracion':
              this.filterParams.status = status;
              this.updateDataTable();
              break;
            case 'tesoreria':
              this.updateDataTable();
              break;
            default:
              this.userService.getUserInfo().then((user) => {
                this.userEmail = user.email;
                this.filterParams = { ...this.filterParams, ...params };
                this.filterParams.solicitante = user.email;
                this.updateDataTable();
              });
              break;
          }
        }
      });
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

  public updateDataTable(currentPage?: number, pageSize?: number) {

    const params: any = this.utilsService.parseFilterParms(this.filterParams);
    params.page = currentPage !== undefined ? currentPage : this.filterParams.page;
    params.size = pageSize !== undefined ? pageSize : this.filterParams.size;

    switch (this.module) {
      case 'promotor':
        this.router.navigate([`./pages/promotor/reportes`],
          { queryParams: params });
        break;
      case 'tesoreria':
        this.router.navigate([`./pages/promotor/reportes`],
          { queryParams: params });
        break;
      case 'operaciones':
        this.router.navigate([`./pages/operaciones/reportes`],
          { queryParams: params });
        break;
      case 'contabilidad':
        this.router.navigate([`./pages/contabilidad/reportes`],
          { queryParams: params });
        break;
      case 'administracion':
        this.router.navigate([`./pages/administracion/reportes`],
          { queryParams: params });
        break;
      default:
        this.router.navigate([`./pages/promotor/reportes`],
          { queryParams: params });
    }

    this.invoiceService.getInvoices(params)
      .subscribe((result: GenericPage<any>) => this.page = result);
  }

  public onChangePageSize(pageSize: number) {
    this.updateDataTable(this.page.number, pageSize);
  }

  public downloadHandler() {

    const params: any = {};
    /* Parsing logic */
    for (const key in this.filterParams) {
      if (this.filterParams[key] !== undefined) {
        let value: string = this.filterParams[key];
        if (this.filterParams[key] instanceof Date) {
          const date: Date = this.filterParams[key] as Date;
          value = `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`;
        }
        if (value !== null && value.length > 0) {
          params[key] = value;
        }
      }
    }
    params.page = 0;
    params.size = 10000;
    this.invoiceService.getInvoices(params).subscribe(result => {
      this.downloadCsvService.exportCsv(result.content, 'Facturas');
    });
  }

  public downloadInvoicesReports() {
    const params: any = this.utilsService.parseFilterParms(this.filterParams);
    params.page = 0;
    params.size = 10000;
    this.invoiceService.getInvoicesReports(params).subscribe(result => {
      this.downloadCsvService.exportCsv(result.content, 'Facturas');
    });
  }

  public downloadComplementReports() {
    const params: any = this.utilsService.parseFilterParms(this.filterParams);
    params.page = 0;
    params.size = 10000;
    this.invoiceService.getComplementReports(params).subscribe(result => {
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
    this.invoiceService.reSendEmail(folio, new Factura).subscribe(
      factura => {
        this.loading = false;
      }, error => {
        console.error('Error sending email', error);
        this.loading = false;
      });
  }

}
