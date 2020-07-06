import { GenericPage } from '../../models/generic-page';
import { Observable } from 'rxjs';
import { PagoBase } from '../../models/pago-base';
import { Catalogo } from '../../models/catalogos/catalogo';
import { PagoFactura } from '../../models/pago-factura';

export abstract class PaymentsData {

    abstract getPaymentsByFolio(folio: string): Observable<PagoFactura[]>;

    abstract insertNewPayment(payment: PagoBase): Observable<PagoBase>;

    abstract deletePayment(paymentId: number): Observable<PagoBase>;

    abstract updatePaymentWithValidation(paymentId: number, payment: PagoBase): Observable<PagoBase>;

    abstract getAllPayments(page: number, size: number, filterParams?: any): Observable<GenericPage<PagoBase>>;

    abstract getFormasPago(roles?: string[]): Observable<Catalogo[]>;

    abstract getIncomes(page: number, size: number, filterParams?: any) : Observable<GenericPage<PagoBase>>;

    abstract getIncomesSum(filterParams?: any) : Observable<number>;

    abstract getExpensesSum(filterParams?: any) : Observable<number>;

    abstract getExpenses(page: number, size: number, filterParams?: any) : Observable<GenericPage<PagoBase>>;


}