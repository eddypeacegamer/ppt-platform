import { Observable } from 'rxjs';
import { GenericPage } from '../../models/generic-page';
import { ZipCodeInfo} from '../../models/zip-code-info';
export abstract class CatalogsData {

    abstract getAllClavesProductoServicio(page:number,size:number) : Observable<GenericPage<any>>;

    abstract getZipCodeInfo(zipCode:String) : Observable<ZipCodeInfo>
  
}