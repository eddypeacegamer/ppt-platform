import { Component, OnInit } from '@angular/core';

import { CatalogsData } from '../../../@core/data/catalogs-data';
import { GenericPage } from '../../../models/generic-page';
import {DownloadCsvService } from '../../../@core/back-services/download-csv.service'

@Component({
  selector: 'ngx-empresas',
  templateUrl: './empresas.component.html',
  styleUrls: ['./empresas.component.scss']
})
export class EmpresasComponent implements OnInit {

  public headers: string[] = ['Clave', 'Descripcion', 'Similares', 'Inicio Vigencia'];
  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';

  constructor(private catalogService: CatalogsData,
    private donwloadService:DownloadCsvService) {}

    ngOnInit() {
      this.updateDataTable();
    }
  
    public updateDataTable(currentPage?: number, pageSize?: number) {
      const pageValue = currentPage || 0;
      const sizeValue = pageSize || 10;
      this.catalogService.getAllClavesProductoServicio(pageValue, sizeValue)
        .subscribe(result => this.page = result);
    }

}
