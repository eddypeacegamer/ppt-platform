import { Impuesto } from './impuesto';

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
	public impuestos : Impuesto[];
	public retenciones : any[];

	
	constructor(){
		this.descuento = 0;
		this.valorUnitario = 0;
		this.valorUnitario = 0;
		this.importe = 0;
		this.impuestos = [];
		this.retenciones = [];
	}

	
}