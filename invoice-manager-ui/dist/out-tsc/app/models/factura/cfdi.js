import { Emisor } from './emisor';
import { Receptor } from './receptor';
export class Cfdi {
    constructor() {
        this.version = '3.3';
        this.tipoDeComprobante = 'I';
        this.conceptos = [];
        this.emisor = new Emisor();
        this.receptor = new Receptor();
        this.total = 0;
        this.subtotal = 0;
        this.descuento = 0;
        this.serie = '1'; //should be generated
    }
}
//# sourceMappingURL=cfdi.js.map