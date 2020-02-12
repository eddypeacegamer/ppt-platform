import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http'
import { Factura } from '../../models/factura/factura';
import { Concepto } from '../../models/factura/concepto';

@Injectable({
  providedIn: 'root'
})
export class InvoicesService {

  constructor(private httpClient:HttpClient) { }

  public getInvoices(page: number, size: number, filterParams?: any): Observable<Object> {
    let pageParams : HttpParams =  new HttpParams().append('page',page.toString()).append('size',size.toString());
    for (const key in filterParams) {
      let value : string;
      if(filterParams[key] instanceof Date){
        let date : Date = filterParams[key] as Date; 
        value = `${date.getFullYear()}-${date.getMonth()+1}-${date.getDate()}`
      }else{
        value = filterParams[key];
      }
      
      if(value.length>0){
        pageParams = pageParams.append(key, (filterParams[key]==='*')?'':value);
      }
    }
    return this.httpClient.get('../api/facturas',{params:pageParams});
  }

  public getInvoiceByFolio(folio:string) : Observable<any>{
    return this.httpClient.get(`../api/facturas/${folio}`);
  }

  public getInvoiceFiles(folio:string) : Observable<any>{
    return this.httpClient.get(`../api/facturas/${folio}/files`);
  }

  public getComplementosInvoice(folioPadre:string) : Observable<any>{
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
