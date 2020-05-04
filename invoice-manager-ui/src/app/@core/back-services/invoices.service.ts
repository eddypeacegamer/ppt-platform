import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http'
import { Factura } from '../../models/factura/factura';
import { Concepto } from '../../models/factura/concepto';
import { Catalogo } from '../../models/catalogos/catalogo';
import { CatalogsData } from '../data/catalogs-data';
import { map } from 'rxjs/operators';
import { GenericPage } from '../../models/generic-page';

@Injectable({
  providedIn: 'root',
})
export class InvoicesService {

  private validationCat: Catalogo[] = [];
  private payCat: Catalogo[] = [];
  private devolutionCat: Catalogo[] = [];
  private formaPagoCat: Catalogo[] = []

  constructor(private httpClient: HttpClient,
              private catalogService: CatalogsData) {
    this.catalogService.getStatusPago().then(cat => this.payCat = cat);
    this.catalogService.getStatusValidacion().then(cat => this.validationCat = cat);
    this.catalogService.getStatusDevolucion().then(cat => this.devolutionCat = cat);
    this.catalogService.getFormasPago().then(cat => this.formaPagoCat = cat);
  }

  private getHttpParamsFromFilterParams(page: number, size: number,filterParams:any):HttpParams{
    let pageParams: HttpParams =  new HttpParams().append('page', page.toString()).append('size', size.toString());
    for (const key in filterParams) {
      if (filterParams[key] !== undefined) {
        let value: string = filterParams[key];
      if ( filterParams[key] instanceof Date) {
        const date: Date = filterParams[key] as Date;
        value = `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`;
      }
      if ( value !== null && value.length > 0 && value !== '*') {
          pageParams = pageParams.append(key, value);
        }
      }
    }
    return pageParams;
  }

  public getInvoices(page: number, size: number, filterParams?: any): Observable<any> {
    return this.httpClient.get('../api/facturas', 
    {params: this.getHttpParamsFromFilterParams(page, size, filterParams)})
    .pipe(
      map((invPage: GenericPage<any>) => {
        const records: any[] = invPage.content.map(record => {
          record.cadenaOriginalTimbrado = '';
          record.statusFactura = this.validationCat.find(v => v.id.toString()
                                      === record.statusFactura.toString()).nombre;
          record.statusPago = this.payCat.find(v => v.id.toString()
                                      === record.statusPago.toString()).nombre;
          record.statusDevolucion = this.devolutionCat.find(v => v.id.toString()
                                      === record.statusDevolucion.toString()).nombre;
          return record;
        });
        invPage.content = records;
        return invPage;
      }));
  }

  public getInvoicesReports(page: number, size: number, filterParams?: any): Observable<any>{
    return this.httpClient.get('../api/facturas/factura-reports', 
    {params: this.getHttpParamsFromFilterParams(page, size, filterParams)})
    .pipe(
      map((invPage: GenericPage<any>) => {
        const records: any[] = invPage.content.map(record => {
          record.statusFactura = this.validationCat.find(v => v.id.toString()
                                      === record.statusFactura.toString()).nombre;
          record.statusPago = this.payCat.find(v => v.id.toString()
                                      === record.statusPago.toString()).nombre;
          record.formaPago = this.formaPagoCat.find(v => v.id.toString()
                                      === record.formaPago.toString()).nombre;
          return record;
        });
        invPage.content = records;
        return invPage;
      }));
  }
  public getComplementReports(page: number, size: number, filterParams?: any): Observable<any>{
    return this.httpClient.get('../api/facturas/complemento-reports',
    {params: this.getHttpParamsFromFilterParams(page, size, filterParams)})
    .pipe(
      map((invPage: GenericPage<any>) => {
        const records: any[] = invPage.content.map(record => {
          record.statusFactura = this.validationCat.find(v => v.id.toString()
                                      === record.statusFactura.toString()).nombre;
          record.statusPago = this.payCat.find(v => v.id.toString()
                                      === record.statusPago.toString()).nombre;
          record.formaPago = this.formaPagoCat.find(v => v.id.toString()
                                      === record.formaPago.toString()).nombre;
          
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
    return this.httpClient.put(`../api/facturas/${invoice.folio}`, invoice);
  }

  public getCfdiByFolio(folio: string): Observable<any > {
    return this.httpClient.get(`../api/facturas/${folio}/cfdi`);
  }

  public insertConcepto(folio: string, concepto: Concepto): Observable<any> {
    return this.httpClient.post(`../api/facturas/${folio}/conceptos`, concepto);
  }

  public updateConcepto(folio: string, id: number, concepto: Concepto): Observable<any> {
    return this.httpClient.put(`../api/facturas/${folio}/conceptos/${id}`, concepto);
  }

  public deleteConcepto(folio: string, conceptoId: number): Observable<any> {
    return this.httpClient.delete(`../api/facturas/${folio}/conceptos/${conceptoId}`);
  }
}
