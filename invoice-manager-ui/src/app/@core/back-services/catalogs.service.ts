import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http'
import { Observable, forkJoin, of } from 'rxjs';
import { Catalogo } from '../../models/catalogos/catalogo';

@Injectable({
  providedIn: 'root'
})
export class CatalogsService {

  constructor(private httpClient:HttpClient) { }

  public getAllClavesProductoServicio(page:number,size:number): Observable<any> {
     const pageParams: HttpParams =  new HttpParams().append('page', page.toString()).append('size', size.toString());
    return this.httpClient.get('../api/catalogs/producto-servicios', { params: pageParams});
  }

  public getZipCodeInfo(zipCode: String): Observable<any> {
    return this.httpClient.get(`/api/catalogs/codigo-postal/${zipCode}`);
  }

  public getProductoServiciosByDescription(description:string): Observable<any> {
    const params: HttpParams =  new HttpParams().append('descripcion',description);
    return this.httpClient.get('/api/catalogs/producto-servicios',{params:params});
  }
  public getProductoServiciosByClave(clave:string): Observable<any> {
    const params: HttpParams =  new HttpParams().append('clave',clave);
    return this.httpClient.get('/api/catalogs/producto-servicios',{params:params});
  }
  public getClaveUnidadByName(name: string): Observable<any> {
    const params: HttpParams =  new HttpParams().append('nombre',name);
    return this.httpClient.get('/api/catalogs/clave-unidad',{params:params});
  }
  public getAllUsoCfdis(): Observable<any> {
    return this.httpClient.get('/api/catalogs/uso-cdfi');
  }
  public getAllRegimenFiscal(): Observable<any> {
    return this.httpClient.get('/api/catalogs/regimen-fiscal');
  }
  public getAllGiros(): Observable<any> {
    return this.httpClient.get('/api/catalogs/giros');
  }
  public getStatusPago(): Observable<any> {
    return this.httpClient.get('/api/catalogs/status-pago');
  }
  public getStatusValidacion(): Observable<any> {
    return this.httpClient.get('/api/catalogs/status-evento');
  }
  public getStatusDevolucion(): Observable<any> {
    return this.httpClient.get('/api/catalogs/status-devolucion');
  }
  public getFormasPago(metodo?: string): Observable<any> {
    if (metodo === 'PUE') {
      return of([new Catalogo('01', 'Efectivo'),
      new Catalogo('02', 'Cheque nominativo'),
      new Catalogo('03', 'Transferencia electrónica de fondos')]);
    }
    if (metodo === 'PPD') {
      return of([new Catalogo('99', 'Por definir')]);
    }
    return of([new Catalogo('01', 'Efectivo'),
    new Catalogo('02', 'Cheque nominativo'),
    new Catalogo('03', 'Transferencia electrónica de fondos'),
    new Catalogo('99', 'Por definir')]);
  }

  public getInvoiceCatalogs(): Observable<any[]> {
    const giros = this.getAllGiros();
    const claveUnidad = this.getClaveUnidadByName('');
    const usoCfdi = this.getAllUsoCfdis();
    const statusPago = this.getStatusPago();
    const statusDevolucion = this.getStatusDevolucion();
    const statusEvento = this.getStatusValidacion();
    const formasPago = this.getFormasPago();
    return forkJoin([giros, claveUnidad, usoCfdi , statusPago, statusDevolucion, statusEvento, formasPago]);
  }
}
