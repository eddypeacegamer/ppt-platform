import { Component, OnInit, Input, TemplateRef } from '@angular/core';
import { Cfdi } from '../../../models/factura/cfdi';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { ClaveProductoServicio } from '../../../models/catalogos/producto-servicio';
import { ClaveUnidad } from '../../../models/catalogos/clave-unidad';
import { Concepto } from '../../../models/factura/concepto';
import { HttpErrorResponse } from '@angular/common/http';
import { CfdiValidatorService } from '../../../@core/util-services/cfdi-validator.service';
import { CfdiData } from '../../../@core/data/cfdi-data';
import { NbDialogService } from '@nebular/theme';

@Component({
  selector: 'ngx-conceptos',
  templateUrl: './conceptos.component.html',
  styleUrls: ['../../pages.component.scss'],
})
export class ConceptosComponent implements OnInit {

  @Input() cfdi: Cfdi;
  @Input() concepto: Concepto;
  @Input() allowEdit: Boolean;

  public formInfo = { prodServ: '*', unidad: '*', claveProdServ: ''};
  public prodServCat: ClaveProductoServicio[] = [];
  public claveUnidadCat: ClaveUnidad[] = [];

  public claveProdServMsg = '';
  public loading: boolean;

  constructor(
    private cfdiService: CfdiData,
    private catalogsService: CatalogsData,
    private cfdiValidator: CfdiValidatorService,
    private dialogService: NbDialogService ) { }

  ngOnInit() {
    this.loading = false;
    this.catalogsService.getClaveUnidadByName('').then(unidadCat => this.claveUnidadCat = unidadCat);
  }

  private showAlert(dialog: TemplateRef<any>, errors: string[]) {
    this.dialogService.open(dialog, { context: errors });
  }

  private showAlertHttpError(dialog: TemplateRef<any>, error: HttpErrorResponse) {
    this.dialogService.open(dialog, { context: [error.error.message || `${error.statusText} : ${error.message}`] });
  }


  public buscarClaveProductoServicio(claveProdServ: string) {
    const value = +claveProdServ;
    this.claveProdServMsg = '';
    if (isNaN(value)) {
      if (claveProdServ.length > 5) {
        this.catalogsService.getProductoServiciosByDescription(claveProdServ).then(claves => {
          this.prodServCat = claves;
          this.formInfo.prodServ = claves[0].clave.toString();
          this.concepto.claveProdServ = claves[0].clave.toString();
          this.concepto.descripcionCUPS = claves[0].descripcion;
        }, (error: HttpErrorResponse) =>
          this.claveProdServMsg = error.error.message || `${error.statusText} : ${error.message}`);
      }else {
        this.prodServCat = [];
      }
    } else {
      if (claveProdServ.length === 8) {
        this.catalogsService.getProductoServiciosByClave(claveProdServ).then(claves => {
          this.prodServCat = claves;
          this.formInfo.prodServ = claves[0].clave.toString();
          this.concepto.claveProdServ = claves[0].clave.toString();
          this.concepto.descripcionCUPS = claves[0].descripcion;
        }, (error: HttpErrorResponse) =>
        this.claveProdServMsg = error.error.message || `${error.statusText} : ${error.message}`);
      } else {
        if (claveProdServ.length < 8 && claveProdServ.length > 3) {
          this.claveProdServMsg = 'Complete los 8 digitos de la clave';
        }
        this.prodServCat = [];
      }
    }
  }

  openSatCatalog() {
    window.open('http://pys.sat.gob.mx/PyS/catPyS.aspx', "_blank");
  }


  onSelectUnidad(clave: string) {
    if (clave !== '*') {
      this.concepto.claveUnidad = clave;
      this.concepto.unidad = this.claveUnidadCat.find(u => u.clave === clave).nombre;
    }
  }

  public onClaveProdServSelected(clave: string) {
    this.concepto.claveProdServ = clave;
    this.concepto.descripcionCUPS = this.prodServCat.find(c => c.clave == clave).descripcion;
  }

