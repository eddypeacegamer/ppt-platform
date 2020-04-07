import { Component, OnInit } from '@angular/core';
import { User, UsersData } from '../../../@core/data/users-data';
import { Factura } from '../../../models/factura/factura';
import { PagoFactura } from '../../../models/pago-factura';
import { Catalogo } from '../../../models/catalogos/catalogo';
import { Client } from '../../../models/client';
import { ClientsData } from '../../../@core/data/clients-data';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { DevolutionData } from '../../../@core/data/devolution-data';
import { PaymentsData } from '../../../@core/data/payments-data';
import { ActivatedRoute } from '@angular/router';
import { Devolucion } from '../../../models/devolucion';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'ngx-devolutions-adjustment',
  templateUrl: './devolutions-adjustment.component.html',
  styleUrls: ['./devolutions-adjustment.component.scss']
})
export class DevolutionsAdjustmentComponent implements OnInit {

  public folioParam: string;
  public fileInput: any = {};
  public user: User;
  public factura: Factura= new Factura();
  public pago: PagoFactura;
  public devoluciones: Devolucion[]= [];

  public formParams: any = {tab: 'CLIENTE', filename: ''};

  public refTypesCat: Catalogo[] = [];
  public messages: string[] = [];
  public successMessage: string;
  public clientInfo: Client = new Client();


  constructor(private clientsService: ClientsData,
    private invoiceService: InvoicesData,
    private userService: UsersData,
    private devolutionService: DevolutionData,
    private paymentservice: PaymentsData,
    private route: ActivatedRoute) {}


  ngOnInit() {
    this.messages = [];
    this.fileInput.value = '';
    this.userService.getUserInfo().then(user => this.user = user);
    this.route.paramMap.subscribe(route => {
      this.folioParam = route.get('folio');
      if (this.folioParam !== '*') {
        this.devolutionService.findDevolutionByFolioFact(this.folioParam)
          .subscribe(devs => this.devoluciones = devs.filter(d =>'D' === d.tipo));
        this.invoiceService.getInvoiceByFolio(this.folioParam)
            .subscribe( invoice => {
              this.clientsService.getClientByRFC(invoice.rfcRemitente).subscribe(client => this.clientInfo = client);
              if ( invoice.tipoDocumento === 'Complemento') {
                this.invoiceService.getInvoiceByFolio(invoice.folioPadre).subscribe(padre => {
                  this.factura = padre;
                  this.paymentservice.getPaymentsByFolio(padre.folio).toPromise()
                  .then(pagos =>
                    this.pago = pagos.filter(p => p.formaPago !== 'CREDITO').find(p => p.folio === this.folioParam));
                });
              }else {
                this.factura = invoice;
                this.paymentservice.getPaymentsByFolio(this.folioParam).toPromise()
                .then(pagos =>
                  this.pago = pagos.filter(p => p.formaPago !== 'CREDITO').find(p => p.folio === this.folioParam));
              }
            });
      } else {
        this.initVariables();
      }
    });
  }

  public updateAmmounts() {
    this.successMessage = undefined;
    this.messages = [];
    this.devolutionService.updateDevolutionByFolioFact(this.folioParam, this.devoluciones)
      .subscribe(devs =>{this.devoluciones = devs;
        this.successMessage = 'Se han actualizado la devoluciones correctamente'; },
      (error: HttpErrorResponse) =>
        this.messages.push(error.error.message || `${error.statusText} : ${error.message}`));
  }

  public initVariables() {
    /** INIT VARIABLES **/
    this.messages = [];
    this.fileInput.value = '';
  }

  public calculateTotal() {
    if (this.devoluciones.length > 0) {
      return this.devoluciones.map(d => d.monto).reduce((total, value) => total + value);
    }else {
      return 0;
    }
  }


}