import { TimbreFiscal } from './timbre-fiscal';
import { Pago } from './pago';

export class Complemento {

    public timbreFiscal: TimbreFiscal;
    public pagos: Pago[];

    constructor() {
        this.pagos = [];
    }
}
