import { Component, OnInit, TemplateRef, OnDestroy } from '@angular/core';
import { NbDialogService } from '@nebular/theme';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { ClientsData } from '../../../@core/data/clients-data';
import { CompaniesData } from '../../../@core/data/companies-data';
import { Giro } from '../../../models/catalogos/giro';
import { HttpErrorResponse } from '@angular/common/http';
import { Contribuyente } from '../../../models/contribuyente';
import { Empresa } from '../../../models/empresa';
import { ClaveProductoServicio } from '../../../models/catalogos/producto-servicio';
import { Concepto } from '../../../models/factura/concepto';
import { ClaveUnidad } from '../../../models/catalogos/clave-unidad';
import { Cfdi } from '../../../models/factura/cfdi';
import { Client } from '../../../models/client';
import { UsoCfdi } from '../../../models/catalogos/uso-cfdi';
import { Factura } from '../../../models/factura/factura';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { ActivatedRoute, Router } from '@angular/router';
import { Catalogo } from '../../../models/catalogos/catalogo';
import { map } from 'rxjs/operators';
import { DownloadInvoiceFilesService } from '../../../@core/util-services/download-invoice-files';
import { PaymentsData } from '../../../@core/data/payments-data';
import { UsersData, User } from '../../../@core/data/users-data';
import { FilesData } from '../../../@core/data/files-data';

@Component({
  selector: 'ngx-revision',
  templateUrl: './revision.component.html',
  styleUrls: ['./revision.component.scss']
})
export class RevisionComponent implements OnInit {

  public girosCat: Giro[] = [];
  public companiesCat: Empresa[] = [];
  public prodServCat: ClaveProductoServicio[] = [];
  public claveUnidadCat: ClaveUnidad[] = [];
  public usoCfdiCat: UsoCfdi[] = [];
  public validationCat: Catalogo[] = [];
  public payCat: Catalogo[] = [];
  public devolutionCat: Catalogo[] = [];
  public payTypeCat: Catalogo[] = [new Catalogo('01', 'Efectivo'), new Catalogo('02', 'Cheque nominativo'), new Catalogo('03', 'Transferencia electrónica de fondos'), new Catalogo('99', 'Por definir')];

  public newConcep: Concepto;
  public factura: Factura;
  public folioParam: string;
  public user: User;

  public complementos: Factura[] = [];
  public headers: string[] = ['Producto Servicio', 'Cantidad', 'Clave Unidad', 'Unidad', 'Descripcion', 'Valor Unitario', 'Impuesto', 'Importe'];
  public successMessage: string;
  public errorMessages: string[] = [];
  public conceptoMessages: string[] = [];
  public payErrorMessages: string[] = [];

  public formInfo = { clientRfc: '', companyRfc: '', claveProdServ: '', giro: '*', empresa: '*', usoCfdi: '*', moneda: '*', payMethod: '*', payType: '*', prodServ: '*', unidad: '*', statusDetail :'' }
  
  public clientInfo: Contribuyente;
  public companyInfo: Empresa;

  public loading : boolean = false;


  constructor(private dialogService: NbDialogService,
    private catalogsService: CatalogsData,
    private clientsService: ClientsData,
    private companiesService: CompaniesData,
    private invoiceService: InvoicesData,
    private filesService: FilesData,
    private userService: UsersData,
    private downloadService: DownloadInvoiceFilesService,
    private route: ActivatedRoute) { }

