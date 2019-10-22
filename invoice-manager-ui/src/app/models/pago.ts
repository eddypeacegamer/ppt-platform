export class Pago{

    public id: number;
	public folio: string;
    public moneda: string;
    public banco: string;
	public documento:any;
	public monto:number;
	public tipoPago:string;
	public fechaPago:Date;
	public fechaCreacion:Date;
    public fechaActualizacion:Date;
    

    constructor(){
        this.monto = 0;
    }
}