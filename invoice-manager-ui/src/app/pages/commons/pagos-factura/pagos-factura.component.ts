import { Component, OnInit, Input, ElementRef, ViewChild, TemplateRef } from '@angular/core';
import { PaymentsData } from '../../../@core/data/payments-data';
import { User } from '../../../models/user';
import { UsersData } from '../../../@core/data/users-data';
import { GenericPage } from '../../../models/generic-page';
import { NbSortDirection, NbSortRequest, NbTreeGridDataSource, NbTreeGridDataSourceBuilder, NbDialogService } from '@nebular/theme';
import { AsignacionPagosComponent } from '../asignacion-pagos/asignacion-pagos.component';
import { PagoBase } from '../../../models/pago-base';
import { map } from 'rxjs/operators';
import { PagoFactura } from '../../../models/pago-factura';
import { DonwloadFileService } from '../../../@core/util-services/download-file-service';
import { HttpErrorResponse } from '@angular/common/http';


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
  defaultColumns = [ 'MONTO', 'statusPago','moneda', 'banco', 'fechaPago', 'acredor', 'deudor', 'folio' ];
  allColumns = [ this.customColumn, ...this.defaultColumns ];

  dataSource: NbTreeGridDataSource<PagoFacturaModel>;

  sortColumn: string;
  sortDirection: NbSortDirection = NbSortDirection.NONE;
  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';
  public errorMessages: string[] = [];
  public filterParams: any = {};

  constructor(
    private dialogService: NbDialogService,
    private paymentsService: PaymentsData,
    private usersService: UsersData,
    private downloadService: DonwloadFileService,
    private dataSourceBuilder: NbTreeGridDataSourceBuilder<PagoFacturaModel>) {}

  ngOnInit() {
    this.usersService.getUserInfo()
      .then(user => {
        this.user = user;
        this.filterParams.solicitante = user.email;
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

 

  /******* PAGOS ********/

 

  //

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



  deletePayment(paymentId) {
    this.paymentsService.deletePayment(+paymentId).subscribe(
      result => console.log("------   "+JSON.stringify(result)) ,
      (error: HttpErrorResponse) => {this.errorMessages.push(error.error.message
        || `${error.statusText} : ${error.message}`));
  }

}

