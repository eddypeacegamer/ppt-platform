import { GenericPage } from '../../models/generic-page';
import { Observable } from 'rxjs';
import { Pago } from '../../models/pago';

export abstract class PaymentsData {

    abstract getPaymentsByFolio(folio : string): Observable<Pago[]>

    abstract insertNewPayment(folio : string, payment : Pago): Observable<Pago>;

    abstract deletePayment(folio : string, paymentId : number): Observable<Pago>;

    abstract updatePayment(folio : string, paymentId : number, payment : Pago) : Observable<Pago>;

    abstract getAllPayments(page: number, size: number, filterParams?: any) : Observable<GenericPage<Pago>>;

}