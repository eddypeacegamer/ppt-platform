import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Empresa } from '../../models/empresa';

@Injectable({
  providedIn: 'root',
})
export class CompaniesService {

  constructor(private httpClient: HttpClient) { }


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


  public getCompanies(filterParams: any): Observable<Object> {
    return this.httpClient.get('../api/empresas', { params: this.getHttpParams(filterParams)});
  }

  public getCompaniesByLineaAndGiro(linea: string, giro: number) {
    return this.httpClient.get(`../api/lineas/${linea}/giros/${giro}/empresas`);
  }

  public getCompanyByRFC(rfc: string): Observable<Object> {
    return this.httpClient.get(`../api/empresas/${rfc}`);
  }

  public insertNewCompany(empresa: Empresa): Observable<Object> {
    return this.httpClient.post('../api/empresas',empresa);
  }

  public updateCompany(rfc:string,empresa: Empresa): Observable<Object> {
    return this.httpClient.put(`../api/empresas/${rfc}`,empresa);
  }
}
