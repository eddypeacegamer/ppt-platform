import { Cuenta } from '../../models/cuenta';
import { GenericPage } from '../../models/generic-page';
import { Observable } from 'rxjs';

export abstract class CuentasData {
    abstract getAllCuentas(page: number, size: number, filterParams?: any): Observable<GenericPage<Cuenta>>;
    abstract getCuentasByCompany(companyRfc: string): Observable<Cuenta[]>;
}
