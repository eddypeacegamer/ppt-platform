import * as tslib_1 from "tslib";
import { Component, Input } from '@angular/core';
import { PaymentsData } from '../../../@core/data/payments-data';
import { PagosValidatorService } from '../../../@core/util-services/pagos-validator.service';
import { Pago } from '../../../models/pago';
import { Factura } from '../../../models/factura/factura';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { CuentasData } from '../../../@core/data/cuentas-data';
import { Cuenta } from '../../../models/cuenta';
import { FilesData } from '../../../@core/data/files-data';
import { ResourceFile } from '../../../models/resource-file';
let PagosComponent = class PagosComponent {
    constructor(paymentsService, accountsService, fileService, paymentValidator, invoiceService) {
        this.paymentsService = paymentsService;
        this.accountsService = accountsService;
        this.fileService = fileService;
        this.paymentValidator = paymentValidator;
        this.invoiceService = invoiceService;
        this.paymentForm = { payType: '*', bankAccount: '*', filename: '' };
        this.newPayment = new Pago();
        this.invoicePayments = [];
        this.paymentSum = 0;
        this.payErrorMessages = [];
        this.payTypeCat = [];
        this.loading = false;
    }
    ngOnInit() {
        this.newPayment.moneda = 'MXN';
        if (this.factura !== undefined && this.factura.folio !== undefined) {
            this.paymentsService.getPaymentsByFolio(this.factura.folio)
                .subscribe(payments => {
                this.invoicePayments = payments;
                this.paymentSum = this.paymentValidator.getPaymentAmmount(payments);
            });
            this.paymentsService.getFormasPago(this.user.roles).subscribe(payTypes => this.payTypeCat = payTypes);
        }
    }
    /******* PAGOS ********/
    onPaymentCoinSelected(clave) {
        this.newPayment.moneda = clave;
    }
    onPaymentTypeSelected(clave) {
        this.newPayment.formaPago = clave;
        if (clave === 'EFECTIVO' || clave === 'CHEQUE' || clave === '*') {
            this.cuentas = [new Cuenta('N/A', 'No aplica', 'Sin especificar')];
            this.paymentForm.bankAccount = 'N/A';
            this.newPayment.banco = 'No aplica';
            this.newPayment.cuenta = 'Sin especificar';
        }
        else {
            this.accountsService.getCuentasByCompany(this.factura.rfcEmisor)
                .subscribe(cuentas => {
                this.cuentas = cuentas;
                this.paymentForm.bankAccount = cuentas[0].id;
                this.newPayment.banco = cuentas[0].banco;
                this.newPayment.cuenta = cuentas[0].cuenta;
            });
        }
    }
    onPaymentBankSelected(clave) {
        this.newPayment.banco = clave;
    }
    fileUploadListener(event) {
        this.fileInput = event.target;
        const reader = new FileReader();
        if (event.target.files && event.target.files.length > 0) {
            const file = event.target.files[0];
            if (file.size > 100000) {
                alert('El archivo demasiado grande, intenta con un archivo mas pequeño.');
            }
            else {
                reader.readAsDataURL(file);
                reader.onload = () => {
                    this.paymentForm.filename = file.name;
                    this.newPayment.documento = reader.result.toString();
                };
                reader.onerror = (error) => { this.payErrorMessages.push('Error parsing image file'); };
            }
        }
    }
    deletePayment(paymentId) {
        this.paymentsService.deletePayment(this.factura.folio, paymentId).subscribe(result => {
            this.paymentsService.getPaymentsByFolio(this.factura.folio)
                .subscribe(payments => {
                this.invoicePayments = payments;
                this.paymentSum = this.paymentValidator.getPaymentAmmount(payments);
            });
            this.invoiceService.getComplementosInvoice(this.factura.folio)
                .subscribe(complementos => this.factura.complementos = complementos);
        }, (error) => this.payErrorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
    }
    sendPayment() {
        this.newPayment.folioPadre = this.factura.folio;
        this.newPayment.folio = this.factura.folio;
        this.newPayment.tipoPago = 'INGRESO';
        this.newPayment.acredor = this.factura.rfcEmisor;
        this.newPayment.deudor = this.factura.rfcRemitente;
        this.newPayment.solicitante = this.user.email;
        const payment = Object.assign({}, this.newPayment);
        this.payErrorMessages = this.paymentValidator.validatePago(payment, this.invoicePayments, this.factura.cfdi);
        if (this.payErrorMessages.length === 0) {
            this.loading = true;
            this.paymentsService.insertNewPayment(this.factura.folio, payment).subscribe(result => {
                this.newPayment = new Pago();
                const resourceFile = new ResourceFile();
                resourceFile.tipoArchivo = 'IMAGEN';
                resourceFile.tipoRecurso = 'PAGO';
                resourceFile.referencia = `${result.id}_${result.folio}`;
                resourceFile.data = payment.documento;
                this.fileService.insertResourceFile(resourceFile).subscribe(response => console.log(response));
                this.paymentsService.getPaymentsByFolio(this.factura.folio)
                    .subscribe(payments => {
                    this.invoicePayments = payments;
                    this.paymentSum = this.paymentValidator.getPaymentAmmount(payments);
                    this.loading = false;
                });
                this.invoiceService.getComplementosInvoice(this.factura.folio)
                    .subscribe(complementos => this.factura.complementos = complementos);
            }, (error) => {
                this.loading = false;
                this.payErrorMessages.push(error.error.message || `${error.statusText} : ${error.message}`);
            });
        }
        this.newPayment = new Pago();
        this.paymentForm = { payType: '*', bankAccount: '*', filename: '' };
        if (this.fileInput !== undefined) {
            this.fileInput.value = '';
        }
    }
};
tslib_1.__decorate([
    Input(),
    tslib_1.__metadata("design:type", Factura)
], PagosComponent.prototype, "factura", void 0);
tslib_1.__decorate([
    Input(),
    tslib_1.__metadata("design:type", Object)
], PagosComponent.prototype, "user", void 0);
PagosComponent = tslib_1.__decorate([
    Component({
        selector: 'ngx-pagos',
        templateUrl: './pagos.component.html',
        styleUrls: ['./pagos.component.scss'],
    }),
    tslib_1.__metadata("design:paramtypes", [PaymentsData,
        CuentasData,
        FilesData,
        PagosValidatorService,
        InvoicesData])
], PagosComponent);
export { PagosComponent };
//# sourceMappingURL=pagos.component.js.map