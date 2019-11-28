export class Pago{

    public id: number;
    public folio: string;
    public folioPadre : string;
    public moneda: string;
    public banco: string;
	public documento:string;
	public monto:number;
    public tipoPago:string;
    public formaPago:string;
	public fechaPago:Date;
	public fechaCreacion:Date;
    public fechaActualizacion:Date;
    public tipoDeCambio:number;
    public statusPago:string;
    public revision1:boolean;
    public revision2:boolean;
    public ultimoUsuario:string;
    public comentarioPago: string;


    constructor(){
        this.monto = 0;
        this.tipoDeCambio =1.00;
        this.statusPago = 'VALIDACION';
        this.revision1 = false;
        this.revision2 = false;
    }
}