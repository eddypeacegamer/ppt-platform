import { Component, OnInit, Input, ElementRef, ViewChild, TemplateRef } from '@angular/core';
import { PaymentsData } from '../../../@core/data/payments-data';
import { PagosValidatorService } from '../../../@core/util-services/pagos-validator.service';
import { PagoFactura } from '../../../models/pago-factura';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { CuentasData } from '../../../@core/data/cuentas-data';
import { Catalogo } from '../../../models/catalogos/catalogo';
import { Cuenta } from '../../../models/cuenta';
import { FilesData } from '../../../@core/data/files-data';
import { User } from '../../../models/user';
import { UsersData } from '../../../@core/data/users-data';
import { GenericPage } from '../../../models/generic-page';
import { NbSortDirection, NbSortRequest, NbTreeGridDataSource, NbTreeGridDataSourceBuilder, NbDialogService } from '@nebular/theme';
import { Factura } from '../../../models/factura/factura';
import { map } from 'rxjs/operators';
import { AsignacionPagosComponent } from '../asignacion-pagos/asignacion-pagos.component';


interface TreeNode<T> {
  data: T;
  children?: TreeNode<T>[];
  expanded?: boolean;
}

interface PagoFacturaModel {
  ID: string;
  TIPO: string;
  MONTO: number;
  FECHA: string;
  STATUS?: string;
  SALDO?: number;
  FOLIO?: number;
}

@Component({
  selector: 'ngx-pagos-factura',
  templateUrl: './pagos-factura.component.html',
  styleUrls: ['./pagos-factura.component.scss'],
})
export class PagosFacturaComponent implements OnInit {

  public user: User;

  public fileInput: any;

  

  public paymentForm = { payType: '*', bankAccount: '*', filename: ''};
  public newPayment: PagoFactura = new PagoFactura();
  public promotorPayments: GenericPage<PagoFactura> = new GenericPage();
  public paymentSum: number = 0;
  public payErrorMessages: string[] = [];
  public payTypeCat: Catalogo[] = [];
  public cuentas: Cuenta[];
  public loading: boolean = false;

  customColumn = 'ACCIONES';
  defaultColumns = [ 'MONTO', 'STATUS', 'FOLIO', 'FECHA' ];
  allColumns = [ this.customColumn, ...this.defaultColumns ];

  dataSource: NbTreeGridDataSource<PagoFacturaModel>;

  sortColumn: string;
  sortDirection: NbSortDirection = NbSortDirection.NONE;

  private data: TreeNode<PagoFacturaModel>[] = [
    {
      data: { ID: '22', MONTO: 3500, TIPO: 'D', FECHA: '2020-05-20', SALDO: 1000, STATUS: 'VALIDACION'},
      children: [
        { data: { ID: '22', MONTO: -1500, TIPO: 'P', FECHA: '2020-05-22', FOLIO: 2020456789034} },
        { data: { ID: '22', MONTO: -1000, TIPO: 'P', FECHA: '2020-05-22', FOLIO: 2020456783456} },
      ],
    },
    {
      data: { ID: '19', MONTO: 7500, TIPO: 'D', FECHA: '2020-05-12', SALDO: 500, STATUS: 'RECHAZADO' },
      children: [
        { data: { ID: '19', MONTO: -3000, TIPO: 'P', FECHA: '2020-05-15', FOLIO: 2020456789876} },
        { data: { ID: '19', MONTO: -4000, TIPO: 'P', FECHA: '2020-05-15', FOLIO: 2020434567899 } },
      ],
    },
    {
      data: { ID: '11', MONTO: 10000, TIPO: 'D', FECHA: '2020-05-03', SALDO: 0, STATUS: 'ACEPTADO'},
      children: [
        { data: { ID: '11', MONTO: -2500, TIPO: 'P', FECHA: '2020-05-08', FOLIO: 2020498765455 } },
        { data: { ID: '11', MONTO: -2500, TIPO: 'P', FECHA: '2020-05-08', FOLIO: 2020764234578 } },
        { data: { ID: '11', MONTO: -2500, TIPO: 'P', FECHA: '2020-05-05', FOLIO: 2020567887895 } },
        { data: { ID: '11', MONTO: -2500, TIPO: 'P', FECHA: '2020-05-05', FOLIO: 2020386468909 } },
      ],
    }];

  constructor(
    private paymentsService: PaymentsData,
    private dialogService: NbDialogService,
    private accountsService: CuentasData,
    private fileService: FilesData,
    private paymentValidator: PagosValidatorService,
    private usersService: UsersData,
    private invoiceService: InvoicesData,
    private dataSourceBuilder: NbTreeGridDataSourceBuilder<PagoFacturaModel>) {
      this.dataSource = this.dataSourceBuilder.create(this.data);
    }

