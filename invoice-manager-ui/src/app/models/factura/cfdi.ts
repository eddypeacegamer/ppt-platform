import  {Concepto} from './concepto';

export class Cfdi{

    id: number;
    version : string;
    serie : string; 
    folio : string;
    fecha : Date;
    sello : string;
    noCertificado : string;
    certificado : string;
    moneda: string;
    subtotal: number;
    descuento : number;
    total : number;
    usoCfdi: string;
    tipoDeComprobante: string;
    metodoPago
    formaPago
    condicionesDePago
    lugarExpedicion
    
    regimenFiscal: string;
    rfcProvCertif: string;
    selloCfd:string;
    noCertificadoSat : string;
    selloSat : string ;
    cadenaOriginal : string;
    emisor:string;
    receptor : string;
    conceptos : Concepto[];
    
    constructor(){
        this.version = '3.3';
        this.tipoDeComprobante = 'I';
        this.conceptos = [];
        this.total = 0;
		this.subtotal  = 0;	
		this.descuento = 0;
        this.serie = '1';//should be generated
    }
}