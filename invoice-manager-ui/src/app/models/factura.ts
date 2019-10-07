import { StatusFactura } from './status-factura';

export class Factura{
    public id : number;
	public version : string;
	public serie : string;
	public folio : string;
	public folioPadre : string;
	public fechaSolicitud : Date;
	public sello : string;
	public certificado : string;
	public noCertificado : string;
	public subtotal : number;
	public descuento: number;
	public moneda : string;
	public total : number;
	public tipoDeComprobante : string;
	public lugarDeExpedicion : string;
	public rfcEmisor : string;
	public usoCFDI : string;
	public razonSocialEmisor : string;
	public rfcRemitente : string;
	public regimenFiscal : string;
	public razonSocialRemitente : string;
	public uuid : string;
	public fechaTimbrado : Date;
	public rfcProvCertif : string;
	public selloCFD : string;
	public noCertificadoSat : string;
	public selloSAT : string;

	public formaPago : string;
	public metodoPago : string;
	public notas : string;
	public statusFactura : StatusFactura;
	public fechaCreacion : Date;
	public fechaActualizacion : Date;
}