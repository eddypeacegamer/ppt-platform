import { GenericPage } from '../../models/generic-page';
import { Observable } from 'rxjs';
import { Pago } from '../../models/pago';

export abstract class PaymentsData {

    abstract getPaymentsByFolio(folio : string): Observable<Pago[]>

    abstract insertNewPayment(folio : string, payment : Pago): Observable<Pago>;

    abstract deletePayment(folio : string, paymentId : number): Observable<Pago>;

    abstract updatePaymentWithValidation(folio : string, paymentId : number, payment : Pago) : Observable<Pago>;

    abstract getAllPayments(page: number, size: number, filterParams?: any) : Observable<GenericPage<Pago>>;

    abstract getIncomes(page: number, size: number, filterParams?: any) : Observable<GenericPage<Pago>>;

    abstract getExpenses(page: number, size: number, filterParams?: any) : Observable<GenericPage<Pago>>;


    abstract updatePayment(payment:Pago):Observable<Pago>;

}