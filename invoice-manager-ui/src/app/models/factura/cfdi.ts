import {Concepto} from './concepto';
import { Emisor } from './emisor';
import { Receptor } from './receptor';
import { Complemento } from './complemento';

export class Cfdi {

    id: number;
    version: string;
    serie: string;
    folio: string;
    fecha: Date;// donde esta esta fecha
    sello: string;
    noCertificado: string;
    certificado: string;
    moneda: string;
    tipoCambio: number;
    subtotal: number;
    descuento: number;
    total: number;
    tipoDeComprobante: string;
    metodoPago: string;
    formaPago: string;
    condicionesDePago: string;
    lugarExpedicion: string;
    //regimenFiscal: string;
    //rfcProvCertif: string;
    //selloCfd: string;
    //noCertificadoSat: string;
    //selloSat: string ;
    //cadenaOriginal: string;
    emisor: Emisor;
    receptor: Receptor;
    conceptos: Concepto[];
    complemento: Complemento;

    constructor() {
        this.version = '3.3';
        this.tipoDeComprobante = 'I';
        this.conceptos = [];
        this.emisor = new Emisor();
        this.receptor = new Receptor();
        this.complemento = new Complemento();
        this.total = 0;
        this.subtotal = 0;
        this.descuento = 0;
        this.tipoCambio = 1.0;
        this.serie = '1';//should be generated
    }
}
