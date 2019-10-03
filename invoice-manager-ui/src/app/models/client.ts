import { Contribuyente } from './contribuyente';

export class Client {

    public id: number;
    public activo : boolean;
    public fechaCreacion: Date;
    public fechaActualizacion: Date;
    public informacionFiscal: Contribuyente;
}
