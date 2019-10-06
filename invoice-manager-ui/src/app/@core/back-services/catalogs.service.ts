import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class CatalogsService {

  constructor(private httpClient:HttpClient) { }

  public getAllClavesProductoServicio(page:number,size:number){
    let pageParams : HttpParams =  new HttpParams().append('page',page.toString()).append('size',size.toString());
    return this.httpClient.get('../api/catalogs/producto-servicios',{params:pageParams});
  }

  public getZipCodeInfo(zipCode:String){
    console.log('fetching zipCode info for :', zipCode);
    return this.httpClient.get(`https://api-codigos-postales.herokuapp.com/v2/codigo_postal/${zipCode}`)
  }

}
