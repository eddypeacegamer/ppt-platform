import * as tslib_1 from "tslib";
import { Component, Input } from '@angular/core';
import { NbDialogRef } from '@nebular/theme';
import { Transferencia } from '../../../../models/transferencia';
import { Concepto } from '../../../../models/factura/concepto';
import { Factura } from '../../../../models/factura/factura';
import { CatalogsData } from '../../../../@core/data/catalogs-data';
import { CompaniesData } from '../../../../@core/data/companies-data';
import { InvoicesData } from '../../../../@core/data/invoices-data';
import { UsersData } from '../../../../@core/data/users-data';
import { Cfdi } from '../../../../models/factura/cfdi';
import { map } from 'rxjs/operators';
import { CfdiValidatorService } from '../../../../@core/util-services/cfdi-validator.service';
let InvoiceRequestComponent = class InvoiceRequestComponent {
    constructor(ref, catalogsService, companiesService, invoiceService, cfdiValidator, userService) {
        this.ref = ref;
        this.catalogsService = catalogsService;
        this.companiesService = companiesService;
        this.invoiceService = invoiceService;
        this.cfdiValidator = cfdiValidator;
        this.userService = userService;
        this.girosCat = [];
        this.companiesCat = [];
        this.usoCfdiCat = [];
        this.validationCat = [];
        this.payCat = [];
        this.devolutionCat = [];
        this.complementos = [];
        this.errorMessages = [];
        this.formInfo = { clientRfc: '', companyRfc: '', giro: '*', empresa: '*', usoCfdi: '*', payType: '*' };
        this.loading = false;
    }
    ngOnInit() {
        this.userService.getUserInfo().subscribe(user => this.user = user);
        this.initVariables();
        this.companiesService.getCompanyByRFC(this.transfer.rfcRetiro).subscribe(emisor => {
            this.factura.rfcEmisor = emisor.informacionFiscal.rfc;
            this.factura.razonSocialEmisor = emisor.informacionFiscal.razonSocial;
            this.factura.cfdi.emisor.rfc = emisor.informacionFiscal.rfc;
            this.factura.cfdi.emisor.nombre = emisor.informacionFiscal.razonSocial;
            this.factura.cfdi.emisor.regimenFiscal = emisor.regimenFiscal;
        }, (error) => this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
        this.companiesService.getCompanyByRFC(this.transfer.rfcDeposito).subscribe(receptor => {
            this.factura.rfcRemitente = receptor.informacionFiscal.rfc;
            this.factura.razonSocialRemitente = receptor.informacionFiscal.razonSocial;
            this.factura.cfdi.receptor.rfc = receptor.informacionFiscal.rfc;
            this.factura.cfdi.receptor.nombre = receptor.informacionFiscal.razonSocial;
        }, (error) => this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
        this.catalogsService.getInvoiceCatalogs().subscribe(results => {
            this.girosCat = results[0];
            this.usoCfdiCat = results[2];
            this.payCat = results[3];
            this.devolutionCat = results[4];
            this.validationCat = results[5];
            this.payTypeCat = results[6];
        });
    }
    ngOnDestroy() {
        /** CLEAN VARIABLES **/
        this.newConcep = new Concepto();
        this.factura = new Factura();
    }
    initVariables() {
        /** INIT VARIABLES **/
        this.newConcep = new Concepto();
        this.factura = new Factura();
        this.errorMessages = [];
        this.loading = false;
        this.factura.cfdi.moneda = 'MXN';
        this.factura.cfdi.metodoPago = '*';
    }
    exit() {
        this.ref.close();
    }
    validarRestante() {
        if (Math.abs(this.transfer.importe - this.factura.cfdi.total) < 0.01 && this.factura.folio === undefined) {
            this.successMessage = 'Muy bien el monto de la factura se cuadro correctamente';
            return true;
        }
        else {
            return false;
        }
    }
    calcularPrecioUnitario(concepto) {
        if (concepto.cantidad < 1) {
            alert('NO es posible calcular montos unitarios para valores menores a 1');
        }
        else {
            const restante = this.transfer.importe - this.factura.cfdi.total;
            if (concepto.iva === true) {
                concepto.valorUnitario = restante / (1.16 * concepto.cantidad);
            }
            else {
                concepto.valorUnitario = restante / concepto.cantidad;
            }
        }
    }
    getInvoiceByFolio(folio) {
        this.invoiceService.getInvoiceByFolio(folio).pipe(map((fac) => {
            fac.cfdi.receptor.usoCfdi = this.usoCfdiCat.find(u => u.clave === fac.cfdi.receptor.usoCfdi).descripcion;
            fac.statusFactura = this.validationCat.find(v => v.id === fac.statusFactura).nombre;
            fac.statusPago = this.payCat.find(v => v.id === fac.statusPago).nombre;
            fac.statusDevolucion = this.devolutionCat.find(v => v.id === fac.statusDevolucion).nombre;
            fac.cfdi.formaPago = this.payTypeCat.find(v => v.id === fac.cfdi.formaPago).nombre;
            return fac;
        })).subscribe(invoice => {
            this.factura = invoice;
            if (invoice.cfdi.metodoPago === 'PPD') {
                this.invoiceService.getComplementosInvoice(folio)
                    .pipe(map((facturas) => {
                    return facturas.map(record => {
                        record.statusFactura = this.validationCat.find(v => v.id === record.statusFactura).nombre;
                        record.statusPago = this.payCat.find(v => v.id === record.statusPago).nombre;
                        record.statusDevolucion = this.devolutionCat.find(v => v.id === record.statusDevolucion).nombre;
                        record.cfdi.formaPago = this.payTypeCat.find(v => v.id === record.cfdi.formaPago).nombre;
                        return record;
                    });
                }))
                    .subscribe(complementos => this.complementos = complementos);
            }
        }, error => {
            console.error('Info cant found, creating a new invoice:', error);
            this.initVariables();
        });
    }
    onGiroSelection(giroId) {
        const value = +giroId;
        if (isNaN(value)) {
            this.companiesCat = [];
        }
        else {
            this.companiesService.getCompaniesByLineaAndGiro('A', Number(giroId))
                .subscribe(companies => this.companiesCat = companies, (error) => this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
        }
    }
    onCompanySelected(companyId) {
        this.companyInfo = this.companiesCat.find(c => c.id === Number(companyId));
    }
    onPayMethodSelected(clave) {
        this.catalogsService.getFormasPago(clave)
            .subscribe(cat => {
            this.payTypeCat = cat;
            this.formInfo.payType = this.payTypeCat[0].id;
            if (clave === 'PPD') {
                this.factura.cfdi.formaPago = '99';
                this.factura.cfdi.metodoPago = 'PPD';
            }
            else {
                this.factura.cfdi.metodoPago = 'PUE';
                this.factura.cfdi.formaPago = '01';
            }
        });
    }
    onUsoCfdiSelected(clave) {
        this.factura.cfdi.receptor.usoCfdi = clave;
    }
    onFormaDePagoSelected(clave) {
        this.factura.cfdi.formaPago = clave;
    }
    limpiarForma() {
        this.initVariables();
        this.clientInfo = undefined;
        this.companyInfo = undefined;
        this.successMessage = undefined;
        this.newConcep = new Concepto();
        this.factura = new Factura();
        this.factura.cfdi = new Cfdi();
        this.factura.cfdi.conceptos = [];
        this.errorMessages = [];
    }
    solicitarCfdi() {
        this.errorMessages = [];
        this.successMessage = undefined;
        this.factura.lineaEmisor = this.transfer.lineaRetiro;
        this.factura.lineaRemitente = this.transfer.lineaDeposito;
        this.factura.statusFactura = '4'; //SET AS DEFAULT POR TIMBRAR
        this.factura.solicitante = this.user.email;
        this.factura.metodoPago = this.factura.cfdi.metodoPago;
        this.errorMessages = this.cfdiValidator.validarCfdi(Object.assign({}, this.factura.cfdi));
        if (this.errorMessages.length < 1) {
            this.invoiceService.insertNewInvoice(Object.assign({}, this.factura))
                .subscribe((invoice) => {
                this.factura.folio = invoice.folio;
                this.successMessage = 'Solicitud de factura enviada correctamente';
            }, (error) => {
                this.errorMessages.push((error.error != null && error.error !== undefined) ?
                    error.error.message : `${error.statusText} : ${error.message}`);
            });
        }
    }
};
tslib_1.__decorate([
    Input(),
    tslib_1.__metadata("design:type", Transferencia)
], InvoiceRequestComponent.prototype, "transfer", void 0);
InvoiceRequestComponent = tslib_1.__decorate([
    Component({
        selector: 'ngx-invoice-request',
        templateUrl: './invoice-request.component.html',
        styleUrls: ['./invoice-request.component.scss']
    }),
    tslib_1.__metadata("design:paramtypes", [NbDialogRef,
        CatalogsData,
        CompaniesData,
        InvoicesData,
        CfdiValidatorService,
        UsersData])
], InvoiceRequestComponent);
export { InvoiceRequestComponent };
//# sourceMappingURL=invoice-request.component.js.map