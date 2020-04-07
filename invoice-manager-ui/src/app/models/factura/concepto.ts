import { Impuesto } from './impuesto';

export class Concepto{
    public id : number;
	public claveProdServ : string;
	public noIdentificacion : string;
	public cantidad : number;
	public claveUnidad : string;
	public descripcionCUPS: string;
	public unidad : string;
	public descripcion : string;
	public valorUnitario : number;
	public importe : number;
	public descuento : number;
	public retencion : number;
	public impuestos : Impuesto[];
	public retenciones : Impuesto[];

	//fields only used on UI
	public iva: boolean;
	public retencionFlag: boolean;
	
	constructor(){
		this.descuento = 0;
		this.cantidad = 0;
		this.valorUnitario = 0;
		this.importe = 0;
		this.retencion=0;
		this.impuestos = [];
		this.retenciones = [];
		this.iva = true;
		this.retencionFlag=false;
	}

	
}