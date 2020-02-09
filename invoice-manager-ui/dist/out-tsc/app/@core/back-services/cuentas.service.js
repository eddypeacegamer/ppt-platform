import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
let CuentasService = class CuentasService {
    constructor(httpClient) {
        this.httpClient = httpClient;
    }
    getAllCuentas(page, size, filterParams) {
        let pageParams = new HttpParams().append('page', page.toString()).append('size', size.toString());
        for (const key in filterParams) {
            let value;
            if (filterParams[key] instanceof Date) {
                let date = filterParams[key];
                value = `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`;
            }
            else {
                value = filterParams[key];
            }
            if (value.length > 0) {
                pageParams = pageParams.append(key, (filterParams[key] === '*') ? '' : value);
            }
        }
        return this.httpClient.get('../api/cuentas', { params: pageParams });
    }
    getCuentasByCompany(companyRfc) {
        return this.httpClient.get(`../api/empresas/${companyRfc}/cuentas`);
    }
};
CuentasService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root',
    }),
    tslib_1.__metadata("design:paramtypes", [HttpClient])
], CuentasService);
export { CuentasService };
//# sourceMappingURL=cuentas.service.js.map