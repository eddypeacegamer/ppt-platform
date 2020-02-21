import { Component, OnInit } from '@angular/core';
import { ClientsData } from '../../../@core/data/clients-data';
import { CompaniesData } from '../../../@core/data/companies-data';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { ActivatedRoute } from '@angular/router';
import { User } from '../../../@core/data/users-data';
import { Factura } from '../../../models/factura/factura';
import { PagoDevolucion } from '../../../models/pago-devolucion';

@Component({
  selector: 'ngx-devolution-preferences',
  templateUrl: './devolution-preferences.component.html',
  styleUrls: ['./devolution-preferences.component.scss'],
})
export class DevolutionPreferencesComponent implements OnInit {

  public folioParam: string;
  public user: User;
  public factura: Factura= new Factura();
  public filterParams: any = {};
  public cliente: PagoDevolucion = new PagoDevolucion;
  public promotor: PagoDevolucion = new PagoDevolucion;
  public contacto: PagoDevolucion = new PagoDevolucion;

  public messages: string[] = [];

  constructor(private clientsService: ClientsData,
    private companiesService: CompaniesData,
    private invoiceService: InvoicesData,
    private route: ActivatedRoute) {}

  ngOnInit() {
    this.route.paramMap.subscribe(route => {
      this.folioParam = route.get('folio');
      if (this.folioParam !== '*') {
        this.invoiceService.getInvoiceByFolio(this.folioParam)
            .subscribe( invoice => {this.factura = invoice;console.log(invoice)});
      } else {
        this.initVariables();
      }
    });
  }

  public initVariables() {
    /** INIT VARIABLES **/
  }


}
