export class Pago {

    public id: number;
    public version: string;
    public fechaPago: Date;
    public formaPago: string;
    public moneda: string;
    public monto: number;
    public folio: string;
    public idDocumento: string;
    public importePagado: number;
    public importeSaldoAnterior: number;
    public importeSaldoInsoluto: number;
    public metodoPago: string;
    public monedaDr: string;
    public numeroParcialidad: number;
    public serie: string;


    constructor() {
        this.moneda = 'MXN';
    }

}
