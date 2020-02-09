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
    public receptor: string;
    public tipoReceptor: string;
    public fechaCreacion: Date;
    public fechaActualizacion: Date;
}
