import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { GenericPage } from '../../../models/generic-page';
import { PaymentsData } from '../../../@core/data/payments-data';
import { DownloadCsvService } from '../../../@core/util-services/download-csv.service';
import { Router } from '@angular/router';
let IngresosComponent = class IngresosComponent {
    constructor(paymentService, donwloadService, router) {
        this.paymentService = paymentService;
        this.donwloadService = donwloadService;
        this.router = router;
        this.filterParams = { formaPago: '*', status: 'ACEPTADO', banco: '*', since: '', to: '' };
        this.errors = [];
        this.page = new GenericPage();
        this.pageSize = '10';
        this.total = 0.0;
    }
    ngOnInit() {
        this.updateDataTable();
        this.filterParams = { formaPago: '*', status: 'ACEPTADO', banco: '*', since: '', to: '' };
    }
    updateDataTable(currentPage, pageSize) {
        const pageValue = currentPage || 0;
        const sizeValue = pageSize || 10;
        this.paymentService.getIncomes(pageValue, sizeValue, this.filterParams)
            .subscribe((result) => this.page = result);
        this.paymentService.getIncomesSum().subscribe(total => this.total = total);
    }
    onChangePageSize(pageSize) {
        this.updateDataTable(this.page.number, pageSize);
    }
    downloadHandler() {
        this.paymentService.getIncomes(0, 10000, this.filterParams).subscribe(result => {
            this.donwloadService.exportCsv(result.content, 'Ingresos');
        });
    }
    redirectToCfdi(folio) {
        this.router.navigate([`./pages/promotor/precfdi/${folio}`]);
    }
};
IngresosComponent = tslib_1.__decorate([
    Component({
        selector: 'ngx-ingresos',
        templateUrl: './ingresos.component.html',
        styleUrls: ['./ingresos.component.scss']
    }),
    tslib_1.__metadata("design:paramtypes", [PaymentsData,
        DownloadCsvService,
        Router])
], IngresosComponent);
export { IngresosComponent };
//# sourceMappingURL=ingresos.component.js.map