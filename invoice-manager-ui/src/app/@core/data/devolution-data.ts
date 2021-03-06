import { Observable } from 'rxjs';
import { GenericPage } from '../../models/generic-page';
import { Devolucion } from '../../models/devolucion';
import { PagoDevolucion } from '../../models/pago-devolucion';

export abstract class DevolutionData {

    abstract findDevolutions(page: number, size: number, filterParams?: any): Observable<GenericPage<Devolucion>>;

    abstract findDevolutionByFolioFactAndTipoReceptor(folio: string, tiporeceptor: string): Observable<PagoDevolucion>;

    abstract findDevolutionByFolioFact(folio: string): Observable<Devolucion[]>;

    abstract updateDevolutionByFolioFact(folio: string, devoluciones: Devolucion[]): Observable<Devolucion[]>;

    abstract getAmmountDevolutions(tipoReceptor: string, receptor: string): Observable<Number>;

    abstract findDevolutionsRequests(page: number, size: number, filterParams?: any):
        Observable<GenericPage<PagoDevolucion>>;

    abstract requestDevolution(pago: PagoDevolucion): Observable<PagoDevolucion>;

    abstract updateDevolution(id: number, pago: PagoDevolucion): Observable<PagoDevolucion>;

}
