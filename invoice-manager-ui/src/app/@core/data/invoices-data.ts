import { Factura } from '../../models/factura/factura';
import { GenericPage } from '../../models/generic-page';
import { Observable } from 'rxjs';
import { Pago } from '../../models/pago';

export abstract class InvoicesData {

    abstract getInvoices(page:number,size:number,filterParams?:any) : Observable<GenericPage<Factura>>;

    abstract getInvoiceByFolio(folio:string) : Observable<Factura>;

    abstract getInvoiceById(id:number) : Observable<[Factura]>;

    abstract insertNewInvoice(invoice : Factura) : Observable<Factura>;

    abstract updateInvoice(invoice : Factura) : Observable<Factura>;

    abstract getPayments(folio : string): Observable<Pago[]>

    abstract insertNewPayment(folio : string, payment : Pago): Observable<Pago>;

    abstract deletePayment(folio : string, paymentId : number): Observable<Pago>;
  
}