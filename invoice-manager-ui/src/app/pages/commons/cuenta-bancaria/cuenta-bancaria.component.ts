import { Component, OnInit } from '@angular/core';
import { Cuenta } from '../../../models/cuenta';
import { Router, ActivatedRoute } from '@angular/router';
import { UtilsService } from '../../../@core/util-services/utils.service';
import { CuentasData } from '../../../@core/data/cuentas-data';
import { Empresa } from '../../../models/empresa';
import { HttpErrorResponse } from '@angular/common/http';
import { UsersData } from '../../../@core/data/users-data';
import { Catalogo } from '../../../models/catalogos/catalogo';
import { CompaniesData } from '../../../@core/data/companies-data';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { map } from 'rxjs/operators';
import { GenericPage } from '../../../models/generic-page';

@Component({
  selector: 'ngx-cuenta-bancaria',
  templateUrl: './cuenta-bancaria.component.html',
  styleUrls: ['./cuenta-bancaria.component.scss']
})
export class CuentaBancariaComponent implements OnInit {

  public cuenta: Cuenta;
  public page: GenericPage<any> = new GenericPage();
  public girosCat: Catalogo[] = [];
  public emisoresCat: Empresa[] = [];
  public banksCat: Catalogo[] = [];

  public filterParams: any = { banco: '', empresa: '', cuenta: '',clave:'', empresarazon:''};
  public Params: any = { success: '', message: ''};


  public module: string = 'tesoreria';

  public errorMessages: string[] = [];
  public loading = true;
  public clear = false;
  

  lastkeydown1: number = 0;
  listEmpresasMatch: any[];
  empresasRfc: any[];
  empresasRazonSocial: any[];

  constructor(
    private router: Router,
    private utilsService: UtilsService,
    private userService: UsersData,
    private route: ActivatedRoute,
    private accountsService: CuentasData,
    private companiesService: CompaniesData,
    private catalogsService: CatalogsData,
  ) { }

  ngOnInit() {
    this.cuenta = new Cuenta();
    this.loading = true;
    this.errorMessages = [];
    this.getEmpresas();
      this.route.paramMap.subscribe(route => {
        let empresa = route.get('empresa');
        let cuenta = route.get('cuenta');

        if (empresa !== '*') {

            this.companiesService.getCompanyByRFC(empresa) .subscribe((company: Empresa) => { this.filterParams.empresarazon = company.informacionFiscal.razonSocial;});      
            this.updatecuentaInfo(empresa,cuenta);
  
        }
        else{
          this.catalogsService.getAllGiros().then(cat => this.girosCat = cat);
          this.catalogsService.getBancos().then(banks => this.banksCat = banks);
          this.loading = false;
        }
    });
  }

  public update(){
    this.accountsService.updateCuenta(this.cuenta).subscribe(
      createdUser => {
        this.Params.success = 'Se actualizado la cuenta satisfactoriamente.';
      }, (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
        || `${error.statusText} : ${error.message}`));
    
  }

  public updatecuentaInfo(empresa: string,cuenta:string) {
  
    this.errorMessages = [];
    this.accountsService.getCuentaInfo(empresa,cuenta).subscribe(
      cuentadata => {
        this.cuenta = cuentadata;
        this.catalogsService.getAllGiros().then(cat => this.girosCat = cat);
        this.catalogsService.getBancos().then(banks => this.banksCat = banks);
      
        this.loading = false;
      },
      (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
        || `${error.statusText} : ${error.message}`));
  }

  public registry() {
    this.errorMessages = [];
      this.accountsService.insertCuenta(this.cuenta).subscribe( createdCuenta => {
          this.Params.success = 'La cuenta ha sido creada satisfactoriamente.';
        }, (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
          || `${error.statusText} : ${error.message}`));
  }

  public delete() {
   
    this.accountsService.deleteCuenta(this.cuenta)
      .subscribe((data: any) => {this.Params.success = 'La cuenta fue borrada exitosamente'
      this.clear = true;
    },
        (error: HttpErrorResponse) => { this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`); });
  }


  //giros

  getSelector($event) {
    let inputValue = (<HTMLInputElement>document.getElementById('empresasId')).value;
    this.listEmpresasMatch = [];

    if (inputValue.length > 2) {
      if ($event.timeStamp - this.lastkeydown1 > 200) {
        this.listEmpresasMatch = this.BuscarMatch(this.empresasRazonSocial, inputValue);
        
      }
    }
   this.cuenta.empresa = this.empresasRfc[this.empresasRazonSocial.indexOf(this.listEmpresasMatch[0])];
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
