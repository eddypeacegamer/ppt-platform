import { Component, OnInit } from '@angular/core';

import { GenericPage } from '../../../models/generic-page';
import { DownloadCsvService } from '../../../@core/util-services/download-csv.service'
import { PaymentsData } from '../../../@core/data/payments-data';
import { UsersData } from '../../../@core/data/users-data';
import { NbDialogService } from '@nebular/theme';
import { ValidacionPagoComponent } from './validacion-pago/validacion-pago.component';
import { PagoBase } from '../../../models/pago-base';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { User } from '../../../models/user';

@Component({
  selector: 'ngx-pagos',
  templateUrl: './pagos.component.html',
  styleUrls: ['./pagos.component.scss'],
})
export class PagosComponent implements OnInit {

  public user: User;
  public vista: string = 'validacion-pagos';
  public filterParams: any = { formaPago: '*', status: 'VALIDACION', acredor: '', deudor: '', since: '', to: '' };
  public errors: string[] = [];
  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';

  constructor(
    private userService: UsersData,
    private paymentService: PaymentsData,
    private donwloadService: DownloadCsvService,
    private router: Router,
    private dialogService: NbDialogService,
  ) { }

  ngOnInit() {


    this.vista = this.router.url.split('/')[3];
    if (this.vista === 'validacion-pagos') {
      this.filterParams.status = 'VALIDACION';
    }
    if (this.vista === 'historial-pagos') {
      this.filterParams.status = 'ACEPTADO';
    }
    this.updateDataTable();
    this.userService.getUserInfo().then(user => this.user = user);
  }

  public updateDataTable(currentPage?: number, pageSize?: number) {
    const pageValue = currentPage || 0;
    const sizeValue = pageSize || 10;
    this.paymentService.getAllPayments(pageValue, sizeValue, this.filterParams)
      .subscribe((result: GenericPage<any>) => { this.page = result; });
  }

  public onChangePageSize(pageSize: number) {
    this.updateDataTable(this.page.number, pageSize);
  }

  public downloadHandler() {
    this.paymentService.getAllPayments(0, 10000, this.filterParams).subscribe(result => {
      this.donwloadService.exportCsv(result.content, 'Pagos');
    });
  }

  public validar1(pago: PagoBase) {
    this.errors = [];
    if (pago.solicitante !== this.user.email) {
      pago.revision1 = true;
      pago.revisor1 = this.user.email;
      this.paymentService.updatePaymentWithValidation(pago.id, pago)
        .subscribe(updatedPayment => pago = updatedPayment,
          (error: HttpErrorResponse) => this.errors.push(error.error.message || `${error.statusText} : ${error.message}`));
    } else {
      this.errors.push('El solicitante del pago no puede validar el pago.');
    }
  }

  public validar2(pago: PagoBase) {
    this.errors = [];
    if (pago.solicitante !== this.user.email && pago.revisor1 !== this.user.email) {
      pago.revision2 = true;
      pago.revisor2 = this.user.email;
      this.paymentService.updatePaymentWithValidation(pago.id, pago)
        .subscribe(updatedPayment => pago = updatedPayment,
          (error: HttpErrorResponse) => this.errors.push(error.error.message || `${error.statusText} : ${error.message}`));
    } else {
      this.errors.push('El segundo revisor, no puede ser ni el solicitante ni el primer revisor.');
    }
  }


  openDialog(payment: PagoBase) {
    this.errors = [];
    this.dialogService.open(ValidacionPagoComponent, {
      context: {
        pago: payment,
      },
    }).onClose.subscribe(pago => {
      if (pago !== undefined) {
        if (pago.revisor2 === undefined) {
          pago.revisor2 = this.user.email;
        } else {
          pago.revisor1 = this.user.email;
        }
        this.paymentService.updatePaymentWithValidation(pago.id, pago).toPromise()
          .then(success => console.log(success), (error: HttpErrorResponse) => this.errors.push(error.error.message || `${error.statusText} : ${error.message}`))
          .then(() => this.updateDataTable(this.page.number, this.page.size))
      } else {
        this.updateDataTable(this.page.number, this.page.size);
      }
    });
  }

  public redirectToCfdi(folio: string) {
    this.router.navigate([`./pages/promotor/precfdi/${folio}`])
  }

  //validacion cuadro rojo por fila
  public cal(fila: any): any {

    const condition = (typeof fila.facturas !== 'undefined' && fila.facturas.length > 0);
    const totalFactura = condition ? fila.facturas.reduce((a, b) => a.totalFactura + b.totalFactura) : 0;
    const metodoPago = condition ? fila.facturas[0].metodoPago : 'PPD';

    if ((+(totalFactura.totalFactura) - (+fila.monto) !== 0 && metodoPago === 'PUE')) {

      return '#ff9494';
    }

    if (Math.abs(new Date(fila.fechaCreacion).getTime() - new Date(fila.fechaPago).getTime()) / 86400000 > 15) {

      return '#ffd982';
    }

    return 'null';
  }



}
