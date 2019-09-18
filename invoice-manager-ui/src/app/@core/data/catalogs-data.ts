import { Observable } from 'rxjs';
import { GenericPage } from '../../models/generic-page';

export abstract class CatalogsData {

    abstract getAllClavesProductoServicio(page:number,size:number) : Observable<GenericPage<any>>;
  
}