import { Component, OnInit } from '@angular/core';
import { Empresa } from '../../../models/empresa';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { CompaniesData } from '../../../@core/data/companies-data';
import { ZipCodeInfo } from '../../../models/zip-code-info';
import { HttpErrorResponse } from '@angular/common/http';
import { Giro } from '../../../models/catalogos/giro';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'ngx-empresa',
  templateUrl: './empresa.component.html',
  styleUrls: ['./empresa.component.scss']
})
export class EmpresaComponent implements OnInit {

  public companyInfo : Empresa;
  public formInfo : any = {rfc:'',message:'',coloniaId:'*', success:''};
  public coloniaId: number=0;
  public colonias = [];
  public paises = ['México'];

  public girosCat: Giro[] = [];
  public errorMessages: string[] = [];
  
  constructor(private catalogsService:CatalogsData,private empresaService:CompaniesData ,private route: ActivatedRoute) { }

  ngOnInit() {
    this.companyInfo = new Empresa();
    this.companyInfo.regimenFiscal = '*';
    this.companyInfo.giro = '*';
    this.companyInfo.tipo = '*';
    this.companyInfo.activo = '*';
      /** recovering folio info**/
      this.route.paramMap.subscribe(route => {
        let rfc = route.get('rfc');
        this.empresaService.getCompanyByRFC(rfc)
        .subscribe((data:Empresa) => {this.companyInfo = data, this.formInfo.rfc = rfc;},
        (error : HttpErrorResponse)=>this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));  
        });
    
    /**** LOADING CAT INFO ****/
    this.catalogsService.getAllGiros().subscribe((giros: Giro[]) => this.girosCat = giros,
      (error: HttpErrorResponse) => this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
  }

  public zipCodeInfo(zipcode:String){
    let zc = new String(zipcode);
    if(zc.length>4 && zc.length <6){
      this.colonias = [];
      this.catalogsService.getZipCodeInfo(zipcode).subscribe(
          (data:ZipCodeInfo) => {this.companyInfo.informacionFiscal.estado = data.estado;
          this.companyInfo.informacionFiscal.municipio= data.municipio;this.colonias=data.colonias; 
          this.companyInfo.informacionFiscal.localidad=data.colonias[0];},
          (error: HttpErrorResponse) => console.error(error));
    }
  }

  public onLocation(index:string){
    this.companyInfo.informacionFiscal.localidad = this.colonias[index];
  }

  public onRegimenFiscalSelected(regimen:string){
    console.log(regimen);
  }

  public onGiroSelection(giro:string){
    console.log(giro);
  }

  public onCompanySelected(company:string){
    console.log(company);
  }

}
