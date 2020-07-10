import { Component, OnInit, Input } from '@angular/core';
import { PagoBase } from '../../../models/pago-base';
import { GenericPage } from '../../../models/generic-page';
import { Factura } from '../../../models/factura/factura';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { map } from 'rxjs/operators';
import { ClientsData } from '../../../@core/data/clients-data';
import { Client } from '../../../models/client';
import { UsersData } from '../../../@core/data/users-data';
import { User } from '../../../models/user';
import { Contribuyente } from '../../../models/contribuyente';
import { Cuenta } from '../../../models/cuenta';
import { CuentasData } from '../../../@core/data/cuentas-data';
import { FilesData } from '../../../@core/data/files-data';
import { PagosValidatorService } from '../../../@core/util-services/pagos-validator.service';
import { PaymentsData } from '../../../@core/data/payments-data';
import { ResourceFile } from '../../../models/resource-file';
import { HttpErrorResponse } from '@angular/common/http';
import { PagoFactura } from '../../../models/pago-factura';
import { NbDialogRef } from '@nebular/theme';
import { Catalogo } from '../../../models/catalogos/catalogo';

@Component({
  selector: 'ngx-asignacion-pagos',
  templateUrl: './asignacion-pagos.component.html',
  styleUrls: ['./asignacion-pagos.component.scss'],
})
export class AsignacionPagosComponent implements OnInit {

  public page: GenericPage<any>;
  public user: User;
  public fileInput: any;
  public paymentForm = { payType: '*', bankAccount: '*', filename: ''};
  public newPayment: PagoBase = new PagoBase();
  public payErrorMessages: string[] = [];
  public payTypeCat: Catalogo[] = [];
  public cuentas: Cuenta[];
  public loading: boolean = false;

  public clientsCat: Contribuyente[] = [];
  public companiesCat: Contribuyente[] = [];

  public selectedClient: Contribuyente;
  public selectedCompany: Contribuyente;

  public filterParams = {solicitante: '', emisor: '', remitente: ''};
  constructor(
    protected ref: NbDialogRef<AsignacionPagosComponent>,
    private paymentsService: PaymentsData,
    private userService: UsersData,
    private clientsService: ClientsData,
    private invoiceService: InvoicesData,
    private accountsService: CuentasData,
    private fileService: FilesData,
    private paymentValidator: PagosValidatorService) {
  }

  ngOnInit() {
    this.newPayment.moneda = 'MXN';
    this.page = new GenericPage();
    this.filterParams = {solicitante: '', emisor: '', remitente: ''};
    this.paymentsService.getFormasPago()
        .subscribe(payTypes => this.payTypeCat = payTypes);

    this.userService.getUserInfo().then(user => {
      this.user = user;
      this.filterParams.solicitante = user.email;
      this.clientsService.getClientsByPromotor(user.email)
        .pipe(
          map((clients: Client[]) => clients.map(c => c.informacionFiscal)),
        ).subscribe(clients => {
          this.clientsCat = clients;
        });
    });
  }

  exit() {
    this.ref.close();
  }


  selectClient(cliente: Contribuyente) {
    this.selectedClient = cliente;
    this.filterParams.remitente = cliente.razonSocial;
    this.invoiceService
      .getInvoices(0, 10000, {remitente: cliente.razonSocial, solicitante: this.user.email })
      .pipe(
        map((page: GenericPage<Factura>) => {
          return page.content.map(f => new Contribuyente(f.rfcEmisor, f.razonSocialEmisor));
        })).subscribe(companies => this.companiesCat = companies);
  }

  onCompanySelected(company: any) {
    this.selectedCompany = this.companiesCat.find(c => c.rfc === company);
    this.filterParams.emisor = this.selectedCompany.razonSocial;
    this.updateDataTable(0, 100);
  }

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
      this.accountsService.getCuentasByCompany(this.selectedCompany.rfc)
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

  sendPayment() {
    this.newPayment.solicitante = this.user.email;
    const payment  = {... this.newPayment};
    this.payErrorMessages = this.paymentValidator.validatePagoSimple(payment);
    if (this.payErrorMessages.length === 0) {
      this.loading = true;
      payment.acredor = this.selectedCompany.razonSocial;
      payment.deudor = this.selectedClient.razonSocial;
      this.paymentsService.insertNewPayment(payment).subscribe(
        result => {
          const resourceFile = new ResourceFile();
          resourceFile.tipoArchivo = 'IMAGEN';
          resourceFile.tipoRecurso = 'PAGO';
          resourceFile.referencia  = `${result.id}`;
          resourceFile.data = payment.documento;
          this.fileService.insertResourceFile(resourceFile).subscribe(response => console.log(response));
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


  public updateDataTable(currentPage?: number, pageSize?: number) {
    const pageValue = currentPage || 0;
    const sizeValue = pageSize || 10;
    this.invoiceService.getInvoices(pageValue, sizeValue, this.filterParams)
      .subscribe((result: GenericPage<any>) => this.page = result,
      error=> console.log(error));
  }



  cancel() {
    this.ref.close();
  }

}
