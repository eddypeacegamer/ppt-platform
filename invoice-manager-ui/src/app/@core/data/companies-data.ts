import { Observable } from 'rxjs';
import { Empresa } from '../../models/empresa';
import { GenericPage } from '../../models/generic-page';

export abstract class CompaniesData {

    abstract getCompanies(filterParams?: any): Observable<GenericPage<Empresa>>;

    abstract getCompaniesByLineaAndGiro(linea: string, giro: number): Observable<Empresa[]>;

    abstract getCompanyByRFC(rfc: string): Observable<Empresa>;

    abstract insertNewCompany(empresa: Empresa): Observable<Empresa>;

    abstract updateCompany(rfc: string, empresa: Empresa): Observable<Empresa>;
}