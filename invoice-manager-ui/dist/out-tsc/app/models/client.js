import { Contribuyente } from './contribuyente';
export class Client {
    constructor() {
        this.porcentajePromotor = 0;
        this.porcentajeCliente = 0;
        this.porcentajeDespacho = 0;
        this.porcentajeContacto = 0;
        this.correoContacto = '';
        this.informacionFiscal = new Contribuyente();
    }
    getPercentagesSum() {
        return this.porcentajePromotor + this.porcentajeCliente + this.porcentajeDespacho + this.porcentajeContacto;
    }
}
//# sourceMappingURL=client.js.map