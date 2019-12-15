import { Component, OnInit, ViewChild, ElementRef} from '@angular/core';
import * as XLSX from 'xlsx';
import { CompaniesData } from '../../../@core/data/companies-data';

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

  
  constructor(private companyService:CompaniesData) { }

  ngOnInit() {
    this.params = {lineaRetiro:'A',lineaDeposito:'B',filename:'',dataValid : false};
  }

  onFileChange(files) {
    let workBook = null;
    let jsonData = null;
    const reader = new FileReader();
    const file = files[0];
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

  clean(){
    this.transfers = [];
    this.params.dataValid = false;
    this.params.filename = '';
    this.fileInput.nativeElement.value='';
  }

  validarInformacion(){
    this.params.dataValid = true;
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

}
