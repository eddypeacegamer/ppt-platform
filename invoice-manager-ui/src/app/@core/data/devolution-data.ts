import { Observable } from 'rxjs';
import { GenericPage } from '../../models/generic-page';
import { Devolucion } from '../../models/devolucion';

export abstract class DevolutionData{

    abstract getDevolutions(page: number, size: number,filterParams?:any): Observable<GenericPage<Devolucion>>;
}