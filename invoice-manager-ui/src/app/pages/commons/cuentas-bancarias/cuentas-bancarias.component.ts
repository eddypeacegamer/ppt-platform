import { Component, OnInit } from '@angular/core';
import { GenericPage } from '../../../models/generic-page';
import { Router, ActivatedRoute } from '@angular/router';
import { UtilsService } from '../../../@core/util-services/utils.service';
import { CuentasData } from '../../../@core/data/cuentas-data';

@Component({
  selector: 'ngx-cuentas-bancarias',
  templateUrl: './cuentas-bancarias.component.html',
  styleUrls: ['./cuentas-bancarias.component.scss']
})
export class CuentasBancariasComponent implements OnInit {

  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';
  public filterParams: any = { banco: '', empresa: '', cuenta: '',clave:'', page: '0', size: '10' };
  public module: string = 'tesoreria';
 
  constructor(
    private router: Router,
    private utilsService: UtilsService,
    private route: ActivatedRoute,
    private accountsService: CuentasData
    ) {
    
   }

  ngOnInit() {

    this.module = this.router.url.split('/')[2];
    this.route.queryParams
      .subscribe(params => {

        if (!this.utilsService.compareParams(params, this.filterParams)) {
          this.filterParams = { ...this.filterParams, ...params };

        this.updateDataTable();
        }
      });
  }

  public updateDataTable(currentPage?: number, pageSize?: number) {
    console.log("aaaa");
    const params: any = this.utilsService.parseFilterParms(this.filterParams);

    params.page = currentPage !== undefined ? currentPage : this.filterParams.page;
    params.size = pageSize !== undefined ? pageSize : this.filterParams.size;

    this.router.navigate([`./pages/tesoreria/cuentas-bancarias`],
    { queryParams: params });
 /*    switch (this.module) {
      case 'operaciones':
        this.router.navigate([`./pages/operaciones/empresas`],
          { queryParams: params });
        break;
      case 'contabilidad':
        this.router.navigate([`./pages/contabilidad/empresas`],
          { queryParams: params });
        break;
      default:
        this.router.navigate([`./pages/operaciones/empresas`],
          { queryParams: params });
    } */

  this.accountsService.getAllCuentas(params.page,params.size,params).subscribe((result: GenericPage<any>) => this.page = result);
  }

  public redirectToEmpresa(empresa: string,cuenta:string) {
    this.router.navigate([`./pages/tesoreria/cuenta-bancaria/${empresa}/${cuenta}`])
  }
  public redirectToEmpresaRegistry() {
    this.router.navigate([`./pages/tesoreria/cuenta-bancaria/*/*`])
  }

}
