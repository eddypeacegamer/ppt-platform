import { Observable } from 'rxjs';
import { GenericPage } from '../../models/generic-page';
import { Contribuyente } from '../../models/contribuyente';

export abstract class ContribuyenteData {
    abstract getContribuyentes(page:number,size:number,filterParams?:any) : Observable<GenericPage<Contribuyente>>;
}