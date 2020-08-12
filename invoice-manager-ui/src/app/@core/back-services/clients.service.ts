import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http'
import { Client } from '../../models/client';

@Injectable({
  providedIn: 'root',
})
export class ClientsService {

  constructor(private httpClient:HttpClient) { }


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
    return pageParams;
  }

  public getClientsAll(filterParams: any): Observable<any> {
    return this.httpClient.get('../api/clientes', {params: this.getHttpParams(filterParams)});
  }

  public getClients(filterParams: any): Observable<Object> {
    return this.httpClient.get('../api/clientes', {params: this.getHttpParams(filterParams)});
  }

  public getClientsByPromotor(promotor: string): Observable<any> {
    return this.httpClient.get(`../api/promotores/${promotor}/clientes`);
  }

  public getClientByRFC(rfc:string) : Observable<Object>{
    return this.httpClient.get(`../api/clientes/${rfc}`);
  }

  public insertNewClient(client : Client) : Observable<Object>{
    return this.httpClient.post('../api/clientes',client);
  }

  public updateClient(cliente : Client) : Observable<Object>{
    return this.httpClient.put(`../api/clientes/${cliente.informacionFiscal.rfc}`,cliente);
  }

}
