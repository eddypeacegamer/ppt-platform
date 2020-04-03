import { Injectable } from '@angular/core';
import { PagoDevolucion } from '../../models/pago-devolucion';
import { Cfdi } from '../../models/factura/cfdi';
import { Client } from '../../models/client';

@Injectable({
  providedIn: 'root',
})
export class DevolucionValidatorService {

  constructor() { }


  public calculateDevolutionAmmount(cfdi: Cfdi, client: Client, tipoReceptor: string): number {
    if (cfdi.metodoPago === 'PUE') {
      const baseComisiones = cfdi.total - cfdi.subtotal;
      if (tipoReceptor === 'CLIENTE') {
        return cfdi.subtotal + baseComisiones * client.porcentajeCliente / 16;
      }
      if (tipoReceptor === 'CONTACTO') {
        return baseComisiones * client.porcentajeContacto / 16;
      }
      if (tipoReceptor === 'PROMOTOR') {
        return baseComisiones * client.porcentajePromotor / 16;
      }
    }else {
      const porcentajeImpuestos = (cfdi.total - cfdi.subtotal) / cfdi.total;
      const baseComisiones = cfdi.complemento.pagos[0].monto * porcentajeImpuestos;
      if (tipoReceptor === 'CLIENTE') {
        return cfdi.complemento.pagos[0].monto + baseComisiones * (client.porcentajeCliente / 16 - 1);
      }
      if (tipoReceptor === 'CONTACTO') {
        return baseComisiones * client.porcentajeContacto / 16;
      }
      if (tipoReceptor === 'PROMOTOR') {
        return baseComisiones * client.porcentajePromotor / 16;
      }
    }
  }

  public validateDevolution(maxAmmount: Number, solicitud: PagoDevolucion): string[] {
    const messages = [];
    if (solicitud.beneficiario === undefined || solicitud.beneficiario.length < 5) {
      messages.push('La informacion del beneficiario es requerida.');
    }
    if (solicitud.monto === undefined) {
      messages.push('El monto de la devolucion es un valor requerido');
    }else {
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
    }else {
      if (solicitud.formaPago === 'EFECTIVO' || solicitud.formaPago === 'CHEQUE') {
        if (solicitud.tipoReferencia !== 'DIRECCION' || solicitud.referencia === undefined
              || solicitud.referencia.length === 0) {
          messages.push('Los pagos en efectivo/cheque requiren como referencia a la direccion de entrega');
          }
      }
      if (solicitud.formaPago === 'FACTURA') {
        if (solicitud.tipoReferencia !== 'FOLIO' ||  solicitud.referencia === undefined
                || solicitud.referencia.length === 0) {
            messages.push('Los pagos en abono a factura requiren como referencia el folio de la factura');
        }
      }
      if (solicitud.formaPago === 'TRANSFERENCIA') {
        if (solicitud.banco === undefined || solicitud.banco === '*') {
          messages.push('La informacion del banco es un valor requerido');
        }
        if (solicitud.tipoReferencia === undefined || solicitud.tipoReferencia === '*' ) {
          messages.push('El tipo de referencia es un valor requerido');
        }else {
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
    if (solicitud.tipoReceptor === undefined || solicitud.tipoReceptor.length < 1 ){
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

