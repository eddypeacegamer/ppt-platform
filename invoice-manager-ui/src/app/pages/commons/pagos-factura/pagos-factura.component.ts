import { Component, OnInit, Input, ElementRef, ViewChild, TemplateRef } from '@angular/core';
import { PaymentsData } from '../../../@core/data/payments-data';
import { User } from '../../../models/user';
import { UsersData } from '../../../@core/data/users-data';
import { GenericPage } from '../../../models/generic-page';
import { NbSortDirection, NbSortRequest, NbTreeGridDataSource, NbTreeGridDataSourceBuilder, NbDialogService } from '@nebular/theme';
import { PagoBase } from '../../../models/pago-base';
import { map } from 'rxjs/operators';
import { PagoFactura } from '../../../models/pago-factura';
import { DonwloadFileService } from '../../../@core/util-services/download-file-service';
import { HttpErrorResponse } from '@angular/common/http';
import { FilesData } from '../../../@core/data/files-data';
import { DownloadCsvService } from '../../../@core/util-services/download-csv.service';
import { AsignacionPagosComponent } from '../asignacion-pagos/asignacion-pagos.component';
import { Router } from '@angular/router';


interface TreeNode<T> {
  data: T;
  children?: TreeNode<T>[];
  expanded?: boolean;
}

interface PagoFacturaModel {
  id: number;
  monto: number;
  acredor: string;
  deudor: string;
  fechaCreacion: Date;
  fechaActualizacion: Date;
  moneda?: string;
  banco?: string;
  cuenta?: string;
  tipoDeCambio?: number;
  formaPago?: string;
  statusPago?: string;
  solicitante?: string;
  revision1?: boolean;
  revision2?: boolean;
  revisor1?: string;
  revisor2?: string;
  fechaPago?: Date;
  idCfdi?: number;
  folio?: string;
  totalFactura?: number;
}

@Component({
  selector: 'ngx-pagos-factura',
  templateUrl: './pagos-factura.component.html',
  styleUrls: ['./pagos-factura.component.scss'],
})
export class PagosFacturaComponent implements OnInit {

  public user: User;
  customColumn = 'ACCIONES';
  defaultHeaders = [ 'MONTO', 'ESTATUS PAGO', 'MONEDA', 'BANCO', 'FECHA PAGO', 'ACREDOR', 'DEUDOR', 'FOLIO' ];
  defaultColumns = [ 'MONTO', 'statusPago', 'moneda', 'banco', 'fechaPago', 'acredor', 'deudor', 'folio' ];
  allColumns = [ this.customColumn, ...this.defaultColumns ];

  dataSource: NbTreeGridDataSource<PagoFacturaModel>;

  sortColumn: string;
  sortDirection: NbSortDirection = NbSortDirection.NONE;
  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';
  public errorMessages: string[] = [];
  public filterParams: any = {};

  public module: string = 'promotor';

  constructor(
    private dialogService: NbDialogService,
    private filesService: FilesData,
    private paymentsService: PaymentsData,
    private router: Router,
    private usersService: UsersData,
    private downloadService: DonwloadFileService,
    private downloadCsvService: DownloadCsvService,
    private dataSourceBuilder: NbTreeGridDataSourceBuilder<PagoFacturaModel>) {}

  ngOnInit() {
    this.errorMessages = [];
    this.module = this.router.url.split('/')[2];

    this.usersService.getUserInfo()
      .then(user => {
        this.user = user;
        if (this.module === 'promotor') {
          this.filterParams.solicitante = user.email;
        }
      }).then(() => this.updateDataTable());
  }

  getShowOn(index: number) {
    const minWithForMultipleColumns = 400;
    const nextColumnStep = 100;
    return minWithForMultipleColumns + (nextColumnStep * index);
  }
  updateSort(sortRequest: NbSortRequest): void {
    this.sortColumn = sortRequest.column;
    this.sortDirection = sortRequest.direction;
  }

  getSortDirection(column: string): NbSortDirection {
    if (this.sortColumn === column) {
      return this.sortDirection;
    }
    return NbSortDirection.NONE;
  }

  openPaymentAssigments() {
    this.dialogService.open(AsignacionPagosComponent)
    .onClose.subscribe(() => this.updateDataTable());
  }

  public updateDataTable(currentPage?: number, pageSize?: number) {
    const pageValue = currentPage || 0;
    const sizeValue = pageSize || 10;
    this.paymentsService.getAllPayments(pageValue, sizeValue, this.filterParams)
    .pipe(
      map((page: GenericPage<PagoBase>) => {
        const newPage: GenericPage<TreeNode<PagoFacturaModel>> =
                      Object.assign({}, page, {content: undefined});
        newPage.content = page.content
                      .map( (pago: PagoBase) => {
                        const children: TreeNode<PagoFacturaModel>[] = pago.facturas
                                  .map((fact: PagoFactura) => {
                                    const childNode: TreeNode<PagoFacturaModel> = {
                                      data : Object.assign({}, fact),
                                    };
                                    return childNode;
                                  });
                        const node: TreeNode<PagoFacturaModel> = {
                          data : Object.assign({}, pago, {facturas: undefined}),
                          children : children,
                        };
                        return node;
                      });
        return newPage;
      }),
    ).subscribe((page: GenericPage<TreeNode<PagoFacturaModel>>) => {
      this.page = page;
      this.dataSource = this.dataSourceBuilder.create(page.content);
      });
  }

  public onChangePageSize(pageSize: number) {
    this.updateDataTable(this.page.number, pageSize);
  }



  public deletePayment(paymentId: number) {
    this.errorMessages = [];
    this.paymentsService.deletePayment(+paymentId).subscribe(
      result => this.updateDataTable() ,
      (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
        || `${error.statusText} : ${error.message}`));
  }

  public downloadPdf(folio: string, data: any) {
    this.errorMessages = [];
    this.filesService.getFacturaFile(folio, 'PDF').subscribe(
      file => this.downloadService.downloadFile(file.data,
        `${folio}-${data.deudor}-${data.acredor}.pdf`, 'application/pdf;'));
  }

  public downloadHandler() {
    this.errorMessages = [];
    this.paymentsService.getAllPayments(0, 10000, this.filterParams).subscribe(result => {
      this.downloadCsvService.exportCsv(result.content, `Pagos_${this.user.email}`);
    });
  }

}

