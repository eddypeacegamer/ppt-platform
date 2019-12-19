import { StatusFactura } from '../status-factura';
import { Cfdi } from './cfdi';

export class Factura{

	
	public id:number;
	//obligatorio
	public rfcEmisor : string;
    public rfcRemitente : string ;
    public razonSocialEmisor : string;
    public razonSocialRemitente : string;
	public solicitante : string;
	public tipoDocumento : string;
	public cfdi : Cfdi;
	public total : number;
	public subtotal : number;
	public descuento : number;
	//opcional
	public notas : string;
	public folioPadre : string;
	//duplicado
	public formaPago : string;
    public metodoPago : string;
	//generado
	public statusPago : string ;
	public statusDevolucion : string ;
	public statusFactura : string ;
    public uuid : string;
    public statusDetail : string;
    public fechaCreacion : Date;
    public fechaActualizacion : Date;
    public fechaTimbrado : Date ;
	public folio : string;

	constructor(){
		this.tipoDocumento = 'Factura';
		this.total = 0;
		this.subtotal  = 0;	
		this.descuento = 0;
		this.statusFactura ='1';
		this.statusDetail = '1';
		this.statusPago = '1';
		this.statusDevolucion = '1';
		this.cfdi = new Cfdi();
	}
}