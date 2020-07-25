import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http'
import { Factura } from '../../models/factura/factura';
import { Catalogo } from '../../models/catalogos/catalogo';
import { CatalogsData } from '../data/catalogs-data';
import { map } from 'rxjs/operators';
import { GenericPage } from '../../models/generic-page';
import { Pago } from '../../models/factura/pago';

@Injectable({
  providedIn: 'root',
})
export class InvoicesService {

  private validationCat: Catalogo[] = [];
  private payCat: Catalogo[] = [];
  private formaPagoCat: Catalogo[] = [];

  constructor(private httpClient: HttpClient,
              private catalogService: CatalogsData) {
    this.catalogService.getStatusPago().then(cat => this.payCat = cat);
    this.catalogService.getStatusValidacion().then(cat => this.validationCat = cat);
    this.catalogService.getFormasPago().then(cat => this.formaPagoCat = cat);
  }

  private getHttpParams(filterParams: any): HttpParams {
    let pageParams: HttpParams =  new HttpParams();
    for (const key in filterParams) {
      if (filterParams[key] !== undefined) {
      const value: string = filterParams[key].toString();
      if ( value !== null && value.length > 0 && value !== '*') {
          pageParams = pageParams.append(key, value);
        }
      }
    }
    console.log('InvoicesService- filter params:', filterParams);
    return pageParams;
  }

  public getInvoices(filterParams: any): Observable<any> {
    return this.httpClient.get('../api/facturas',
    {params: this.getHttpParams(filterParams)})
    .pipe(
      map((invPage: GenericPage<any>) => {
        const records: any[] = invPage.content.map(record => {
          record.cadenaOriginalTimbrado = '';
          record.statusFactura = this.validationCat.find(v => v.id.toString()
                                      === record.statusFactura.toString()).nombre;
          return record;
        });
        invPage.content = records;
        return invPage;
      }));
  }

  public getInvoicesReports(filterParams: any): Observable<any> {
    return this.httpClient.get('../api/facturas/factura-reports',
    {params: this.getHttpParams(filterParams)})
    .pipe(
      map((invPage: GenericPage<any>) => {
        const records: any[] = invPage.content.map(record => {
          record.statusFactura = this.validationCat.find(v => v.id.toString()
                                      === record.statusFactura.toString()).nombre;
          return record;
        });
        invPage.content = records;
        return invPage;
      }));
  }
  public getComplementReports(filterParams: any): Observable<any>{
    return this.httpClient.get('../api/facturas/complemento-reports',
    {params: this.getHttpParams(filterParams)})
    .pipe(
      map((invPage: GenericPage<any>) => {
        const records: any[] = invPage.content.map(record => {
          record.statusFactura = this.validationCat.find(v => v.id.toString()
                                      === record.statusFactura.toString()).nombre;
          return record;
        });
        invPage.content = records;
        return invPage;
      }));
  }

  public getInvoiceByFolio(folio: string): Observable<any> {
    return this.httpClient.get(`../api/facturas/${folio}`);
  }

  public getInvoiceFiles(folio: string): Observable<any>{
    return this.httpClient.get(`../api/facturas/${folio}/files`);
  }

  public getComplementosInvoice(folioPadre: string): Observable<any>{
    return this.httpClient.get(`../api/facturas/${folioPadre}/complementos`);
  }

  public timbrarFactura(folio:string,factura:Factura) : Observable<any>{
    return this.httpClient.post(`../api/facturas/${folio}/timbrar`,factura);
  }

  public cancelarFactura(folio:string,factura:Factura) : Observable<any>{
    return this.httpClient.post(`../api/facturas/${folio}/cancelar`,factura);
  }

  public insertNewInvoice(invoice : Factura): Observable<any>{
    return this.httpClient.post('../api/facturas',invoice);
  }

  public updateInvoice(invoice: Factura): Observable<any> {
    return this.httpClient.put(`../api/facturas/${invoice.cfdi.id}`, invoice);
  }

  public generateInvoiceComplement(folioPadre: string, complemento: Pago): Observable <any> {
    return this.httpClient.post(`../api/facturas/${folioPadre}/complementos`, complemento);
  }

  public getInvoiceSaldo(folio: string): Observable<any>{
    return this.httpClient.get(`../api/facturas/${folio}/saldos`);
  }

  public reSendEmail(folio:string,factura:Factura) : Observable<any>{
    return this.httpClient.post(`../api/facturas/${folio}/correos`,factura);
  }
}
