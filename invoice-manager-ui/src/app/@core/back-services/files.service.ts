import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResourceFile } from '../../models/resource-file';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FilesService {

  constructor(private httpClient:HttpClient) { }


  public getFacturaFile(folio:string,tipoArchivo:string):Observable<any>{
    return this.httpClient.get(`../api/facturas/${folio}/files/${tipoArchivo}`);
  }
  public getResourceFile(referencia:string,tipoRecurso:string,tipoArchivo:string):Observable<any>{
    return this.httpClient.get(`../api/recursos/${tipoRecurso}/files/${tipoArchivo}/referencias/${referencia}`);
  }

  public insertFacturaFile(file:ResourceFile):Observable<any>{
    return this.httpClient.post(`../api/facturas/${file.folio}/files`,file);
  }
  public insertResourceFile(file:ResourceFile):Observable<any>{
    return this.httpClient.post(`../api/recursos/${file.tipoRecurso}/files`,file);
  }

  public deleteFacturaFile(id:number):Observable<any>{
    return this.httpClient.delete(`../api/facturas/files/${id}`);
  }
  public deleteResourceFile(id:number):Observable<any>{
    return this.httpClient.delete(`../api/recursos/files/${id}`);
  }
}
