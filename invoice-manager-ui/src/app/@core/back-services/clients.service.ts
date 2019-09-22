import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class ClientsService {

  constructor(private httpClient:HttpClient) { }


  public getClients(page:number,size:number, filterParams?: any){
    let pageParams : HttpParams =  new HttpParams().append('page',page.toString()).append('size',size.toString());
    for (const key in filterParams) {
      pageParams = pageParams.append(key, filterParams[key]);
    }
    return this.httpClient.get('../api/clients',{params:pageParams});
  }


}
