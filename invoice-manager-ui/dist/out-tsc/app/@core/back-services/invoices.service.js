import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
let InvoicesService = class InvoicesService {
    constructor(httpClient) {
        this.httpClient = httpClient;
    }
    getInvoices(page, size, filterParams) {
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
        return this.httpClient.get('../api/facturas', { params: pageParams });
    }
    getInvoiceByFolio(folio) {
        return this.httpClient.get(`../api/facturas/${folio}`);
    }
    getInvoiceFiles(folio) {
        return this.httpClient.get(`../api/facturas/${folio}/files`);
    }
    getComplementosInvoice(folioPadre) {
        return this.httpClient.get(`../api/facturas/${folioPadre}/complementos`);
    }
    timbrarFactura(folio, factura) {
        return this.httpClient.post(`../api/facturas/${folio}/timbrar`, factura);
    }
    cancelarFactura(folio, factura) {
        return this.httpClient.post(`../api/facturas/${folio}/cancelar`, factura);
    }
    insertNewInvoice(invoice) {
        return this.httpClient.post('../api/facturas', invoice);
    }
    updateInvoice(invoice) {
        return this.httpClient.put(`../api/facturas/${invoice.folio}`, invoice);
    }
    getCfdiByFolio(folio) {
        return this.httpClient.get(`../api/facturas/${folio}/cfdi`);
    }
    insertConcepto(folio, concepto) {
        return this.httpClient.post(`../api/facturas/${folio}/conceptos`, concepto);
    }
    updateConcepto(folio, id, concepto) {
        return this.httpClient.put(`../api/facturas/${folio}/conceptos/${id}`, concepto);
    }
    deleteConcepto(folio, conceptoId) {
        return this.httpClient.delete(`../api/facturas/${folio}/conceptos/${conceptoId}`);
    }
};
InvoicesService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root'
    }),
    tslib_1.__metadata("design:paramtypes", [HttpClient])
], InvoicesService);
export { InvoicesService };
//# sourceMappingURL=invoices.service.js.map