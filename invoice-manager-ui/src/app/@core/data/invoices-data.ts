import { Factura } from '../../models/factura/factura';
import { GenericPage } from '../../models/generic-page';
import { Observable } from 'rxjs';
import { Pago } from '../../models/pago';

export abstract class InvoicesData {

    abstract getInvoices(page:number,size:number,filterParams?:any) : Observable<GenericPage<Factura>>;

    abstract getInvoiceByFolio(folio:string) : Observable<Factura>;

    abstract getInvoiceById(id:number) : Observable<Factura>;

    abstract getInvoiceFiles(folio:string) : Observable<any>;

    abstract getComplementosInvoice(folioPadre:string) : Observable<Factura[]>;

    abstract timbrarFactura(folio:string,factura:Factura) : Observable<any>;

    abstract cancelarFactura(folio:string,factura:Factura) : Observable<any>;

    abstract insertNewInvoice(invoice : Factura) : Observable<Factura>;

    abstract updateInvoice(invoice : Factura) : Observable<Factura>;

    abstract getPayments(folio : string): Observable<Pago[]>

    abstract insertNewPayment(folio : string, payment : Pago): Observable<Pago>;

    abstract deletePayment(folio : string, paymentId : number): Observable<Pago>;

    abstract updatePayment(folio : string, paymentId : number, payment : Pago) : Observable<Pago>;

    abstract getAllPayments(page: number, size: number, filterParams?: any) : Observable<GenericPage<Pago>>;
}