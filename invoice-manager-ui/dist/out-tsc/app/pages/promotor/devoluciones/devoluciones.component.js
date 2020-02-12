import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { DevolutionData } from '../../../@core/data/devolution-data';
import { DownloadCsvService } from '../../../@core/util-services/download-csv.service';
import { Router } from '@angular/router';
import { UsersData } from '../../../@core/data/users-data';
import { SolicitudDevolucion } from '../../../models/solicitud-devolucion';
import { NbDialogService } from '@nebular/theme';
import { PaymentsService } from '../../../@core/back-services/payments.service';
let DevolucionesComponent = class DevolucionesComponent {
    constructor(dialogService, devolutionService, paymentsService, donwloadService, userService, router) {
        this.dialogService = dialogService;
        this.devolutionService = devolutionService;
        this.paymentsService = paymentsService;
        this.donwloadService = donwloadService;
        this.userService = userService;
        this.router = router;
        this.devolutions = [];
        this.solicitud = new SolicitudDevolucion();
        this.filterParams = { tipoReceptor: 'POR SOLICITAR', idReceptor: 'promotor@gmail.com', statusDevolucion: '*' };
    }
    ngOnInit() {
        this.montoDevolucion = 0.0;
        this.solicitud = new SolicitudDevolucion();
        this.solicitud.formaPago = 'TRANSFERENCIA';
        this.solicitud.banco = 'BBVA';
        this.userService.getUserInfo()
            .subscribe(user => {
            this.userEmail = user.email;
            this.solicitud.user = user.email;
            this.filterParams = { tipoReceptor: 'PROMOTOR', idReceptor: this.userEmail, statusDevolucion: 'POR SOLICITAR' };
            this.updateDataTable();
        });
    }
    onReceptorTypeSelected(type) {
        if (type == 'PROMOTOR') {
            this.filterParams.idReceptor = this.userEmail;
        }
        else {
            this.filterParams.idReceptor = '';
        }
        this.filterParams.tipoReceptor = type;
    }
    onDevolutionStatusSelected(status) {
        this.filterParams.statusPago = status;
    }
    updateDataTable() {
        this.devolutionService.getDevolutionsByReceptor(this.filterParams.tipoReceptor, this.filterParams.idReceptor, this.filterParams.statusDevolucion)
            .subscribe((result) => this.devolutions = result);
    }
    downloadHandler() {
        this.devolutionService.getDevolutionsByReceptor(this.filterParams.tipoReceptor, this.filterParams.idReceptor, this.filterParams.statusDevolucion).subscribe(result => {
            this.donwloadService.exportCsv(result, 'Devoluciones');
        });
    }
    addDevolucion(devolucion) {
        devolucion.solicitud = !devolucion.solicitud;
        if (devolucion.solicitud === true) {
            devolucion.solicitud = true;
            this.solicitud.devoluciones.push(devolucion);
        }
        else {
            devolucion.solicitud = false;
            let index = this.solicitud.devoluciones.findIndex(e => e.id == devolucion.id);
            if (index > -1) {
                this.solicitud.devoluciones.splice(index, 1);
            }
        }
        if (this.solicitud.devoluciones != undefined && this.solicitud.devoluciones.length > 0) {
            this.montoDevolucion = this.solicitud.devoluciones.map(d => d.monto).reduce((a, c) => a + c);
        }
        else {
            this.montoDevolucion = 0.0;
        }
    }
    solicitudDevoluciones() {
        if (this.solicitud.cuenta == undefined || this.solicitud.cuenta.length < 8) {
            alert('El numero de cuenta/CLABE es un valor requerido');
        }
        else if (this.solicitud.beneficiario == undefined || this.solicitud.beneficiario.length < 5) {
            alert('El nombre del beneficiario es un valor requerido');
        }
        else {
            let solicitud = Object.assign({}, this.solicitud);
            this.devolutionService.requestMultipleDevolution(solicitud)
                .subscribe(pago => { this.updateDataTable(); });
            this.solicitud = new SolicitudDevolucion();
            this.solicitud.formaPago = 'TRANSFERENCIA';
            this.solicitud.banco = 'BBVA';
        }
    }
    redirectToCfdi(folio) {
        this.router.navigate([`./pages/promotor/precfdi/${folio}`]);
    }
    redirectToPago(id) {
        console.log("searching payment with id :" + id);
    }
    openPaymentDetails(dialog, destPayment) {
        this.paymentsService.getPaymentById(destPayment)
            .subscribe(payment => {
            this.dialogService.open(dialog, { context: payment })
                .onClose.subscribe(() => this.updateDataTable());
        });
    }
};
DevolucionesComponent = tslib_1.__decorate([
    Component({
        selector: 'ngx-devoluciones',
        templateUrl: './devoluciones.component.html',
        styleUrls: ['./devoluciones.component.scss']
    }),
    tslib_1.__metadata("design:paramtypes", [NbDialogService,
        DevolutionData,
        PaymentsService,
        DownloadCsvService,
        UsersData,
        Router])
], DevolucionesComponent);
export { DevolucionesComponent };
//# sourceMappingURL=devoluciones.component.js.map