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

@Component({
  selector: 'ngx-cuenta-bancaria',
  templateUrl: './cuenta-bancaria.component.html',
  styleUrls: ['./cuenta-bancaria.component.scss']
})
export class CuentaBancariaComponent implements OnInit {

  public cuenta: Cuenta;

  public girosCat: Catalogo[] = [];
  public emisoresCat: Empresa[] = [];
  public banksCat: Catalogo[] = [];

  public filterParams: any = { banco: '', empresa: '', cuenta: '',clave:''};
  public Params: any = { success: '', message: ''};
  public formInfo = { giro: '*', linea: 'B' };

  public module: string = 'tesoreria';

  public errorMessages: string[] = [];
  public loading = true;
  public clear = false;
  

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
   
      this.route.paramMap.subscribe(route => {
        let empresa = route.get('empresa');
        let cuenta = route.get('cuenta');

        if (empresa !== '*') {
          this.userService.getUserInfo().then((user) => {
           
        /*     this.filterParams = { ...this.filterParams, ...params };
            this.filterParams.solicitante = user.email; */
           
            this.updatecuentaInfo(empresa,cuenta);
          });
         // this.updatecuentaInfo(empresa,cuenta);
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
