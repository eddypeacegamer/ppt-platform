import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { GenericPage } from '../../../models/generic-page';
import { DownloadCsvService } from '../../../@core/util-services/download-csv.service';
import { Router } from '@angular/router';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { map } from 'rxjs/operators';
import { UsersData } from '../../../@core/data/users-data';
let ReportesComponent = class ReportesComponent {
    constructor(invoiceService, catalogService, userService, donwloadService, router) {
        this.invoiceService = invoiceService;
        this.catalogService = catalogService;
        this.userService = userService;
        this.donwloadService = donwloadService;
        this.router = router;
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
        }).then(() => {
            if (this.userEmail === undefined) {
                this.userService.getUserInfo().subscribe((user) => {
                    this.userEmail = user.email;
                    this.filterParams.solicitante = user.email;
                    this.updateDataTable();
                });
            }
            else {
                this.filterParams.solicitante = this.userEmail;
                this.updateDataTable();
            }
        });
    }
    onPayStatus(payStatus) {
        this.filterParams.payStatus = payStatus;
    }
    onValidationStatus(validationStatus) {
        this.filterParams.status = validationStatus;
    }
    redirectToCfdi(folio) {
        this.router.navigate([`./pages/promotor/precfdi/${folio}`]);
    }
    /***     Funciones tabla      ***/
    /* Mapping function to  return correct information to view */
    getInvoiceData(pageValue, sizeValue, filterParams) {
        return this.invoiceService.getInvoices(pageValue, sizeValue, filterParams)
            .pipe(map((page) => {
            const records = page.content.map(record => {
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
        UsersData,
        DownloadCsvService,
        Router])
], ReportesComponent);
export { ReportesComponent };
//# sourceMappingURL=reportes.component.js.map