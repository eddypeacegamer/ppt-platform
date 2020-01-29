import { Component, OnInit, Input, TemplateRef } from '@angular/core';
import { NbDialogRef, NbDialogService } from '@nebular/theme';
import { Transferencia } from '../../../../models/transferencia';
import { Giro } from '../../../../models/catalogos/giro';
import { Empresa } from '../../../../models/empresa';
import { ClaveProductoServicio } from '../../../../models/catalogos/producto-servicio';
import { ClaveUnidad } from '../../../../models/catalogos/clave-unidad';
import { UsoCfdi } from '../../../../models/catalogos/uso-cfdi';
import { Catalogo } from '../../../../models/catalogos/catalogo';
import { Concepto } from '../../../../models/factura/concepto';
import { Factura } from '../../../../models/factura/factura';
import { CatalogsData } from '../../../../@core/data/catalogs-data';
import { CompaniesData } from '../../../../@core/data/companies-data';
import { InvoicesData } from '../../../../@core/data/invoices-data';
import { UsersData, User } from '../../../../@core/data/users-data';
import { HttpErrorResponse } from '@angular/common/http';
import { Impuesto } from '../../../../models/factura/impuesto';
import { Cfdi } from '../../../../models/factura/cfdi';
import { map } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';
import { TransferData } from '../../../../@core/data/transfers-data';
import { ClientsData } from '../../../../@core/data/clients-data';
import { CfdiValidatorService } from '../../../../@core/util-services/cfdi-validator.service';
import { FilesData } from '../../../../@core/data/files-data';
import { DownloadInvoiceFilesService } from '../../../../@core/util-services/download-invoice-files';
import { PdfMakeService } from '../../../../@core/util-services/pdf-make.service';
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
  public formInfo = { clientRfc: '', companyRfc: '', giro: '*', empresa: '*', usoCfdi: '*', payType: '*'};
  public clientInfo: Contribuyente;
  public companyInfo: Empresa;
  public loading: boolean = false;

  constructor(
    private catalogsService: CatalogsData,
    private companiesService: CompaniesData,
    private invoiceService: InvoicesData,
    private cfdiValidator: CfdiValidatorService,
    private userService: UsersData,
    private filesService: FilesData,
    private downloadService: DownloadInvoiceFilesService,
    private pdfMakeService: PdfMakeService,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.userService.getUserInfo().subscribe(user => this.user = user as User);
    this.initVariables();
    this.route.paramMap.subscribe(route => {
      this.folioParam = route.get('folio');
      this.catalogsService.getInvoiceCatalogs()
        .toPromise().then(results => {
          this.girosCat = results[0];
          this.usoCfdiCat = results[2];
          this.payCat = results[3];
          this.devolutionCat = results[4];
          this.validationCat = results[5];
          this.payTypeCat = results[6];
        }).then(() => {
          if (this.folioParam !== '*') {
            this.getInvoiceByFolio(this.folioParam);
          }
        });
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
   
    this.errorMessages = this.cfdiValidator.validarCfdi({...this.factura.cfdi});
    if (this.errorMessages.length < 1) {
      this.invoiceService.insertNewInvoice(this.factura)
      .subscribe((invoice: Factura) => {
        this.factura.folio = invoice.folio;
        this.successMessage = 'Solicitud de factura enviada correctamente';
      }, (error: HttpErrorResponse) => { this.errorMessages.push((error.error != null && error.error !== undefined) ?
        error.error.message : `${error.statusText} : ${error.message}`);
      });
    }
  }
  public downloadPdf(folio: string) {
    //console.log('calling pdfMakeService for :', folio)
    //this.pdfMakeService.generatePdf(this.factura);
    this.filesService.getFacturaFile(folio, 'PDF').subscribe(
      file => this.downloadService.downloadFile(file.data, `${this.factura.folio}-${this.factura.rfcEmisor}-${this.factura.rfcRemitente}.pdf`, 'application/pdf;')
    );
  }
  public downloadXml(folio: string) {
    this.filesService.getFacturaFile(folio, 'XML').subscribe(
      file => this.downloadService.downloadFile(file.data, `${this.factura.folio}-${this.factura.rfcEmisor}-${this.factura.rfcRemitente}.xml`, 'text/xml;charset=utf8;')
    )
  }

}
