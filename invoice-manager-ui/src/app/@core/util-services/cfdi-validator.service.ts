import { Injectable } from '@angular/core';
import { Concepto } from '../../models/factura/concepto';
import { Cfdi } from '../../models/factura/cfdi';
import { Impuesto } from '../../models/factura/impuesto';
import { CatalogsData } from '../data/catalogs-data';

@Injectable({
  providedIn: 'root',
})
export class CfdiValidatorService {

  private usoCfdiCat: string[] = ['D01','D02','D03','D04','D05','D06','D07','D08','D09','D10','G01','G02','G03','I01','I02','I03','I04','I05','I06','I07','I08','P01'];
  private metodoPagoCat: string[]  = ['PUE','PPD'];
  private formaPagoCat: string[]  = ['01','02','03','04','05','06','08','12','13','14','15','17','23','24','25','26','27','28','29','30','31','99'];
  private unidadCat: string[]  = ['10','11','A9','AB ','ACT','AS','BB','DAY','DPC','E48','E51','E54','EA','GRM','H87','HUR','KGM','KT','LTR','MGM','MLT','MON','MTK','MTR','PR','SET','XBX','XKI','XLT','XPK','XUN'];

  constructor(private catService: CatalogsData) {

  }

  public buildConcepto(concepto: Concepto): Concepto {
    concepto.importe = concepto.cantidad * concepto.valorUnitario;
    const base = concepto.importe - concepto.descuento;
    if (concepto.iva) {
      const impuesto = base * 0.16; // TODO calcular impuestos dinamicamente no solo IVA
      concepto.impuestos = [new Impuesto('002', '0.160000', base, impuesto)]; }
      if (concepto.retencionFlag) {
      const retencion = base * 0.06; // TODO calcular retencion dinamicamente
      concepto.retenciones = [new Impuesto('002', '0.060000', base, retencion)]; }
    return concepto;
  }

  public validarConcepto(concepto: Concepto): string[] {
    const messages: string[] = [];
    if (concepto.cantidad <= 0) {
      messages.push('La cantidad requerida debe ser mayor a 0');
    }
    if (concepto.claveProdServ === undefined || concepto.claveProdServ === '*') {
      messages.push('La clave producto servicio del concepto es un valor requerido.');
    }
    if (concepto.claveUnidad === undefined || concepto.claveUnidad === '*') {
      messages.push('La clave de unidad y la unidad son campos requeridos.');
    } else if (this.unidadCat.find(e => e === concepto.claveUnidad) === undefined) {
      messages.push(`La clave de unidad ${concepto.claveUnidad} no es valida.`);
    }
    if (concepto.descripcion === undefined || concepto.descripcion.length < 1) {
      messages.push('La descripción del concepto es un valor requerido.');
    }
    if (concepto.valorUnitario <= 0) {
      messages.push('El valor unitario del  concepto no puede ser menor igual a 0 pesos.');
    }
    return messages;
  }

  public calcularImportes(cfdi: Cfdi): Cfdi {
    let total = 0;
    let subtotal = 0;
    for (const concepto of cfdi.conceptos) {
      let impuesto = 0;
      let retencion = 0;
      for (const imp of concepto.retenciones) {
        retencion = (imp.importe * 3 + retencion * 3) / 3;
      }
      const base = concepto.importe - concepto.descuento-retencion;
      subtotal += base;
      for (const imp of concepto.impuestos) {
        impuesto = (imp.importe * 3 + impuesto * 3) / 3;
      }
      total += (base * 3 + impuesto * 3) / 3;
    }
    cfdi.total = total;
    cfdi.subtotal = subtotal;
    return cfdi;
  }

  public validarCfdi(cfdi: Cfdi): string[] {
    const messages: string[] = [];
    if (cfdi.receptor === undefined || cfdi.receptor.rfc === undefined  || cfdi.receptor.rfc.length < 8) {
      messages.push('La información del receptor es un valor solicitado');
    }
    if (cfdi.emisor === undefined  || cfdi.emisor.rfc === undefined || cfdi.emisor.rfc.length < 8) {
      messages.push('La información del emisor es un valor solicitado');
    }
    if (cfdi.receptor.usoCfdi === undefined || cfdi.receptor.usoCfdi === '*') {
      messages.push('El uso del CFDI es un campo requerido.');
    }else if (this.usoCfdiCat.find(e => e === cfdi.receptor.usoCfdi) === undefined) {
      messages.push(`El uso de cfdi ${cfdi.receptor.usoCfdi} no es valido.`);
    }
    if (cfdi.moneda === undefined) {
      messages.push('La moneda es un campo requerido.');
    }else if (cfdi.moneda !== 'MXN') {
      messages.push('Solo son soportadas facturas en pesos');
    }
    if (cfdi.formaPago === undefined || cfdi.formaPago === '*' ) {
      messages.push('La forma de pago es un campo requerido.');
    }else if (this.formaPagoCat.find(e => e === cfdi.formaPago) === undefined) {
      messages.push(`La forma de pago ${cfdi.formaPago} no es valida.`);
    }
    if (cfdi.metodoPago === undefined || cfdi.metodoPago === '*') {
      messages.push('El metodo de pago es un campo requerido.');
    }else if (this.metodoPagoCat.find(e => e === cfdi.metodoPago) === undefined) {
      messages.push(`El metodo de pago ${cfdi.metodoPago} no es valido.`);
    }
    if (cfdi.conceptos.length < 1) {
      messages.push('La factura debe contener a menos 1 concepto a declarar.');
    }
    if (cfdi.total > 2000 && cfdi.formaPago === '01') {
      messages.push('En pagos en efectivo el monto a facturar no debe de ser superior a 2000 pesos');
    }
    return messages;
  }
}
