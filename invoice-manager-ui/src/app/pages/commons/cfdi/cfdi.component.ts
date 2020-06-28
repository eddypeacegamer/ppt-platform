import { Component, OnInit, Input } from '@angular/core';
import { Cfdi } from '../../../models/factura/cfdi';
import { Pago } from '../../../models/factura/pago';
import { UsoCfdi } from '../../../models/catalogos/uso-cfdi';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { Catalogo } from '../../../models/catalogos/catalogo';
import { Concepto } from '../../../models/factura/concepto';
import { CfdiData } from '../../../@core/data/cfdi-data';
import { HttpErrorResponse } from '@angular/common/http';
import { Factura } from '../../../models/factura/factura';

@Component({
  selector: 'ngx-cfdi',
  templateUrl: './cfdi.component.html',
  styleUrls: ['./cfdi.component.scss'],
})
export class CfdiComponent implements OnInit {


  @Input() cfdi: Cfdi;
  @Input() pagos: Pago[];
  @Input() allowEdit: Boolean;
  @Input() factura?: Factura;

  public loading: boolean = false;

  // auxiliar variables
  public newConcep: Concepto;
  public successMessage: string;
  public errorMessages: string[] = [];

  // catalogs
  public usoCfdiCat: UsoCfdi[] = [];
  public payTypeCat: Catalogo[] = [];

  constructor(
    private catalogsService: CatalogsData,
    private cfdiservice: CfdiData) { }

  ngOnInit() {
    //catalogs info
    this.catalogsService.getAllUsoCfdis().then(cat => this.usoCfdiCat = cat);
    this.catalogsService.getFormasPago().then(cat => this.payTypeCat = cat);
    this.errorMessages = [];
    this.successMessage = undefined;
    this.newConcep = new Concepto();
    this.loading = false;
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

  updateCfdi() {
    this.loading = true;
    this.errorMessages = [];
    this.successMessage = undefined;
    this.cfdiservice.updateCfdi(this.cfdi)
      .subscribe(cfdi => {
        this.cfdi = cfdi;
        this.loading = false;
        this.successMessage = "CFDI actualizado correctamente";
      } , (error: HttpErrorResponse) => {
        this.loading = false;
        this.errorMessages.push((error.error != null && error.error !== undefined) ?
          error.error.message : `${error.statusText} : ${error.message}`);
      });
  }

}