  ngOnInit() {
    this.newPayment.moneda = 'MXN';
    this.usersService.getUserInfo()
      .then(user => this.user = user)
      .then(() => {
        this.paymentsService.getAllPayments(0, 10, {})
              .subscribe((page: GenericPage<PagoFactura>) => {
                this.promotorPayments = page;
                });
      });
  }

  getShowOn(index: number) {
    const minWithForMultipleColumns = 400;
    const nextColumnStep = 100;
    return minWithForMultipleColumns + (nextColumnStep * index);
  }
  updateSort(sortRequest: NbSortRequest): void {
    this.sortColumn = sortRequest.column;
    this.sortDirection = sortRequest.direction;
  }

  getSortDirection(column: string): NbSortDirection {
    if (this.sortColumn === column) {
      return this.sortDirection;
    }
    return NbSortDirection.NONE;
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
      /*
      this.accountsService.getCuentasByCompany(this.factura.rfcEmisor)
          .subscribe(cuentas => {
            this.cuentas = cuentas;
            this.paymentForm.bankAccount = cuentas[0].id;
            this.newPayment.banco = cuentas[0].banco;
            this.newPayment.cuenta = cuentas[0].cuenta;
          });*/
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


  public openPaymentAssigments(pago?: PagoFactura) {
    const payment = pago || new PagoFactura();
    payment.monto = 3500;
    payment.moneda = 'MXN';
    this.dialogService.open(AsignacionPagosComponent, {
      context: {
        payment : payment,
      },
    }).onClose.subscribe(() => console.log('Closing view'));
  }

  deletePayment(paymentId) {
    /*
    this.paymentsService.deletePayment(this.factura.folio, paymentId).subscribe(
      result => {
        this.paymentsService.getPaymentsByFolio(this.factura.folio)
          .subscribe(payments => {
            this.invoicePayments = payments;
            this.paymentSum = this.paymentValidator.getPaymentAmmount(payments);
          });
        this.invoiceService.getComplementosInvoice(this.factura.folio)
          .subscribe(complementos => this.factura.complementos = complementos);
      }, (error: HttpErrorResponse) =>
      this.payErrorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));*/
  }

  sendPayment() {
    /*
    this.newPayment.folioPadre = this.factura.folio;
    this.newPayment.folio = this.factura.folio;
    this.newPayment.tipoPago = 'INGRESO';
    this.newPayment.acredor = this.factura.razonSocialEmisor;
    this.newPayment.deudor = this.factura.razonSocialRemitente;
    this.newPayment.solicitante = this.user.email;
    const payment  = {... this.newPayment};
    this.payErrorMessages = this.paymentValidator.validatePago(payment, this.invoicePayments, this.factura.cfdi);
    if (this.payErrorMessages.length === 0) {
      this.loading = true;
      this.paymentsService.insertNewPayment(this.factura.folio, payment).subscribe(
        result => {
          this.newPayment = new PagoFactura();
          const resourceFile = new ResourceFile();
          resourceFile.tipoArchivo = 'IMAGEN';
          resourceFile.tipoRecurso = 'PAGO';
          resourceFile.referencia  = `${result.id}_${result.folio}`;
          resourceFile.data = payment.documento;
          this.fileService.insertResourceFile(resourceFile).subscribe(response => console.log(response));
          this.paymentsService.getPaymentsByFolio(this.factura.folio)
          .subscribe((payments: PagoFactura[]) => { this.invoicePayments = payments;
            this.paymentSum = this.paymentValidator.getPaymentAmmount(payments);
            this.loading = false;
             if (payments.find(p => p.formaPago === 'CREDITO') !== undefined
                      && this.payTypeCat.find(c => c.id === 'CREDITO') !== undefined) {
                    this.payTypeCat.pop();//removes credit as option, there is no posible require a second credit
              }
          });
          this.invoiceService.getComplementosInvoice(this.factura.folio)
          .subscribe(complementos => this.factura.complementos = complementos);
        }, (error: HttpErrorResponse) => {
          this.loading = false;
          this.payErrorMessages.push(error.error.message || `${error.statusText} : ${error.message}`);
        });
    }
    this.newPayment = new PagoFactura();
    this.paymentForm = { payType: '*', bankAccount: '*', filename: ''};
    if (this.fileInput !== undefined) {
      this.fileInput.value = '';
    }*/
  }
}

