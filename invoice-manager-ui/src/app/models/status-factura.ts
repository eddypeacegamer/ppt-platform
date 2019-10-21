export class StatusFactura{

    private id : number;
	private statusEvento : string;
	private statusPago : string;
	private statusDevolucion : string;
	private fechaActualizacion: Date;

	constructor(id?:number){
		this.id = id || 0;
	}
}