import * as tslib_1 from "tslib";
import { Component, Input } from '@angular/core';
import { Pago } from '../../../../models/pago';
import { NbDialogRef } from '@nebular/theme';
import { DevolutionData } from '../../../../@core/data/devolution-data';
import { Router } from '@angular/router';
let SolicitudDevolucionComponent = class SolicitudDevolucionComponent {
    constructor(ref, devolutionsService, router) {
        this.ref = ref;
        this.devolutionsService = devolutionsService;
        this.router = router;
        this.devoluciones = [];
    }
    ngOnInit() {
        this.errorMesage = undefined;
        this.devolutionsService.getDevolutionsByPayment(this.pago.id)
            .subscribe(devolutions => this.devoluciones = devolutions);
    }
    exit() {
        this.ref.close();
    }
    updatePaymentStatus() {
        this.errorMesage = undefined;
        this.pago.statusPago = 'PAGADO';
        this.devolutionsService.updateDevolutionAsPaid(this.pago)
            .subscribe(success => { console.log(success); this.ref.close(); }, (error) => this.errorMesage = error.error.message || `${error.statusText} : ${error.message}`);
    }
    redirectToCfdi(folio) {
        this.router.navigate([`./pages/promotor/precfdi/${folio}`]);
    }
};
tslib_1.__decorate([
    Input(),
    tslib_1.__metadata("design:type", Pago)
], SolicitudDevolucionComponent.prototype, "pago", void 0);
SolicitudDevolucionComponent = tslib_1.__decorate([
    Component({
        selector: 'ngx-solicitud-devolucion',
        templateUrl: './solicitud-devolucion.component.html',
        styleUrls: ['./solicitud-devolucion.component.scss']
    }),
    tslib_1.__metadata("design:paramtypes", [NbDialogRef,
        DevolutionData,
        Router])
], SolicitudDevolucionComponent);
export { SolicitudDevolucionComponent };
//# sourceMappingURL=solicitud-devolucion.component.js.map