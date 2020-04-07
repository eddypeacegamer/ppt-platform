import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { GenericPage } from '../../../models/generic-page';
import { UsersData } from '../../../@core/data/users-data';
import { PaymentsData } from '../../../@core/data/payments-data';
import { DownloadCsvService } from '../../../@core/util-services/download-csv.service';
import { NbDialogService } from '@nebular/theme';
import { SolicitudDevolucionComponent } from './solicitud-devolucion/solicitud-devolucion.component';
let DevolucionesComponent = class DevolucionesComponent {
    constructor(userService, paymentService, donwloadService, dialogService) {
        this.userService = userService;
        this.paymentService = paymentService;
        this.donwloadService = donwloadService;
        this.dialogService = dialogService;
        this.filterParams = { formaPago: '*', status: 'DEVOLUCION', banco: '*', since: '', to: '' };
        this.errors = [];
        this.page = new GenericPage();
        this.pageSize = '10';
    }
    ngOnInit() {
        this.updateDataTable();
        this.filterParams = { formaPago: '*', status: 'DEVOLUCION', banco: '*', since: '', to: '' };
        this.userService.getUserInfo().subscribe(user => this.userEmail = user.email);
    }
    updateDataTable(currentPage, pageSize) {
        const pageValue = currentPage || 0;
        const sizeValue = pageSize || 10;
        this.paymentService.getExpenses(pageValue, sizeValue, this.filterParams)
            .subscribe((result) => this.page = result);
    }
    onChangePageSize(pageSize) {
        this.updateDataTable(this.page.number, pageSize);
    }
    downloadHandler() {
        this.paymentService.getExpenses(0, 10000, this.filterParams).subscribe(result => {
            this.donwloadService.exportCsv(result.content, 'SolicitudesDevolucion');
        });
    }
    openDialog(payment) {
        this.dialogService.open(SolicitudDevolucionComponent, {
            context: {
                pago: payment,
            },
        }).onClose.subscribe(() => this.updateDataTable(this.page.number, this.page.size));
    }
};
DevolucionesComponent = tslib_1.__decorate([
    Component({
        selector: 'ngx-devoluciones',
        templateUrl: './devoluciones.component.html',
        styleUrls: ['./devoluciones.component.scss']
    }),
    tslib_1.__metadata("design:paramtypes", [UsersData,
        PaymentsData,
        DownloadCsvService,
        NbDialogService])
], DevolucionesComponent);
export { DevolucionesComponent };
//# sourceMappingURL=devoluciones.component.js.map