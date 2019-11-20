import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http'
import { Observable, forkJoin } from 'rxjs';

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

  public getProductoServiciosByDescription(description:string) {
    let params : HttpParams =  new HttpParams().append('descripcion',description);
    return this.httpClient.get("/api/catalogs/producto-servicios",{params:params});
  }
  public getClaveUnidadByName(name:string) {
    let params : HttpParams =  new HttpParams().append('nombre',name);
    return this.httpClient.get("/api/catalogs/clave-unidad",{params:params});
  }
  public getAllUsoCfdis() {
    return this.httpClient.get("/api/catalogs/uso-cdfi");
  }
  public getAllRegimenFiscal() {
    return this.httpClient.get("/api/catalogs/regimen-fiscal");
  }
  public getAllGiros(){
    return this.httpClient.get("/api/catalogs/giros");
  }
  public getStatusPago() {
    return this.httpClient.get("/api/catalogs/status-pago");
  }
  public getStatusValidacion() {
    return this.httpClient.get("/api/catalogs/status-evento");
  }
  public getStatusDevolucion() {
    return this.httpClient.get("/api/catalogs/status-devolucion");
  }

  public getInvoiceCatalogs() : Observable<any[]>{
    let giros = this.getAllGiros();
    let claveUnidad = this.getClaveUnidadByName('');
    let usoCfdi = this.getAllUsoCfdis();
    let statusPago = this.getStatusPago();
    let statusDevolucion = this.getStatusDevolucion();
    let statusEvento = this.getStatusValidacion();
    return forkJoin([giros,claveUnidad,usoCfdi,statusPago,statusDevolucion,statusEvento]);
  }
}
