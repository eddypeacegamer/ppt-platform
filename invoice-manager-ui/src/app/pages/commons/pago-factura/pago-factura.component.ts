import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { Factura } from '../../../models/factura/factura';
import { User } from '../../../models/user';
import { PagoBase } from '../../../models/pago-base';
import { Catalogo } from '../../../models/catalogos/catalogo';
import { Cuenta } from '../../../models/cuenta';
import { PaymentsData } from '../../../@core/data/payments-data';
import { CuentasData } from '../../../@core/data/cuentas-data';
import { FilesData } from '../../../@core/data/files-data';
import { PagosValidatorService } from '../../../@core/util-services/pagos-validator.service';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { HttpErrorResponse } from '@angular/common/http';
import { ResourceFile } from '../../../models/resource-file';
import { PagoFactura } from '../../../models/pago-factura';

@Component({
  selector: 'ngx-pago-factura',
  templateUrl: './pago-factura.component.html',
  styleUrls: ['./pago-factura.component.scss'],
})
export class PagoFacturaComponent implements OnInit {

  @Input() factura: Factura;
  @Input() user: User;
  @Output() myEvent = new EventEmitter<string>();

  public fileInput: any;

  public paymentForm = { payType: '*', bankAccount: '*', filename: ''};
  public newPayment: PagoBase = new PagoBase();
  public invoicePayments: PagoBase[] = [];
  public paymentSum: number = 0;
  public payErrorMessages: string[] = [];
  public payTypeCat: Catalogo[] = [];
  public cuentas: Cuenta[];
  public loading: boolean = false;

  constructor(private paymentsService: PaymentsData,
    private accountsService: CuentasData,
    private fileService: FilesData,
    private paymentValidator: PagosValidatorService,
    private invoiceService: InvoicesData) {
      this.factura = new Factura();
      this.newPayment.moneda = 'MXN';
      this.newPayment.facturas = [new PagoFactura()];
    }

  ngOnInit() {
    
    if (this.factura !== undefined && this.factura.folio !== undefined) {
      this.paymentsService.getFormasPago(this.user.roles.map(r => r.role))
        .subscribe(payTypes => this.payTypeCat = payTypes);
      this.paymentsService.getPaymentsByFolio(this.factura.folio)
              .subscribe((payments: PagoBase[]) => this.invoicePayments = payments);
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
      this.newPayment.banco = 'No aplica';
            this.newPayment.cuenta = 'Sin especificar';
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
    this.fileInput = event.target;
    const reader = new FileReader();
    if (event.target.files && event.target.files.length > 0) {
      const file = event.target.files[0];
      if (file.size > 1000000) {
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
    this.paymentsService.deletePayment(paymentId).subscribe(
      result => {
        this.paymentsService.getPaymentsByFolio(this.factura.folio)
          .subscribe(payments => {
            this.invoicePayments = payments;
            this.myEvent.emit(this.factura.cfdi.id.toString());
          });
        this.invoiceService.getComplementosInvoice(this.factura.folio)
          .subscribe(complementos => {this.factura.complementos = complementos;
          });
      }, (error: HttpErrorResponse) =>
      this.payErrorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
  }

  sendPayment() {

    this.newPayment.solicitante = this.user.email;
    const payment  = {... this.newPayment};
    payment.facturas[0].folio = this.factura.folio;
    payment.facturas[0].monto = payment.monto;
    this.payErrorMessages = this.paymentValidator.validatePago(payment, this.factura);
    if (this.payErrorMessages.length === 0) {
      this.loading = true;
      payment.acredor = this.factura.razonSocialEmisor;
      payment.deudor = this.factura.razonSocialRemitente;
      this.paymentsService.insertNewPayment(payment).subscribe(
        result => {
          const resourceFile = new ResourceFile();
          resourceFile.tipoArchivo = 'IMAGEN';
          resourceFile.tipoRecurso = 'PAGO';
          resourceFile.referencia  = `${result.id}`;
          resourceFile.data = payment.documento;
          this.fileService.insertResourceFile(resourceFile).subscribe(response => console.log(response));
          this.paymentsService.getPaymentsByFolio(this.factura.folio)
          .subscribe((payments: PagoBase[]) => {
            this.invoicePayments = payments;
            this.loading = false; });
            if (this.factura.metodoPago === 'PPD') {
              this.invoiceService.getComplementosInvoice(this.factura.folio)
                .subscribe(complementos => this.factura.complementos = complementos);
            }
            this.myEvent.emit(this.factura.cfdi.id.toString());          
        }, (error: HttpErrorResponse) => {
          this.loading = false;
          this.payErrorMessages.push(error.error.message || `${error.statusText} : ${error.message}`);
        });
    }
    this.newPayment = new PagoBase();
    this.newPayment.moneda = 'MXN';
    this.newPayment.facturas = [new PagoFactura()];
    this.paymentForm = { payType: '*', bankAccount: '*', filename: ''};
    if (this.fileInput !== undefined) {
      this.fileInput.value = '';
    }
  }
}
