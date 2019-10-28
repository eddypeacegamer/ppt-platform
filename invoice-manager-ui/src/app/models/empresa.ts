import { Contribuyente } from './contribuyente';

export class Empresa{

    public id: number;
    public activo : boolean;
    public referencia: string;
    public regimenFiscal: string;
    public web: string;
    public contactoAdmin: string;
    public sucursal: string;
    public lugarExpedicion: string;
    public logotipo: Blob; 
    public llavePrivada: Blob;
    public certificado: Blob;
    public pw: string;
    public encabezado: string;
    public piePagina: string;
    public tipo: string;
    public giro : number;
    public fechaCreacion: Date;
    public fechaActualizacion: Date;
    public informacionFiscal: Contribuyente


    constructor(){
        this.informacionFiscal = new Contribuyente();
    }
}