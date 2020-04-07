import { Observable } from 'rxjs';
import { GenericPage } from '../../models/generic-page';
import { Devolucion } from '../../models/devolucion';
import { ResourceFile } from '../../models/resource-file';

export abstract class FilesData{

    abstract getFacturaFile(folio:string,tipoArchivo:string):Observable<ResourceFile>;
    abstract getResourceFile(referencia:string,tipoRecurso:string,tipoArchivo:string):Observable<ResourceFile>;

    abstract insertFacturaFile(file: ResourceFile):Observable<ResourceFile>;
    abstract insertResourceFile(file: ResourceFile):Observable<ResourceFile>;

    abstract deleteFacturaFile(id:number):Observable<any>;
    abstract deleteResourceFile(id:number):Observable<any>;
}