import { Component, OnInit, Input } from '@angular/core';
import { Factura } from '../../../models/factura/factura';
import { Pago } from '../../../models/factura/pago';
import { PaymentsData } from '../../../@core/data/payments-data';
import { Catalogo } from '../../../models/catalogos/catalogo';
import { PagoBase } from '../../../models/pago-base';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { HttpErrorResponse } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Component({
  selector: 'ngx-generar-complemento',
  templateUrl: './generar-complemento.component.html',
  styleUrls: ['./generar-complemento.component.scss']
})
export class GenerarComplementoComponent implements OnInit {

  @Input() factura: Factura;
  @Input() loading: boolean;

  public complementPayTypeCat: Catalogo[] = [];
  public payment: Pago;
  public paymentForm = { coin: '*', payType: '*', bank: '*', filename: '', successPayment: false };
  public newPayment: PagoBase;
  public invoicePayments = [];
  public payErrorMessages: string[] = [];
  public validationCat: Catalogo[] = [];
  public paymentSum: number = 0;

  public complementos: Factura[] = [];

  constructor(
    private paymentsService: PaymentsData,
    private invoiceService: InvoicesData,
  ) {

   }

  ngOnInit() {
    
    this.paymentsService.getFormasPago().subscribe(payTypes => this.complementPayTypeCat = payTypes);
    this.initVariables();
  }

  public initVariables() {
    this.payment = new Pago();
    this.payment.formaPago = '*'; 
    this.payErrorMessages = [];
  }

   generateComplement() {
    this.loading = true;
    this.payErrorMessages = [];
    if (this.payment.monto === undefined) {
      this.payErrorMessages.push('El monto del complemento es un valor requerido');
    }
    if (this.payment.monto <= 0) {
      this.payErrorMessages.push('El monto del complemento no puede ser igual a 0');
    }
    if (this.payment.monto + this.paymentSum > this.factura.cfdi.total) {
      this.payErrorMessages.push('El monto del complemento no puede ser superior al monto total de la factura');
    }
    if (this.payment.moneda !== this.factura.cfdi.moneda) {
      this.payErrorMessages.push('El monto del complemento no puede ser superior al monto total de la factura');
    }
    if (this.payment.formaPago === undefined) {
      this.payErrorMessages.push('La forma de pago es requerida');
    }
    if (this.payment.fechaPago === undefined || this.payment.fechaPago === null) {
      this.payErrorMessages.push('La fecha de pago es un valor requerido');
    }
    if (this.payErrorMessages.length === 0) {
        this.invoiceService.generateInvoiceComplement(this.factura.folio, this.payment)
        .subscribe(complement => {
          this.loadConceptos();
        }, ( error: HttpErrorResponse) => {
          this.payErrorMessages.push((error.error != null && error.error !== undefined)
            ? error.error.message : `${error.statusText} : ${error.message}`);
          this.loadConceptos();
          this.loading = false;
        });
      }else {
        this.loading = false;
      }
  }

  
  private loadConceptos() {
    this.invoiceService.getInvoiceSaldo(this.factura.folio).subscribe(a => this.payment.monto = a);
          this.invoiceService.getComplementosInvoice(this.factura.folio)
          .pipe(
            map((facturas: Factura[]) => {
              return facturas.map(record => {
                record.statusFactura = this.validationCat.find(v => v.id === record.statusFactura).nombre;
                return record;
              });
            })).subscribe(complementos => {
            this.factura.complementos = complementos;
            this.calculatePaymentSum(complementos);
            this.loading = false;
          });
  }

  calculatePaymentSum(complementos: Factura[]) {
    if (complementos.length === 0) {
      this.paymentSum = 0;
    } else {
      this.paymentSum = complementos.map((c: Factura) => c.total).reduce((total, c) => total + c);
    }
  }

}
