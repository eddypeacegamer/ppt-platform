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
	public statusFactura : StatusFactura ;
    public uuid : string;
    public statusDetail : string;
    public fechaCreacion : Date;
    public fechaActualizacion : Date;
    public fechaTimbrado : Date ;
	

	constructor(){
		this.tipoDocumento = 'Factura';
	}
}