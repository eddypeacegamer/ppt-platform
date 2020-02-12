import { Component, OnInit, Input } from '@angular/core';
import { Pago } from '../../../../models/pago';
import { SafeUrl } from '@angular/platform-browser';
import { NbDialogRef } from '@nebular/theme';
import { DevolutionData } from '../../../../@core/data/devolution-data';
import { Devolucion } from '../../../../models/devolucion';
import { PaymentsData } from '../../../../@core/data/payments-data';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'ngx-solicitud-devolucion',
  templateUrl: './solicitud-devolucion.component.html',
  styleUrls: ['./solicitud-devolucion.component.scss']
})
export class SolicitudDevolucionComponent implements OnInit {

  
  @Input() pago: Pago;
  public comprobanteUrl:SafeUrl;
  public devoluciones:Devolucion[]=[];
  public errorMesage : string;
  
  constructor(protected ref: NbDialogRef<SolicitudDevolucionComponent>,
    private devolutionsService : DevolutionData,
    private router: Router) { }

  ngOnInit() {
    this.errorMesage = undefined;
    //this.devolutionsService.getDevolutionsByPayment(this.pago.id)
      //.subscribe(devolutions=>this.devoluciones = devolutions);

  }

  exit() {
    this.ref.close();
  }

  updatePaymentStatus(){
    this.errorMesage = undefined;
    this.pago.statusPago='PAGADO';
    /*this.devolutionsService.updateDevolutionAsPaid(this.pago)
      .subscribe(success =>{console.log(success); this.ref.close();},
      (error: HttpErrorResponse) => this.errorMesage = error.error.message || `${error.statusText} : ${error.message}`)*/
  }

  public redirectToCfdi(folio:string){
    this.router.navigate([`./pages/promotor/precfdi/${folio}`])
  }

}
