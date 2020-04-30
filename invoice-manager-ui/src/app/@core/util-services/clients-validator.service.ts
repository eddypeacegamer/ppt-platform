import { Injectable } from '@angular/core';
import { Client } from '../../models/client';


@Injectable({
  providedIn: 'root',
})
export class ClientsValidatorService {

    camposObligatoriosCliente = [
        {
          field: 'porcentajePromotor',
          description: 'porcentaje promotor',
        },
        {
          field: 'porcentajeCliente',
          description: 'porcentaje cliente',
        },
        {
          field: 'porcentajeDespacho',
          description: 'porcentaje despacho',
        },
        {
          field: 'porcentajeContacto',
          description: 'porcentaje contacto',
        },
        {
          field: 'correoPromotor',
          description: 'correo promotor',
        },
        {
          field: 'informacionFiscal',
          description: 'informacion fiscal',
        },
      ];
    
      camposObligatoriosInformacionFiscal = [
        {
          field: 'rfc',
          description: 'RFC',
        },
        {
          field: 'razonSocial',
          description: 'Razón Social',
        },
        {
          field: 'cp',
          description: 'Código Postal',
        },
        {
          field: 'municipio',
          description: 'Municipio',
        },
        {
          field: 'estado',
          description: 'Estado',
        },
        {
          field: 'localidad',
          description: 'Localidad',
        },
        {
          field: 'calle',
          description: 'Calle',
        },
      ];

      public validarCliente(cliente: Client) {
        const messages: string[] = [];
        if (cliente == null) {
            throw 'El cliente a validar tiene un valor de null';
          }

        this.camposObligatoriosInformacionFiscal.forEach(campo => {
          if(cliente.informacionFiscal[campo.field] === null
            || cliente.informacionFiscal[campo.field] === '') {
              messages.push(`El campo '${campo.description}' es obligatorio`);
            }
        });
        this.camposObligatoriosCliente.forEach(campo => {
          if (cliente[campo.field] === null
            || cliente[campo.field] === '') {
              messages.push(`El campo '${campo.description}' es obligatorio`);
            }
        });
        if (Math.abs(cliente.porcentajeCliente + cliente.porcentajeContacto
                + cliente.porcentajeDespacho + cliente.porcentajePromotor - 16) >= 0.01) {
            messages.push('La suma de los porcentajes asignados no debe ser mayor o menor a 16%');
        }
        if (cliente.porcentajeDespacho < 4) {
            messages.push('El porcentaje asignado al despacho no debe ser menor al 4%');
        }
        if (cliente.porcentajeContacto > 0 && (cliente.correoContacto === undefined)) {
            messages.push('Si fue asignado un porcentaje a un contacto, se debe de especificar una cuenta de correo el mismo.');
        }
        return messages;
      }

}