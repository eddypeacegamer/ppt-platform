import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Catalogo } from '../../models/catalogos/catalogo';

@Injectable({
  providedIn: 'root',
})
export class CatalogsService {
  private giros = undefined;
  private claveUnidad = undefined;
  private usoCfdi = undefined;
  private regimenFiscal = undefined;
  private statusPago = undefined;
  private statusDevolucion = undefined;
  private statusValidacion = undefined;

  constructor(private httpClient: HttpClient) { }

  public getAllClavesProductoServicio(page: number, size: number): Promise<any> {
     const pageParams: HttpParams =  new HttpParams().append('page', page.toString()).append('size', size.toString());
    return this.httpClient.get('../api/catalogs/producto-servicios', { params: pageParams}).toPromise();
  }

  public getZipCodeInfo(zipCode: String): Promise<any> {
    return this.httpClient.get(`/api/catalogs/codigo-postal/${zipCode}`).toPromise();
  }

  public getProductoServiciosByDescription(description:string): Promise<any> {
    const params: HttpParams =  new HttpParams().append('descripcion',description);
    return this.httpClient.get('/api/catalogs/producto-servicios',{params:params}).toPromise();
  }
  public getProductoServiciosByClave(clave:string): Promise<any> {
    const params: HttpParams =  new HttpParams().append('clave',clave);
    return this.httpClient.get('/api/catalogs/producto-servicios',{params:params}).toPromise();
  }
  public getClaveUnidadByName(name: string): Promise<any> {
    return new Promise(resolve => {
      if (this.claveUnidad !== undefined && name === '') {
        resolve(this.claveUnidad);
      }else {
        this.httpClient.get(`/api/catalogs/clave-unidad?nombre=${name}`)
        .subscribe(cat => {
          if (name === '') {
            this.claveUnidad = cat;
          }
          resolve(cat);
        });
      }});
  }
  public getAllUsoCfdis(): Promise<any> {
    return new Promise(resolve => {
      if (this.usoCfdi !== undefined) {
        resolve(this.usoCfdi);
      }else {
        this.httpClient.get('/api/catalogs/uso-cdfi')
        .subscribe(cat => {
          this.usoCfdi = cat;
          resolve(cat);
        });
      }});
  }
  public getAllRegimenFiscal(): Promise<any> {
    return new Promise(resolve => {
      if (this.regimenFiscal !== undefined) {
        resolve(this.regimenFiscal);
      }else {
        this.httpClient.get('/api/catalogs/regimen-fiscal')
        .subscribe(cat => {
          this.regimenFiscal = cat;
          resolve(cat);
        });
      }});
  }
  public getAllGiros(): Promise<any> {
    return new Promise(resolve => {
      if (this.giros !== undefined) {
        resolve(this.giros);
      }else {
        this.httpClient.get('/api/catalogs/giros')
        .subscribe(cat => {
          this.giros = cat;
          resolve(cat);
        });
      }});
  }
  public getStatusPago(): Promise<any> {
    return new Promise(resolve => {
      if (this.statusPago !== undefined) {
        resolve(this.statusPago);
      }else {
        this.httpClient.get('/api/catalogs/status-pago')
        .subscribe(cat => {
          this.statusPago = cat;
          resolve(cat);
        });
      }});
  }
  public getStatusValidacion(): Promise<any> {
    return new Promise(resolve => {
      if (this.statusValidacion !== undefined) {
        resolve(this.statusValidacion);
      }else {
        this.httpClient.get('/api/catalogs/status-evento')
        .subscribe(cat => {
          this.statusValidacion = cat;
          resolve(cat);
        });
      }});
  }
  public getStatusDevolucion(): Promise<any> {
    return new Promise(resolve => {
      if (this.statusDevolucion !== undefined) {
        resolve(this.statusDevolucion);
      }else {
        this.httpClient.get('/api/catalogs/status-devolucion')
        .subscribe(cat => {
          this.statusDevolucion = cat;
          resolve(cat);
        });
      }});
  }

  public getFormasPago(metodo?: string): Promise<any> {
    return new Promise(resolve => {
      if (metodo === 'PUE') {
        return resolve([new Catalogo('01', 'Efectivo'),
        new Catalogo('02', 'Cheque nominativo'),
        new Catalogo('03', 'Transferencia electrónica de fondos')]);
      }
      if (metodo === 'PPD') {
        return resolve([new Catalogo('99', 'Por definir')]);
      }
      return resolve([new Catalogo('01', 'Efectivo'),
      new Catalogo('02', 'Cheque nominativo'),
      new Catalogo('03', 'Transferencia electrónica de fondos'),
      new Catalogo('04', 'Tarjeta de crédito'),
      new Catalogo('05', 'Monedero electrónico'),
      new Catalogo('08', 'Vales de despensa'),
      new Catalogo('28', 'Tarjeta de débito'),
      new Catalogo('29', 'Tarjeta de servicios'),
      new Catalogo('99', 'Por definir')]);
    });
  }

  public getTiposReferencia(formapago: string): Promise<any> {
    return new Promise(resolve => {
      if (formapago === 'EFECTIVO') {
        return resolve([new Catalogo('DIRECCION', 'Direcccion de entrega')]);
      }
      if (formapago === 'CHEQUE') {
        return resolve([new Catalogo('DIRECCION', 'Direcccion de entrega')]);
      }
      if (formapago === 'TRANSFERENCIA') {
        return resolve([new Catalogo('CLABE', 'CLABE interbacaria'),
        new Catalogo('TC', 'Tarjeta de credito'),
        new Catalogo('TD', 'Tarjeta de debito'),
      ]); }
      if (formapago === 'FACTURA') {
        return resolve([new Catalogo('FOLIO', 'Folio factura a pagar')]);
      }
      if (formapago === 'PAGO_MULTIPLE') {
        return resolve([new Catalogo('ARCHIVO', 'Archivo con detalles de pago')]);
      }
      return resolve([]);
    });
  }

  public getBancos(): Promise<any> {
    return this.httpClient.get('/api/catalogs/bancos').toPromise();
  }
}
