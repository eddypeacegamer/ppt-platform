import { Component, OnInit, TemplateRef } from '@angular/core';
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
import { ActivatedRoute} from '@angular/router';
import { Catalogo } from '../../../models/catalogos/catalogo';
import { map } from 'rxjs/operators';
import { DonwloadFileService } from '../../../@core/util-services/download-file-service';
import { UsersData, User } from '../../../@core/data/users-data';
import { FilesData } from '../../../@core/data/files-data';
import { GenericPage } from '../../../models/generic-page';

@Component({
  selector: 'ngx-revision',
  templateUrl: './revision.component.html',
  styleUrls: ['./revision.component.scss']
})
export class RevisionComponent implements OnInit {

  public girosCat: Catalogo[] = [];
  public companiesCat: Empresa[] = [];
  public prodServCat: ClaveProductoServicio[] = [];
  public claveUnidadCat: ClaveUnidad[] = [];
  public usoCfdiCat: UsoCfdi[] = [];
  public validationCat: Catalogo[] = [];
  public payCat: Catalogo[] = [];
  public devolutionCat: Catalogo[] = [];
  public payTypeCat: Catalogo[] = [];
  public clientsCat: Client[] = [];
  public newConcep: Concepto;
  public factura: Factura;
  public folioParam: string;
  public user: User;

  public complementos: Factura[] = [];
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
    private downloadService: DonwloadFileService,
    private route: ActivatedRoute) { }

    ngOnInit() {
      this.userService.getUserInfo().then(user => this.user = user as User);
      this.initVariables();
      /* preloaded cats*/
      this.catalogsService.getStatusPago().then(cat => this.payCat = cat);
      this.catalogsService.getStatusDevolucion().then(cat => this.devolutionCat = cat);
      this.catalogsService.getStatusValidacion().then(cat => this.validationCat = cat);
      this.catalogsService.getFormasPago().then(cat => this.payTypeCat = cat);
      /* not pre-loaded cats*/
      this.catalogsService.getAllUsoCfdis().then(cat => this.usoCfdiCat = cat);
      this.catalogsService.getAllGiros().then(cat => this.girosCat = cat)
      .then(() => {
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
      .then(cat => {
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

    buscarClientInfo( razonSocial: string) {
      if ( razonSocial !== undefined && razonSocial.length > 5) {
        this.clientsService.getClients(0 , 20, { promotor: this.user.email, razonSocial: razonSocial })
            .pipe(map((clientsPage: GenericPage<Client>) => clientsPage.content))
            .subscribe(clients => {
              this.clientsCat = clients;
              if ( clients.length > 0) {
                this.formInfo.clientRfc = clients[0].id.toString();
                this.onClientSelected(this.formInfo.clientRfc);
              }
            }, (error: HttpErrorResponse) => {
              this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`);
              this.clientsCat = [];
              this.clientInfo = undefined;
            });
      }else {
        this.clientsCat = [];
        this.clientInfo = undefined;
      }
    }

    onClientSelected(id: string) {
      const value = +id;
      if (!isNaN(value)) {
        const client = this.clientsCat.find(c => c.id === Number(value));
        if (client.activo === true) {
          this.clientInfo = client.informacionFiscal;
        } else {
          alert(`El cliente ${client.informacionFiscal.razonSocial} no se encuentra activo en el sistema`);
        }
      }
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
    const fact = { ...factura };
    fact.cfdi = null;
    fact.statusFactura = this.validationCat.find(v => v.nombre === fact.statusFactura).id;
    fact.statusPago = this.payCat.find(v => v.nombre === fact.statusPago).id;
    fact.statusDevolucion = this.devolutionCat.find(v => v.nombre === fact.statusDevolucion).id;


    this.clientsService.getClientByRFC(this.factura.cfdi.receptor.rfc)
    .subscribe((client: Client) => {
      if ( client.activo) {
        this.dialogService.open(dialog, { context: fact })
      .onClose.subscribe(invoice => {
        if (invoice !== undefined) {
          this.invoiceService.timbrarFactura(fact.folio, invoice)
            .subscribe(result => {
              this.loading = false;
              this.getInvoiceByFolio(fact.folioPadre || fact.folio); },
              (error: HttpErrorResponse) => {
                this.loading = false;
                this.errorMessages.push((error.error != null && error.error !== undefined) ?
                   error.error.message : `${error.statusText} : ${error.message}`);
              });
        }else {
          this.loading = false;
        }
      });
      } else {
        this.loading = false;
        this.errorMessages.push('No es posible timbrar con clientes inactivos');
      }
    });
  }

  public cancelarFactura(factura: Factura) {
    this.loading = true;
    this.successMessage = undefined;
    this.errorMessages = [];
    let fact = { ...factura };
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
