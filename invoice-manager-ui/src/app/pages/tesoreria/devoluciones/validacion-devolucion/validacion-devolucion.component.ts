import { Component, OnInit, Input } from '@angular/core';
import { PagoDevolucion } from '../../../../models/pago-devolucion';
import { NbDialogRef } from '@nebular/theme';
import { HttpErrorResponse } from '@angular/common/http';
import { DevolutionData } from '../../../../@core/data/devolution-data';
import { map } from 'rxjs/operators';
import { GenericPage } from '../../../../models/generic-page';
import { Empresa } from '../../../../models/empresa';
import { Contribuyente } from '../../../../models/contribuyente';
import { CuentasData } from '../../../../@core/data/cuentas-data';

@Component({
  selector: 'ngx-validacion-devolucion',
  templateUrl: './validacion-devolucion.component.html',
  styleUrls: ['./validacion-devolucion.component.scss'],
})
export class ValidacionDevolucionComponent implements OnInit {

  @Input() payment: PagoDevolucion;
  public errorMesage: string;
  public cuentas = [];

  public formInfo: any = {rfc : '', empresa: '*', cuenta: '*', fechaPago: ''};

  constructor(protected ref: NbDialogRef<ValidacionDevolucionComponent>,
    private devolutionsService: DevolutionData,
    private accountsService: CuentasData) { }
  ngOnInit() {
    this.errorMesage = '';
    this.getAccountInfo();
  }


  exit() {
    this.ref.close();
  }

  onCompanySelected() {

  }

  onPaymentBankSelected() {

  }

  companySearch(rfc: string) {
   if (rfc !== undefined && rfc.length >= 3) {
    this.getAccountInfo(rfc);
   }
   if (rfc !== undefined && rfc.length === 0) {
    this.getAccountInfo();
   }
  }
  private getAccountInfo(rfc?: string) {
    this.accountsService.getAllCuentas(0, 25, {empresa: rfc || ''})
    .subscribe(accounts => {
      this.cuentas = accounts.content;
      if ( !accounts.empty) {
        this.formInfo.cuenta = this.cuentas[0].id; }
      });
  }

  acceptDevolution() {
    this.errorMesage = '';
    const solicitud = {... this.payment};
    solicitud.status = 'RECHAZADO';
    this.devolutionsService.updateDevolution(this.payment.id, solicitud)
      .subscribe(success => this.ref.close(),
      (error: HttpErrorResponse) => this.errorMesage = error.error.message || `${error.statusText} : ${error.message}`);
  }

  rejectDevolution() {
    this.errorMesage = '';
    const solicitud = {... this.payment};
    solicitud.status = 'ACEPTADO';
    this.devolutionsService.updateDevolution(this.payment.id, solicitud)
      .subscribe(success => this.ref.close(),
      (error: HttpErrorResponse) => this.errorMesage = error.error.message || `${error.statusText} : ${error.message}`);
  }

}
