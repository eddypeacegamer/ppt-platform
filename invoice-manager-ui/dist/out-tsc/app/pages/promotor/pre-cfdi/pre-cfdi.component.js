import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { ClientsData } from '../../../@core/data/clients-data';
import { CompaniesData } from '../../../@core/data/companies-data';
import { Concepto } from '../../../models/factura/concepto';
import { Cfdi } from '../../../models/factura/cfdi';
import { Factura } from '../../../models/factura/factura';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { ActivatedRoute } from '@angular/router';
import { map } from 'rxjs/operators';
import { DownloadInvoiceFilesService } from '../../../@core/util-services/download-invoice-files';
import { UsersData } from '../../../@core/data/users-data';
import { FilesData } from '../../../@core/data/files-data';
import { PdfMakeService } from '../../../@core/util-services/pdf-make.service';
import { CfdiValidatorService } from '../../../@core/util-services/cfdi-validator.service';
let PreCfdiComponent = class PreCfdiComponent {
    constructor(catalogsService, clientsService, companiesService, invoiceService, cfdiValidator, userService, filesService, downloadService, pdfMakeService, route) {
        this.catalogsService = catalogsService;
        this.clientsService = clientsService;
        this.companiesService = companiesService;
        this.invoiceService = invoiceService;
        this.cfdiValidator = cfdiValidator;
        this.userService = userService;
        this.filesService = filesService;
        this.downloadService = downloadService;
        this.pdfMakeService = pdfMakeService;
        this.route = route;
        this.girosCat = [];
        this.companiesCat = [];
        this.usoCfdiCat = [];
        this.validationCat = [];
        this.payCat = [];
        this.devolutionCat = [];
        this.factura = new Factura();
        this.errorMessages = [];
        this.formInfo = { clientRfc: '', companyRfc: '', giro: '*', empresa: '*', usoCfdi: '*', payType: '*' };
        this.loading = false;
    }
    ngOnInit() {
        this.userService.getUserInfo().subscribe(user => this.user = user);
        this.initVariables();
        this.catalogsService.getInvoiceCatalogs()
            .toPromise().then(results => {
            this.girosCat = results[0];
            this.usoCfdiCat = results[2];
            this.payCat = results[3];
            this.devolutionCat = results[4];
            this.validationCat = results[5];
            this.payTypeCat = results[6];
        }).then(() => {
            this.route.paramMap.subscribe(route => {
                this.folioParam = route.get('folio');
                if (this.folioParam !== '*') {
                    this.getInvoiceByFolio(this.folioParam);
                }
                else {
                    this.initVariables();
                }
            });
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
                        //record.cfdi.formaPago = this.payTypeCat.find(v => v.id === record.cfdi.formaPago).nombre;
                        return record;
                    });
                }))
                    .subscribe(complementos => this.factura.complementos = complementos);
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
                this.factura.metodoPago = 'PPD';
            }
            else {
                this.factura.metodoPago = 'PUE';
                this.factura.cfdi.metodoPago = 'PUE';
                this.factura.cfdi.formaPago = '01';
            }
        });
    }
    buscarClientInfo() {
        this.errorMessages = [];
        this.clientsService.getClientByRFC(this.formInfo.clientRfc).subscribe((client) => {
            if (client.activo === true) {
                this.clientInfo = client.informacionFiscal;
            }
            else {
                alert(`El cliente ${client.informacionFiscal.razonSocial} no se encuentra activo en el sistema`);
                this.formInfo.clientRfc = '';
            }
        }, (error) => this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
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
        this.factura.solicitante = this.user.email;
        this.factura.lineaEmisor = 'A';
        this.factura.lineaRemitente = 'CLIENTE';
        if (this.clientInfo === undefined || this.clientInfo.rfc === undefined) {
            this.errorMessages.push('La informacion del cliente es insuficiente o no esta presente.');
        }
        else if (this.companyInfo === undefined || this.companyInfo.informacionFiscal === undefined) {
            this.errorMessages.push('La informacion de la empresa es insuficiente o no esta presente.');
        }
        else {
            this.factura.rfcEmisor = this.companyInfo.informacionFiscal.rfc;
            this.factura.razonSocialEmisor = this.companyInfo.informacionFiscal.razonSocial;
            this.factura.cfdi.emisor.regimenFiscal = this.companyInfo.regimenFiscal;
            this.factura.rfcEmisor = this.companyInfo.informacionFiscal.rfc;
            this.factura.razonSocialEmisor = this.companyInfo.informacionFiscal.razonSocial;
            this.factura.cfdi.emisor.rfc = this.companyInfo.informacionFiscal.rfc;
            this.factura.rfcRemitente = this.clientInfo.rfc;
            this.factura.razonSocialRemitente = this.clientInfo.razonSocial;
            this.factura.cfdi.receptor.rfc = this.clientInfo.rfc;
            this.errorMessages = this.cfdiValidator.validarCfdi(Object.assign({}, this.factura.cfdi));
        }
        if (this.errorMessages.length === 0) {
            this.invoiceService.insertNewInvoice(this.factura)
                .subscribe((invoice) => {
                this.factura.folio = invoice.folio;
                this.successMessage = 'Solicitud de factura enviada correctamente';
            }, (error) => {
                this.errorMessages.push((error.error != null && error.error !== undefined) ?
                    error.error.message : `${error.statusText} : ${error.message}`);
            });
        }
    }
    downloadPdf(folio) {
        //console.log('calling pdfMakeService for :', folio)
        //this.pdfMakeService.generatePdf(this.factura);
        this.filesService.getFacturaFile(folio, 'PDF').subscribe(file => this.downloadService.downloadFile(file.data, `${this.factura.folio}-${this.factura.rfcEmisor}-${this.factura.rfcRemitente}.pdf`, 'application/pdf;'));
    }
    downloadXml(folio) {
        this.filesService.getFacturaFile(folio, 'XML').subscribe(file => this.downloadService.downloadFile(file.data, `${this.factura.folio}-${this.factura.rfcEmisor}-${this.factura.rfcRemitente}.xml`, 'text/xml;charset=utf8;'));
    }
};
PreCfdiComponent = tslib_1.__decorate([
    Component({
        selector: 'ngx-pre-cfdi',
        templateUrl: './pre-cfdi.component.html',
        styleUrls: ['./pre-cfdi.component.scss'],
    }),
    tslib_1.__metadata("design:paramtypes", [CatalogsData,
        ClientsData,
        CompaniesData,
        InvoicesData,
        CfdiValidatorService,
        UsersData,
        FilesData,
        DownloadInvoiceFilesService,
        PdfMakeService,
        ActivatedRoute])
], PreCfdiComponent);
export { PreCfdiComponent };
//# sourceMappingURL=pre-cfdi.component.js.map