import { Component, OnInit, Input, TemplateRef } from '@angular/core';
import { Cfdi } from '../../../models/factura/cfdi';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { ClaveProductoServicio } from '../../../models/catalogos/producto-servicio';
import { ClaveUnidad } from '../../../models/catalogos/clave-unidad';
import { Concepto } from '../../../models/factura/concepto';
import { HttpErrorResponse } from '@angular/common/http';
import { NbDialogService } from '@nebular/theme';
import { CfdiValidatorService } from '../../../@core/util-services/cfdi-validator.service';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { Impuesto } from '../../../models/factura/impuesto';

@Component({
  selector: 'ngx-conceptos',
  templateUrl: './conceptos.component.html',
  styleUrls: ['./conceptos.component.scss']
})
export class ConceptosComponent implements OnInit {

  @Input() cfdi: Cfdi;
  @Input() concepto: Concepto;
  @Input() allowEdit: Boolean;

  public formInfo = { prodServ: '*', unidad: '*', claveProdServ: ''};
  public conceptoMessages: string[] = [];
  public prodServCat: ClaveProductoServicio[] = [];
  public claveUnidadCat: ClaveUnidad[] = [];

  constructor(private dialogService: NbDialogService,
    private invoiceService: InvoicesData,
    private catalogsService: CatalogsData,
    private cfdiValidator: CfdiValidatorService ) { }

  ngOnInit() {
  }


  public buscarClaveProductoServicio(claveProdServ: string) {
    this.conceptoMessages = [];
    const value = +claveProdServ;
    if (isNaN(value)) {
      if (claveProdServ.length > 5) {
        this.catalogsService.getProductoServiciosByDescription(claveProdServ).subscribe(claves => {
          this.prodServCat = claves;
          this.formInfo.prodServ = claves[0].clave.toString();
          this.concepto.claveProdServ = claves[0].clave.toString();
          this.concepto.descripcionCUPS = claves[0].descripcion;
        }, (error: HttpErrorResponse) => {
          this.conceptoMessages = [];
          this.conceptoMessages.push(error.error.message || `${error.statusText} : ${error.message}`)
        });
      }
    } else {
      if (claveProdServ.length > 5) {
        this.catalogsService.getProductoServiciosByClave(claveProdServ).subscribe(claves => {
          this.prodServCat = claves;
          this.formInfo.prodServ = claves[0].clave.toString();
          this.concepto.claveProdServ = claves[0].clave.toString();
          this.concepto.descripcionCUPS = claves[0].descripcion;
        }, (error: HttpErrorResponse) => {
          this.conceptoMessages = [];
          this.conceptoMessages.push(error.error.message || `${error.statusText} : ${error.message}`);
          });
      }
    }
  }

  openSatCatalog(dialog: TemplateRef<any>) {
    this.dialogService.open(dialog);
  }


  onSelectUnidad(clave: string) {
    if (clave != '*') {
      this.concepto.claveUnidad = clave;
      this.concepto.unidad = this.claveUnidadCat.find(u => u.clave === clave).nombre;
    }
  }

  onClaveProdServSelected(clave: string) {
    this.concepto.claveProdServ = clave;
    this.concepto.descripcionCUPS = this.prodServCat.find(c => c.clave === clave).descripcion;
  }



  removeConcepto(index: number) {
    this.conceptoMessages = [];
    if (this.cfdi.folio !== undefined) {
      this.invoiceService.deleteConcepto(this.cfdi.folio, this.cfdi.conceptos[index].id)
      .subscribe((cfdi: Cfdi) => this.cfdi = cfdi,
      (error: HttpErrorResponse) => { this.conceptoMessages.push((error.error != null && error.error !== undefined)
          ? error.error.message : `${error.statusText} : ${error.message}`)});
    }else {
      this.cfdi.conceptos.splice(index, 1);
      this.cfdi = this.cfdiValidator.calcularImportes(this.cfdi);
    }
  }

  updateConcepto(concepto: Concepto) {
    this.concepto = {... concepto};
    if (concepto.impuestos.length > 0 ) {
      this.concepto.iva = true;
    }
    this.formInfo.unidad = concepto.claveUnidad;
    this.formInfo.claveProdServ = concepto.claveProdServ;
    this.buscarClaveProductoServicio(concepto.claveProdServ);
  }
  agregarConcepto(id?: number) {
    this.conceptoMessages = [];
    const concepto = this.cfdiValidator.buildConcepto({... this.concepto});
    this.conceptoMessages = this.cfdiValidator.validarConcepto(concepto);
    if (this.conceptoMessages.length === 0) {
      if (this.cfdi.folio !== undefined) {
        let promise;
        if (id === undefined) {
          promise = this.invoiceService.insertConcepto(this.cfdi.folio, concepto).toPromise();
        }else {
          promise = this.invoiceService.updateConcepto(this.cfdi.folio, id, concepto).toPromise();
        }
        promise.then((cfdi) => {
              this.formInfo.prodServ = '*';
              this.formInfo.unidad = '*';
              this.concepto = new Concepto();
            }, (error: HttpErrorResponse) => {
              this.conceptoMessages.push((error.error != null && error.error !== undefined)
                ? error.error.message : `${error.statusText} : ${error.message}`);
            }).then(() => {
              this.invoiceService.getCfdiByFolio(this.cfdi.folio)
              .subscribe((cfdi: Cfdi) => this.cfdi = cfdi,
              (error: HttpErrorResponse) => { this.conceptoMessages.push((error.error != null && error.error !== undefined) 
                  ? error.error.message : `${error.statusText} : ${error.message}`)});
            });
      }else {
        this.cfdi.conceptos.push(concepto);
        this.cfdi = this.cfdiValidator.calcularImportes(this.cfdi);
        this.formInfo.prodServ = '*';
        this.formInfo.unidad = '*';
        this.concepto = new Concepto();
      }
    }else {
      this.formInfo.prodServ = '*';
      this.formInfo.unidad = '*';
      this.concepto = new Concepto();
    }
  }

  public getImporteImpuestos(impuestos: Impuesto[]): number {
    if (impuestos.length > 0) {
      return impuestos.map(i => i.importe).reduce((total, value) => total + value);
    } else {
      return 0;
    }
  }

}
