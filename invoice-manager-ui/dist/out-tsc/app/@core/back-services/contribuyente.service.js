import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
let ContribuyenteService = class ContribuyenteService {
    constructor(httpClient) {
        this.httpClient = httpClient;
    }
    getContribuyentes(page, size, filterParams) {
        let pageParams = new HttpParams().append('page', page.toString()).append('size', size.toString());
        for (const key in filterParams) {
            const value = filterParams[key];
            if (value.length > 0) {
                pageParams = pageParams.append(key, filterParams[key]);
            }
        }
        return this.httpClient.get('../api/contribuyentes', { params: pageParams });
    }
};
ContribuyenteService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root'
    }),
    tslib_1.__metadata("design:paramtypes", [HttpClient])
], ContribuyenteService);
export { ContribuyenteService };
//# sourceMappingURL=contribuyente.service.js.map