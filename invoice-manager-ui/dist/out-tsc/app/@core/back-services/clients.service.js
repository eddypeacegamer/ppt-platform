import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
let ClientsService = class ClientsService {
    constructor(httpClient) {
        this.httpClient = httpClient;
    }
    getClients(page, size, filterParams) {
        let pageParams = new HttpParams().append('page', page.toString()).append('size', size.toString());
        for (const key in filterParams) {
            if (filterParams[key] !== undefined) {
                const value = filterParams[key];
                if (value.length > 0 && value !== '*') {
                    pageParams = pageParams.append(key, value);
                }
            }
        }
        return this.httpClient.get('../api/clientes', { params: pageParams });
    }
    getClientByRFC(rfc) {
        return this.httpClient.get(`../api/clientes/${rfc}`);
    }
    insertNewClient(client) {
        return this.httpClient.post('../api/clientes', client);
    }
    updateClient(cliente) {
        return this.httpClient.put(`../api/clientes/${cliente.informacionFiscal.rfc}`, cliente);
    }
};
ClientsService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root',
    }),
    tslib_1.__metadata("design:paramtypes", [HttpClient])
], ClientsService);
export { ClientsService };
//# sourceMappingURL=clients.service.js.map