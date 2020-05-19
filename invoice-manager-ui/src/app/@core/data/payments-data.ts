import { GenericPage } from '../../models/generic-page';
import { Observable } from 'rxjs';
import { PagoFactura } from '../../models/pago-factura';
import { Catalogo } from '../../models/catalogos/catalogo';
import { Rolesdata } from './users-data';

export abstract class PaymentsData {

    abstract getPaymentsByFolio(folio : string): Observable<PagoFactura[]>

    abstract insertNewPayment(folio : string, payment : PagoFactura): Observable<PagoFactura>;

    abstract deletePayment(folio : string, paymentId : number): Observable<PagoFactura>;

    abstract updatePaymentWithValidation(folio : string, paymentId : number, payment : PagoFactura) : Observable<PagoFactura>;

    abstract getAllPayments(page: number, size: number, filterParams?: any) : Observable<GenericPage<PagoFactura>>;

    abstract getFormasPago(roles?: Rolesdata[]): Observable<Catalogo[]>;

    abstract getIncomes(page: number, size: number, filterParams?: any) : Observable<GenericPage<PagoFactura>>;

    abstract getIncomesSum(filterParams?: any) : Observable<number>;

    abstract getExpensesSum(filterParams?: any) : Observable<number>;

    abstract getExpenses(page: number, size: number, filterParams?: any) : Observable<GenericPage<PagoFactura>>;


}