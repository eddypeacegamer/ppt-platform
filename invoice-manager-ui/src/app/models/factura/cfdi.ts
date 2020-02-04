import  {Concepto} from './concepto';

export class Cfdi{

    //defaults
    id: number;
    version : string;
    tipoDeComprobante: string;
    //obligatorio
    moneda: string;
    usoCfdi: string;
    regimenFiscal: string;
    conceptos : Concepto[];
    //generado
    folio : string;
    serie : string; 
    fechaCreacion: Date; //esta fecha cual es?
    fechaTimbrado: Date;
    fechaActualizacion :Date;
    sello : string;
    uuid: string;
    selloCfd: string;
    noCertificado : string;
    noCertificadoSat : string;
    selloSat : string ;
    
    //no deberia ser expuesto
    rfcProvCertif: string;
    
    constructor(){
        this.version = '3.3';
        this.tipoDeComprobante = 'I';
        this.conceptos = [];
        this.serie = '1';//should be generated
    }
}