import { Component, OnInit } from '@angular/core';

import { CatalogsData } from '../../../@core/data/catalogs-data';
import { GenericPage } from '../../../models/generic-page';
import {DownloadCsvService } from '../../../@core/back-services/download-csv.service'
import { InvoicesData } from '../../../@core/data/invoices-data';
import { PaymentsData } from '../../../@core/data/payments-data';
import { UsersData } from '../../../@core/data/users-data';
import { NbDialogService } from '@nebular/theme';
import { ValidacionPagoComponent } from './validacion-pago/validacion-pago.component';
import { Pago } from '../../../models/pago';

@Component({
  selector: 'ngx-pagos',
  templateUrl: './pagos.component.html',
  styleUrls: ['./pagos.component.scss']
})
export class PagosComponent implements OnInit {

  public userEmail : string;
  public paymentForm : any = {'payType':'*','status':'*','bank':'*','monto':0}

  public headers: string[] = ['Folio', 'Moneda', 'Banco', 'Monto','Estatus Pago','Tipo pago', 'Forma de pago', 'Fecha pago','Actualizado por'];
  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';

  constructor(
    private userService : UsersData,
    private paymentService : PaymentsData,
    private dialogService: NbDialogService
    ) {}

  ngOnInit() {
    this.updateDataTable();
    this.userService.getUserInfo().subscribe(user => this.userEmail = user.email);
  }

  public updateDataTable(currentPage?: number, pageSize?: number) {
    const pageValue = currentPage || 0;
    const sizeValue = pageSize || 10;
    this.paymentService.getAllPayments(pageValue,sizeValue)
      .subscribe((result:GenericPage<any>) => this.page = result);
  }

  public onChangePageSize(pageSize: number) {
    this.updateDataTable(this.page.number, pageSize);
  }

  openDialog(payment:Pago) {
    this.dialogService.open(ValidacionPagoComponent, {
      context: {
        pago: payment,
      },
    }).onClose.subscribe(pago => {
      console.log(pago);
      if(pago!=undefined){
        pago.ultimoUsuario = this.userEmail;
        this.paymentService.updatePayment(pago.folio,pago.id,pago).toPromise()
        .then(success=>console.log(success), error=>console.error(error))
        .then(()=>this.updateDataTable())
      }
      });
  }
 
  public onPaymentTypeSelected(event){
    console.log(event);
  }

  public onPaymentStatusSelected(event){
    console.log(event);
  }

  public onPaymentBankSelected(event){
    console.log(event);
  }

  

}
