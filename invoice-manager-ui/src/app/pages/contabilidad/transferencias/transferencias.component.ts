import { Component, OnInit, ViewChild, ElementRef} from '@angular/core';
import * as XLSX from 'xlsx';
import { CompaniesData } from '../../../@core/data/companies-data';
import { TransferData } from '../../../@core/data/transfers-data';
import { Transferencia } from '../../../models/transferencia';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'ngx-transferencias',
  templateUrl: './transferencias.component.html',
  styleUrls: ['./transferencias.component.scss']
})
export class TransferenciasComponent implements OnInit {


  @ViewChild('fileInput',{static:true})
  public fileInput: ElementRef;
  public transfers : any[] = [];
  public params : any ={lineaRetiro:'A',lineaDeposito:'B',filename:'',dataValid : false};

  public errorMessages : string[] = [];
  private transferencias : Transferencia[] = [];

  constructor(private companyService:CompaniesData,
              private transferService: TransferData) { }

  ngOnInit() {
    this.params = {lineaRetiro:'A',lineaDeposito:'B',filename:'',dataValid : false};
    this.transferencias = [];
    this.errorMessages = [];
  }

  onFileChange(files) {
    let workBook = null;
    let jsonData = null;
    const reader = new FileReader();
    const file = files[0];
    this.transferencias = [];
    this.params.filename = file.name;
    reader.onload = (event) => {
      const data = reader.result;
      workBook = XLSX.read(data, { type: 'binary' });
      jsonData = workBook.SheetNames.reduce((initial, name) => {
        const sheet = workBook.Sheets[name];
        initial[name] = XLSX.utils.sheet_to_json(sheet);
        return initial;
      }, {});
      if(jsonData.TRANSFERENCIAS==undefined){
        console.log(jsonData)
        alert('Formato Excel invalido')
      }else{
        this.transfers = jsonData.TRANSFERENCIAS;
      }
    }
    reader.readAsBinaryString(file);
  }

  calcularTotal(){
   if(this.transfers == undefined || this.transfers.length==0){
     return 0;
   }else{
    return this.transfers.map(t=>t.MONTO).reduce((total,m)=>total+m);
   }
  }

  clean() {
    this.transfers = [];
    this.transferencias = [];
    this.params.dataValid = false;
    this.params.filename = '';
    this.errorMessages = [];
    this.fileInput.nativeElement.value='';
    this.params.successMessage = undefined;
  }

  validarInformacion(){
    this.params.successMessage = undefined;
    this.params.dataValid = true;
    this.errorMessages = [];
    if(this.transfers!=undefined  && this.transfers.length>0){
      for (const transfer of this.transfers) {
        this.companyService.getCompanyByRFC(transfer.RFC_DEPOSITO)
        .subscribe(depositCompany =>{
          if(depositCompany.tipo === this.params.lineaDeposito){
            this.companyService.getCompanyByRFC(transfer.RFC_RETIRO)
                   .subscribe(withdrawalCompany =>{
                    if(withdrawalCompany.tipo != this.params.lineaRetiro){
                      this.params.dataValid = false;
                      transfer.observaciones = `${transfer.RFC_DEPOSITO} no es de tipo ${this.params.lineaDeposito}`;          
                    }else{
                      transfer.observaciones='VALIDO';
                      let transferencia = new Transferencia();
                      transferencia.importe =  transfer.MONTO;
                      transferencia.bancoRetiro = transfer.BANCO_RETIRO;
                      transferencia.rfcRetiro = transfer.RFC_RETIRO;
                      transferencia.cuentaRetiro = transfer.CUENTA_RETIRO;
                      transferencia.lineaRetiro = this.params.lineaRetiro;
                      transferencia.bancoDeposito = transfer.BANCO_DEPOSITO;
                      transferencia.rfcDeposito = transfer.RFC_DEPOSITO;
                      transferencia.cuentaDeposito = transfer.CUENTA_DEPOSITO;
                      transferencia.lineaDeposito = this.params.lineaDeposito;
                      this.transferencias.push(transferencia);
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
    if(this.transferencias.length>0){
      this.transferService.saveAllTransfers(this.transferencias).subscribe(data=>{this.params.successMessage = `Se han cargado ${data.length} transferencias exitosamente`;this.transfers=[]},
      (error: HttpErrorResponse) => this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`))
    }
  }

}
