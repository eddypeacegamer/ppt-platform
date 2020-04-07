import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { GenericPage } from '../../../models/generic-page';
import { DownloadCsvService } from '../../../@core/util-services/download-csv.service';
import { Router } from '@angular/router';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { map } from 'rxjs/operators';
let ReportesComponent = class ReportesComponent {
    constructor(invoiceService, catalogService, donwloadService, router) {
        this.invoiceService = invoiceService;
        this.catalogService = catalogService;
        this.donwloadService = donwloadService;
        this.router = router;
        this.headers = ['Folio', 'RFC Emisor', 'Emisor', 'RFC Remitente', 'Remitente', 'Tipo', 'Metodo pago', 'Estatus Validacion', 'Estatus Pago', 'Total', 'Fecha Solicitud', 'Fecha Timbrado'];
        this.page = new GenericPage();
        this.pageSize = '10';
        this.filterParams = { emisor: '', remitente: '', folio: '', status: '*', since: '', to: '' };
        this.validationCat = [];
        this.payCat = [];
        this.devolutionCat = [];
    }
    ngOnInit() {
        this.catalogService.getInvoiceCatalogs().toPromise().then(results => {
            this.payCat = results[3];
            this.devolutionCat = results[4];
            this.validationCat = results[5];
        }).then(() => this.updateDataTable());
    }
    onPayStatus(payStatus) {
        this.filterParams.payStatus = payStatus;
    }
    onValidationStatus(validationStatus) {
        this.filterParams.status = validationStatus;
        console.log(this.filterParams);
    }
    redirectToCfdi(folio) {
        this.router.navigate([`./pages/operaciones/revision/${folio}`]);
    }
    /***     Funciones tabla      ***/
    /* Mapping function to  return correct information to view */
    getInvoiceData(pageValue, sizeValue, filterParams) {
        return this.invoiceService.getInvoices(pageValue, sizeValue, filterParams)
            .pipe(map((page) => {
            let records = page.content.map(record => {
                record.statusFactura = this.validationCat.find(v => v.id === record.statusFactura).nombre;
                record.statusPago = this.payCat.find(v => v.id === record.statusPago).nombre;
                record.statusDevolucion = this.devolutionCat.find(v => v.id === record.statusDevolucion).nombre;
                return record;
            });
            page.content = records;
            return page;
        }));
    }
    updateDataTable(currentPage, pageSize) {
        const pageValue = currentPage || 0;
        const sizeValue = pageSize || 10;
        this.getInvoiceData(pageValue, sizeValue, this.filterParams)
            .subscribe((result) => this.page = result);
    }
    onChangePageSize(pageSize) {
        this.updateDataTable(this.page.number, pageSize);
    }
    downloadHandler() {
        this.getInvoiceData(0, 10000, this.filterParams).subscribe(result => {
            this.donwloadService.exportCsv(result.content, 'Facturas');
        });
    }
};
ReportesComponent = tslib_1.__decorate([
    Component({
        selector: 'ngx-reportes',
        templateUrl: './reportes.component.html',
        styleUrls: ['./reportes.component.scss']
    }),
    tslib_1.__metadata("design:paramtypes", [InvoicesData,
        CatalogsData,
        DownloadCsvService,
        Router])
], ReportesComponent);
export { ReportesComponent };
//# sourceMappingURL=reportes.component.js.map