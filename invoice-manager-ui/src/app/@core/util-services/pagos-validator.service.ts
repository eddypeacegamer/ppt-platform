import { Injectable } from '@angular/core';
import { Pago } from '../../models/pago';
import { Cfdi } from '../../models/factura/cfdi';

@Injectable({
  providedIn: 'root',
})
export class PagosValidatorService {

  constructor() { }


  public validatePago(pago: Pago, pagos: Pago[], cfdi: Cfdi): string[] {
    console.log(pago);
    const messages = [];
    if (pago.banco === undefined) {
      messages.push('El banco es un valor requerido');
    }
    if (pago.fechaPago === undefined) {
      messages.push('La fecha de pago es un valor requerido');
    }
    if (pago.moneda === undefined) {
      messages.push('Es necesario especificar la moneda con la que se realizo el pago.');
    }
    if (pago.monto === undefined) {
      messages.push('El monto del pago es requerido.');
    }
    if (pago.monto <= 0) {
      messages.push('El monto pagado es invalido');
    }
    if (pago.formaPago === undefined) {
      messages.push('El tipo de pago es requerido.');
    }
    if (pago.formaPago !== 'CREDITO' && pago.documento === undefined) {
      messages.push('La imagen del documento de pago es requerida.');
    }
    if (cfdi.metodoPago === 'PUE' && Math.abs(cfdi.total - pago.monto) > 0.01) {
      messages.push('Para pagos en una unica exibicion,' +
      'el monto del pago debe coincidir con el monto total de la factura.');
    }
    if ((this.getPaymentAmmount(pagos) + pago.monto - cfdi.total) > 0.01) {
      messages.push('La suma de los pagos no puede ser superior al monto total de la factura.');
    }

    return messages;
  }

  getPaymentAmmount(pagos: Pago[]): number {
    if (pagos.length === 0) {
      return 0;
    } else {
      const payments: Pago[] = pagos.filter(p => p.formaPago !== 'CREDITO');
      if (payments.length === 0) {
        return  0;
      } else {
        return payments.map((p: Pago) => p.monto).reduce((total, p) => total + p);
      }
    }
  }
}
