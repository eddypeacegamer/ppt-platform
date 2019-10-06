import { Factura } from '../../models/factura';
import { GenericPage } from '../../models/generic-page';
import { Observable } from 'rxjs';

export abstract class InvoicesData {

    abstract getInvoices(page:number,size:number,filterParams?:any) : Observable<GenericPage<Factura>>;

    abstract getInvoiceByFolio(folio:string) : Observable<Factura>;

    abstract getInvoiceById(id:number) : Observable<[Factura]>;

    abstract insertNewCompany(empresa : Factura) : Observable<Factura>;

    abstract updateCompany(empresa : Factura) : Observable<Factura>;
  
}