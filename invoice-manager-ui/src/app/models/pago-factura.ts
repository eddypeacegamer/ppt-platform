export class PagoFactura {
    public id: number;
    public idCfdi: number;
    public folio: string;
    public monto: number;
    public metodoPago: string;
    public totalFactura: number;
    public acredor: string;
    public deudor: string;
    public fechaCreacion: Date;
    public fechaActualizacion: Date;


    constructor(monto?: number, folio?: string, emisor?: string, receptor?: string) {
        this.monto = monto;
        this.folio = folio;
        this.acredor = emisor;
        this.deudor = receptor;
    }
}
