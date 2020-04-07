import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
let DevolutionService = class DevolutionService {
    constructor(httpClient) {
        this.httpClient = httpClient;
    }
    getDevolutions(page, size, filterParams) {
        let pageParams = new HttpParams().append('page', page.toString()).append('size', size.toString());
        for (const key in filterParams) {
            const value = filterParams[key];
            if (value.length > 0) {
                pageParams = pageParams.append(key, (filterParams[key] === '*') ? '' : filterParams[key]);
            }
        }
        return this.httpClient.get('../api/devoluciones', { params: pageParams });
    }
    getDevolutionsByReceptor(tipoReceptor, idReceptor, status) {
        if (status === '*') {
            return this.httpClient.get(`../api/devoluciones/receptor/${tipoReceptor}/${idReceptor}`);
        }
        else {
            let pageParams = new HttpParams().append('statusDevolucion', status);
            return this.httpClient.get(`../api/devoluciones/receptor/${tipoReceptor}/${idReceptor}`, { params: pageParams });
        }
    }
    requestMultipleDevolution(solicitud) {
        return this.httpClient.post('../api/devoluciones', solicitud);
    }
    getDevolutionsByPayment(payment) {
        return this.httpClient.get(`../api/pagos/${payment}/devoluciones`);
    }
    updateDevolutionAsPaid(payment) {
        return this.httpClient.post('../api/devoluciones/pago', payment);
    }
};
DevolutionService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root'
    }),
    tslib_1.__metadata("design:paramtypes", [HttpClient])
], DevolutionService);
export { DevolutionService };
//# sourceMappingURL=devolution.service.js.map