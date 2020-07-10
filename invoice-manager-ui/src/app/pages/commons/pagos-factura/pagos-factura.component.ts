import { Component, OnInit, Input, ElementRef, ViewChild, TemplateRef } from '@angular/core';
import { PaymentsData } from '../../../@core/data/payments-data';
import { PagosValidatorService } from '../../../@core/util-services/pagos-validator.service';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { CuentasData } from '../../../@core/data/cuentas-data';
import { Catalogo } from '../../../models/catalogos/catalogo';
import { Cuenta } from '../../../models/cuenta';
import { FilesData } from '../../../@core/data/files-data';
import { User } from '../../../models/user';
import { UsersData } from '../../../@core/data/users-data';
import { GenericPage } from '../../../models/generic-page';
import { NbSortDirection, NbSortRequest, NbTreeGridDataSource, NbTreeGridDataSourceBuilder, NbDialogService } from '@nebular/theme';
import { AsignacionPagosComponent } from '../asignacion-pagos/asignacion-pagos.component';
import { PagoBase } from '../../../models/pago-base';
import { map } from 'rxjs/operators';
import { empty } from 'rxjs';
import { PagoFactura } from '../../../models/pago-factura';
import { Contribuyente } from '../../../models/contribuyente';
import { ClientsData } from '../../../@core/data/clients-data';
import { Client } from '../../../models/client';
import { Factura } from '../../../models/factura/factura';
import { ResourceFile } from '../../../models/resource-file';
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
  
  public filterParams: any = {};

  constructor(
    private dialogService: NbDialogService,
    private paymentsService: PaymentsData,
    private usersService: UsersData,
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


  


  public openPaymentAssigments(pago?: PagoBase) {
    const payment = pago || new PagoBase();
    payment.monto = 3500;
    payment.moneda = 'MXN';
    this.dialogService.open(AsignacionPagosComponent, {
      context: {
        payment : payment,
      },
    }).onClose.subscribe(() => console.log('Closing view'));
  }

  deletePayment(paymentId) {
    /*
    this.paymentsService.deletePayment(this.factura.folio, paymentId).subscribe(
      result => {
        this.paymentsService.getPaymentsByFolio(this.factura.folio)
          .subscribe(payments => {
            this.invoicePayments = payments;
            this.paymentSum = this.paymentValidator.getPaymentAmmount(payments);
          });
        this.invoiceService.getComplementosInvoice(this.factura.folio)
          .subscribe(complementos => this.factura.complementos = complementos);
      }, (error: HttpErrorResponse) =>
      this.payErrorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));*/
  }

}

