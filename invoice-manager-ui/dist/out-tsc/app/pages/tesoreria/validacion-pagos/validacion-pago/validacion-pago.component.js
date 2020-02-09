import * as tslib_1 from "tslib";
import { Component, Input } from '@angular/core';
import { NbDialogRef } from '@nebular/theme';
import { Pago } from '../../../../models/pago';
import { FilesData } from '../../../../@core/data/files-data';
import { DomSanitizer } from '@angular/platform-browser';
let ValidacionPagoComponent = class ValidacionPagoComponent {
    constructor(ref, filesService, sanitizer) {
        this.ref = ref;
        this.filesService = filesService;
        this.sanitizer = sanitizer;
    }
    ngOnInit() {
        this.mostrarComprobante(this.pago);
        this.updatedPayment = Object.assign({}, this.pago);
    }
    mostrarComprobante(pago) {
        this.comprobanteUrl = undefined;
        if (pago.formaPago != 'CREDITO') {
            this.filesService.getResourceFile(`${pago.id}_${pago.folio}`, 'PAGO', 'IMAGEN').subscribe((file) => this.comprobanteUrl = this.sanitizer.bypassSecurityTrustUrl(file.data));
        }
    }
    cancel() {
        this.ref.close();
    }
    onRecahzarPago() {
        this.updatedPayment.statusPago = 'RECHAZADO';
        this.ref.close(this.updatedPayment);
    }
    onValidarPago() {
        this.ref.close(this.updatedPayment);
    }
};
tslib_1.__decorate([
    Input(),
    tslib_1.__metadata("design:type", Pago)
], ValidacionPagoComponent.prototype, "pago", void 0);
ValidacionPagoComponent = tslib_1.__decorate([
    Component({
        selector: 'ngx-validacion-pago',
        templateUrl: './validacion-pago.component.html',
        styleUrls: ['./validacion-pago.component.scss'],
    }),
    tslib_1.__metadata("design:paramtypes", [NbDialogRef,
        FilesData,
        DomSanitizer])
], ValidacionPagoComponent);
export { ValidacionPagoComponent };
//# sourceMappingURL=validacion-pago.component.js.map