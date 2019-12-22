import { Observable } from 'rxjs';
import { GenericPage } from '../../models/generic-page';
import { Devolucion } from '../../models/devolucion';
import { Pago } from '../../models/pago';

export abstract class DevolutionData{

    abstract getDevolutions(page: number, size: number,filterParams?:any): Observable<GenericPage<Devolucion>>;

    abstract generateDevolutions(payment:Pago):Observable<any>;
}