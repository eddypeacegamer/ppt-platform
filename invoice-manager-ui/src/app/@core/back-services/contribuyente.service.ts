import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http'
import { Contribuyente } from '../../models/contribuyente';

@Injectable({
  providedIn: 'root'
})
export class ContribuyenteService {

  constructor(private httpClient:HttpClient) { }

  public getContribuyentes(page:number,size:number, filterParams?: any) : Observable<Object>{
    let pageParams : HttpParams =  new HttpParams().append('page',page.toString()).append('size',size.toString());
    for (const key in filterParams) {
      const value : string = filterParams[key];
      if(value.length>0){
        pageParams = pageParams.append(key, filterParams[key]);
      }
    }
    return this.httpClient.get('../api/contribuyentes',{params:pageParams});
  }

}
