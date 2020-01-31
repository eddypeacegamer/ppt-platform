import { Component, OnInit, Input, TemplateRef } from '@angular/core';
import { NbDialogRef, NbDialogService } from '@nebular/theme';
import { Transferencia } from '../../../../models/transferencia';
import { Giro } from '../../../../models/catalogos/giro';
import { Empresa } from '../../../../models/empresa';
import { UsoCfdi } from '../../../../models/catalogos/uso-cfdi';
import { Catalogo } from '../../../../models/catalogos/catalogo';
import { Concepto } from '../../../../models/factura/concepto';
import { Factura } from '../../../../models/factura/factura';
import { CatalogsData } from '../../../../@core/data/catalogs-data';
import { CompaniesData } from '../../../../@core/data/companies-data';
import { InvoicesData } from '../../../../@core/data/invoices-data';
import { UsersData, User } from '../../../../@core/data/users-data';
import { HttpErrorResponse } from '@angular/common/http';
import { Cfdi } from '../../../../models/factura/cfdi';
import { map } from 'rxjs/operators';
import { CfdiValidatorService } from '../../../../@core/util-services/cfdi-validator.service';
import { Contribuyente } from '../../../../models/contribuyente';

@Component({
  selector: 'ngx-invoice-request',
  templateUrl: './invoice-request.component.html',
  styleUrls: ['./invoice-request.component.scss']
})
export class InvoiceRequestComponent implements OnInit {

  @Input() transfer: Transferencia;

  public girosCat: Giro[] = [];
  public companiesCat: Empresa[] = [];
  public usoCfdiCat: UsoCfdi[] = [];
  public validationCat: Catalogo[] = [];
  public payCat: Catalogo[] = [];
  public devolutionCat: Catalogo[] = [];
  public payTypeCat: Catalogo[];

  public newConcep: Concepto;
  public factura: Factura;
  public folioParam: string;
  public user: User;

  public complementos: Factura[] = [];
  public successMessage: string;
  public errorMessages: string[] = [];
  public formInfo = { clientRfc: '', companyRfc: '', giro: '*', empresa: '*', usoCfdi: '*', payType: '*' };
  public clientInfo: Contribuyente;
  public companyInfo: Empresa;
  public loading: boolean = false;

  constructor(
    protected ref: NbDialogRef<InvoiceRequestComponent>,
    private catalogsService: CatalogsData,
    private companiesService: CompaniesData,
    private invoiceService: InvoicesData,
    private cfdiValidator: CfdiValidatorService,
    private userService: UsersData,
    ) { }

  ngOnInit() {

    console.log(this.transfer);


    this.userService.getUserInfo().subscribe(user => this.user = user as User);
    this.initVariables();
    this.companiesService.getCompanyByRFC(this.transfer.rfcRetiro).subscribe(
      emisor => {
        this.factura.rfcEmisor = emisor.informacionFiscal.rfc;
        this.factura.razonSocialEmisor = emisor.informacionFiscal.razonSocial;
        this.factura.cfdi.emisor.regimenFiscal = emisor.regimenFiscal;
      },
      (error: HttpErrorResponse) => this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`)
    );

    this.companiesService.getCompanyByRFC(this.transfer.rfcDeposito).subscribe(
      receptor => {
        this.factura.rfcRemitente = receptor.informacionFiscal.rfc;
        this.factura.razonSocialRemitente = receptor.informacionFiscal.razonSocial;
      },
      (error: HttpErrorResponse) => this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`)
    );

