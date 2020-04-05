import { Component, OnInit } from '@angular/core';

import { GenericPage } from '../../../models/generic-page';
import {DownloadCsvService } from '../../../@core/util-services/download-csv.service'
import { PaymentsData } from '../../../@core/data/payments-data';
import { UsersData, User } from '../../../@core/data/users-data';
import { NbDialogService } from '@nebular/theme';
import { ValidacionPagoComponent } from './validacion-pago/validacion-pago.component';
import { PagoFactura } from '../../../models/pago-factura';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'ngx-valiudacion-pagos',
  templateUrl: './validacion-pagos.component.html',
  styleUrls: ['./validacion-pagos.component.scss'],
})
export class ValidacionPagosComponent implements OnInit {

  public user: User;
  public filterParams: any = { formaPago: '*', status: 'VALIDACION', acredor: '', deudor: '', since: '', to: '' };
  public errors: string[]= [];
  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';

  constructor(
    private userService: UsersData,
    private paymentService: PaymentsData,
    private donwloadService: DownloadCsvService,
    private router: Router,
    private dialogService: NbDialogService,
    ) {}

  ngOnInit() {
    this.updateDataTable();
    this.filterParams = { formaPago: '*', status: 'VALIDACION', acredor: '', deudor: '', since: '', to: '' };
    this.userService.getUserInfo().then(user => this.user = user);
  }

  public updateDataTable(currentPage?: number, pageSize?: number) {
    const pageValue = currentPage || 0;
    const sizeValue = pageSize || 10;
    this.paymentService.getAllPayments(pageValue, sizeValue, this.filterParams)
      .subscribe((result: GenericPage<any>) => this.page = result);
  }

  public onChangePageSize(pageSize: number) {
    this.updateDataTable(this.page.number, pageSize);
  }

  public downloadHandler() {
    this.paymentService.getIncomes(0, 10000, this.filterParams).subscribe(result => {
      this.donwloadService.exportCsv(result.content, 'Pagos');
    });
  }

  public validar1(pago: PagoFactura) {
    this.errors = [];
    if (pago.solicitante !== this.user.email) {
      pago.revision1 = true;
      pago.revisor1 = this.user.email;
      this.paymentService.updatePaymentWithValidation(pago.folio, pago.id, pago)
      .subscribe(updatedPayment => pago = updatedPayment,
      (error: HttpErrorResponse) => this.errors.push(error.error.message || `${error.statusText} : ${error.message}`));
    }else {
      this.errors.push('El solicitante del pago no puede validar el pago.');
    }
  }

  public validar2(pago: PagoFactura) {
    this.errors = [];
    if (pago.solicitante !== this.user.email && pago.revisor1 !== this.user.email ) {
      pago.revision2 = true;
      pago.revisor2 = this.user.email;
      this.paymentService.updatePaymentWithValidation(pago.folio, pago.id, pago)
      .subscribe(updatedPayment => pago = updatedPayment,
      (error: HttpErrorResponse) => this.errors.push(error.error.message || `${error.statusText} : ${error.message}`));
    }else {
      this.errors.push('El segundo revisor, no puede ser ni el solicitante ni el primer revisor.');
    }
  }


  openDialog(payment: PagoFactura) {
    this.errors = [];
    this.dialogService.open(ValidacionPagoComponent, {
      context: {
        pago: payment,
      },
    }).onClose.subscribe(pago => {
      if (pago !== undefined) {
        if (pago.revisor2 === undefined){
          pago.revisor2 = this.user.email;
        }else {
          pago.revisor1 = this.user.email;
        }
        this.paymentService.updatePaymentWithValidation(pago.folio,pago.id,pago).toPromise()
        .then( success => console.log(success), (error:HttpErrorResponse)=>this.errors.push(error.error.message || `${error.statusText} : ${error.message}`))
        .then(() => this.updateDataTable(this.page.number, this.page.size))
      }else {
        this.updateDataTable(this.page.number, this.page.size);
      }
      });
  }

  public redirectToCfdi(folio: string) {
    this.router.navigate([`./pages/promotor/precfdi/${folio}`])
  }

  
 
  

  

}
