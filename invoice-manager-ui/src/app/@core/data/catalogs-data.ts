import { Observable } from 'rxjs';
import { GenericPage } from '../../models/generic-page';
import { ZipCodeInfo} from '../../models/zip-code-info';
import { ClaveProductoServicio } from '../../models/catalogos/producto-servicio';
import { ClaveUnidad } from '../../models/catalogos/clave-unidad';
import { UsoCfdi } from '../../models/catalogos/uso-cfdi';
import { RegimenFiscal } from '../../models/catalogos/regimen-fiscal';
import { Giro } from '../../models/catalogos/giro';
import { Status } from '../../models/catalogos/status';




export abstract class CatalogsData {

    abstract getAllClavesProductoServicio(page:number,size:number) : Observable<GenericPage<any>>;
    abstract getProductoServiciosByDescription(description:string) : Observable<ClaveProductoServicio[]>;
    abstract getClaveUnidadByName(name:string) : Observable<ClaveUnidad[]>;
    abstract getAllUsoCfdis() : Observable<UsoCfdi[]>;
    abstract getAllRegimenFiscal() : Observable<RegimenFiscal[]>;
    abstract getAllGiros() : Observable<Giro[]>;
    abstract getZipCodeInfo(zipCode:String) : Observable<ZipCodeInfo>
    abstract getStatusPago() : Observable<Status[]>;
    abstract getStatusValidacion() : Observable<Status[]>;
    abstract getStatusDevolucion() : Observable<Status[]>;
}