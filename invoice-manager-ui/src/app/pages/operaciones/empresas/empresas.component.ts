import { Component, OnInit } from '@angular/core';

import { CompaniesData } from '../../../@core/data/companies-data';
import { GenericPage } from '../../../models/generic-page';
import {DownloadCsvService } from '../../../@core/back-services/download-csv.service'
import { Router } from '@angular/router';

@Component({
  selector: 'ngx-empresas',
  templateUrl: './empresas.component.html',
  styleUrls: ['./empresas.component.scss']
})
export class EmpresasComponent implements OnInit {

  public headers: string[] = ['RFC', 'Razon Social', 'Nombre', 'Tipo', 'Activa', 'Correo', 'Fecha Creacion', 'Fecha Actualizacion'];
  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';
  public filterParams : any = {razonSocial:'',rfc:'',linea:''};

  constructor(private router: Router,
    private companyService: CompaniesData,
    private donwloadService:DownloadCsvService) {}

    ngOnInit() {
      this.updateDataTable();
    }
  
    public updateDataTable(currentPage?: number, pageSize?: number, filterParams?:any) {
      const pageValue = currentPage || 0;
      const sizeValue = pageSize || 10;
      this.companyService.getCompanies(pageValue, sizeValue,filterParams)
        .subscribe(result => this.page = result);
    }


    public onChangePageSize(pageSize: number) {
      this.updateDataTable(this.page.number, pageSize);
    }
  
    public onCompanySelected(tipo:string){
      if(tipo === '*' ){
        this.filterParams.linea = '';
      }else{
        this.filterParams.linea = tipo;
      }
    }

    public newCompany(){
      this.router.navigate([`./pages/operaciones/empresa/*`])
    }
  
    public downloadHandler() {
      this.companyService.getCompanies(0, 10000, this.filterParams).subscribe(result => {
        this.donwloadService.exportCsv(result.content.map(r=>r.informacionFiscal),'Empresas')
      });
    }

    public redirectToEmpresa(rfc:string){
      this.router.navigate([`./pages/operaciones/empresa/${rfc}`])
    }
}
