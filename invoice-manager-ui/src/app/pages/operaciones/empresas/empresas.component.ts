import { Component, OnInit } from '@angular/core';

import { CompaniesData } from '../../../@core/data/companies-data';
import { GenericPage } from '../../../models/generic-page';
import {DownloadCsvService } from '../../../@core/back-services/download-csv.service'
import { Router } from '@angular/router';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { Giro } from '../../../models/catalogos/giro';
import { Observable } from 'rxjs';
import { Empresa } from '../../../models/empresa';
import { map } from 'rxjs/operators';

@Component({
  selector: 'ngx-empresas',
  templateUrl: './empresas.component.html',
  styleUrls: ['./empresas.component.scss']
})
export class EmpresasComponent implements OnInit {

  public girosCat : Giro[];
  public headers: string[] = ['RFC', 'Razon Social', 'Giro', 'Tipo', 'Activa', 'Correo', 'Fecha Creacion', 'Fecha Actualizacion'];
  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';
  public filterParams : any = {razonSocial:'',rfc:'',linea:''};

  constructor(private router: Router,
    private companyService: CompaniesData,
    private catalogsService : CatalogsData,
    private donwloadService:DownloadCsvService) {}

    ngOnInit() {
      this.catalogsService.getAllGiros().toPromise()
        .then(cat=>this.girosCat = cat)
        .then(()=>this.updateDataTable())
    }
  
    public getCompanyInfo(pageValue, sizeValue,filterParams):Observable<GenericPage<any>>{
      return this.companyService.getCompanies(pageValue, sizeValue,filterParams).pipe(
        map((page:GenericPage<Empresa>)=>{
          let records:Empresa[] = page.content.map(r=>{
            let record : any = {};
            record.rfc = r.informacionFiscal.rfc;
            record.razonSocial = r.informacionFiscal.razonSocial;
            record.direccion = `${r.informacionFiscal.calle} ${r.informacionFiscal.noExterior}, ${r.informacionFiscal.localidad} ${r.informacionFiscal.municipio} ${r.informacionFiscal.estado}, C.P. ${r.informacionFiscal.cp}`;
            record.giro = this.girosCat.find(g=>g.clave==Number(r.giro)).nombre;
            record.tipo = r.tipo;
            record.correo = r.correo;
            record.activo = (r.activo)?'SI':'NO';
            record.fechaCreacion = r.fechaCreacion;
            record.fechaActualizacion = r.fechaActualizacion;
            return record;
          });
          page.content = records;
          return page;
        })
      );
    }


    public updateDataTable(currentPage?: number, pageSize?: number, filterParams?:any) {
      const pageValue = currentPage || 0;
      const sizeValue = pageSize || 10;
      this.getCompanyInfo(pageValue, sizeValue,filterParams).subscribe(result => this.page = result);
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
      this.getCompanyInfo(0, 10000, this.filterParams).subscribe(result => {
        this.donwloadService.exportCsv(result.content,'Empresas')
      });
    }

    public redirectToEmpresa(rfc:string){
      this.router.navigate([`./pages/operaciones/empresa/${rfc}`])
    }
}
