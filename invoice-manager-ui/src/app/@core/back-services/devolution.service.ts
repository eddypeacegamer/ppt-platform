import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http'
import { Pago } from '../../models/pago';
import { PagoDevolucion } from '../../models/pago-devolucion';
import { GenericPage } from '../../models/generic-page';


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
    return of(70985.55);
  }

  public findDevolutionsRequests(page: number, size: number, filterParams?: any): Observable<any> {
    const pago: PagoDevolucion = new PagoDevolucion();
    pago.id = 12;
    pago.solicitante = 'aaa@gmail.com';
    pago.banco = 'BBVA';
    pago.beneficiario = 'fulanito';
    pago.formaPago = 'TRANSFERENCIA';
    pago.monto = 15544.5;
    pago.receptor = 'aaa@gmail.com';
    pago.referencia = '098765432112345678';
    pago.tipoReferencia = 'CLABE';
    pago.status = 'ACEPTADO';
    pago.comentarios = 'Loa pagos deberan de hacerse directamente al deposito de la compra de un automovil';
    pago.fechaActualizacion = new Date();
    pago.fechaCreacion = new Date();
    pago.fechaPago = new Date();
    const pageResponse: GenericPage<PagoDevolucion> = new GenericPage();
    pageResponse.empty = false;
    pageResponse.numberOfElements = 1;
    pageResponse.totalElements = 1;
    pageResponse.size = size;
    pageResponse.number = page;
    pageResponse.content = [pago];
    console.log('finding devolutions requests:', filterParams);
    return of(pageResponse);
    /* let pageParams: HttpParams = new HttpParams().append('page', page.toString()).append('size', size.toString());
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
    return this.httpClient.get('../api/devoluciones/pagos', { params: pageParams }); */
  }

  public requestDevolution(solicitud: PagoDevolucion): Observable<any> {
    return this.httpClient.post('../api/devoluciones', solicitud);
  }

  public updateDevolution(id: number, solicitud: PagoDevolucion): Observable<any> {
    return this.httpClient.put(`../api/devoluciones/${id}`, solicitud);
  }

}
