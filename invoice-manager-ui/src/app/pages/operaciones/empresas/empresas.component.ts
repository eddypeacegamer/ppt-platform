import { Component, OnInit } from '@angular/core';

import { CompaniesData } from '../../../@core/data/companies-data';
import { GenericPage } from '../../../models/generic-page';
import {DownloadCsvService } from '../../../@core/back-services/download-csv.service'

@Component({
  selector: 'ngx-empresas',
  templateUrl: './empresas.component.html',
  styleUrls: ['./empresas.component.scss']
})
export class EmpresasComponent implements OnInit {

  public headers: string[] = ['RFC', 'Razon Social', 'Nombre', 'Tipo', 'Activa', 'Correo', 'Fecha Creacion', 'Fecha Actualizacion'];
  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';
  public filterParams : any = {razonSocial:'',rfc:''};

  constructor(private companyService: CompaniesData,
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
  
  
    public downloadHandler() {
      this.companyService.getCompanies(0, 10000, this.filterParams).subscribe(result => {
        this.donwloadService.exportCsv(result.content,'Empresas')
      });
    }
}
