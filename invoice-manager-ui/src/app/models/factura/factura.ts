import { StatusFactura } from '../status-factura';
import { Cfdi } from './cfdi';

export class Factura{

	//obligatorio
	public rfcEmisor : string;
    public rfcRemitente : string ;
    public razonSocialEmisor : string;
    public razonSocialRemitente : string;
	public folioPadre : string;
	public tipoDocumento : string;
	public cfdi : Cfdi;
	public total : string;
	//opcional
	public notas : string;
    
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
		this.metodoPago = 'PUE';
		this.formaPago = '003';
		this.notas ='campo no obligatorio a remover';
	}
}