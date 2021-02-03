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
import { map } from 'rxjs/operators';
import { DownloadCsvService } from '../../../@core/util-services/download-csv.service';
@Component({
  selector: 'ngx-cuentas-bancarias',
  templateUrl: './cuentas-bancarias.component.html',
  styleUrls: ['./cuentas-bancarias.component.scss']
})
export class CuentasBancariasComponent implements OnInit {

  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';
  public currentPage = '0';
  public filterParams: any = { banco: '', empresa: '', cuenta: '',clabe:'', page: '', size: '10', empresarazon:'' };

  public module: string = 'tesoreria';
  public girosCat: Catalogo[] = [];
  public companiesCat: Empresa[] = [];
  public banksCat: Catalogo[] = [];

  public companyInfo: Empresa;

  listEmpresasMatch: any[];
  empresasRfc: any[];
  empresasRazonSocial: any[];
  
  public errorMessages: string[] = [];
  constructor(
    private router: Router,
    private utilsService: UtilsService,
    private route: ActivatedRoute,
    private accountsService: CuentasData,
    private companiesService: CompaniesData,
    private catalogsService: CatalogsData,
    private donwloadService: DownloadCsvService,
    ) {
    
   }

  ngOnInit() {
    this.errorMessages = [];
    this.module = this.router.url.split('/')[2];
    this.route.queryParams
      .subscribe(params => {
        if (!this.utilsService.compareParams(params, this.filterParams)) {
          this.filterParams = { ...this.filterParams, ...params };
          this.catalogsService.getAllGiros().then(cat => this.girosCat = cat);
          this.catalogsService.getBancos().then(banks => this.banksCat = banks);

          if(params.empresa){    
            this.companiesService.getCompanyByRFC(params.empresa) .subscribe((company: Empresa) => { this.filterParams.empresarazon = company.informacionFiscal.razonSocial;});      
          }
      
          this.getEmpresas();
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

  public downloadHandler() {
    const params: any = {};
    /* Parsing logic */
    for (const key in this.filterParams) {
      if (this.filterParams[key] !== undefined) {
        const value: string = this.filterParams[key];
        if (value !== null && value.length > 0) {
          params[key] = value;
        }
      }
    }
    params.page = 0;
    params.size = 10000;
    this.accountsService.getAllCuentas(params.page,params.size,params).subscribe(result => {
      this.donwloadService.exportCsv(result.content, 'Cuentas-Bancarias')
    });
  }
  public onChangePageSize(pageSize: number) {
    this.updateDataTable(this.page.number, pageSize);
  }


  //Giros


  getSelector($event) {
    let inputValue = (<HTMLInputElement>document.getElementById('empresasId')).value;
    this.listEmpresasMatch = [];
    this.errorMessages = [];
    if (inputValue.length > 1) {
        this.listEmpresasMatch = this.BuscarMatch(this.empresasRazonSocial, inputValue);
        let index = this.empresasRazonSocial.indexOf(this.listEmpresasMatch[0]);
        this.filterParams.empresa = this.empresasRfc[index];

        if(index == -1)
           this.errorMessages.push("No hay empresas registradas con esa razon social.");
    }
  
  }  


  getEmpresas(){
    this.companiesService.getCompanies({ page: 0, size: 10000})
      .pipe(map((Page: GenericPage<Empresa>) => Page.content))   
      .subscribe(companies => {        
            this.empresasRfc = companies.map(c => c.informacionFiscal.rfc);   
            this.empresasRazonSocial = companies.map(c => c.informacionFiscal.razonSocial).map(function(x){ return x.toUpperCase(); });         
      },
        (error: HttpErrorResponse) =>
          this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
      
  }

  BuscarMatch(arr, regex) {
    let matches = [], i;
    regex = regex.toUpperCase();
    for (i = 0; i < arr.length; i++) {
      if (arr[i].match(regex)) {
        matches.push(arr[i]);
      }
    }
    return matches;
  };

}
