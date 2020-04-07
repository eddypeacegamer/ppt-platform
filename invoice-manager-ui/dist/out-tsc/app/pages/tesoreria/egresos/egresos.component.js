import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { GenericPage } from '../../../models/generic-page';
import { PaymentsData } from '../../../@core/data/payments-data';
import { DownloadCsvService } from '../../../@core/util-services/download-csv.service';
import { Router } from '@angular/router';
let EgresosComponent = class EgresosComponent {
    constructor(paymentService, donwloadService, router) {
        this.paymentService = paymentService;
        this.donwloadService = donwloadService;
        this.router = router;
        this.filterParams = { formaPago: '*', status: 'PAGADO', banco: '*', since: '', to: '' };
        this.errors = [];
        this.page = new GenericPage();
        this.pageSize = '10';
        this.total = 0.0;
    }
    ngOnInit() {
        this.updateDataTable();
        this.filterParams = { formaPago: '*', status: 'PAGADO', banco: '*', since: '', to: '' };
    }
    updateDataTable(currentPage, pageSize) {
        const pageValue = currentPage || 0;
        const sizeValue = pageSize || 10;
        this.paymentService.getExpenses(pageValue, sizeValue, this.filterParams)
            .subscribe((result) => this.page = result);
        this.paymentService.getExpensesSum().subscribe(total => this.total = total);
    }
    onChangePageSize(pageSize) {
        this.updateDataTable(this.page.number, pageSize);
    }
    downloadHandler() {
        this.paymentService.getExpenses(0, 10000, this.filterParams).subscribe(result => {
            this.donwloadService.exportCsv(result.content, 'Ingresos');
        });
    }
};
EgresosComponent = tslib_1.__decorate([
    Component({
        selector: 'ngx-egresos',
        templateUrl: './egresos.component.html',
        styleUrls: ['./egresos.component.scss']
    }),
    tslib_1.__metadata("design:paramtypes", [PaymentsData,
        DownloadCsvService,
        Router])
], EgresosComponent);
export { EgresosComponent };
//# sourceMappingURL=egresos.component.js.map