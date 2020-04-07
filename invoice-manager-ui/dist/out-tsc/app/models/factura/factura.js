import { Cfdi } from './cfdi';
export class Factura {
    constructor() {
        this.tipoDocumento = 'Factura';
        this.statusFactura = '1';
        this.statusDetail = '';
        this.statusPago = '1';
        this.statusDevolucion = '1';
        this.lineaEmisor = 'A';
        this.lineaRemitente = 'CLIENTE';
        this.cfdi = new Cfdi();
        this.complementos = [];
    }
}
//# sourceMappingURL=factura.js.map