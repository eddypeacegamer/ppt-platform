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

  public updatePayment(folio: string, paymentId: number, payment: Pago): Observable<any> {
    return this.httpClient.put(`../api/facturas/${folio}/pagos/${paymentId}`, payment);
  }

  public getAllPayments(page: number, size: number, filterParams?: any): Observable<Object> {
    let pageParams: HttpParams = new HttpParams().append('page', page.toString()).append('size', size.toString());
    for (const key in filterParams) {
      const value: string = filterParams[key];
      if (value.length > 0) {
        pageParams = pageParams.append(key, (filterParams[key] === '*') ? '' : filterParams[key]);
      }
    }
    return this.httpClient.get('../api/pagos', { params: pageParams });
  }
}