    this.catalogsService.getInvoiceCatalogs().subscribe(results => {
      this.girosCat = results[0];
      this.usoCfdiCat = results[2];
      this.payCat = results[3];
      this.devolutionCat = results[4];
      this.validationCat = results[5];
      this.payTypeCat = results[6];
    });
  }

  ngOnDestroy() {
    /** CLEAN VARIABLES **/
    this.newConcep = new Concepto();
    this.factura = new Factura();
  }

  public initVariables() {
    /** INIT VARIABLES **/
    this.newConcep = new Concepto();
    this.factura = new Factura();
    this.errorMessages = [];
    this.loading = false;
    this.factura.cfdi.moneda = 'MXN';
    this.factura.cfdi.metodoPago = '*';
  }

  exit() {
    this.ref.close();
  }

  public validarRestante(): boolean {
    return Math.abs(this.transfer.importe - this.factura.cfdi.total) < 0.01;
  }
  public calcularPrecioUnitario(concepto: Concepto) {
    if (concepto.cantidad < 1) {
      alert('NO es posible calcular montos unitarios para valores menores a 1');
    }else {
      const restante  = this.transfer.importe - this.factura.cfdi.total;
      if (concepto.iva === true){
        concepto.valorUnitario = restante / (1.16 * concepto.cantidad);
      }else{
        concepto.valorUnitario = restante / concepto.cantidad;
      }
    }
  }

  public getInvoiceByFolio(folio: string) {
    this.invoiceService.getInvoiceByFolio(folio).pipe(
      map((fac: Factura) => {
        fac.cfdi.receptor.usoCfdi = this.usoCfdiCat.find(u => u.clave === fac.cfdi.receptor.usoCfdi).descripcion;
        fac.statusFactura = this.validationCat.find(v => v.id === fac.statusFactura).nombre;
        fac.statusPago = this.payCat.find(v => v.id === fac.statusPago).nombre;
        fac.statusDevolucion = this.devolutionCat.find(v => v.id === fac.statusDevolucion).nombre;
        fac.cfdi.formaPago = this.payTypeCat.find(v => v.id === fac.cfdi.formaPago).nombre;
        return fac;
      })).subscribe(invoice => {
        this.factura = invoice;
        if (invoice.cfdi.metodoPago === 'PPD') {
          this.invoiceService.getComplementosInvoice(folio)
            .pipe(
              map((facturas: Factura[]) => {
                return facturas.map(record => {
                  record.statusFactura = this.validationCat.find(v => v.id === record.statusFactura).nombre;
                  record.statusPago = this.payCat.find(v => v.id === record.statusPago).nombre;
                  record.statusDevolucion = this.devolutionCat.find(v => v.id === record.statusDevolucion).nombre;
                  record.cfdi.formaPago = this.payTypeCat.find(v => v.id === record.cfdi.formaPago).nombre;
                  return record;
                })
              }))
            .subscribe(complementos => this.complementos = complementos);
        }
      },
        error => {
          console.error('Info cant found, creating a new invoice:', error);
          this.initVariables();
        });
  }

  onGiroSelection(giroId: string) {
    const value = +giroId;
    if (isNaN(value)) {
      this.companiesCat = [];
    } else {
      this.companiesService.getCompaniesByLineaAndGiro('A', Number(giroId))
        .subscribe(companies => this.companiesCat = companies,
          (error: HttpErrorResponse) =>
            this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
    }
  }

  onCompanySelected(companyId: string) {
    this.companyInfo = this.companiesCat.find(c => c.id === Number(companyId));
  }

  onPayMethodSelected(clave: string) {
    this.catalogsService.getFormasPago(clave)
      .subscribe(cat => {
        this.payTypeCat = cat;
        this.formInfo.payType = this.payTypeCat[0].id;
        if (clave === 'PPD') {
          this.factura.cfdi.formaPago = '99';
          this.factura.cfdi.metodoPago = 'PPD';
        } else {
          this.factura.cfdi.metodoPago = 'PUE';
          this.factura.cfdi.formaPago = '01';
        }
      });
  }


  onUsoCfdiSelected(clave: string) {
    this.factura.cfdi.receptor.usoCfdi = clave;
  }

  onFormaDePagoSelected(clave: string) {
    this.factura.cfdi.formaPago = clave;
  }

  limpiarForma() {
    this.initVariables();
    this.clientInfo = undefined;
    this.companyInfo = undefined;
    this.successMessage = undefined;
    this.newConcep = new Concepto();
    this.factura = new Factura();
    this.factura.cfdi = new Cfdi();
    this.factura.cfdi.conceptos = [];
    this.errorMessages = [];
  }

  solicitarCfdi() {

    this.errorMessages = [];
    this.successMessage = undefined;
    this.factura.lineaEmisor = this.transfer.lineaRetiro;
    this.factura.lineaRemitente = this.transfer.lineaDeposito;
    this.factura.statusFactura = '4';//SET AS DEFAULT POR TIMBRAR
    this.factura.solicitante = this.user.email;
    this.factura.rfcEmisor = this.transfer.rfcRetiro;
    this.factura.razonSocialEmisor = this.companyInfo.informacionFiscal.razonSocial;
    this.factura.rfcRemitente = this.transfer.rfcDeposito;
    this.factura.rfcEmisor = this.transfer.rfcRetiro;
    this.factura.rfcRemitente = this.transfer.rfcDeposito;

    this.errorMessages = [];

    this.factura.rfcEmisor = this.companyInfo.informacionFiscal.rfc;

    this.factura.cfdi.emisor.regimenFiscal = this.companyInfo.regimenFiscal;

    this.errorMessages = this.cfdiValidator.validarCfdi({ ...this.factura.cfdi });
    if (this.errorMessages.length < 1) {
      this.invoiceService.insertNewInvoice(this.factura)
        .subscribe((invoice: Factura) => {
          this.factura.folio = invoice.folio;
          this.successMessage = 'Solicitud de factura enviada correctamente';
        }, (error: HttpErrorResponse) => {
          this.errorMessages.push((error.error != null && error.error !== undefined) ?
            error.error.message : `${error.statusText} : ${error.message}`);
        });
    }
  }

}
