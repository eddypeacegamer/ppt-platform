import { Injectable } from '@angular/core';
import { Empresa } from '../../models/empresa';

@Injectable({
  providedIn: 'root',
})
export class CompaniesValidatorService {

  camposObligatoriosEmpresa = [
    {
      field: 'encabezado',
      description: 'Encabezado',
    },
    {
      field: 'tipo',
      description: 'Linea',
    },
    {
      field: 'piePagina',
      description: 'Pie de página',
    },
    {
      field: 'contactoAdmin',
      description: 'Contacto Admin.',
    },
    {
      field: 'correo',
      description: 'Correo',
    },
    {
      field: 'pwCorreo',
      description: 'Password Correo',
    },
    {
      field: 'pwSat',
      description: 'Password Sat',
    },
    {
      field: 'certificado',
      description: 'Certificado',
    },
    {
      field: 'llavePrivada',
      description: 'Llave privada',
    },
    {
      field: 'logotipo',
      description: 'Logotipo',
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

  public validarEmpresa(empresa: Empresa) {
    const messages: string[] = [];

    if (empresa == null) {
      throw 'La empresa a validar tiene un valor de null';
    }

    if (empresa.giro == null || empresa.giro === "*" || empresa.giro.length < 1) {
      messages.push("Se debe seleccionar un giro de empresa");
    }

    if (empresa.tipo == null || empresa.tipo === "*" || empresa.tipo.length < 1) {
      messages.push("Se debe seleccionar la linea de empresa");
    }

    this.camposObligatoriosInformacionFiscal.forEach(campo => {
      if(empresa.informacionFiscal[campo.field] === null
        || empresa.informacionFiscal[campo.field] === '') {
          messages.push(`El campo '${campo.description}' es obligatorio`);
        }
    });

    this.camposObligatoriosEmpresa.forEach(campo => {
      if (empresa[campo.field] === null
        || empresa[campo.field] === '') {
          messages.push(`El campo '${campo.description}' es obligatorio`);
        }
    });

    return messages;
  }
}
