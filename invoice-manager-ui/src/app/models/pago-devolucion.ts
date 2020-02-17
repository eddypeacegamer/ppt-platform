export class PagoDevolucion {

    public id: number;
    public moneda: string;
    public tipoCambio: number;
    public monto: number;
    public beneficiario: string;
    public formaPago: string;
    public banco: string;
    public tipoReferencia: string;
    public referencia: string;
    public status: string;
    public fechaPago: Date;
    public solicitante: string;
    public comentarios: string;
    public receptor: string;
    public cuentaPago: string;
    public rfcEmpresa: string;
    public autorizador: string;
    public idDevolucion: number;
    public tipoReceptor: string;
    public fechaCreacion: Date;
    public fechaActualizacion: Date;

    constructor() {
        this.moneda = 'MXN';
        this.tipoCambio = 1.00;
        this.status = 'VALIDACION';
        this.banco = 'N/A';
        this.formaPago = '*';
        this.tipoReferencia = '*';
    }
}
