import * as tslib_1 from "tslib";
import { Component, Input } from '@angular/core';
import { Cfdi } from '../../../models/factura/cfdi';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { Concepto } from '../../../models/factura/concepto';
import { NbDialogService } from '@nebular/theme';
import { CfdiValidatorService } from '../../../@core/util-services/cfdi-validator.service';
import { InvoicesData } from '../../../@core/data/invoices-data';
let ConceptosComponent = class ConceptosComponent {
    constructor(dialogService, invoiceService, catalogsService, cfdiValidator) {
        this.dialogService = dialogService;
        this.invoiceService = invoiceService;
        this.catalogsService = catalogsService;
        this.cfdiValidator = cfdiValidator;
        this.formInfo = { prodServ: '*', unidad: '*', claveProdServ: '' };
        this.conceptoMessages = [];
        this.prodServCat = [];
        this.claveUnidadCat = [];
    }
    ngOnInit() {
        this.catalogsService.getClaveUnidadByName('').subscribe(unidadCat => this.claveUnidadCat = unidadCat);
    }
    buscarClaveProductoServicio(claveProdServ) {
        this.conceptoMessages = [];
        const value = +claveProdServ;
        if (isNaN(value)) {
            if (claveProdServ.length > 5) {
                this.catalogsService.getProductoServiciosByDescription(claveProdServ).subscribe(claves => {
                    this.prodServCat = claves;
                    this.formInfo.prodServ = claves[0].clave.toString();
                    this.concepto.claveProdServ = claves[0].clave.toString();
                    this.concepto.descripcionCUPS = claves[0].descripcion;
                }, (error) => {
                    this.conceptoMessages = [];
                    this.conceptoMessages.push(error.error.message || `${error.statusText} : ${error.message}`);
                });
            }
        }
        else {
            if (claveProdServ.length > 5) {
                this.catalogsService.getProductoServiciosByClave(claveProdServ).subscribe(claves => {
                    this.prodServCat = claves;
                    this.formInfo.prodServ = claves[0].clave.toString();
                    this.concepto.claveProdServ = claves[0].clave.toString();
                    this.concepto.descripcionCUPS = claves[0].descripcion;
                }, (error) => {
                    this.conceptoMessages = [];
                    this.conceptoMessages.push(error.error.message || `${error.statusText} : ${error.message}`);
                });
            }
        }
    }
    openSatCatalog(dialog) {
        this.dialogService.open(dialog);
    }
    onSelectUnidad(clave) {
        if (clave !== '*') {
            this.concepto.claveUnidad = clave;
            this.concepto.unidad = this.claveUnidadCat.find(u => u.clave === clave).nombre;
        }
    }
    onClaveProdServSelected(clave) {
        this.concepto.claveProdServ = clave;
        this.concepto.descripcionCUPS = this.prodServCat.find(c => c.clave == clave).descripcion;
    }
    removeConcepto(index) {
        this.conceptoMessages = [];
        if (this.cfdi.folio !== undefined) {
            this.invoiceService.deleteConcepto(this.cfdi.folio, this.cfdi.conceptos[index].id)
                .subscribe((cfdi) => this.cfdi = cfdi, (error) => {
                this.conceptoMessages.push((error.error != null && error.error !== undefined)
                    ? error.error.message : `${error.statusText} : ${error.message}`);
            });
        }
        else {
            this.cfdi.conceptos.splice(index, 1);
            this.cfdi = this.cfdiValidator.calcularImportes(this.cfdi);
        }
    }
    updateConcepto(concepto) {
        this.concepto = Object.assign({}, concepto);
        if (concepto.impuestos.length > 0) {
            this.concepto.iva = true;
        }
        this.formInfo.unidad = concepto.claveUnidad;
        this.formInfo.claveProdServ = concepto.claveProdServ;
        this.buscarClaveProductoServicio(concepto.claveProdServ);
    }
    agregarConcepto(id) {
        this.conceptoMessages = [];
        const concepto = this.cfdiValidator.buildConcepto(Object.assign({}, this.concepto));
        this.conceptoMessages = this.cfdiValidator.validarConcepto(concepto);
        if (this.conceptoMessages.length === 0) {
            if (this.cfdi.folio !== undefined) {
                let promise;
                if (id === undefined) {
                    promise = this.invoiceService.insertConcepto(this.cfdi.folio, concepto).toPromise();
                }
                else {
                    promise = this.invoiceService.updateConcepto(this.cfdi.folio, id, concepto).toPromise();
                }
                promise.then((cfdi) => {
                    this.formInfo.prodServ = '*';
                    this.formInfo.unidad = '*';
                    this.concepto = new Concepto();
                }, (error) => {
                    this.conceptoMessages.push((error.error != null && error.error !== undefined)
                        ? error.error.message : `${error.statusText} : ${error.message}`);
                }).then(() => {
                    this.invoiceService.getCfdiByFolio(this.cfdi.folio)
                        .subscribe((cfdi) => this.cfdi = cfdi, (error) => {
                        this.conceptoMessages.push((error.error != null && error.error !== undefined)
                            ? error.error.message : `${error.statusText} : ${error.message}`);
                    });
                });
            }
            else {
                this.cfdi.conceptos.push(concepto);
                this.cfdi = this.cfdiValidator.calcularImportes(this.cfdi);
                this.formInfo.prodServ = '*';
                this.formInfo.unidad = '*';
                this.concepto = new Concepto();
            }
        }
        else {
            this.formInfo.prodServ = '*';
            this.formInfo.unidad = '*';
            this.concepto = new Concepto();
        }
    }
    getImporteImpuestos(impuestos) {
        if (impuestos.length > 0) {
            return impuestos.map(i => i.importe).reduce((total, value) => total + value);
        }
        else {
            return 0;
        }
    }
};
tslib_1.__decorate([
    Input(),
    tslib_1.__metadata("design:type", Cfdi)
], ConceptosComponent.prototype, "cfdi", void 0);
tslib_1.__decorate([
    Input(),
    tslib_1.__metadata("design:type", Concepto)
], ConceptosComponent.prototype, "concepto", void 0);
tslib_1.__decorate([
    Input(),
    tslib_1.__metadata("design:type", Boolean)
], ConceptosComponent.prototype, "allowEdit", void 0);
ConceptosComponent = tslib_1.__decorate([
    Component({
        selector: 'ngx-conceptos',
        templateUrl: './conceptos.component.html',
        styleUrls: ['./conceptos.component.scss']
    }),
    tslib_1.__metadata("design:paramtypes", [NbDialogService,
        InvoicesData,
        CatalogsData,
        CfdiValidatorService])
], ConceptosComponent);
export { ConceptosComponent };
//# sourceMappingURL=conceptos.component.js.map