import { Contribuyente } from './contribuyente';

export class Client {

    public id: number;
    public activo : boolean;
    public porcentajePromotor: number;
    public porcentajeCliente: number;
    public porcentajeDespacho: number;
    public porcentajeContacto: number;
    public correoPromotor : string;
    public correoContacto : string;
    public fechaCreacion: Date;
    public fechaActualizacion: Date;
    public informacionFiscal: Contribuyente;


    constructor(){
        this.porcentajePromotor = 0;
        this.porcentajeCliente = 0;
        this.porcentajeDespacho = 0;
        this.porcentajeContacto = 0;
        this.correoContacto = '';
        this.informacionFiscal = new Contribuyente();
    }

    public getPercentagesSum(){
        return this.porcentajePromotor + this.porcentajeCliente + this.porcentajeDespacho + this.porcentajeContacto;
    }
}
