import { Injectable } from '@angular/core';
import { PagoBase } from '../../models/pago-base';
import { Factura } from '../../models/factura/factura';
import { add, subtract, bignumber, unequal, number } from 'mathjs';

@Injectable({
  providedIn: 'root',
})
export class PagosValidatorService {

  constructor() { }


  public validatePago(pago: PagoBase, factura: Factura): string[] {
    const messages = [];
    if (factura.metodoPago === 'PPD' && factura.statusFactura !== 'Timbrada') {
      messages.push('Las facturas PPD no pueden agregar pagos hasta que  no se encuentre timbrada la factura.');
    }
    if (pago.banco === undefined || pago.banco === '*') {
      messages.push('El banco es un valor requerido');
    }
    if (pago.fechaPago === undefined) {
      messages.push('La fecha de pago es un valor requerido');
    }
    if (pago.moneda === undefined || pago.moneda === '*') {
      messages.push('Es necesario especificar la moneda con la que se realizo el pago.');
    }
    if (pago.monto === undefined) {
      messages.push('El monto del pago es requerido.');
    }
    if (pago.monto <= 0) {
      messages.push('El monto pagado es invalido');
    }
    if (pago.formaPago === undefined || pago.formaPago === '*') {
      messages.push('El tipo de pago es requerido.');
    }
    if ((pago.formaPago === 'CHEQUE' || pago.formaPago === 'TRANSFERENCIA' || pago.formaPago === 'DEPOSITO')
           && pago.documento === undefined) {
      messages.push('La imagen del documento de pago es requerida.');
    }
    if (factura.cfdi.metodoPago === 'PUE' && Math.abs(factura.cfdi.total - pago.monto) > 0.01) {
      messages.push('Para pagos en una unica exibicion,' +
      'el monto del pago debe coincidir con el monto total de la factura.');
    }
    if (factura.saldoPendiente - pago.monto < -0.01) {
      messages.push('La suma de los pagos no puede ser superior al monto total de la factura.');
    }
    if(pago.facturas === undefined || pago.facturas.length === 0){
      messages.push('La asignacion de las facturas al pago no fue realizada');
    } else {
      let total = bignumber(0.0);
      for( const fact of pago.facturas){
        total = add(bignumber(fact.monto),total);
      }
      if(unequal(bignumber(pago.monto),total)){
        messages.push('La suma de los pagos a las facturas individuales no es igual al monto del pago.');
      }
    }

    return messages;
  }


  public validatePagoSimple(pago: PagoBase): string[] {
    
    const messages = [];
    if (pago.banco === undefined || pago.banco === '*') {
      messages.push('El banco es un valor requerido');
    }
    if (pago.fechaPago === undefined) {
      messages.push('La fecha de pago es un valor requerido');
    }
    if (pago.moneda === undefined || pago.moneda === '*') {
      messages.push('Es necesario especificar la moneda con la que se realizo el pago.');
    }
    if (pago.monto === undefined) {
      messages.push('El monto del pago es requerido.');
    }
    if (pago.monto <= 0) {
      messages.push('El monto pagado es invalido');
    }
    if (pago.formaPago === undefined || pago.formaPago === '*') {
      messages.push('El tipo de pago es requerido.');
    }
    if ((pago.formaPago === 'CHEQUE' || pago.formaPago === 'TRANSFERENCIA' || pago.formaPago === 'DEPOSITO')
           && pago.documento === undefined) {
      messages.push('La imagen del documento de pago es requerida.');
    }
    if(pago.facturas === undefined || pago.facturas.length === 0){
      messages.push('La asignacion de las facturas al pago no fue realizada');
    } else {
      let total = bignumber(0.0);
      for( const fact of pago.facturas){
        total = add(bignumber(fact.monto),total);
      }
      if(unequal(bignumber(pago.monto),total)){
        messages.push('La suma de los pagos a las facturas individuales no es igual al monto del pago.');
      }
    }

    return messages;
  }

}
