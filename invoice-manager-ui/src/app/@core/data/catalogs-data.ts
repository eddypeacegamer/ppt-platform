import { GenericPage } from '../../models/generic-page';
import { ZipCodeInfo} from '../../models/zip-code-info';
import { ClaveProductoServicio } from '../../models/catalogos/producto-servicio';
import { ClaveUnidad } from '../../models/catalogos/clave-unidad';
import { UsoCfdi } from '../../models/catalogos/uso-cfdi';
import { RegimenFiscal } from '../../models/catalogos/regimen-fiscal';
import { Catalogo } from '../../models/catalogos/catalogo';




export abstract class CatalogsData {

    abstract getAllClavesProductoServicio(page:number, size:number): Promise<GenericPage<any>>;
    abstract getProductoServiciosByDescription(description: string): Promise<ClaveProductoServicio[]>;
    abstract getProductoServiciosByClave(clave: string): Promise<ClaveProductoServicio[]>;
    abstract getClaveUnidadByName(name: string): Promise<ClaveUnidad[]>;
    abstract getAllUsoCfdis(): Promise<UsoCfdi[]>;
    abstract getAllRegimenFiscal(): Promise<RegimenFiscal[]>;
    abstract getAllGiros(): Promise<Catalogo[]>;
    abstract getZipCodeInfo(zipCode: String): Promise<ZipCodeInfo>;
    abstract getStatusPago(): Promise<Catalogo[]>;
    abstract getStatusValidacion(): Promise<Catalogo[]>;
    abstract getStatusDevolucion(): Promise<Catalogo[]>;
    abstract getFormasPago(metodo?: string): Promise<Catalogo[]>;
    abstract getInvoiceCatalogs(): Promise<any[]>;
    abstract getBancos(): Promise<Catalogo[]>;
    abstract getTiposReferencia(formapago: string): Promise<Catalogo[]>;
}
