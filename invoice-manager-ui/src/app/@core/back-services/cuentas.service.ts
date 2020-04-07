import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class CuentasService {

  constructor(private httpClient: HttpClient) { }

  public getAllCuentas(page: number, size: number, filterParams?: any): Observable<any>{
    let pageParams : HttpParams =  new HttpParams().append('page',page.toString()).append('size',size.toString());
    for (const key in filterParams) {
      let value : string;
      if(filterParams[key] instanceof Date){
        let date : Date = filterParams[key] as Date; 
        value = `${date.getFullYear()}-${date.getMonth()+1}-${date.getDate()}`
      }else{
        value = filterParams[key];
      }
      if(value.length>0){
        pageParams = pageParams.append(key, (filterParams[key]==='*')?'':value);
      }
    }
    return this.httpClient.get('../api/cuentas', { params: pageParams });
  }
  public getCuentasByCompany(companyRfc: string): Observable<any>{
    return this.httpClient.get(`../api/empresas/${companyRfc}/cuentas`);
  }
}
