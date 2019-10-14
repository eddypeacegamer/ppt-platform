import { Observable } from 'rxjs';
import { Empresa } from '../../models/empresa';
import { GenericPage } from '../../models/generic-page';

export abstract class CompaniesData {

    abstract getCompanies(page:number,size:number,filterParams?:any) : Observable<GenericPage<Empresa>>;

    abstract getCompaniesByLineaAndGiro(linea:string, giro: number) : Observable<Empresa[]>;
    
    abstract getCompanyByRFC(rfc:string) : Observable<Empresa>;

    abstract insertNewCompany(empresa : Empresa) : Observable<Empresa>;

    abstract updateCompany(empresa : Empresa) : Observable<Empresa>;
  
}