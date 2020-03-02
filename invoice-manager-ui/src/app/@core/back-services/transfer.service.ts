import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Transferencia } from '../../models/transferencia';

@Injectable({
  providedIn: 'root'
})
export class TransferService {

  constructor(private httpClient: HttpClient) { }

  public getAllTransfers(page: number, size: number, filterParams?: any) : Observable<any>{
    console.log(filterParams)
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
    return this.httpClient.get('../api/transferencias',{ params: pageParams });
  }

  public saveAllTransfers(transferencias: Transferencia[]) : Observable<any>{
    return this.httpClient.post('../api/transferencias/bulk', transferencias);
  }

  public updateTranfer(transfer : Transferencia): Observable<any>{
    return this.httpClient.put(`../api/transferencias/${transfer.id}`, transfer);
  }
}
