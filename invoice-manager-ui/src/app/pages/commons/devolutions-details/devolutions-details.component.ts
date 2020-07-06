import { Component, OnInit } from '@angular/core';
import { UsersData } from '../../../@core/data/users-data';
import { Factura } from '../../../models/factura/factura';
import { PagoBase } from '../../../models/pago-base';
import { Catalogo } from '../../../models/catalogos/catalogo';
import { Client } from '../../../models/client';
import { ClientsData } from '../../../@core/data/clients-data';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { DevolutionData } from '../../../@core/data/devolution-data';
import { PaymentsData } from '../../../@core/data/payments-data';
import { ActivatedRoute, Router } from '@angular/router';
import { Devolucion } from '../../../models/devolucion';
import { HttpErrorResponse } from '@angular/common/http';
import { PagoDevolucion } from '../../../models/pago-devolucion';
import { GenericPage } from '../../../models/generic-page';
import { User } from '../../../models/user';

@Component({
  selector: 'ngx-devolutions-details',
  templateUrl: './devolutions-details.component.html',
  styleUrls: ['./devolutions-details.component.scss']
})
export class DevolutionsDetailsComponent implements OnInit {

  public folioParam: string;
  public module: string = 'promotor';
  public fileInput: any = {};
  public user: User;
  public factura: Factura= new Factura();
  public pago: PagoBase;
  public devoluciones: Devolucion[]= [];
  public pageDevolutions: GenericPage<PagoDevolucion> = new GenericPage();

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
    private route: ActivatedRoute,
    private router: Router) {}


  ngOnInit() {
    this.module = this.router.url.split('/')[2];
    this.messages = [];
    this.fileInput.value = '';
    this.userService.getUserInfo().then(user => this.user = user);
    this.route.paramMap.subscribe(route => {
      this.folioParam = route.get('folio');
      if (this.folioParam !== '*') {
        this.devolutionService.findDevolutionByFolioFact(this.folioParam)
          .subscribe(devs => this.devoluciones = devs.filter(d => 'D' === d.tipo));
        this.devolutionService.findDevolutionsRequests(0, 1, { folio: this.folioParam})
          .subscribe(page => this.pageDevolutions = page);
        /*this.invoiceService.getInvoiceByFolio(this.folioParam)
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
            });*/
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
