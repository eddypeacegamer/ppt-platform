import { Component, OnInit, Input, ElementRef, ViewChild, TemplateRef } from '@angular/core';
import { PaymentsData } from '../../../@core/data/payments-data';
import { PagosValidatorService } from '../../../@core/util-services/pagos-validator.service';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { CuentasData } from '../../../@core/data/cuentas-data';
import { Catalogo } from '../../../models/catalogos/catalogo';
import { Cuenta } from '../../../models/cuenta';
import { FilesData } from '../../../@core/data/files-data';
import { User } from '../../../models/user';
import { UsersData } from '../../../@core/data/users-data';
import { GenericPage } from '../../../models/generic-page';
import { NbSortDirection, NbSortRequest, NbTreeGridDataSource, NbTreeGridDataSourceBuilder, NbDialogService } from '@nebular/theme';
import { AsignacionPagosComponent } from '../asignacion-pagos/asignacion-pagos.component';
import { PagoBase } from '../../../models/pago-base';
import { map } from 'rxjs/operators';
import { empty } from 'rxjs';
import { PagoFactura } from '../../../models/pago-factura';
import { Contribuyente } from '../../../models/contribuyente';
import { ClientsData } from '../../../@core/data/clients-data';
import { Client } from '../../../models/client';
import { Factura } from '../../../models/factura/factura';


interface TreeNode<T> {
  data: T;
  children?: TreeNode<T>[];
  expanded?: boolean;
}

interface PagoFacturaModel {
  id: number;
  monto: number;
  acredor: string;
  deudor: string;
  fechaCreacion: Date;
  fechaActualizacion: Date;
  moneda?: string;
  banco?: string;
  cuenta?: string;
  tipoDeCambio?: number;
  formaPago?: string;
  statusPago?: string;
  solicitante?: string;
  revision1?: boolean;
  revision2?: boolean;
  revisor1?: string;
  revisor2?: string;
  fechaPago?: Date;
  idCfdi?: number;
  folio?: string;
  totalFactura?: number;
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
  public newPayment: PagoBase = new PagoBase();
  public promotorPayments: GenericPage<PagoBase> = new GenericPage();
  public pageSize = '10';
  public payErrorMessages: string[] = [];
  public payTypeCat: Catalogo[] = [];
  public cuentas: Cuenta[];
  public loading: boolean = false;

  public clientsCat: Contribuyente[] = [];
  public companiesCat: Contribuyente[] = [];

  public selectedClient: Contribuyente;
  public selectedCompany: Contribuyente;



  customColumn = 'ACCIONES';
  defaultColumns = [ 'MONTO', 'statusPago','moneda', 'banco', 'fechaPago', 'acredor', 'deudor', 'folio' ];
  allColumns = [ this.customColumn, ...this.defaultColumns ];

  dataSource: NbTreeGridDataSource<PagoFacturaModel>;

  sortColumn: string;
  sortDirection: NbSortDirection = NbSortDirection.NONE;
  public page: GenericPage<any> = new GenericPage();

  public filterParams: any = {};

  constructor(
    private paymentsService: PaymentsData,
    private dialogService: NbDialogService,
    private invoiceService: InvoicesData,
    private accountsService: CuentasData,
    private fileService: FilesData,
    private paymentValidator: PagosValidatorService,
    private clientsService: ClientsData,
    private usersService: UsersData,
    private dataSourceBuilder: NbTreeGridDataSourceBuilder<PagoFacturaModel>) {}

  ngOnInit() {
    this.newPayment.moneda = 'MXN';
    this.paymentsService.getFormasPago()
        .subscribe(payTypes => this.payTypeCat = payTypes);
    this.usersService.getUserInfo()
      .then(user => {
        this.user = user;
        this.filterParams.solicitante = user.email;
        this.clientsService.getClientsByPromotor(user.email)
        .pipe(
          map((clients: Client[]) => clients.map(c => c.informacionFiscal)),
        ).subscribe(clients => {
          this.clientsCat = clients;
        });
      }).then(() => this.updateDataTable());
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

  selectClient(cliente: Contribuyente) {
    this.selectedClient = cliente;
    this.invoiceService
      .getInvoices(0, 10000, {remitente: cliente.razonSocial, solicitante: this.user.email })
      .pipe(
        map((page: GenericPage<Factura>) => {
          return page.content.map(f => new Contribuyente(f.rfcEmisor, f.razonSocialEmisor));
        })).subscribe(companies => this.companiesCat = companies);
  }

  onCompanySelected(company: any) {
    this.selectedCompany = this.companiesCat.find(c => c.rfc === company);
    console.log(this.selectedCompany);
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

  //

  public updateDataTable(currentPage?: number, pageSize?: number) {
    const pageValue = currentPage || 0;
    const sizeValue = pageSize || 10;
    this.paymentsService.getAllPayments(pageValue, sizeValue, this.filterParams)
    .pipe(
      map((page: GenericPage<PagoBase>) => {
        const newPage: GenericPage<TreeNode<PagoFacturaModel>> =
                      Object.assign({}, page, {content: undefined});
        newPage.content = page.content
                      .map( (pago: PagoBase) => {
                        const children: TreeNode<PagoFacturaModel>[] = pago.facturas
                                  .map((fact: PagoFactura) => {
                                    const childNode: TreeNode<PagoFacturaModel> = {
                                      data : Object.assign({}, fact),
                                    };
                                    return childNode;
                                  });
                        const node: TreeNode<PagoFacturaModel> = {
                          data : Object.assign({}, pago, {facturas: undefined}),
                          children : children,
                        };
                        return node;
                      });
        return newPage;
      }),
    ).subscribe((page: GenericPage<TreeNode<PagoFacturaModel>>) => {
      console.log(page);
      this.page = page;
      this.dataSource = this.dataSourceBuilder.create(page.content);
      });
  }

  public onChangePageSize(pageSize: number) {
    this.updateDataTable(this.page.number, pageSize);
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


  public openPaymentAssigments(pago?: PagoBase) {
    const payment = pago || new PagoBase();
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

