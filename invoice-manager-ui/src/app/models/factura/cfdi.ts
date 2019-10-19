import  {Concepto} from './concepto';

export class Cfdi{

    //defaults
    version : string;
    tipoDeComprobante: string;
    //obligatorio
    formaPago: string;
    moneda: string;
    metodoPago : string;
    usoCfdi: string;
    
    lugarExpedicion : string;
    nombreEmisor: string;
    rfcEmisor: string;
    nombreReceptor : string;
    rfcReceptor : string;
    regimenFiscal: string;

    conceptos : Concepto[];
    total: number;
    subtotal: number;
    descuento: number;
    //generado
    serie : string; 
    fecha: Date; //esta fecha cual es?
    sello : string;
    uuid: string;
    selloCfd: string;
    fechaTimbrado: Date;
    noCertificadoSat : string;
    selloSat : string ;
    fechaActualizacion :Date;
    //no deberia ser expuesto
    noCertificado: string;
    certificado: string;
    rfcProvCertif: string;
    
    constructor(){
        this.version = '3.3';
        this.tipoDeComprobante = 'I';
    }
}