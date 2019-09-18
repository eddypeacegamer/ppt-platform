import { Observable } from 'rxjs';
import { GenericPage } from '../../models/generic-page';
export abstract class InvoiceData {
    
  abstract getInvoicesByTransmitterRfc(transmitter : string ) : Observable<GenericPage<any>>;

  abstract getInvoicesByReceiverRfc(receiver : string ) : Observable<GenericPage<any>>;

  abstract getInvoicesByPromoter(promoter : string ) : Observable<GenericPage<any>>;

  abstract getInvoicesBetweenDates(promoter : string ) : Observable<GenericPage<any>>;
}