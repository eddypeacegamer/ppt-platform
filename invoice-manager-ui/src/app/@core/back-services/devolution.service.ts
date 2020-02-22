import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http'
import { PagoDevolucion } from '../../models/pago-devolucion';


@Injectable({
  providedIn: 'root',
})
export class DevolutionService {

  constructor(private httpClient: HttpClient) { }

  public findDevolutions(page: number, size: number, filterParams?: any): Observable<any> {
    let pageParams: HttpParams = new HttpParams().append('page', page.toString()).append('size', size.toString());
    for (const key in filterParams) {
      if (filterParams[key] !== undefined && filterParams[key].length > 0) {
        if (filterParams[key] instanceof Date) {
          const date: Date = filterParams[key] as Date;
          pageParams = pageParams.append(key, `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`);
        } else {
          pageParams = pageParams.append(key, (filterParams[key] === '*') ? '' : filterParams[key]);
        }
      }
    }
    return this.httpClient.get('../api/devoluciones', { params: pageParams });
  }

  public getAmmountDevolutions(tipoReceptor: string, receptor: string): Observable<any> {
    return this.httpClient.get(`../api/devoluciones/receptor/${tipoReceptor}/${receptor}/saldo`);
  }

  public findDevolutionsRequests(page: number, size: number, filterParams?: any): Observable<any> {
    let pageParams: HttpParams = new HttpParams().append('page', page.toString()).append('size', size.toString());
    for (const key in filterParams) {
      if (filterParams[key] !== undefined && filterParams[key].length > 0) {
        if (filterParams[key] instanceof Date) {
          const date: Date = filterParams[key] as Date;
          pageParams = pageParams.append(key, `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`);
        } else {
          pageParams = pageParams.append(key, (filterParams[key] === '*') ? '' : filterParams[key]);
        }
      }
    }
    return this.httpClient.get('../api/devoluciones/pagos', { params: pageParams });
  }

  public requestDevolution(solicitud: PagoDevolucion): Observable<any> {
    return this.httpClient.post('../api/devoluciones', solicitud);
  }

  public updateDevolution(id: number, solicitud: PagoDevolucion): Observable<any> {
    return this.httpClient.put(`../api/devoluciones/${id}`, solicitud);
  }

}
