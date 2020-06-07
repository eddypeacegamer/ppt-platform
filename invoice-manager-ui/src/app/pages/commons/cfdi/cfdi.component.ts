import { Component, OnInit, Input } from '@angular/core';
import { Cfdi } from '../../../models/factura/cfdi';
import { Pago } from '../../../models/factura/pago';
import { UsoCfdi } from '../../../models/catalogos/uso-cfdi';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { Catalogo } from '../../../models/catalogos/catalogo';
import { Concepto } from '../../../models/factura/concepto';

@Component({
  selector: 'ngx-cfdi',
  templateUrl: './cfdi.component.html',
  styleUrls: ['./cfdi.component.scss'],
})
export class CfdiComponent implements OnInit {


  @Input() cfdi: Cfdi;
  @Input() pagos: Pago[];
  @Input() allowEdit: Boolean;

  // auxiliar variables
  public newConcep: Concepto;
  public successMessage: string;
  public errorMessages: string[] = [];

  // catalogs
  public usoCfdiCat: UsoCfdi[] = [];
  public payTypeCat: Catalogo[] = [];

  constructor(
    private catalogsService: CatalogsData) { }

  ngOnInit() {
    //catalogs info
    this.catalogsService.getAllUsoCfdis().then(cat => this.usoCfdiCat = cat);
    this.catalogsService.getFormasPago().then(cat => this.payTypeCat = cat);

    this.errorMessages = [];
    this.newConcep = new Concepto();
  }


  onPayMethodSelected(clave: string) {
    this.catalogsService.getFormasPago(clave)
      .then(cat => {
        this.payTypeCat = cat;
        this.cfdi.formaPago = this.payTypeCat[0].id;
        if (clave === 'PPD') {
          this.cfdi.formaPago = '99';
        } else {
          this.cfdi.formaPago = '01';
        }
      });
  }

  onUsoCfdiSelected(clave: string) {
    this.cfdi.receptor.usoCfdi = clave;
  }

  onFormaDePagoSelected(clave: string) {
    this.cfdi.formaPago = clave;
  }

}
