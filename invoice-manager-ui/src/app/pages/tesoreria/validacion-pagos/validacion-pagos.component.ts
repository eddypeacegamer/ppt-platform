import { Component, OnInit } from '@angular/core';

import { GenericPage } from '../../../models/generic-page';
import {DownloadCsvService } from '../../../@core/util-services/download-csv.service'
import { PaymentsData } from '../../../@core/data/payments-data';
import { UsersData } from '../../../@core/data/users-data';
import { NbDialogService } from '@nebular/theme';
import { ValidacionPagoComponent } from './validacion-pago/validacion-pago.component';
import { Pago } from '../../../models/pago';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'ngx-valiudacion-pagos',
  templateUrl: './validacion-pagos.component.html',
  styleUrls: ['./validacion-pagos.component.scss']
})
export class ValidacionPagosComponent implements OnInit {

  public userEmail : string;
  public filterParams: any = { formaPago: '*', status: 'VALIDACION', banco: '*', since: '', to: '' };
  public errors : string[]=[];
  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';

  constructor(
    private userService : UsersData,
    private paymentService : PaymentsData,
    private donwloadService: DownloadCsvService,
    private router: Router,
    private dialogService: NbDialogService
    ) {}

  ngOnInit() {
    this.updateDataTable();
    this.filterParams= { formaPago: '*', status: 'VALIDACION', banco: '*', since: '', to: '' };
    this.userService.getUserInfo().subscribe(user => this.userEmail = user.email);
  }

  public updateDataTable(currentPage?: number, pageSize?: number) {
    const pageValue = currentPage || 0;
    const sizeValue = pageSize || 10;
    this.paymentService.getIncomes(pageValue,sizeValue, this.filterParams)
      .subscribe((result:GenericPage<any>) => this.page = result);
  }

  public onChangePageSize(pageSize: number) {
    this.updateDataTable(this.page.number, pageSize);
  }

  public downloadHandler() {
    this.paymentService.getIncomes(0, 10000, this.filterParams).subscribe(result => {
      this.donwloadService.exportCsv(result.content, 'Pagos')
    });
  }

  openDialog(payment:Pago) {
    this.errors =[];
    this.dialogService.open(ValidacionPagoComponent, {
      context: {
        pago: payment,
      },
    }).onClose.subscribe(pago => {
      if(pago!=undefined){
        pago.ultimoUsuario = this.userEmail;
        this.paymentService.updatePaymentWithValidation(pago.folio,pago.id,pago).toPromise()
        .then(success=>console.log(success), (error:HttpErrorResponse)=>this.errors.push(error.error.message || `${error.statusText} : ${error.message}`))
        .then(()=>this.updateDataTable(this.page.number, this.page.size))
      }else{
        this.updateDataTable(this.page.number, this.page.size);
      }
      });
  }

  public redirectToCfdi(folio:string){
    this.router.navigate([`./pages/promotor/precfdi/${folio}`])
  }

  
 
  

  

}
