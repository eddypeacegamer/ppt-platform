import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class ClientsService {

  constructor(private httpClient:HttpClient) { }


  public getAllClients(page:number,size:number){
    let pageParams : HttpParams =  new HttpParams().append('page',page.toString()).append('size',size.toString());
    return this.httpClient.get('../api/clients',{params:pageParams});
  }


}
