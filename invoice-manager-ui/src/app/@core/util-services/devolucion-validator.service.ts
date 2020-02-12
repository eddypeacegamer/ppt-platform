import { Injectable } from '@angular/core';
import { PagoDevolucion } from '../../models/pago-devolucion';

@Injectable({
  providedIn: 'root',
})
export class DevolucionValidatorService {

  constructor() { }

  public validateDevolution(maxAmmount: Number, solicitud: PagoDevolucion): string[] {
    const messages = [];
    if (solicitud.beneficiario === undefined || solicitud.beneficiario.length < 5) {
      messages.push('La informacion del beneficiario es requerida.');
    }
    if (solicitud.monto === undefined) {
      messages.push('El monto de la devolucion es un valor requerido');
    }else {
      if (solicitud.monto > maxAmmount || solicitud.monto <= 0) {
        messages.push(`El monto solicitado es invalido`);
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
          if (solicitud.tipoReferencia === 'CLABE' && solicitud.referencia.length !== 18) {
            messages.push('La clabe especificada debe de contener 18 digitos');
          }
          if (solicitud.tipoReferencia === 'TC' && solicitud.referencia.length !== 16) {
            messages.push('Para pagos con tarjeta de credito son necesarios 16 digitos');
          }
          if (solicitud.tipoReferencia === 'TD' && solicitud.referencia.length !== 16) {
            messages.push('Para pagos con tarjeta de debito son necesarios 16 digitos');
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

