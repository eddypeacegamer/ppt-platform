import { Injectable } from '@angular/core';
import { PagoDevolucion } from '../../models/pago-devolucion';
import { Cfdi } from '../../models/factura/cfdi';
import { Client } from '../../models/client';
import { Concepto } from '../../models/factura/concepto';
import { PaymentsData } from '../data/payments-data';
import { Factura } from '../../models/factura/factura';
import { filter } from 'rxjs/operators';
import { PagoFactura } from '../../models/pago-factura';

@Injectable({
  providedIn: 'root',
})
export class DevolucionValidatorService {

  constructor() { }

  public calculateDevolutionAmmount(factura: Factura, payment: PagoFactura, client: Client, tipoReceptor: string): number {
    //TODO set this methiod as promise

    const impuestos = this.getTotalImpuestos(factura.cfdi.conceptos);
    const retenciones = this.getTotalRetenciones(factura.cfdi.conceptos);
    


    if (payment) {
      if (factura.cfdi.metodoPago === 'PUE') {
        if (tipoReceptor === 'CLIENTE') {
          return factura.cfdi.subtotal + (impuestos * client.porcentajeCliente / 16) - retenciones;
        }
        if (tipoReceptor === 'CONTACTO') {
          return impuestos * client.porcentajeContacto / 16;
        }
        if (tipoReceptor === 'PROMOTOR') {
          return impuestos * client.porcentajePromotor / 16;
        }
      } else {
        const porcentajeImpuestos = impuestos / factura.cfdi.total;
        //const porcentajeRetenciones = retenciones / factura.cfdi.total;
        const baseComision = payment.monto * porcentajeImpuestos;
        if (tipoReceptor === 'CLIENTE') {
          return payment.monto  + baseComision * (client.porcentajeCliente / 16 - 1);
          //return payment.monto + baseComision * (client.porcentajeCliente / 16) - baseComision
          //return (payment.monto - baseComision + baseRetencion ) + baseComision * (client.porcentajeCliente / 16) - baseRetencion;
        }
        if (tipoReceptor === 'CONTACTO') {
          return baseComision * client.porcentajeContacto / 16;
        }
        if (tipoReceptor === 'PROMOTOR') {
          return baseComision * client.porcentajePromotor / 16;
        }
      }
    } else {
      return 0;
    }


  }


  public calcularImportes(impuestos): number {
    if (impuestos.length > 0) {
      return impuestos.map(i => i.importe).reduce((total, value) => total + value);
    } else {
      return 0;
    }
  }

  public getTotalImpuestos(conceptos: Concepto[]): number {
    let impuestos = 0;
    for (const concepto of conceptos) {
      impuestos += this.calcularImportes(concepto.impuestos);
    }
    return impuestos;
  }

  public getTotalRetenciones(conceptos: Concepto[]): number {
    let retenciones = 0;
    for (const concepto of conceptos) {
      retenciones += this.calcularImportes(concepto.retenciones);
    }
    return retenciones;
  }

  public validateDevolution(maxAmmount: Number, solicitud: PagoDevolucion): string[] {
    const messages = [];
    if (solicitud.beneficiario === undefined || solicitud.beneficiario.length < 5) {
      messages.push('La informacion del beneficiario es requerida.');
    }
    if (solicitud.monto === undefined) {
      messages.push('El monto de la devolucion es un valor requerido');
    } else {
      if (solicitud.monto <= 0) {
        messages.push(`No es posible realizar solicitudes de montos negativos`);
      }
      if (solicitud.monto > maxAmmount) {
        messages.push(`No se cuentan con fondos suficientes para la devolucion,
              saldo disponible ${(maxAmmount > 0) ? maxAmmount : 0}`);
      }
    }
    if (solicitud.formaPago === undefined || solicitud.formaPago === '*') {
      messages.push('La forma de pago es requerida.');
    } else {
      if (solicitud.formaPago === 'EFECTIVO' || solicitud.formaPago === 'CHEQUE') {
        if (solicitud.tipoReferencia !== 'DIRECCION' || solicitud.referencia === undefined
          || solicitud.referencia.length === 0) {
          messages.push('Los pagos en efectivo/cheque requiren como referencia a la direccion de entrega');
        }
      }
      if (solicitud.formaPago === 'FACTURA') {
        if (solicitud.tipoReferencia !== 'FOLIO' || solicitud.referencia === undefined
          || solicitud.referencia.length === 0) {
          messages.push('Los pagos en abono a factura requiren como referencia el folio de la factura');
        }
      }
      if (solicitud.formaPago === 'TRANSFERENCIA') {
        if (solicitud.banco === undefined || solicitud.banco === '*') {
          messages.push('La informacion del banco es un valor requerido');
        }
        if (solicitud.tipoReferencia === undefined || solicitud.tipoReferencia === '*') {
          messages.push('El tipo de referencia es un valor requerido');
        } else {
          if (solicitud.tipoReferencia === 'CLABE' && !((new RegExp(/^[\d]{18}$/)).test(solicitud.referencia))) {
            messages.push('La clabe especificada debe de contener 18 digitos numericos');
          }
          if (solicitud.tipoReferencia === 'TC' && !((new RegExp(/^[\d]{16}$/)).test(solicitud.referencia))) {
            messages.push('Para pagos con tarjeta de credito son necesarios 16 digitos numericos');
          }
          if (solicitud.tipoReferencia === 'TD' && !((new RegExp(/^[\d]{16}$/)).test(solicitud.referencia))) {
            messages.push('Para pagos con tarjeta de debito son necesarios 16 digitos numericos');
          }
        }

      }
    }
    if (solicitud.tipoReceptor === undefined || solicitud.tipoReceptor.length < 1) {
      messages.push('La informacion del tipo receptor es requerida.');
    }
    if (solicitud.receptor === undefined || solicitud.receptor.length === 0) {
      messages.push('La informacion del receptor es requerida.');
    }
    if (solicitud.solicitante === undefined || solicitud.solicitante.length === 0) {
      messages.push('La informacion del solicitante es requerida.');
    }
    return messages;
  }

}

