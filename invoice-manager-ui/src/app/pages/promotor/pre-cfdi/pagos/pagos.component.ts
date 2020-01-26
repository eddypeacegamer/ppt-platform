import { Component, OnInit, Input } from '@angular/core';
import { Cfdi } from '../../../../models/factura/cfdi';
import { PaymentsData } from '../../../../@core/data/payments-data';
import { PagosValidatorService } from '../../../../@core/util-services/pagos-validator.service';
import { Pago } from '../../../../models/pago';
import { Factura } from '../../../../models/factura/factura';
import { InvoicesData } from '../../../../@core/data/invoices-data';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'ngx-pagos',
  templateUrl: './pagos.component.html',
  styleUrls: ['./pagos.component.scss'],
})
export class PagosComponent implements OnInit {

  @Input() factura: Factura;
  @Input() user: string;

  public paymentForm = { coin: 'MXN', payType: '*', bank: '*', filename: '', successPayment: false };
  public newPayment: Pago;
  public invoicePayments: Pago[] = [];
  public paymentSum: number = 0;
  public payErrorMessages: string[] = [];
  public loading: boolean = false;

  constructor(private paymentsService: PaymentsData,
    private paymentValidator: PagosValidatorService,
    private invoiceService: InvoicesData) { }

  ngOnInit() {
    if (this.factura.folio !== undefined) {
      this.paymentsService.getPaymentsByFolio(this.factura.folio)
              .subscribe(payments => this.invoicePayments = payments);
    }
  }

  /******* PAGOS ********/

  onPaymentCoinSelected(clave: string) {
    this.newPayment.moneda = clave;
  }

  onPaymentTypeSelected(clave: string) {
    this.newPayment.formaPago = clave;
  }

  onPaymentBankSelected(clave: string) {
    this.newPayment.banco = clave;
  }

  fileUploadListener(event: any): void {
    const reader = new FileReader();
    if (event.target.files && event.target.files.length > 0) {
      const file = event.target.files[0];
      if (file.size > 100000) {
        alert('El archivo demasiado grande, intenta con un archivo mas pequeño.');
      } else {
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
    this.paymentsService.deletePayment(this.factura.folio, paymentId).subscribe(
      result => {
        this.paymentsService.getPaymentsByFolio(this.factura.folio)
          .subscribe(payments => {
            this.invoicePayments = payments;
            this.paymentSum = this.paymentValidator.getPaymentAmmount(payments); });
        this.invoiceService.getComplementosInvoice(this.factura.folio)
          .subscribe(complementos => this.factura.complementos = complementos);
      }, (error: HttpErrorResponse) =>
      this.payErrorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
  }

  sendPayment() {
    this.loading = true;
    this.paymentForm.successPayment = false;
    this.newPayment.folioPadre = this.factura.folio;
    this.newPayment.folio = this.factura.folio;
    this.newPayment.tipoPago = 'INGRESO';
    this.newPayment.ultimoUsuario = this.user;
    const payment  = {... this.newPayment};
    this.payErrorMessages = this.paymentValidator.validatePago(payment, this.invoicePayments, this.factura.cfdi);
    if (this.payErrorMessages.length === 0) {

      this.paymentsService.insertNewPayment(this.factura.folio, payment).subscribe(
        result => {
          this.paymentForm.successPayment = true;
          this.newPayment = new Pago();
          this.paymentsService.getPaymentsByFolio(this.factura.folio)
          .subscribe(payments => { this.invoicePayments = payments;
            this.paymentSum = this.paymentValidator.getPaymentAmmount(payments);
            this.loading = false; });
          this.invoiceService.getComplementosInvoice(this.factura.folio)
          .subscribe(complementos => this.factura.complementos = complementos);
        }, (error: HttpErrorResponse) => {
          this.loading = false;
          this.payErrorMessages.push(error.error.message || `${error.statusText} : ${error.message}`);
        });
    }
    this.newPayment = new Pago();
    this.paymentForm = { coin: 'MXN', payType: '*', bank: '*', filename: '', successPayment: false };
  }

}
