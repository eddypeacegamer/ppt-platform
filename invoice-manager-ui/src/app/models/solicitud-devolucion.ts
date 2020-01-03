import { Devolucion } from './devolucion';

export class SolicitudDevolucion{

    public cuenta:string;
	public banco:string;
	public user:string;
	public beneficiario:string;
	public formaPago:string;
	public moneda:string;
	public tipoCambio:number;
    public devoluciones:Devolucion[];

    constructor(){
        this.tipoCambio = 1.00;
        this.devoluciones = [];
        this.moneda = 'MXN';
    }
}