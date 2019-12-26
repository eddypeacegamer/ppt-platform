import { Observable } from 'rxjs';
import { GenericPage } from '../../models/generic-page';
import { Devolucion } from '../../models/devolucion';
import { Pago } from '../../models/pago';
import { SolicitudDevolucion } from '../../models/solicitud-devolucion';

export abstract class DevolutionData{

    
    abstract getDevolutionsByReceptor(tipoReceptor:string,idReceptor:string,status?:string): Observable<Devolucion[]>;
    
    abstract getDevolutions(page: number, size: number,filterParams?:any): Observable<GenericPage<Devolucion>>;

    abstract generateDevolutions(payment:Pago):Observable<any>;

    abstract requestMultipleDevolution(solicitud:SolicitudDevolucion):Observable<Pago>;
}