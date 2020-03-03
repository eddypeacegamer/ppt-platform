import { Component, OnInit, ViewChild, ElementRef} from '@angular/core';
import * as XLSX from 'xlsx';
import { CompaniesData } from '../../../@core/data/companies-data';
import { TransferData } from '../../../@core/data/transfers-data';
import { Transferencia } from '../../../models/transferencia';
import { HttpErrorResponse } from '@angular/common/http';
import { Factura } from '../../../models/factura/factura';
import { Cfdi } from '../../../models/factura/cfdi';
import { Concepto } from '../../../models/factura/concepto';
import { Impuesto } from '../../../models/factura/impuesto';

@Component({
  selector: 'ngx-transferencias',
  templateUrl: './transferencias.component.html',
  styleUrls: ['./transferencias.component.scss']
})
export class TransferenciasComponent implements OnInit {


  @ViewChild('fileInput',{static:true})
  public fileInput: ElementRef;
  public transfers: any[] = [];
  public params: any ={lineaRetiro:'A',lineaDeposito:'B',filename:'',dataValid : false};

  public errorMessages: string[] = [];
  private facturas: Factura[] = [];

  constructor(private companyService: CompaniesData,
              private transferService: TransferData) { }

  ngOnInit() {
    this.params = {lineaRetiro:'A',lineaDeposito:'B',filename:'',dataValid : false};
    this.facturas = [];
    this.errorMessages = [];
  }

  onFileChange(files) {
    let workBook = null;
    let jsonData = null;
    const reader = new FileReader();
    const file = files[0];
    this.facturas = [];
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
      }else {
        this.transfers = jsonData.CARGA_MASIVA;
      }
    }
    reader.readAsBinaryString(file);
  }

  calcularTotal(){
   if(this.transfers == undefined || this.transfers.length==0){
     return 0;
   }else{
    return this.transfers.map(t=>t.TOTAL).reduce((total,m)=>total+m);
   }
  }

  clean() {
    this.transfers = [];
    this.facturas = [];
    this.params.dataValid = false;
    this.params.filename = '';
    this.errorMessages = [];
    this.fileInput.nativeElement.value='';
    this.params.successMessage = undefined;
  }

  validarInformacion() {
    this.params.successMessage = undefined;
    this.params.dataValid = true;
    this.errorMessages = [];
    if(this.transfers !== undefined  && this.transfers.length > 0 ) {
      for (const transfer of this.transfers) {
        this.companyService.getCompanyByRFC(transfer.RFC_EMISOR)
        .subscribe(depositCompany => {
          if(depositCompany.tipo === this.params.lineaDeposito) {
            this.companyService.getCompanyByRFC(transfer.RFC_RECEPTOR)
                   .subscribe(withdrawalCompany => {
                    if(withdrawalCompany.tipo !== this.params.lineaRetiro){
                      this.params.dataValid = false;
                      transfer.observaciones = `${transfer.RFC_DEPOSITO} no es de tipo ${this.params.lineaDeposito}`;          
                    }else {
                      transfer.observaciones = 'VALIDO';
                      let factura = new Factura();
                      factura.rfcEmisor = transfer.RFC_EMISOR;
                      factura.razonSocialEmisor = withdrawalCompany.informacionFiscal.razonSocial;
                      factura.lineaEmisor = this.params.lineaDeposito;
                      factura.rfcRemitente = transfer.RFC_RECEPTOR;
                      factura.razonSocialRemitente = depositCompany.informacionFiscal.razonSocial;
                      factura.lineaRemitente = this.params.lineaRetiro;
                      factura.metodoPago = 'PUE';
                      factura.statusFactura = '4';
                      factura.solicitante = 'CARGA_MASIVA';
                      factura.cfdi = new Cfdi();
                      factura.cfdi.receptor.rfc =  transfer.RFC_RECEPTOR;
                      factura.cfdi.receptor.usoCfdi = transfer.USO_CFDI;
                      factura.cfdi.emisor.rfc = transfer.RFC_EMISOR;
                      factura.cfdi.emisor.regimenFiscal = depositCompany.regimenFiscal;
                      factura.cfdi.formaPago = transfer.FORMA_PAGO;
                      factura.cfdi.moneda = 'MXN';
                      factura.cfdi.total = transfer.TOTAL;
                      factura.cfdi.subtotal = transfer.IMPORTE;
                      factura.cfdi.metodoPago = 'PUE';
                      const concepto = new Concepto();
                      concepto.cantidad = transfer.CANTIDAD;
                      concepto.claveProdServ = transfer.CLAVE_PROD_SERVICIO;
                      concepto.claveUnidad = transfer.CLAVE_UNIDAD;
                      concepto.descripcion = transfer.CONCEPTO;
                      concepto.descripcionCUPS = transfer.CONCEPTO;
                      concepto.importe = transfer.IMPORTE;
                      concepto.iva = true;
                      concepto.impuestos = [new Impuesto('002', '0.160000', transfer.IMPORTE, transfer.IVA)];
                      concepto.unidad = transfer.UNIDAD;
                      concepto.valorUnitario = transfer.PRECIO_UNITARIO;
                      factura.cfdi.conceptos.push(concepto);

                      this.facturas.push(factura);
                    }
                   },error=>{transfer.observaciones = error.error.message || `${error.statusText} : ${error.message}`; this.params.dataValid = false;});
          }else{
            transfer.observaciones = `${transfer.RFC_DEPOSITO} no es de tipo ${this.params.lineaDeposito}`;
            this.params.dataValid = false;
          }
        },error=>{ transfer.observaciones = error.error.message || `${error.statusText} : ${error.message}`; this.params.dataValid = false;});
      }
    }else{
      alert('Formato invalido o sin informacion cargada')
    }
  }

  cargarTransferencias(){
    this.params.successMessage = undefined
    this.errorMessages = [];
    if ( this.facturas.length > 0) {
      console.log('loading facturas :', this.facturas)
      //this.transferService.saveAllTransfers(this.facturas).subscribe(data=>{this.params.successMessage = `Se han cargado ${data.length} transferencias exitosamente`;this.transfers=[]},
      //(error: HttpErrorResponse) => this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`))
    }
  }

}
