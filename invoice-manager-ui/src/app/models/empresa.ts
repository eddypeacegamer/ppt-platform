import { Contribuyente } from './contribuyente';

export class Empresa {
    public id: number;
    public referencia: string;
    public regimenFiscal: string;
    public web: string;
    public contactoAdmin: string;
    public pwSat: string;
    public pwCorreo: string;
    public correo: string;
    public encabezado: string;
    public piePagina: string;
    public activo: boolean;
    public tipo: string;
    public giro: string;
    public logotipo: string;
    public llavePrivada: string;
    public certificado: string;
    public fechaCreacion: Date;
    public fechaActualizacion: Date;
    public informacionFiscal: Contribuyente;
    public sucursal: string;
    public lugarExpedicion: string;

    constructor() {
        this.informacionFiscal = new Contribuyente();
    }
}