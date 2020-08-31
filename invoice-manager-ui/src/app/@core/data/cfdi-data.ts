import { Observable } from 'rxjs';
import { Concepto } from '../../models/factura/concepto';
import { Cfdi } from '../../models/factura/cfdi';
import { Pago } from '../../models/factura/pago';
import { Factura } from '../../models/factura/factura';

export abstract class CfdiData {

    abstract getCfdiByFolio(prefolio: number): Observable<Cfdi>;
    abstract updateCfdi(cfdi: Cfdi): Observable<Cfdi>;
    abstract calcularMontosCfdi(cfdi: Cfdi): Observable<Cfdi>;
    abstract insertConcepto(prefolio: number, concepto: Concepto): Observable<Concepto>;
    abstract updateConcepto(prefolio: number, id: number, concepto: Concepto): Observable<Concepto>;
    abstract deleteConcepto(prefolio: number, conceptoId: number): Observable<any>;
    abstract findPagosPPD(prefolio: number): Observable<Pago[]>;
    abstract getFacturaInfo(prefolio: number): Observable<Factura>;
    abstract getChildrenCfdi(folio: string, parcialidad: number): Observable<Factura>;

}
