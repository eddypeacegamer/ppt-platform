import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http'
import { Client } from '../../models/client';

@Injectable({
  providedIn: 'root'
})
export class ClientsService {

  constructor(private httpClient:HttpClient) { }

  public getClients(page:number,size:number, filterParams?: any) : Observable<Object>{
    let pageParams : HttpParams =  new HttpParams().append('page',page.toString()).append('size',size.toString());
    for (const key in filterParams) {
      const value : string = filterParams[key];
      if(value.length>0){
        pageParams = pageParams.append(key, filterParams[key]);
      }
    }
    return this.httpClient.get('../api/clients',{params:pageParams});
  }

  public getClientByRFC(rfc:string) : Observable<Object>{
    return this.httpClient.get(`../api/clients/${rfc}`);
  }

  public insertNewClient(client : Client) : Observable<Object>{
    return this.httpClient.post('../api/clients',client);
  }

  public updateClient(client : Client) : Observable<Object>{
    return this.httpClient.put(`../api/clients/${client.rfc}`,client);
  }

}
