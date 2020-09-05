import { Component, OnInit } from '@angular/core';
import { Cuenta } from '../../../models/cuenta';
import { ActivatedRoute } from '@angular/router';
import { CuentasData } from '../../../@core/data/cuentas-data';
import { Empresa } from '../../../models/empresa';
import { HttpErrorResponse } from '@angular/common/http';
import { Catalogo } from '../../../models/catalogos/catalogo';
import { CompaniesData } from '../../../@core/data/companies-data';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { map } from 'rxjs/operators';
import { GenericPage } from '../../../models/generic-page';
import { Contribuyente } from '../../../models/contribuyente';

@Component({
  selector: 'ngx-cuenta-bancaria',
  templateUrl: './cuenta-bancaria.component.html',
  styleUrls: ['./cuenta-bancaria.component.scss']
})
export class CuentaBancariaComponent implements OnInit {

  public cuenta: Cuenta;
  public page: GenericPage<any> = new GenericPage();
  public girosCat: Catalogo[] = [];
  public empresas: Contribuyente[] = [];
  public banksCat: Catalogo[] = [];

  public filterParams: any = { banco: '', empresa: '', cuenta: '', clabe:'', empresarazon:''};
  public Params: any = { success: '', message: ''};

  public formInfo = { giro: '*', linea: 'A'};


  public module: string = 'tesoreria';

  public errorMessages: string[] = [];
  public loading = true;
  public clear = false;

  lastkeydown1: number = 0;
  listEmpresasMatch: any[];
  empresasRfc: any[];
  empresasRazonSocial: any[];

  constructor(
    private route: ActivatedRoute,
    private accountsService: CuentasData,
    private companiesService: CompaniesData,
    private catalogsService: CatalogsData,
  ) { }

  ngOnInit() {
    this.cuenta = new Cuenta();
    this.loading = true;
    this.errorMessages = [];
      this.route.paramMap.subscribe(route => {
        const empresa = route.get('empresa');
        const cuenta = route.get('cuenta');
        if (empresa !== '*') {
            this.companiesService.getCompanyByRFC(empresa)
              .subscribe((company: Empresa) => {
                this.filterParams.empresarazon = company.informacionFiscal.razonSocial;
              });
              this.catalogsService.getBancos().then(banks => this.banksCat = banks);
            this.updatecuentaInfo(empresa, cuenta);
        } else {
          this.catalogsService.getAllGiros().then(cat => this.girosCat = cat);
          this.catalogsService.getBancos().then(banks => this.banksCat = banks);
          this.loading = false;
        }
    });
  }

  public update() {
    this.accountsService.updateCuenta(this.cuenta).subscribe(
      createdUser => {
        this.Params.success = 'Se actualizado la cuenta satisfactoriamente.';
      }, (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
        || `${error.statusText} : ${error.message}`));
  }

  public updatecuentaInfo(empresa: string, cuenta: string) {
    this.errorMessages = [];
    this.accountsService.getCuentaInfo(empresa, cuenta).subscribe(
      cuentadata => {
        this.cuenta = cuentadata;
        this.loading = false;
      }, (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
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

  onGiroSelection(giroId: string) {
    const value = +giroId;
    this.formInfo.giro = giroId;
    if (isNaN(value)) {
      this.empresas = [];
    } else {
      this.companiesService.getCompaniesByLineaAndGiro(this.formInfo.linea, Number(giroId))
        .pipe(map((empresas: Empresa[]) => empresas.map(e => e.informacionFiscal)))
        .subscribe(companies => this.empresas = companies,
          (error: HttpErrorResponse) =>
            this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
    }
  }
  onLineaSelection() {
  
  this.empresas = [];
  if (this.formInfo.giro === '*') {
    this.empresas = [];
  } else {
    
      this.companiesService.getCompaniesByLineaAndGiro(this.formInfo.linea, Number(this.formInfo.giro))
        .pipe(map((empresas: Empresa[]) => empresas.map(e => e.informacionFiscal)))
        .subscribe(companies => this.empresas = companies,
          (error: HttpErrorResponse) =>
            this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
        }
  }

  onEmpresaSelected(rfc: string) {
    this.cuenta.empresa = rfc;
  }
}
