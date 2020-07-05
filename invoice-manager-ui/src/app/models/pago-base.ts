import { PagoFactura } from './pago-factura';

export class PagoBase {

    public id: number;
    public cuenta: string;
    public moneda: string;
    public banco: string;
    public tipoDeCambio: number;
    public formaPago: string;
    public monto: number;
    public statusPago: string;
    public comentarioPago: string;
    public acredor: string;
    public deudor: string;
    public revision1: boolean;
    public revision2: boolean;
    public solicitante: string;
    public revisor1: string;
    public revisor2: string;
    public fechaPago: Date;
    public fechaCreacion: Date;
    public fechaActualizacion: Date;
    public facturas: PagoFactura[];

    public documento: string;

    constructor()  {
        this.monto = 0;
        this.tipoDeCambio = 1.00;
        this.statusPago = 'VALIDACION';
        this.moneda = 'MXN';
        this.revision1 = false;
        this.revision2 = false;
        this.facturas = [];
    }
}
