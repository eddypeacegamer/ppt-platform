export class PagoFactura {
    public id: number;
    public idCfdi: number;
    public folio: string;
    public monto: number;
    public totalFactura: number;
    public acredor: string;
    public deudor: string;
    public fechaCreacion: Date;
    public fechaActualizacion: Date;


    constructor() {

    }
}
