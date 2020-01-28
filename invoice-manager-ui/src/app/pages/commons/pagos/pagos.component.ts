import { Component, OnInit, Input } from '@angular/core';
import { PaymentsData } from '../../../@core/data/payments-data';
import { PagosValidatorService } from '../../../@core/util-services/pagos-validator.service';
import { Pago } from '../../../models/pago';
import { Factura } from '../../../models/factura/factura';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { HttpErrorResponse } from '@angular/common/http';
import { CuentasData } from '../../../@core/data/cuentas-data';
import { User } from '../../../@core/data/users-data';
import { Catalogo } from '../../../models/catalogos/catalogo';
import { Cuenta } from '../../../models/cuenta';

@Component({
  selector: 'ngx-pagos',
  templateUrl: './pagos.component.html',
  styleUrls: ['./pagos.component.scss'],
})
export class PagosComponent implements OnInit {

  @Input() factura: Factura;
  @Input() user: User;

  public paymentForm = { payType: '*', bankAccount: '*', filename: ''};
  public newPayment: Pago = new Pago();
  public invoicePayments: Pago[] = [];
  public paymentSum: number = 0;
  public payErrorMessages: string[] = [];
  public payTypeCat: Catalogo[] = [];
  public cuentas: Cuenta[];
  public loading: boolean = false;

  constructor(private paymentsService: PaymentsData,
    private accountsService: CuentasData,
    private paymentValidator: PagosValidatorService,
    private invoiceService: InvoicesData) { }

  ngOnInit() {
    this.newPayment.moneda = 'MXN';
    if (this.factura.folio !== undefined) {
      this.paymentsService.getPaymentsByFolio(this.factura.folio)
              .subscribe(payments => this.invoicePayments = payments);
      this.paymentsService.getFormasPago(this.user.roles).subscribe(payTypes => this.payTypeCat = payTypes);
    }
  }

  /******* PAGOS ********/

  onPaymentCoinSelected(clave: string) {
    this.newPayment.moneda = clave;
  }

  onPaymentTypeSelected(clave: string) {
    this.newPayment.formaPago = clave;
    if (clave === 'EFECTIVO' || clave === 'CHEQUE' || clave === '*') {
      this.cuentas = [ new Cuenta('N/A', 'No aplica', 'Sin especificar')];
      this.paymentForm.bankAccount = 'N/A';
    }else {
      this.accountsService.getCuentasByCompany(this.factura.rfcEmisor)
          .subscribe(cuentas => {
            this.cuentas = cuentas;
            this.paymentForm.bankAccount = cuentas[0].id;
            this.newPayment.banco = cuentas[0].banco;
            this.newPayment.cuenta = cuentas[0].cuenta;
          });
    }
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
    this.newPayment.folioPadre = this.factura.folio;
    this.newPayment.folio = this.factura.folio;
    this.newPayment.tipoPago = 'INGRESO';
    this.newPayment.ultimoUsuario = this.user.email;
    const payment  = {... this.newPayment};
    this.payErrorMessages = this.paymentValidator.validatePago(payment, this.invoicePayments, this.factura.cfdi);
    if (this.payErrorMessages.length === 0) {

      this.paymentsService.insertNewPayment(this.factura.folio, payment).subscribe(
        result => {
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
    this.paymentForm = { payType: '*', bankAccount: '*', filename: ''};
  }

}
