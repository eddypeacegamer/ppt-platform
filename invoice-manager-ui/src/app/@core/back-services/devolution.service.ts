import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http'
import { Pago } from '../../models/pago';
import { SolicitudDevolucion } from '../../models/solicitud-devolucion';

@Injectable({
  providedIn: 'root'
})
export class DevolutionService {

  constructor(private httpClient:HttpClient) { }

  public getDevolutions(page: number, size: number, filterParams?: any): Observable<any> {
    let pageParams : HttpParams =  new HttpParams().append('page',page.toString()).append('size',size.toString());
    for (const key in filterParams) {
      const value : string = filterParams[key];
      if(value.length>0){
        pageParams = pageParams.append(key, (filterParams[key]==='*')?'':filterParams[key]);
      }
    }
    return this.httpClient.get('../api/devoluciones',{params:pageParams});
  }

  public getDevolutionsByReceptor(tipoReceptor:string,idReceptor:string,status:string): Observable<any> {
    if(status ==='*'){
      return this.httpClient.get(`../api/devoluciones/receptor/${tipoReceptor}/${idReceptor}`);
    }else{
      let pageParams : HttpParams =  new HttpParams().append('statusDevolucion',status);
      return this.httpClient.get(`../api/devoluciones/receptor/${tipoReceptor}/${idReceptor}`,{params:pageParams});
      
    }
  }

  public requestMultipleDevolution(solicitud:SolicitudDevolucion):Observable<any>{
    return this.httpClient.post('../api/pagos/devoluciones',solicitud);
  }


  public generateDevolutions(payment:Pago):Observable<any>{
    return this.httpClient.post('../api/devoluciones', payment);
  }

}
