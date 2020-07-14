import { Factura } from '../../models/factura/factura';
import { GenericPage } from '../../models/generic-page';
import { Observable } from 'rxjs';
import { Pago } from '../../models/factura/pago';

export abstract class InvoicesData {
    abstract getInvoices(page: number, size: number, filterParams?: any): Observable<GenericPage<Factura>>;

    abstract getInvoicesReports(page: number, size: number, filterParams?: any): Observable<GenericPage<any>>;
    abstract getComplementReports(page: number, size: number, filterParams?: any): Observable<GenericPage<any>>;

    abstract getInvoiceSaldo(prefolio: string): Observable<number>;

    abstract getInvoiceByFolio(folio: string): Observable<Factura>;
    abstract getInvoiceFiles(folio: string): Observable<any>;
    abstract getComplementosInvoice(folioPadre: string): Observable<Factura[]>;
    abstract timbrarFactura(folio: string, factura: Factura): Observable<any>;
    abstract cancelarFactura(folio: string, factura: Factura): Observable<any>;
    abstract insertNewInvoice(invoice: Factura): Observable<Factura>;
    abstract updateInvoice(invoice: Factura): Observable<Factura>;
    abstract generateInvoiceComplement(folioPadre: string, complemento: Pago): Observable<Factura>;
    abstract reSendEmail(folio: string, factura: Factura): Observable<any>;
}
