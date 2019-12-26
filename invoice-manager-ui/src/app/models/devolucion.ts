
export class Devolucion{

    public id:number;
	public idPagoOrigen:number;
	public idPagoDestino:number;
	public folio:string;
	public statusDevolucion:string;
	public monto:number;
	public receptor:string;
	public tipoReceptor:string;
	public fechaCreacion:Date;
	public fechaActualizacion:Date;

	public solicitud:boolean;

	constructor(){
		this.solicitud = false;
	}
}

