import { StatusFactura } from '../status-factura';
import { Cfdi } from './cfdi';
import { Pago } from '../pago';

export class Factura {

	public id: number;
	public rfcEmisor: string;
	public razonSocialEmisor: string;
	public lineaEmisor: string;
	public rfcRemitente: string;
	public lineaRemitente: string;
	public razonSocialRemitente: string;
	public tipoDocumento: string;
	public solicitante: string;
	public metodoPago: string;
	public folio: string;
	public folioPadre: string;
	public uuid: string;
	public notas: string;
	public statusPago: string;
	public statusDevolucion: string;
	public statusFactura: string;
	public statusDetail: string;
	public statusCancelacion: string;
	public fechaCancelacion: string;
	public fechaCreacion: Date;
	public fechaActualizacion: Date;
	public fechaTimbrado: Date;
	public packFacturacion : string;
	public cfdi: Cfdi;
	public complementos: Factura[];
	
	constructor() {
		this.tipoDocumento = 'Factura';
		this.statusFactura = '1';
		this.statusDetail = '';
		this.statusPago = '1';
		this.statusDevolucion = '1';
		this.lineaEmisor = 'A';
		this.lineaRemitente = 'CLIENTE';
		this.cfdi = new Cfdi();
		this.complementos = [];
	}
}
