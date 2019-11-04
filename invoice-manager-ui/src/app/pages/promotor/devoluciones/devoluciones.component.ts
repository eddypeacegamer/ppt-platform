import { Component, OnInit } from '@angular/core';
import { GenericPage } from '../../../models/generic-page';
import { DevolutionData } from '../../../@core/data/devolution-data';
import { Devolucion } from '../../../models/devolucion';
import { DownloadCsvService } from '../../../@core/back-services/download-csv.service';
import { Router } from '@angular/router';

@Component({
  selector: 'ngx-devoluciones',
  templateUrl: './devoluciones.component.html',
  styleUrls: ['./devoluciones.component.scss']
})
export class DevolucionesComponent implements OnInit {

  public headers: string[] = ['Folio', 'Tipo Receptor','Receptor', 'Estatus Pago','Pago', 'Monto'];
  public page: GenericPage<Devolucion> = new GenericPage();
  public pageSize = '10';
  public filterParams : any;

  constructor(private devolutionService:DevolutionData,
              private donwloadService:DownloadCsvService,
              private router: Router) { }

  ngOnInit() {
    this.filterParams= {tipoReceptor:'PROMOTOR',idReceptor:'promotor@gmail.com',statusPago:'*'};
    this.updateDataTable(0,10,this.filterParams);
  }

  public onReceptorTypeSelected(type:string){
    if(type=='PROMOTOR'){ this.filterParams.idReceptor ='promotor@gmail.com' }
    else{ this.filterParams.idReceptor = ''}
    this.filterParams.tipoReceptor = type;
  }

  public onDevolutionStatusSelected(status:string){
    this.filterParams.statusPago= status;
  }

  public updateDataTable(currentPage?: number, pageSize?: number,filterParams?:any) {
    const pageValue = currentPage || 0;
    const sizeValue = pageSize || 10;
    this.devolutionService.getDevolutions(pageValue,sizeValue,filterParams)
          .subscribe((result:GenericPage<Devolucion>) => this.page = result);
  }

  public downloadHandler() {
    this.devolutionService.getDevolutions(0, 10000, this.filterParams).subscribe(result => {
      this.donwloadService.exportCsv(result.content,'Devoluciones')
    });
  }

  public redirectToCfdi(folio:string){
    this.router.navigate([`./pages/promotor/precfdi/${folio}`])
  }

}