  public removeConcepto(dialog: TemplateRef<any>, index: number) {
    if (this.cfdi.folio !== undefined) {
      this.cfdiService.deleteConcepto(this.cfdi.id, this.cfdi.conceptos[index].id)
      .subscribe((cfdi: Cfdi) => {
        this.cfdi.conceptos.splice(index, 1);
        this.cfdiValidator.calcularImportes(this.cfdi).then(c => this.cfdi.total = c.total);
      }, (error: HttpErrorResponse) => this.showAlertHttpError(dialog, error));
    } else {
      this.cfdi.conceptos.splice(index, 1);
      this.cfdiValidator.calcularImportes(this.cfdi)
        .then(c => this.cfdi.total = c.total,
        (error: HttpErrorResponse) => this.showAlertHttpError(dialog, error));
    }
  }

  updateConcepto(concepto: Concepto) {
    this.concepto = {... concepto};
    if (concepto.impuestos.length > 0 ) {
      this.concepto.iva = true;
    }
    if (concepto.retenciones.length > 0 ) {
      this.concepto.retencionFlag = true;
    }
    this.formInfo.unidad = concepto.claveUnidad;
    this.formInfo.claveProdServ = concepto.claveProdServ;
    this.buscarClaveProductoServicio(concepto.claveProdServ);
  }

  public agregarConcepto(dialog: TemplateRef<any>, id?: number) {
    const concepto = this.cfdiValidator.buildConcepto({... this.concepto});
    const errors = this.cfdiValidator.validarConcepto(concepto);

    if (errors.length === 0) {
      this.loading = true;
      if (this.cfdi.id !== undefined) {
        let promise;
        if (id === undefined) {
          promise = this.cfdiService.insertConcepto(this.cfdi.id, concepto).toPromise();
        } else {
          promise = this.cfdiService.updateConcepto(this.cfdi.id, id, concepto).toPromise();
        }
        promise.then((cfdi) => {
              this.formInfo.prodServ = '*';
              this.formInfo.unidad = '*';
              this.concepto = new Concepto();
            }, (error: HttpErrorResponse) => this.showAlertHttpError(dialog, error))
            .then(() => {
              this.cfdiService.getCfdiByFolio(this.cfdi.id)
              .subscribe((cfdi: Cfdi) => {this.cfdi = cfdi; },
              (error: HttpErrorResponse) => this.showAlertHttpError(dialog, error));
            }).then(() => this.loading = false);
      } else {
        this.cfdi.conceptos.push(concepto);
        this.cfdiValidator.calcularImportes(this.cfdi)
        .then(c => {this.cfdi.total = c.total; this.loading = false; },
        (error: HttpErrorResponse) => {
          this.loading = false; this.cfdi.conceptos.pop(); this.showAlertHttpError(dialog, error); });
        this.formInfo.prodServ = '*';
        this.formInfo.unidad = '*';
        this.concepto = new Concepto();
      }
    } else {
      this.showAlert(dialog, errors);
      this.formInfo.prodServ = '*';
      this.formInfo.unidad = '*';
      this.concepto = new Concepto();
    }
  }

  public calcularImportes(impuestos): number {
    if (impuestos.length > 0) {
      return impuestos.map(i => i.importe).reduce((total, value) => total + value);
    } else {
      return 0;
    }
  }

  public getTotalImpuestos(conceptos: Concepto[]): number {
   let impuestos = 0;
   for (const concepto of conceptos) {
     impuestos += this.calcularImportes(concepto.impuestos);
   }
   return impuestos;
  }

  public getTotalRetenciones(conceptos: Concepto[]): number {
    let retenciones = 0;
    for (const concepto of conceptos) {
      retenciones += this.calcularImportes(concepto.retenciones);
    }
    return retenciones;
   }

  public handleIvaInputChange() {
    if (!this.concepto.iva) this.concepto.retencionFlag = false;
  }

}
