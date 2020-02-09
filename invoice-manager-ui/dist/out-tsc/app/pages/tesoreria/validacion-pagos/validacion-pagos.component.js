import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { GenericPage } from '../../../models/generic-page';
import { DownloadCsvService } from '../../../@core/util-services/download-csv.service';
import { PaymentsData } from '../../../@core/data/payments-data';
import { UsersData } from '../../../@core/data/users-data';
import { NbDialogService } from '@nebular/theme';
import { ValidacionPagoComponent } from './validacion-pago/validacion-pago.component';
import { Router } from '@angular/router';
let ValidacionPagosComponent = class ValidacionPagosComponent {
    constructor(userService, paymentService, donwloadService, router, dialogService) {
        this.userService = userService;
        this.paymentService = paymentService;
        this.donwloadService = donwloadService;
        this.router = router;
        this.dialogService = dialogService;
        this.filterParams = { formaPago: '*', status: 'VALIDACION', acredor: '', deudor: '', since: '', to: '' };
        this.errors = [];
        this.page = new GenericPage();
        this.pageSize = '10';
    }
    ngOnInit() {
        this.updateDataTable();
        this.filterParams = { formaPago: '*', status: 'VALIDACION', acredor: '', deudor: '', since: '', to: '' };
        this.userService.getUserInfo().subscribe(user => this.user = user);
    }
    updateDataTable(currentPage, pageSize) {
        const pageValue = currentPage || 0;
        const sizeValue = pageSize || 10;
        this.paymentService.getAllPayments(pageValue, sizeValue, this.filterParams)
            .subscribe((result) => this.page = result);
    }
    onChangePageSize(pageSize) {
        this.updateDataTable(this.page.number, pageSize);
    }
    downloadHandler() {
        this.paymentService.getIncomes(0, 10000, this.filterParams).subscribe(result => {
            this.donwloadService.exportCsv(result.content, 'Pagos');
        });
    }
    validar1(pago) {
        this.errors = [];
        if (pago.solicitante !== this.user.email) {
            pago.revision1 = true;
            pago.revisor1 = this.user.email;
            this.paymentService.updatePaymentWithValidation(pago.folio, pago.id, pago)
                .subscribe(updatedPayment => pago = updatedPayment, (error) => this.errors.push(error.error.message || `${error.statusText} : ${error.message}`));
        }
        else {
            this.errors.push('El solicitante del pago no puede validar el pago.');
        }
    }
    validar2(pago) {
        this.errors = [];
        if (pago.solicitante !== this.user.email && pago.revisor1 !== this.user.email) {
            pago.revision2 = true;
            pago.revisor2 = this.user.email;
            this.paymentService.updatePaymentWithValidation(pago.folio, pago.id, pago)
                .subscribe(updatedPayment => pago = updatedPayment, (error) => this.errors.push(error.error.message || `${error.statusText} : ${error.message}`));
        }
        else {
            this.errors.push('El segundo revisor, no puede ser ni el solicitante ni el primer revisor.');
        }
    }
    openDialog(payment) {
        this.errors = [];
        this.dialogService.open(ValidacionPagoComponent, {
            context: {
                pago: payment,
            },
        }).onClose.subscribe(pago => {
            if (pago !== undefined) {
                if (pago.revisor2 === undefined) {
                    pago.revisor2 = this.user.email;
                }
                else {
                    pago.revisor1 = this.user.email;
                }
                this.paymentService.updatePaymentWithValidation(pago.folio, pago.id, pago).toPromise()
                    .then(success => console.log(success), (error) => this.errors.push(error.error.message || `${error.statusText} : ${error.message}`))
                    .then(() => this.updateDataTable(this.page.number, this.page.size));
            }
            else {
                this.updateDataTable(this.page.number, this.page.size);
            }
        });
    }
    redirectToCfdi(folio) {
        this.router.navigate([`./pages/promotor/precfdi/${folio}`]);
    }
};
ValidacionPagosComponent = tslib_1.__decorate([
    Component({
        selector: 'ngx-valiudacion-pagos',
        templateUrl: './validacion-pagos.component.html',
        styleUrls: ['./validacion-pagos.component.scss'],
    }),
    tslib_1.__metadata("design:paramtypes", [UsersData,
        PaymentsData,
        DownloadCsvService,
        Router,
        NbDialogService])
], ValidacionPagosComponent);
export { ValidacionPagosComponent };
//# sourceMappingURL=validacion-pagos.component.js.map