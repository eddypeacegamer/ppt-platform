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
    impuestos : number;
    descuento: number;
    //generado
    serie : string; 
    fecha: Date; //esta fecha cual es?
    fechaTimbrado: Date;
    fechaActualizacion :Date;
    sello : string;
    uuid: string;
    selloCfd: string;
    noCertificadoSat : string;
    selloSat : string ;
    
    //no deberia ser expuesto
    noCertificado: string;
    certificado: string;
    rfcProvCertif: string;
    
    constructor(){
        this.version = '3.3';
        this.tipoDeComprobante = 'I';
        this.conceptos = [];
        this.total = 0;
        this.subtotal = 0;
        this.descuento = 0;
        this.impuestos = 0;
    }
}