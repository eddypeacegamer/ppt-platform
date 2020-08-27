import { Component, OnInit } from '@angular/core';
import { GenericPage } from '../../../models/generic-page';
import { Router, ActivatedRoute } from '@angular/router';
import { UtilsService } from '../../../@core/util-services/utils.service';
import { CuentasData } from '../../../@core/data/cuentas-data';
import { Catalogo } from '../../../models/catalogos/catalogo';
import { Empresa } from '../../../models/empresa';
import { CompaniesData } from '../../../@core/data/companies-data';
import { HttpErrorResponse } from '@angular/common/http';
import { CatalogsData } from '../../../@core/data/catalogs-data';

@Component({
  selector: 'ngx-cuentas-bancarias',
  templateUrl: './cuentas-bancarias.component.html',
  styleUrls: ['./cuentas-bancarias.component.scss']
})
export class CuentasBancariasComponent implements OnInit {

  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';
  public currentPage = '0';
  public filterParams: any = { banco: '', empresa: '', cuenta: '',clave:'', page: '0', size: '10' };
  public formInfo = { giro: '*', linea: 'B' };

  public module: string = 'tesoreria';
  public girosCat: Catalogo[] = [];
  public emisoresCat: Empresa[] = [];
  public banksCat: Catalogo[] = [];

  public companyInfo: Empresa;
  
  public errorMessages: string[] = [];
  constructor(
    private router: Router,
    private utilsService: UtilsService,
    private route: ActivatedRoute,
    private accountsService: CuentasData,
    private companiesService: CompaniesData,
    private catalogsService: CatalogsData,
    ) {
    
   }

  ngOnInit() {

    this.module = this.router.url.split('/')[2];
    this.route.queryParams
      .subscribe(params => {

        if (!this.utilsService.compareParams(params, this.filterParams)) {
          this.filterParams = { ...this.filterParams, ...params };
          this.catalogsService.getAllGiros().then(cat => this.girosCat = cat);
          this.catalogsService.getBancos().then(banks => this.banksCat = banks);
        this.updateDataTable();
        }
      });
  }

  public updateDataTable(currentPage?: number, pageSize?: number) {
   
    const params: any = this.utilsService.parseFilterParms(this.filterParams);

    params.page = currentPage !== undefined ? currentPage : this.filterParams.page;
    params.size = pageSize !== undefined ? pageSize : this.filterParams.size;

    this.router.navigate([`./pages/tesoreria/cuentas-bancarias`],
    { queryParams: params });

  this.accountsService.getAllCuentas(params.page,params.size,params).subscribe((result: GenericPage<any>) => this.page = result);
  }

  public redirectToEmpresa(empresa: string,cuenta:string) {
    this.router.navigate([`./pages/tesoreria/cuenta-bancaria/${empresa}/${cuenta}`])
  }
  public redirectToEmpresaRegistry() {
    this.router.navigate([`./pages/tesoreria/cuenta-bancaria/*/*`])
  }

  //Giros

  public valueGeneral: number;
  onGiroSelection(giroId: string) {
    const value = +giroId;
    this.valueGeneral = value;
    if (isNaN(value)) {
      this.emisoresCat = [];
    } else {
      this.companiesService.getCompaniesByLineaAndGiro(this.formInfo.linea, Number(giroId))
        .subscribe(companies => this.emisoresCat = companies,
          (error: HttpErrorResponse) =>
            this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
    }
    
  }

  onLineaSelect() {
    
    if (isNaN(this.valueGeneral)) {
      this.emisoresCat = [];
    } else {
      this.companiesService.getCompaniesByLineaAndGiro(this.formInfo.linea,this.valueGeneral)
        .subscribe(companies => this.emisoresCat = companies,
          (error: HttpErrorResponse) =>
            this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
    }
  }


}
