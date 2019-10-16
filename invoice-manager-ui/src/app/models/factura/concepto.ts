export class Concepto{
    public id : number;
	public claveProdServ : string;
	public noIdentificacion : string;
	public cantidad : number;
	public claveUnidad : string;
	public unidad : string;
	public descripcion : string;
	public valorUnitario : number;
	public importe : number;
	public descuento : number;

	constructor(){
		this.descuento = 0;
		this.valorUnitario = 0;
		this.valorUnitario = 0;
		this.importe = 0;
	}
}