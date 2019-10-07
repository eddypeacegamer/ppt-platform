import { Contribuyente } from './contribuyente';

export class Client {

    public id: number;
    public activo : boolean;
    public porcentajePromotor: number=25;
    public porcentajeCliente: number=25;
    public porcentajeDespacho: number=25;
    public porcentajeContacto: number=25;
    public fechaCreacion: Date;
    public fechaActualizacion: Date;
    public informacionFiscal: Contribuyente;
}
