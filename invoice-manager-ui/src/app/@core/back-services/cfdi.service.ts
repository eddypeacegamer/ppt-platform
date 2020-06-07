import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Concepto } from '../../models/factura/concepto';

@Injectable({
  providedIn: 'root',
})
export class CfdiService {

  constructor(private httpClient: HttpClient) { }

  public getCfdiByFolio(prefolio: number): Observable<any > {
    return this.httpClient.get(`../api/cfdis/${prefolio}`);
  }

  public insertConcepto(prefolio: number, concepto: Concepto): Observable<any> {
    return this.httpClient.post(`../api/cfdis/${prefolio}/conceptos`, concepto);
  }

  public updateConcepto(prefolio: number, id: number, concepto: Concepto): Observable<any> {
    return this.httpClient.put(`../api/cfdis/${prefolio}/conceptos/${id}`, concepto);
  }

  public deleteConcepto(prefolio: number, conceptoId: number): Observable<any> {
    return this.httpClient.delete(`../api/cfdis/${prefolio}/conceptos/${conceptoId}`);
  }

  public findPagosPPD(prefolio: number): Observable<any> {
    return this.httpClient.get(`../api/cfdis/${prefolio}/pagos`);
  }
  public getFacturaInfo(prefolio: number): Observable<any> {
    return this.httpClient.get(`../api/cfdis/${prefolio}/facturaInfo`);
  }



}
