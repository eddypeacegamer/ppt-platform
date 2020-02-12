import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
let CompaniesService = class CompaniesService {
    constructor(httpClient) {
        this.httpClient = httpClient;
    }
    getCompanies(page, size, filterParams) {
        let pageParams = new HttpParams().append('page', page.toString()).append('size', size.toString());
        for (const key in filterParams) {
            const value = filterParams[key];
            if (value.length > 0) {
                pageParams = pageParams.append(key, filterParams[key]);
            }
        }
        return this.httpClient.get('../api/empresas', { params: pageParams });
    }
    getCompaniesByLineaAndGiro(linea, giro) {
        return this.httpClient.get(`../api/lineas/${linea}/giros/${giro}/empresas`);
    }
    getCompanyByRFC(rfc) {
        return this.httpClient.get(`../api/empresas/${rfc}`);
    }
    insertNewCompany(empresa) {
        return this.httpClient.post('../api/empresas', empresa);
    }
    updateCompany(rfc, empresa) {
        return this.httpClient.put(`../api/empresas/${rfc}`, empresa);
    }
};
CompaniesService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root'
    }),
    tslib_1.__metadata("design:paramtypes", [HttpClient])
], CompaniesService);
export { CompaniesService };
//# sourceMappingURL=companies.service.js.map