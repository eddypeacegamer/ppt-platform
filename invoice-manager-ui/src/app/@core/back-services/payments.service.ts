import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Pago } from '../../models/pago';

@Injectable({
  providedIn: 'root'
})
export class PaymentsService {

  constructor(private httpClient: HttpClient) { }

  public getPaymentsByFolio(folio: string): Observable<any> {
    return this.httpClient.get(`../api/facturas/${folio}/pagos`);
  }

  public insertNewPayment(folio: string, payment: Pago): Observable<any> {
    return this.httpClient.post(`../api/facturas/${folio}/pagos`, payment);
  }

  public deletePayment(folio: string, paymentId: number): Observable<any> {
    return this.httpClient.delete(`../api/facturas/${folio}/pagos/${paymentId}`);
  }

  public updatePaymentWithValidation(folio: string, paymentId: number, payment: Pago): Observable<any> {
    return this.httpClient.put(`../api/facturas/${folio}/pagos/${paymentId}`, payment);
  }


  public getAllIncomes(page: number, size: number, filterParams?: any): Observable<Object> {
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
    return this.httpClient.get('../api/pagos', { params: pageParams });
  }


  public getIncomes(page: number, size: number, filterParams?: any): Observable<Object> {
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
    return this.httpClient.get('../api/pagos/ingresos', { params: pageParams });
  }

  public getExpenses(page: number, size: number, filterParams?: any): Observable<Object> {
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
    return this.httpClient.get('../api/pagos/egresos', { params: pageParams });
  }

  public updatePayment(payment : Pago) : Observable<any>{
    return this.httpClient.put(`../api/pagos/${payment.id}`,payment);
  }
}