    ngOnInit() {
      this.userService.getUserInfo().subscribe(user => this.user = user as User);
      this.initVariables();
      this.catalogsService.getInvoiceCatalogs()
          .toPromise().then(results => {
            this.girosCat = results[0];
            this.usoCfdiCat = results[2];
            this.payCat = results[3];
            this.devolutionCat = results[4];
            this.validationCat = results[5];
            this.payTypeCat = results[6];
          }).then(() => {
            this.route.paramMap.subscribe(route => {
              this.folioParam = route.get('folio');
              if (this.folioParam !== '*') {
                this.getInvoiceByFolio(this.folioParam);
              }else {
                this.initVariables();
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
                    //record.cfdi.formaPago = this.payTypeCat.find(v => v.id === record.cfdi.formaPago).nombre;
                    return record;
                  })
                }))
              .subscribe(complementos => this.factura.complementos = complementos);
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
  
    buscarClientInfo() {
      this.errorMessages = [];
      this.clientsService.getClientByRFC(this.formInfo.clientRfc).subscribe(
        (client: Client) => {
          if (client.activo === true) {
            this.clientInfo = client.informacionFiscal;
          } else {
            alert(`El cliente ${client.informacionFiscal.razonSocial} no se encuentra activo en el sistema`);
            this.formInfo.clientRfc = '';
          }
        }, (error: HttpErrorResponse) => this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
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

  public downloadPdf(folio: string) {
    this.filesService.getFacturaFile(folio, 'PDF').subscribe(
      file => this.downloadService.downloadFile(file.data, `${this.factura.folio}-${this.factura.rfcEmisor}-${this.factura.rfcRemitente}.pdf`, 'application/pdf;')
    )
  }
  public downloadXml(folio: string) {
    this.filesService.getFacturaFile(folio, 'XML').subscribe(
      file => this.downloadService.downloadFile(file.data, `${this.factura.folio}-${this.factura.rfcEmisor}-${this.factura.rfcRemitente}.xml`, 'text/xml;charset=utf8;')
    )
  }

  public aceptarFactura() {
    this.loading = true;
    this.successMessage = undefined;
    this.errorMessages = [];
    const fact = { ...this.factura };
    if (fact.cfdi.metodoPago === 'PPD') {
      fact.statusFactura = '4'; // update to por timbrar
    }else {
      fact.statusFactura = '2'; // update to validacion tesoreria
    }
    fact.statusPago = this.payCat.find(v => v.nombre === fact.statusPago).id;
    fact.statusDevolucion = this.devolutionCat.find(v => v.nombre == fact.statusDevolucion).id;
    fact.cfdi.formaPago = this.payTypeCat.find(v => v.nombre == fact.cfdi.formaPago).id;
    this.invoiceService.updateInvoice(fact).subscribe(result => { 
      this.loading = false;
      console.log('factura actualizada correctamente');
      this.getInvoiceByFolio(fact.folioPadre || fact.folio);},
      (error: HttpErrorResponse) => {
        this.loading = false;
        this.errorMessages.push((error.error != null && error.error != undefined) ? error.error.message : `${error.statusText} : ${error.message}`);
      });
  }

  public rechazarFactura(){
    this.loading = true;
    this.successMessage = undefined;
    this.errorMessages = [];
    let fact = { ...this.factura };
    fact.statusFactura = '6';// update to recahzo operaciones
    fact.statusPago = this.payCat.find(v => v.nombre === fact.statusPago).id;
    fact.statusDevolucion = this.devolutionCat.find(v => v.nombre === fact.statusDevolucion).id;
    this.invoiceService.updateInvoice(fact).subscribe(result => { 
      this.loading = false;
      console.log('factura actualizada correctamente');
      this.getInvoiceByFolio(fact.folioPadre || fact.folio);},
      (error: HttpErrorResponse) => {
        this.loading = false;
        this.errorMessages.push((error.error != null && error.error != undefined) ? error.error.message : `${error.statusText} : ${error.message}`);
      });
  }

  public timbrarFactura(factura: Factura, dialog: TemplateRef<any>) {
    this.loading = true;
    this.successMessage = undefined;
    this.errorMessages = [];
    let fact = { ...factura };
    fact.cfdi = null;
    console.log(this.payTypeCat)

    fact.statusFactura = this.validationCat.find(v => v.nombre === fact.statusFactura).id;
    fact.statusPago = this.payCat.find(v => v.nombre === fact.statusPago).id;
    fact.statusDevolucion = this.devolutionCat.find(v => v.nombre === fact.statusDevolucion).id;

    this.dialogService.open(dialog, { context: fact })
      .onClose.subscribe(invoice => {
        if (invoice !== undefined) {
          this.invoiceService.timbrarFactura(fact.folio, invoice)
            .subscribe(result => { 
              this.loading = false;
              console.log('factura timbrada correctamente');
              this.getInvoiceByFolio(fact.folioPadre || fact.folio);},
              (error: HttpErrorResponse) => {
                this.loading = false;
                this.errorMessages.push((error.error != null && error.error != undefined) ? error.error.message : `${error.statusText} : ${error.message}`);
              });
        }else{
          this.loading = false;
        }
      }
      );
  }

  public cancelarFactura(factura: Factura) {
    this.loading = true;
    this.successMessage = undefined;
    this.errorMessages = [];
    let fact = { ...factura };
    fact.cfdi = null;
    fact.statusFactura = this.validationCat.find(v => v.nombre === fact.statusFactura).id;
    fact.statusPago = this.payCat.find(v => v.nombre === fact.statusPago).id;
    fact.statusDevolucion = this.devolutionCat.find(v => v.nombre == fact.statusDevolucion).id;
    fact.cfdi.formaPago = this.payTypeCat.find(v => v.nombre == fact.cfdi.formaPago).id;

    this.invoiceService.cancelarFactura(fact.folio, fact)
      .subscribe(success =>{this.successMessage = 'Factura correctamente cancelada'; 
      this.getInvoiceByFolio(fact.folioPadre || fact.folio);
      this.loading = false;},
        (error: HttpErrorResponse) => { 
          this.errorMessages.push((error.error != null && error.error != undefined) ? error.error.message : `${error.statusText} : ${error.message}`); 
          this.loading = false;
          console.error(this.errorMessages); });
  }

}
