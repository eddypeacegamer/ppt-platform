import { Contribuyente } from './contribuyente';

export class Client {

    public id: number;
    public activo : boolean;
    public porcentajePromotor: number;
    public porcentajeCliente: number;
    public porcentajeDespacho: number;
    public porcentajeContacto: number;
    public fechaCreacion: Date;
    public fechaActualizacion: Date;
    public informacionFiscal: Contribuyente;
}
