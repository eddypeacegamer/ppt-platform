import { Contribuyente } from './contribuyente';

export class Empresa{

    public id: number;
    public activo : string;
    public referencia: string;
    public regimenFiscal: string;
    public web: string;
    public contactoAdmin: string;
    public sucursal: string;
    public lugarExpedicion: string;
    public logotipo: string; 
    public llavePrivada: string;
    public certificado: string;
    public pw: string;
    public encabezado: string;
    public piePagina: string;
    public tipo: string;
    public giro : string;
    public fechaCreacion: Date;
    public fechaActualizacion: Date;
    public informacionFiscal: Contribuyente


    constructor(){
        this.informacionFiscal = new Contribuyente();
    }
}