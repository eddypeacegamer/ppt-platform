import { Component, OnInit, Input } from '@angular/core';
import { PagoDevolucion } from '../../../../models/pago-devolucion';
import { NbDialogRef } from '@nebular/theme';
import { HttpErrorResponse } from '@angular/common/http';
import { DevolutionData } from '../../../../@core/data/devolution-data';

@Component({
  selector: 'ngx-validacion-devolucion',
  templateUrl: './validacion-devolucion.component.html',
  styleUrls: ['./validacion-devolucion.component.scss'],
})
export class ValidacionDevolucionComponent implements OnInit {

  @Input() payment: PagoDevolucion;
  public errorMesage: string;
  public companiesCat = [];
  public cuentas = [];

  public formInfo: any = {rfc : '', empresa: '*', cuenta: '*', fechaPago: ''};

  constructor(protected ref: NbDialogRef<ValidacionDevolucionComponent>,
    private devolutionsService: DevolutionData) { }
  ngOnInit() {
    this.errorMesage = '';
  }

  exit() {
    this.ref.close();
  }

  onCompanySelected() {

  }

  onPaymentBankSelected() {

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
