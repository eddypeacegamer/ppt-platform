import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http'
import { Pago } from '../../models/pago';

@Injectable({
  providedIn: 'root'
})
export class DevolutionService {

  constructor(private httpClient:HttpClient) { }

  public getDevolutions(page: number, size: number, filterParams?: any): Observable<Object> {
    let pageParams : HttpParams =  new HttpParams().append('page',page.toString()).append('size',size.toString());
    for (const key in filterParams) {
      const value : string = filterParams[key];
      if(value.length>0){
        pageParams = pageParams.append(key, (filterParams[key]==='*')?'':filterParams[key]);
      }
    }
    return this.httpClient.get('../api/devoluciones',{params:pageParams});
  }


  public generateDevolutions(payment:Pago):Observable<any>{
    return this.httpClient.post('../api/devoluciones', payment);
  }

}
