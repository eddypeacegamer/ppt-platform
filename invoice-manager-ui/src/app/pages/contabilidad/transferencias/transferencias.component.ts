import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import * as XLSX from 'xlsx';
import { CompaniesData } from '../../../@core/data/companies-data';
import { TransferData } from '../../../@core/data/transfers-data';
import { HttpErrorResponse } from '@angular/common/http';
import { CfdiValidatorService } from '../../../@core/util-services/cfdi-validator.service';
import { Factura } from '../../../models/factura/factura';
import { Cfdi } from '../../../models/factura/cfdi';
import { Concepto } from '../../../models/factura/concepto';
import { Impuesto } from '../../../models/factura/impuesto';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { Empresa } from '../../../models/empresa';

@Component({
  selector: 'ngx-transferencias',
  templateUrl: './transferencias.component.html',
  styleUrls: ['./transferencias.component.scss']
})
export class TransferenciasComponent implements OnInit {


  @ViewChild('fileInput', { static: true })
  public fileInput: ElementRef;
  public transfers: any[] = [];
  public params: any = { lineaRetiro: 'A', lineaDeposito: 'B', filename: '', dataValid: false };

  public errorMessages: string[] = [];
  private companies: any = {};

  constructor(private companyService: CompaniesData,
    private cfdiValidator: CfdiValidatorService,
    private invoiceService: InvoicesData) { }

  ngOnInit() {
    this.params = { lineaRetiro: 'A', lineaDeposito: 'B', filename: '', dataValid: false };
    this.companies = {};
    this.errorMessages = [];
  }

  onFileChange(files) {
    let workBook = null;
    let jsonData = null;
    const reader = new FileReader();
    const file = files[0];
    this.companies = {};
    this.params.filename = file.name;
    reader.onload = (event) => {
      const data = reader.result;
      workBook = XLSX.read(data, { type: 'binary' });
      jsonData = workBook.SheetNames.reduce((initial, name) => {
        const sheet = workBook.Sheets[name];
        initial[name] = XLSX.utils.sheet_to_json(sheet);
        return initial;
      }, {});
      if (jsonData.CARGA_MASIVA === undefined) {
        alert('Formato Excel invalido');
      } else {
        this.transfers = jsonData.CARGA_MASIVA;
        this.getCompaniesInfo(this.transfers);
      }
    }
    reader.readAsBinaryString(file);
  }

  calcularTotal() {
    if (this.transfers === undefined || this.transfers.length === 0) {
      return 0;
    } else {
      return this.transfers.map(t => t.TOTAL).reduce((total, m) => total + m);
    }
  }

  clean() {
    this.transfers = [];
    this.companies = {};
    this.params.dataValid = false;
    this.params.filename = '';
    this.errorMessages = [];
    this.fileInput.nativeElement.value = '';
    this.params.successMessage = undefined;
  }

  validarInformacion() {
    this.params.successMessage = undefined;
    this.params.dataValid = true;
    this.errorMessages = [];
    if (this.transfers !== undefined && this.transfers.length > 0) {
      for (const transfer of this.transfers) {
        transfer.observaciones = [];
        if (this.companies[transfer.RFC_EMISOR].tipo !== this.params.lineaDeposito) {
          transfer.observaciones.push(`${transfer.RFC_EMISOR} no es de tipo ${this.params.lineaDeposito}`);
          this.params.dataValid = false;
        }else if (!this.companies[transfer.RFC_EMISOR].activo) {
          transfer.observaciones.push(`${transfer.RFC_EMISOR} no se encunetra activa`);
        }
        if (this.companies[transfer.RFC_RECEPTOR].tipo !== this.params.lineaRetiro) {
          transfer.observaciones.push(`${transfer.RFC_RECEPTOR} no es de tipo ${this.params.lineaRetiro}`);
          this.params.dataValid = false;
        }else if (!this.companies[transfer.RFC_EMISOR].activo) {
          transfer.observaciones.push(`${transfer.RFC_RECEPTOR} no se encunetra activa`);
        }
        const fact = this.buildFacturaFromTransfer(transfer,
              this.companies[transfer.RFC_EMISOR], this.companies[transfer.RFC_RECEPTOR]);
        transfer.observaciones = this.cfdiValidator.validarCfdi(fact.cfdi);

        if (transfer.observaciones.length === 0){
          transfer.observaciones = 'VALIDO';
        }
      }
    } else {
      this.errorMessages.push('No se encontro informacion cargada o valida');
    }
  }

  cargarTransferencias() {
    this.params.successMessage = undefined
    this.errorMessages = [];
    // if (this.facturas.length > 0) {
    //   for (const factura of this.facturas) {
    //     this.invoiceService.insertNewInvoice(factura).subscribe(fact => console.log(fact),
    //       (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
    //         || `${error.statusText} : ${error.message}`));
    //   }
    // }
  }

  private getCompaniesInfo(transfers: any[]) {
    for (const transfer of this.transfers) {
      this.companyService.getCompanyByRFC(transfer.RFC_EMISOR)
          .subscribe(company => this.companies[transfer.RFC_EMISOR] = company);
      this.companyService.getCompanyByRFC(transfer.RFC_RECEPTOR)
          .subscribe(company => this.companies[transfer.RFC_RECEPTOR] = company);
    }
  }

  private buildFacturaFromTransfer(transfer: any,emisorCompany: Empresa, receptorCompany: Empresa): Factura {
    const factura = new Factura();
    factura.rfcEmisor = transfer.RFC_EMISOR;
    factura.razonSocialEmisor = receptorCompany.informacionFiscal.razonSocial;
    factura.lineaEmisor = this.params.lineaDeposito;
    factura.rfcRemitente = transfer.RFC_RECEPTOR;
    factura.razonSocialRemitente = emisorCompany.informacionFiscal.razonSocial;
    factura.lineaRemitente = this.params.lineaRetiro;
    factura.metodoPago = transfer.METODO_PAGO;
    factura.statusFactura = '4';
    factura.solicitante = 'CARGA_MASIVA';
    const cfdi = new Cfdi();
    cfdi.receptor.rfc = transfer.RFC_RECEPTOR;
    cfdi.receptor.usoCfdi = 'P01';
    cfdi.emisor.rfc = transfer.RFC_EMISOR;
    cfdi.emisor.regimenFiscal = emisorCompany.regimenFiscal;
    cfdi.formaPago = transfer.FORMA_PAGO;
    cfdi.moneda = 'MXN';
    cfdi.total = transfer.TOTAL;
    cfdi.subtotal = transfer.IMPORTE;
    cfdi.metodoPago = transfer.METODO_PAGO;
    const concepto = new Concepto();
    concepto.cantidad = transfer.CANTIDAD;
    concepto.claveProdServ = transfer.CLAVE_PROD_SERVICIO;
    concepto.claveUnidad = transfer.CLAVE_UNIDAD;
    concepto.descripcion = transfer.CONCEPTO;
    concepto.descripcionCUPS = transfer.CONCEPTO;
    concepto.unidad = transfer.UNIDAD;
    concepto.valorUnitario = transfer.PRECIO_UNITARIO;
    concepto.importe = transfer.IMPORTE;
    concepto.iva = true;
    this.cfdiValidator.validarConcepto(concepto);
    cfdi.conceptos.push(this.cfdiValidator.buildConcepto(concepto));
    
    factura.cfdi = this.cfdiValidator.calcularImportes(cfdi);
    return factura;
  }

}
